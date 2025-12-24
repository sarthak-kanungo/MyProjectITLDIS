package com.modernapp.services.controller;

import com.modernapp.services.model.JobCard;
import com.modernapp.services.service.JobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services/job-cards")
@CrossOrigin(origins = "*")
public class JobCardController {

    @Autowired
    private JobCardService jobCardService;

    @GetMapping
    public ResponseEntity<List<JobCard>> getAllJobCards() {
        return ResponseEntity.ok(jobCardService.getAllJobCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCard> getJobCardById(@PathVariable Long id) {
        Optional<JobCard> jobCard = jobCardService.getJobCardById(id);
        return jobCard.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jobCardNo/{jobCardNo}")
    public ResponseEntity<JobCard> getJobCardByJobCardNo(@PathVariable String jobCardNo) {
        Optional<JobCard> jobCard = jobCardService.getJobCardByJobCardNo(jobCardNo);
        return jobCard.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobCard>> getJobCardsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(jobCardService.getJobCardsByStatus(status));
    }

    @GetMapping("/vinNo/{vinNo}")
    public ResponseEntity<List<JobCard>> getJobCardsByVinNo(@PathVariable String vinNo) {
        return ResponseEntity.ok(jobCardService.getJobCardsByVinNo(vinNo));
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobCard>> searchJobCards(@RequestParam String customerName) {
        return ResponseEntity.ok(jobCardService.searchJobCardsByCustomerName(customerName));
    }

    @PostMapping
    public ResponseEntity<JobCard> createJobCard(@RequestBody JobCard jobCard) {
        JobCard savedJobCard = jobCardService.saveJobCard(jobCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJobCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCard> updateJobCard(@PathVariable Long id, @RequestBody JobCard jobCard) {
        try {
            JobCard updatedJobCard = jobCardService.updateJobCard(id, jobCard);
            return ResponseEntity.ok(updatedJobCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<JobCard> approveJobCard(@PathVariable Long id) {
        try {
            JobCard approvedJobCard = jobCardService.approveJobCard(id);
            return ResponseEntity.ok(approvedJobCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<JobCard> closeJobCard(@PathVariable Long id) {
        try {
            JobCard closedJobCard = jobCardService.closeJobCard(id);
            return ResponseEntity.ok(closedJobCard);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobCard(@PathVariable Long id) {
        jobCardService.deleteJobCard(id);
        return ResponseEntity.noContent().build();
    }
}

