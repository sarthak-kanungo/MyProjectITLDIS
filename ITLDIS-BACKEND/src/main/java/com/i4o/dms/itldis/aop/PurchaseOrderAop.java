package com.i4o.dms.itldis.aop;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.SalesPoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.PoApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.PurchaseOrderRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.SalesPoApprovalRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.i4o.dms.itldis.configurations.Constants.*;

@Aspect
@Configuration
public class  PurchaseOrderAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private SalesPoApprovalRepository salesPoApprovalRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

   /* @AfterReturning(value = "execution (* com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.controller.PurchaseOrderController.savePurchaseOrder(..))",
            returning = "retVal")
    public void savePurchaseOrder(Object retVal) {

        ResponseEntity<ApiResponse> entity = (ResponseEntity<ApiResponse>) retVal;
        ApiResponse apiResponse = null;
        try {
            apiResponse = entity.getBody();
            assert apiResponse != null;
            if (apiResponse.getStatus() == HttpStatus.OK.value()){
                PurchaseOrder purchaseOrder = purchaseOrderRepo.getOne(apiResponse.getId());
                if (purchaseOrder.getPoStatus().equalsIgnoreCase(UNDER_KAI_APPROVAL)){
                    SalesPoApproval salesPoApproval = new SalesPoApproval();
                    salesPoApproval.setPurchaseOrder(purchaseOrder);
                    salesPoApproval.setApprovalStatus(PENDING_AT_SALES_ADMIN);
                    salesPoApproval.setDesignationHierarchy(kubotaEmployeeRepository.findByHierarchy(SALES_ADMIN));
                    salesPoApprovalRepository.save(salesPoApproval);
                    purchaseOrder.setPoStatus(UNDER_KAI_APPROVAL);
                    purchaseOrder.setDraftMode(false);
                }

                purchaseOrderRepo.save(purchaseOrder);
            }
        } catch (Exception e) {
            logger.info("savePurchaseOrder Exception: " + e.getMessage());
        }
    }*/
/*
    @AfterReturning(value = "execution (* com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.controller.PurchaseOrderController.approvePurchaseOrder(..)) && args(poApproval,..)",
            returning = "retVal")
    public void approvePurchaseOrder(Object retVal, PoApproval poApproval) {

        ResponseEntity<ApiResponse> entity = (ResponseEntity<ApiResponse>) retVal;
        ApiResponse apiResponse = null;
        logger.info("poApproval: "+poApproval.toString());
        try {
            apiResponse = entity.getBody();
            assert apiResponse != null;
            PurchaseOrder purchaseOrder = purchaseOrderRepo.getOne(poApproval.getPurchaseOrderId());
            DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(purchaseOrder.getDealerEmployeeMaster().getId());
            List<SalesPoApproval> approvalList = new ArrayList<>();
            salesPoApprovalRepository.getApprovalHierarchyLevel(dealerEmployeeMaster.getDealerMaster().getId()).forEach(id -> {
                KubotaEmployeeMaster kubotaEmployeeMaster = kubotaEmployeeRepository.getOne(id);
                SalesPoApproval salesPoApproval = new SalesPoApproval();
                salesPoApproval.setKubotaEmployeeMaster(kubotaEmployeeMaster);
                salesPoApproval.setPurchaseOrder(purchaseOrder);
                salesPoApproval.setApprovalStatus(PENDING_AT + kubotaEmployeeMaster.getDesignationHierarchy().getHierarchy());
                approvalList.add(salesPoApproval);
                logger.info("Added");
            });
            salesPoApprovalRepository.saveAll(approvalList);
        } catch (Exception e) {
            logger.info("savePurchaseOrder Exception: " + e.getMessage());
        }
    }*/
}
