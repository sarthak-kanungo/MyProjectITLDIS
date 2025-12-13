package com.i4o.dms.kubota.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="SP_MT_SUPPLIER")
public class SparesMtSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCode;

    private String vendorName;

    private String firstName;

    private String middleName;

    private String lastName;

    @Column(length = 15)
    private String contactNumber;

    private String contactEmail;

    private String gstNumber;

    private String panNumber;

    private String designation;

    @Column(length = 15)
    private String fax;

    private String address1;

    private String address2;

    private String address3;

    @Column(length = 6)
    private Integer pinCode;

    private String postOffice;

    private String locality;

    private String taluka;

    private String district;

    private String state;

    private String country;
}
