package com.biseo.modules.common.pointHistory.web.dto.request;

import com.biseo.common.web.dto.request.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PointHistorySearchRequest extends BasePageRequest {


    private String code;
    private String name;
    private String address;
}
