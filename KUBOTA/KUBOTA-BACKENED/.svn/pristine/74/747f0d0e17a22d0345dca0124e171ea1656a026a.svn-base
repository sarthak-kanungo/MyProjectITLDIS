package com.i4o.dms.kubota.warranty.warrantyclaimrequest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
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

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto.ProposalApproval;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.kubota.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;
import com.i4o.dms.kubota.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.kubota.warranty.goodwill.dto.GoodwillViewDto;
import com.i4o.dms.kubota.warranty.goodwill.repository.WarrantyGoodwillRepo;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrApproval;
import com.i4o.dms.kubota.warranty.pcr.dto.PcrApprovalDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrDetailsExcelResponse;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrSearchDto;
import com.i4o.dms.kubota.warranty.pcr.dto.WarrantyPcrSummaryExcelResponse;
import com.i4o.dms.kubota.warranty.pcr.repository.WarrantyPcrRepo;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain.WarrantyWcr;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.domain.WarrantyWcrApproval;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.PcrWarrantyClaimDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrExcelSummaryResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrResponse;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrSearch;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WarrantyWcrView;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WcrApprovalDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WcrReportDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.dto.WcrWarrantyDto;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.repository.WarrantyWcrApprovalRepo;
import com.i4o.dms.kubota.warranty.warrantyclaimrequest.repository.WarrantyWcrRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/Wcr")
public class WarrantyWcrController {

    @Autowired
    private WarrantyPcrRepo warrantyPcrRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private WarrantyWcrRepo warrantyWcrRepo;
    
    @Autowired
    private StorageService storageService;

    @Autowired
    private WarrantyGoodwillRepo warrantyGoodwillRepo;
    
    @Autowired
    private WarrantyWcrApprovalRepo warrantyWcrApprovalRepo;


    @GetMapping(value = "pcrWarrantyClaim")
    public ResponseEntity<?> pcrWarrantyClaim(@RequestParam String pcrNo,@RequestParam Long id)  {
        ApiResponse apiResponse=new ApiResponse();
        WcrWarrantyDto wcrWarrantyDto=new WcrWarrantyDto();
        
        PcrWarrantyClaimDto claimDto = warrantyPcrRepo.findByPcrNoAndId(pcrNo,id);
        Map<String, Object> map = warrantyPcrRepo.getDealerDetails(claimDto.getBranchId());
        claimDto.setEmailId((String)map.get("email_id"));
        claimDto.setDealerCode((String)map.get("dealer_code"));
        claimDto.setMobileNumber((String)map.get("mobile_no"));
        
        wcrWarrantyDto.setPcrWarrantyClaimDto(claimDto);
        
        
        wcrWarrantyDto.setWarrantyPart(warrantyWcrRepo.getJobCardPartWarrantyInfo(pcrNo, "PCR"));
        wcrWarrantyDto.setLabourCharge(warrantyWcrRepo.getLabourCharge(pcrNo, "PCR"));
        wcrWarrantyDto.setOutSideLabourCharge(warrantyWcrRepo.getOutSideLabourCharge(pcrNo, "PCR"));
        apiResponse.setResult(wcrWarrantyDto);
        apiResponse.setMessage("get pcr details  wcr");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "goodwillWarrantyClaim")
    public ResponseEntity<?> goodwillWarrantyClaim(@RequestParam String goodwillNo,@RequestParam Long id)  {
        ApiResponse apiResponse=new ApiResponse();
        WcrWarrantyDto wcrWarrantyDto=new WcrWarrantyDto();
        
        GoodwillViewDto claimDto = warrantyGoodwillRepo.findByGoodwillNo(goodwillNo);
        Map<String, Object> map = warrantyPcrRepo.getDealerDetails(claimDto.getBranchId());
        claimDto.setEmailId((String)map.get("email_id"));
        claimDto.setDealerCode((String)map.get("dealer_code"));
        claimDto.setMobileNumber((String)map.get("mobile_no"));
        
        wcrWarrantyDto.setGoodwillViewDto(claimDto);
        
        
        wcrWarrantyDto.setWarrantyPart(warrantyWcrRepo.getJobCardPartWarrantyInfo(claimDto.getWarrantyPcr().getPcrNo(), "GOODWILL"));
        wcrWarrantyDto.setLabourCharge(warrantyWcrRepo.getLabourCharge(claimDto.getWarrantyPcr().getPcrNo(), "GOODWILL"));
        wcrWarrantyDto.setOutSideLabourCharge(warrantyWcrRepo.getOutSideLabourCharge(claimDto.getWarrantyPcr().getPcrNo(), "GOODWILL"));
        apiResponse.setResult(wcrWarrantyDto);
        apiResponse.setMessage("get goodwill details  wcr");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "saveWcr")
    public ResponseEntity<?> saveWcr(@RequestBody WarrantyWcr warrantyWcr)  {
        List<SparePartRequisitionItem>sparePartRequisitionItems=new ArrayList<>();
        warrantyWcr.setBranchId(userAuthentication.getBranchId());
        warrantyWcr.setCreatedBy(userAuthentication.getLoginId());
        warrantyWcr.setCreatedOn(new Date());
        warrantyWcr.setWcrDate(new Date());
        if(warrantyWcr.getId()!=null){
        	warrantyWcr.setModifiedBy(userAuthentication.getLoginId());
        	warrantyWcr.setModifiedOn(new Date());
        }
        ApiResponse apiResponse=new ApiResponse();
        warrantyWcr.setWcrStatus("Submitted");
        if(warrantyWcr.getWarrantyPcr()==null){
        	warrantyWcr.setWcrType("GOODWILL");
        }else{
        	warrantyWcr.setWcrType("PCR");
        }
        WarrantyWcr warrantyWcr1= warrantyWcrRepo.save(warrantyWcr);
        
        if(warrantyWcr.getWarrantyPcr()==null){
        	if(warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getSparePartRequisitionItem()!=null){
    	        warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
    	        	sparePartRequisitionItemRepo.updateWarrantyClaimPartGoodwill(warrantyWcr1.getId(), sparePartRequisitionItem.getClaimApprovedAmount(), sparePartRequisitionItem.getClaimApprovedQuantity() , sparePartRequisitionItem.getId());
    	        });
            }
            if(warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getLabourCharge()!=null){
    	        warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getLabourCharge().forEach(labourItem -> {
    	        	sparePartRequisitionItemRepo.updateWarrantyClaimLabourGoodwill(warrantyWcr1.getId(), labourItem.getClaimApprovedAmount(), labourItem.getId());
    	        });
            }
            if(warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getOutsideJobCharge()!=null){
    	        warrantyWcr.getWarrantyGoodwill().getWarrantyPcr().getServiceJobCard().getOutsideJobCharge().forEach(outsideChargeItem -> {
    	        	sparePartRequisitionItemRepo.updateWarrantyClaimOutsideChargeGoodwill(warrantyWcr1.getId(), outsideChargeItem.getClaimApprovedAmount(), outsideChargeItem.getId());
    	        });
            }
        }else{
	        if(warrantyWcr.getWarrantyPcr().getServiceJobCard().getSparePartRequisitionItem()!=null){
		        warrantyWcr.getWarrantyPcr().getServiceJobCard().getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimPartPcr(warrantyWcr1.getId(), sparePartRequisitionItem.getClaimApprovedAmount(), sparePartRequisitionItem.getClaimApprovedQuantity() , sparePartRequisitionItem.getId());
		        });
	        }
	        if(warrantyWcr.getWarrantyPcr().getServiceJobCard().getLabourCharge()!=null){
		        warrantyWcr.getWarrantyPcr().getServiceJobCard().getLabourCharge().forEach(labourItem -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimLabourPcr(warrantyWcr1.getId(), labourItem.getClaimApprovedAmount(), labourItem.getId());
		        });
	        }
	        if(warrantyWcr.getWarrantyPcr().getServiceJobCard().getOutsideJobCharge()!=null){
		        warrantyWcr.getWarrantyPcr().getServiceJobCard().getOutsideJobCharge().forEach(outsideChargeItem -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimOutsideChargePcr(warrantyWcr1.getId(), outsideChargeItem.getClaimApprovedAmount(), outsideChargeItem.getId());
		        });
	        }
        }

        List<WarrantyWcrApproval> warrantyWcrApprovals = new ArrayList<>();
        warrantyWcrApprovalRepo.getWarrantyWcrApprovalHierarchyLevel(userAuthentication.getDealerId())
                .forEach(designationHierarchy -> {
                    WarrantyWcrApproval approval = new WarrantyWcrApproval();
                    approval.setWarrantyWcrId(warrantyWcr.getId());
                    approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                    approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                    approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                    approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                    approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                    approval.setRejectedFlag('N');
                    warrantyWcrApprovals.add(approval);
                });
        warrantyWcrApprovalRepo.saveAll(warrantyWcrApprovals);
        
        apiResponse.setMessage("WCR submitted successfully");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "searchWcrCreditIssued")
    public ResponseEntity<?> searchWcrCreditIssued()  {
        ApiResponse apiResponse=new ApiResponse();
        Integer size = Integer.MAX_VALUE-1;
        
		List<WarrantyWcrResponse> list = warrantyWcrRepo.searchWcr(userAuthentication.getManagementAccess(),
				userAuthentication.getKubotaEmployeeId(), 
				null,
				null,null,null,
				"Credit Note Issued", null, null,
				null, null, null,
				null, null,
				null, null,
				null, null,
				null, null, 0l,
				size.longValue(), null, userAuthentication.getUsername(), 'N',
				null, null, null);
		if(list!=null)
			list = list.stream().filter(l -> l.getFinalStatus()==null).collect(Collectors.toList());
        apiResponse.setResult(list);
        apiResponse.setMessage("search wcr credit issued");
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/wcrFinalStatusUpdate")
    public ResponseEntity<?> wcrFinalStatusUpdate(@Valid @RequestBody List<WcrApprovalDto> proposalList) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        ResponseEntity<?> entity = null;
        proposalList.stream().filter(proposalApproval -> proposalApproval.getIsSelect()!=null && proposalApproval.getIsSelect()).forEach(proposalApproval -> {
        	warrantyWcrRepo
            .updateStatus(proposalApproval.getStatus(),userAuthentication.getLoginId(),proposalApproval.getId());
    
        });
        apiResponse.setMessage("WCR Status has been updated");
        apiResponse.setStatus(HttpStatus.OK.value());
        entity = ResponseEntity.ok(apiResponse);
        return entity;
    }
    @PostMapping(value = "searchWcr")
    public ResponseEntity<?> searchWcr(@RequestBody WarrantyWcrSearch warrantyWcrSearch)  {
        ApiResponse apiResponse=new ApiResponse();
        
		List<WarrantyWcrResponse> list = warrantyWcrRepo.searchWcr(userAuthentication.getManagementAccess(),
				userAuthentication.getKubotaEmployeeId(), 
				userAuthentication.getDealerId()==null?warrantyWcrSearch.getDealerId():userAuthentication.getDealerId(),
				userAuthentication.getDealerEmployeeId(), warrantyWcrSearch.getWcrNo(), warrantyWcrSearch.getWcrType(),
				warrantyWcrSearch.getWcrStatus(), warrantyWcrSearch.getPcrNo(), warrantyWcrSearch.getJobCardNo(),
				warrantyWcrSearch.getChassisNo(), warrantyWcrSearch.getWcrFromDate(), warrantyWcrSearch.getWcrToDate(),
				warrantyWcrSearch.getMachineModel(), warrantyWcrSearch.getFailureType(),
				warrantyWcrSearch.getCustomerMobileNo(), warrantyWcrSearch.getRegistrationNo(),
				warrantyWcrSearch.getJobCardFromDate(), warrantyWcrSearch.getJobCardToDate(),
				warrantyWcrSearch.getPcrFromDate(), warrantyWcrSearch.getPcrToDate(), warrantyWcrSearch.getPage(),
				warrantyWcrSearch.getSize(), warrantyWcrSearch.getOrgHierarchyId(), userAuthentication.getUsername(), 'N',
				warrantyWcrSearch.getBranch(), warrantyWcrSearch.getState(), warrantyWcrSearch.getFinalStatus());
        Long count = 0L;
        if(list!=null && !list.isEmpty()){
        	count = list.get(0).getTotalCount();
        }
        apiResponse.setResult(list);
        apiResponse.setCount(count);
        apiResponse.setMessage("Search WCR Completed");
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "searchStatus")
    public ResponseEntity<?> searchStatus()  {

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.searchStatus(userAuthentication.getDealerId()));
        apiResponse.setMessage("search  wcr status");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "searchWcrType")
    public ResponseEntity<?> searchWcrType()  {

        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.searchWcrType());
        apiResponse.setMessage("search  wcr type");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "viewWarrantyWcr")
    public ResponseEntity<?> viewWarrantyWcr(@RequestParam String wcrNo)  {
        ApiResponse apiResponse=new ApiResponse();
        WcrWarrantyDto wcrWarrantyDto=new WcrWarrantyDto();
        WarrantyWcrView wcrView= warrantyWcrRepo.findByWcrNo(wcrNo);
        String pcrNo = "";
        if(wcrView.getWarrantyPcr()!=null)
        	pcrNo = wcrView.getWarrantyPcr().getPcrNo();
        else
        	pcrNo = wcrView.getWarrantyGoodwill().getWarrantyPcr().getPcrNo();
        
        wcrWarrantyDto.setWarrantyWcrView(wcrView);
        wcrWarrantyDto.setWarrantyPart(warrantyWcrRepo.getJobCardPartWarrantyInfo(pcrNo, wcrView.getWcrType()));
        wcrWarrantyDto.setLabourCharge(warrantyWcrRepo.getLabourCharge(pcrNo, wcrView.getWcrType()));
        wcrWarrantyDto.setOutSideLabourCharge(warrantyWcrRepo.getOutSideLabourCharge(pcrNo, wcrView.getWcrType()));
        wcrWarrantyDto.setApprovalHierDetails(warrantyWcrApprovalRepo.getApprovalHierDetails(wcrView.getId(), userAuthentication.getUsername()));
        wcrWarrantyDto.setIsShowInspectionBtn(warrantyWcrRepo.getInspectionBtn(wcrNo,userAuthentication.getUsername()));
        
        wcrWarrantyDto.setInvoiceType(warrantyWcrRepo.getInvoiceType(wcrNo, userAuthentication.getUsername()));
        
        apiResponse.setResult(wcrWarrantyDto);
        apiResponse.setMessage("view wcr by no");
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/approveWarrantyWcr")
    public ResponseEntity<?> approveWarrantyPcr(@RequestBody WcrApprovalDto wcrApprovalDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(warrantyWcrApprovalRepo.approveWcr(wcrApprovalDto.getId(), userAuthentication.getKubotaEmployeeId(), wcrApprovalDto.getRemarks(), userAuthentication.getUsername(), wcrApprovalDto.getApprovalStatus()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "wcrReport")
    public ResponseEntity<?> wcrReport(@RequestBody WcrReportDto wcrReportDto)  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.wcrReport(wcrReportDto.getWcrType(),wcrReportDto.getStatus(),wcrReportDto.getFromDate(),wcrReportDto.getToDate(),wcrReportDto.getModel(),wcrReportDto.getMachineNo(),wcrReportDto.getPartNo(),wcrReportDto.getDealerCode(),wcrReportDto.getState(),wcrReportDto.getCreditNoteReqNo(),wcrReportDto.getDealerName(), userAuthentication.getBranchId()));
        apiResponse.setMessage("wcr reports ");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "searchAutoCompleteWcrNo")
    public ResponseEntity<?> searchAutoCompleteWcrNo(@RequestParam String wcrNo)  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.searchWcrNo(wcrNo, userAuthentication.getUsername()));
        apiResponse.setMessage("search wcr no ");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "updateWcrAsReceived")
    public ResponseEntity<?> updateWcrAsReceived(@RequestParam Long wcrId)  {
        ApiResponse apiResponse=new ApiResponse();
        warrantyWcrRepo.markWcrReceived(userAuthentication.getLoginId(), wcrId);
        apiResponse.setMessage("WCR Updated Successfuly");
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/downloadWcrExcelReport")
    public ResponseEntity<InputStreamResource> wcrExcelReport(@RequestBody WarrantyWcrSearch wcrExcel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
		List<WarrantyWcrExcelSummaryResponse> summary = warrantyWcrRepo.wcrExcelSummary(
				userAuthentication.getManagementAccess(), userAuthentication.getKubotaEmployeeId(),
				userAuthentication.getDealerEmployeeId(),
				userAuthentication.getDealerId() == null ? wcrExcel.getDealerId() : userAuthentication.getDealerId(),
				wcrExcel.getWcrNo(), wcrExcel.getJobCardNo(), wcrExcel.getChassisNo(), wcrExcel.getWcrFromDate(),
				wcrExcel.getWcrToDate(), wcrExcel.getMachineModel(), wcrExcel.getProduct(), wcrExcel.getVariant(),
				wcrExcel.getSeries(), wcrExcel.getSubModel(), wcrExcel.getOrgHierarchyId(),
				userAuthentication.getUsername(), 'N', "summary", wcrExcel.getBranch(), wcrExcel.getState(),wcrExcel.getWcrStatus());
        
		List<WarrantyPcrDetailsExcelResponse> details = warrantyWcrRepo.wcrExcelDetails(
				userAuthentication.getManagementAccess(), userAuthentication.getKubotaEmployeeId(),
				userAuthentication.getDealerEmployeeId(), 
        		userAuthentication.getDealerId()==null?wcrExcel.getDealerId():userAuthentication.getDealerId(), 
				wcrExcel.getWcrNo(),
				wcrExcel.getJobCardNo(), wcrExcel.getChassisNo(), wcrExcel.getWcrFromDate(), wcrExcel.getWcrToDate(),
				wcrExcel.getMachineModel(), wcrExcel.getProduct(), wcrExcel.getVariant(), wcrExcel.getSeries(),
				wcrExcel.getSubModel(), wcrExcel.getOrgHierarchyId(), userAuthentication.getUsername(), 'N', "details", wcrExcel.getBranch(), wcrExcel.getState(),wcrExcel.getWcrStatus());

        response.setContentType("application/vnd.ms-excel");
        
        
        ByteArrayInputStream in = ExcelCellGenerator.wcrExcelReport(summary,details);
        HttpHeaders headers = new HttpHeaders();
        String filename = "WCR_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    /**
     * @author suraj.gaur (modified)
     * @param invoiceData
     * @param attachedFile
     * @return
     */
    @PostMapping(value = "/invoiceUpload", consumes = { "multipart/form-data" })
   	public ResponseEntity<?> invoiceUpload(@RequestPart(value = "invoiceData") Map<String,Object> invoiceData,
   			@RequestPart(value = "attachedFile", required = false) MultipartFile attachedFile) {
   		ApiResponse apiResponse = new ApiResponse();
   		
   		if (attachedFile != null) {
   			Integer wcrId = (Integer) invoiceData.get("wcrId");
   			String invoiceNo = (String) invoiceData.get("invoiceNo");
   			String invoiceDateString = (String) invoiceData.get("invoiceDate");

   		    // Check if invoiceNo and invoiceDate are not null or empty
   		    if (invoiceNo == null || invoiceNo.trim().isEmpty() || invoiceDateString == null || invoiceDateString.trim().isEmpty()) {
   		        apiResponse.setMessage("invoiceNo and invoiceDate must not be null or empty.");
   		        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
   		        return ResponseEntity.badRequest().body(apiResponse);
   		    }
   			
   			Date invoiceDate = null;
			try {
				invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(invoiceDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
   			String invoiceFileName = attachedFile.getOriginalFilename().replaceAll(" ", "_");
   			String fileType = attachedFile.getContentType();
   			String photoName = "WCRInvoice_" + System.currentTimeMillis() + "_" + invoiceFileName;
   			try{
   				//warrantyWcrRepo.uploadInvoice(photoName, wcrId, invoiceNo, invoiceDate, userAuthentication.getLoginId());
   				warrantyWcrRepo.uploadInvoice(wcrId, invoiceNo, invoiceDate, userAuthentication.getLoginId(), "Invoice Uploaded", photoName);
   				storageService.store(attachedFile, photoName);
   				apiResponse.setStatus(HttpStatus.OK.value());
   				apiResponse.setMessage("Invoice Copy uploaded Succesfully");
   			}catch(Exception ex){
   				ex.printStackTrace();
   				apiResponse.setMessage("Error while uploading Invoice Copy");
   			}
   		}else{
   			apiResponse.setMessage("Invoice Copy not found");
   		}
   	    return ResponseEntity.ok(apiResponse);
    }

    /**
     * @author suraj.gaur (Name Modified from invoice upload to kaiInvoiceUpload)
     * @return
     */
    @PostMapping(value = "/kaiInvoiceUpload", consumes = { "multipart/form-data" })
   	public ResponseEntity<?> kaiInvoiceUpload(@RequestPart(value = "invoiceData") Map<String,Object> invoiceData,
   			@RequestPart(value = "attachedFile", required = false) MultipartFile attachedFile) {
   		ApiResponse apiResponse = new ApiResponse();
   		
   		if (attachedFile != null) {
   			Integer wcrId = (Integer) invoiceData.get("wcrId");
   			String invoiceFileName = attachedFile.getOriginalFilename().replaceAll(" ", "_");
   			String fileType = attachedFile.getContentType();
   			String photoName = "WCRInvoice_" + System.currentTimeMillis() + "_" + invoiceFileName;
   			try{
   				warrantyWcrRepo.KaiUploadInvoice(photoName, wcrId, userAuthentication.getLoginId());
   				storageService.store(attachedFile, photoName);
   				apiResponse.setStatus(HttpStatus.OK.value());
   				apiResponse.setMessage("Invoice Copy uploaded Succesfully");
   			}catch(Exception ex){
   				ex.printStackTrace();
   				apiResponse.setMessage("Error while uploading Invoice Copy");
   			}
   		}else{
   			apiResponse.setMessage("Invoice Copy not found");
   		}
   	    return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur (modified)
     * @param invoiceData
     * @return
     */
    @PostMapping(value = "invoiceVerify")
//    public ResponseEntity<?> invoiceVerify(@RequestParam Long wcrId) {
    public ResponseEntity<?> invoiceVerify(@RequestBody Map<String,Object> invoiceData) {
        ApiResponse apiResponse=new ApiResponse();
        
		Integer wcrId = (Integer) invoiceData.get("wcrId");
		String invoiceNo = (String) invoiceData.get("invoiceNo");
		String invoiceDateString = (String) invoiceData.get("invoiceDate");

		if (invoiceNo == null || invoiceNo.trim().isEmpty() || invoiceDateString == null
				|| invoiceDateString.trim().isEmpty()) {
			apiResponse.setMessage("invoiceNo and invoiceDate must not be null or empty.");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(apiResponse);
		}

		Date invoiceDate = null;
		try {
			invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(invoiceDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        //warrantyWcrRepo.markAsInvoiceVerified(userAuthentication.getLoginId(), wcrId, invoiceNo, invoiceDate);
		warrantyWcrRepo.markAsInvoiceVerified(wcrId, invoiceNo, invoiceDate, userAuthentication.getLoginId(), "Invoice Verified", "");
        apiResponse.setMessage("Invoice Verified Successfuly");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value="/invoiceDownload")
    public void invoiceDownload(@RequestParam Long id, HttpServletResponse response) throws IOException{
    	String filename = warrantyWcrRepo.getInvoiceFileName(id);
    	ServletOutputStream outputStream;
    	if(filename!=null){
	    	Resource file = storageService.loadAsResource(filename);
	    	InputStream is = file.getInputStream();
	    	response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + filename);
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			outputStream = response.getOutputStream();
			IOUtils.copy(is, outputStream);
			outputStream.flush();
			is.close();
	    }
    }
    
    @GetMapping(value="/getFinalStatus")
    public ResponseEntity<?> getFinalStatus() {
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.getFinalStatus());
        apiResponse.setMessage("Search Final Status");
        return ResponseEntity.ok(apiResponse);

    }
    
    /**
     * @author suraj.gaur
     * @param wcrId
     * @return
     */
    @GetMapping(value="/getInvoiceDetail")
    public ResponseEntity<?> getInvoiceDetail(@RequestParam Long wcrId) {
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyWcrRepo.getInvoiceDetail(wcrId));
        apiResponse.setMessage("Get Invoice No and Invoice Date successfull!");
        return ResponseEntity.ok(apiResponse);

    }
}
