package com.onz.modules.company.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.*;
import java.util.stream.Collectors;

public class CharItemConverter implements AttributeConverter<List<CharItem>, String> {
    @Override
    public String convertToDatabaseColumn(List<CharItem> items) {
        String itemsValue = "";
        if(items != null){
            itemsValue = String.join(",", items.stream().map(charItem -> charItem.getValue()).collect(Collectors.toList()));
        }
        return itemsValue;
    }

    @Override
    public List<CharItem> convertToEntityAttribute(String dbDataStr) {
        List<CharItem> charItems = null;
        if( dbDataStr != null && !"".equals(dbDataStr) ){
            String[] dbDatas = dbDataStr.split(",");
            charItems = Arrays.stream(dbDatas).map(dbData -> {
                            CharItem charItem = EnumSet.allOf(CharItem.class).stream()
                                    .filter(e->e.getValue().equals(dbData))
                                    .findAny()
                                    .orElseThrow(()-> new NoSuchElementException());
                            return charItem;
                        }).collect(Collectors.toList());

        }
        return charItems;
    }
}
