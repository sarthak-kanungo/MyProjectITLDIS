package com.i4o.dms.kubota.crm.crmmodule.directsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.i4o.dms.kubota.crm.crmmodule.directsurvey.domain.CallAttempt;

public interface CallAttemptRepo extends JpaRepository<CallAttempt, Long> {

}
