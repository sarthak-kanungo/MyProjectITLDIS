package com.i4o.dms.kubota.common.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="CM_SMSMAIL_MAIL")
public class MailEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long mailitemId;
	
	private Long eventId;
	
	private Integer referenceid;
	
	private String frommailid;
	
	private String tomailid;
	
	private String ccmailid;
	
	private String bccmailid;
	
	private String mailsubject;
	
	private String mailbodytxt;
	
	private String mailstatus;
	
	private Date mailsentdate;
	
    private Date createddate;
	
    private String createdby;
	
}
