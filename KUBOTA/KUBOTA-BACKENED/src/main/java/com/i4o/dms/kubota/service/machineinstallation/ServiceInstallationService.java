package com.i4o.dms.kubota.service.machineinstallation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.service.machineinstallation.domain.ServiceMachineInstallation;

//photo upload
@Transactional
public interface ServiceInstallationService {

    void saveServiceMachineInstallation(@RequestPart(value = "serviceMachineInstallation") ServiceMachineInstallation serviceMachineInstallation,
                                        @RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList);

}

