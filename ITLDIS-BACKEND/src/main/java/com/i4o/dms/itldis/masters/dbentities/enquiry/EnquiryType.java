package com.i4o.dms.itldis.masters.dbentities.enquiry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "SA_ENQ_MST_ENQUIRY_TYPE")
public class EnquiryType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false)
    @NotBlank(message = "enquiry type can not be blank")
    private String enquiryType;
    private Integer days;
    @Column(length =5)
    @NotBlank(message = "marketingactivity status can not be null")
    private String activeStatus="Y";


}
