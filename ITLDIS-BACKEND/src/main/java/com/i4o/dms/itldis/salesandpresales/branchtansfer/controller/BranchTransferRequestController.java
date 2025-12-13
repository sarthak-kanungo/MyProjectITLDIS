//package com.i4o.dms.itldis.salesandpresales.branchtansfer.controller;
//
//import com.i4o.dms.itldis.salesandpresales.branchtansfer.repository.BranchTransferRequestRepo;
//import com.i4o.dms.itldis.salesandpresales.branchtansfer.service.btreceipt.BranchTransferRequestService;
//import com.i4o.dms.itldis.utils.ApiResponse;
//
//import org.hibernate.annotations.common.util.impl.Log_.logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@ResponseBody
//@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
//        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
//@RequestMapping(value = "/api/branchTransferRequest")
//public class BranchTransferRequestController {
//
//    @Autowired
//    private BranchTransferRequestRepo branchTransferRequestRepo;
//    
//    @Autowired
//    BranchTransferRequestService requestService;
//
//    /**
//	 * @author mahesh.kumar
//	 * @apiNote get requested branch by id
//	 * @return ResponseEntity<?>
//	 */
//	@GetMapping("/getReqToBranchDeatilsById")
//	public ResponseEntity<?> getReqToBranchDeatilsById()
//	{
//		try {
//			ApiResponse<?> apiResponse = requestService.getReqToBranchDeatilsById();
//			apiResponse.setMessage("Get Request to Branch Details Success");
//			apiResponse.setStatus(HttpStatus.OK.value());
//			
//			return ResponseEntity.ok(apiResponse);
//		} catch (Exception e) {
//			ApiResponse<?> apiResponse = new ApiResponse<>();
//			System.out.println(e.getMessage());
//			logger.info("/getReqToBranchDeatilsById Exception: " + e.getMessage());
//			apiResponse.setMessage("There is some error occured");
//			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
//			
//			return ResponseEntity.badRequest().body(apiResponse);
//		}
//	}
//}
//
//
//
//
