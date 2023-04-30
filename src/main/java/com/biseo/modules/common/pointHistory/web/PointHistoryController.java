//package com.onz.modules.common.pointHistory.web;
//
//import com.onz.common.web.BaseApiController;
//import com.onz.modules.common.pointHistory.application.PointHistoryService;
//import com.onz.modules.common.pointHistory.domain.PointHistory;
//import com.onz.modules.common.pointHistory.web.dto.request.PointHistoryCreateRequest;
//import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
//import com.onz.modules.common.pointHistory.web.dto.request.PointHistoryUpdateRequest;
//import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class PointHistoryController extends BaseApiController {
//
//    private final PointHistoryService pointService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping("/pointHistories")
//    public Page<PointHistoryResponse> list(PointHistorySearchRequest pointSearchRequest) {
//        return pointService.list(pointSearchRequest);
//    }
//
//}
