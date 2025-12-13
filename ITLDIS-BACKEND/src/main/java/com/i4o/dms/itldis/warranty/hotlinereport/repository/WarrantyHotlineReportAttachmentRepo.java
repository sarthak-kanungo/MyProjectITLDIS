package com.i4o.dms.itldis.warranty.hotlinereport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.itldis.warranty.hotlinereport.domain.WarrantyHotlineReportAttachment;

public interface WarrantyHotlineReportAttachmentRepo extends JpaRepository<WarrantyHotlineReportAttachment, Long>  {
	
}
