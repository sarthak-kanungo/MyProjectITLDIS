package com.i4o.dms.itldis.spares.purchase.grn.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.repository.SparesPartMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.BinLocationSearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.BinLocationMasterRepository;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.StoreMasterRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.repository.StockAdjustmentRepo;
import com.i4o.dms.itldis.spares.purchase.grn.domain.SparePartGrn;
import com.i4o.dms.itldis.spares.purchase.grn.dto.AccpacInvoiceDetailDto;
import com.i4o.dms.itldis.spares.purchase.grn.dto.GrnDetailDto;
import com.i4o.dms.itldis.spares.purchase.grn.dto.GrnSearchDto;
import com.i4o.dms.itldis.spares.purchase.grn.dto.GrnSearchResponseDto;
import com.i4o.dms.itldis.spares.purchase.grn.repository.SparePartGrnRepository;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.SearchSparePurchaseOrder;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/grn")
@Slf4j
public class SparePartGrnController {

    @Autowired
    private SparePartGrnRepository sparePartGrnRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private SparesPartMasterRepo sparesPartMasterRepo;

    @Autowired
    private BinLocationMasterRepository binLocationMasterRepository;
    @Autowired
    private StockAdjustmentRepo stockAdjustmentRepo;
    @Autowired
    private StoreMasterRepository storeMasterRepository;

    @GetMapping("/getGrnType")
    public ResponseEntity<?> getGrnType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getGrnType());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Part Grn type successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getGrnStatus")
    public ResponseEntity<?> getGrnStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getGrnStatus());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Part Grn Status successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSupplierType")
    public ResponseEntity<?> getSupplierType(@RequestParam String grnType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getSupplierType(grnType));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Part Supplier type successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/saveGrn")
    @Transactional
    public ResponseEntity<?> saveGrn(@Valid @RequestBody SparePartGrn sparePartGrn) {
		ApiResponse apiResponse = new ApiResponse();
		sparePartGrn.getSparePartGrnItems().forEach(sparePartGrnItem -> {
			if (sparePartGrnItem.getBinLocation() != null && sparePartGrnItem.getBinLocation().getId() == null) {

				BinLocationMaster binLocation = new BinLocationMaster();
				/*
				 * BinLocationSearchResponse existingBin =
				 * binLocationMasterRepository.getExistingBinLocation(
				 * userAuthentication.getBranchId(), sparePartGrn.getStore().getId(),
				 * sparePartGrnItem.getBinLocation().getBinLocation()); if (existingBin != null)
				 * { binLocation = binLocationMasterRepository.getOne(existingBin.getId());
				 * 
				 * } else {
				 */

				/*
				 * binLocation.setBinLocation(sparePartGrnItem.getBinLocation().getBinLocation()
				 * ); binLocation.setBranchId(userAuthentication.getBranchId());
				 * binLocation.setStoreMaster(storeMasterRepository.getOne(sparePartGrn.getStore
				 * ().getId())); binLocationMasterRepository.save(binLocation);
				 */

				BigInteger locationId = stockAdjustmentRepo.createBinLocation(
						sparePartGrnItem.getSparePartMaster().getItemNo(), sparePartGrn.getStore().getId(),
						sparePartGrnItem.getBinLocation().getBinLocation(), userAuthentication.getBranchId());

				binLocation.setId(locationId.longValue());

				/* } */
				sparePartGrnItem.setBinLocation(binLocation);

			}
		});

		sparePartGrn.setBranchId(userAuthentication.getBranchId());
		sparePartGrn.setGrnStatus(sparePartGrn.getDraftFlag() ? "Draft" : "Submitted");
		sparePartGrn.setCreatedBy(userAuthentication.getLoginId());
		sparePartGrn.setCreatedDate(new Date());
		sparePartGrn.setGrnDate(new Date());
		if (sparePartGrn.getId() != null) {
			sparePartGrn.setLastModifiedBy(userAuthentication.getLoginId());
			sparePartGrn.setLastModifiedDate(new Date());
		}
		if (sparePartGrn.getSupplierType().equalsIgnoreCase("co-dealer")) {
			DealerMaster dealerMaster = new DealerMaster();
			dealerMaster.setId(sparePartGrn.getSupplierName().getId());
			sparePartGrn.setDealerMaster(dealerMaster);
			sparePartGrn.setSupplierName(null);
			sparePartGrn.setCoDealerInvoiceId(sparePartGrn.getInvoiceNumber().getId());
			sparePartGrn.setInvoiceNumber(null);
		}
		if (sparePartGrn.getSupplierType().equalsIgnoreCase("Other Suppliers")) {
			sparePartGrn.setVendor_party_id(sparePartGrn.getSupplierName().getId());
			sparePartGrn.setSupplierName(null);
		}

		SparePartGrn savedSparePartGrn = sparePartGrnRepository.save(sparePartGrn);
		System.out.println("saved grn---->>" + savedSparePartGrn.getId());
		if (sparePartGrn.getGrnStatus().equals("Submitted")) {
			sparePartGrnRepository.updateStockTable(savedSparePartGrn.getId());
		}

		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setMessage("Spare Part Grn saved successfully");
		return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchSupplierName")
    public ResponseEntity<?> searchSupplierName(@RequestParam(name="supplierType")String supplierType, @RequestParam(name="supplierName")String supplierName) {
        ApiResponse apiResponse = new ApiResponse();
        //if (supplierType.equalsIgnoreCase(THIRD_PARTY_SUPPLIER))
        apiResponse.setResult(sparePartGrnRepository.getSupplierName(supplierName, supplierType,userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Part Grn saved successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSupplierDetailsBySupplierId")
    public ResponseEntity<?> getSupplierDetailsBySupplierId(@RequestParam Long supplierId,@RequestParam(name="supplierType")String supplierType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getSupplierDetailsBySupplierId(supplierId, supplierType));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Part Grn saved successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSearchSparesInvoiceNo")
    public ResponseEntity<?> getSearchSparesInvoiceNo(@RequestParam String invoiceNo, @RequestParam Long supplierDealerId, @RequestParam(name="supplierType")String supplierType, @RequestParam Long storeId) {
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.searchSparesInvoiceNo(invoiceNo, userAuthentication.getBranchId(), supplierDealerId, supplierType, storeId));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Spare Invoice Details");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSparesInvoiceDetails")
    public ResponseEntity<?> getSparesInvoiceDetails(@RequestParam Long invoiceId, @RequestParam Long supplierDealerId, @RequestParam(name="supplierType")String supplierType, @RequestParam Long storeId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        AccpacInvoiceDetailDto accpacInvoiceDetailDto = new AccpacInvoiceDetailDto();
        accpacInvoiceDetailDto.setAccpacInvoice(sparePartGrnRepository.getInvoiceDetails(invoiceId, userAuthentication.getBranchId(), supplierDealerId, supplierType));
        accpacInvoiceDetailDto.setAccpacInvoiceItems(sparePartGrnRepository.getInvoiceItemDetails(invoiceId, userAuthentication.getBranchId(), supplierDealerId, supplierType, storeId));
        apiResponse.setResult(accpacInvoiceDetailDto);
        apiResponse.setMessage("Spare Invoice Details");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getStoresName")
    public ResponseEntity<?> getStoresName(@RequestParam(required=false)String tranType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getStoresList(userAuthentication.getBranchId(),tranType));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("stores list");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBinLocationByStoreId")
    public ResponseEntity<?> getBinLocationByStoreId(@RequestParam Long storeId, @RequestParam String binLocation,@RequestParam String itemNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartGrnRepository.getBinLocationById(storeId, binLocation, userAuthentication.getBranchId(), itemNo));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("stores list");
        return ResponseEntity.ok(apiResponse);
    }

    ////
    @ApiOperation(value = "search grn number from grn on dealer basis")
    @GetMapping("/searchBySpareGrnNumber")
    public ResponseEntity<?> searchBySpareGrnNumber(@RequestParam String grnNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchBySpareGrnNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartGrnRepository.searchBySpareGrnNumber(grnNumber, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "search acc pac number from grn on dealer basis")
    @GetMapping("/searchInvoiceNumberFromGrn")
    public ResponseEntity<?> searchInvoiceNumberFromGrn(@RequestParam String invoiceNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchBySpareGrnNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartGrnRepository.searchInvoiceNumberFromGrn(invoiceNumber, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchSpareGrn")
    public ResponseEntity<?> searchSpareGrn(@RequestBody GrnSearchDto grnSearchDto) {
    	
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchBySpareGrnNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        List<GrnSearchResponseDto> result = sparePartGrnRepository.searchSpareGrn(grnSearchDto.getDmsGrnNumber(),
                grnSearchDto.getGrnType(), grnSearchDto.getGrnStatus(), grnSearchDto.getInvoiceNumber(),
                grnSearchDto.getSupplierType(), grnSearchDto.getSupplierName(),
                grnSearchDto.getFromDate(), grnSearchDto.getToDate(),
                grnSearchDto.getResult(),
                userAuthentication.getDealerId(), userAuthentication.getDealerEmployeeId(),
                grnSearchDto.getPage(), grnSearchDto.getSize(),userAuthentication.getUsername(), 'N', 0l, "View"
                );
        apiResponse.setResult(result);
        Long recordCount = 0l;
        if(result!=null && result.size()>0){
        	recordCount = result.get(0).getRecordCount();
        }
        apiResponse.setCount(recordCount);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/downloadReport")
    public ResponseEntity<InputStreamResource> downloadReport(@RequestBody GrnSearchDto grnSearchDto,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;
    	
    	List<GrnSearchResponseDto> result = sparePartGrnRepository.searchSpareGrn(grnSearchDto.getDmsGrnNumber(),
                grnSearchDto.getGrnType(), grnSearchDto.getGrnStatus(), grnSearchDto.getInvoiceNumber(),
                grnSearchDto.getSupplierType(), grnSearchDto.getSupplierName(),
                grnSearchDto.getFromDate(), grnSearchDto.getToDate(),
                grnSearchDto.getResult(),
                userAuthentication.getDealerId(), userAuthentication.getDealerEmployeeId(),
                grnSearchDto.getPage(), grnSearchDto.getSize(),userAuthentication.getUsername(), 'N', 0l, "Details"
                );
    	
    	ByteArrayInputStream in = ExcelCellGenerator.spareGrnDetailReport(result);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "SpareGRNDetailReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    @GetMapping("/getSpareGrnByGrnId/{grnId}")
    public ResponseEntity<?> getGrnByDmsGrnNumber(@PathVariable Long grnId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchGrn");
        apiResponse.setStatus(HttpStatus.OK.value());
        GrnDetailDto detailDto = new GrnDetailDto();
        detailDto.setGrn(sparePartGrnRepository.findGrnByGrnId(grnId));
        detailDto.setGrnItems(sparePartGrnRepository.findGrnItemDetailByGrnId(grnId));
        apiResponse.setResult(detailDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchPoNumberForGrn")
    public ResponseEntity<?> searchGrnByPoNumber(@RequestParam String poNumber, @RequestParam Long supplierId, @RequestParam String supplierType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search Po number for grn");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartGrnRepository.searchPoNumberForGrn(poNumber, supplierId, userAuthentication.getDealerId(), supplierType));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPoDetailsByPoNumberForGrn")
    public ResponseEntity<?> getPoDetailsByPoNumberForGrn(@RequestParam Long poId, @RequestParam Long storeId) {
        ApiResponse apiResponse = new ApiResponse();
        //check po no exists with draft
        //if (!sparePartGrnRepository.checkPoExistsWithDraft(poId)) {
            AccpacInvoiceDetailDto detailDto = new AccpacInvoiceDetailDto();
            detailDto.setAccpacInvoice(sparePartGrnRepository.getPoDetailsByPoNumberForGrn(poId, userAuthentication.getDealerId()));
            detailDto.setAccpacInvoiceItems(sparePartGrnRepository.getPoItemDetailsByPoNumberForGrn(poId, userAuthentication.getBranchId(),storeId));
            apiResponse.setResult(detailDto);
            apiResponse.setMessage("search Po number for grn");
            apiResponse.setStatus(HttpStatus.OK.value());
      /*  }else{
            apiResponse.setMessage("Draft GRN already exists for this PO");
            apiResponse.setStatus(HttpStatus.OK.value());
        }*/
        return ResponseEntity.ok(apiResponse);
    }
}
