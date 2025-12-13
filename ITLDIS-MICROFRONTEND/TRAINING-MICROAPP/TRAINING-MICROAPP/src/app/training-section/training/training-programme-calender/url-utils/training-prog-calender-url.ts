import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class TrainingApi {
    private static readonly module = 'training';
    private static readonly controller = 'training-program-calender';
    static readonly getempMasterdepartmentForOrgHier = `${environment.baseUrl}/${environment.api}${urlService.itldisemployee}/getempMasterdepartmentForOrgHier`

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TrainingApi.module}/${TrainingApi.controller}`

    static readonly getProgramLocation = `${TrainingApi.apiController}/getProgramLocation`;

    static readonly getTrainingType = `${TrainingApi.apiController}/getTrainingType`;
    
    static readonly getTrainingModule = `${TrainingApi.apiController}/getTrainingModule`;

    static readonly getStates = `${environment.baseUrl}/${environment.api}/master/areamaster/getStateAutocomplete`;

    static readonly getDealersName = `${TrainingApi.apiController}/getDealerName`;

    static readonly saveTrainingProgramCalendar = `${TrainingApi.apiController}/saveTrainingProgramCalendar`;

    static readonly tpcSearch = `${TrainingApi.apiController}/tpcSearch`;

    static readonly getViewEditData = `${TrainingApi.apiController}/getViewEditData`;

    static readonly updateTrainingProgramCalendar = `${TrainingApi.apiController}/updateTrainingProgramCalendar`;

    static readonly getNomineesApproval = `${TrainingApi.apiController}/getNomineesApproval`;

    static readonly getProgramNo = `${TrainingApi.apiController}/getProgramNo`;

    static readonly getNomineeDetails = `${TrainingApi.apiController}/getNomineeDetails`;

   
}
