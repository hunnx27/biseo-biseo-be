package com.onz.modules.common.code.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@SequenceGenerator(
        name = "COMMON_CODE_SEQ_GENERATOR"
        , sequenceName = "COMMON_CODE_SEQ"
        , initialValue = 2656
        , allocationSize = 1
)
@Getter
@Setter
@NoArgsConstructor
public class CommonCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(unique = true)
    private Long id;

    private String code;

    private String codeSebu;

    private String codeName;
    @ColumnDefault("'Y'")
    @Enumerated(EnumType.STRING)
    private YN useYn;

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime createDt;


    private String bigo;

    @Builder
    public CommonCode(String code, String codeSebu, String codeName, YN useYn) {
        this.code = code;
        this.codeSebu = codeSebu;
        this.codeName = codeName;
        this.useYn = useYn;
    }
}
