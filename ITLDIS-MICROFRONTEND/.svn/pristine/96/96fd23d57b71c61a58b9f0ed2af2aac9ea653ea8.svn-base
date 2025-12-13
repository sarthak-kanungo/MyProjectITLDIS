import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class KaiInspectionSheetApi {

    private static readonly module = 'warranty';
    private static readonly controller = 'kaiinspectionsheet';
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${KaiInspectionSheetApi.module}/${KaiInspectionSheetApi.controller}`;
    
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`;
    static readonly wcrDcForKaiInspectionSheet = `${KaiInspectionSheetApi.apiController}/WcrDcForKaiInspectionSheet`;
    static readonly saveKaiInspectionSheet = `${KaiInspectionSheetApi.apiController}/saveKaiInspectionSheet`;
    static readonly searchKaiInspectionSheet = `${KaiInspectionSheetApi.apiController}/searchKaiInspectionSheet`;
    static readonly viewKaiInspectionSheet = `${KaiInspectionSheetApi.apiController}/viewKaiInspectionSheet`;
    static readonly dropDownTypeOfUse = `${KaiInspectionSheetApi.apiController}/dropDownTypeOfUse`;
    static readonly dropDownFailureUnit = `${KaiInspectionSheetApi.apiController}/dropDownFailureUnit`;
    static readonly dropDownFailureMode = `${KaiInspectionSheetApi.apiController}/dropDownFailureMode`;
    
    static readonly autoCompleteInspectionNo = `${KaiInspectionSheetApi.apiController}/autoCompleteInspectionNo`;
    static readonly autoCompleteWcrNo = `${KaiInspectionSheetApi.apiController}/autoCompleteWcrNo`;
}