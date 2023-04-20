package com.onz.modules.admin.member.deluser.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.admin.member.deluser.infra.DelUserRepository;
import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserResponse;
import com.onz.modules.admin.member.livemember.application.LiveMemberService;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepository;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseWrapDto;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.onz.modules.admin.member.livemember.application.LiveMemberService.getFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DelUserService {

    private final DelUserRepository delUserRepository;

    public List<DelUserListResponseDto> delUserList(HttpServletResponse response, DelUserRequestDto delUserRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<DelUserListResponseDto> delUserListResponse = delUserRepository.findByDelUser(delUserRequestDto,pageable);

        return delUserListResponse;
    }

    public DelUserResponse delUserDetail(@PathVariable Long id) throws CustomException {
        //전체회원을 받아온다
        DelUserResponse delUserListResponse = delUserRepository.findByDelUserDetail(id);

        return delUserListResponse;
    }

    public void getExcelFile(HttpServletResponse response, DelUserRequestDto delUserRequestDto) throws IOException {
        //전체회원을 받아온다
        List<DelUserListResponseDto> liveMemberListResult = delUserRepository.findByDelUser(delUserRequestDto);

        Workbook wb = new XSSFWorkbook();


        String[] headerStrings = {"회원구분","ID(메일주소)","SNS타입","삭제일","가입일"};
        //front연결시 String[] string으로 date를 request받으면 안되나?

        Sheet sheet = LiveMemberService.wbSetting(headerStrings,wb);

        Row bodyRow = null;
        Cell bodyCell = null;
        int index = 1;
        // Body
        for (DelUserListResponseDto delMemberResponseDto : liveMemberListResult) {
            bodyRow = sheet.createRow(index++);
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(delMemberResponseDto.getGubnName());
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(delMemberResponseDto.getUserId());
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(delMemberResponseDto.getSnsTypeName());
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(delMemberResponseDto.getDeletedAt());
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(delMemberResponseDto.getCreatedAt());
        }
        for (int i=0; i<headerStrings.length; i++){
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
        }
        getFile(wb,response);
    }
}
