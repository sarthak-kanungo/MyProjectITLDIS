package com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain;

import com.i4o.dms.kubota.masters.areamaster.model.District;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DealerDistrictMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dealer_master_id")
    private DealerMaster dealerMaster;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}
