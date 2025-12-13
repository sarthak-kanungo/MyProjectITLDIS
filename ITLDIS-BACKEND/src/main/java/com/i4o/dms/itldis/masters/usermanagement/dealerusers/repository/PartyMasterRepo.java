package com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.PartySearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PartyMasterRepo extends JpaRepository<PartyMaster,Long> {

    @Query( value = "{call sp_mt_party_master_party_code_autocomplete(:partyCode,:branchID)}",nativeQuery = true)
    List<Map<String,Object>> findByPartyCodeContaining(@Param("partyCode") String partyCode,
    		@Param("branchID") Long branchID);

    @Query( value = "{call sp_mt_party_master_party_name_autocomplete(:partyName, :categoryId)}",nativeQuery = true)
    List<Map<String,Object>> findByPartyNameContaining(@Param("partyName") String partyName, @Param("categoryId")Long categoryId);

    @Query(value = "{call sp_mt_party_master_search(:usercode,:partyCode,:partyName,:page,:size)}",nativeQuery = true)
    List<PartySearchResponse> searchParty(@Param("usercode") String usercode,
    		@Param("partyCode") String partyCode,
                                                @Param("partyName") String partyName,
                                                @Param("page")Integer page,
                                                @Param("size")Integer size);

//    @Query(value = "{call sp_mt_party_master_search_count(:partyCode,:partyName,:page,:size)}",nativeQuery = true)
//    Long searchPartyCount(@Param("partyCode") String partyCode,
//                                   @Param("partyName") String partyName,
//                                   @Param("page")Integer page,
//                                   @Param("size")Integer size);

    @Query( value = "{call sp_mt_party_category(:userID)}",nativeQuery = true)
    List<Map<String,Object>> findByPartyCategories(@Param("userID") Integer userID);
    
    @Query( value = "{call sp_branch_autocomplete(:Value,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> findBranchCode(@Param("Value") String  Value,@Param("usercode") String usercode);
}
