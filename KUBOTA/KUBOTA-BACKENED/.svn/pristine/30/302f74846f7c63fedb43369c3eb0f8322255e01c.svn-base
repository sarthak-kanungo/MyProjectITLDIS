package com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.service;

import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimApproval;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimAttachment;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.domain.SpPartDiscrepancyClaimHdr;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.ClaimApprovalRequestDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimViewResponseDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.PrintPdfRequestDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.exception.ClaimApprovalRejectionException;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.exception.ClaimIdNotFoundException;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.exception.NotAkaiUserException;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.exception.StockNotUpdateException;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimSearchRequestDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.dto.DiscrepancyClaimViewDto;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.repository.SpDiscrepancyKaiAdditionalRemarksRepo;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.repository.SpPartDiscrepancyClaimApprovalRepo;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.repository.SpPartDiscrepancyClaimAttachmentRepo;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.repository.SpPartDiscrepancyClaimHdrRepo;
import com.i4o.dms.kubota.spares.purchase.discrepancyClaimMmrRequest.validation.CommonValidator;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@Service
public class SpPartDiscrepancyClaimServiceImpl implements SpPartDiscrepancyClaimService {

	@Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private SpPartDiscrepancyClaimHdrRepo claimHdrRepo;
    
    @Autowired
    private StorageService storageService;
    
    @Autowired
    private SpPartDiscrepancyClaimAttachmentRepo attachmentRepo;
    
    @Autowired
    private SpPartDiscrepancyClaimApprovalRepo approvalRepo;
    
    @Autowired
    private DealerEmployeeMasterRepo employeeMasterRepo;
    
    @Autowired
    private SpDiscrepancyKaiAdditionalRemarksRepo kaiAdditionalRemarksRepo;
    
    @Autowired
    private JasperPrintService jasperPrintService;
    
//	@Autowired
//	private StringValidator stringValidator;
    
	@Autowired
	private CommonValidator commonValidator;
    
    
	@Override
	public ApiResponse<?> autoGetDiscrepancyClaimNo(String claimNoString) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(claimHdrRepo.autoGetDiscrepancyClaimNo(claimNoString));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> autoGetDiscrepancyGrnNo(String descrClaimType, String grnStr) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(claimHdrRepo.autoGetDiscrepancyGrnNo(descrClaimType, grnStr, userAuthentication.getBranchId()));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getDiscrepancyItems(Long grnId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();		
		
		apiResponse.setResult(claimHdrRepo.getDiscrepancyItems(grnId));
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getHeaderGrnDetails(Long grnId) {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(claimHdrRepo.getHeaderGrnDetails(grnId));
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveDiscrepancyClaim(SpPartDiscrepancyClaimHdr discrepancyClaim, List<MultipartFile> multipartFileList) 
	{
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		discrepancyClaim.setBranchId(userAuthentication.getBranchId());
		discrepancyClaim.setClaimDate(new Date());
		
		if (discrepancyClaim.getId() == null) {
			Optional<DealerEmployeeMaster> dealerEmployeeMaster = employeeMasterRepo.findById(userAuthentication.getDealerEmployeeId());
			
			if(dealerEmployeeMaster.isPresent()) {
				discrepancyClaim.setDealerEmployeeMaster(dealerEmployeeMaster.get());
			}
			discrepancyClaim.setCreatedBy(userAuthentication.getLoginId());
			discrepancyClaim.setCreatedDate(new Date());
			discrepancyClaim.setClaimDate(new Date());
		}
		else {
			discrepancyClaim.setLastModifiedBy(userAuthentication.getLoginId());
			discrepancyClaim.setLastModifiedDate(new Date());
		}
		
		if(discrepancyClaim.isDraftFlag()) {
			discrepancyClaim.setStatus("Draft");
		}else {
			discrepancyClaim.setStatus("Submitted");
		}
		
		claimHdrRepo.save(discrepancyClaim);
		
		if(!discrepancyClaim.isDraftFlag()) {
			if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
	            multipartFileList.forEach(m -> {
	            	SpPartDiscrepancyClaimAttachment claimAttachment = new SpPartDiscrepancyClaimAttachment();
	            	String fileName = m.getOriginalFilename();
	            	String photoName = "sp_disc_claim_" + System.currentTimeMillis() + "_" + fileName;
	                
	                storageService.store(m, photoName);	//Storing the file to the location
	                
	                claimAttachment.setFileName(photoName);
	                claimAttachment.setFileType(m.getContentType());
	                claimAttachment.setSpDiscrHdrId(discrepancyClaim.getId());
	                
	                attachmentRepo.save(claimAttachment);
	            });
			}
			
			List<SpPartDiscrepancyClaimApproval> claimApprovals = new ArrayList<>();
			claimHdrRepo.discrepancyClaimHierarchyLevel().forEach(designationHierarchy -> {
				SpPartDiscrepancyClaimApproval approval = new SpPartDiscrepancyClaimApproval();
				approval.setSpDiscrHdrId(discrepancyClaim.getId());
	            approval.setApproverLevelSeq((Integer)designationHierarchy.get("approverLevelSeq"));
	            approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designationLevelId"));
	            approval.setGrpSeqNo((Integer)designationHierarchy.get("grpSeqNo"));
	            approval.setIsFinalApprovalStatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
	            approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
	            approval.setRejectedFlag('N');
	            
	            claimApprovals.add(approval);
			});
			approvalRepo.saveAll(claimApprovals);
		}
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetGrnItemsNo(Long grnId, String itemStr) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(claimHdrRepo.autoGetGrnItemsNo(grnId, itemStr));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getGrnItemDiscrDetails(Long grnId, String itemNo) {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(claimHdrRepo.getGrnItemDiscrDetails(grnId, itemNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> searchDiscrepancyClaim(DiscrepancyClaimSearchRequestDto requestDto) {
		ApiResponse<List<DiscrepancyClaimSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<DiscrepancyClaimSearchResponseDto> response = claimHdrRepo.searchDiscrepancyClaim(
				requestDto.getClaimNo(),
				requestDto.getClaimType(),
				requestDto.getClaimStatus(),
				requestDto.getGrnNo(),
				requestDto.getInvoiceNo(),
				userAuthentication.getDealerEmployeeId(),
				requestDto.getFromDate(),
				requestDto.getToDate(),
				requestDto.getPage(),
				requestDto.getSize(),
				userAuthentication.getUsername(),
				'N');
		
		Long count = 0L;
		if (response != null && response.size() > 0) {
			count = response.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(response);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getDiscrepancyClaimView(String discrClaimNo) {
		ApiResponse<DiscrepancyClaimViewResponseDto> apiResponse = new ApiResponse<>();
		
		DiscrepancyClaimViewDto claimViewDto = claimHdrRepo.findByClaimReqNo(discrClaimNo);
		List<Map<String, Object>> claimAttachment = claimHdrRepo.getDiscrepancyAttachNKaiRemark(claimViewDto.getId(), "attachment");
		List<Map<String, Object>> kaiRemarks = claimHdrRepo.getDiscrepancyAttachNKaiRemark(claimViewDto.getId(), "kaiRemarks");
		
		DiscrepancyClaimViewResponseDto claimFullViewDto = new DiscrepancyClaimViewResponseDto();
		claimFullViewDto.setClaimViewDto(claimViewDto);
		claimFullViewDto.setClaimAttachment(claimAttachment);
		if(!kaiRemarks.isEmpty()) {
			claimFullViewDto.setKaiRemarks(kaiRemarks.get(0));
		}
		
		apiResponse.setResult(claimFullViewDto);
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> claimApproveReject(ClaimApprovalRequestDto approvalRequestDto) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		if(commonValidator.validKaiUser(userAuthentication.getKubotaEmployeeId())) {
			if(approvalRequestDto.getClaimId() != null) {
				Optional<SpPartDiscrepancyClaimHdr> discrepancyClaimHdr = claimHdrRepo.findById(approvalRequestDto.getClaimId());
				
				if(discrepancyClaimHdr.isPresent()) {
					
					//Updating KAI approved quantity and remarks
					approvalRequestDto.getDiscrepancyClaimDtl().forEach(discClaimDtl -> {
						
						discrepancyClaimHdr.get().getDiscrepancyClaimDtls().stream()
							.filter(obj -> obj.getId() == discClaimDtl.getId())
							.findFirst().get().setKaiAcceptedQty(discClaimDtl.getKaiAcceptedQty());
						
						discrepancyClaimHdr.get().getDiscrepancyClaimDtls().stream()
							.filter(obj -> obj.getId() == discClaimDtl.getId())
							.findFirst().get().setKaiRemarkReason(discClaimDtl.getKaiRemarkReason());
						
					});

					//Saving Changes in discrepancy claim table
					claimHdrRepo.save(discrepancyClaimHdr.get());
					
					approvalRequestDto.getKaiAdditionalRemarks().setDiscrepancyClaimHdr(discrepancyClaimHdr.get());
					approvalRequestDto.getKaiAdditionalRemarks().setKaiAcceptanceDate(new Date());
					approvalRequestDto.getKaiAdditionalRemarks().setKaiSettlementDate(new Date());
					
					//Saving KAI Remarks
					kaiAdditionalRemarksRepo.save(approvalRequestDto.getKaiAdditionalRemarks());
				}
				
				//Approving/Rejecting Claims
				String msg = claimHdrRepo.claimApproveOrReject(approvalRequestDto.getClaimId(), userAuthentication.getKubotaEmployeeId(), 
						approvalRequestDto.getRemark(),	userAuthentication.getUsername(), approvalRequestDto.getApprovalStatus());
				
				if(!msg.startsWith("Approve")) {
					msg = "Approval failed due to designation level does not matched.";
					apiResponse.setResult(msg);
					
					throw new ClaimApprovalRejectionException(msg);
				}
				
				//Update Stock after successful approval
				Map<String, Object> stockUpdateRes =  claimHdrRepo.updateStockAfterDiscrepacnyClaim(
						approvalRequestDto.getClaimId(), userAuthentication.getLoginId());
				
				msg = (String)stockUpdateRes.get("message");
				if(!msg.equalsIgnoreCase("SUCCESS")) {
					msg = (String)stockUpdateRes.get("messageError");
					apiResponse.setResult(msg);
					
					throw new StockNotUpdateException(msg);
				}
				
				apiResponse.setMessage("Discrepancy/MMR Claim Approved");
				apiResponse.setResult(msg);
			} else {
				apiResponse.setMessage("Claim Id should not be Empty.");
				throw new ClaimIdNotFoundException("Claim Id should not be Empty.");
			}
		} else {
			apiResponse.setMessage("Not a valid KAI user, kindly login with a valid KAI user.");
			throw new NotAkaiUserException("Not a valid KAI user, kindly login with a valid KAI user.");
		}
		
		return apiResponse;
	}

	@Override
	public void printDMCReport(PrintPdfRequestDto printPdfDto, String filePath, OutputStream outputStream) throws Exception {
		String jasperfile = filePath + "SpareDiscrepancyMmrClaimReport.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("claimId", printPdfDto.getClaimId());
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printPdfDto.getPrintStatus(), outputStream);
	}

}
