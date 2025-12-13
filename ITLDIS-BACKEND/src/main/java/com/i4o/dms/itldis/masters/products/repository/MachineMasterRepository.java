package com.i4o.dms.itldis.masters.products.repository;


import com.i4o.dms.itldis.masters.products.domain.MachineMaster;
import com.i4o.dms.itldis.masters.products.dto.ItemSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MachineMasterRepository extends JpaRepository<MachineMaster, Integer> {
    @Query(value = "{call sp_getByItemNo(:itemNo,:requestFrom,:depot,:branchId)}", nativeQuery = true)
    Map<String, Object> getByItemNo(@Param("itemNo") String itemNo, @Param("requestFrom") String requestFrom, @Param("depot") String depot, @Param("branchId")Long branchId);

    @Query(value = "{call sp_getExchangeBrand()}", nativeQuery = true)
    List<Map<String, Object>> getExchangeBrand();

    @Query(value = "{call sp_getSubModel(:model)}", nativeQuery = true)
    List<Map<String, Object>> getSubModel(@Param("model") String model);

    @Query(value = "{call sp_getModel(:model)}", nativeQuery = true)
    List<Map<String, Object>> getModel(@Param("model")  String model);

    @Query(value = "{call sp_getVariant(:model,:subModel)}", nativeQuery = true)
    List<Map<String, Object>> getVariant(@Param("model") String model,@Param("subModel") String subModel);

    @Query(value = "{call sp_getItemNo(:itemNo)}", nativeQuery = true)
    List<Object> getItemNo(@Param("itemNo") String itemNo);

    @Query(value = "{call sp_getAllModel()}", nativeQuery = true)
    List<Map<String, Object>> getAllModel();

    @Query(value = "{call sp_getAllSub_Model()}", nativeQuery = true)
    List<Map<String, Object>> getAllSubModel();

    @Query(value = "{call sp_getAll_Variant()}", nativeQuery = true)
    List<Map<String, Object>> getAllVariant();

    @Query(value = "{call sp_getAllProduct()}", nativeQuery = true)
    List<Map<String, Object>> getAllProduct();

    //product without id
    @Query(value = "{call sp_getProduct()}", nativeQuery = true)
    List<Map<String, Object>> getProduct();

    @Query(value = "{call sp_getAllSeries()}", nativeQuery = true)
    List<Map<String, Object>> getAllSeries();

    @Query(value = "{call sp_getVariantByModel(:model)}", nativeQuery = true)
    List<Map<String, Object>> getVariantByModel(String model);

    MachineMaster findByItemNo(String itemNo);

    @Query(value = "{call sp_getProductByGroup(:prodGroup)}", nativeQuery = true)
    List<Map<String, Object>> getProductByGroup(@Param("prodGroup") String prodGroup);
    
    @Query(value = "{call sp_getSeriesByProductId(:product)}", nativeQuery = true)
    List<Map<String, Object>> getSeriesByProductId(String product);

    @Query(value = "{call sp_getModelBySeriesId(:series)}",nativeQuery = true)
    List<Map<String,Object>> getModelBySeriesId(String series);

    @Query(value="{call sp_getSubModelByModelId(:model)}",nativeQuery = true)
    List<Map<String,Object>> getSubModelByModelId(String model);

    @Query(value="{call sp_getVariantBySubModelId(:subModel)}",nativeQuery = true)
    List<Map<String,Object>> getVariantBySubModel(String subModel);

  /*  @Query(value = "{call sp_getSeriesByProductId(:productName)}", nativeQuery = true)
    List<Map<String, Object>> getSeriesByProductId(@Param("productName") String productName);

    @Query(value = "{call sp_getModelBySeriesId(:id)}",nativeQuery = true)
    List<Map<String,Object>> getModelBySeriesId(@Param("id") String id);

    @Query(value="{call sp_getSubModelByModelId(:id)}",nativeQuery = true)
    List<Map<String,Object>> getSubModelByModelId(@Param("id") String id);

    @Query(value="{call sp_getVariantBySubModelId(:id)}",nativeQuery = true)
    List<Map<String,Object>> getVariantBySubModel(@Param("id")String id);*/


    @Query(value="{call sp_getSeriesAndProduct(:model)}",nativeQuery = true)
    Map<String,Object> getSeriesAndProduct(@Param("model")String model);

    @Query(value="{call sp_master_product_itemMaster_getItemByModelSubModelVariant(:model,:subModel,:variant)}",nativeQuery = true)
   List<Map<String,Object>>getItemByModelSubModelVariant(@Param("model")String model,@Param("subModel")String subModel,@Param("variant") String variant);

    @Query(value="{call sp_master_getSeriesByProduct(:product)}",nativeQuery = true)
    List<Map<String,Object>> getSeriesByProduct(@Param("product") String product);

    @Query(value="{call sp_master_getModelBySeries(:series)}",nativeQuery = true)
    List<Map<String,Object>> getModelBySeries(@Param("series") String series);

    @Query( value = "{call sp_itemNo_auto(:itemNo)}",nativeQuery = true)
    List<Map<String,Object>> findByItemNoContaining(@Param("itemNo") String itemNo);

    @Query(value="{call sp_mastar_getItemNumberModelProductSeries(:search)}", nativeQuery = true)
    List<Map<String,Object>> getItemNumberModelProductSeries(@Param("search") String search);

    @Query(value = "{call sp_getSearchItemMaster(:itemNo,:model,:product,:series,:subModel,:variant,:page,:size)}",nativeQuery = true)
    List<ItemSearchResponse> searchItem(@Param("itemNo") String itemNo,
                                        @Param("model") String model,
                                        @Param("product")String product,
                                        @Param("series")String series,
                                        @Param("subModel")String subModel,
                                        @Param("variant")String variant,
                                        @Param("page")Integer page, @Param("size")Integer size);
    //count
    @Query(value = "{call sp_getSearchItemMasterCount(:itemNo,:model,:product,:series,:subModel,:variant,:page,:size)}",nativeQuery = true)
    Long searchItemCount (@Param("itemNo") String itemNo,
                             @Param("model") String model,
                             @Param("product")String product,
                             @Param("series")String series,
                             @Param("subModel")String subModel,
                             @Param("variant")String variant,
                             @Param("page")Integer page,
                             @Param("size")Integer size);

    @Query(value="{call sp_getAutoCompleteItemNo(:itemNo,:productGroup,:functionality, :machineType)}",nativeQuery = true)
    List<Map<String,Object>> getAutoCompleteItemNo(@Param("itemNo") String itemNo,@Param("productGroup") String productGroup,@Param("functionality")String functionality, @Param("machineType")String machineType);

    @Query(value="{call sp_master_get_variant_by_sub_model(:subModel)}",nativeQuery = true)
    List<Map<String,Object>> getVariantBySubModels(@Param("subModel") String subModel);

    @Query(value = "{call sp_service_ri_dropdown_machine_series()}", nativeQuery = true)
    List<Map<String, Object>> getSeries();

    @Query(value="{call sp_sales_po_create_autocomplete_item_no(:itemNo)}",nativeQuery = true)
    List<Map<String,Object>> getMachineItemNo(@Param("itemNo") String itemNo);
}
