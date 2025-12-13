import { environment } from '../../../../../environments/environment';

export abstract class SourceApi {
    private static readonly module = 'salesandpresales'
    private static readonly controller = 'enquirySource'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${SourceApi.module}/${SourceApi.controller}`

    static readonly getPurpose = `${SourceApi.apiController}/getPurpose`
    static readonly saveEnquirySource = `${SourceApi.apiController}/SaveEnquirySource`
    static readonly searchEnquiryMaster = `${SourceApi.apiController}/searchEnquiryMaster`
    static readonly getSourceCodeAutocomplete = `${SourceApi.apiController}/getSourceCodeAutocomplete`
    static readonly getSourceNameAutocomplete = `${SourceApi.apiController}/getSourceNameAutocomplete`
    static readonly changeActiveStatus = `${SourceApi.apiController}/changeActiveStatus`
}//api/salesAndPrSales/enquirySource/changeActiveStatus?id=1