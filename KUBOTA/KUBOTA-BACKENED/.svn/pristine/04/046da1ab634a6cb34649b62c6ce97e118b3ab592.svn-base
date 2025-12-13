package com.i4o.dms.kubota.spares.purchase.blockpartsforsale.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.spares.purchase.blockpartsforsale.dto.BlockPartsForSaleSearchRespose;

public interface BlockPartsForSaleRepo extends JpaRepository<SparePartMaster, Long>{
	
	@Query(value = "{call SP_GET_PARTS_NO_BY_PART_NO_DESC(:partNoStr)}", nativeQuery = true)
    List<Map<String, Object>> getPartsNoByPartNo(@Param("partNoStr") String partNoStr);
	
	@Query(value = "{call SP_BLOCK_PART_FOR_SALE_SEARCH(:partNo, :status, :page, :size)}", nativeQuery = true)
	List<BlockPartsForSaleSearchRespose> blockPartsforSaleSearch(
			@Param("partNo") String partNo,
			@Param("status") String status,
			@Param("page") Integer page,
			@Param("size") Integer size);
	
	@Query(value = "SELECT id as id, itemNo as partNo, itemDescription as partDescription "
			+ "FROM SparePartMaster where itemNo=:partNo")
    Map<String, Object> partDetailsByPartNo(@Param("partNo") String partNo);
}
