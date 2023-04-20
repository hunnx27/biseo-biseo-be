package com.onz.modules.company.domain.enums;

import com.onz.modules.counsel.domain.enums.QnaItem;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Deprecated
public class EstablishmentTypeConverter implements AttributeConverter<EstablishmentType, String> {
    @Override
    public String convertToDatabaseColumn(EstablishmentType type) {
        String typeCode = "";
        if(type != null){
            typeCode = type.getValue();
        }
        return typeCode;
    }

    @Override
    public EstablishmentType convertToEntityAttribute(String dbData) {
        EstablishmentType type = null;
        if( dbData != null && !"".equals(dbData) ){
            type = EnumSet.allOf(EstablishmentType.class).stream()
                    .filter(e->e.getValue().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return type;
    }
}
