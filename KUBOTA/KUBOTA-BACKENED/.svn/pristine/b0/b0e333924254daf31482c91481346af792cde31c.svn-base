package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.service;

import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.PoApproval;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto.PurchaseOrderResponseDto;
import com.i4o.dms.kubota.utils.ApiResponse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PurchaseOrderService  {
    //PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
    PurchaseOrderResponseDto getPurchaseOrderById(PurchaseOrderResponseDto purchaseOrder, Long kubotaUserId);
    String poApproval(PoApproval poApproval, String userCode);
    
    
    ApiResponse<?> getPoPendingForApproval();
    
    ApiResponse<?> purchaseOrderGroupApproval(List<PoApproval> poApprovalList, String userCode);

}
