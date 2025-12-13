package com.i4o.dms.kubota.masters.dealermaster.customermaster.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "customer_vehicle_detail")

public class CustomerVehicleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @NotBlank(message = "model code can't be null")
    private String modelCode;


    private String modelDesc;

    @Column(length = 50)
    @NotBlank(message = "chassis no  code can't be null")
    private String chassisNo;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "registration no code can't be null")
    private String registrationNo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private CustomerMaster customerMaster;

}
