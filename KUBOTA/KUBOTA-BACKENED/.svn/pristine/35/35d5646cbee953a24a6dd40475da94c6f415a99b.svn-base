package com.i4o.dms.kubota.masters.postatus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@JsonIgnoreProperties(value = {"createdDate","lastModifiedDate"}, allowSetters = true)
@Entity
@Getter
@Setter
public class PurchaseOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "PO Status can`t be blank")
    @NotNull
    @Column(length = 10)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();
}
