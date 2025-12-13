package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "SA_channel_finance_indent_detail")
public class ChannelFinanceIntentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;

    @Column(length = 15)
    private double invoiceAmount;

    @Column(length = 15)
    private double utilisedAmount=0.0;

    @Column(length = 50)
    private String ageing;

    @Column(length = 50)
    private String status;

    @Column(length = 15)
    private double channelFinanceAmount;

//    @Column(length = 50)
//    private String bankName;
//
//    @Column(length = 50)
//    private String flexiAccountNumber;

    @Column(length = 50)
    private String invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "channel_finance_indent_id")
    @JsonBackReference
    private ChannelFinanceIndent channelFinanceIndent;

    @Override
    public String toString() {
        return "ChannelFinanceIntentDetail{" +
                "id=" + id +
                ", invoiceDate=" + invoiceDate +
                ", invoiceAmount=" + invoiceAmount +
                ", utilisedAmount=" + utilisedAmount +
                ", ageing='" + ageing + '\'' +
                ", status='" + status + '\'' +
                ", channelFinanceAmount=" + channelFinanceAmount +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", channelFinanceIndent=" + channelFinanceIndent +
                '}';
    }
}
