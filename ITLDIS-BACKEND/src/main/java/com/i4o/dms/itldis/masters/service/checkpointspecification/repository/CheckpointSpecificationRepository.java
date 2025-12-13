package com.i4o.dms.itldis.masters.service.checkpointspecification.repository;

import com.i4o.dms.itldis.masters.service.checkpointspecification.domain.CheckpointSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CheckpointSpecificationRepository extends JpaRepository<CheckpointSpecification,Long> {

    @Query(value = "{call sp_service_checkpoint_specification_by_checkpointId(:checkpointId)}",nativeQuery = true)
    List<Map<String,Object>> getSpecificationList(@Param("checkpointId") Integer checkpontId);
}
