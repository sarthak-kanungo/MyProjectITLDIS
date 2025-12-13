package com.i4o.dms.itldis.salesandpresales.marketIntelligence.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","userName","dealerName","dealerCode","date","feedbackCategory","product","competitor","feedback"})
public interface MarketIntelligenceSearchResponse {

	String getUserName();
	String getDealerName();
	String getDealerCode();
	String getDate();
	String getFeedbackCategory();
	String getProduct();
	String getCompetitor();
	String getFeedback();
	@JsonIgnore
	Long getTotalCount();
}
