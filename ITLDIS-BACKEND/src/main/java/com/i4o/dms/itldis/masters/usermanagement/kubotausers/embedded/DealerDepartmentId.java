package com.i4o.dms.itldis.masters.usermanagement.kubotausers.embedded;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaDepartmentMaster;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class DealerDepartmentId implements Serializable {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @Column(name = "dealer_code", nullable = false)
    private String dealerCode;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private KubotaDepartmentMaster departmentMaster;
}
