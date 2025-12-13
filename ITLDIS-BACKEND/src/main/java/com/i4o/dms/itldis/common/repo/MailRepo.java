package com.i4o.dms.itldis.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.common.model.MailEntity;
import com.i4o.dms.itldis.connection.ConnectionConfiguration;

public interface MailRepo extends JpaRepository<MailEntity, Long>,ConnectionConfiguration  {

	List<MailEntity> findByMailstatus(@Param("mailstatus")String mailstatus);
}
