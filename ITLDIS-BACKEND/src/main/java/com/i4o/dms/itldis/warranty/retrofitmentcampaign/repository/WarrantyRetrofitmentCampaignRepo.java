package com.i4o.dms.itldis.warranty.retrofitmentcampaign.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaign;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto.WarrantyRetrofitmentCampaignResponseDto;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto.WarrantyRetrofitmentcampaignViewDto;

public interface WarrantyRetrofitmentCampaignRepo extends JpaRepository<WarrantyRetrofitmentCampaign,Long> {

    WarrantyRetrofitmentcampaignViewDto findByRetrofitmentNo(String retrofitmentNo);

    @Query(value = "{call sp_warranty_retro_fitment_campaign_search(:rfcNo,:status,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
    List<WarrantyRetrofitmentCampaignResponseDto> searchWarrantyRetrofitmentCampaign(@Param("rfcNo")String rfcNo,
			@Param("status") String status, @Param("fromDate") String fromDate, @Param("toDate") String toDate,
			@Param("page") Long page, @Param("size") Long size);

    @Query(value = "{call sp_warranty_retro_fitment_campaign_search_count(:rfcNo,:status,:fromDate,:toDate,:page,:size)}", nativeQuery = true)
	Long searchWarrantyRetrofitmentCampaignCount(@Param("rfcNo") String rfcNo, @Param("status") String status,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("page") Long page,
			@Param("size") Long size);


    @Query(value = "{call sp_warranty_retrofitment_campaign_dropdown_status()}", nativeQuery = true)
    List<Map<String,Object>> searchRetrofitmentStatus();

    @Query(value = "{call sp_warranty_retro_fitment_campaign_dropdown_retrofitment_no(:retrofitmentNo)}", nativeQuery = true)
    List<Map<String,Object>> searchRetrofitmentNo(@Param("retrofitmentNo")String retrofitmentNo);


    /**
     * @author suraj.gaur
     */
    @Query(value = "{call sp_service_jobcode_auto_complete_jobcode_no(:jobcode)}", nativeQuery = true)
	List<Map<String, Object>> getAutoCompleteJobCode(@Param("jobcode") String jobcode);


}

