package com.onz.modules.admin.menu.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.admin.menu.web.dto.response.MenuSelectResponseDto;
import com.onz.modules.admin.menu.web.dto.request.MenuRequsetDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;


    private String role;

    @NotNull
    private Long mainCode;

    @NotNull
    private Long subCode;

    @NotNull
    private String subject;

    public Menu(MenuRequsetDto menuRequsetDto){
        this.link=menuRequsetDto.getLink();
        this.mainCode=menuRequsetDto.getMainCode();
        this.subCode=menuRequsetDto.getSubCode();
        this.subject=menuRequsetDto.getSubject();
        this.role=menuRequsetDto.getRole();
    }
    public Menu(MenuSelectResponseDto menuSelectResponseDto){
        this.link= menuSelectResponseDto.getLink();
        this.role= menuSelectResponseDto.getRole();
        this.subject= menuSelectResponseDto.getSubject();
        this.subCode= menuSelectResponseDto.getSubCode();
    }
}
