package com.i4o.dms.itldis.common.model;

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
@Table(name="CM_SMSMAIL_EVENT_MST")
public class MailEventEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eventId;
	
	private String eventname;
}
