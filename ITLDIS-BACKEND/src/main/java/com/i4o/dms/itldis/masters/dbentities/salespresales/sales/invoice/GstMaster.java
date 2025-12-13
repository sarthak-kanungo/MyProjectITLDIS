package com.i4o.dms.itldis.masters.dbentities.salespresales.sales.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "mt_gst_master")
public class GstMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gstPercent;

}
