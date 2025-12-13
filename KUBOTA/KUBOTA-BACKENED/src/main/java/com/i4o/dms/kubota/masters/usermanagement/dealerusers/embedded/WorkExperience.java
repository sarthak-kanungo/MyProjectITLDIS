package com.i4o.dms.kubota.masters.usermanagement.dealerusers.embedded;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//@Entity
public class WorkExperience {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
    private String companyName;

    private Date fromDate;

    private Date toDate;

    private String designation;

    private String role;

}
