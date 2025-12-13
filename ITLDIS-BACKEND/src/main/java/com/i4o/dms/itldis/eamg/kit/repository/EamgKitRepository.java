package com.i4o.dms.itldis.eamg.kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.eamg.kit.domain.EamgKit;

/**
 * EAMG Kit Repository
 */
@Repository
public interface EamgKitRepository extends JpaRepository<EamgKit, Long> {
    EamgKit findByKitNo(String kitNo);
}
