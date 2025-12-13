package com.i4o.dms.itldis.masters.dbentities.purchase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class MachineDescripancyComplaintType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "complaint status cannot empty")
    @NotNull
    @Column(length = 50)
    private String complaintType;

}
