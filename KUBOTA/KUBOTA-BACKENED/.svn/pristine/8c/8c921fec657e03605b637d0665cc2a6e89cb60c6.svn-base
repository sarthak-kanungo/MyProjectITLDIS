package com.i4o.dms.kubota.warranty.kaiinspectionsheet.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Table(name="WA_KAI_INSPECTION_SHEET_photo")
public class KaiInspectionSheetPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "kaiInspectionPhoto")
    private KaiInspectionSheet kaiInspectionSheet;

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
