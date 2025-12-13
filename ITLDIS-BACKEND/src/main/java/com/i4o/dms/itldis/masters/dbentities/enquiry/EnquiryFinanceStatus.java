package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SA_ENQ_MST_FINANCE_STATUS")
public class EnquiryFinanceStatus
{
    @Id
    private Long id;
    private String finalStatus;

}
