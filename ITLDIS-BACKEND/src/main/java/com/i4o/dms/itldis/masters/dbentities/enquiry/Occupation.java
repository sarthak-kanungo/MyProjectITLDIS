package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "occupation")
public class Occupation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String occupation;

}
