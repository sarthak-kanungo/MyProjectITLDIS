package com.i4o.dms.kubota.salesandpresales.purchase.machinedescripancycomplaint.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="machine_descripancy_complaint_photo")
public class MachineDescripancyComplaintPhoto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fileName;    
    
    @Transient
    private MultipartFile file;

    @ManyToOne
    @JoinColumn(name = "machine_descripancy_complaint_id")
    @JsonBackReference
    private MachineDescripancyComplaint machineDescripancyComplaint;
}
