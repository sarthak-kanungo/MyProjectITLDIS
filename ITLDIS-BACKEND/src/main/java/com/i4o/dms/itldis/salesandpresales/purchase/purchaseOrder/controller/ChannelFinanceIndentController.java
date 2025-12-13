package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIndent;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.ChannelFinanceIntentDetail;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.ResponseChannelFinanceIndentDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.ResponseInvoiceDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.SearchChannelFinanceIndentDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto.SearchInvoicesDto;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.repository.ChannelFinanceIndentRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.service.ChannelFinanceIndentService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/channelFinanceIndent")
public class ChannelFinanceIndentController {

    @Autowired
    private ChannelFinanceIndentRepo channelFinanceIndentRepo;

    @Autowired
    private ChannelFinanceIndentService channelFinanceIndentService;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Transactional
    @PostMapping(value = "/saveChannelFinanceIndent")
    public ResponseEntity<?> saveChannelFinanceIndent(@RequestBody ChannelFinanceIndent channelFinanceIndent) throws ParseException {
        channelFinanceIndent.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        channelFinanceIndent.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        channelFinanceIndent.setCreatedBy(userAuthentication.getLoginId());
        channelFinanceIndentService.saveChannelFinanceIndent(channelFinanceIndent);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("channel finance indent Successfully Saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    

    @SuppressWarnings("unchecked")
	@PostMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestBody SearchChannelFinanceIndentDto searchChannelFinanceIndentDto) {

        ApiResponse apiResponse = new ApiResponse();
        List<ResponseChannelFinanceIndentDto> result = channelFinanceIndentRepo.searchBy(
                searchChannelFinanceIndentDto.getIndentNumber(),
                //searchChannelFinanceIndentDto.getDealerCode(),
                searchChannelFinanceIndentDto.getDealerCategory(),
                searchChannelFinanceIndentDto.getBank(),
                searchChannelFinanceIndentDto.getFromDate(),
                searchChannelFinanceIndentDto.getToDate(),
                (userAuthentication.getDealerId()==null?searchChannelFinanceIndentDto.getDealerId():userAuthentication.getDealerId()),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchChannelFinanceIndentDto.getPage(),
                searchChannelFinanceIndentDto.getSize(),
                userAuthentication.getUsername(),
                'N',searchChannelFinanceIndentDto.getOrgHierId(),searchChannelFinanceIndentDto.getStatus()
                );
        
        apiResponse.setMessage("channel finance indent get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

/*

    @PostMapping(value = "/getInvoices")
    public ResponseEntity<?> getInvoices(@RequestParam(required = false) String dealerCode,
                                         @RequestParam(required = false) String flexiAccount,
                                         @RequestParam(required = false) double indentAmount) throws ParseException {
        List<ResponseInvoiceDto> responseInvoiceDtos=channelFinanceIndentService.getInvoice(dealerCode,flexiAccount,indentAmount);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("channel finance indent Successfully Saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(responseInvoiceDtos);
        return ResponseEntity.ok(apiResponse);
    }
*/

    @PostMapping(value = "/updateIndentStatus")
    public ResponseEntity<?> updateIndentStatus(@RequestBody ChannelFinanceIndent channelFinanceIndent) throws ParseException {
        String msg = channelFinanceIndentRepo.updateIndentStatus(channelFinanceIndent.getIndentNumber(),channelFinanceIndent.getIndentStatus(),channelFinanceIndent.getApproverRemarks(), userAuthentication.getUsername());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/getInvoices")
    public ResponseEntity<?> getInvoices(@RequestBody SearchInvoicesDto invoiceDto) throws ParseException {
        List<ResponseInvoiceDto> responseInvoiceDtos = channelFinanceIndentService.getInvoice(invoiceDto.getDealerCode(),invoiceDto.getIndentAmount(),invoiceDto.getBankName());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("invoice get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(responseInvoiceDtos);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getChannelFinanceIndentById/{channelFinanceId}")
    public ResponseEntity<?> getChannelFinanceIndentById(@PathVariable("channelFinanceId") Long channelFinanceId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("channel finance indent get Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        ChannelFinanceIndent indent = channelFinanceIndentRepo.findByChannelFinanceId(channelFinanceId);
        indent.setDealerCode(indent.getDealerMaster().getDealerCode());
        indent.setApprovalButtonList(channelFinanceIndentRepo.getApprovalBtnList(userAuthentication.getUsername(), 
        		indent.getIndentNumber()));
        apiResponse.setResult(indent);
        return ResponseEntity.ok(apiResponse);
    }

    @SuppressWarnings("unchecked")
	@GetMapping(value = "/searchIndentNumber")
    public ResponseEntity<?> searchIndentNumber(@RequestParam() String indentNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(" Indent number get Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(channelFinanceIndentRepo.searchIndentNumber(indentNumber,userAuthentication.getDealerId(),userAuthentication.getManagementAccess(),
        		userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerCategory")
    public ResponseEntity<?> getDealerCategory(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(" Dealer category get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(channelFinanceIndentRepo.getCategory());
        return ResponseEntity.ok(apiResponse);
    }




  /*  @GetMapping(value = "/getInvoice")
    public ResponseEntity<?> getInvoice(@RequestParam(required = false) String dealerCode,
                                        @RequestParam(required = false) String flexiLoanAccountNumber,
                                        @RequestParam(required = false) String indentAmount) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("details get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getDealerBankDetailsByBankAndDealer(dealerCode,bankName));
        return ResponseEntity.ok(apiResponse);
    }*/
}
