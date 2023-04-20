package com.onz.modules.common.address.infra.dto;

import com.onz.modules.common.address.domain.Address;
import lombok.Getter;
import lombok.Setter;

public interface DistinctAddressResponse {
    int getSigunguCode();
    String getSidoName();
    String getSigunguName();
}
