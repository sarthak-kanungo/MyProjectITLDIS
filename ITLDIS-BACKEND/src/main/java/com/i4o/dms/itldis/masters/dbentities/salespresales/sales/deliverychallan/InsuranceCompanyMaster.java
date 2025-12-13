package com.i4o.dms.itldis.masters.dbentities.salespresales.sales.deliverychallan;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cm_mst_insurance_company")
public class InsuranceCompanyMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyCode;

    private String companyName;

    private String address1;

    private String address2;

    private String address3;

    private Integer pinCode;

    private String locality;

    private String tehsil;

    private String city;

    private String state;

    private String country;

    private String std;

    private Long telephoneNumber;

    @Email
    private String email;

}
