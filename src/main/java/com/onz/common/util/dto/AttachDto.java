package com.onz.common.util.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDto {
    /** 파일 번호 (PK) */
    private Long idx;
    /** Entity 번호 (FK) */
    private Long entityId;
    /** 원본 파일명 */
    private String originalName;
    /** 저장 파일명 */
    private String saveName;
    /** Upload File Path */
    private String filePath;
    /** 파일 크기 */
    private long size;
}
