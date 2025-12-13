package com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerMasterSearchResponse;

import com.i4o.dms.kubota.service.activityproposal.dto.SearchResponsiveServiceActivityProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DealerMasterRepo extends JpaRepository<DealerMaster,Long> {

    DealerMaster findByDealerCode(String dealerCode);

    @Query( value = "{call sp_dealer_autocomplete(:dealerCode,:usercode)}",nativeQuery = true)
    List<Map<String,Object>> getDealerCodeAutoComplete(@Param("dealerCode") String dealerCode, @Param("usercode") String usercode);
    
    @Query( value = "{call sp_dealer_mt_dealer_code_autocomplete(:dealerCode)}",nativeQuery = true)
    List<Map<String,Object>> findByDealerCodeContaining(@Param("dealerCode") String dealerCode);

    @Query( value = "{call sp_dealer_mt_dealer_name_autocomplete(:dealerName)}",nativeQuery = true)
    List<Map<String,Object>> findByDealerNameContaining(@Param("dealerName") String dealerName);

    @Query(value="{call sp_dealer_mt_dealer_allocated_territory_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getAllocatedTerritory();

    @Query (value="{call sp_dealer_mt_dealer_area_level_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getAreaLevel();

    @Query (value="{call sp_dealer_mt_dealer_star_rating_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getAStarRating();

//    @Query(value = "{call sp_dealer_mt_dealer_search(:dealerCode,:dealerName,:page,:size)}",nativeQuery = true)
//    List<DealerMasterSearchResponse> searchDealer(@Param("dealerCode") String dealerCode,
//                                                 @Param("dealerName") String dealerName,
//                                                 @Param("page")Integer page,
//                                                 @Param("size")Integer size);

    @Query(value = "{call sp_dealer_mt_dealer_search_count(:dealerCode,:dealerName,:page,:size)}",nativeQuery = true)
    Long searchDealerCount(@Param("dealerCode") String dealerCode,
                                                  @Param("dealerName") String dealerName,
                                                  @Param("page")Integer page,
                                                  @Param("size")Integer size);


    @Query (value="{call sp_getDealerDistrictMapping(:userCode,:allDist)}",nativeQuery = true)
    List<Map<String,Object>> getDealerRegionInfo(@Param("userCode")String userCode,@Param("allDist")Boolean allDist);


    //Stored Procedure Not Available
    @Query (value="{call sp_getPinCode(:code,:districtId)}",nativeQuery = true)
    List<Map<String,Object>> autoCompleteTehsilCityPincode(@Param("code")String  code,@Param("districtId")Long districtId);


    //Stored Procedure Not Available
    //pcmId=pincode_city_mapping_id
    @Query (value="{call sp_getByPinCode(:cityId, :pincodeId)}",nativeQuery = true)
    Map<String,Object> getPincodeDetailByPincodeId(@Param("cityId")Long cityId, @Param("pincodeId")Long pincodeId);



    @Query(value = "{call sp_dealer_master_search(:zone,:region,:area,:territory,:dealerCode,:dealerName,:status,:subsidyDealer,:page,:size)}", nativeQuery = true)
    List<DealerMasterSearchResponse> searchDealerMaster(
            @Param("zone") String zone,
            @Param("region") String region,
            @Param("area") String area,
            @Param("territory") String territory,
            @Param("dealerCode") String dealerCode,
            @Param("dealerName") String dealerName,
            @Param("status") String status,
            @Param("subsidyDealer") String subsidyDealer,
            @Param("page") Integer page,
            @Param("size") Integer size
    );


    @Query(value = "{call sp_dealer_master_search_count(:zone,:region,:area,:territory,:dealerCode,:dealerName,:status,:subsidyDealer,:page,:size)}", nativeQuery = true)
    Long searchDealerMasterCount(
            @Param("zone") String zone,
            @Param("region") String region,
            @Param("area") String area,
            @Param("territory") String territory,
            @Param("dealerCode") String dealerCode,
            @Param("dealerName") String dealerName,
            @Param("status") String status,
            @Param("subsidyDealer") String subsidyDealer,
            @Param("page") Integer page,
            @Param("size") Integer size
    );

    @Query(value="{call sp_dealer_mt_status_dropdown()}",nativeQuery = true)
    List<Map<String,Object>> getStatus();
    
    @Query (value="{call sp_getAllDealerDistrictMapping()}",nativeQuery = true)
    List<Map<String,Object>> getAllDealerRegionInfo();

}

