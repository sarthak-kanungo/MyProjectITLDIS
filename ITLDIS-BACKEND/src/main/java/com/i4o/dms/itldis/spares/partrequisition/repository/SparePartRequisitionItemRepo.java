package com.i4o.dms.itldis.spares.partrequisition.repository;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;

@Transactional
public interface SparePartRequisitionItemRepo extends JpaRepository<SparePartRequisitionItem,Long> {
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set pcr_approved_quantity=:qty, failure_code_id=:failureCode where id=:id ", nativeQuery = true)
	public Integer updatePartApproveQty(@Param("qty")Integer qty, @Param("id")Long id,@Param("failureCode")Long failureCode);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set pcr_approved_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateLabourApproveAmount(@Param("amount")Double amount, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set pcr_approved_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateOutsideChargeApproveAmount(@Param("amount")Double amount, @Param("id")Long id);
	
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set gw_claim_approved_quantity=:qty , gw_approved_percent=:perc, gw_claim_approved_amount=:approvedAmount, price_type=:priceType where id=:id ", nativeQuery = true)
	public Integer updateGoodwillPartApproveQty(@Param("qty")Integer qty, @Param("perc")Double perc, @Param("approvedAmount")Double approvedAmount, @Param("id")Long id, @Param("priceType")String priceType);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set goodwill_approved_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateGoodwillLabourApproveAmount(@Param("amount")Double amount, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set goodwill_approved_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateGoodwillOutsideChargeApproveAmount(@Param("amount")Double amount, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set pcr_quantity=:qty, warranty_pcr_id=:pcrId, failure_code_id=:failureId where id=:id ", nativeQuery = true)
	public Integer updatePartPcrQty(@Param("qty")Integer qty, @Param("pcrId")Long pcrId, @Param("failureId")Long failureId, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set pcr_amount=:amount, warranty_pcr_id=:pcrId where id=:id ", nativeQuery = true)
	public Integer updateLabourPcrAmount(@Param("amount")Double amount, @Param("pcrId")Long pcrId, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set pcr_amount=:amount, warranty_pcr_id=:pcrId where id=:id ", nativeQuery = true)
	public Integer updateOutsideChargePcrAmount(@Param("amount")Double amount, @Param("pcrId")Long pcrId, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set gw_claim_quantity=:qty, gw_id=:gwid, price_type=:priceType where id=:id ", nativeQuery = true)
	public Integer updatePartGoodwillClaimQty(@Param("qty")Integer qty, @Param("gwid")Long gwid, @Param("priceType") String priceType, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set goodwill_amount=:amount, gw_id=:gwid where id=:id ", nativeQuery = true)
	public Integer updateLabourGoodwillClaimAmount(@Param("amount")Double amount, @Param("gwid")Long gwid, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set goodwill_amount=:amount, gw_id=:gwid where id=:id ", nativeQuery = true)
	public Integer updateOutsideChargeGoodwillClaimAmount(@Param("amount")Double amount, @Param("gwid")Long gwid, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set pcr_wcr_id=:wcrid, pcr_wcr_claim_amount=:amount, pcr_wcr_claim_quantity=:qty where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimPartPcr(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("qty")Integer qty, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set pcr_wcr_id=:wcrid, pcr_wcr_claim_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimLabourPcr(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set pcr_wcr_id=:wcrid, pcr_wcr_claim_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimOutsideChargePcr(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set gw_wcr_id=:wcrid, gw_wcr_claim_amount=:amount, gw_wcr_claim_quantity=:qty where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimPartGoodwill(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("qty")Integer qty, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set gw_wcr_id=:wcrid, gw_wcr_claim_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimLabourGoodwill(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set gw_wcr_id=:wcrid, gw_wcr_claim_amount=:amount where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimOutsideChargeGoodwill(@Param("wcrid")Long wcrid, @Param("amount")Double amount, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set pcr_wcr_approved_amount=:amount, pcr_wcr_approved_quantity=:qty, pcr_wcr_claimable=:claimable, pcr_wcr_inspection_remark=:inspectionRemark, pcr_wcr_key_part_number=:keyPartNumber where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimPartApprovedPcr(@Param("amount")Double amount, @Param("qty")Integer qty, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("keyPartNumber")Boolean keyPartNumber, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set pcr_wcr_approved_amount=:amount, pcr_wcr_claimable=:claimable, pcr_wcr_inspection_remark=:inspectionRemark where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimLabourApprovedPcr(@Param("amount")Double amount, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set pcr_wcr_approved_amount=:amount, pcr_wcr_claimable=:claimable, pcr_wcr_inspection_remark=:inspectionRemark where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimOutsideChargeApprovedPcr(@Param("amount")Double amount, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("id")Long id);
	
	
	@Modifying
    @Query(value = "update SP_PART_REQUISITION_ITEM set gw_wcr_claim_approved_amount=:amount, gw_wcr_claim_approved_quantity=:qty, gw_wcr_claimable=:claimable, gw_wcr_inspection_remark=:inspectionRemark, gw_wcr_key_part_number=:keyPartNumber where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimPartApprovedGoodwill(@Param("amount")Double amount, @Param("qty")Integer qty, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("keyPartNumber")Boolean keyPartNumber, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_LABOUR_CHARGE_INFO set gw_wcr_claim_approved_amount=:amount, gw_wcr_claimable=:claimable, gw_wcr_inspection_remark=:inspectionRemark where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimLabourApprovedGoodwill(@Param("amount")Double amount, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("id")Long id);
	
	@Modifying
    @Query(value = "update SV_JC_OUTSIDE_CHARGE_INFO set gw_wcr_claim_approved_amount=:amount, gw_wcr_claimable=:claimable, gw_wcr_inspection_remark=:inspectionRemark where id=:id ", nativeQuery = true)
	public Integer updateWarrantyClaimOutsideChargeApprovedGoodwill(@Param("amount")Double amount, @Param("claimable")Boolean claimable, @Param("inspectionRemark")String inspectionRemark, @Param("id")Long id);
	
	
	@Query(value="select spmgst, spegst, spmrp, igst_percent from sp_part_master(nolock) s inner join SP_PART_REQUISITION_ITEM(nolock) i on i.spare_part_master_id=item_no where i.id=:id", nativeQuery = true)
	public Map<String, Object> getPartPrice(@Param("id")Long id);	
	
}
