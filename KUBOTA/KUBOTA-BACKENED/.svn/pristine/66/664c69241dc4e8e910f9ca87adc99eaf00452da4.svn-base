package com.i4o.dms.kubota.service.jobcard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineVinMaster;
import com.i4o.dms.kubota.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.kubota.service.jobcard.domain.OutsideJobCharge;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobcardPhotos;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobcardRetroMapping;

import org.springframework.beans.factory.annotation.Value;


import java.util.Date;
import java.util.List;

public interface JobCardViewDto {

    Long getId();
    String getJobCardNo();
    Double getPreviousHour();
    String getRegistrationNumber();
    Double getTotalHour();
    Double getMeterChangeHour();
    Boolean getPcrRaiseFlag();
    Double getCurrentHour();
    String getCustomerConcern();
   /* Boolean getInvoiceFlag();
    Boolean getPreinvoiceFlag();*/
    Date getMachineOutDateTime();
    String getOutDateTime();
	String getCloseDelayReason();
    void setOutDateTime(String outDateTime);
    String getCloseRemark();
    String getPcrNo();
    String getClosedDate();
    String getSoldDealer();
    Boolean getPcrApprovedFlag();
    void setPcrApprovedFlag(Boolean pcrApprovedFlag);
    Date getInstallationDate();
    Boolean getGoodwillRaised();
    void setGoodwillRaised(Boolean goodwillRaised);
    
    Boolean getGoodwillRequired();
    void setGoodwillRequired(Boolean goodwillRequired);
    
    Boolean getGoodwillApproved();
    void setGoodwillApproved(Boolean goodwillApproved);
    
    Boolean getAllowVideoUpload();
    void setAllowVideoUpload(Boolean allowVideoUpload);
    
    String getGwNo();
    void setGwNo(String gwNo);
    
    String setPcrNo(String pcrNo);
    Boolean getInvoicedFlag();
    Boolean getDraftFlag();
    String getFinalActionTaken();
    String getSuggestionToCustomer();
    String getMechanicObservation();
    Date getDateOfFailure();
    Boolean getPartIssueFlag();
    Boolean setPartIssueFlag(Boolean partIssueFlag);
    String getStatus();
    @JsonFormat(pattern = "yyyy-MM-dd")
    String getEstimatedCompletionDate();
    String getEstimatedAmount();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getJobCardDate();
    ServiceMtCategory getServiceCategory();
    interface ServiceMtCategory{
        Long getId();
        String getCategory();
    }
    String getPlaceOfService();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    Date getTaskTime();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getTaskDate();
    String getCustomerName();
    String getAlternateNumber();
    String getMobileNumber();

    @JsonProperty("address")
    String getCustomerAddress();

    CustomerMaster getCustomerMaster();
    interface  CustomerMaster{
        Long getId();

    }
    ServiceMtServiceTypeInfo getServiceType();
    interface  ServiceMtServiceTypeInfo{
        Long getId();
        String getServiceType();

    }
    MachineInventory getMachineInventory();
    interface MachineInventory{
        Long getVinId();
        String getChassisNo();
        String getEngineNo();
        @JsonFormat(pattern = "dd-MM-yyyy")
        Date getInstallationDate();
        String getCsbNumber();
        String getRegistrationNumber();
        MachineMaster getMachineMaster();
        interface MachineMaster{
            Long getId();
            String getModel();
        }
        /*WarrantyWcrView.DealerMaster getDealerMaster();
        interface  DealerMaster{
            Long getId();
            @JsonProperty("serviceDealer")
            String getDealerName();
            @JsonProperty("serviceDealerAddress")
            String getShowroomAddress1();
            String getEmailId();
            String getDealerCode();
            String getMobileNo();
        }*/

    }
    DealerEmployeeMaster getMechanic();
    DealerEmployeeMaster getServiceRepresentative();
    interface  DealerEmployeeMaster{
        Long getId();
        void setFirstName(String name);

        @JsonProperty("name")
        @Value("#{target.firstName} #{target.lastName}")
        String getFirstName();
        @JsonIgnore
        String getLastName();
    }



    ServiceBooking getServiceBooking();
    interface  ServiceBooking{
        Long getId();
        String getBookingNo();
        String getBookingDate();
    }



    List<SparePartRequisitionItem> getSparePartRequisitionItem();
    interface  SparePartRequisitionItem{
        @JsonProperty("sparePartId")
        Long getId();
        Boolean getQtyUpdateFlag();
        Long getPcrQuantity();
        SparePartMaster getSparePartMaster();
        @JsonProperty("approveQuantity")
        Integer getApprovedQuantity();
        Integer getUtilisedQuantity();
        interface SparePartMaster{
            Long getId();
            @JsonProperty("partNumber")
            String getItemNo();
            String getItemDescription();
            Double getSpmrp();
        }
        @JsonProperty("quantity")
        Long getReqQuantity();
        String getUom();
        SpareMtPartCategory getCategory();
        interface  SpareMtPartCategory{
            Long getId();
            String getCategory();
        }

        @JsonProperty("mrp")
        Double getPriceUnit();
        Double getAmount();
//        Boolean getIsSelected();

    }
    ServiceActivityProposal.ServiceMtActivityType getServiceMtActivityType();
    interface ServiceMtActivityType{
        Long getId();
        String getActivityType();

    }
    ServiceActivityProposal getServiceActivityProposal();
    interface  ServiceActivityProposal{
        Long getId();
        String getActivityNumber();
        ServiceMtActivityType getServiceMtActivityType();
        interface ServiceMtActivityType{
            Long getId();
            String getActivityType();

        }

    }
    List<LabourJobCharges> getLabourCharge();
    List<OutsideJobCharge> getOutsideJobCharge();

//    List<CustomerConcerns> getCustomerConcerns();
//    List<CustomerConcerns> setCustomerConcerns(List<CustomerConcerns> customerConcerns);

//    List<MechanicConcerns> getMechanicConcerns();
//    void setMechanicConcerns(List<MechanicConcerns> mechanicConcerns);

    List<ServiceJobcardPhotos> getServiceJobcardPhotos();
    
    void setServiceJobcardPhotos(List<ServiceJobcardPhotos> ServiceJobcardPhotos);

    Boolean getIsLatest();
    void setIsLatest(Boolean isLatest);

    String getWarrantyEndDate();
    void setWarrantyEndDate(String warrantyEndDate);
    
    Double getTotalWarrantyHour();
    void setTotalWarrantyHour(Double totalWarrantyHour);
    
    //Suraj-24-04-2023-START
    List<ServiceJobcardRetroMapping> getJobcardRetroMappings();	
    interface ServiceJobcardRetroMapping {
    	Long getId();
    	@JsonFormat(pattern = "dd-MM-yyyy")
    	Date getRetroDoneOn();
    	
    	WarrantyRetrofitmentCampaign getWarrantyRetrofitmentCampaign();
    	interface WarrantyRetrofitmentCampaign {
    		Long getId();
    		String getRetrofitmentNo();
    		String getCampaignName();
    		@JsonFormat(pattern = "dd-MM-yyyy")
    		Date getRetrofitmentDate();    		
    		@JsonFormat(pattern = "dd-MM-yyyy")
    		Date getStartDate();
    		@JsonFormat(pattern = "dd-MM-yyyy")
    		Date getEndDate();
    	}
    	
    	MachineVinMaster getMachineInventory();
    	interface MachineVinMaster {
    		Long getVinId();
            String getChassisNo();
            String getEngineNo();
            @JsonFormat(pattern = "dd-MM-yyyy")
            Date getInstallationDate();
            String getCsbNumber();
            String getRegistrationNumber();
            MachineMaster getMachineMaster();
            interface MachineMaster{
                Long getId();
                String getModel();
            }
    	}
    }
    //Suraj-24-04-2023-END
    
}