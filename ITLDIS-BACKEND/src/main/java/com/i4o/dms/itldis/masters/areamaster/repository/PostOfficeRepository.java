package com.i4o.dms.itldis.masters.areamaster.repository;

import com.i4o.dms.itldis.masters.areamaster.model.PostOffices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostOfficeRepository extends JpaRepository<PostOffices, Long> {
    PostOffices findByPostOffice(String postOffice);
}
