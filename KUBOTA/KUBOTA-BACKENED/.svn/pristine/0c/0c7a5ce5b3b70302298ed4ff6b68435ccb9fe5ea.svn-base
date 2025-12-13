package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.repository;

import com.i4o.dms.kubota.masters.dbentities.salespresales.sales.deliverychallan.InsuranceCompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InsuranceCompanyMasterRepository extends JpaRepository<InsuranceCompanyMaster,Long> {

    InsuranceCompanyMaster findByCompanyCode(@Param("companyCode") String companyCode);

    @Query(value = "{call sp_getCompanyCode_autoComplete(:companyCode)}",nativeQuery = true)
    List<Map<String,Object>> getCompanyCodeAutoComplete(@Param("companyCode")String companyCode);

}
