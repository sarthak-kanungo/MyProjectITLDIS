import { environment } from '../../../../../environments/environment'

export abstract class CustomerMasterApi {
    private static readonly apiControllerPathForMaster = `${environment.baseUrl}/${environment.api}/master`
    private static readonly enquirycontroller = `${environment.baseUrl}/${environment.api}/salesandpresales/enquiry`
    private static readonly apiControllerPathForDealerMaster = `${environment.baseUrl}/${environment.api}/dealerMaster`
        
    private static readonly module = 'customerMaster'
    private static readonly controller = `${environment.baseUrl}/${environment.api}/${CustomerMasterApi.module}`
    static readonly submitSearchDto = `${CustomerMasterApi.controller}/customerMasterSearch`
    static readonly submitSearchApprovalDto = `${CustomerMasterApi.controller}/customerMasterApprovalSearch`
    static readonly getCustomerMaster = `${CustomerMasterApi.controller}/getCustomerMaster`
    static readonly getCustomerMasterChangeRequest = `${CustomerMasterApi.controller}/getCustomerMasterChangeRequest`
    static readonly submitCreateDto = `${CustomerMasterApi.controller}/addCustomerChangeRequest`
    static readonly submitChangeRequest = `${CustomerMasterApi.controller}/submitChangeRequest`
    static readonly customerCodeAuto = `${CustomerMasterApi.controller}/customerCodeAuto`
    static readonly mobileNumberAuto = `${CustomerMasterApi.controller}/autocompleteMobileNumber`
    static readonly createCustomerCodeAuto = ``
    static readonly validateMobileNumber = `${CustomerMasterApi.controller}/validateMobileNumber`
    static readonly customerTypeDropdown = `${CustomerMasterApi.enquirycontroller}/getProspectType`
    static readonly customerGroupAuto = ``
    static readonly titleDropdown = `${CustomerMasterApi.enquirycontroller}/dropDownTitle`
    static readonly qualificationDropdown = `${CustomerMasterApi.enquirycontroller}/getOccupation`
    static readonly preferrdeLanguageDropdown = `${CustomerMasterApi.enquirycontroller}/getLanguages`
    static readonly addressTypeDropdown = ``
    static readonly pinCodeAuto = ``
    static readonly localityDropdown = ``
    static readonly cropsGrownDropdown = `${CustomerMasterApi.enquirycontroller}/getMajorCropGrown`
    static readonly soilTypeDropdown = `${CustomerMasterApi.enquirycontroller}/getSoilType`
    static readonly manufacturerDropdown = `${CustomerMasterApi.enquirycontroller}/getBrands`
    static readonly modelAuto = `${CustomerMasterApi.apiControllerPathForMaster}/product/getModel`
    static readonly yearOfPurchaseAuto = ``
    static readonly serialNoAuto = ``
    static readonly getDealerRegionInfo = `${CustomerMasterApi.apiControllerPathForDealerMaster}/getDealerRegionInfo`
    static readonly autoCompleteTehsilCityPincode = `${CustomerMasterApi.apiControllerPathForDealerMaster}/autoCompleteTehsilCityPincode`
    static readonly getPincodeDetail = `${CustomerMasterApi.apiControllerPathForMaster}/areamaster/getByPinCode`
}