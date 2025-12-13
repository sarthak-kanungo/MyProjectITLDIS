package com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository;

import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.dto.MachineDescripancyComplaintViewDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.dto.ResponseSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineDescripancyComplaintRepository extends JpaRepository<MachineDescripancyComplaint,Long> {

    @Query(value = "{call sp_sales_and_presales_purchase_machine_descripancy_complaint_search_chassis_number(:chassisNumber,:branchId)}",nativeQuery = true)
    List<Map<String,Object>> searchChassisNumber(@Param("chassisNumber") String chassisNumber,
                                                 @Param("branchId") Long branchId);

    @Query(value = "{call sp_sales_and_presales_purchase_machine_descripancy_complaint_get_details_by_chassis_number(:chassisNumber)}",nativeQuery = true)
    Map<String,Object> getDetailsByChassisNumber(@Param("chassisNumber") String chassisNumber);

    @Query(value = "{call sp_sales_and_presales_purchase_machine_descripancy_complaint_search_item(:itemNo)}",nativeQuery = true)
    List<Map<String,Object>> searchItemNumber(@Param("itemNo") String itemNo);

    @Query(value = "{call sp_sales_and_presales_purchase_machine_descripancy_complaint_get_details_by_itemId(:itemId)}",nativeQuery = true)
    Map<String,Object> getDetailsByItemId(@Param("itemId") Long itemId);

    @Query(value = "{call sp_sales_presales_machine_descripancy_complaint_search_complaint_number(:complaintNumber)}",nativeQuery = true)
    List<Map<String,Object>> getComplaintNumber(@Param("complaintNumber") String complaintNumber);

    @Query(value = "{call sp_sales_presales_machine_descripancy_complaint_get_complaint_status()}",nativeQuery = true)
    List<Map<String,Object>> getComplaintStatus();

    @Query(value = "{call sp_sales_presales_machine_descripancy_complaint_get_complaint_type()}",nativeQuery = true)
    List<Map<String,Object>> getComplaintType();

    @Query(value = "{call sp_sales_presales_machine_descripancy_complaint_search_existing_chassis_number(:chassisNumber)}",nativeQuery = true)
    List<Map<String,Object>> searchExistingChassisNumber(@Param("chassisNumber") String chassisNumber);

    @Query(value = "{call sp_sales_presales_machine_descripancy_complaint_search_grn_number(:grnNumber)}",nativeQuery = true)
    List<Map<String,Object>> searchGrnNumber(@Param("grnNumber") String grnNumber);

    //sp_sales_and_presales_machine_descripancy_complaint_search
    @Query(value = "{call sp_sales_and_presales_machine_descripancy_complaint_search(:complaintNumber,:complaintStatus,:dmsGrnNumber,:chassisNo,:fromDate,:toDate,:page,:size,:userName)}", nativeQuery = true)
    List<ResponseSearchDto> searchBy(
            @Param("complaintNumber") String complaintNumber,
            @Param("complaintStatus") String complaintStatus,
            @Param("dmsGrnNumber") String dmsGrnNumber,
            @Param("chassisNo") String chassisNo,
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate,
            @Param("page") Integer page,
            @Param("size") Integer size,
            @Param("userName") String userName);


    MachineDescripancyComplaintViewDto findByComplaintId(Long complaintId);

    List<MachineDescripancyComplaint> findByDealerMasterId(Long dealerId);
    List<MachineDescripancyComplaint> findByDealerEmployeeMasterId(Long dealerId);
}

