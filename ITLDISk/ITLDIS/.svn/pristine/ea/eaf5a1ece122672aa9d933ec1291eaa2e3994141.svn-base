/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_jobcarddetails")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class Jobcarddetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Column(name = "DealerCode")
    private String dealerCode;
    @Column(name = "DealerJCNo")
    private String dealerJCNo;
    @Column(name = "JobCardDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobCardDate;
    @Column(name = "vinno")
    private String vinno;
    @Column(name = "engineno")
    private String engineno;
    @Column(name = "vin_details_type")
    private String vinDetailsType;
    @Basic(optional = false)
    @Column(name = "vinid")
    private String vinid;
    @Column(name = "couponNo")
    private String couponNo;
    @Column(name = "JobTypeId")
    private Integer jobTypeId;
    @Column(name = "ProductCatId")
    private Integer productCatId;
    @Column(name = "HMRWorking")
    private Character hMRWorking;
    @Column(name = "HMR")
    private Long hmr;
    @Column(name = "EventId")
    private Integer eventId;
    @Column(name = "LocationId")
    private Integer locationId;
    @Column(name = "NextService")
    private String nextService;
    @Column(name = "WarrantyStatus")
    private String warrantyStatus;
    @Column(name = "Is_warranty_req")
    private Character iswarrantyreq;
    @Column(name = "ServiceTypeId")
    private Integer serviceTypeId;
    @Column(name = "PromiseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promiseDate;
    @Column(name = "ReqCustDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reqCustDate;
    @Column(name = "EstDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date estDate;
    @Column(name = "CurrentEstimateTime")
    private String currentEstimateTime;
    @Column(name = "CurrentEstimateHrs")
    private String currentEstimateHrs;
    @Column(name = "jobCardPriority")
    private String jobCardPriority;
    @Column(name = "ActualDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualDate;
    @Column(name = "WorkStartDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workStartDate;
    @Column(name = "WorkFinishDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date workFinishDate;
    @Column(name = "VehicleOutDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vehicleOutDate;
    @Column(name = "BayDesc")
    private String bayDesc;
    @Column(name = "MechanicName")
    private String mechanicName;
    @Column(name = "InspectedBy")
    private String inspectedBy;
    @Column(name = "PayeeName")
    private String payeeName;
    @Column(name = "village")
    private String village;
    @Column(name = "Tehsil")
    private String tehsil;
    @Column(name = "District")
    private String district;
    @Column(name = "Pincode")
    private Long pincode;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "MobilePhone")
    private Long mobilePhone;
    @Column(name = "Landline")
    private String landline;
    @Column(name = "emailid")
    private String emailid;
    @Column(name = "ttl_estimate_parts_amt")
    private Double ttlEstimatePartsAmt;
    @Column(name = "ttl_estimate_lubes_amt")
    private Double ttlEstimateLubesAmt;
    @Column(name = "ttl_estimate_other_charges_amt")
    private Double ttlEstimateOtherChargesAmt;
    @Column(name = "ttl_estimate_labour_charges_amt")
    private Double ttlEstimateLabourChargesAmt;
    @Column(name = "ttl_actual_parts_amt")
    private Double ttlActualPartsAmt;
    @Column(name = "ttl_actual_lubes_amt")
    private Double ttlActualLubesAmt;
    @Column(name = "ttl_actual_other_charges_amt")
    private Double ttlActualOtherChargesAmt;
    @Column(name = "ttl_actual_labour_charges_amt")
    private Double ttlActualLabourChargesAmt;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "LastModifiedBy")
    private String lastModifiedBy;
    @Column(name = "LastModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "DE_TMS_APPROVAL")
    private String deTmsApproval;
    @Column(name = "DE_REMARKS")
    private String deRemarks;
    @Column(name = "DE_APPROVED_BY")
    private String deApprovedBy;
    @Column(name = "DE_APPROVED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deApprovedOn;
    @Column(name = "IS_TMS_SYNC")
    private Character isTmsSync;
    @Column(name = "LAST_TMS_SYNC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastTmsSync;
    @Column(name = "TMS_REF_NO")
    private String tmsRefNo;
    @Column(name = "TMS_REF_SYNC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmsRefSyncDate;
    @Column(name = "WTY_CLAIM_STATUS")
    private String wtyClaimStatus;
    @Column(name = "COMPLAINT_REQ")
    private String complaintReq;

    //IsVinValidated
    @Column(name = "IsVinValidated")
    private String isVinValidated;
    @Column(name = "ttl_actual_amount")
    private Double ttlActualAmount;
    @Column(name = "ttl_discount")
    private Double ttlDiscount;
    @Column(name = "OPEN_REJECT_REMARKS")
    private String openRejectRemarks;
    @Column(name = "REASON_FOR_DELAY")
    private String reasonForDealy;
    @Column(name = "ComplaintDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date complaintDate;
    @Column(name="ITL_EW_Status")
    private String itlEwStatus;
  
    public String getReasonForDealy() {
        return reasonForDealy;
    }

    public void setReasonForDealy(String reasonForDealy) {
        this.reasonForDealy = reasonForDealy;
    }

    public String getOpenRejectRemarks() {
        return openRejectRemarks;
    }

    public void setOpenRejectRemarks(String openRejectRemarks) {
        this.openRejectRemarks = openRejectRemarks;
    }

    public Double getTtlDiscount() {
        return ttlDiscount;
    }

    public void setTtlDiscount(Double ttlDiscount) {
        this.ttlDiscount = ttlDiscount;
    }

    public Double getTtlActualAmount() {
        return ttlActualAmount;
    }

    public void setTtlActualAmount(Double ttlActualAmount) {
        this.ttlActualAmount = ttlActualAmount;
    }


    
    public String getIsVinValidated() {
        return isVinValidated;
    }

    public void setIsVinValidated(String isVinValidated) {
        this.isVinValidated = isVinValidated;
    }

    

    public Jobcarddetails() {
    }

    public Jobcarddetails(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public Jobcarddetails(String jobCardNo, String vinid, String status) {
        this.jobCardNo = jobCardNo;
        this.vinid = vinid;
        this.status = status;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerJCNo() {
        return dealerJCNo;
    }

    public void setDealerJCNo(String dealerJCNo) {
        this.dealerJCNo = dealerJCNo;
    }

    public Date getJobCardDate() {
        return jobCardDate;
    }

    public void setJobCardDate(Date jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getVinDetailsType() {
        return vinDetailsType;
    }

    public void setVinDetailsType(String vinDetailsType) {
        this.vinDetailsType = vinDetailsType;
    }

    public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public Integer getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Integer productCatId) {
        this.productCatId = productCatId;
    }

    public Character getHMRWorking() {
        return hMRWorking;
    }

    public void setHMRWorking(Character hMRWorking) {
        this.hMRWorking = hMRWorking;
    }

    public Long getHmr() {
        return hmr;
    }

    public void setHmr(Long hmr) {
        this.hmr = hmr;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }



    public String getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }

    public Character getIswarrantyreq() {
        return iswarrantyreq;
    }

    public void setIswarrantyreq(Character iswarrantyreq) {
        this.iswarrantyreq = iswarrantyreq;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Date getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(Date promiseDate) {
        this.promiseDate = promiseDate;
    }

    public Date getReqCustDate() {
        return reqCustDate;
    }

    public void setReqCustDate(Date reqCustDate) {
        this.reqCustDate = reqCustDate;
    }

    public Date getEstDate() {
        return estDate;
    }

    public void setEstDate(Date estDate) {
        this.estDate = estDate;
    }

    public String getCurrentEstimateTime() {
        return currentEstimateTime;
    }

    public void setCurrentEstimateTime(String currentEstimateTime) {
        this.currentEstimateTime = currentEstimateTime;
    }

    public String getCurrentEstimateHrs() {
        return currentEstimateHrs;
    }

    public void setCurrentEstimateHrs(String currentEstimateHrs) {
        this.currentEstimateHrs = currentEstimateHrs;
    }

    public String getJobCardPriority() {
        return jobCardPriority;
    }

    public void setJobCardPriority(String jobCardPriority) {
        this.jobCardPriority = jobCardPriority;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public Date getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        this.workStartDate = workStartDate;
    }

    public Date getWorkFinishDate() {
        return workFinishDate;
    }

    public void setWorkFinishDate(Date workFinishDate) {
        this.workFinishDate = workFinishDate;
    }

    public Date getVehicleOutDate() {
        return vehicleOutDate;
    }

    public void setVehicleOutDate(Date vehicleOutDate) {
        this.vehicleOutDate = vehicleOutDate;
    }

    public String getBayDesc() {
        return bayDesc;
    }

    public void setBayDesc(String bayDesc) {
        this.bayDesc = bayDesc;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getInspectedBy() {
        return inspectedBy;
    }

    public void setInspectedBy(String inspectedBy) {
        this.inspectedBy = inspectedBy;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public Double getTtlEstimatePartsAmt() {
        return ttlEstimatePartsAmt;
    }

    public void setTtlEstimatePartsAmt(Double ttlEstimatePartsAmt) {
        this.ttlEstimatePartsAmt = ttlEstimatePartsAmt;
    }

    public Double getTtlEstimateLubesAmt() {
        return ttlEstimateLubesAmt;
    }

    public void setTtlEstimateLubesAmt(Double ttlEstimateLubesAmt) {
        this.ttlEstimateLubesAmt = ttlEstimateLubesAmt;
    }

    public Double getTtlEstimateOtherChargesAmt() {
        return ttlEstimateOtherChargesAmt;
    }

    public void setTtlEstimateOtherChargesAmt(Double ttlEstimateOtherChargesAmt) {
        this.ttlEstimateOtherChargesAmt = ttlEstimateOtherChargesAmt;
    }

    public Double getTtlEstimateLabourChargesAmt() {
        return ttlEstimateLabourChargesAmt;
    }

    public void setTtlEstimateLabourChargesAmt(Double ttlEstimateLabourChargesAmt) {
        this.ttlEstimateLabourChargesAmt = ttlEstimateLabourChargesAmt;
    }

    public Double getTtlActualPartsAmt() {
        return ttlActualPartsAmt;
    }

    public void setTtlActualPartsAmt(Double ttlActualPartsAmt) {
        this.ttlActualPartsAmt = ttlActualPartsAmt;
    }

    public Double getTtlActualLubesAmt() {
        return ttlActualLubesAmt;
    }

    public void setTtlActualLubesAmt(Double ttlActualLubesAmt) {
        this.ttlActualLubesAmt = ttlActualLubesAmt;
    }

    public Double getTtlActualOtherChargesAmt() {
        return ttlActualOtherChargesAmt;
    }

    public void setTtlActualOtherChargesAmt(Double ttlActualOtherChargesAmt) {
        this.ttlActualOtherChargesAmt = ttlActualOtherChargesAmt;
    }

    public Double getTtlActualLabourChargesAmt() {
        return ttlActualLabourChargesAmt;
    }

    public void setTtlActualLabourChargesAmt(Double ttlActualLabourChargesAmt) {
        this.ttlActualLabourChargesAmt = ttlActualLabourChargesAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getDeTmsApproval() {
        return deTmsApproval;
    }

    public void setDeTmsApproval(String deTmsApproval) {
        this.deTmsApproval = deTmsApproval;
    }

    public String getDeRemarks() {
        return deRemarks;
    }

    public void setDeRemarks(String deRemarks) {
        this.deRemarks = deRemarks;
    }

    public String getDeApprovedBy() {
        return deApprovedBy;
    }

    public void setDeApprovedBy(String deApprovedBy) {
        this.deApprovedBy = deApprovedBy;
    }

    public Date getDeApprovedOn() {
        return deApprovedOn;
    }

    public void setDeApprovedOn(Date deApprovedOn) {
        this.deApprovedOn = deApprovedOn;
    }

    public Character getIsTmsSync() {
        return isTmsSync;
    }

    public void setIsTmsSync(Character isTmsSync) {
        this.isTmsSync = isTmsSync;
    }

    public Date getLastTmsSync() {
        return lastTmsSync;
    }

    public void setLastTmsSync(Date lastTmsSync) {
        this.lastTmsSync = lastTmsSync;
    }

    public String getTmsRefNo() {
        return tmsRefNo;
    }

    public void setTmsRefNo(String tmsRefNo) {
        this.tmsRefNo = tmsRefNo;
    }

    public Date getTmsRefSyncDate() {
        return tmsRefSyncDate;
    }

    public void setTmsRefSyncDate(Date tmsRefSyncDate) {
        this.tmsRefSyncDate = tmsRefSyncDate;
    }

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }

    
//    public Integer getJobid() {
//        return jobid;
//    }
//
//    public void setJobid(Integer jobid) {
//        this.jobid = jobid;
//    }


    public String getWtyClaimStatus() {
        return wtyClaimStatus;
    }

    public void setWtyClaimStatus(String wtyClaimStatus) {
        this.wtyClaimStatus = wtyClaimStatus;
    }

    public String getComplaintReq() {
		return complaintReq;
	}

	public void setComplaintReq(String complaintReq) {
		this.complaintReq = complaintReq;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (jobCardNo != null ? jobCardNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobcarddetails)) {
            return false;
        }
        Jobcarddetails other = (Jobcarddetails) object;
        if ((this.jobCardNo == null && other.jobCardNo != null) || (this.jobCardNo != null && !this.jobCardNo.equals(other.jobCardNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Jobcarddetails[jobCardNo=" + jobCardNo + "]";
    }

	public String getItlEwStatus() {
		return itlEwStatus;
	}

	public void setItlEwStatus(String itlEwStatus) {
		this.itlEwStatus = itlEwStatus;
	}
	
	@Column(name = "vorJobCard")
    private String vorJobCard;

	public String getVorJobCard() {
		return vorJobCard;
	}

	public void setVorJobCard(String vorJobCard) {
		this.vorJobCard = vorJobCard;
	}
    
	
	
    



}
