package com.i4o.dms.itldis.masters.profilepicture.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.profilepicture.domain.ImageUpload;
import com.i4o.dms.itldis.masters.profilepicture.domain.UserProfilePicture;
import com.i4o.dms.itldis.masters.profilepicture.repository.UserProfilePictureRepo;
import com.i4o.dms.itldis.masters.usermanagement.user.repository.KubotaUserRepository;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/master/profilepicture")
public class ProfilePictureController {
	
	
	@Autowired
    private StorageService storageService;
	
	@Autowired
	private UserProfilePictureRepo userProfilePictureRepo;
	
	@Autowired
    private KubotaUserRepository kubotaUserRepository;	
	

	@PostMapping("/saveProfilePicture")
	public ResponseEntity<?> saveUserProfilePicture(@RequestBody ImageUpload imageUpload) {
		System.out.println("image is uploading.......");
		ApiResponse apiResponse = new ApiResponse();
		if (kubotaUserRepository.findByUserName(imageUpload.getUserCode()) != null) {
			String profilePhoto = imageUpload.getImageName();
			String pictureName = "user_profile_pic" + "_" + imageUpload.getUserCode() + "_" + System.currentTimeMillis() + "_" + profilePhoto;
			 
			storageService.store(imageUpload.getProfilePicture(), pictureName, profilePhoto);
			
			UserProfilePicture userProfilePicture = userProfilePictureRepo.findByUserCode(imageUpload.getUserCode());
			if(userProfilePicture==null){
				userProfilePicture = new UserProfilePicture();
			}
			userProfilePicture.setImageName(pictureName);
			userProfilePicture.setUserCode(imageUpload.getUserCode());
			
			userProfilePictureRepo.save(userProfilePicture);

			apiResponse.setMessage("User profile picture saved successfully.");
			apiResponse.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(apiResponse);
		}

		apiResponse.setMessage("Invalid User");
		apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.badRequest().body(apiResponse);
	}
	
	@GetMapping("/getProfilePicture")
	public ResponseEntity<?> getUserProfilePicture(@RequestParam String userCode) {
		UserProfilePicture userProfilePicture = userProfilePictureRepo.findByUserCode(userCode);
		System.out.println("userProfilePicture : "+userProfilePicture);
		if (userProfilePicture != null) {
			Resource file = storageService.loadAsResource(userProfilePicture.getImageName());
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		}
		return ResponseEntity.badRequest().build();
	}
	 
}
