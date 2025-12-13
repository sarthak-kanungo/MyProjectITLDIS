package com.i4o.dms.itldis.masters.service.servicebooking.repository;

import com.i4o.dms.itldis.masters.service.servicebooking.domain.ServiceMtBookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ServiceMtBookingStatusRepo extends JpaRepository<ServiceMtBookingStatus,Long> {
    @Query(value = "{call sp_service_mt_dropdown_booking_status()}", nativeQuery = true)
    List< Map<String, Object>> dropDownBookingStatus();
}
