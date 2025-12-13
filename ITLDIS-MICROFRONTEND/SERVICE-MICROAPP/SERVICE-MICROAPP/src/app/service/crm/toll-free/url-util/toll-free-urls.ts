import { environment } from '../../../../../environments/environment';

export abstract class TollFreeCallUrls {

    private static readonly module = 'crm/crmmodule'
    private static readonly directsurvey = 'directsurvey'
    private static readonly customerCareExeCallController = 'customerCareExeCall'
    private static readonly controller = 'tollFreeCall'
    static readonly serveyApiController = `${environment.baseUrl}/${environment.api}/${TollFreeCallUrls.module}/${TollFreeCallUrls.directsurvey}`
    static readonly getDistrictList = `${environment.baseUrl}/${environment.api}/dealerMaster/getDealerRegionInfo`;
    static readonly customerCareExeCallApiController = `${environment.baseUrl}/${environment.api}/${TollFreeCallUrls.module}/${TollFreeCallUrls.customerCareExeCallController}`
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TollFreeCallUrls.module}/${TollFreeCallUrls.controller}`
    static readonly dealerDetails = `${TollFreeCallUrls.customerCareExeCallApiController}/dealerDetails`
    static readonly lookupurl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`
    static readonly villageautosearchurl = `${environment.baseUrl}/${environment.api}/dealerMaster/autoCompleteTehsilCityPincode`
    static readonly getPincodeDetailurl = `${environment.baseUrl}/${environment.api}/dealerMaster/getPincodeDetail`
    static readonly autocompleteMobileNumber = `${environment.baseUrl}/${environment.api}/salesandpresales/enquiry/getMobileNumber`
    static readonly getCustomerDetails = `${environment.baseUrl}/${environment.api}/customerMaster/getCustomerDetails`
    static readonly getCustomerMachineDetails = `${TollFreeCallUrls.customerCareExeCallApiController}/getCustomerMachineDetails`
    static readonly getCustomerServiceHistory = `${TollFreeCallUrls.customerCareExeCallApiController}/getCustomerServiceHistory`
    static readonly getComplaintReportingList = `${TollFreeCallUrls.customerCareExeCallApiController}/complaintReportingList`
    static readonly getCustomerCallHistory = `${TollFreeCallUrls.apiController}/getCallHistory`
    static readonly saveCallDetails = `${TollFreeCallUrls.apiController}/create`
    static readonly searchCallDetails = `${TollFreeCallUrls.apiController}/search`
    static readonly fetchCallDetails = `${TollFreeCallUrls.apiController}/fetchCallDetails`
    static readonly getTsmDetails = `${TollFreeCallUrls.apiController}/getTsmDetails`
    static readonly getDealerDetails = `${TollFreeCallUrls.apiController}/getDealerDetails`
    static readonly getDirectSurveyDetails = `${TollFreeCallUrls.serveyApiController}/getDirectSurveyDetails`
    static readonly tollFreeReportExportUrl = `${environment.baseUrl}/${environment.api}/crm/report/downloadTollFreeReport`
}

