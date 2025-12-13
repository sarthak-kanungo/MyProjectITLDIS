package com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.configurations.MessageConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Entity
@Getter
@Setter
@Table(name="CM_MST_transporter")
public class TransporterMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "transporter Name can`t be blank")
    @Column(length = 50)
    private String transporterName;

    @Column(length = 50)
    private String code;

    @NotBlank(message = "transporter Location can`t be blank")
    @Column(length = 50)
    private String transporterLocation;

    @Column(length = 50)
    private String gstNumber;

    @Column(length = 50)
    private String panNumber;

    @Column(length = 50)
    private String adharCardNumber;

    @NotBlank(message = "title can`t be blank")
    @Column(length = 50)
    private String title;

    @NotBlank(message = "first Name can`t be blank")
    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String middleName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String designation;

    @NotBlank(message = "mobile Number can`t be blank")
    @Column(length = 50)
    private String mobile;

    @Column(length = 50)
    @Email(message = MessageConstants.EMAIL_NOT_VALID)
    private String email;

    @Column(length = 50)
    private String telephone;

    @NotBlank(message = "address1 can`t be blank")
    @Column(length = 50)
    private String address1;

    @Column(length = 50)
    private String address2;

    @Column(length = 50)
    private String address3;

    @NotBlank(message = "Pin Code can't be blank")
    @Column(length = 50)
    private String pinCode;

    @NotBlank(message = "locality can`t be blank")
    @Column(length = 50)
    private String locality;

    @Column(length = 50)
    private String tehsil;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String district;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate=new Date();
}
