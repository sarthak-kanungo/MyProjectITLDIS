package com.i4o.dms.itldis.salesandpresales.marketIntelligence.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.salesandpresales.marketIntelligence.domain.MarketIntelligence;
import com.i4o.dms.itldis.salesandpresales.marketIntelligence.domain.SearchRequest;
import com.i4o.dms.itldis.salesandpresales.marketIntelligence.dto.MarketIntelligenceSearchResponse;
import com.i4o.dms.itldis.salesandpresales.marketIntelligence.repo.MarketIntelligenceRepo;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.DcSearchRequestDto;
import com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto.SearchDeliveryChallanResponse;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/marketIntelligence")
public class MarketIntelligenceController {
	
	@Autowired
	UserAuthentication userAuthentication;
	@Autowired
	MarketIntelligenceRepo repo;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody MarketIntelligence marketIntelligence) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
		marketIntelligence.setCreatedBy(userAuthentication.getLoginId());
		marketIntelligence.setDealerEmpId(userAuthentication.getDealerEmployeeId());
		repo.save(marketIntelligence);
        apiResponse.setMessage("Details Saved Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) throws ParseException {
        ApiResponse<MarketIntelligence> apiResponse = new ApiResponse<MarketIntelligence>();
        Optional<MarketIntelligence> data = repo.findById(id);
        if(data!=null){
        	apiResponse.setResult(data.get());
        	apiResponse.setMessage("Details Fetched Successfully.");
        }else{
        	apiResponse.setMessage("Record not found.");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "")
    public ResponseEntity<?> search(@RequestBody SearchRequest searchRequest) throws ParseException {
    	Long dealerId;
        if(userAuthentication.getDealerId()==null) {
        	dealerId = searchRequest.getDealerId();
        }else {
        	dealerId = userAuthentication.getDealerId();
        }
        ApiResponse<List<MarketIntelligenceSearchResponse>> apiResponse = new ApiResponse<List<MarketIntelligenceSearchResponse>>();
        List<MarketIntelligenceSearchResponse> result = repo.getSearchResult(userAuthentication.getUsername(), searchRequest.getFeedbackCategory(),
        		searchRequest.getFromDate(), searchRequest.getToDate(), searchRequest.getOrgHierId(), dealerId, searchRequest.getPage(), searchRequest.getSize());
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setResult(result);
        apiResponse.setCount(count);
    	apiResponse.setMessage("Details Fetched Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/report")
   	public ResponseEntity<InputStreamResource> downloadBBExcelReport(@RequestBody SearchRequest searchRequest,HttpServletResponse response) throws IOException{
       	 Integer size = Integer.MAX_VALUE-1;
       	 Long dealerId;
         if(userAuthentication.getDealerId()==null) {
         	dealerId = searchRequest.getDealerId();
         }else {
         	dealerId = userAuthentication.getDealerId();
         }
         List<MarketIntelligenceSearchResponse> result = repo.getSearchResult(userAuthentication.getUsername(), searchRequest.getFeedbackCategory(),
         		searchRequest.getFromDate(), searchRequest.getToDate(), searchRequest.getOrgHierId(), dealerId, searchRequest.getPage(), size);
         
       	 ByteArrayInputStream byteInputStream = ExcelCellGenerator.marketIntelligenceReport(result);
            response.setContentType("application/vnd.ms-excel");
            HttpHeaders headers = new HttpHeaders();
            String filename = "Market_Intelligence"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
            headers.add("Content-Disposition", "attachment ; filename = "+filename);
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
            return ResponseEntity.ok().headers(headers).body(new InputStreamResource(byteInputStream));
       }

}
