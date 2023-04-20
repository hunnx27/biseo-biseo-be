package com.onz.common.util;

import com.onz.common.exception.FileException;
import com.onz.common.util.dto.AttachDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class FileUtil {
    // Paths.get()으로 운영체제에 따라서 다른 파일구분자 처리
//    public final static String rootPath = Paths.get("C:", "Users", "jihun.park", "Desktop", "testFile").toString();
    @Value("${file.path.root}")
    private String ROOT_PATH;
    @Value("${file.path.data}")
    private String DATA_PATH;

    /**
     * MultipartFile 형태의 파일을 Attachments Entity 형태로 파싱
     *
     * @param multipartFiles
     */
    public List<AttachDto> uploadFiles(List<MultipartFile> multipartFiles, Long id, String customPath) throws Exception {

        // 파일이 첨부되지 않았을 경우
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return Collections.emptyList();
        }

        // 파일 업로드 경로 생성
        Path saveDirPath = Paths.get(DATA_PATH, customPath);
        String fullPath = Paths.get(ROOT_PATH, saveDirPath.toString()).toString();
        if (!new File(fullPath).exists()) {
            try {
                new File(fullPath).mkdirs();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        List<AttachDto> fileList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            String origFilename = multipartFile.getOriginalFilename();
            if (origFilename == null || "".equals(origFilename)) continue;
            String filename = renameFile(FilenameUtils.getBaseName(origFilename)).toString() + "." + FilenameUtils.getExtension(origFilename);
            //String filename = origFilename;
            String filePath = Paths.get(fullPath, filename).toString();
            String tmpSavePathString = Paths.get(saveDirPath.toString(), filename).toString();
            String savePathString = "/" + tmpSavePathString.toString().replaceAll("\\\\","/");

            try {
                File target = new File(filePath);
                multipartFile.transferTo(target);

                // 파일 권한 설정(쓰기, 읽기)
                target.setWritable(true);
                target.setReadable(true);

                /* 파일 정보 저장 */
                AttachDto attach = new AttachDto();
                attach.setEntityId(id);
                attach.setOriginalName(origFilename);
                attach.setSaveName(savePathString);
                attach.setSize(multipartFile.getSize());
                attach.setFilePath(filePath);
                fileList.add(attach);

            } catch (IOException e) {
                throw new FileException("[" + multipartFile.getOriginalFilename() + "] failed to save file... : " + e.getMessage());

            } catch (Exception e) {
                throw new FileException("[" + multipartFile.getOriginalFilename() + "] failed to save file... : " + e.getMessage());
            }
        }

        return fileList;
    }

    public List<AttachDto> uploadFiles(List<MultipartFile> multipartFiles, Long id) throws Exception {
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMM");
        String today = formatter.format(new java.util.Date());
        List<AttachDto> fileList = this.uploadFiles(multipartFiles, id, today);

        return fileList;
    }

    /**
     * 다운로드 받을 파일 생성
     * @param fileName
     * @return
     */
    public File getDownloadFile(String fileName) {
        File file = new File(Paths.get(ROOT_PATH).toString(), fileName);
        return file;
    }

    /**
     * 파일명 중복 방지를 위해 MD5(128비트 암호화 해시 함수) 파일명 생성
     *
     * @param input
     */
    public String MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(input.getBytes("UTF-8"));

        byte[] md5Hash = mdMD5.digest();
        StringBuilder hexMD5hash = new StringBuilder();

        for(byte b : md5Hash) {
            String hexString = String.format("%02x", b);
            hexMD5hash.append(hexString);
        }

        return hexMD5hash.toString();
    }

    public String renameFile(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(new Date());
        input = date+"-"+this.MD5Generator(input);
        return input;
    }

    /**
     * MediaType 생성
     *
     * @param filename
     */
    public MediaType getMediaType(String filename) {

        String contentType = FilenameUtils.getExtension(filename);
        MediaType mediaType = null;

        if (contentType.equals("png")) {
            mediaType = MediaType.IMAGE_PNG;
        } else if (contentType.equals("jpeg") || contentType.equals("jpg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (contentType.equals("gif")) {
            mediaType = MediaType.IMAGE_GIF;
        }

        return mediaType;
    }

}
