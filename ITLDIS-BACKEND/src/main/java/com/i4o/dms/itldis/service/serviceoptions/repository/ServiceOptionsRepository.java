package com.i4o.dms.itldis.service.serviceoptions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.service.serviceoptions.domain.ServiceOption;
import java.util.List;

/**
 * Service Options Repository
 */
@Repository
public interface ServiceOptionsRepository extends JpaRepository<ServiceOption, Long> {
    
    List<ServiceOption> findByDealerCode(String dealerCode);
}
