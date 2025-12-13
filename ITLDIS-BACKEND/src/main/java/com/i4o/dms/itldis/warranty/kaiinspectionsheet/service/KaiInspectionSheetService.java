package com.i4o.dms.itldis.warranty.kaiinspectionsheet.service;

import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain.KaiInspectionSheet;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface KaiInspectionSheetService {

    ApiResponse saveKaiInspectionSheet(@RequestPart(value = "kaiInspectionSheet")KaiInspectionSheet kaiInspectionSheet,
                                       @RequestPart List<MultipartFile> multipartFileList);
}

