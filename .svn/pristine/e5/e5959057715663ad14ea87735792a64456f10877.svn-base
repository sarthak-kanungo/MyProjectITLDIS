import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class LogSheetApi {
    
    private static readonly module = 'warranty';
    private static readonly controller1 = 'pcr';
    private static readonly controller = 'logsheet';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${LogSheetApi.module}/${LogSheetApi.controller}`;

    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly autoCompleteChassisNoInJobCard = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompleteChassisNoInJobCard`;
    static readonly getChassisDetailsByChassisNoInJobCard = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/getChassisDetailsByChassisNoInJobCard`;
    static readonly dropDownLogsheetType = `${LogSheetApi.apiController}/dropDownLogsheetType`;
    static readonly searchAutoCompleteJobCode = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/searchAutoCompleteJobCode`;
    static readonly autoCompletePartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/autoCompletePartNumber`; 
    static readonly getPartDetailsByPartNumber = `${environment.baseUrl}/${environment.api}${urlService.service}${urlService.jobcard}/getPartDetailsByPartNumber`;   
    static readonly saveWarrantyLogsheet = `${LogSheetApi.apiController}/saveWarrantyLogsheet`;
    static readonly logsheetSearch = `${LogSheetApi.apiController}/logsheetSearch`;
    static readonly logsheetClose = `${LogSheetApi.apiController}/close`;
    static readonly searchLogsheetNo = `${LogSheetApi.apiController}/searchLogsheetNo`;
    static readonly searchCustomerMobileNo = `${LogSheetApi.apiController}/searchCustomerMobileNo`;
    static readonly lookupUrl = `${environment.baseUrl}/${environment.api}/common/syslookup/lookupByCode`;
    static readonly warrantyLogsheetViewByLogsheetNo = `${LogSheetApi.apiController}/warrantyLogsheetViewByLogsheetNo`;
    static readonly getPartFailureCodeByCode = `${environment.baseUrl}/${environment.api}/warranty/getPartFailureCodeByCode`; 
   
    static readonly getPcrNumberByJobCardId = `${environment.baseUrl}/${environment.api}${urlService.warranty}/pcr/getPcrNumberByJobCardId`;
    static readonly  downloadExcel=`${LogSheetApi.apiController}/downloadLogsheetExcelReport`;
}