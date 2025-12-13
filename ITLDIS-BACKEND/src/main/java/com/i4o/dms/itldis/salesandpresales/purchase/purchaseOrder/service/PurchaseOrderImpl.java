package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.service;

import com.i4o.dms.itldis.configurations.Constants;
import com.i4o.dms.itldis.masters.dbentities.user.DesignationHierarchy;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.SalesPoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PurchaseOrderResponseDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.PurchaseOrderRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.SalesPoApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.purchaseordermachinedetails.domain.PurchaseOrderMachineDetails;
import com.i4o.dms.itldis.salesandpresales.purchaseordermachinedetails.repository.PurchaseOrderMachineDetailsRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.i4o.dms.itldis.configurations.Constants.*;
import static com.i4o.dms.itldis.utils.Constants.DRAFT;


@Service
public class PurchaseOrderImpl implements PurchaseOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private SalesPoApprovalRepository salesPoApprovalRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private PurchaseOrderMachineDetailsRepo purchaseOrderMachineDetailsRepo;

    @Autowired
    private DesignationHierarchyRepository hierarchyRepository;

  /*  @Override
    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
        if (purchaseOrder.isDraftMode()) {
            purchaseOrder.setPoStatus(DRAFT);
        } else {
            purchaseOrder.setPoStatus(RELEASED);
        }
        return purchaseOrderRepo.save(purchaseOrder);
    }
*/
    @Override
    public PurchaseOrderResponseDto getPurchaseOrderById(PurchaseOrderResponseDto purchaseOrder, Long kubotaUserId) {
        if (kubotaUserId!=null) {
            Character isSalesAdmin = kubotaEmployeeRepository.isSaleAdmin(kubotaUserId);
            if(isSalesAdmin!=null){
            	purchaseOrder.setSalesAdmin(isSalesAdmin.equals('Y'));
            }
            Character checkForAllApprovalButtons = salesPoApprovalRepository.checkForAllApprovalButtons(kubotaUserId, purchaseOrder.getId());
            if(checkForAllApprovalButtons!=null){
            	purchaseOrder.setShowAllButton(checkForAllApprovalButtons.equals('Y'));
            }
        }
       // purchaseOrder.getMachineDetails().removeIf(PurchaseOrderMachineDetails::isDelete);
        return purchaseOrder;
    }

    @Override
    @Transactional
    public String poApproval(PoApproval poApproval, String usercode) {
        
        String poStatus = salesPoApprovalRepository
                .approvePoAccordingToDesignationLevel(poApproval.getPurchaseOrderId(),
                        poApproval.getUserId(), 
                        poApproval.getRemark(), 
                        usercode,
                        poApproval.getApprovalFlag(),
                        poApproval.getCurrentOs(),
                        poApproval.getOs0To30Days(),
                        poApproval.getOs31To60Days(),
                        poApproval.getOs61To90Days(),
                        poApproval.getOs90Days(),
                        poApproval.getPaymentPending(),
                        poApproval.getNetOs(),
                        poApproval.getPendingOrder(),
                        poApproval.getOrderUnderProcess(),
                        poApproval.getChannelFinanceAvailable() ,
                        poApproval.getExposureAmount(),
                        poApproval.getTotalCreditLimit(),
                        poApproval.getAvailableLimit(),
                        poApproval.getBasicAmount(),
                        poApproval.getTotalGstAmount(),
                        poApproval.getTotalAmount(),
                        (poApproval.getIsApprovalRequired()==null || !poApproval.getIsApprovalRequired() ? "No" : "Yes"));
        
        
	        Character isSalesAdmin = kubotaEmployeeRepository.isSaleAdmin(poApproval.getUserId());
	        
	        if (isSalesAdmin!=null && isSalesAdmin.equals('Y') && poApproval.getApprovalFlag().equalsIgnoreCase("APPROVE")) {
	            List<PurchaseOrderMachineDetails> details = new ArrayList<>();
	            poApproval.getMachineDetails().forEach(poApprovalMachineDetail -> {
	                PurchaseOrderMachineDetails machineDetails = purchaseOrderMachineDetailsRepo
	                        .getOne(poApprovalMachineDetail.getId());
	                machineDetails.setAccpacLocationCode(poApprovalMachineDetail.getAccpacLocationCode());
	                machineDetails.setDiscountAmount(poApprovalMachineDetail.getDiscountAmount());
	                machineDetails.setDiscountPercentage(poApprovalMachineDetail.getDiscountPercentage());
	                machineDetails.setDiscountType(poApprovalMachineDetail.getDiscountType());
	                machineDetails.setAmount(poApprovalMachineDetail.getAmount());
	                details.add(machineDetails);
	            });
	            purchaseOrderMachineDetailsRepo.saveAll(details);
	        }
        
        return poStatus;
    }

    /*private String approvedByManagingDirector(PoApproval poApproval, String designation) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.getOne(poApproval.getPurchaseOrderId());
        if (poApproval.getApprovalFlag()) {
            SalesPoApproval salesPoApproval = salesPoApprovalRepository
                    .findByPurchaseOrderIdAndKubotaEmployeeMasterIdAndFinalStatusIsNull(poApproval.getPurchaseOrderId(), poApproval.getUserId());
            salesPoApproval.setApprovalStatus(poApproval.getApprovalFlag()
                    ? Constants.APPROVED_BY + designation : Constants.REJECTED_BY + designation);
            salesPoApproval.setApprovalStatus(poApproval.getApprovalFlag() ? APPROVED : REVERSE);
            salesPoApproval.setApprovedDate(poApproval.getUserId().equals(salesPoApproval.getKubotaEmployeeMaster().getId())
                    ? new Date() : null);
            salesPoApprovalRepository.save(salesPoApproval);
            purchaseOrder.setPoStatus(APPROVED);
        } else {
            purchaseOrder.setPoStatus(REVERSE);
        }
        purchaseOrderRepo.save(purchaseOrder);
        return poApproval.getApprovalFlag()
                ? "Po Approved by " + designation : "Po Rejected by " + designation;
    }*/

  /*  private String approvalProcess(PoApproval poApproval, String designation) {
        if (poApproval.getApprovalFlag()) {
            SalesPoApproval salesPoApproval = salesPoApprovalRepository
                    .findByPurchaseOrderIdAndKubotaEmployeeMasterId(poApproval.getPurchaseOrderId(), poApproval.getUserId());
            salesPoApproval.setApprovalStatus(poApproval.getApprovalFlag()
                    ? Constants.APPROVED_BY + designation : Constants.REJECTED_BY + designation);
            salesPoApproval.setApprovalStatus(poApproval.getApprovalFlag() ? APPROVED : REVERSE);
            salesPoApproval.setApprovedDate(poApproval.getUserId().equals(salesPoApproval.getKubotaEmployeeMaster().getId())
                    ? new Date() : null);
            salesPoApprovalRepository.save(salesPoApproval);
        } else {
            PurchaseOrder purchaseOrder = purchaseOrderRepo.getOne(poApproval.getPurchaseOrderId());
            purchaseOrder.setPoStatus(REVERSE);
            purchaseOrderRepo.save(purchaseOrder);
        }
        return poApproval.getApprovalFlag()
                ? "Po Approved by " + designation : "Po Rejected by " + designation;
    }

    private String salesAdminApprovalProcess(PoApproval poApproval, SalesPoApproval poApprovals, String designation) {

        poApprovals.setApprovalStatus(poApproval.getApprovalFlag()
                ? Constants.APPROVED_BY + designation : Constants.REJECTED_BY + designation);
        poApprovals.setApprovalStatus(poApproval.getApprovalFlag() ? APPROVED : REVERSE);
        poApprovals.setApprovedDate(poApproval.getUserId().equals(poApprovals.getKubotaEmployeeMaster().getId())
                ? new Date() : null);
        salesPoApprovalRepository.save(poApprovals);

        List<SalesPoApproval> list = new ArrayList<>();
        PurchaseOrder purchaseOrder = purchaseOrderRepo.getOne(poApproval.getPurchaseOrderId());
        if (poApproval.getApprovalFlag()) {
            DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo
                    .getOne(purchaseOrder.getDealerEmployeeMaster().getId());
            salesPoApprovalRepository.getApprovalHierarchyLevel(dealerEmployeeMaster.getDealerMaster().getId()).forEach(id -> {
                KubotaEmployeeMaster kubotaEmployeeMaster = kubotaEmployeeRepository.getOne(id);
                SalesPoApproval salesPoApproval = new SalesPoApproval();
                salesPoApproval.setKubotaEmployeeMaster(kubotaEmployeeMaster);
                salesPoApproval.setPurchaseOrder(purchaseOrder);
                salesPoApproval.setApprovalStatus(PENDING_AT + kubotaEmployeeMaster.getDesignationHierarchy().getHierarchy());
                list.add(salesPoApproval);
                logger.info("Added");
            });

            List<KubotaEmployeeMaster> kubotaEmployeeMasters = kubotaEmployeeRepository.getMdAndDmd();
            kubotaEmployeeMasters.forEach(kubotaEmployeeMaster -> {
                SalesPoApproval salesPoApproval = new SalesPoApproval();
                salesPoApproval.setKubotaEmployeeMaster(kubotaEmployeeMaster);
                salesPoApproval.setPurchaseOrder(purchaseOrder);
                salesPoApproval.setApprovalStatus(PENDING_AT + kubotaEmployeeMaster.getDesignationHierarchy().getHierarchy());
                list.add(salesPoApproval);
                logger.info("Added");
            });
            salesPoApprovalRepository.saveAll(list);

        } else {
            purchaseOrder.setPoStatus(REVERSE);
            purchaseOrderRepo.save(purchaseOrder);
        }

        return poApproval.getApprovalFlag()
                ? "Po Approved by " + designation : "Po Rejected by " + designation;
    }*/
    
    @Override
	public ApiResponse<?> getPoPendingForApproval() {
    	ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
    	apiResponse.setResult(purchaseOrderRepo.getPoPendingForApproval(userAuthentication.getUsername()));
    	
    	return apiResponse;
    }
    
    public ApiResponse<?> purchaseOrderGroupApproval(List<PoApproval> poApprovalList, String usercode){
    	
    	ApiResponse<List<PoApproval>> apiResponse = new ApiResponse<>();
    	
    	poApprovalList.stream().filter(poApproval-> poApproval.getIsSelect()!= null && poApproval.getIsSelect()).forEach(selectedPoApproval ->{
    		 salesPoApprovalRepository.approvePoAccordingToDesignationLevel
    			(	selectedPoApproval.getPurchaseOrderId(),
//    					selectedPoApproval.getUserId(), 
    				userAuthentication.getKubotaEmployeeId(),
    				selectedPoApproval.getRemark(), 
                    usercode,
                    "APPROVE",
//                    poApproval.getApprovalFlag(),
                    selectedPoApproval.getCurrentOs(),
                    selectedPoApproval.getOs0To30Days(),
                    selectedPoApproval.getOs31To60Days(),
                    selectedPoApproval.getOs61To90Days(),
                    selectedPoApproval.getOs90Days(),
                    selectedPoApproval.getPaymentPending(),
                    selectedPoApproval.getNetOs(),
                    selectedPoApproval.getPendingOrder(),
                    selectedPoApproval.getOrderUnderProcess(),
                    selectedPoApproval.getChannelFinanceAvailable() ,
                    selectedPoApproval.getExposureAmount(),
                    selectedPoApproval.getTotalCreditLimit(),
                    selectedPoApproval.getAvailableLimit(),
                    selectedPoApproval.getBasicAmount(),
                    selectedPoApproval.getTotalGstAmount(),
                    selectedPoApproval.getTotalAmount(),
                    (selectedPoApproval.getIsApprovalRequired()==null || !selectedPoApproval.getIsApprovalRequired() ? "No" : "Yes"));
    
    
	        Character isSalesAdmin = kubotaEmployeeRepository.isSaleAdmin(selectedPoApproval.getUserId());
	        
	        if (isSalesAdmin!=null && isSalesAdmin.equals('Y') && selectedPoApproval.getApprovalFlag().equalsIgnoreCase("APPROVE")) {
	            List<PurchaseOrderMachineDetails> details = new ArrayList<>();
	            selectedPoApproval.getMachineDetails().forEach(poApprovalMachineDetail -> {
	                PurchaseOrderMachineDetails machineDetails = purchaseOrderMachineDetailsRepo
	                        .getOne(poApprovalMachineDetail.getId());
	                machineDetails.setAccpacLocationCode(poApprovalMachineDetail.getAccpacLocationCode());
	                machineDetails.setDiscountAmount(poApprovalMachineDetail.getDiscountAmount());
	                machineDetails.setDiscountPercentage(poApprovalMachineDetail.getDiscountPercentage());
	                machineDetails.setDiscountType(poApprovalMachineDetail.getDiscountType());
	                machineDetails.setAmount(poApprovalMachineDetail.getAmount());
	                details.add(machineDetails);
	            });
	            purchaseOrderMachineDetailsRepo.saveAll(details);
	        }
    	});
    	return apiResponse;
    	
//    	apiResponse.setResult(purchaseOrderRepo.purchaseOrderGroupApproval);
    }
}
