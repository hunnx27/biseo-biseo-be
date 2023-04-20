package com.onz.modules.admin.notice.domain.enums;

import com.onz.modules.account.domain.enums.AuthProvider;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class DeviceGubnConverter implements AttributeConverter<DeviceGubn, String>{
        @Override
        public String convertToDatabaseColumn(DeviceGubn deviceGubn) {
            return deviceGubn.name();
        }

        @Override
        public DeviceGubn convertToEntityAttribute(String dbData) {
            DeviceGubn deviceGubn = null;
            if(dbData != null && !"".equals(dbData)){
                deviceGubn = EnumSet.allOf(DeviceGubn.class).stream()
                        .filter(e->e.getName().equals(dbData))
                        .findAny()
                        .orElseThrow(()-> new NoSuchElementException());
            }
            return deviceGubn;
        }
    }
