package com.i4o.dms.kubota.warranty.logsheet.repository;

import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheetPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarrantyLogsheetPhotoRepo extends JpaRepository<WarrantyLogsheetPhotos,Long> {
}
