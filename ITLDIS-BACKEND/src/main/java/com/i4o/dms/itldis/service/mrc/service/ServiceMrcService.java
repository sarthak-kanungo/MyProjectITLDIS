package com.i4o.dms.itldis.service.mrc.service;

import com.i4o.dms.itldis.service.mrc.domain.ServiceMrc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
public interface ServiceMrcService {

    void saveServiceMrc(@RequestPart(value = "serviceMrc") ServiceMrc serviceMrc,
                        @RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList);

}
