package com.onz.modules.admin.member.livemember.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepository;
import com.onz.modules.admin.member.livemember.web.dto.*;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LiveMemberService {

    private final LiveMemberRepository liveMemberRepository;

    public LiveMemberResponseWrapDto liveMember(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<LiveMemberResponseDto> liveMemberListResult = liveMemberRepository.findByLiveMember(liveMemberRequestDto, pageable);
        LiveMemberResponseDto liveMemberTotalResult = liveMemberRepository.findByLiveMemberTotal(liveMemberRequestDto);
        JPQLQuery<Long> liveMemberTotalCnt = liveMemberRepository.findCountMember(liveMemberRequestDto);

        LiveMemberResponseWrapDto result3 = new LiveMemberResponseWrapDto(liveMemberTotalCnt.fetchOne(), liveMemberTotalResult, liveMemberListResult);
//        List<LiveMemberResponseDto> result = liveMemberResponseDtos.stream().map(e -> {
//            LiveMemberResponseDto rs = new LiveMemberResponseDto(e);
//            // 기관리뷰 개수
//            Long companyReviewCnt = companyReviewRepository.countByAccount_Id(e.getId());
//            rs.setCompanyReviewCnt(companyReviewCnt);
//            // 인터뷰리뷰 개수
//            Long interviewReviewCnt = interviewReviewRepository.countByAccount_Id(e.getId());
//            rs.setInterviewReviewCnt(interviewReviewCnt);
//            // ...
//            // 연봉리뷰 개수
//            Long amtReviewCnt = amtReviewRepository.countByAccount_Id(e.getId());
//            rs.setAmtReviewCnt(amtReviewCnt);
//            // ...
//            // 상담리뷰 개수
//            Long counselQCnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(), QnaGubn.Q);
//            rs.setCounselQCnt(counselQCnt);
//            // ...
//            Long counselACnt = counselRepository.countByAccount_IdAndQnaGubn(e.getId(),QnaGubn.A);
//            rs.setCounselACnt(counselACnt);
//            return rs;
//        }).collect(Collectors.toList());
        return result3;
    }

    public LiveMemberDetailResponse liveMemberDetail(HttpServletResponse response, @PathVariable Long id) {
        return liveMemberRepository.findByAccountDetail(id);
    }

    public LiveMemberResponseWrapPDto liveMemberResponseWrapPDto(HttpServletResponse response, @PathVariable Long id, Pageable pageable) {
        List<LiveMemberPointListResponse> liveMemberPointListResponses = liveMemberRepository.findByAccountPointList(id, pageable);
        LiveMemberPointResponse liveMemberPointResponse = liveMemberRepository.findByAccountPointDetail(id);
//
        LiveMemberResponseWrapPDto result3 = new LiveMemberResponseWrapPDto(liveMemberPointResponse, liveMemberPointListResponses);
//
        return result3;
    }

    public static Sheet wbSetting(String[] headerStrings, Workbook wb){
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        /* 글꼴 */
        Sheet sheet = wb.createSheet("첫번째 시트");

        /* 스타일 */

        int idx = 0;
        Cell headerCell = null;
        Row headerRow = sheet.createRow(0);

        for(String s : headerStrings)
        {
            headerCell = headerRow.createCell(idx++);
            headerCell.setCellValue(s);
            headerCell.setCellStyle(headerCellStyle);
        }
        return sheet;
    }
    public static HttpServletResponse getFile(Workbook wb,HttpServletResponse response) {
        try {
            //excel
            String fileName = "  _" + DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss") + ".xls";
            //
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            //
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(">>>         ！" + e.getMessage());
        }
        return response;
    }


    public void getExcelFile(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto) throws IOException {
        //전체회원을 받아온다
        List<LiveMemberResponseDto> liveMemberListResult = liveMemberRepository.findByLiveMember(liveMemberRequestDto);

        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        wb.setCompressTempFiles(true);
        String[] headerStrings = {"회원구분","ID(메일주소)","SNS타입","포인트","리뷰","연봉","면접","상담(질문)","상담(답변)","가입일"};
        SXSSFSheet sheet = (SXSSFSheet) wb.createSheet();
        // 행
        SXSSFRow row = null;
        // 셀
        SXSSFCell cell = null;
        // 셀 헤더 카운트
        int index2 = 0;
        // 행 카운트
        row = sheet.createRow(0);
        for(String head : headerStrings ) {
            cell = row.createCell(index2++);
            cell.setCellValue(head);
        }

        sheet.setRandomAccessWindowSize(100);
        Row bodyRow = null;
        Cell bodyCell = null;
        int index = 1;
        // Body
        for (LiveMemberResponseDto liveMemberResponseDto : liveMemberListResult) {
            bodyRow = sheet.createRow(index++);
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(liveMemberResponseDto.getGubnName()==null?"":liveMemberResponseDto.getGubnName());
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(liveMemberResponseDto.getUserId()==null?"":liveMemberResponseDto.getUserId());
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(liveMemberResponseDto.getSnsTypeName()==null?"":liveMemberResponseDto.getSnsTypeName());
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(liveMemberResponseDto.getPoint()==null?0:liveMemberResponseDto.getPoint());
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(liveMemberResponseDto.getCompanyReviewCnt());
            bodyCell = bodyRow.createCell(5);
            bodyCell.setCellValue(liveMemberResponseDto.getAmtReviewCnt());
            bodyCell = bodyRow.createCell(6);
            bodyCell.setCellValue(liveMemberResponseDto.getInterviewReviewCnt());
            bodyCell = bodyRow.createCell(7);
            bodyCell.setCellValue(liveMemberResponseDto.getCounselQCnt());
            bodyCell = bodyRow.createCell(8);
            bodyCell.setCellValue(liveMemberResponseDto.getCounselACnt());
            bodyCell = bodyRow.createCell(9);
            bodyCell.setCellValue(liveMemberResponseDto.getCreatedAt()==null?"":liveMemberResponseDto.getCreatedAt());
        }

        for (int i=0; i<headerStrings.length; i++){
            sheet.trackAllColumnsForAutoSizing();
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
        }
        getFile(wb,response);
    }
}


/*

 */