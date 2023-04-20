package com.onz.modules.admin.faq.domian.enums;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;
import java.util.NoSuchElementException;
@Converter
public class CategoryConverter implements AttributeConverter<Category,String> {

    @Override
    public String convertToDatabaseColumn(Category faq){
        return faq.getValue();
    }
    @Override
    public Category convertToEntityAttribute(String dbData){
        Category category = null;
        if(dbData != null && !"".equals(dbData)){
            category = EnumSet.allOf(Category.class).stream()
                    .filter(e->e.getValue().equals(dbData))
                    .findAny()
                    .orElseThrow(()-> new NoSuchElementException());
        }
        return category;
    }

}

//@Converter
//public class LanddingConverter implements AttributeConverter<Landding, String> {
//
//    @Override
//    public String convertToDatabaseColumn(Landding landding) {
//        return landding.getValue();
//    }
//
//    @Override
//    public Landding convertToEntityAttribute(String dbData) {
//        Landding landding = null;
//        if(dbData != null && !"".equals(dbData)){
//            landding = EnumSet.allOf(Landding.class).stream()
//                    .filter(e->e.getValue().equals(dbData))
//                    .findAny()
//                    .orElseThrow(()-> new NoSuchElementException());
//        }
//        return landding;
//    }
