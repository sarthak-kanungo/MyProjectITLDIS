package com.modernapp.services.repository;

import com.modernapp.services.model.DealerVsLocationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealerVsLocationCodeRepository extends JpaRepository<DealerVsLocationCode, String> {
    
    Optional<DealerVsLocationCode> findByDealerCode(String dealerCode);
    
    List<DealerVsLocationCode> findByDealerNameContainingIgnoreCase(String dealerName);
}

