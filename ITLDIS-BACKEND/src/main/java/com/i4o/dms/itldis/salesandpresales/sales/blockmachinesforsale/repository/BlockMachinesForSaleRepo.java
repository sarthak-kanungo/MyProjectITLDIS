package com.i4o.dms.itldis.salesandpresales.sales.blockmachinesforsale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.i4o.dms.itldis.salesandpresales.sales.blockmachinesforsale.dto.BlockMachineForSaleSearchResponse;
import com.i4o.dms.itldis.masters.products.domain.MachineMaster;

public interface BlockMachinesForSaleRepo extends JpaRepository<MachineMaster, Long> {
	
	@Query(value = "{call SP_BLOCK_MACHINE_FOR_SALE_SEARCH(:product,:series,:model,:subModel,:variant,:page,:size)}", nativeQuery = true)
	public List<BlockMachineForSaleSearchResponse> getBlockMachinesForSaleSearch(
			@Param("product")String product,
			@Param("series")String series,
			@Param("model")String model,
			@Param("subModel")String subModel,
			@Param("variant")String variant,
			@Param("page")Integer page,
			@Param("size")Integer size);
	
}
