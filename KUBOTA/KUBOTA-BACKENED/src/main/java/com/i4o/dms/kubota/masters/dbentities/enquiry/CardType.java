package com.i4o.dms.kubota.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "CM_MST_credit_card_type")
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardType;
}
