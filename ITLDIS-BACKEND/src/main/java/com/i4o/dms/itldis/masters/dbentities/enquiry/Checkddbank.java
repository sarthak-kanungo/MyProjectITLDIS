package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "CM_MST_bank")
public class Checkddbank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cheque;
}
