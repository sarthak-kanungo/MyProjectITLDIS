package com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.i4o.dms.itldis.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcrImplements;

import java.util.Date;
import java.util.List;

public interface WarrantyWcrView {

    Long getId();
    String getWcrNo();
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getWcrDate();
    String getWcrStatus();
    String getWcrType();
    String getKaiRemarks();
    String getInspectionRemarks();

    /*DealerMaster getDealerMaster();
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

    WarrantyPcr getWarrantyPcr();
    
    WarrantyGoodwill getWarrantyGoodwill();
    
    interface  WarrantyGoodwill {
    	String getGoodwillNo();
    	@JsonFormat(pattern = "yyyy-MM-dd")
        Date getGoodwillDate(); 
    	WarrantyPcr getWarrantyPcr();
    }
    
    interface  WarrantyPcr {

        List<WarrantyPcrImplements> getWarrantyPcrImplements();

        Long getId();

        String getPcrNo();

        String getStatus();

        @JsonFormat(pattern = "yyyy-MM-dd")
        Date getPcrDate();

        String getFailureType();

        @JsonProperty("repeatFailure")
        Boolean getRepeatFailureFlag();

        String getKaiRemarks();

        String getDealerRemarks();

        @JsonProperty("noOfTimes")
        Integer getFailureCount();

        String getDealerObservation();

        String getActionTaken();

        String getServiceDealer();
        
        String getSoldToDealer();
        
        String getServiceDealerAddress();

        String getMobileNumber();

        String getEmailId();
        
        /*DealerMaster getDealerMaster();

        interface DealerMaster {
            Long getId();

            @JsonProperty("serviceDealer")
            String getDealerName();

            @JsonProperty("dealerCode")
            String getDealerCode();

            @JsonProperty("serviceDealerAddress")
            String getShowroomAddress1();

            @JsonProperty("mobileNumber")
            String getMobileNo();

            @JsonProperty("emailId")
            String getEmailId();
        }*/

        ServiceJobCard getServiceJobCard();

        interface ServiceJobCard {
            List<LabourJobCharges> getLabourCharge();

            String getJobCardNo();

            Double getTotalHour();

            String getStatus();

            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getJobCardDate();

            @JsonProperty("dateOfRepair")
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getClosedDate();

            String getCustomerConcern();

            @JsonProperty("dateOfFailure")
            @JsonFormat(pattern = "yyyy-MM-dd")
            Date getTaskDate();

            MachineInventory getMachineInventory();

            interface MachineInventory {
                Long getVinId();

                String getChassisNo();

                String getEngineNo();

                @JsonProperty("installationDate")
                @JsonFormat(pattern = "yyyy-MM-dd")
                //Date getInstallationDate();
                Date getDeliveryDate();

                MachineMaster getMachineMaster();

                interface MachineMaster {
                    Long getId();

                    String getModel();
                }

                /*DealerMaster getDealerMaster();

                interface DealerMaster {
                    Long getId();

                    @JsonProperty("soldToDealer")
                    String getDealerName();

                }*/
            }

            CustomerMaster getCustomerMaster();

            interface CustomerMaster {
                Long getId();

                @JsonProperty("mobileNo")
                String getMobileNumber();

                @JsonProperty("customerName")
                String getFirstName();

                String getLastName();

                @JsonProperty("address")
                String getAddress1();

            }

        }
    }


}
