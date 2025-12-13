package com.i4o.dms.kubota.salesandpresales.enquiry.dto;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;
import java.util.Set;
@Getter
@Setter
public class TransferEnquiryDto {
    private Set<Long> enquiryId;
    private DealerEmployeeMaster transferToEmployeeId;
    private DealerEmployeeMaster transferByEmployeeId;

}
