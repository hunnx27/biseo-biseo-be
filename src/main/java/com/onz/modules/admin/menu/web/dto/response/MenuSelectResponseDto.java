package com.onz.modules.admin.menu.web.dto.response;

import com.onz.modules.admin.menu.domain.Menu;
import lombok.Getter;

@Getter
public class MenuSelectResponseDto {
    private String link;
    private Long subCode;
    private String subject;
    private String role;

    public MenuSelectResponseDto(Menu menu){
        this.link=menu.getLink();
        this.subject=menu.getSubject();
        this.subCode=menu.getSubCode();
        this.role=menu.getRole();
    }

}
