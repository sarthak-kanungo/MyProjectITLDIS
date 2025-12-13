package com.i4o.dms.itldis.masters.kaicommonmaster.assignorghierarchytodealer.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class DealerHoDepartmentId implements Serializable {

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "dealer_id")
//    private DealerMaster dealerMaster;
//
//    @Column(name = "dealer_code", nullable = false)
//    private String dealerCode;
//
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "department_id")
//    private KubotaDepartmentMaster departmentMaster;

	@NotNull
	private Long dealerId;

	@NotNull
	private Long hoDepartmentId;

	
	
}
