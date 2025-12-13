package com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.BinLocationMasterRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.claim.domain.ServiceClaimApprovalEntity;
import com.i4o.dms.kubota.service.claim.domain.ServiceClaimApprovalRequestModel;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.domain.ApprovalRequestModel;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.domain.StockAdjustmentApprovalEntity;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.domain.StockAdjustmentDtl;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.domain.StockAdjustmentHdr;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentDeatils;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchDto;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.StockAdjustmentSearchResult;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.dto.UploadExcelDto;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.repository.StockAdjustmentApprovalRepo;
import com.i4o.dms.kubota.spares.inventorymanagement.stockAdjustment.repository.StockAdjustmentRepo;
import com.i4o.dms.kubota.spares.invoice.dto.ResponseSearchSparesInvoice;
import com.i4o.dms.kubota.spares.invoice.dto.SpareInvoiceSearch;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;
import com.i4o.dms.kubota.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/spares/stockAdjustment")
public class StockAdjustmentController {
	String[] PreDefinedColumns = new String[]{"Adjustment Type","Part No","Store","Bin Name","MRP","Qty"};

	@Autowired
	StockAdjustmentApprovalRepo approvalRepo;
	@Autowired
    private UserAuthentication userAuthentication;
	@Autowired
	private StockAdjustmentRepo stockAdjustmentRepo;
    @Autowired
    private ExcelImportManager excelImportManager;
    @Autowired
    private BinLocationMasterRepository binLocationMasterRepository;
    
	@PostMapping(value = "/excelUpload")
    public ResponseEntity<?> uploadExcel(MultipartFile file) {
        ApiResponse apiResponse = new ApiResponse();

        try {
        	InputStream in = file.getInputStream();
        	excelImportManager.checkXLSValidity(
                    PreDefinedColumns,
                    excelImportManager.getXLSHeaders(
                            WorkbookFactory.create(
                            		file.getInputStream()
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
            StringBuffer stores = new StringBuffer("");
            StringBuffer locations = new StringBuffer("");
            StringBuffer mrps = new StringBuffer("");
            StringBuffer qtys = new StringBuffer("");
            StringBuffer adjustmentTypes = new StringBuffer("");
            
            List<Map<String, Object>> list = new ArrayList<>();
            String regex = "^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]";
            Pattern pattern = Pattern.compile(regex);
            
            partMasters.forEach(p -> {
                Matcher matcher = pattern.matcher(p.getLocation());
            	if(p.getAdjustmentType().equals("D") || matcher.matches()) {
            		System.out.println("matches : "+p.getItemNumber());
            		parts.append(","+(p.getItemNumber()==null?"":p.getItemNumber()));
                	stores.append(","+(p.getStore()==null?"":p.getStore()));
                	locations.append(","+(p.getLocation()==null?"":p.getLocation()));
                	mrps.append(","+(p.getMrp()==null?"":p.getMrp()));
                	qtys.append(","+(p.getQuantity()==null?"":p.getQuantity()));
                	adjustmentTypes.append(","+(p.getAdjustmentType()==null?"":p.getAdjustmentType()));
            	}
            	
            });
            if(parts.toString().length()>0){
        		//sp_spare_bin2bin_get_item_details_by_excel
    	       list = stockAdjustmentRepo.uploadExcel(
    	    		   adjustmentTypes.substring(1),
    	    		   parts.substring(1), 
    	    		   stores.substring(1),
    	    		   locations.substring(1),
    	    		   mrps.substring(1),
    	    		   qtys.substring(1),
    	    		   userAuthentication.getBranchId());
    	       List<StockAdjustmentDtl> dtls = new ArrayList<>();

	  	         StockAdjustmentHdr stockDetails = new StockAdjustmentHdr();
	  	         stockDetails.setAdjustmentDate(new Date());
	  			 stockDetails.setBranchId(userAuthentication.getBranchId());
	  			 stockDetails.setCreatedBy(userAuthentication.getLoginId());
	  			 stockDetails.setCreatedDate(new Date());
	  		   List<Map<String,Object>> result = new ArrayList<>();
    	       list.forEach(l -> {
    	    	   StockAdjustmentDtl dtl = new StockAdjustmentDtl();
    	    	   if(l.get("validItem").toString().equals("Y")){
	    	    	   dtl.setPartNo(l.get("itemNo").toString());
	    	    	   dtl.setAdjustmentType(l.get("adjType").toString());
	    	    	   dtl.setStoreId(((BigInteger)l.get("store_id")).longValue());
	    	    	   dtl.setStockBinId(((BigInteger)l.get("bin_location")).longValue());
	    	    	   dtl.setQtyAdjusted((Integer)l.get("qty"));
	    	    	   dtl.setMrp((BigDecimal)l.get("mrp"));
	    	    	   dtl.setSrNo(((BigInteger)l.get("SRN")).intValue());
	    	    	   if(dtl.getAdjustmentType().equals("I")){
	    	    		   dtl.setIncreasedAmount(dtl.getMrp().multiply(BigDecimal.valueOf(dtl.getQtyAdjusted().doubleValue())));
	    	    		   dtl.setDecreasedAmount(BigDecimal.ZERO);
	    	    		  }
	    	    	   if(dtl.getAdjustmentType().equals("D")){
	    	    		   dtl.setDecreasedAmount(dtl.getMrp().multiply(BigDecimal.valueOf(dtl.getQtyAdjusted().doubleValue())));
	    	    		   dtl.setIncreasedAmount(BigDecimal.ZERO);
	    	    		  }
	    	    	   dtl.setStockAdjustmentHdr(stockDetails);
	    	    	   dtls.add(dtl);
    	    	   }else{
    	    		   Map<String,Object> map = new HashMap<>();
    	    		   map.put("itemNo", l.get("itemNo"));
    	    		   map.put("itemDescription", l.get("itemDescription"));
    	    		   map.put("msg", "Invalid Part / Qty");
    	    		   result.add(map);  
    	    	   }
    	       });
    			 
    	       stockDetails.setStockAdjustmentDtls(dtls);
    	       if(dtls.size()>0){
    	    	   stockDetails.setAdjustmentStatus("Waiting For Approval");
    	    	   stockAdjustmentRepo.save(stockDetails);
    			
    	    	   List<StockAdjustmentApprovalEntity> approvalList = new ArrayList<StockAdjustmentApprovalEntity>();
    	  		   stockAdjustmentRepo.getStockAdjApprovalHierarchyLevel(userAuthentication.getDealerId())
	    	  	        .forEach(map -> {
	    	  	        	StockAdjustmentApprovalEntity approval = new StockAdjustmentApprovalEntity();
	    	  	            approval.setStkadjHdrId(stockDetails.getStkadjHdrId());
	    	  	            approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
	    	  	            approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
	    	  	            approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
	    	  	            approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
	    	  	            approval.setApprovalStatus((String)map.get("approvalStatus"));
	    	  	            approval.setRejectedFlag('N');
	    	  	            approvalList.add(approval);
	    	  	        });
    	  	        approvalRepo.saveAll(approvalList);
    			 //List<Map<String,Object>> result1 = stockAdjustmentRepo.updateStockTable(stockDetails.getStkadjHdrId(), userAuthentication.getBranchId(), userAuthentication.getLoginId());
    			 //result.addAll(result1);
    	       }
    			 
    	         apiResponse.setResult(result);
            }
            apiResponse.setMessage("Excel Uploaded Item Details");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Message" + e.getMessage());

            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
	
	@Transactional
	@RequestMapping(value="/saveStockAdjDetails", method=RequestMethod.POST)
	public ResponseEntity<?> saveStockAdjDetails(@RequestBody StockAdjustmentHdr stockDetails){
		 ApiResponse apiResponse = new ApiResponse();
		 stockDetails.setAdjustmentDate(new Date());
		 stockDetails.setBranchId(userAuthentication.getBranchId());
		 stockDetails.setCreatedBy(userAuthentication.getLoginId());
		 stockDetails.setCreatedDate(new Date());

         
		 stockDetails.getStockAdjustmentDtls().stream().forEach(stockDtl -> {
			 if(stockDtl.getStockBinId()==null){
				 /*StoreMaster storeMaster = new StoreMaster();
				 storeMaster.setId(stockDtl.getStoreId());
				 
				 BinLocationMaster locationMaster = new BinLocationMaster();
				 locationMaster.setBranchId(userAuthentication.getBranchId());
				 locationMaster.setBinLocation(stockDtl.getBinLocation());
				 locationMaster.setStoreMaster(storeMaster);
				 
				 BinLocationMaster m = binLocationMasterRepository.save(locationMaster);*/
				 
				 Long binid = stockAdjustmentRepo.createBinLocation(stockDtl.getPartNo(),stockDtl.getStoreId(),stockDtl.getBinLocation(),userAuthentication.getBranchId()).longValue();
				 
				 stockDtl.setStockBinId(binid);

			 }
		 });
		 stockDetails.setAdjustmentStatus("Waiting For Approval");
		 stockAdjustmentRepo.save(stockDetails);
		 
		 List<StockAdjustmentApprovalEntity> approvalList = new ArrayList<StockAdjustmentApprovalEntity>();
		 stockAdjustmentRepo.getStockAdjApprovalHierarchyLevel(userAuthentication.getDealerId())
	        .forEach(map -> {
	        	StockAdjustmentApprovalEntity approval = new StockAdjustmentApprovalEntity();
	            approval.setStkadjHdrId(stockDetails.getStkadjHdrId());
	            approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
	            approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
	            approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
	            approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
	            approval.setApprovalStatus((String)map.get("approvalStatus"));
	            approval.setRejectedFlag('N');
	            approvalList.add(approval);
	        });
	        approvalRepo.saveAll(approvalList);
	        
		 List<Map<String,Object>> result = stockAdjustmentRepo.updateStockTable(stockDetails.getStkadjHdrId(), userAuthentication.getBranchId(), userAuthentication.getLoginId());
         apiResponse.setResult(result);
	     apiResponse.setMessage("Stock Details Saved successfully");
	     apiResponse.setStatus(HttpStatus.OK.value());
	     return ResponseEntity.ok(apiResponse);
	}
	
	@RequestMapping(value="/seachStockAdjDetails", method=RequestMethod.POST)
	public ResponseEntity<?> seachStockAdjDetails(@RequestBody StockAdjustmentSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<StockAdjustmentSearchResult> result = stockAdjustmentRepo.searchAdjustmentList(searchDto.getStockAdjustmentNo(), 
				searchDto.getAdjustmentFromDate(), searchDto.getAdjustmentToDate(), searchDto.getPage(), searchDto.getSize(), 
				userAuthentication.getUsername(),"View", searchDto.getAdjustmentStatus(), searchDto.getHierId(), searchDto.getDealerId());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getTotalCount();
		}
		apiResponse.setResult(result);
		apiResponse.setCount(count);
	    apiResponse.setMessage("Fetch Stock Details");
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	}
	
	@RequestMapping(value="/getStockAdjDetails", method=RequestMethod.GET)
	public ResponseEntity<?> getStockAdjDetails(@RequestParam("id")Long id){
		ApiResponse apiResponse = new ApiResponse();
		List<StockAdjustmentDeatils> result = stockAdjustmentRepo.getAdjustmentDetails(id, userAuthentication.getBranchId());

		Map<String,Object> map = new HashMap<>();
		map.put("result", result);
		if(userAuthentication.getBranchId()==null){
		List<Map<String,Object>> hieries = stockAdjustmentRepo.getApprovalHierDetails(id, userAuthentication.getUsername());
			map.put("hieries", hieries);
		}
		if(result!=null){
			apiResponse.setResult(map);
			apiResponse.setMessage("Stock Details Fetched Successfuly");
		}else{
			apiResponse.setMessage("Stock Detail not found");
		}
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	}
	
	@RequestMapping(value="/adjustmentNoAuto", method=RequestMethod.GET)
	public ResponseEntity<?> adjustmentNoAuto(@RequestParam("searchVal")String searchVal){
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String,Object>> result = stockAdjustmentRepo.getAdjustmentNumber(searchVal, userAuthentication.getBranchId(), userAuthentication.getUsername());
		apiResponse.setResult(result);
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	}
	
	@RequestMapping(value="/getStockValue", method=RequestMethod.GET)
	public ResponseEntity<?> getCurrentStockValue(@RequestParam("binId")Long binId,@RequestParam("storeId")Long storeId, @RequestParam("itemNo")String itemNo){
		ApiResponse apiResponse = new ApiResponse();
		Integer currentStock = stockAdjustmentRepo.getCurrentStockValue(itemNo, storeId, binId, userAuthentication.getBranchId());
		apiResponse.setResult(currentStock);
	    apiResponse.setStatus(HttpStatus.OK.value());
	    return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/approval")
    public ResponseEntity<?> stockAdjApproval(@RequestBody ApprovalRequestModel request) {
        ApiResponse apiResponse = new ApiResponse();
        String msg = stockAdjustmentRepo.adjustmentApproval(request.getAdjId(), userAuthentication.getKubotaEmployeeId(), request.getRemarks(), userAuthentication.getUsername(), request.getApprovalStatus());
        if(msg!=null && msg.equals("Approved")){
        	List<Map<String,Object>> result = stockAdjustmentRepo.updateStockTable(request.getAdjId(), userAuthentication.getBranchId(), userAuthentication.getLoginId());
        	apiResponse.setResult(result);
        }
        apiResponse.setMessage(msg);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/downloadAdjustmentReport")
    public ResponseEntity<InputStreamResource> downloadAdjustmentReport(@RequestBody StockAdjustmentSearchDto searchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;

		List<StockAdjustmentSearchResult> result = stockAdjustmentRepo.searchAdjustmentList(searchDto.getStockAdjustmentNo(), searchDto.getAdjustmentFromDate(), searchDto.getAdjustmentToDate(), searchDto.getPage(), size, userAuthentication.getUsername(),"Details", searchDto.getAdjustmentStatus(), searchDto.getHierId(), searchDto.getDealerId());
	
    	ByteArrayInputStream in = ExcelCellGenerator.adjustmentReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "StockAdjustmentReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
}
