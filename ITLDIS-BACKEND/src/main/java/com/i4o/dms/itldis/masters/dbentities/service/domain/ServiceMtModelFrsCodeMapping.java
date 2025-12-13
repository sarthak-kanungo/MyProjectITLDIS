package com.i4o.dms.itldis.masters.dbentities.service.domain;

import com.i4o.dms.itldis.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class ServiceMtModelFrsCodeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_master_id")
    private ModelMaster modelMaster;

    @ManyToOne
    @JoinColumn(name = "frs_code_id",unique = true)
    private ServiceMtFrsCode serviceMtFrsCode;
}
