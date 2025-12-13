import { environment } from '../../../../../environments/environment';

export abstract class PartQuotationApi {
    private static readonly module = 'master'
    private static readonly controller = 'areamaster'
    private static readonly moduleForSpare = 'spares'
    private static readonly salesOrderController = 'salesOrder'
    private static readonly controllerForSpare = 'quotation'
    private static readonly controllerForSparePartMaster = 'sparePartMaster'
    private static readonly controllerForDbEntities = 'dbEntities'
    private static readonly controllerForCustomerMaster = 'customerMaster'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.module}/${PartQuotationApi.controller}`
    static readonly apiControllerForSpare = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.moduleForSpare}/${PartQuotationApi.controllerForSpare}`
    static readonly apiControllerForSalesOrder = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.moduleForSpare}/${PartQuotationApi.salesOrderController}`
    static readonly apiControllerForSparePartMaster = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.moduleForSpare}/${PartQuotationApi.controllerForSparePartMaster}`
    static readonly apiControllerForDbEntities = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.moduleForSpare}/${PartQuotationApi.controllerForDbEntities}`
    static readonly apiControllerForCustomerMaster = `${environment.baseUrl}/${environment.api}/${PartQuotationApi.controllerForCustomerMaster}`

    static readonly autocompleteMobileNumber = `${PartQuotationApi.apiControllerForCustomerMaster}/autocompleteMobileNumber`
    static readonly getCustomerDetails = `${PartQuotationApi.apiControllerForCustomerMaster}/getCustomerDetails`
    static readonly getSpareCustomerTypeDropdown = `${PartQuotationApi.apiControllerForDbEntities}/getSpareCustomerTypeDropdown`
    static readonly getCountry = `${PartQuotationApi.apiController}/getCountry`
    static readonly getStateAutocomplete = `${PartQuotationApi.apiController}/getStateAutocomplete`
    static readonly getDistrictAutocomplete = `${PartQuotationApi.apiController}/getDistrictAutocomplete`
    static readonly getTehsilAutocomplete = `${PartQuotationApi.apiController}/getTehsilAutocomplete`
    static readonly getCityAutocomplete = `${PartQuotationApi.apiController}/getCityAutocomplete`
    static readonly getPinCodeAutocomplete = `${PartQuotationApi.apiController}/getPinCodeAutocomplete`
    static readonly getSparesPinCodeAutocomplete = `${PartQuotationApi.apiController}/getSparesPinCodeAutocomplete`
    static readonly getPostOfficeAutocomplete = `${PartQuotationApi.apiController}/getPostOfficeAutocomplete`
    static readonly autocompletePartNo = `${PartQuotationApi.apiControllerForSparePartMaster}/autocompletePartNo`
    static readonly getSparePartDetailsForQuotation = `${PartQuotationApi.apiControllerForSparePartMaster}/getSparePartDetailsForQuotation`
    static readonly saveQuotation = `${PartQuotationApi.apiControllerForSpare}/saveQuotation`
    static readonly uploadExcelQuotation = `${PartQuotationApi.apiControllerForSpare}/uploadExcel`
    static readonly getQuotationSearch = `${PartQuotationApi.apiControllerForSpare}/getQuotationSearch`
    static readonly getQuotationNumberAutocomplete = `${PartQuotationApi.apiControllerForSpare}/getQuotationNumberAutocomplete`
    static readonly getCustomerNameAutocomplete = `${PartQuotationApi.apiControllerForSpare}/getCustomerNameAutocomplete`
    static readonly getQuotationById = `${PartQuotationApi.apiControllerForSpare}/getQuotationById`
    static readonly getRetailerOrMechanicAutocomplete = `${PartQuotationApi.apiControllerForSalesOrder}/getRetailerOrMechanicAutocomplete`
    static readonly getRetailerOrMechanicDetails = `${PartQuotationApi.apiControllerForSalesOrder}/getRetailerOrMechanicDetails`
}