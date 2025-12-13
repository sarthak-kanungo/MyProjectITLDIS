package com.i4o.dms.itldis.service.jobcard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.service.mrc.domain.ServiceMrc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="SV_JOBCARD_PHOTOS")
public class ServiceJobcardPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_jobcard_id")
    @JsonBackReference("serviceJobcardPhotos")
    private ServiceJobCard serviceJobCard;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @NotNull
    @Column(length=50)
    private String fileType;

    @Column(columnDefinition = "boolean default false",name ="delete_flag")
    private Boolean isSelected;


//    @Override
//    public String toString() {
//        return "ServiceMrcPhotos{" +
//                "id=" + id +
//                ", serviceMrc=" + serviceJobCard +
//                ", fileName='" + fileName + '\'' +
//                '}';
//    }
}
