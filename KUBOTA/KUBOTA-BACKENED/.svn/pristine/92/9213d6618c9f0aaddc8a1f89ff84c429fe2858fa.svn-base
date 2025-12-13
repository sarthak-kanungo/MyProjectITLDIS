package com.i4o.dms.kubota.masters.kfmmasters.cflu.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.DealerBankDetails;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository.DealerBankDetailsRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/kfmMasters/channelFinanceLimitUpload")
public class ChannelFinanceLimitUploadController {
	
    @Autowired
    private DealerBankDetailsRepo channelFinanceLimitUploadRepo;
    
    @Autowired
    private UserAuthentication userAuthentication;


	@PostMapping(value = "/uploadData")
	public ResponseEntity<?> excelDataUpload(@RequestBody List<DealerBankDetails> cfLimitUpload) {
		List<String> notFoundCode = new ArrayList<>();
		HashSet<String> repeatedCode = new HashSet<>();
		Map<String, Object> delaerCodeStatus = new HashMap<>();

		Set<DealerBankDetails> set = new TreeSet<DealerBankDetails>(new Comparator<DealerBankDetails>() {

			@Override
			public int compare(DealerBankDetails d1, DealerBankDetails d2) {
				if (d1.getDealerBankDetailsId().getDealerCode()
						.equalsIgnoreCase(d2.getDealerBankDetailsId().getDealerCode())) {
					repeatedCode.add(d1.getDealerBankDetailsId().getDealerCode());
					return 0;
				}
				return 1;
			}
		});
		set.addAll(cfLimitUpload);

		final List<DealerBankDetails> newList = new ArrayList<>(set);

		for (DealerBankDetails d : newList) {
			DealerBankDetails d1 = channelFinanceLimitUploadRepo.findByDealerBankDetailsIdDealerCode(
					d.getDealerBankDetailsId().getDealerCode(), d.getDealerBankDetailsId().getBankName(),
					d.getFlexiLoanAccountNumber());
			if (d1 != null) {
				System.out.println(d.getDealerBankDetailsId().getDealerCode() + " exists-------->");
				d1.setCfCreditLimit(d.getCfCreditLimit());
				d1.setUtilisedLimit(d.getUtilisedLimit());
				d1.setAvailableAmount(d.getAvailableAmount());
				channelFinanceLimitUploadRepo.updateChannelFinanceLimitUpload(d.getCfCreditLimit(),
						d.getUtilisedLimit(), d.getAvailableAmount(), d.getDealerBankDetailsId().getDealerCode(),
						userAuthentication.getLoginId(), new Date());
			} else {
				System.out.println();
				System.out.println(d.getDealerBankDetailsId().getDealerCode() + " does not exists-------->");
				notFoundCode.add(d.getDealerBankDetailsId().getDealerCode());
			}
		}

		delaerCodeStatus.put("notFoundCode", notFoundCode);
		delaerCodeStatus.put("repeatedCode", repeatedCode);

		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Data Processed Successfully");
		apiResponse.setResult(delaerCodeStatus);
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}

}
