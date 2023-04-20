package com.onz.modules.admin.member.deluser.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.member.deluser.infra.DelUserRepository;
import com.onz.modules.admin.member.deluser.infra.HumanRepository;
import com.onz.modules.admin.member.deluser.web.dto.*;
import com.onz.modules.admin.member.livemember.application.LiveMemberService;
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
public class HumanService {

    private final HumanRepository humanRepository;

    public List<HumanListResponseDto> humanListResponseDtoList(HttpServletResponse response, HumanListRequestDto humanListRequestDto, Pageable pageable) throws CustomException {
        //전체회원을 받아온다
        List<HumanListResponseDto> humanListResponseDtoList = humanRepository.findByHumanList(humanListRequestDto,pageable);

        return humanListResponseDtoList;
    }
    public HumanResponseDto humanAccountDetail(@PathVariable Long id) throws CustomException {
        //전체회원을 받아온다
        HumanResponseDto humanResponseDto = humanRepository.findByHumanDetail(id);

        return humanResponseDto;
    }

    public void getExcelFile(HttpServletResponse response, HumanListRequestDto humanListRequestDto) throws IOException {
        //전체회원을 받아온다
        List<HumanListResponseDto> humanListResponseDtoList = humanRepository.findByHumanUser(humanListRequestDto);

        Workbook wb = new XSSFWorkbook();


        String[] headerStrings = {"회원구분","ID(메일주소)","SNS타입","최근접속일","가입일"};
        //front연결시 String[] string으로 date를 request받으면 안되나?

        Sheet sheet = LiveMemberService.wbSetting(headerStrings,wb);

        Row bodyRow = null;
        Cell bodyCell = null;
        int index = 1;
        // Body
        for (HumanListResponseDto humanListResponseDto : humanListResponseDtoList) {
            bodyRow = sheet.createRow(index++);
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(humanListResponseDto.getGubnName());
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(humanListResponseDto.getUserId());
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(humanListResponseDto.getSnsTypeName());
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(humanListResponseDto.getLastedAt());
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(humanListResponseDto.getCreatedAt());
        }
        for (int i=0; i<headerStrings.length; i++){
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)1024);
        }
        getFile(wb,response);
    }

}
