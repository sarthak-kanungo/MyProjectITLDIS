package com.i4o.dms.kubota.masters.dbentities.warranty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "mt_field_condition")
public class WarrantyMtFieldCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldCondition;

    private String activeStatus;
}
