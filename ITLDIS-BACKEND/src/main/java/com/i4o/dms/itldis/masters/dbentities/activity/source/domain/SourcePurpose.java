package com.i4o.dms.itldis.masters.dbentities.activity.source.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SA_MST_ENQUIRY_SOURCE_PURPOSE")
public class SourcePurpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String purpose;
}
