package com.onz.modules.admin.event.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Converter
public class LanddingConverter implements AttributeConverter<Landding, String> {

    @Override
    public String convertToDatabaseColumn(Landding landding) {
        return landding.getValue();
    }

    @Override
    public Landding convertToEntityAttribute(String dbData) {
        Landding landding = null;
        if(dbData != null && !"".equals(dbData)){
            landding = EnumSet.allOf(Landding.class).stream()
                    .filter(e->e.getValue().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return landding;
    }
}