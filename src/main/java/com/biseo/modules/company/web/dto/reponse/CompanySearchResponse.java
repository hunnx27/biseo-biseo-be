package com.biseo.modules.company.web.dto.reponse;

import com.biseo.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@NoArgsConstructor
@Slf4j
public class CompanySearchResponse {
    @JsonIgnore
    private Long id;
    private String officeName;
    private String zonecode;
    private String establishName;
    @Setter
    private String mapsidogunguName;

    public CompanySearchResponse(Long id, EstablishmentType establishmentType, String officeName, String zonecode) {
        this.id = id;
        this.establishName= establishmentType!=null ? establishmentType.getName() : EstablishmentType.C99.getName();
        this.officeName = officeName;
        this.zonecode = zonecode;
    }


}
