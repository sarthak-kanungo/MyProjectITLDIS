package com.i4o.dms.itldis.warranty.logsheet.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="WA_LOGSHEET_PHOTOS")
public class WarrantyLogsheetPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(length = 300)
    private String fileName;

    @ManyToOne
    @JsonBackReference
    private WarrantyLogsheet warrantyLogsheet;

    @Override
    public String toString() {
        return "ServiceMrcPhotos{" +
                "id=" + id +
                "fileName='" + fileName + '\'' +
                '}';
    }

}
