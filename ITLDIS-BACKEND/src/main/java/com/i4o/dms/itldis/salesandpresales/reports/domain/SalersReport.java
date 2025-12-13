package com.i4o.dms.itldis.salesandpresales.reports.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="")
public class SalersReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
