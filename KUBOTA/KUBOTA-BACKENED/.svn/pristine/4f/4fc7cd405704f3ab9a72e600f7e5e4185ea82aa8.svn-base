package com.i4o.dms.kubota.masters.service.serviceactivityproposal.repository;

import com.i4o.dms.kubota.masters.service.serviceactivityproposal.domain.ServiceMtActivityTypeHeads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ActivityProposalMtRepo extends JpaRepository<ServiceMtActivityTypeHeads,Long>
{
    @Query(value = "{call sp_mt_service_activity_proposal_get_all_activity_type}", nativeQuery = true)
    List<Map<String,Object>> getAllActivityType();

    @Query(value = "{call sp_mt_service_activity_proposal_get_sub_activities(:activityTypeId)}",nativeQuery = true)
    List<Map<String,Object>> getSubActivity(@Param("activityTypeId") Long activityTypeId
                                            );

    @Query(value = "{call sp_mt_service_activity_proposal_get_all_heads_by_activity_type_id(:activityTypeId)}",nativeQuery = true)
    List<Map<String,Object>> getHeadsOnActivityType(@Param("activityTypeId") Long activityTypeId
                                                    );

    @Query(value = "{call sp_mt_service_activity_proposal_get_all_products}", nativeQuery = true)
    List<Map<String,Object>> getAllProductFromProductMaster();

    @Query(value = "{call sp_mt_service_activity_proposal_get_max_allowed_budget_by_numberOfPerson(:numberOfPerson)}", nativeQuery = true)
    Double getMAxAllowedBudgetByPerson(@Param("numberOfPerson") Integer numberOfPerson);
}
