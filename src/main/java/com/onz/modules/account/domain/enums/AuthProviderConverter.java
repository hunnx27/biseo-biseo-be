package com.onz.modules.account.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AuthProviderConverter implements AttributeConverter<AuthProvider, String> {
    @Override
    public String convertToDatabaseColumn(AuthProvider authProvider) {
        return authProvider.getCode();
    }

    @Override
    public AuthProvider convertToEntityAttribute(String dbData) {
        AuthProvider authProvider = null;
        if(dbData != null && !"".equals(dbData)){
            authProvider = EnumSet.allOf(AuthProvider.class).stream()
                    .filter(e->e.getCode().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return authProvider;
    }
}
