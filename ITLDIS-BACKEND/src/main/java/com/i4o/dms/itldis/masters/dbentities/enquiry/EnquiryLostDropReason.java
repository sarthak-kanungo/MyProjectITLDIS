package com.i4o.dms.itldis.masters.dbentities.enquiry;



import javax.persistence.*;

@Entity
@Table(name = "SA_ENQ_MST_REASON")
public class EnquiryLostDropReason
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lostDropReason;
    private  String result;

}
