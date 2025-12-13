package com.i4o.dms.kubota.masters.profilepicture.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageUpload{

	@JsonProperty("imageName")
    private String imageName;
    
	@JsonProperty("userCode")
    private String userCode;
	
	@JsonProperty("profilePicture")
    private String profilePicture;
	
}
