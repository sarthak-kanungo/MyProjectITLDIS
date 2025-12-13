package com.i4o.dms.itldis.warranty.goodwill.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@Table(name="wa_goodwill_photo")
public class WarrantyGoodwillPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private WarrantyGoodwill warrantyGoodwill;


    @NotNull
    @Column(length = 300)
    private String fileName;

    @NotNull
    @Column(length=50)
    private String fileType;

    @Override
    public String toString() {
        return "WarrantyGoodwillPhoto{" +
                "id=" + id +
                ", warrantyGoodwill=" + warrantyGoodwill +
                ", fileName='" + fileName + '\'' +
                '}';
    }


}
