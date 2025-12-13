package com.i4o.dms.kubota.warranty.logsheet.service;

import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheet;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface WarrantyLogsheetService {

    ApiResponse saveWarrantyLogsheet(@RequestPart(value = "warrantyLogsheet") WarrantyLogsheet warrantyLogsheet,@RequestPart("multipartFileList") List<MultipartFile> multipartFileList);
}
