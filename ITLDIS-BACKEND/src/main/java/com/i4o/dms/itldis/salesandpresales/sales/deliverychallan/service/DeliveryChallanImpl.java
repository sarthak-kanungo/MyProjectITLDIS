package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.salesandpresales.grn.repository.MachineInventoryRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.repository.DealerMachineTransferRepository;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.domain.MachineAllotment;
import com.i4o.dms.itldis.salesandpresales.sales.allotment.repository.MachineAllotmentRepository;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DcDeliverableChecklist;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallan;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.DeliveryChallanCancelRequest;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.domain.SalesDcApproval;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.ApproveDcDto;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.DcCancelRequestDto;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository.DeliveryChallanRepositroy;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository.SalesDCCancellationRepository;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.repository.SalesDcApprovalRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;

@Service
public class DeliveryChallanImpl implements DeliveryChallanService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CustomerMasterRepo customerMasterRepo;

    @Autowired
    private DeliveryChallanRepositroy deliveryChallanRepositroy;

    /*@Autowired
    private ProspectMasterRepo prospectMasterRepo;*/

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private DealerMachineTransferRepository dealerMachineTransferRepository;

    private Map<String, Object> map = new HashMap<>();

    @Autowired
    private MachineInventoryRepository machineInventoryRepository;

    @Autowired
    private MachineAllotmentRepository machineAllotmentRepository;

    @Autowired
    private SalesDcApprovalRepository salesDcApprovalRepository;

    @Autowired
    private SalesDCCancellationRepository salesDCCancellationRepository;
    
    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;
    
    @Autowired
    private UserAuthentication userAuthentication;

    @Override
    @Transactional
    public DeliveryChallan saveDc(DeliveryChallan deliveryChallan) {
    	String msg = "NOT OK";
    	/*switch(deliveryChallan.getDeliveryChallanType()){
    		case "COMMERCIAL" : {
    			msg = deliveryChallanRepositroy.validateDataBeforeSave(deliveryChallan.getDeliveryChallanType(), deliveryChallan.getEnquiry().getId());
    			break;
    		}
    		case "ONLY IMPLEMENTS" : {
    			msg = deliveryChallanRepositroy.validateDataBeforeSave(deliveryChallan.getDeliveryChallanType(), deliveryChallan.getMachineAllotment().getId());
    			break;
    		}
    		case "DEALER TRANSFER" : {
    			msg = deliveryChallanRepositroy.validateDataBeforeSave(deliveryChallan.getDeliveryChallanType(), deliveryChallan.getDealerMachineTransfer().getId());
    			break;
    		}
    		default : {
    			msg = "NOT OK";
    		}
    	}*/
    	msg = deliveryChallanRepositroy.validateDataBeforeSave(deliveryChallan.getDeliveryChallanType(), deliveryChallan.getMachineAllotment().getId());
    	
    	if(msg.equals("OK")){
	        if(deliveryChallan.getDeliveryChallanId()==null){
	        	if(deliveryChallan.getDeliverableChecklist()!=null){
	        		for(DcDeliverableChecklist chl : deliveryChallan.getDeliverableChecklist()){
	        			chl.setId(null);
	        		}
	        	}
	        }
	        
	        if (deliveryChallan.getCustomerMaster() != null && deliveryChallan.getCustomerMaster().getId() != null) {
	        	
	            CustomerMaster customerMaster=customerMasterRepo.getOne(deliveryChallan.getCustomerMaster().getId());
	            deliveryChallan.setCustomerMaster(customerMaster);
	            deliveryChallan.setCustomerName(customerMaster.getFirstName()+(customerMaster.getMiddleName()==null?"":" "+customerMaster.getMiddleName())+(customerMaster.getLastName()==null?"":" "+customerMaster.getLastName()));
	            deliveryChallan.setCustomerAddress(customerMaster.getAddress1()+(customerMaster.getAddress2()==null?"":" "+customerMaster.getAddress2())+(customerMaster.getAddress3()==null?"":" "+customerMaster.getAddress3()));
	            deliveryChallan.setPinCode(customerMaster.getPinCode());
	            deliveryChallan.setPostOffice(customerMaster.getPostOffice());
	            deliveryChallan.setCity(customerMaster.getCity());
	            deliveryChallan.setTehsil(customerMaster.getTehsil());
	            deliveryChallan.setDistrict(customerMaster.getDistrict());
	            deliveryChallan.setState(customerMaster.getState());
	            deliveryChallan.setCountry(customerMaster.getCountry());
	            deliveryChallan.setCustomerMobileNumber(customerMaster.getMobileNumber());
	            deliveryChallan.setCustomerCode(customerMaster.getCustomerCode());
	
	        }
	        
	        deliveryChallanRepositroy.save(deliveryChallan);
	        
    	}else{
    		deliveryChallan.setMessage(msg);
    	}
    	return deliveryChallan;
    }

    @Override
    public void cancelDc(DcCancelRequestDto dcCancelRequestDto) {
       DeliveryChallan deliveryChallan = deliveryChallanRepositroy.getOne(dcCancelRequestDto.getId());
       deliveryChallan.setStatus("Cancellation Under Approval");
       deliveryChallanRepositroy.save(deliveryChallan);
       /*deliveryChallan.setDcCancellationReason(dcCancelRequestDto.getDcCancellationReason());
        deliveryChallan.setDcCancellationType(dcCancelRequestDto.getDcCancellationType());*/
        //deliveryChallan.setBrand(dcCancelRequestDto.getBrand());
        //deliveryChallan.setModel(dcCancelRequestDto.getModel());
       /* deliveryChallan.setDcCancelRemark(dcCancelRequestDto.getDcCancelRemark());
        deliveryChallan.setOtherReason(dcCancelRequestDto.getOtherReason());
        
        deliveryChallan.setStatus("DC CANCELLED");*/
        /*deliveryChallan.getDcMachineDetail().forEach(d -> {
            MachineInventory machineInventory = machineInventoryRepository.getOne(d.getMachineInventory().getId());
             // machineInventory.setDcCancel(true);
            //machineInventory.setAllotFlag(false);
            //machineInventory.setStockType("");
            machineInventoryRepository.save(machineInventory);
        });*/
        DeliveryChallanCancelRequest cancelRequest = new DeliveryChallanCancelRequest();
        cancelRequest.setDeliveryChallanId(dcCancelRequestDto.getId());
        cancelRequest.setCreatedBy(userAuthentication.getLoginId());
        cancelRequest.setCreatedDate(new Date());
        cancelRequest.setDcCancellationType(dcCancelRequestDto.getDcCancellationType());
        cancelRequest.setDcCancelRemark(dcCancelRequestDto.getDcCancelRemark());
        cancelRequest.setDcCancellationReason(dcCancelRequestDto.getDcCancellationReason());
        cancelRequest.setDcCancellationOtherReason(dcCancelRequestDto.getOtherReason());
        cancelRequest.setDcCancelDate(new Date());
        cancelRequest.setRequestDate(new Date());
        cancelRequest.setCancelApprovalStatus("Waiting For Approval");
        
        salesDCCancellationRepository.save(cancelRequest);
        
        List<SalesDcApproval> salesDcApprovals = new ArrayList<>();
        salesDcApprovalRepository.dcApprovalHierarchy(userAuthentication.getDealerId()).forEach(designationHierarchy -> {
            SalesDcApproval approval = new SalesDcApproval();
            approval.setDcCancelReqId(cancelRequest.getId());
            approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
            approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
            approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
            approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
            approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
            approval.setRejectedFlag('N');
            salesDcApprovals.add(approval);
        });
        salesDcApprovalRepository.saveAll(salesDcApprovals);
        /*MachineAllotment machineAllotment = machineAllotmentRepository.findByEnquiryId(deliveryChallan.getEnquiry().getId());
        machineAllotment.setDeAllotFlag(true);
        machineAllotmentRepository.save(machineAllotment);*/
        //deliveryChallanRepositroy.save(deliveryChallan);
    }

   /* @Override
    @Transactional
    public void approveDc(ApproveDcDto approveDcDto) {
        DeliveryChallan deliveryChallan = deliveryChallanRepositroy.getOne(approveDcDto.getDcId());
        KubotaEmployeeMaster kubotaEmployeeMaster = kubotaEmployeeRepository.getOne(approveDcDto.getUserId());
        String designation = "";//kubotaEmployeeMaster.getDesignationHierarchy().getHierarchy();
        salesDcApprovalRepository.approveCancelledDC(approveDcDto.getDcId(), approveDcDto.getUserId(), designation, approveDcDto.getApprovalFlag());
    }*/
}
