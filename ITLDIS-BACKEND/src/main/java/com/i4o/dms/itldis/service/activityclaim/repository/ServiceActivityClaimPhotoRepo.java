package com.i4o.dms.itldis.service.activityclaim.repository;

import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposalHeads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceActivityClaimPhotoRepo extends JpaRepository<ServiceActivityProposalHeads,Long> {
}
