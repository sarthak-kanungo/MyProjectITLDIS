package com.modernapp.services.repository;

import com.modernapp.services.model.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobCardRepository extends JpaRepository<JobCard, Long> {
    Optional<JobCard> findByJobCardNo(String jobCardNo);
    List<JobCard> findByStatus(String status);
    List<JobCard> findByVinNo(String vinNo);
    List<JobCard> findByCustomerNameContainingIgnoreCase(String customerName);
}

