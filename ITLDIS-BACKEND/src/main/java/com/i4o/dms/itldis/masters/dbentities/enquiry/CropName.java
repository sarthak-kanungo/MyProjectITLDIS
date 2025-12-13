package com.i4o.dms.itldis.masters.dbentities.enquiry;

import javax.persistence.*;

@Entity
@Table(name = "CM_MST_CROP")
public class CropName
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropName;
}
