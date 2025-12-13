package com.i4o.dms.itldis.service.servicebooking.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtCategory;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtPlaceOfService;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtServiceTypeInfo;
import com.i4o.dms.itldis.masters.service.serviceactivityproposal.domain.ServiceMtActivityType;
import com.i4o.dms.itldis.masters.service.servicebooking.domain.ServiceMtSourceOfBooking;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineVinMaster;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
@Entity
@Getter
@Setter
@Table(name = "SV_BOOKING")
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "service booking no can't be blank")
    @Column(length = 21,unique = true)
    private String bookingNo = "DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis();

    private Date bookingDate;
    
    @NotNull(message = "service booking can't be null")
    @NotBlank
    private String status;

    @ManyToOne
    @JoinColumn(name = "place_of_service_id")
    private ServiceMtPlaceOfService placeOfService;

    private Boolean cancelBookingFlag=false;

    private String reasonForCancellation;


//    @ManyToOne
//    @JoinColumn(name = "machine_inventory_id")
//    private MachineInventory machineInventory;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private DealerEmployeeMaster mechanic;
    private Integer hours;

    @ManyToOne
    @JoinColumn(name = "source_of_booking_id")
    private ServiceMtSourceOfBooking sourceOfBooking;

    @ManyToOne
    @JoinColumn(name = "service_category_id")
    private ServiceMtCategory serviceCategory;

    @ManyToOne
    @JoinColumn(name = "service_type_id")
    private ServiceMtServiceTypeInfo serviceMtServiceTypeInfo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date appointmentDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private Date appointmentTime;
    private String remarks;

    private Double currentHour;

    private Double totalHour;
    private Double previousHour;


    @ManyToOne
    @JoinColumn(name = "customer_master_id")
    private CustomerMaster customerMaster;

    @ManyToOne
    @JoinColumn(name = "activity_type_id")
    private ServiceMtActivityType serviceMtActivityType;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_activity_proposal_id")
    private ServiceActivityProposal serviceActivityProposal;

//    @ManyToOne
//    @JoinColumn(name = "created_by")
//    private  DealerEmployeeMaster createdBy;
//
//    @ManyToOne
//    @JoinColumn(name ="last_modified_by")
//    private DealerEmployeeMaster lastModifiedBy;

//    @ManyToOne
//    @JoinColumn(name = "dealer_id")
//    private DealerMaster dealerMaster;
    
    private Boolean draftFlag;
    
    @ManyToOne
    @JoinColumn(name = "vin_id")
    private MachineVinMaster machineInventory;
    
    private Long branchId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdOn = new Date();
    

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedOn = new Date();
    
    @Column(updatable = false)
    private  Long createdBy;

    private Long lastModifiedBy;
}
