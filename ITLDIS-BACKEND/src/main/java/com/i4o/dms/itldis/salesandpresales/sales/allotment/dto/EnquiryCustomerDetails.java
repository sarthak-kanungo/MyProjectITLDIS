package com.i4o.dms.itldis.salesandpresales.sales.allotment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnquiryCustomerDetails {

    CustomerDetail customerDetail;
    List<MachineDetails> machineDetails = new ArrayList<>();
    //List<MachineDetails> machineImplements = new ArrayList<>();
}
