package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.service.impl;

import static com.i4o.dms.kubota.utils.Constants.CO_DEALER_OILORDER;
import static com.i4o.dms.kubota.utils.Constants.CO_DEALER_ORDER;
import static com.i4o.dms.kubota.utils.Constants.DEALER;
import static com.i4o.dms.kubota.utils.Constants.FREE_OF_COST;
import static com.i4o.dms.kubota.utils.Constants.KAI;
import static com.i4o.dms.kubota.utils.Constants.KAI_EMERGENCY_ORDER;
import static com.i4o.dms.kubota.utils.Constants.KAI_MACHINE_DOWN_ORDER;
import static com.i4o.dms.kubota.utils.Constants.KAI_MONTHLY_ORDER;
import static com.i4o.dms.kubota.utils.Constants.OIL_AND_LUBRICANT;
import static com.i4o.dms.kubota.utils.Constants.OTHER_SUPPLIER;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.spares.dbentities.domain.SparesMtPurchaseOrderOrderType;
import com.i4o.dms.kubota.masters.spares.dbentities.repository.OrderTypeRepository;
import com.i4o.dms.kubota.masters.spares.dbentities.repository.SparesMtSupplierRepository;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.repository.SPOrderPlanningSheetRepo;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePOApproval;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrderItem;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.DealerOutstandingResponse;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.HeaderResponse;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.OPSItemsDetailResponseDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.PoViewDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.UploadExcelDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderApprovalRepository;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderRepository;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.service.SparesPurchaseOrderService;
import com.i4o.dms.kubota.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.kubota.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

@Service
public class SparePurchaseOrderImpl implements SparesPurchaseOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SparePurchaseOrderRepository sparePurchaseOrderRepository;
    
    @Autowired
    private SparePurchaseOrderApprovalRepository sparePurchaseOrderApprovalRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private SparesMtSupplierRepository sparesMtSupplierRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;
    
    @Autowired
    private SPOrderPlanningSheetRepo orderPlanningSheetRepo;

    @Autowired
    private ExcelImportManager excelImportManager;

    private Double totalBaseAmount = 0.0;
    private Double totalPoAmount = 0.0;

    @Override
    @Transactional
    public void saveSparePurchaseOrder(SparePurchaseOrder sparePurchaseOrder) {

        sparePurchaseOrder.getSparePurchaseOrderPartDetail().forEach(partDetail -> {

            //SparesMtPurchaseOrderOrderType orderOrderType = orderTypeRepository.getOne(sparePurchaseOrder.getOrderType().getId());

            /*if (orderOrderType.getOrderType().equals("Oil and Lubricant") || orderOrderType.getOrderType().equals("Other Suppliers")) {
                SpareLocalPartMaster moq = spareLocalPartMasterRepo.getOne(partDetail.getSpareLocalPartMaster().getId());
                if (partDetail.getQuantity() % moq.getMoq() == 0) {
                    //&& !partDetail.getDeleteFlag()
                    calculations(partDetail);
//                    partDetail.setBaseAmount(baseAmount(partDetail));

                } else {
                    logger.info("Quantity is not next multiple of Moq-------------->>>>>" + partDetail.getQuantity());
                }
            } else {
                Long moq = sparePurchaseOrderRepository.getMoq(partDetail.getSparePartMaster().getId());
                if (partDetail.getQuantity() % moq == 0) {
                    //&& !partDetail.getDeleteFlag()
                    calculations(partDetail);
                } else {
                    logger.info("Quantity is not next multiple of Moq-------------->>>>>" + partDetail.getQuantity());
                }
            }*/
            
        	if (partDetail.getQuantity() % partDetail.getMoq() != 0) {
        		Integer qty = partDetail.getMoq() * ((Integer)(partDetail.getQuantity() / partDetail.getMoq())) ;
        		partDetail.setQuantity(qty);
        	}
        	calculations(partDetail);
        	
        });
        if(sparePurchaseOrder.getSparesMtSupplier()!=null && sparePurchaseOrder.getSparesMtSupplier().getId()!=null){
	        if(sparePurchaseOrder.getOrderType().getOrderType().equals("Other Suppliers")){
	        	sparePurchaseOrder.setVendorPartyId(sparePurchaseOrder.getSparesMtSupplier().getId());
	        	sparePurchaseOrder.setSparesMtSupplier(null);
	        }
        }
        sparePurchaseOrder.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        sparePurchaseOrder.setCreatedBy(userAuthentication.getLoginId());
        sparePurchaseOrder.setCreatedDate(new Date());
        sparePurchaseOrder.setPurchaseOrderDate(new Date());
        if (sparePurchaseOrder.getSparesMtSupplier() != null) {
            sparePurchaseOrder.setSparesMtSupplier(sparesMtSupplierRepository.getOne(sparePurchaseOrder.getSparesMtSupplier().getId()));
        }
        
        DecimalFormat df2 = new DecimalFormat("#.##");
        
        sparePurchaseOrder.setTotalBaseAmount(Double.valueOf(df2.format(totalBaseAmount)));
        sparePurchaseOrder.setTotalPoAmount(Double.valueOf(df2.format(totalPoAmount)));

        if (sparePurchaseOrder.getDraftFlag()) {
            sparePurchaseOrder.setPurchaseOrderStatus("Draft");
        } else {
        	if(sparePurchaseOrder.getOrderType().getOrderType().equals(KAI_MACHINE_DOWN_ORDER))
        		sparePurchaseOrder.setPurchaseOrderStatus("Waiting for Approval");
        	else
        		sparePurchaseOrder.setPurchaseOrderStatus("Submitted");
        }
        //dealer outstanding
        DealerOutstandingResponse dealerOutstandingResponse = sparePurchaseOrderRepository
                .getDealerOutStanding(userAuthentication.getDealerId(), sparePurchaseOrder.getId());

        dealerOutStanding(dealerOutstandingResponse, sparePurchaseOrder);
        netAmountPayable(dealerOutstandingResponse, sparePurchaseOrder);
        freightBorneBy(sparePurchaseOrder);

        List<Long> ids = new ArrayList<>();
        for(SparePurchaseOrderItem item : sparePurchaseOrder.getSparePurchaseOrderPartDetail()){
        	if(item.getId()!=null){
        		ids.add(item.getId());
        	}
        }
        if(!ids.isEmpty()){
        	sparePurchaseOrderRepository.deletePartFromPO(ids, sparePurchaseOrder.getId());
        }
        sparePurchaseOrderRepository.save(sparePurchaseOrder);
        
    	//Suraj_Start_15-03-2024
        orderPlanningSheetRepo.updatePOIdInOps(sparePurchaseOrder.getOpsId(), sparePurchaseOrder.getId());
        if (!sparePurchaseOrder.getDraftFlag()) {
        	orderPlanningSheetRepo.updateStatusOfOps(sparePurchaseOrder.getOpsId());
        }
    	//Suraj_End_15-03-2024
        
        if((sparePurchaseOrder.getPurchaseOrderStatus().equals("Submitted") || sparePurchaseOrder.getPurchaseOrderStatus().equals("Waiting for Approval"))&& sparePurchaseOrder.getSupplierType().equalsIgnoreCase("KAI")) {
            logger.info("PoId : "+ sparePurchaseOrder.getId());
            sparePurchaseOrderRepository.insertPoToDmsSparesPo(sparePurchaseOrder.getId());
            
            if(sparePurchaseOrder.getOrderType().getOrderType().equalsIgnoreCase(KAI_MACHINE_DOWN_ORDER)){
            	
            	List<SparePOApproval> approvalList = new ArrayList<>();
            	sparePurchaseOrderApprovalRepository.getApprovalHierarchyLevel(userAuthentication.getDealerId(), sparePurchaseOrder.getId()).forEach(map -> {
            		SparePOApproval approval = new SparePOApproval();
                    approval.setSparesOrderId(sparePurchaseOrder.getId());
                    approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
                    approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
                    approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
                    approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
                    approval.setApprovalStatus((String)map.get("approvalStatus"));
                    approval.setRejectedFlag('N');
                    approvalList.add(approval);
                });
            	sparePurchaseOrderApprovalRepository.saveAll(approvalList);
            }
        }else if(sparePurchaseOrder.getPurchaseOrderStatus().equals("Submitted") && sparePurchaseOrder.getSupplierType().equalsIgnoreCase("Co-Dealer")){
        	
        	sparePurchaseOrderRepository.generateSalesOrderForCodealerOrder(sparePurchaseOrder.getId());
        }
        totalPoAmount = 0.0;
        totalBaseAmount = 0.0;

    }


    private void calculations(SparePurchaseOrderItem sparePurchaseOrderItem) {
    	
    	Double baseAmount = (sparePurchaseOrderItem.getUnitPrice() * sparePurchaseOrderItem.getQuantity());
        sparePurchaseOrderItem.setBaseAmount(baseAmount);

        Double gstAmount = (baseAmount * sparePurchaseOrderItem.getGstPercent()) / 100;
        sparePurchaseOrderItem.setGstValue(gstAmount);

        Double totalAmount = baseAmount + gstAmount;
        sparePurchaseOrderItem.setTotalAmount(totalAmount);
        
        if(sparePurchaseOrderItem.getTcsPercent()!=null && sparePurchaseOrderItem.getTcsPercent()>0){
        	totalAmount = totalAmount + ((totalAmount * sparePurchaseOrderItem.getTcsPercent())/100); 
        }

        totalBaseAmount = totalBaseAmount + baseAmount;
        totalPoAmount = totalPoAmount + totalAmount;

    }

   /* private Double baseAmount(SparePurchaseOrderItem sparePurchaseOrderItem) {
        Double baseAmount = (sparePurchaseOrderItem.getUnitPrice() * sparePurchaseOrderItem.getQuantity());
        return baseAmount;
    }

    private Double gstAmount(SparePurchaseOrderItem sparePurchaseOrderItem) {
        Double gstAmount = (baseAmount(sparePurchaseOrderItem) * sparePurchaseOrderItem.getGstPercent()) / 100;
        return gstAmount;
    }
*/


    private void netAmountPayable(DealerOutstandingResponse dealerOutstandingResponse, SparePurchaseOrder sparePurchaseOrder) {
        /*Double netOverDue = dealerOutstandingResponse.getOverDuesOutStanding() - dealerOutstandingResponse.getPaymentUnderProcess();
        Double amountPayable = Math.max(netOverDue, totalPoAmount - dealerOutstandingResponse.getAvailableLimit());
        Double netAmountPayable = Math.max(netOverDue, amountPayable);
        Double netAmount = Math.max(0, netAmountPayable);*/
    	
    	Double amountPayable = dealerOutstandingResponse.getAvailableLimit() - dealerOutstandingResponse.getOrderUnderProcess() - totalPoAmount;
    	
    	Double netAmount = Math.max(dealerOutstandingResponse.getOverDuesOutStanding(), amountPayable);
    	
        sparePurchaseOrder.setNetAmountPayable(netAmount);
        //Avl. Limit – Orders Under Process – This Order Value
        //Overdue O/S
    }

    private void dealerOutStanding(DealerOutstandingResponse dealerOutstandingResponse, SparePurchaseOrder sparePurchaseOrder) {
        sparePurchaseOrder.setOrdersUnderProcess(dealerOutstandingResponse.getOrderUnderProcess());
        sparePurchaseOrder.setOverduesOutStanding(dealerOutstandingResponse.getOverDuesOutStanding());
        sparePurchaseOrder.setPaymentUnderProcess(dealerOutstandingResponse.getPaymentUnderProcess());
        sparePurchaseOrder.setAvailableLimit(dealerOutstandingResponse.getAvailableLimit());
        sparePurchaseOrder.setCreditLimit(dealerOutstandingResponse.getCreditLimit());
        sparePurchaseOrder.setCurrentOutStanding(dealerOutstandingResponse.getCurrentOutStanding());
    }

    private void freightBorneBy(SparePurchaseOrder sparePurchaseOrder) {
        SparesMtPurchaseOrderOrderType orderType = orderTypeRepository.getOne(sparePurchaseOrder.getOrderType().getId());
        if (totalPoAmount > 25000 && (orderType.getOrderType().equals(KAI_MONTHLY_ORDER))) {
            sparePurchaseOrder.setFreightBorneBy(KAI);
        } else if (totalPoAmount < 25000 && (orderType.getOrderType().equals(KAI_MONTHLY_ORDER))) {
            sparePurchaseOrder.setFreightBorneBy(DEALER);
        } else if (orderType.getOrderType().equals(KAI_EMERGENCY_ORDER)) {
            sparePurchaseOrder.setFreightBorneBy(DEALER);
        } else if (orderType.getOrderType().equals(KAI_MACHINE_DOWN_ORDER)) {
            sparePurchaseOrder.setFreightBorneBy(DEALER);
        } else if (orderType.getOrderType().equals(FREE_OF_COST)) {
            sparePurchaseOrder.setFreightBorneBy(KAI);
        } else if (orderType.getOrderType().equals(OIL_AND_LUBRICANT) || orderType.getOrderType().equals(CO_DEALER_OILORDER)) {
            sparePurchaseOrder.setFreightBorneBy(sparePurchaseOrder.getFreightBorneBy());
        } else if (orderType.getOrderType().equals(OTHER_SUPPLIER)) {
            sparePurchaseOrder.setFreightBorneBy(sparePurchaseOrder.getFreightBorneBy());
        } else if (orderType.getOrderType().equals(CO_DEALER_ORDER)) {
            sparePurchaseOrder.setFreightBorneBy(sparePurchaseOrder.getFreightBorneBy());
        }
    }


    @Override
    public List<Map<String, Object>> uploadExcel(MultipartFile multipartFile, String orderType, String priceType, String existingItems)
            throws InvalidColumnException, IOException {
        InputStream in = multipartFile.getInputStream();

        excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                                multipartFile.getInputStream()
                        )
                )
        );
        List<UploadExcelDto> partMasters = Poiji.fromExcel(
                in,
                PoijiExcelType.XLSX,
                UploadExcelDto.class,
                PoijiOptions.PoijiOptionsBuilder
                        .settings()
                        .headerStart(0)
                        .build()
        );
        
        StringBuffer parts = new StringBuffer("");
        StringBuffer qtys = new StringBuffer("");
        
        List<Map<String, Object>> list = new ArrayList<>();
        
        System.out.println(existingItems);
        
        partMasters.forEach(p -> {
            /*Map<String, Object> map =
                    sparePurchaseOrderRepository.getItemDetailsByItemId(
                            p.getItemId(),
                            orderType,
                            userAuthentication.getDealerId(),priceType
                    );
            Map<String, Object> map1 = new HashMap<>(map);
            map1.put("quantity", p.getQuantity());
            list.add(map1);*/
        	
        	parts.append(","+p.getItemId());
        	qtys.append(","+p.getQuantity());
        });

        if(parts.toString().length()>0){
	        list = sparePurchaseOrderRepository.getItemDetailsByExcel(parts.substring(1), qtys.substring(1), orderType, userAuthentication.getDealerId(), priceType, existingItems);
        }
        
        return list;
    }


    @Override
    public PoViewDto getViewDto(Long id, String orderType) {
        PoViewDto poViewDto = new PoViewDto();
        HeaderResponse headerResponse = new HeaderResponse();

        headerResponse.setHeaderDetails(sparePurchaseOrderRepository.getPoViewDetails(id));
        headerResponse.setOrderType(sparePurchaseOrderRepository.getPoViewOrderType(id));
        
        if(!headerResponse.getHeaderDetails().get("purchaseOrderStatus").equals("Draft") && headerResponse.getOrderType().get("orderType").equals("KAI Machine Down Order")){
        	poViewDto.setApprovalResponse(sparePurchaseOrderApprovalRepository.getSparesPoApprovalDetails(id, userAuthentication.getKubotaEmployeeId()));
        }
//                Map<String, Object> coDealer =
//                sparePurchaseOrderRepository.getPoViewCoDealerDetails(id);
//        if (coDealer.get("coDealerName") != null && coDealer.get("dealerCode") != null) {
//            headerResponse.setCoDealerMaster(coDealer);
//        }
        poViewDto.setHeaderResponse(headerResponse);
        poViewDto.setPartDetails(sparePurchaseOrderRepository.getPoViewPartDetails(id, orderType));
        poViewDto.setSupplierName(sparePurchaseOrderRepository.getPoViewSupplierName(id));
        return poViewDto;
    }


	@Override
	public OPSItemsDetailResponseDto getOPSitemsDeatail(String opsId, String priceType, String existingItems) {
		List<Map<String, Object>> items = sparePurchaseOrderRepository.getOPSitemsDeatail(opsId);
		
		int orderTypeId = (int) items.get(0).get("orderTypeId");
		String orderType = "";
		
		if(orderTypeId == 1)
			orderType = "KAI Monthly Order";
		else if(orderTypeId == 2)
			orderType = "KAI Emergency Order";
		else if(orderTypeId == 3)
			orderType = "KAI Machine Down Order";
			
		StringBuffer parts = new StringBuffer("");
        StringBuffer qtys = new StringBuffer("");
        
        List<Map<String, Object>> itemsDetails = new ArrayList<>();
        
        items.forEach(item -> {
        	parts.append("," + item.get("itemNo"));
        	qtys.append("," + item.get("quantity"));
        });
        
        if(parts.toString().length()>0){
        	itemsDetails = sparePurchaseOrderRepository.getItemDetailsByExcel(parts.substring(1), qtys.substring(1), orderType, userAuthentication.getDealerId(), priceType, existingItems);
        }
		
        OPSItemsDetailResponseDto detailResponseDto = new OPSItemsDetailResponseDto();
        detailResponseDto.setItemDetailsList(itemsDetails);
        detailResponseDto.setOrderTypeId(orderTypeId);
        detailResponseDto.setOrderType(orderType);     
        
		return detailResponseDto;
	}


}