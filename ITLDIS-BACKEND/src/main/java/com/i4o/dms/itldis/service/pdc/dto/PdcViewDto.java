package com.i4o.dms.itldis.service.pdc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter

public class PdcViewDto {

    private PdcViewHeaderResponse pdcViewHeaderResponse;
    private List<Map<String,Object>> pdcCheckpointList;
}
