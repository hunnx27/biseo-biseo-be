package com.onz.modules.common.image.web;

import com.onz.common.util.FileUtil;
import com.onz.common.web.ApiR;
import com.onz.modules.auth.web.dto.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
@Tag(name="파일 이미지 제어",description = "파일이미지 관련 api입니다.")
public class DownloadController {
    private final FileUtil fileUtil;
    private final ResourceLoader resourceLoader;


    @Operation(summary = "파일 업로드 주소변환 불러오기", description = "file 레코드의 업로드된 이미지의 주소를 받아옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = RequestBody.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = RequestBody.class)))
    })
    @GetMapping("/download/image")
    @ResponseBody
    public  ResponseEntity<ApiR<?>>  getImage(@RequestParam String path) throws IOException {
        try {
            File file = fileUtil.getDownloadFile(path);
            try {
//            Resource resource = resourceLoader.getResource(file.getPath());
                byte[] fileByte = FileUtils.readFileToByteArray(file);


//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION,file.getName())	//다운 받아지는 파일 명 설정
//                    .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()))	//파일 사이즈 설정
//                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString())	//바이너리 데이터로 받아오기 설정
//                    .body(fileByte);	//파일 넘기기

                Path pathObj = Paths.get(file.getPath());
                String contentType = Files.probeContentType(pathObj);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(
                        ContentDisposition.builder("attachment")
                                .filename(file.getName(), StandardCharsets.UTF_8)
                                .build());
                headers.add(HttpHeaders.CONTENT_TYPE, contentType);

                Resource resource = new InputStreamResource(Files.newInputStream(pathObj));
                return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(resource));

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

            }
        }catch (Exception e){
            throw e;
        }
    }

    @Operation(summary = "파일 업로드 주소변환 불러오기", description = "file 레코드의 업로드된 이미지의 주소를 받아옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = File.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = File.class)))
    })
    @GetMapping("/download/image2")
    @ResponseBody
    public  ResponseEntity<ApiR<?>>  getImage2(@RequestParam String path) throws IOException {
        try {
            File file = fileUtil.getDownloadFile(path);

            Resource resource = resourceLoader.getResource("file:" + file.getPath());
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(resource));
        }catch (Exception e){
            throw e;
        }
    }
}
