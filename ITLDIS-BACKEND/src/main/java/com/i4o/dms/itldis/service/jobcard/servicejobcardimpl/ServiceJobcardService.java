package com.i4o.dms.itldis.service.jobcard.servicejobcardimpl;

import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.itldis.service.mrc.domain.ServiceMrc;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ServiceJobcardService {

    ApiResponse saveJobCard(@RequestPart(value = "serviceJobcard")ServiceJobCard serviceJobCard,
                            @RequestPart(value = "fsCouponPage1") MultipartFile fsCouponPage1,
                            @RequestPart(value = "fsCouponPage1") MultipartFile fsCouponPage2,
                            @RequestPart(value = "hourMeterPhoto") MultipartFile hourMeterPhoto,
                            @RequestPart(value = "chassisPhoto") MultipartFile chassisPhoto,
                			@RequestPart(value = "signedJobcard") MultipartFile signedJobcard,
                			@RequestPart(value = "retroAcknowledgementForm") MultipartFile retroAcknowledgementForm);
}
