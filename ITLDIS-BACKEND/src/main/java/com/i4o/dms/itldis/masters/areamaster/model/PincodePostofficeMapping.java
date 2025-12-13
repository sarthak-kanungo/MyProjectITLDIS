package com.i4o.dms.itldis.masters.areamaster.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
public class PincodePostofficeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pincode_id")
    private PinCode pinCode;

    @JoinColumn(name = "postoffice_id")
    @ManyToOne
    private PostOffices postOffice;


}
