package com.i4o.dms.kubota.spares.inventorymanagement.btbt.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.BinLocationSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.BinLocationMasterRepository;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.StoreMasterRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.domain.SparePartBinTransferHDR;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.SpareBinTransferRequestBody;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.SpareBinTransferSearchDto;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.SpareBinTransferSearchResponseDto;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto.UploadExcelDto;
import com.i4o.dms.kubota.spares.inventorymanagement.btbt.repository.BinToBinTransferRepository;
import com.i4o.dms.kubota.spares.salesorder.dto.SpareSaleOrderResponseDto;
import com.i4o.dms.kubota.spares.salesorder.dto.UploadCustomerExcelDto;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;
import com.i4o.dms.kubota.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/btbt")
public class BinToBinTransferController {
	
	String[] PreDefinedColumns = new String[]{"ItemNumber","FromStore","FromLocation","TransferQuantity","ToStore","ToLocation"};

    @Autowired
    private ExcelImportManager excelImportManager;
    
    @Autowired
    private StoreMasterRepository storeMasterRepository;
    
    @Autowired
    private BinLocationMasterRepository binLocationMasterRepository;
    
	@Autowired
    private BinToBinTransferRepository btbtDTLRepository;
	
	@Autowired
    private UserAuthentication userAuthentication;
	
	
	@GetMapping("/getAvlQtyForStoreBin")
    public ResponseEntity<?> getAvlQtyForStoreBin(@RequestParam String itemNo,
    											  @RequestParam Long Stockstoreid,
    											  @RequestParam Long StockBInId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("available qty for store bin");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(btbtDTLRepository.getAvlQtyForStoreBin(userAuthentication.getBranchId(), itemNo, Stockstoreid, StockBInId));
        return ResponseEntity.ok(apiResponse);
    }
	@GetMapping("searchAutoTransferNumber")
	public ResponseEntity<?> searchAutoTransferNumber(@RequestParam("transferNo")String transferNo){
		ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Transfer No search");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(btbtDTLRepository.searchAutoTransferNumber(transferNo,userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/searchBinTransfer")
	public ResponseEntity<?> searchBinTransfer(@RequestBody SpareBinTransferSearchDto searchDto){
		ApiResponse apiResponse = new ApiResponse();
		List<SpareBinTransferSearchResponseDto> result = btbtDTLRepository.getBinTransferSearchResult(
				searchDto.getTransferNumber(), 
				searchDto.getFromDate(), 
				searchDto.getToDate(), 
				userAuthentication.getBranchId(),
				userAuthentication.getDealerEmployeeId(),
				searchDto.getPage(), 
				searchDto.getSize(), 
				userAuthentication.getUsername());
		Long count = 0l;
		if(result!=null && result.size()>0){
			count = result.get(0).getRecordCount();
		}
		apiResponse.setCount(count);
		apiResponse.setResult(result);
        apiResponse.setMessage("Search Bin Transfer Records");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/downloadBBExcelReport")
	public ResponseEntity<InputStreamResource> downloadBBExcelReport(@RequestBody SpareBinTransferSearchDto searchDto,HttpServletResponse response) throws IOException{
	
		Integer size = Integer.MAX_VALUE-1;
		List<SpareBinTransferSearchResponseDto> result = btbtDTLRepository.getBinTransferSearchResult(
				searchDto.getTransferNumber(), 
				searchDto.getFromDate(), 
				searchDto.getToDate(), 
				userAuthentication.getBranchId(),
				userAuthentication.getDealerEmployeeId(),
				searchDto.getPage(), 
				size, 
				userAuthentication.getUsername());
		ByteArrayInputStream byteInputStream = ExcelCellGenerator.bintobinTransferExcelReport(result);
        response.setContentType("application/vnd.ms-excel");
        HttpHeaders headers = new HttpHeaders();
        String filename = "BinToBinTransferDetails_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(byteInputStream));
	}
	
	
	@GetMapping("/getToBinLocations")
    public ResponseEntity<?> getBinLocationByStoreId(@RequestParam Long storeId, 
    												 @RequestParam String binLocation,
    												 @RequestParam String itemNo,
    												 @RequestParam(required=false) String selectedFromBin,
    												 @RequestParam(required=false) String tranType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(btbtDTLRepository.getBinToLocations(storeId, binLocation, userAuthentication.getBranchId(), itemNo, tranType, selectedFromBin));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("stores list");
        return ResponseEntity.ok(apiResponse);
    }
	
	@PostMapping("/saveBtBtDetails")
	public ResponseEntity<?> saveBtBtDetails(@RequestBody SpareBinTransferRequestBody requestBody){
		ApiResponse apiResponse = new ApiResponse();

		SparePartBinTransferHDR hdr = new SparePartBinTransferHDR();
		hdr.setBranchId(userAuthentication.getBranchId());
		hdr.setCreatedby(userAuthentication.getLoginId());
		hdr.setCreateddate(new Date());
		hdr.setTransferDate(new Date());
		hdr = btbtDTLRepository.save(hdr);
		
		Long refTransHdrId = hdr.getBinTransferId();
		
		requestBody.getItemDetails().forEach(item -> {
			if(item.getToLocation().getId()==null){
				BinLocationSearchResponse existingBin = binLocationMasterRepository.getExistingBinLocation(
						userAuthentication.getBranchId(), item.getToStore().getId(),
						item.getToLocation().getValue());
				if (existingBin != null) {
					item.getToLocation().setId(existingBin.getId());
				} else {
					BinLocationMaster binLocation = new BinLocationMaster();
					binLocation.setBinLocation(item.getToLocation().getValue());
					binLocation.setBranchId(userAuthentication.getBranchId());
					binLocation.setStoreMaster(storeMasterRepository.getOne(item.getToStore().getId()));
					binLocation = binLocationMasterRepository.save(binLocation);
					item.getToLocation().setId(binLocation.getId());
				}
			}
			
			try{
				btbtDTLRepository.sparesUpdateStockBin2BinDetail(userAuthentication.getBranchId(), 
						item.getItemNo().getItemNo(), 
						item.getFromStore().getId(), 
						item.getFromLocation().getId(), 
						"BIN2BIN", 
						refTransHdrId, 
						item.getTransferQty(), 
						item.getToStore().getId(), 
						item.getToLocation().getId(), 
						userAuthentication.getLoginId());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		});
		
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Transferred successfuly");
        return ResponseEntity.ok(apiResponse);
	}

	
	
	@PostMapping(value = "/uploadExcel")
    public ResponseEntity<?> uploadExcel(MultipartFile file,
                                         @RequestPart(required=false) String existingItems) {
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
            StringBuffer fromStores = new StringBuffer("");
            StringBuffer toStores = new StringBuffer("");
            StringBuffer fromLocations = new StringBuffer("");
            StringBuffer toLocations = new StringBuffer("");
            StringBuffer qtys = new StringBuffer("");
            
            List<Map<String, Object>> list = new ArrayList<>();
            String regex = "^[A-Z][0-9][0-9]-[0-9][0-9]-[a-zA-Z0-9][a-zA-Z0-9]";
            Pattern pattern = Pattern.compile(regex);
            
            partMasters.forEach(p -> {
            	Matcher matcher = pattern.matcher(p.getToLocation());
            	if(matcher.matches()) {
            		System.out.println("matches");
            		parts.append(","+(p.getItemNumber()==null?"":p.getItemNumber()));
                	fromStores.append(","+(p.getFromStore()==null?"":p.getFromStore()));
                	toStores.append(","+(p.getToStore()==null?"":p.getToStore()));
                	fromLocations.append(","+(p.getFromLocation()==null?"":p.getFromLocation()));
                	toLocations.append(","+(p.getToLocation()==null?"":p.getToLocation()));
                	qtys.append(","+(p.getQuantity()==null?0:p.getQuantity().intValue()));
            	}
            });
            System.out.println("qtys : "+qtys);
            if(parts.toString().length()>0){
        		//sp_spare_bin2bin_get_item_details_by_excel
    	       list = btbtDTLRepository.uploadExcelBinTransfer(parts.substring(1), 
    	    		   qtys.substring(1),
    	    		   fromStores.substring(1),
    	    		   toStores.substring(1),
    	    		   fromLocations.substring(1),
    	    		   toLocations.substring(1),
    	    		   existingItems,
    	    		   userAuthentication.getBranchId());
            }
            apiResponse.setResult(list);
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
}
