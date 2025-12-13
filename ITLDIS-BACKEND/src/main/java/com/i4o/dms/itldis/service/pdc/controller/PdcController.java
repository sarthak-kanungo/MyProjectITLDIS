package com.i4o.dms.itldis.service.pdc.controller;

import java.util.Date;
import java.util.List;

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

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.pdc.domain.ServicePdc;
import com.i4o.dms.itldis.service.pdc.dto.PdcSearchDto;
import com.i4o.dms.itldis.service.pdc.dto.PdcSearchResponse;
import com.i4o.dms.itldis.service.pdc.dto.PdcViewDto;
import com.i4o.dms.itldis.service.pdc.repository.PdcRepository;
import com.i4o.dms.itldis.service.pdc.repository.ServiceCheckpointPdcRepo;
import com.i4o.dms.itldis.utils.ApiResponse;


@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/pdc")
public class PdcController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    @Autowired
    private PdcRepository pdcRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private ServiceCheckpointPdcRepo serviceCheckpointPdcRepo;

    @PostMapping(value = "/savePdc")
    public ResponseEntity<?> savePdc(@Valid @RequestBody ServicePdc servicePdc)
    {
        ApiResponse apiResponse = new ApiResponse();
        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
        servicePdc.setBranchId(userAuthentication.getBranchId());
        servicePdc.setCreatedBy(userAuthentication.getLoginId());
        servicePdc.setCreatedDate(new Date());
        servicePdc.setPdcDate(new Date());
        if(servicePdc.getId()!=null){
        	servicePdc.setModifiedBy(userAuthentication.getLoginId());
            servicePdc.setModifiedDate(new Date());
        }
      Boolean isPdcCreated=pdcRepository.isPdcCreated(servicePdc.getMachineInventory().getId(),userAuthentication.getDealerId());
        logger.info(""+isPdcCreated+"''''''''''''''''''''''''''''''''");
       if(isPdcCreated){
           apiResponse.setMessage("pdc already created ");
       }else {
           apiResponse.setStatus(HttpStatus.OK.value());
           ServicePdc servicePdc1 = pdcRepository.save(servicePdc);

           servicePdc.getServicePdcChassisCheckpointInfoSet()
                   .forEach(s -> {
                               s.getChassisCheckpointId().setServicePdc(servicePdc1);
                               serviceCheckpointPdcRepo.save(s);
                           }
                   );
           apiResponse.setMessage("pdc save successfully ");
       }
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "/getChassisNumberAutoComplete")
    public ResponseEntity<?> autoCompleteChassisNumber(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get chassis number auto complete");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pdcRepository.getChassisNumberAutoComplete(chassisNo,userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getChassisDetailsByChassisNo")
    public ResponseEntity<?> getChassisDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(pdcRepository.getChassisDetailsByChassisNo(chassisNo, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

   /* @GetMapping(value = "/getAggregateAndCheckPointByModel")
    public ResponseEntity<?> getAggregateAndCheckPointByModel(@RequestParam("model") String model) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get  aggregate and checkpoints by model");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pdcRepository.getAggregateAndCheckPointByModel(model));
        return ResponseEntity.ok(apiResponse);
    }*/

    @PostMapping(value = "/searchPdc")
    public ResponseEntity<?> searchPdc(@RequestBody PdcSearchDto pdcSearchDto)
    {
    	List<PdcSearchResponse>  result = pdcRepository.pdcSearch(userAuthentication.getManagementAccess(),userAuthentication.getDealerEmployeeId(),userAuthentication.getDealerId(),userAuthentication.getKubotaEmployeeId(),pdcSearchDto.getChassisNo(),pdcSearchDto.getPdcNo(),pdcSearchDto.getModel(),pdcSearchDto.getPdcFromDate(),pdcSearchDto.getPdcToDate(),pdcSearchDto.getPage(),pdcSearchDto.getSize(),
    			userAuthentication.getUsername(),
    			'N',
    			pdcSearchDto.getOrgId()
    			);
    	Long recordCount = 0l;
    	if(result!=null && result.size()>0){
    		recordCount = result.get(0).getRecordCount();
    	}
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search pdc ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        apiResponse.setCount(recordCount);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getPdcById/{id}")
    public ResponseEntity<?> getPdcById(@PathVariable Long id){
        ApiResponse apiResponse = new ApiResponse();
        PdcViewDto pdcViewDto=new PdcViewDto();
        pdcViewDto.setPdcViewHeaderResponse(pdcRepository.getViewHeader(id));
        pdcViewDto.setPdcCheckpointList(pdcRepository.getAggregateCheckpointListByPdcId(id));
        apiResponse.setMessage("get aggregate and checkpoints");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pdcViewDto);
        return  ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/pdcCreateAutoCompleteChassisNo")
    public ResponseEntity<?> pdcCreateAutoCompleteChassisNo(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(pdcRepository.pdcCreateAutoCompleteChassisNo(chassisNo,userAuthentication.getUsername()));
        apiResponse.setMessage("auto complete pdc number");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


}

