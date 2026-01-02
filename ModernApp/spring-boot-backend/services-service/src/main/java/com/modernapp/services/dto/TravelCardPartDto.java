package com.modernapp.services.dto;

public class TravelCardPartDto {
    private int sno;
    private String contentDesc;
    private String observation;
    private String serialNo;

    public TravelCardPartDto() {}

    public TravelCardPartDto(int sno, String contentDesc, String observation, String serialNo) {
        this.sno = sno;
        this.contentDesc = contentDesc;
        this.observation = observation;
        this.serialNo = serialNo;
    }

    public int getSno() { return sno; }
    public void setSno(int sno) { this.sno = sno; }

    public String getContentDesc() { return contentDesc; }
    public void setContentDesc(String contentDesc) { this.contentDesc = contentDesc; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }
}
