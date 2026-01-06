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

    public com.modernapp.services.dto.VehicleInfoInitDataDTO getVehicleInfoInitData() {
        com.modernapp.services.dto.VehicleInfoInitDataDTO data = new com.modernapp.services.dto.VehicleInfoInitDataDTO();
        
        data.setJobTypes(java.util.List.of(
            new com.modernapp.services.dto.DropdownOptionDTO("Free Service", "1"),
            new com.modernapp.services.dto.DropdownOptionDTO("Paid Service", "2"),
            new com.modernapp.services.dto.DropdownOptionDTO("Running Repair", "3"),
            new com.modernapp.services.dto.DropdownOptionDTO("Accidantal", "4"),
            new com.modernapp.services.dto.DropdownOptionDTO("PDI", "5"),
            new com.modernapp.services.dto.DropdownOptionDTO("Campaign", "6")
        ));
        
        data.setLocations(java.util.List.of(
            new com.modernapp.services.dto.DropdownOptionDTO("Workshop", "W"),
            new com.modernapp.services.dto.DropdownOptionDTO("On Site", "S")
        ));

        data.setServiceTypes(java.util.List.of(
            new com.modernapp.services.dto.DropdownOptionDTO("1st Free Service", "101"),
            new com.modernapp.services.dto.DropdownOptionDTO("2nd Free Service", "102"),
             new com.modernapp.services.dto.DropdownOptionDTO("General Repair", "201")
        ));

        data.setProductCategories(java.util.List.of(
             new com.modernapp.services.dto.DropdownOptionDTO("Tractor", "TR"),
             new com.modernapp.services.dto.DropdownOptionDTO("Harvester", "HR")
        ));
        
        return data;
    }

    public com.modernapp.services.dto.VehicleDetailsDTO getVehicleDetails(String vinNo) {
        com.modernapp.services.dto.VehicleDetailsDTO details = new com.modernapp.services.dto.VehicleDetailsDTO();
        details.setVinNo(vinNo);
        // Mock data logic
        if (vinNo.toUpperCase().startsWith("MB1")) {
             details.setEngineNo("ENG-" + vinNo.substring(3));
             details.setModelFamily("M-Series");
             details.setModelCode("M-5000");
             details.setModelDescription("M-Series 50HP Tractor");
        } else {
             details.setEngineNo("ENG-UNKNOWN");
             details.setModelFamily("Unknown");
             details.setModelCode("UNK");
             details.setModelDescription("Unknown Model");
        }
        details.setSaleDate("15/08/2024");
        details.setSellingDealerCode("DLR001");
        details.setSellingDealerName("Kubota Dealer A");
        details.setPlateNo("MH-12-AB-1234");
        details.setCustomerName("John Doe");
        details.setCustomerId("CUST001");
        
        return details;
    }
    @jakarta.persistence.PersistenceContext
    private jakarta.persistence.EntityManager entityManager;

    public List<com.modernapp.services.dto.ViewJobCardDTO> getViewJobCards(String fromDate, String toDate, String status, String dealerCode, String searchColumn, String searchValue) {
        StringBuilder hql = new StringBuilder("SELECT new com.modernapp.services.dto.ViewJobCardDTO(" +
                "j.jobCardNo, j.jobCardDate, j.vinNo, j.customerName, d.dealerCode, d.dealerName, d.location, d.locationCode, j.status, j.mobilePhone) " +
                "FROM JobCard j, DealerVsLocationCode d WHERE j.dealerCode = d.dealerCode");

        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
            hql.append(" AND j.jobCardDate BETWEEN :fromDate AND :toDate");
        }
        
        if (status != null && !status.equalsIgnoreCase("ALL") && !status.isEmpty()) {
            hql.append(" AND j.status = :status");
        }
        
        if (dealerCode != null && !dealerCode.equalsIgnoreCase("ALL") && !dealerCode.isEmpty()) {
             hql.append(" AND j.dealerCode = :dealerCode");
        }
        
        if (searchValue != null && !searchValue.isEmpty()) {
             if (searchColumn != null && java.util.List.of("jobCardNo", "vinNo", "customerName", "mobilePhone").contains(searchColumn)) {
                  hql.append(" AND j." + searchColumn + " LIKE :searchValue");
             } else {
                  hql.append(" AND j.jobCardNo LIKE :searchValue");
             }
        }
        
        hql.append(" ORDER BY j.jobCardDate DESC");

        var query = entityManager.createQuery(hql.toString(), com.modernapp.services.dto.ViewJobCardDTO.class);
        
        if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
             java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
             // Parse start/end of day
             java.time.LocalDateTime start = java.time.LocalDate.parse(fromDate, formatter).atStartOfDay();
             java.time.LocalDateTime end = java.time.LocalDate.parse(toDate, formatter).atTime(23, 59, 59);
             query.setParameter("fromDate", start);
             query.setParameter("toDate", end);
        }
        
        if (status != null && !status.equalsIgnoreCase("ALL") && !status.isEmpty()) {
            query.setParameter("status", status);
        }
        
        if (dealerCode != null && !dealerCode.equalsIgnoreCase("ALL") && !dealerCode.isEmpty()) {
             query.setParameter("dealerCode", dealerCode);
        }
        
        if (searchValue != null && !searchValue.isEmpty()) {
             query.setParameter("searchValue", "%" + searchValue + "%");
        }
        
        return query.getResultList();
    }
}

