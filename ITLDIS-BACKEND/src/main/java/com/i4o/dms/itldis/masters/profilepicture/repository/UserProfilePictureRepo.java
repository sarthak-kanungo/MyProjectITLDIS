package com.i4o.dms.itldis.masters.profilepicture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.masters.profilepicture.domain.UserProfilePicture;

public interface UserProfilePictureRepo extends JpaRepository<UserProfilePicture,Long>{

	UserProfilePicture findByUserCode(String delerCode);
}
