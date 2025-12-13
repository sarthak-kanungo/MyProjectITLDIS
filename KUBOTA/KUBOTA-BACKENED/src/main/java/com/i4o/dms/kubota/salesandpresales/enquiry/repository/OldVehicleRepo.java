package com.i4o.dms.kubota.salesandpresales.enquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.salesandpresales.enquiry.domain.OldVehicleDetails;

public interface OldVehicleRepo extends JpaRepository<OldVehicleDetails, Long> {

	OldVehicleDetails getByEnquiryId(Long id);
}
