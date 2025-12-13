package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartySearchDto {
    private String partyCode;

    private String partyName;

    private Integer page;

    private Integer size;
}
