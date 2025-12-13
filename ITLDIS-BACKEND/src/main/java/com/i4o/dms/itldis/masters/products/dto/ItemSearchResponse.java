package com.i4o.dms.itldis.masters.products.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","itemDesciption","productGroup","product","series","model","subModel","variant","purchase","isSFG","isFG","isAssemblyReq","sfgPartNo",
"isPalletReq","palletItemNo","noOfItemInPallet","vendorCode","hsnCode","bcd","sws","igst","cgst","sgst","portOfLoading","etdInDays","etaInDays","chassisNoReq",
"engineModel","batteryNo","fipNo","crawlerNoLeft","hydraulicPumpNo","starterNo","alternatorNo","frontTyreLeft","frontTyreRight","rearTyreLeft",
"rearTyreRight","frontRimLeft","frontRimRight","rearRimLeft","rearRimRight","frontTyreMake","rearTyreMake","transmissionNo","hstNo","swingMotorNo","leadTime","rtoRegistration"})

public interface ItemSearchResponse {

    Long getId();
    String getItemDesciption();
    String getProductGroup();
    String getProduct();
    String getSeries();
    String getModel();
    String getSubModel();
    String getVariant();
    Character getPurchase();
    Character getIsSFG();
    Character getIsFG();
    Character getIsAssemblyReq();
    String getSfgPartNo();
    String getIsPalletReq();
    String getPalletItemNo();
    Integer getNoOfItemInPallet();
    String getVendorCode();
    String getHsnCode();
    Float getBcd();
    Float getSws();
    Float getIgst();
    Float getCgst();
    Float getSgst();
    String getPortOfLoading();
    Long getEtdInDays();
    Long getEtaInDays();
    Character getChassisNoReq();
    String getEngineModel();
    String getBatteryNo();
    String getFipNo();
    String getCrawlerNoLeft();
    String getCrawlerNoRight();
    String getHydraulicPumpNo();
    String getStarterNo();
    String getAlternatorNo();
    String getFrontTyreLeft();
    String getFrontTyreRight();
    String getRearTyreLeft();
    String getRealTyreRight();
    String getFrontRimLeft();
    String getFrontRimRight();
    String getRealRimLeft();
    String getRealRimRight();
    String getFrontTyreMake();
    String geRearTyreMake();
    String getRadiatorNo();
    String getTransmissionNo();
    String getHstNo();
    String getSwingMotorNo();
    String getLeadTime();
    Character getRtoRegistration();
}
