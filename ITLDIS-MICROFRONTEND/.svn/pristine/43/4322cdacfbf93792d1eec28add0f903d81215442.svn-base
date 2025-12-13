import { environment } from '../../../../../environments/environment';


export abstract class SparesGrnUrl {
    private static readonly module = 'spares';
    private static readonly master = 'master';
    private static readonly controller = 'grn';
    private static readonly apiController = `${ environment.baseUrl }/${ environment.api }/${ SparesGrnUrl.module }/${ SparesGrnUrl.controller }`;

    static readonly getGrnStatus = `${ SparesGrnUrl.apiController }/getGrnStatus`;
    static readonly getGrnType = `${ SparesGrnUrl.apiController }/getGrnType`;
    static readonly getSupplierType = `${ SparesGrnUrl.apiController }/getSupplierType`;
    static readonly getStoresName = `${ SparesGrnUrl.apiController }/getStoresName`;
    static readonly getBinLocationByStoreId = `${ SparesGrnUrl.apiController }/getBinLocationByStoreId`;
    static readonly getSearchSparesInvoiceNo = `${ SparesGrnUrl.apiController }/getSearchSparesInvoiceNo`;
    static readonly getSparesInvoiceDetails = `${ SparesGrnUrl.apiController }/getSparesInvoiceDetails`;
    static readonly searchSupplierName = `${ SparesGrnUrl.apiController }/searchSupplierName`;
    static readonly downloadReport = `${ SparesGrnUrl.apiController }/downloadReport`;
    static readonly saveGrn = `${ SparesGrnUrl.apiController }/saveGrn`;
    static readonly searchSpareGrn = `${ SparesGrnUrl.apiController }/searchSpareGrn`;
    static readonly searchBySpareGrnNumber = `${ SparesGrnUrl.apiController }/searchBySpareGrnNumber`;
    static readonly searchInvoiceNumberFromGrn = `${ SparesGrnUrl.apiController }/searchInvoiceNumberFromGrn`;
    static readonly getSpareGrnByGrnId = `${ SparesGrnUrl.apiController }/getSpareGrnByGrnId`;
    static readonly searchPoNumberForGrn = `${ SparesGrnUrl.apiController }/searchPoNumberForGrn`;
    static readonly getSupplierDetailsBySupplierId = `${ SparesGrnUrl.apiController }/getSupplierDetailsBySupplierId`;
    static readonly getPoDetailsByPoNumberForGrn = `${ SparesGrnUrl.apiController }/getPoDetailsByPoNumberForGrn`;


    /* 
    Supplier Type - http://localhost:8383/api/spares/grn/getSupplierType
    Store Name - http://localhost:8383/api/spares/grn/getStoresName
Bin Location - http://localhost:8383/api/spares/grn/getBinLocationByStoreId?storeId=5&binLocation=a

    */
}