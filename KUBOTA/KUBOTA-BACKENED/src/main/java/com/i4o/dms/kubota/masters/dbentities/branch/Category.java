package com.i4o.dms.kubota.masters.dbentities.branch;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "category")
@Getter
@Setter
public class Category {
//    @Id
    private Long id;
    private String category;
}
