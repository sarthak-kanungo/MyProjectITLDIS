package com.i4o.dms.itldis.salesandpresales.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryAttachmentsImages;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReportImages;

@Repository
public interface EnquiryAttachmentsImagesRepo extends JpaRepository<EnquiryAttachmentsImages,Long>{

}
