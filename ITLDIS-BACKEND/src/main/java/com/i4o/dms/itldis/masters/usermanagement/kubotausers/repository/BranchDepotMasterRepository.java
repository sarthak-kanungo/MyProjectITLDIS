package com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.BranchDepotMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BranchDepotMasterRepository extends JpaRepository<BranchDepotMaster,Long> {

    //Need to have it in dealer_depot_mapping
    @Query(value = "{call sp_mt_branch_depot_getAllDepot(:dealerId,:poId)}", nativeQuery = true)
    List<Map<String,Object>> getAllDepot(@Param("dealerId") Long id,@Param("poId") Long poId);

//    @Query("select b.id as id,b.branchName as branchName,b.branchCode as branchCode from BranchDepotMaster b where b.activeStatus = 'Y' and b.depotFlag = 'Y' ")
//    List<Map<String,Object>> getAllActiveBranchName();

    @Query(value = "{call sp_mt_branch_depot_autocomplete_branch_code(:branchCode)}",nativeQuery = true)
    List<Map<String,Object>> findByBranchCodeContaining(@Param("branchCode") String branchCode);

    @Query(value = "{call sp_depot_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getDepotDropdown();

}
