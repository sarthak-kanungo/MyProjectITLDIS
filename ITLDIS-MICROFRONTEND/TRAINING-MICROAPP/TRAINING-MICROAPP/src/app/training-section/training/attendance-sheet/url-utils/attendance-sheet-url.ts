import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class TrainingApi {
    private static readonly module = 'training';
    private static readonly controller = 'itldisemployee';
    static readonly getempMasterdepartmentForOrgHier = `${environment.baseUrl}/${environment.api}${urlService.itldisemployee}/getempMasterdepartmentForOrgHier`
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TrainingApi.module}/${TrainingApi.controller}`;
    static readonly staticPath = `${environment.baseUrl}${urlService.api}${urlService.staticPath}`
    // static readonly getempMasterdepartmentForOrgHier = `${TrainingApi.apiController}/getempMasterdepartmentForOrgHier`;
   
}
