package com.onz.modules.common.pointHistory.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class PointTableConverter implements AttributeConverter<PointTable, String> {
    @Override
    public String convertToDatabaseColumn(PointTable pointTable) {
        String pointTableCode = "";
        if(pointTable != null){
            pointTableCode = pointTable.getCode();
        }
        return pointTableCode;
    }

    @Override
    public PointTable convertToEntityAttribute(String dbData) {
        PointTable pointTable = null;
        if( dbData != null && !"".equals(dbData) ){
            pointTable = EnumSet.allOf(PointTable.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return pointTable;
    }
}
