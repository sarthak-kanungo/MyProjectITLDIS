/*
package com.i4o.dms.itldis.salesandpresales.sales.invoice.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDeliveryChallanMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @JsonBackReference
    private SalesInvoice invoice;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    private DeliveryChallan deliveryChallan;

    private Boolean isDelete;

}
*/
