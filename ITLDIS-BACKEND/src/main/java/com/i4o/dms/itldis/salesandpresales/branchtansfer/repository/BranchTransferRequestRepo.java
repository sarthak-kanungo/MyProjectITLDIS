package com.i4o.dms.itldis.salesandpresales.branchtansfer.repository;

import com.i4o.dms.itldis.salesandpresales.branchtansfer.domain.BranchTransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BranchTransferRequestRepo extends JpaRepository<BranchTransferRequest,Long> {

    @Query(value = "{call sp_get_branch_transfer_request_dropdown()}", nativeQuery = true)
    List<Map<String, Object>> getBranchTransferRequest();
}