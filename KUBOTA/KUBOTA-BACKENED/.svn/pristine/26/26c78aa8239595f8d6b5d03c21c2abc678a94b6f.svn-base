package com.i4o.dms.kubota.salesandpresales.marketIntelligence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.salesandpresales.marketIntelligence.domain.MarketIntelligence;
import com.i4o.dms.kubota.salesandpresales.marketIntelligence.dto.MarketIntelligenceSearchResponse;

public interface MarketIntelligenceRepo extends JpaRepository<MarketIntelligence, Long>{

	@Query(value="{call SP_MI_SEARCH (:userCode, :feedbackCatg, :fromDate, :toDate, :orgHier, :dealerId, :Page, :Size)}", nativeQuery=true)
	List<MarketIntelligenceSearchResponse> getSearchResult(@Param("userCode")String userCode, 
			@Param("feedbackCatg")String feedbackCatg, 
			@Param("fromDate")String fromDate, 
			@Param("toDate")String toDate, 
			@Param("orgHier")Long orgHier, 
			@Param("dealerId")Long dealerId, 
			@Param("Page")Integer page, 
			@Param("Size")Integer size);
}
