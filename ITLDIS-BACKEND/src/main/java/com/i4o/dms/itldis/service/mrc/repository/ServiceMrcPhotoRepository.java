package com.i4o.dms.itldis.service.mrc.repository;

import com.i4o.dms.itldis.service.mrc.domain.ServiceMrcPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMrcPhotoRepository extends JpaRepository<ServiceMrcPhotos,Long> {
}
