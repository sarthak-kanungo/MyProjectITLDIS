package com.i4o.dms.itldis.salesandpresales.grn.dto;

import com.i4o.dms.itldis.masters.dbentities.grn.Transporter;

import java.util.Date;

public interface MachineGrnDto {
    Long getId();
    String getGrnType();
    String getDmsGrnNumber();
    String getGrnStatus();
    Date getGrnDate();
    String getDriverName();
    String getDriverMobile();
    String getTransporterVehicleNumber();
    String getGrnBy();
    Double getGrossTotalValue();
    Transporter getTransporter();
}
