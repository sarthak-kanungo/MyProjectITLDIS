package com.i4o.dms.itldis.masters.dbentities.warranty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "CM_MST_CROP_CONDITION")
public class WarrantyMtCropCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  cropCondition;

    private String activeStatus;
}
