package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "SA_ENQ_MST_CANCEL_REASON")
public class EnquiryCancelReason
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
}
