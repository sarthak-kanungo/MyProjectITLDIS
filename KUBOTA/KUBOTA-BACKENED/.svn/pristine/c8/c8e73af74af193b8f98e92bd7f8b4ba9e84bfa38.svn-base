package com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerBranchAndSubDealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerBranchAndSubDealerSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DealerBranchAndSubDealerRepo extends JpaRepository<DealerBranchAndSubDealerMaster,Long> {

    @Query(value = "{call sp_dealer_mt_branch_subDealer_category_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> branchCategoryDropdown();

    @Query(value = "{call sp_dealer_mt_branch_subDealer_code_autocomplete(:branchCode)}",nativeQuery = true)
    List<Map<String,Object>> findBySubDealerCodeContaining(@Param("branchCode") String branchCode);

    @Query(value = "{call sp_dealer_mt_branch_subDealer_name_autocomplete(:subDealerName)}",nativeQuery = true)
    List<Map<String,Object>> findBySubDealerNameContaining(@Param("subDealerName") String subDealerName);

    @Query(value="{call sp_dealer_mt_branch_subDealer_autocomplete_category(:category)}",nativeQuery = true)
    List<Map<String,Object>> findByCategoryContaining(@Param("category") String category);

    @Query(value = "{call sp_dealer_mt_branch_subDealer_search(:branchCode,:subDealerName,:category,:page,:size)}",nativeQuery = true)
    List<DealerBranchAndSubDealerSearchResponse> searchBranch(@Param("branchCode") String branchCode,
                                                              @Param("subDealerName") String subDealerName,
                                                              @Param("category") String category,
                                                              @Param("page")Integer page,
                                                              @Param("size")Integer size);

    @Query(value = "{call sp_dealer_mt_branch_subDealer_search_count(:branchCode,:subDealerName,:category,:page,:size)}",nativeQuery = true)
    Long searchBranchCount(@Param("branchCode") String branchCode,
                                                    @Param("subDealerName") String subDealerName,
                                                    @Param("category") String category,
                                                    @Param("page")Integer page,
                                                    @Param("size")Integer size);

    @Query(value = "{call sp_dealer_mt_branch_subDealer_branch_name_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getBranchName();

}
