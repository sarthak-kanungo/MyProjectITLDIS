package com.i4o.dms.kubota.warranty.pcr.service;

import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;
import com.i4o.dms.kubota.warranty.pcr.dto.PcrApprovalDto;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface WarrantyPcrService {

    ApiResponse saveWarrantyPcr(@RequestPart(value = "warrantyPcr") WarrantyPcr warrantyPcr,
                                @RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList);

    String approvedPcr(PcrApprovalDto pcrApprovalDto);
    
    
    ApiResponse<?> getPcrPendingForApproval();
}
