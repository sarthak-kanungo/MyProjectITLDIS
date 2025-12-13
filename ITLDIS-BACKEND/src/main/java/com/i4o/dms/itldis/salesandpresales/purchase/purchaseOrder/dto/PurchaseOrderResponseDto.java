package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface PurchaseOrderResponseDto {

	DealerMaster getDealerMaster();
	
    Long getId();

    String getPoType();

    String getDepot();

    String getPoNumber();

    String getPoDate();

    String getTotalQuantity();

    String getPoStatus();

    Double getTotalCreditLimit();

    Double getAvailableLimit();

    Double getTotalOs();

    Double getCurrentOs();

    Double getOs0To30Days();

    Double getOs31To60Days();

    Double getOs61To90Days();

    Double getOs90Days();

    Double getPaymentPending();

    Double getNetOs();

    Double getChannelFinanceAvailable();

    Double getBasicAmount();

    Double getTotalGstAmount();

    Double getTotalAmount();

    Double getOrderUnderProcess();

    Double getPendingOrder();

    Double getExposureAmount();

    String getChequeLeaf();

    String getCoveringLetter();

    String getCreditApplication();
    
    String getChequeNumber();	//Suraj-11-07-2023
    
    String getChequeDate();	//Suraj-11-07-2023
    
    String getChequeAmount();	//Suraj-11-07-2023
    
    String getChequeCopy();	//Suraj-11-07-2023

    List<PurchaseOrderMachineDetails> getMachineDetails();

  //  boolean salesAdmin=false;
    void setSalesAdmin(boolean equalsIgnoreCase);

    interface PurchaseOrderMachineDetails {
        Long getId();

        MachineMaster getMachineMaster();

        interface MachineMaster {
            Long getId();
            @JsonProperty("code")
            String getItemNo();
            String getItemDescription();
            String getVariant();
        }

        String getColour();

        Integer getQuantity();

        Integer getAccpacStockQuantity();

        String getAccpacLocationCode();

        Double getUnitPrice();
        Double getAmount();
        Double getIgst();
        Double getDiscountPercentage();
        Double getDiscountAmount();
        String getDiscountType();
        Boolean getIsDelete();
      //  void setSalesAdmin(String salesAdmin);
        void setDiscountPercentage(BigDecimal discountPercentage);
        void setDiscountAmount(BigDecimal discountAmount);

    }
    boolean getSalesAdmin();
    
    void setShowAllButton(boolean showAllButton);
    boolean getShowAllButton();
    
    Double getTcsPercent();
    Double getTcsValue();
    
    interface DealerMaster{
    	 String getDealerCode();
    	 String getDealerName();
    	 String getBillingState();	//Suraj-09/11/2022
    }
    
//    String getTypeOfDelivery();	//Suraj--27-03-2023
}



