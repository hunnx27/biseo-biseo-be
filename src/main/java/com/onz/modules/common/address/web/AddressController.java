package com.onz.modules.common.address.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.common.address.domain.Address;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.web.dto.AddressResponse;
import com.onz.modules.common.address.web.dto.AddressSidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Tag(name = "지역 제어", description = "지역을 제어하는 api.")
public class AddressController extends BaseApiController {
    AddressRepository addressRepository;

    @Operation(summary = "주소 불러오기", description = "주소 레코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AddressResponse.class)))
    })
    @GetMapping("/common/address")
    public ResponseEntity<ApiR<?>> getAllAddress() {
        try {
            List<Address> result = addressRepository.findAll();

            List<AddressResponse> result2 = result.stream().map(AddressResponse::new)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "시도 그룹화해서 불러오기", description = "시,도를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = AddressSidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AddressSidoResponse.class)))
    })
    @GetMapping("/common/address/sido")
    public ResponseEntity<ApiR<?>> getAddressGroupBySidoCode() {
        try {
            List<Address> result = addressRepository.findByAddressGroupBySidoCide();

            List<AddressSidoResponse> result2 = result.stream().map(AddressSidoResponse::new)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result2));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "SidoCode 불러오기", description = "불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = AddressResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AddressResponse.class)))
    })
    @GetMapping("/common/address/sido/{sidoCode}")
    public ResponseEntity<ApiR<?>> getAddressBySidoCode(@PathVariable int sidoCode, Pageable pageable) {
        try {
            Page<Address> result = addressRepository.findBySidoCode(sidoCode, pageable);

            List<AddressResponse> result2 = result.stream().map(AddressResponse::new)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result2));
        } catch (Exception e) {
            throw e;
        }
    }

}
