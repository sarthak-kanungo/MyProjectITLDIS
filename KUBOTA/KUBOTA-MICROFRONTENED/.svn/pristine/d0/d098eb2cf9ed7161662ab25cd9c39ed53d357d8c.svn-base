import { urlService } from "src/app/webservice-config/baseurl";
import { environment } from "src/environments/environment";

export abstract class spareDescripancy {
    private static readonly module = 'spares';
    private static readonly controller = 'purchase';
    private static readonly endPoint="discrepancyClaim"
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${spareDescripancy.module}/${spareDescripancy.controller}/${spareDescripancy.endPoint}`;
    static readonly claimType=`${spareDescripancy.apiController}/getClaimTypes`;
    static readonly autoSearchGrnNo = `${spareDescripancy.apiController}/autoGetDiscrepancyGrnNo`;
    static readonly grnDetails=`${spareDescripancy.apiController}/getHeaderGrnDetails`;
    static readonly autoSearchItemNo=`${spareDescripancy.apiController}/autoGetGrnItemsNo`;
    static readonly getItemDetails =`${spareDescripancy.apiController}/getGrnItemDiscrDetails`;
    static readonly createDescripancyClaim=`${spareDescripancy.apiController}/saveDiscrepancyClaim`;

    // Search
    static readonly searchSpareClaim=`${spareDescripancy.apiController}/searchDiscrepancyClaim`;
    static readonly autoseachClaimNo=`${spareDescripancy.apiController}/autoGetDiscrepancyClaimNo`;
    static readonly getClaimStatus=`${spareDescripancy.apiController}/getClaimStatus`;

    // view

   static readonly viewSpareClaimDescripancy =`${spareDescripancy.apiController}/getDiscrepancyClaimView`;
   static readonly printSpareDescripancyClaim=`${spareDescripancy.apiController}/printDMCReport`;
   static readonly approveorReject=`${spareDescripancy.apiController}/claimApproveReject`
   static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}` 
    
}