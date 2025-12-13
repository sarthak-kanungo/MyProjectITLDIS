package com.i4o.dms.itldis.spares.picklist.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SP_SALES_PICKLIST_RETURN_DTL")
public class PickListReturn {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long picklistReturnId;
	
	private Long picklistDtlId;
	
	private Integer returnQty;
	
	private Long createdBy;
	
	private Date createdDate = new Date();
	
}
