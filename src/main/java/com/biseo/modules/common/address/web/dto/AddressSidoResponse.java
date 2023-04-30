package com.biseo.modules.common.address.web.dto;

import com.biseo.modules.common.address.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressSidoResponse {
    private int sidoCode;
    private String sidoName;

    public AddressSidoResponse(Address addressCode) {
        this.sidoCode = addressCode.getSidoCode();
        this.sidoName = addressCode.getSidoName();
    }
}
