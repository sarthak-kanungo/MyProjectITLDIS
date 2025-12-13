package com.i4o.dms.kubota.masters.dbentities.enquiry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "enquiry_purpose_of_purchase")
public class PurposeOfPurchase
{
@Id
private  Long id;
@Column(length = 50,nullable = false)
@NotBlank(message = "purpose of purchase can not be null")
private String purposeOfPurchase;
@Column(length = 5,nullable = false)
@NotBlank(message = "marketingactivity status can not be null")
private String activeStatus="Y";


}
