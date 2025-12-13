import { urlService } from 'src/app/webservice-config/baseurl';
import { environment } from '../../../../../environments/environment';

export abstract class SparePoApi {
    private static readonly module = 'spares';
    private static readonly dbModule = 'dbEntities';
    private static readonly controller = 'purchaseOrder';
    private static readonly transitreport = 'transitreport';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SparePoApi.module}/${SparePoApi.controller}`;
    static readonly dbEntityController = `${environment.baseUrl}/${environment.api}/${SparePoApi.module}/${SparePoApi.dbModule}`;

    static readonly saveSparePurchaseOrder = `${SparePoApi.apiController}/saveSparePurchaseOrder`;
    static readonly getPurchaseOrderNumberAutoComplete = `${SparePoApi.apiController}/getPurchaseOrderNumberAutoComplete`;
    static readonly searchPurchaseOrder = `${SparePoApi.apiController}/searchPurchaseOrder`;
    static readonly searchPurchaseOrderCount = `${SparePoApi.apiController}/searchPurchaseOrderCount`;
    static readonly getSupplierNameAutoComplete = `${SparePoApi.dbEntityController}/getSupplierNameAutoComplete`;
    static readonly getVendorNameAutoComplete = `${SparePoApi.dbEntityController}/getVendorNameAutoComplete`;
    // static readonly getOrderPlanningComplete = `${SparePoApi.apiController}/`;
    static readonly getJobCardAutoComplete = `${SparePoApi.apiController}/getJobCardAutocomplete`;
    static readonly downloadSparePOReportExcelUrl = `${SparePoApi.apiController}/downloadSparePurchaseReportExcel`;
    static readonly downloadSparePurchaseDetailReport = `${SparePoApi.apiController}/downloadSparePurchaseDetailReport`;

    static readonly getSalesTypeDropdown = `${SparePoApi.dbEntityController}/getSalesTypeDropdown`;
    static readonly getOrderTypeDropdown = `${SparePoApi.dbEntityController}/getOrderTypeDropdown`;
    static readonly getTransferModeDropdown = `${SparePoApi.dbEntityController}/getTransferModeDropdown`;
    static readonly getTransporterDropdown = `${SparePoApi.dbEntityController}/getTransporterDropdown`;
    static readonly getDealerOutStandingDetails = `${SparePoApi.apiController}/getDealerOutStandingDetails`;
    static readonly getItemNumberAutoComplete = `${SparePoApi.apiController}/getItemNumberAutoComplete`;
    static readonly getSupplierTypeDropdown = `${SparePoApi.apiController}/getSupplierTypeDropdown`;
    static readonly getOrderTypeBySupplierType = `${SparePoApi.apiController}/getOrderTypeBySupplierType`;
    static readonly getSparePriceTypeDropdown = `${SparePoApi.dbEntityController}/getSparePriceTypeDropdown`;
    static readonly getItemDetailsByItemId = `${SparePoApi.apiController}/getItemDetailsByItemId`;
    static readonly getSparePurchaseOrderById = `${SparePoApi.apiController}/getSparePurchaseOrderById`;
    static readonly approveSparePurchaseOrderById = `${SparePoApi.apiController}/approveSparePurchaseOrder`;
    static readonly getDealerCodeAutocomplete = `${SparePoApi.apiController}/getDealerCodeAutocomplete`;
    static readonly getItemDetailsByJobCardId = `${SparePoApi.apiController}/getItemDetailsByJobCardId`;
    static readonly getPurchaseOrderStatusDropdown = `${SparePoApi.dbEntityController}/getPurchaseOrderStatusDropdown`;
    static readonly getSparePoType = `${SparePoApi.dbEntityController}/getSparePoType`;
    static readonly getTransitReport = `${environment.baseUrl}/${environment.api}/${SparePoApi.module}/${SparePoApi.transitreport}/getTransitReport`;
    static readonly downloadTransitReportExcelUrl =`${environment.baseUrl}/${environment.api}/${SparePoApi.module}/${SparePoApi.transitreport}/downloadTransitReportExcel`;
    static readonly getTransitReportItems = `${environment.baseUrl}/${environment.api}/${SparePoApi.module}/${SparePoApi.transitreport}/getTransitReportItems`;
    // AutoSearch Order Planning Sheet No
    static readonly autoSearchOpNo=`${SparePoApi.apiController}/autoGetOPSnumber`;

    // get details for Item No
    static readonly getItemDetailsBaseonOp= `${SparePoApi.apiController}/getOPSitemsDeatail`
    

}