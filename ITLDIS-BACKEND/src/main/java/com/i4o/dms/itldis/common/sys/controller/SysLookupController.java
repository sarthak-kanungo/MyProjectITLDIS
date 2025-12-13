/**
 * 
 */
package com.i4o.dms.itldis.common.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.common.model.SystemLookUpEntity;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * @author dnsh87
 *
 */
@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/common/syslookup")
public class SysLookupController {
	
	@Autowired
	private SysLookupRepo repo;
	
	@GetMapping(value="/lookupByCode")
	public ResponseEntity<?> getLookupByCode(@RequestParam(value="code")String code){
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		//List<SystemLookUpEntity> lookupDetails = repo.findByLookuptypecode(code);
		List<SystemLookUpEntity> lookupDetails = repo.lookupByTypeCode(code);	//Suraj-23/12/2022
		apiResponse.setResult(lookupDetails);
		apiResponse.setMessage("Lookup Details");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	/**
	 * @author suraj.gaur
	 * @param filename
	 * @param response
	 * @param request
	 * @return ResponseEntity<InputStreamResource>
	 * @throws FileNotFoundException
	 */
	@GetMapping(value="/downloadTemplate")
    public ResponseEntity<InputStreamResource> downloadTemplate(
    		@RequestParam("filename")String filename,
    		HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException
	{
    	File fileOnServer = null;
		FileInputStream inputStream = null;
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/template/" + filename);
    	
		fileOnServer = new File(filePath);
		inputStream = new FileInputStream(fileOnServer);
		
    	response.setContentType("application/vnd.ms-excel");
    	
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment ; filename ="+fileOnServer.getName());
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }
}
