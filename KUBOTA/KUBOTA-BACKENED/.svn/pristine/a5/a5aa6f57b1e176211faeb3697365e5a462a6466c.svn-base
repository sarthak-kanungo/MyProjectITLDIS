package com.i4o.dms.kubota.security.controller;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.security.dto.LoggedInUserDetailsResDto;
import com.i4o.dms.kubota.security.service.UserTokenService;
import com.i4o.dms.kubota.utils.ApiResponse;

/**
 * @author suraj.gaur
 * @implNote created as per client request for not reusing token after logout successfully.
 */
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("api/security/userToken")
public class UserTokenController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private UserTokenService userTokenService;
	
	/**
	 * @author suraj.gaur
	 * @param ServletRequest
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/logoutUser")
	public ResponseEntity<?> logoutUser(ServletRequest request)
	{
		try {
			ApiResponse<String> apiResponse = new ApiResponse<>(); 
			String result = userTokenService.logoutUser(request);
			
			apiResponse.setResult(result);
			apiResponse.setMessage("Logout User Successful!");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/logoutUser Exception: " + e.getMessage());
			logger.error("An error occurred in /logoutUser:", e);
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	@GetMapping("/isUserLoggedIn")
	public ResponseEntity<?> isUserLoggedIn(ServletRequest request)
	{
		try {
			ApiResponse<Boolean> apiResponse = new ApiResponse<>(); 
			Boolean result = userTokenService.isUserLoggedIn(request);
			
			apiResponse.setResult(result);
			apiResponse.setMessage(result ? "User Logged-In!" : "User Not Logged-In!");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/logoutUser Exception: " + e.getMessage());
			logger.error("An error occurred in /logoutUser:", e);
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @param ServletRequest
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getLoggedInUser")
	public ResponseEntity<?> getLoggedInUser()
	{
		try {
			ApiResponse<LoggedInUserDetailsResDto> apiResponse = new ApiResponse<>(); 
			LoggedInUserDetailsResDto result = userTokenService.getLoggedInUser();
			
			apiResponse.setResult(result);
			apiResponse.setMessage("Logged-in User Get Successful!");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getLoggedInUser Exception: " + e.getMessage());
			logger.error("An error occurred in /getLoggedInUser:", e);
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}

}
