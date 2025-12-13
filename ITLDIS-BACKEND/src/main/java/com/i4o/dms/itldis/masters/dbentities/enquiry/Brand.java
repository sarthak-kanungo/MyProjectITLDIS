package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CM_MST_competition_brand")
public class Brand
{
    @Id
    private Long id;
    private String brandName;
}
