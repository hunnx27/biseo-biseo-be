package com.onz.common.web.dto.response.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class InterestCompanyConverter implements AttributeConverter<InterestCompany, String> {
    @Override
    public String convertToDatabaseColumn(InterestCompany intrsOrg) {
        String intrsOrgCoe = "";
        if(intrsOrg != null){
            intrsOrgCoe = intrsOrg.getCode();
        }
        return intrsOrgCoe;
    }

    @Override
    public InterestCompany convertToEntityAttribute(String dbData) {
        InterestCompany intrsOrg = null;
        if( dbData != null && !"".equals(dbData) ){
            intrsOrg = EnumSet.allOf(InterestCompany.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return intrsOrg;
    }
}
