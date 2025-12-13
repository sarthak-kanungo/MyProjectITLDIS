package com.i4o.dms.kubota.service.activityreport.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="SV_ACTIVITY_REPORT_PHOTOS")
public class ServiceActivityReportPhotos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_activity_report_id")
    private ServiceActivityReport serviceActivityReport;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @Override
    public String toString() {
        return "ServiceActivityReportPhotos{" +
                "id=" + id +
                ", serviceActivityReport=" + serviceActivityReport +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
