package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class DealerBankDetailsId implements Serializable {
    @NotNull
    @Column(length = 50)
    private String dealerCode;

    @NotNull
    @Column(length = 50)
    private String bankName;

}
