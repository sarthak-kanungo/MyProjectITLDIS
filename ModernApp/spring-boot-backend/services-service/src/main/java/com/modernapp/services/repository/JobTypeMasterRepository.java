package com.modernapp.services.repository;

import com.modernapp.services.model.JobTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobTypeMasterRepository extends JpaRepository<JobTypeMaster, String> {

    @Query("SELECT j FROM JobTypeMaster j WHERE j.isActive = 'Y' AND j.freeService = 'Y' ORDER BY j.seqNo ASC")
    List<JobTypeMaster> findAllActiveFreeServices();
}
