package com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.embedded.DealerDepartmentId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="ADM_DEALER_DEPT_MAP")
public class DealerDepartmentMapping implements Serializable {

    @EmbeddedId
    private DealerDepartmentId dealerDepartmentId;

    private Character activeStatus;

}
