import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class PcrApi {
    
    private static readonly module = 'warranty';
    private static readonly controller = 'pcr';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${PcrApi.module}/${PcrApi.controller}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`
    static readonly dropDownFieldCondition = `${PcrApi.apiController}/dropDownFieldCondition`;
    static readonly dropDownFailureType = `${PcrApi.apiController}/dropDownFailureType`;
    static readonly dropDownCropCondition = `${PcrApi.apiController}/dropDownCropCondition`;
    static readonly dropDownStatus = `${PcrApi.apiController}/dropDownPcrStatus`;
    static readonly dropDownImplCategory = `${PcrApi.apiController}/dropDownImplCategory`;
    static readonly autoCompletePartFailureCode = `${environment.baseUrl}/${environment.api}/${PcrApi.module}/autoCompletePartFailureCode`;
    static readonly getSoilType = `${environment.baseUrl}/${environment.api}${urlService.salesandpresales}${urlService.enquiry}/getSoilType`;
    static readonly getMajorCropGrown = `${environment.baseUrl}/${environment.api}${urlService.salesandpresales}${urlService.enquiry}/getMajorCropGrown`;
    static readonly jobCardForPcr = `${PcrApi.apiController}/jobCardForPcr`;
    static readonly serviceHistory = `${PcrApi.apiController}/serviceHistory`;
    static readonly enableGoodwill = `${PcrApi.apiController}/pcrEnableGoodwill`;
    static readonly saveWarrantyPcr = `${PcrApi.apiController}/saveWarrantyPcr`;
    static readonly searchWarrantyPcr = `${PcrApi.apiController}/searchWarrantyPcr`;
    static readonly pcrSpecialApproval = `${PcrApi.apiController}/pcrSpecialApproval`;
    static readonly warrantyPcrView = `${PcrApi.apiController}/warrantyPcrView`;
    static readonly warrantyPcrGoodwill = `${PcrApi.apiController}/warrantyPcrGoodwill`;
    static readonly updatePcrFromJobcard = `${PcrApi.apiController}/updatePcrFromJobcard`;
    static readonly approveWarrantyPcr = `${PcrApi.apiController}/approveWarrantyPcr`;
    static readonly autoCompletePcrNo = `${PcrApi.apiController}/autoCompletePcrNo`;
    static readonly autoCompleteSearchChassisNo = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompleteSearchChassisNo`;
    static readonly searchAutoCompleteEngineNo = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteEngineNo`;
    static readonly searchAutoCompleteJobCode = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteJobCode`;
    static readonly dropDownModel = `${environment.baseUrl}/${environment.api}/jobcard/dropDownModel`;
    static readonly getSystemDateUrl = `${environment.baseUrl}/${environment.api}/getSystemGeneratedDate`;
    static readonly updatePcr = `${PcrApi.apiController}/updatePcr`;
    static readonly syslookupByCode = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`;
    static readonly getAllProduct = `${environment.baseUrl}${urlService.api}/master/product/getAllProduct`
    static readonly getSeriesByProduct = `${environment.baseUrl}${urlService.api}/master/product/getSeriesByProduct`
    static readonly getModelBySeries = `${environment.baseUrl}${urlService.api}/master/product/getModelBySeries`
    static readonly getSubModelByModel = `${environment.baseUrl}${urlService.api}/master/product/subModelDropdown`
    static readonly getVariantBySubModel = `${environment.baseUrl}${urlService.api}/master/product/getVariantBySubModel`
    static readonly getItemNoAutoComplete = `${environment.baseUrl}${urlService.api}/service/jobcard/autoCompletePartNumber`;

  
    static readonly downloadPcrExcelReport = `${PcrApi.apiController}/downloadPcrExcelReport`;
}
