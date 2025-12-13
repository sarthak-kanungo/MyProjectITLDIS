package com.i4o.dms.kubota.warranty.goodwill.service;

import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.kubota.warranty.goodwill.dto.GoodwillApprovalDto;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface GoodwillService {

    ApiResponse saveGoodwill(@RequestPart(value = "goodwill") WarrantyGoodwill warrantyGoodwill,
                             @RequestPart List<MultipartFile> multipartFileList);
    
    String approvedGwl(GoodwillApprovalDto pcrApprovalDto);
}
