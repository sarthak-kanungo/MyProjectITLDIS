import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class WcrApi {
    private static readonly module = 'warranty';
    private static readonly controller = 'Wcr';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${WcrApi.module}/${WcrApi.controller}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly pcrWarrantyClaim = `${WcrApi.apiController}/pcrWarrantyClaim`;
    static readonly goodwillWarrantyClaim = `${WcrApi.apiController}/goodwillWarrantyClaim`;
    static readonly saveWcr = `${WcrApi.apiController}/saveWcr`;
    static readonly approveWcr = `${WcrApi.apiController}/approveWarrantyWcr`;
    static readonly searchWcr = `${WcrApi.apiController}/searchWcr`;
    static readonly searchWcrType = `${WcrApi.apiController}/searchWcrType`;
    static readonly searchStatus = `${WcrApi.apiController}/searchStatus`;
    static readonly viewWarrantyWcr = `${WcrApi.apiController}/viewWarrantyWcr`;
    static readonly searchAutoCompleteWcrNo = `${WcrApi.apiController}/searchAutoCompleteWcrNo`;
    static readonly downloadWcrExcelReport = `${WcrApi.apiController}/downloadWcrExcelReport`;
    static readonly updateWcrAsReceived = `${WcrApi.apiController}/updateWcrAsReceived`;
    static readonly invoiceUploadUrl = `${WcrApi.apiController}/invoiceUpload`;
    static readonly kaiInvoiceUpload=`${WcrApi.apiController}/kaiInvoiceUpload`;
    static readonly invoiceDownloadUrl = `${WcrApi.apiController}/invoiceDownload`;
    static readonly invoiceVerifiedUrl = `${WcrApi.apiController}/invoiceVerify`;
    static readonly searchWcrCreditIssued = `${WcrApi.apiController}/searchWcrCreditIssued`;
    static readonly wcrFinalStatusUpdate = `${WcrApi.apiController}/wcrFinalStatusUpdate`;
    static readonly getFinalStatus = `${WcrApi.apiController}/getFinalStatus`;
    // Add getInvoiceDetails
    static readonly  getInvoiceDetail=`${WcrApi.apiController}/getInvoiceDetail`;

} 

