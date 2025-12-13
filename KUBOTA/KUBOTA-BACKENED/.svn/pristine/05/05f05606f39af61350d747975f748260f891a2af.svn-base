package com.i4o.dms.kubota.masters.dbentities.grn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"transporterLocation", "gstNumber", "panNumber", "adharCardNumber", "title", "firstName", "middleName",
        "lastName", "designation", "mobile", "email","telephone","address1","address2","address3","pinCode","locality","tehsil","city","state","country"})
@Table(name="CM_MST_transporter")
public class Transporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transporterName;

    private String transporterLocation;

    private String gstNumber;

    private String panNumber;

    private String adharCardNumber;

    private String title;

    private String firstName;

    private String middleName;

    private String lastName;

    private String designation;

    private String mobile;

    private String email;

    private String telephone;

    private String address1;

    private String address2;

    private String address3;

    private String pinCode;

    private String locality;

    private String tehsil;

    private String city;

    private String state;

    private String country;

}
