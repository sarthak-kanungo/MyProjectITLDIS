package com.i4o.dms.kubota.masters.dbentities.enquiry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SA_ENQ_MST_ENQUIRY_TYPE")

public class EnquiryProspectType
{
    @Id
    private long id;
    private String prospectType;



}
