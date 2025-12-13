package com.i4o.dms.kubota.accpac.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AccpacSparesDealerOutstanding {

    @Id
    @Column(unique = true)
    private String dealerCode;

    private Double creditLimit;

    private Double currentOutStanding;

    private Double overDuesOutStanding;

    private Double paymentUnderProcess;

    private Double orderUnderProcess;

}
