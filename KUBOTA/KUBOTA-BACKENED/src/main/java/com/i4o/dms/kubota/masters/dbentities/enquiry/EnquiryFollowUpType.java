package com.i4o.dms.kubota.masters.dbentities.enquiry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "SA_ENQ_MST_FOLLOWUP_TYPE")

public class EnquiryFollowUpType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50,nullable = false,name="follow_up_type")
    @NotBlank(message = "followUpType can be blank")
    private String followUpType;
}
