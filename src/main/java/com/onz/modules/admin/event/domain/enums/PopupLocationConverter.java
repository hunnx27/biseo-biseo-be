package com.onz.modules.admin.event.domain.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

@Converter
public class PopupLocationConverter implements AttributeConverter<PopupLocation, String> {

    @Override
    public String convertToDatabaseColumn(PopupLocation popupLocation) {
        return popupLocation.getValue();
    }

    @Override
    public PopupLocation convertToEntityAttribute(String dbData) {
        PopupLocation popupLocation = null;
        if (dbData != null && !"".equals(dbData)) {
            popupLocation = EnumSet.allOf(PopupLocation.class).stream()
                    .filter(e -> e.getValue().equals(dbData))
                    .findAny()
                    .orElseThrow(() -> new NoSuchElementException());
        }
        return popupLocation;
    }
}