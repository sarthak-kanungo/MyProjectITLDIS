package com.i4o.dms.kubota.service.mrc.repository;

import com.i4o.dms.kubota.service.mrc.domain.ServiceMrcPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMrcPhotoRepository extends JpaRepository<ServiceMrcPhotos,Long> {
}
