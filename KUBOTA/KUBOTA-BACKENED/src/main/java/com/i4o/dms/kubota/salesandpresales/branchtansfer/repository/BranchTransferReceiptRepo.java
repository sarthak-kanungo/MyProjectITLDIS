package com.i4o.dms.kubota.salesandpresales.branchtansfer.repository;

import com.i4o.dms.kubota.salesandpresales.branchtansfer.domain.BranchTransferReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchTransferReceiptRepo extends JpaRepository<BranchTransferReceipt, Long> {
}
