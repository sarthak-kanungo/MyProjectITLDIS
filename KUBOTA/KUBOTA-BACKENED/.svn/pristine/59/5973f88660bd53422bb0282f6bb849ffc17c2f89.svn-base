package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;

@JsonPropertyOrder({"deliveryChallanId", "deliveryChallanNumber", "deliveryChallanType", "gatePassNumber", "enquiry"})
public interface DeliveryChallanEditResponse {

    Long getDeliveryChallanId();

    String getDeliveryChallanNumber();

    String getDeliveryChallanDate();

    String getDeliveryChallanType();

    String getGatePassNumber();

    String getInvoiceCustomerName();

    String getCustomerMobileNumber();

    String getDcCancellationType();

    String getDcCancellationReason();
    
    String getDcCancelRemark();

    String getCreatedDate();

    String getOtherReason();

    String getRemarks();

    String getStatus();

    String getPolicyStartDate();

    String getPolicyExpiryDate();

    Enquiry getEnquiry();

    CustomerMaster getCustomerMaster();

    List<DcMachineDetail> getDcMachineDetail();

    List<DcAccessoriesDetail> getDcAccessoriesDetails();
    
    List<DcDeliverableChecklist> getDeliverableChecklist();

    InsuranceCompanyMaster getInsuranceCompanyMaster();

    MachineAllotment getMachineAllotment();
    
    DealerMachineTransfer getDealerMachineTransfer();
    
    interface DealerMachineTransfer{
    	String getRequestNumber();
        DealerMaster getDealerMaster();
    }
    interface MachineAllotment{
    	String getAllotmentNumber();
    	String getAllotmentDate();
    }
    
    interface Enquiry {

        @JsonProperty("value")
        String getEnquiryNumber();

        String getEnquiryType();

        String getFirstName();

        Long getId();

    }

    interface DcAccessoriesDetail {
    	Long getId();
    	Long getQuantity();
    	MachineMaster getMachineMaster();

        interface MachineMaster {

            @JsonProperty("itemNumber")
            String getItemNo();

            String getItemDescription();

            String getProductGroup();
        }
    }
    
    interface DcMachineDetail {

        @JsonProperty("machineDetailId")
        Long getId();

        MachineInventory getMachineInventory();

        Long getQuantity();

        Boolean getDeleteFlag();

        interface MachineInventory {


            Long getId();

            MachineVinMaster getMachineVinMaster();
            
            interface MachineVinMaster {
            	Long getVinId();
            
            	String getChassisNo();

                @JsonProperty("engineNumber")
                String getEngineNo();

            	MachineMaster getMachineMaster();

                interface MachineMaster {

                    @JsonProperty("itemNumber")
                    String getItemNo();

                    String getItemDescription();

                    String getProductGroup();

                    String getColor();

                }

            }
        }
    }


   interface DcDeliverableChecklist {

        Long getId();
        
        Boolean getIsDelivered();
        
        DcDeliverableChecklistMaster getDcDeliverableChecklistMaster();

        interface DcDeliverableChecklistMaster {

            String getDeliverableChecklist();
        }
    }


    interface InsuranceCompanyMaster {

        Long getId();

        String getCompanyCode();

        String getCompanyName();

        String getAddress1();

        String getAddress2();

        String getAddress3();

        Long getPinCode();

        String getLocality();

        String getTehsil();

        String getCity();

        String getState();

        String getCountry();

        String getStd();

        String getTelephoneNumber();

        String getEmail();

    }

    interface CustomerMaster {

        Long getId();

        String getCustomerCode();

        String getCustomerType();

        String getCompanyName();

        @JsonProperty("customerName")
        String getFirstName();

        String getStd();

        String getTelephoneNo();

        String getAddress1();

        String getAddress2();

        String getAddress3();

        String getPinCode();

        String getPostOffice();

        String getCity();

        String getTehsil();

        String getDistrict();

        String getState();

        String getCountry();

        String getMobileNumber();

        @JsonProperty("panNumber")
        String getPancardNo();

        @JsonProperty("gstInNumber")
        String getGstNo();

    }

}
