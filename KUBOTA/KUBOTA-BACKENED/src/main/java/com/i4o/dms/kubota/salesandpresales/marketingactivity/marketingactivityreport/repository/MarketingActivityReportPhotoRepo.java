package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.repository;

import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReportImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketingActivityReportPhotoRepo extends JpaRepository<MarketingActivityReportImages,Long> {
}
