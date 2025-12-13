package com.i4o.dms.itldis.masters.spares;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="SP_MT_PART_CATEGORY")
public class SpareMtPartCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String activeStatus;

   // @Column(columnDefinition = "boolean default false")
    private Boolean applicableToLabourCharge;

 //   @Column(columnDefinition = "boolean default false")
    private Boolean applicableToOsLabourCharge;
}
