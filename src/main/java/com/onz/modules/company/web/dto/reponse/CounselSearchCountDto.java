package com.onz.modules.company.web.dto.reponse;

import com.onz.modules.counsel.domain.enums.QnaItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselSearchCountDto {
    List<QnaItemInfo> qnaList;

    @Getter
    public static class QnaItemInfo{
        private String code;
        private String name;
        private Long cnt=0l;

        @Builder
        public QnaItemInfo(QnaItem item, long cnt) {
            this.code = item.name();
            this.name = item.getName();
            this.cnt = cnt;
        }
    }
}
