package com.i4o.dms.itldis.service.jobcard.repository;

import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobcardPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceJobcardPhotosRepo extends JpaRepository<ServiceJobcardPhotos,Long> {
}
