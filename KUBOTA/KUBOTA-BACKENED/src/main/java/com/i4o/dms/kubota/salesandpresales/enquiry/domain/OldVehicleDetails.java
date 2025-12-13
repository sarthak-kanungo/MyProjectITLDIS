package com.i4o.dms.kubota.salesandpresales.enquiry.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sa_old_veh_inv")
public class OldVehicleDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7674036705352186582L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long oldVehId;
	private Long branchId;
	private Long enquiryId;
	private String brandName;
	private String modelName;
	private Integer modelYear;
	private Date invInDate;
	private Double estimatedExchangePrice;
	private String status;
	private Long buyerId;
	private String buyerName;
	private String buyerContactNo;
	private Date saledate;
	private Double sellingprice;
	private String saleremarks;
	private Long createdby;
	private Date createddate;
	private Long modifiedby;
	private Date modifieddate;
}
