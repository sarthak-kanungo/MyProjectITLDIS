import { environment } from '../../../../../environments/environment'

export abstract class SoApi {
    private static readonly module = ''
    private static readonly controller = 'customerMaster'
    private static readonly masterModule = 'master'
    private static readonly areaMasterController = 'areamaster'
    private static readonly spareController = 'spares'
    private static readonly salesOrderController = 'salesOrder'
    private static readonly sparePartMasterController = 'sparePartMaster'
    
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SoApi.module}`
    static readonly autoCompleteCustomerNMobileN = `${environment.baseUrl}/${environment.api}/${SoApi.controller}/autocompleteMobileNumber`
    // GET /api/customerMaster/autocompleteMobileNumber
    static readonly getCustomerDetails = `${environment.baseUrl}/${environment.api}/${SoApi.controller}/getCustomerDetails`
    // GET /api/customerMaster/getCustomerDetails
    static readonly autocompleteQuotationNo = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getQuotationNumberAutocomplete`
    //localhost:8383/api/spares/salesOrder/getQuotationNumberAutocomplete?quotationNumber=1
    //localhost:8383/api/spares/quotation/getQuotationNumberAutocomplete?quotationNumber=1
    static readonly getSpareCustomerTypeDropdown = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/dbEntities/getSpareCustomerTypeDropdown`
    //localhost:8383/api/spares/dbEntities/getSpareCustomerTypeDropdown?isQuotation=true
    static readonly getCountry = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getCountry`
    // GET /api/master/areamaster/getCountry
    static readonly getStateAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getStateAutocomplete`
    // GET /api/master/areamaster/getStateAutocomplete
    static readonly getDistrictAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getDistrictAutocomplete`
    // GET /api/master/areamaster/getDistrictAutocomplete
    static readonly getCityAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getCityAutocomplete`
    // GET /api/master/areamaster/getCityAutocomplete
    static readonly getTehsilAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getTehsilAutocomplete`
    // GET /api/master/areamaster/getTehsilAutocomplete
    static readonly getPinCodeAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getPinCodeAutocomplete`
    // GET /api/master/areamaster/getPinCodeAutocomplete
    static readonly getPostOfficeAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.masterModule}/${SoApi.areaMasterController}/getPostOfficeAutocomplete`
    // GET /api/master/areamaster/getPostOfficeAutocomplete
    static readonly autocompletePartNo = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.sparePartMasterController}/autocompletePartNo`
    // GET /api/spares/sparePartMaster/autocompletePartNo
    //localhost:8383/api/spares/sparePartMaster/autocompletePartNo?itemNumber=12&itemId=13311%2C13312 
    static readonly getSparePartDetailsForQuotation = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.sparePartMasterController}/getSparePartDetailsForQuotation`
    // GET /api/spares/sparePartMaster/getSparePartDetailsForQuotation
    static readonly getItemDetailsByItemNumber = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getItemDetailsByItemNumber`
    // GET /api/spares/salesOrder/getItemDetailsByItemNumber
    static readonly getQuotationById = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/quotation/getQuotationByIdForSalesOrder`
    // GET /api/spares/quotation/getQuotationById/{id}
    // getQuotationByIdForSalesOrder
    static readonly saveSpareSalesOrder = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/saveSpareSalesOrder`
    // POST /api/spares/salesOrder/saveSpareSalesOrder
    static readonly getSalesOrderById = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getSalesOrderById`
    // GET /api/spares/salesOrder/getSalesOrderById/{id}
    static readonly search = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/search`
    // POST /api/spares/salesOrder/search
    static readonly getSalesOrderNumberAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getSalesOrderNumberAutocomplete`
    // GET /api/spares/salesOrder/getSalesOrderNumberAutocomplete
    static readonly getCustomerNameAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getCustomerNameAutocomplete`
    // GET /api/spares/salesOrder/getCustomerNameAutocomplete
    static readonly getRetailerOrMechanicAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getRetailerOrMechanicAutocomplete`
    // GET /api/spares/salesOrder/getRetailerOrMechanicAutocomplete
    static readonly getRetailerOrMechanicDetails = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getRetailerOrMechanicDetails`
    // GET /api/spares/salesOrder/getRetailerOrMechanicDetails
    static readonly getDealerCodeAutocomplete = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getDealerCodeAutocomplete`
    // GET /api/spares/salesOrder/getDealerCodeAutocomplete
    static readonly getDealerDetails = `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/getDealerDetails`
    
    static readonly deletePart= `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/deletePart`
    static readonly customerUploadExcel= `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/customerUploadExcel`
    static readonly spareSalesReportExcelUrl= `${environment.baseUrl}/${environment.api}/${SoApi.spareController}/${SoApi.salesOrderController}/downloadCustomerOrderSearchExcel`
//localhost:8383/api/spares/salesOrder/deletePart?partId=182,181
}