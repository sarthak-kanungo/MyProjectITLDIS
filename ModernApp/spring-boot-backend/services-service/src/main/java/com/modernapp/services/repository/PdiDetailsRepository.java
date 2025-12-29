package com.modernapp.services.repository;

import com.modernapp.services.model.PdiDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PdiDetailsRepository extends JpaRepository<PdiDetails, String> {
    
    List<PdiDetails> findByVinNoContainingIgnoreCase(String vinNo);
    
    List<PdiDetails> findByDealercode(String dealercode);
    
    List<PdiDetails> findByPdiDateBetween(LocalDate fromDate, LocalDate toDate);
    
    @Query("SELECT p FROM PdiDetails p WHERE p.vinNo LIKE %:vinNo% AND p.dealercode = :dealercode")
    List<PdiDetails> findByVinNoAndDealercode(@Param("vinNo") String vinNo, @Param("dealercode") String dealercode);
}

