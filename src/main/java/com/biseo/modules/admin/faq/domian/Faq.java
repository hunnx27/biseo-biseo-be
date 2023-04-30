package com.biseo.modules.admin.faq.domian;

import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.admin.faq.domian.enums.Category;
import com.biseo.modules.admin.faq.domian.enums.CategoryConverter;
import com.biseo.modules.admin.faq.web.dto.FaqCreateRequestDto;
import com.biseo.modules.admin.notice.domain.enums.DeviceGubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    @Enumerated(EnumType.STRING)
    private DeviceGubn deviceGubn= DeviceGubn.P;
    @Convert(converter = CategoryConverter.class)
    private Category category= Category.CG07;
    private String title;
    private String content;
    private ZonedDateTime createDt;
    @Enumerated(EnumType.STRING)
    private YN useYn;

    public Faq(FaqCreateRequestDto faqCreateRequestDto) {
        this.deviceGubn = DeviceGubn.valueOf(faqCreateRequestDto.getDeviceGubn());
        this.category = Category.valueOf(faqCreateRequestDto.getCategory());
        this.title = faqCreateRequestDto.getTitle();
        this.content = faqCreateRequestDto.getContent();
        this.useYn = YN.valueOf(faqCreateRequestDto.getUseYn());
    }
}
