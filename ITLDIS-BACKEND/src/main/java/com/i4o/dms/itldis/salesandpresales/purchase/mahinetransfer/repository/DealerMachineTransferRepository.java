package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.repository;

import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto.MachineTransfer;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto.MachineTransferList;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.domain.DealerMachineTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DealerMachineTransferRepository extends JpaRepository<DealerMachineTransfer,Long> {

    @Query(value = "{call sp_salesAndPresales_getCoDealer(:id,:code,:dealerCode)}",nativeQuery = true)
    List<Map<String,Object>> getCoDealer(@Param("id") Long id, @Param("code") String code, @Param("dealerCode") String dealerCode);


    @Query(value = "{call sp_sales_and_presales_getItemInMachineTransfer(:itemNumber)}",nativeQuery = true)
    Map<String,Object> getItemInMachineTransfer(@Param("itemNumber") String itemNumber);

    @Query(value = "{call sp_sales_getMachineTransferStatus()}",nativeQuery = true)
    List<Map<String,Object>> getMachineTransferStatus();

    @Query(value = "{call sp_sales_searchMachineTransfer(:managementAccess,:dealerId,:kaiEmployeeId,:dealerEmployeeId,:requestNumber,:requestStatus,:product,:series,:model,:subModel,:variant,:itemNo,:fromDate,:toDate,:usercode,:page,:size)}", nativeQuery = true)
    List<MachineTransferList> searchMachineTransfer(
            @Param("managementAccess") Boolean managementAccess,
            @Param("dealerId") Long dealerId,
            @Param("kaiEmployeeId") Long kaiEmployeeId,
            @Param("dealerEmployeeId") Long dealerEmployeeId,

                                                   @Param("requestNumber") String requestNumber,
                                                    @Param("requestStatus") String requestStatus,
                                                    @Param("product") String product,
                                                    @Param("series") String series,
                                                    @Param("model") String model,
                                                    @Param("subModel") String subModel,
                                                    @Param("variant") String variant,
                                                    @Param("itemNo") String itemNo,
                                                    @Param("fromDate") String fromDate,
                                                    @Param("toDate") String toDate, @Param("usercode") String usercode,
                                                    @Param("page") Integer page,
                                                    @Param("size") Integer size);

    @Query(value = "{call sp_sales_getDealerCodeAndDealerName(:userId)}",nativeQuery = true)
   Map<String,Object> getDealerCodeAndDealerName(@Param("userId") Long userId);

    @Query(value = "{call sp_purchase_getTrasnferFromDealerCode(:dealerId,:code)}",nativeQuery = true)
   List<Map<String,Object>> getTrasnferFromDealerCode(@Param("dealerId") Long dealerId, @Param("code") String code);


    @Query(value = "{call sp_sales_getReceiptNumber(:receiptNumber, :usercode)}",nativeQuery = true)
    List< Map<String,Object>> searchReceiptNumber(@Param("receiptNumber") String receiptNumber, @Param("usercode") String usercode);




    MachineTransfer findByRequestNumber(String requestNumber);

    @Query(value = "{call sp_sales_presales_machine_transfer_request_getRequestNumberAfterMachineTranfer(:id)}",nativeQuery = true)
    Map<String,Object> getRequestNumberAfterSubmitMachineTransfer(@Param("id") Long id);


    @Query(value = "{call sp_machine_transfer_request_for_allotment(:requestNo, :dealerId)}",nativeQuery = true)
    List<Map<String,Object>> getRequestForAllotment(@Param("requestNo")String requestNo, @Param("dealerId")Long dealerId);
    
    @Query(value = "{call sp_machine_transfer_request_dealer_details_for_allotment(:requestNo, :dealerId)}",nativeQuery = true)
    Map<String,Object> getRequestDealerDetailsForAllotment(@Param("requestNo")String requestNo, @Param("dealerId")Long dealerId);

    @Query(value = "{call sp_machine_transfer_request_machine_details_for_allotment(:requestNo, :dealerId, :branchId)}",nativeQuery = true)
    List<Map<String,Object>> getRequestMachineDetailsForAllotment(@Param("requestNo")String requestNo, @Param("dealerId")Long dealerId, @Param("branchId")Long branchId);
    
    @Query(value = "{call sp_machine_transfer_request_csb_details(:csbNo, :model, :dealerId)}",nativeQuery = true)
    List<Map<String,Object>> getCsbNoToTransfer(@Param("csbNo")String csbNo,@Param("model")String model, @Param("dealerId")Long dealerId);
}
