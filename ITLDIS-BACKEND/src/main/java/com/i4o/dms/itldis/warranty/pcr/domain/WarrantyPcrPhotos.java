package com.i4o.dms.itldis.warranty.pcr.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name="WA_PCR_PHOTOS")
public class WarrantyPcrPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private WarrantyPcr warrantyPcr;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @Override
    public String toString() {
        return "ServiceMrcPhotos{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }

}
