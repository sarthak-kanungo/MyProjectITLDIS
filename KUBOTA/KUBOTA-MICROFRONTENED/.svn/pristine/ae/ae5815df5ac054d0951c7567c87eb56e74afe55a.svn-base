
import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';
export abstract class SparesSalesInvoiceApi{
    private static readonly module = 'spares';
    private static readonly module1 = 'warranty';
    private static readonly dbModule = 'dbEntities';
    private static readonly controller = 'invoice';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SparesSalesInvoiceApi.module}/${SparesSalesInvoiceApi.controller}`;
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly dbEntityController = `${environment.baseUrl}/${environment.api}/${SparesSalesInvoiceApi.module}/${SparesSalesInvoiceApi.dbModule}`;
    static readonly getReferenceDocumentsTypes = `${SparesSalesInvoiceApi.dbEntityController}/getReferenceDocumentTypes`;
    static readonly getReferenceDocument = `${SparesSalesInvoiceApi.apiController}/getReferenceDocument`;
    static readonly documentAutoComplete = `${SparesSalesInvoiceApi.apiController}/documentAutoComplete`;
    static readonly saveInvoice = `${SparesSalesInvoiceApi.apiController}/saveInvoice`;
    static readonly getSpareInvoiceById = `${SparesSalesInvoiceApi.apiController}/getSpareInvoiceById`;
    static readonly searchSpareInvoice = `${SparesSalesInvoiceApi.apiController}/searchSpareInvoice`;
    static readonly cancelInvoice = `${SparesSalesInvoiceApi.apiController}/cancelInvoice`;
    static readonly getJobCardDetails = `${SparesSalesInvoiceApi.apiController}/getJobCardDetails`;
    static readonly getWcrDetails = `${SparesSalesInvoiceApi.apiController}/getWcrDetails`;
    static readonly getSparesInvoiceNumberAutocomplete = `${SparesSalesInvoiceApi.apiController}/getSparesInvoiceNumberAutocomplete`;
    static readonly downloadReportExcelUrl = `${SparesSalesInvoiceApi.apiController}/downloadPartSalesReport`;
    static readonly autoCompleteWcrNo = `${environment.baseUrl}/${environment.api}/${SparesSalesInvoiceApi.module1}/Wcr/searchAutoCompleteWcrNo`;
   // static readonly searchAutoCompleteJobCode = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteJobCode`;
   static readonly searchAutoCompleteJobCode = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteJobCode`;
}    