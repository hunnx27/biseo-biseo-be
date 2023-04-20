package com.onz.modules.counsel.domain.enums;

import com.onz.modules.counsel.domain.enums.QnaItem;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class QnaItemConverter implements AttributeConverter<QnaItem, String> {
    @Override
    public String convertToDatabaseColumn(QnaItem qnaItem) {
        String qnaItemCode = "";
        if(qnaItem != null){
            qnaItemCode = qnaItem.getCode();
        }
        return qnaItemCode;
    }

    @Override
    public QnaItem convertToEntityAttribute(String dbData) {
        QnaItem qnaItem = null;
        if( dbData != null && !"".equals(dbData) ){
            qnaItem = EnumSet.allOf(QnaItem.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return qnaItem;
    }
}
