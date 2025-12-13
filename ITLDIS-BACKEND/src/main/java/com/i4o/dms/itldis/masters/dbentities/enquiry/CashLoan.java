package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cash_loan")
public class CashLoan {
    @Id
    private Long id;
    private String cashLoanType;

}
