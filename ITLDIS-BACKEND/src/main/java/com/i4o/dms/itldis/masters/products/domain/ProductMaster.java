package com.i4o.dms.itldis.masters.products.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "MODEL_PRODUCT_MASTER")
public class ProductMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "product can't be blank")
    @Column(unique = true)
    private String product;

//    @ManyToMany(mappedBy = "targetedProducts")
//    private List<ServiceActivityProposal> serviceActivityProposals = new ArrayList<>();
}
