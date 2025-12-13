package com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

// This Entity need to be changed based on the All Dealer Master updation

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "ADM_DEALER_DEPOT_MAP")
public class DealerDepotMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String dealerCode;

    @Column(length = 50)
    private String dealerName;

    private Character activeStatus='Y';

    @Column(length = 50)
    private String depot;

    private Character defaultDepot;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();
}
