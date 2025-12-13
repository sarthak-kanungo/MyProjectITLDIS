package com.i4o.dms.itldis.masters.dbentities.enquiry;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "SA_ENQ_MST_ENQUIRY_STATUS")
public class EnquiryStatus
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long id;
  @Column(length = 50,nullable = false)
  @NotBlank(message = "enquiryStatus can not be blank")
  private String enquiryStatus;


}
