package com.i4o.dms.itldis.warranty.logsheet.repository;

import com.i4o.dms.itldis.warranty.logsheet.domain.WarrantyLogsheetPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyLogsheetPhotoRepo extends JpaRepository<WarrantyLogsheetPhotos,Long> {
}
