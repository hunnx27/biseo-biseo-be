package com.onz.modules.common.address.web.dto;

import com.onz.modules.common.address.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private int sidoCode;
    private String sidoName;
    private int sigunguCode;
    private String sigunguName;

    public AddressResponse(Address address) {
        this.id = address.getId();
        this.sidoCode = address.getSidoCode();
        this.sidoName = address.getSidoName();
        this.sigunguCode = address.getSigunguCode();
        this.sigunguName = address.getSigunguName();
    }
}
