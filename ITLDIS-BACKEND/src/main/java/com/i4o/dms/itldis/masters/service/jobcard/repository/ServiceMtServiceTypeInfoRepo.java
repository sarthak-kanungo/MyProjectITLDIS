package com.i4o.dms.itldis.masters.service.jobcard.repository;

import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtServiceTypeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceMtServiceTypeInfoRepo extends JpaRepository<ServiceMtServiceTypeInfo,Long> {
    @Query(value = "{call sp_service_mt_service_booking_dropdown_service_type(:serviceCategoryId,:modelId)}", nativeQuery = true)
    List<Map<String, Object>> dropDownServiceType(@Param("serviceCategoryId")Long serviceCategoryId,
                                     @Param("modelId")Long modelId);


    @Query(value = "{call sp_service_mt_service_booking_dropdown_model()}", nativeQuery = true)
    List<Map<String, Object>> dropDownModel();

}
