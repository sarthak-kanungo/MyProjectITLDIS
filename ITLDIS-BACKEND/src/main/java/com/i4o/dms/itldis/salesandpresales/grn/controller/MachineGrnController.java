package com.i4o.dms.itldis.salesandpresales.grn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.accpac.domain.AccPacInvoice;
import com.i4o.dms.itldis.accpac.repository.AccPacInvoiceRepository;
import com.i4o.dms.itldis.masters.products.repository.MachineMasterRepository;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.grn.domain.GrnMachineDetails;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineGrn;
import com.i4o.dms.itldis.salesandpresales.grn.dto.GrnSearchDto;
import com.i4o.dms.itldis.salesandpresales.grn.dto.GrnSearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.grn.repository.MachineGrnRepository;
import com.i4o.dms.itldis.salesandpresales.grn.service.GrnService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/salesandpresales/grn")
public class MachineGrnController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MachineGrnRepository machineGrnRepository;

    @Autowired
    private AccPacInvoiceRepository accPacInvoiceRepository;

    @Autowired
    private MachineMasterRepository machineMasterRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private GrnService grnService;

    @ApiOperation(value = "Get list of active grn type ")
    @GetMapping("/getGrnType")
    public ResponseEntity<?> getGrnType(@RequestParam Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get grn type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.getGrnType());
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "Get list of active grn status according to user ")
    @GetMapping("/getGrnStatus")
    public ResponseEntity<?> getGrnStatus(@RequestParam Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get grn status");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.getGrnStatus());
        return ResponseEntity.ok(apiResponse);
    }


    @ApiOperation(value = "Get list of kubota transporter name")
    @GetMapping("/getTransporterName")
    public ResponseEntity<?> getTransporterName() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get transporter name");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.getTransporterName());
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "create grn against invoice")
    @PostMapping("createMachineGrn")
    public ResponseEntity<?> createMachineGrn(@Valid @RequestBody MachineGrn machineGrn){
        ApiResponse apiResponse = new ApiResponse();
        machineGrn.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        machineGrn.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        machineGrn.setGrnDate(new Date());
        machineGrn.setCreatedBy(userAuthentication.getLoginId());
        machineGrn.setGrnBy(userAuthentication.getUsername());
        if(machineGrn.getGrnType().equals("Machine Transfer Request")){
        	machineGrn.setCoDealerInvoiceId(machineGrn.getAccPacInvoice().getId());
        	machineGrn.setAccPacInvoice(null);
        }
        List<GrnMachineDetails> list = machineGrn.getGrnMachineDetails().stream().filter(grnMachineDetail -> grnMachineDetail.getItemNo()!=null).collect(Collectors.toList());
        if(list!=null && list.size()>0){
        	list.forEach(grnMachineDetail -> grnMachineDetail.setMachineMaster(machineMasterRepository
	                        .findByItemNo(grnMachineDetail.getItemNo())));
        	machineGrn.setGrnMachineDetails(list);
            machineGrnRepository.save(machineGrn);
            apiResponse.setMessage("save grn");
            apiResponse.setStatus(HttpStatus.OK.value());
        } else {
        	  apiResponse.setMessage("No Machine/Implement details found");
              apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchAccPacInvoiceNumber")
    public ResponseEntity<?> searchAccPacInvoiceNumber(@RequestParam String invoiceNumber, @RequestParam String grnType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchAccPacInvoiceNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.searchAccPacInvoiceNumber(invoiceNumber, grnType, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchAccPacInvoiceByInvoiceNumber")
    public ResponseEntity<?> searchAccPacInvoiceByInvoiceNumber(@RequestParam String invoiceNumber, @RequestParam String grnType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchAccPacInvoiceNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(grnService.getAccPacInvoice(invoiceNumber, grnType));
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "search invoice number from grn")
    @GetMapping("/searchGrnInvoiceNumber")
    public ResponseEntity<?> searchGrnInvoiceNumber(@RequestParam String invoiceNumber, @RequestParam Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchGrnInvoiceNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.searchGrnInvoiceNumber(invoiceNumber, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "search grn number from grn on user basis")
    @GetMapping("/searchByDmsGrnNumber")
    public ResponseEntity<?> searchByDmsGrnNumber(@RequestParam String grnNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchGrnNumber");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineGrnRepository.searchGrnNumber(grnNumber, userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "search grn according to condition")
    @PostMapping("/searchGrn")
    public ResponseEntity<?> searchGrn(@RequestBody GrnSearchDto grnSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        List<GrnSearchResponseDto> result = machineGrnRepository.searchGrn(grnSearchDto.getDmsGrnNumber(),
                grnSearchDto.getGrnType(), grnSearchDto.getGrnStatus(), grnSearchDto.getInvoiceNumber(),
                grnSearchDto.getFromDate(), grnSearchDto.getToDate(),
                userAuthentication.getDealerId(),userAuthentication.getDealerEmployeeId(),
                grnSearchDto.getPage(), grnSearchDto.getSize(), grnSearchDto.getItemNo(), grnSearchDto.getSupplierType(), userAuthentication.getUsername());
        
        apiResponse.setMessage("searchGrn");
        apiResponse.setStatus(HttpStatus.OK.value());
        Long count =0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }		
        apiResponse.setResult(result);
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getGrnByDmsGrnNumber/{grnId}")
    public ResponseEntity<?> getGrnByDmsGrnNumber(@PathVariable Long grnId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchGrn");
        apiResponse.setStatus(HttpStatus.OK.value());
        MachineGrn grn = machineGrnRepository.getOne(grnId);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(grn!=null && grn.getGrnMachineDetails()!=null){
        	if(grn.getCoDealerInvoiceId()!=null){
        		Map<String,Object> inv = machineGrnRepository.getInvoiceDetails(grn.getCoDealerInvoiceId(), userAuthentication.getBranchId());
        		AccPacInvoice accPacInvoice = new AccPacInvoice();
        		accPacInvoice.setLrNo(grn.getLrNo());
        		accPacInvoice.setInvoiceNumber(inv.get("invoice_number").toString());
        		accPacInvoice.setBillTo(inv.get("bill_to").toString());
        		accPacInvoice.setShipTo(inv.get("ship_to").toString());
        		try {
					accPacInvoice.setInvoiceDate(df.parse(inv.get("invoice_Date").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
        		grn.setAccPacInvoice(accPacInvoice);
        	}
        	for(GrnMachineDetails d : grn.getGrnMachineDetails()){
        		if(d.getMachineMaster()!=null)
        			d.setCategory(d.getMachineMaster().getProductGroup());
        	}
        }
        apiResponse.setResult(grn);
        return ResponseEntity.ok(apiResponse);
    }

    
}
