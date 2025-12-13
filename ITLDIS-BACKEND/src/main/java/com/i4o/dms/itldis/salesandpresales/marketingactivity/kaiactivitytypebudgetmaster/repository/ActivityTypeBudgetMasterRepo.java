package com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.repository;

import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.domain.ActivityTypeBudgetMaster;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.dto.ActivityBudgetMasterSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ActivityTypeBudgetMasterRepo extends JpaRepository<ActivityTypeBudgetMaster,Long> {

//    @Query(value = "{call sp_getMaximumLimitByActivityType(:activityType)}",nativeQuery = true)
//    Map<String,Object> getMaximumLimitByActivityType(@Param("activityType") String activityType);

    @Query(value = "{call sp_mt_sales_get_activity_type_by_purpose(:purpose)}",nativeQuery = true)
    List<Map<String,Object>> getActivityTypeByPurpose(@Param("purpose") String purpose);

    //sp_sales_and_presales_activity_type_budget_master
    @Query(value = "{call sp_SM_activity_proposal_get_activity_type()}",nativeQuery = true)
    List<Map<String,Object>> getAllActivityType();

    @Query(value = "{call sp_budgetTypeDropdown}",nativeQuery = true)
    List<Map<String,Object>> budgetType();

    @Query( value = "{call sp_maxDayMonth_auto(:maximumDayMonth)}",nativeQuery = true)
    List<Map<String,Object>> findByMaximumDayMonthContaining(@Param("maximumDayMonth") Integer maximumDayMonth);

    @Query( value = "{call sp_kaiShare_auto(:kaiShare)}",nativeQuery = true)
    List<Map<String,Object>> findByKaiShareContaining(@Param("kaiShare") Double kaiShare);

    @Query( value = "{call sp_maxLimit_auto(:maximumLimit)}",nativeQuery = true)
    List<Map<String,Object>> findByMaximumLimitContaining(@Param("maximumLimit") Double maximumLimit);

    @Query(value = "{call sp_getSearchActivityBudgetMaster(:activityType,:budgetType,:maximumLimit,:maximumDayMonth,:kaiShare,:page,:size)}",nativeQuery = true)
    List<ActivityBudgetMasterSearchResponse> searchActivityBudget(@Param("activityType") String activityType,
                                                                  @Param("budgetType") String budgetType,
                                                                  @Param("maximumLimit")Double maximumLimit,
                                                                  @Param("maximumDayMonth")Integer maximumDayMonth,
                                                                  @Param("kaiShare")Double kaiShare,
                                                                  @Param("page")Integer page,
                                                                  @Param("size")Integer size);

    @Query(value = "{call sp_getSearchActivityBudgetMasterCount(:activityType,:budgetType,:maximumLimit,:maximumDayMonth,:kaiShare,:page,:size)}",nativeQuery = true)
    Long searchActivityBudgetCount(@Param("activityType") String activityType,
                                                                  @Param("budgetType") String budgetType,
                                                                  @Param("maximumLimit")Double maximumLimit,
                                                                  @Param("maximumDayMonth")Integer maximumDayMonth,
                                                                  @Param("kaiShare")Double kaiShare,
                                                                  @Param("page")Integer page,
                                                                  @Param("size")Integer size);

    @Query(value="{call sp_get_heads_for_marketing_activity}",nativeQuery = true)
    List<Map<String,Object>> getHeadsForMarketingActivity();


}
