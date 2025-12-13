package com.i4o.dms.itldis.spares.partrequisition.repository;

import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisition;
import com.i4o.dms.itldis.spares.partrequisition.dto.RequisitionSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SparePartRequisitionRepo extends JpaRepository<SparePartRequisition,Long> {
     SparePartRequisition findByServiceJobCard(ServiceJobCard serviceJobCard);

    @Query(value = "{call sp_spares_part_requisition_get_requisition_purpose}",nativeQuery = true)
    List<Map<String,Object>> getRequisitionPurpose();

    @Query(value="select id from SP_MT_PART_CATEGORY where category=:catg",nativeQuery = true)
    Long getCategoryIdByName(@Param("catg")String catg);
    
    @Query(value = "{call sp_spares_part_requisition_search_part_requisition_no(:requisitionNo,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchPartRequisitionNo(@Param("requisitionNo") String requisitionNo,
                                                     @Param("usercode") String usercode);

    @Query(value = "{call sp_spares_part_requisition_search_job_card_no(:jobCardNo,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> searchJobCardNo(@Param("jobCardNo") String jobCardNo,
                                             @Param("usercode") String usercode);

    @Query(value = "{call sp_spares_part_requisition_search_part_requisition(:requisitionNo,:jobCardNo,:requisitionPurpose,:fromDate,:toDate,:branchId,:dealerEmplId,:page,:size,:userCode)}",nativeQuery = true)
    List<RequisitionSearchResponse> searchPartRequisition(@Param("requisitionNo") String requisitionNo,
                                                          @Param("jobCardNo") String jobCardNo,
                                                          @Param("requisitionPurpose") String requisitionPurpose,
                                                          @Param("fromDate") String fromDate,
                                                          @Param("toDate") String toDate,
                                                          @Param("branchId") Long branchId,
                                                          @Param("dealerEmplId") Long dealerEmplId,
                                                          @Param("page") Integer page, 
                                                          @Param("size") Integer size,
                                                          @Param("userCode")String userCode);

    @Query(value = "{call sp_spares_part_requisition_get_part_requisition_by_id(:requisitionId)}",nativeQuery = true)
    Map<String, Object> findPartRequisitionById(@Param("requisitionId") Long requisitionId);

    @Query(value = "{call sp_spares_part_requisition_get_part_requisition_item_by_id(:requisitionId)}",nativeQuery = true)
    List<Map<String, Object>> findPartRequisitionItemsById(@Param("requisitionId") Long requisitionId);

    @Query(value = "{call sp_spares_part_requisition_search_item_no(:itemNo,:existingIds,:dealerId)}",nativeQuery = true)
    List<Map<String,Object>> searchSparesPartItemNo(@Param("itemNo") String itemNo,@Param("existingIds")String existingIds,@Param("dealerId") Long dealerId);

    @Query(value = "{call sp_spares_part_requisition_get_spares_part_item_details(:itemNo)}",nativeQuery = true)
    Map<String,Object> getSparesPartItemNoDetails(@Param("itemNo") String itemNo);




}
