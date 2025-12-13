package com.i4o.dms.itldis.eamg.servicebulletin.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.servicebulletin.dto.ServiceBulletinRequestDto;
import com.i4o.dms.itldis.eamg.servicebulletin.dto.ServiceBulletinResponseDto;

/**
 * EAMG Service Bulletin Service Interface
 * Reference: EAMG_Service_Bulletin.EAMG_ServiceBulletinAction
 */
public interface EamgServiceBulletinService {
    
    /**
     * Initialize Service Bulletin Update
     */
    ServiceBulletinResponseDto initUpdate();
    
    /**
     * Insert/Update Service Bulletin
     */
    ServiceBulletinResponseDto insertUpdateServiceBulletin(ServiceBulletinRequestDto request, MultipartFile[] files);
    
    /**
     * View Bulletin
     */
    ServiceBulletinResponseDto viewBulletin(String bulletinNo);
    
    /**
     * View Bulletin Details
     */
    ServiceBulletinResponseDto viewBulletinDetails(String type, String yearOfIssue);
    
    /**
     * Initialize Service Bulletin Delete
     */
    ServiceBulletinResponseDto initDelete();
    
    /**
     * Get Bulletins for Delete
     */
    ServiceBulletinResponseDto getBulletinsForDelete(String type, String yearOfIssue);
    
    /**
     * Delete Bulletin
     */
    ServiceBulletinResponseDto deleteBulletin(ServiceBulletinRequestDto request);
    
    /**
     * Download Bulletin File
     */
    byte[] downloadBulletinFile(String bulletinNo, String fileName);
}
