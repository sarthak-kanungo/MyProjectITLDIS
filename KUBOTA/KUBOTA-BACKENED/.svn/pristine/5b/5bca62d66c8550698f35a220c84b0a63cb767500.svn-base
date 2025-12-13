package com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.configurations.MessageConstants;
import com.i4o.dms.kubota.masters.areamaster.model.PinCode;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.domain.BranchDepotMaster;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" }, allowSetters = true)
@Entity
@Getter
@Setter
@Table(name = "ADM_BRANCH_PARTY_MASTER")
public class PartyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//    @NotBlank(message = "category can`t be blank")
	@Column(length = 50)
	private String category;

	@Column(length = 50)
	private String partyCode;

//    @NotBlank(message = "party Name can`t be blank")
	@Column(length = 50)
	private String partyName;

//    @NotBlank(message = "party Location can`t be blank")
	@Column(length = 50)
	private String partyLocation;

	@Column(length = 50)
	private String gstNumber;

	@Column(length = 50)
	private String panNumber;

	@Column(length = 50)
	private String adharCardNumber;

//    @NotBlank(message = "title can`t be blank")
	@Column(length = 50)
	private String title;

	@Column(length = 150)
	private String contactName;

	/*
	 * @NotBlank(message = "first Name can`t be blank")
	 * 
	 * @Column(length = 50) private String firstName;
	 * 
	 * @Column(length = 50) private String middleName;
	 * 
	 * @Column(length = 50) private String lastName;
	 */

	// need to discuss
	@Column(length = 50)
	private String designation;

//    @NotBlank(message = "mobile Number can`t be blank")
	@Column(length = 50)
	private String mobileNumber;

	@Column(length = 50)
	@Email(message = MessageConstants.EMAIL_NOT_VALID)
	private String email;

//    @NotBlank(message = "address1 can`t be blank")
	@Column(length = 50)
	private String address1;

	@Column(length = 50)
	private String address2;

	@Column(length = 50)
	private String address3;

//    @NotBlank(message = "pin Code can`t be blank")
	@Column(length = 50)
	private String pinCode;

//    @NotBlank(message = "locality can`t be blank")
	@Column(length = 50)
	private String locality;

	@Column(length = 50)
	private String country;

	@Column(length = 50)
	private String state;

	@Column(length = 50)
	private String Tehsil;

	// need to discuss
	@Column(length = 50)
	private String district;

	@Column(length = 50)
	private String city;

	@Column(length = 50)
	private String telephone;

	@Column(length = 50)
	private String postOffice;

	// need to discuss
	// @Email(message = MessageConstants.EMAIL_NOT_VALID)
	// private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(updatable = false)
	private Date createdDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date lastModifiedDate;

	@Column(updatable = false, nullable = false)
	private Long createdBy;

	@Column(updatable = false, nullable = false)
	private Long lastModifiedBy;

//    @ManyToOne
//    @JoinColumn(name = "branch_id")
//    private BranchDepotMaster branchDepotMaster;

	@Column(updatable = false)
	private Long branchId;

//    @ManyToOne
//    @JoinColumn(name = "party_category_id")
//    private ;

//    @ManyToOne
	@JoinColumn(name = "pin_id")
	private Long pinId;
	
	@JoinColumn(name = "party_category_id")
	private Long partyCategoryId;
}
