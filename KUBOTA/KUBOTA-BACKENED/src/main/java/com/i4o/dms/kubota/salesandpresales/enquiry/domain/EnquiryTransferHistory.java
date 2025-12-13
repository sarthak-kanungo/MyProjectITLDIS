package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SA_ENQ_TRANSFER_HISTORY")
public class EnquiryTransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private DealerEmployeeMaster transferBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private DealerEmployeeMaster transferTo;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date transferDate;
}
