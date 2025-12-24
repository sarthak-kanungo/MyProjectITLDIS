package com.modernapp.services.service;

import com.modernapp.services.model.JobCard;
import com.modernapp.services.repository.JobCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobCardService {
    
    @Autowired
    private JobCardRepository jobCardRepository;

    public List<JobCard> getAllJobCards() {
        return jobCardRepository.findAll();
    }

    public Optional<JobCard> getJobCardById(Long id) {
        return jobCardRepository.findById(id);
    }

    public Optional<JobCard> getJobCardByJobCardNo(String jobCardNo) {
        return jobCardRepository.findByJobCardNo(jobCardNo);
    }

    public List<JobCard> getJobCardsByStatus(String status) {
        return jobCardRepository.findByStatus(status);
    }

    public List<JobCard> getJobCardsByVinNo(String vinNo) {
        return jobCardRepository.findByVinNo(vinNo);
    }

    public List<JobCard> searchJobCardsByCustomerName(String customerName) {
        return jobCardRepository.findByCustomerNameContainingIgnoreCase(customerName);
    }

    public JobCard saveJobCard(JobCard jobCard) {
        return jobCardRepository.save(jobCard);
    }

    public JobCard updateJobCard(Long id, JobCard jobCardDetails) {
        JobCard jobCard = jobCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Card not found with id: " + id));
        
        jobCard.setVinNo(jobCardDetails.getVinNo());
        jobCard.setCustomerName(jobCardDetails.getCustomerName());
        jobCard.setModelFamily(jobCardDetails.getModelFamily());
        jobCard.setEngineNumber(jobCardDetails.getEngineNumber());
        jobCard.setRegistrationNo(jobCardDetails.getRegistrationNo());
        jobCard.setJobType(jobCardDetails.getJobType());
        jobCard.setJobLocation(jobCardDetails.getJobLocation());
        jobCard.setServiceType(jobCardDetails.getServiceType());
        jobCard.setStatus(jobCardDetails.getStatus());
        
        return jobCardRepository.save(jobCard);
    }

    public JobCard approveJobCard(Long id) {
        JobCard jobCard = jobCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Card not found with id: " + id));
        jobCard.setStatus("APPROVED");
        return jobCardRepository.save(jobCard);
    }

    public JobCard closeJobCard(Long id) {
        JobCard jobCard = jobCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Card not found with id: " + id));
        jobCard.setStatus("CLOSED");
        return jobCardRepository.save(jobCard);
    }

    public void deleteJobCard(Long id) {
        jobCardRepository.deleteById(id);
    }
}

