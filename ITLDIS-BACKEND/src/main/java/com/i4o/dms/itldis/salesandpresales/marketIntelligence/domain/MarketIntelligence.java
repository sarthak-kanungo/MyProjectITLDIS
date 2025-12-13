package com.i4o.dms.itldis.salesandpresales.marketIntelligence.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SA_MARKET_INTELLIGENCE")
public class MarketIntelligence implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2508723654166094409L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;
    
    private Long dealerEmpId;
    
    private Long feedbackCategoryId;
    
    private String productName;
    
    private String competitorName;
    
    private String feedbackDesc;
    
    private Long createdBy;
    
    private Date createdOn = new Date(); 
    
}
