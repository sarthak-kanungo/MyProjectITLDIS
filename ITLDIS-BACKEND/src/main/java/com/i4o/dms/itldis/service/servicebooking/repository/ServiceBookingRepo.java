package com.i4o.dms.itldis.service.servicebooking.repository;

import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.itldis.service.servicebooking.domain.ServiceBooking;
import com.i4o.dms.itldis.service.servicebooking.dto.ServiceBookingResponseDto;
import com.i4o.dms.itldis.service.servicebooking.dto.ServiceBookingViewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceBookingRepo extends JpaRepository<ServiceBooking,Long> {
    @Query(value = "{call sp_service_service_booking_dropdown_source_of_booking()}", nativeQuery = true)
    List<Map<String, Object>> dropDownSourceOfBooking();

    @Query(value = "{call sp_service_service_booking_autocomplete_chassisNo(:chassisNo,:dealerId)}", nativeQuery = true)
    List<Map<String, Object>> autoCompleteChassisNo(@Param("chassisNo")String chassisNo,@Param("dealerId")Long dealerId);

    @Query(value = "{call sp_service_service_booking_search(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:bookingNo,:status,:chassisNo,:bookingFromDate,:bookingToDate,:mechanicName,:engineNo,:machineSubModel,:sourceOfBooking,:serviceOfCategory,:serviceType,:placeOfService,:activityType,:activityNo,:appointmentFromDate,:appointmentToDate,:page,:size,:userCode,:includeInactive,:orgHierId)}", nativeQuery = true)
    List<ServiceBookingResponseDto> serviceBookingSearch(
                                                  @Param("management") Boolean managementAccess,
                                                  @Param("dealerId") Long dealerId,
                                                    @Param("kaiEmployeeId") Long kaiEmployeeId,
                                                    @Param("dealerEmployeeId") Long userId
                                                   ,@Param("bookingNo") String bookingNo,
                                                   @Param("status") String status,
                                                   @Param("chassisNo") String chassisNo,
                                                   @Param("bookingFromDate") String bookingFromDate,
                                                   @Param("bookingToDate") String bookingToDate,
                                                   @Param("mechanicName") String mechanicName,
                                                   @Param("engineNo") String engineNo,
                                                   @Param("machineSubModel") String machineSubModel,
                                                   @Param("sourceOfBooking") String sourceOfBooking,
                                                   @Param("serviceOfCategory") String serviceOfCategory,
                                                   @Param("serviceType") String serviceType,
                                                   @Param("placeOfService") String placeOfService,
                                                   @Param("activityType") String activityType,
                                                   @Param("activityNo") String activityNo,
                                                   @Param("appointmentFromDate") String appointmentFromDate,
                                                   @Param("appointmentToDate") String appointmentToDate,
                                                   @Param("page") Integer page,
                                                   @Param("size")Integer size,
    											   @Param("userCode") String userCode,
                                                   @Param("includeInactive")Character includeInactive,
    											   @Param("orgHierId") Long orgHierId);

    @Query(value = "{call sp_service_service_booking_search_count(:management,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:bookingNo,:status,:chassisNo,:bookingFromDate,:bookingToDate,:mechanicName,:engineNo,:machineSubModel,:sourceOfBooking,:serviceOfCategory,:serviceType,:placeOfService,:activityType,:activityNo,:appointmentFromDate,:appointmentToDate,:page,:size)}", nativeQuery = true)
    Long  serviceBookingSearchCount(
            @Param("management") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long userId
            ,@Param("bookingNo") String bookingNo,
            @Param("status") String status,
            @Param("chassisNo") String chassisNo,
            @Param("bookingFromDate") String bookingFromDate,
            @Param("bookingToDate") String bookingToDate,
            @Param("mechanicName") String mechanicName,
            @Param("engineNo") String engineNo,
            @Param("machineSubModel") String machineSubModel,
            @Param("sourceOfBooking") String sourceOfBooking,
            @Param("serviceOfCategory") String serviceOfCategory,
            @Param("serviceType") String serviceType,
            @Param("placeOfService") String placeOfService,
            @Param("activityType") String activityType,
            @Param("activityNo") String activityNo,
            @Param("appointmentFromDate") String appointmentFromDate,
            @Param("appointmentToDate") String appointmentToDate,
            @Param("page") Integer page,
            @Param("size")Integer size);



    @Query(value = "{call sp_service_service_booking_autocomplete_booking_no(:bookingNo,:dealerId,:jobCardFlag,:userCode)}", nativeQuery = true)
    List<Map<String, Object>> autoCompleteBookingNo(@Param("bookingNo")String bookingNo,@Param("dealerId")Long dealerId,
    		@Param("jobCardFlag")Boolean jobCardFlag,@Param("userCode")String userCode);

    @Query(value = "{call sp_service_service_booking_search_autocomplete_engine_number(:engineNo,:userCode)}", nativeQuery = true)
//    List<Map<String, Object>> autoCompleteEngineNumber(@Param("engineNo")String bookingNo,@Param("dealerId")Long dealerId);
    List<Map<String, Object>> autoCompleteEngineNumber(@Param("engineNo")String bookingNo,@Param("userCode")String userCode);

    @Query(value = "{call sp_service_booking_view_by_id(:id)}", nativeQuery = true)
    ServiceBookingViewDto viewServiceBookingById(@Param("id")Long id);














}
