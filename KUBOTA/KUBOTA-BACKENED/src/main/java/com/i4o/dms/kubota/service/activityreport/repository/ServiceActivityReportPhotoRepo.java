package com.i4o.dms.kubota.service.activityreport.repository;

import com.i4o.dms.kubota.service.activityreport.domain.ServiceActivityReportPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceActivityReportPhotoRepo extends JpaRepository<ServiceActivityReportPhotos,Long> {
}
