package com.i4o.dms.itldis.service.mrc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="SV_MRC_PHOTOS")
public class ServiceMrcPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_mrc_id")
    private ServiceMrc serviceMrcId;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @Override
    public String toString() {
        return "ServiceMrcPhotos{" +
                "id=" + id +
                ", serviceMrc=" + serviceMrcId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
