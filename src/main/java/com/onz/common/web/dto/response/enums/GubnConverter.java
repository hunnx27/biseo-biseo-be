package com.onz.common.web.dto.response.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class GubnConverter implements AttributeConverter<Gubn, String> {
    @Override
    public String convertToDatabaseColumn(Gubn gubn) {
        String gubnCode = "";
        if(gubn != null){
            gubnCode = gubn.getCode();
        }
        return gubnCode;
    }

    @Override
    public Gubn convertToEntityAttribute(String dbData) {
        Gubn gubn = null;
        if(dbData != null && !"".equals(dbData)){
            gubn = EnumSet.allOf(Gubn.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return gubn;
    }
}
