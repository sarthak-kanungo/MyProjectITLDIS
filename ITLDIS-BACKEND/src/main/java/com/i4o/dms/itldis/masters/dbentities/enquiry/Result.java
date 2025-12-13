package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "enquiry_lost_drop_result")
public class Result
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String result;
}
