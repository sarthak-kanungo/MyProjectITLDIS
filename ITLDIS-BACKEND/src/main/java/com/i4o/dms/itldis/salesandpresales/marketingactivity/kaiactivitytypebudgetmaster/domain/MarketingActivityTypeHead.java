package com.i4o.dms.itldis.salesandpresales.marketingactivity.kaiactivitytypebudgetmaster.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SM_MST_activity_type_head")
public class MarketingActivityTypeHead
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String head;

    @ManyToMany(mappedBy = "marketingActivityTypeHeads")
    private List<ActivityTypeBudgetMaster> activityTypeBudgetMasterList = new ArrayList<>();



}
