import { environment } from '../../../../../environments/environment';
import { urlService } from '../../../../webservice-config/baseurl';

export abstract class TrainingApi {
    private static readonly module = 'training';
    private static readonly controller = 'training-program-report';

    
    static readonly getempMasterdepartmentForOrgHier = `${environment.baseUrl}/${environment.api}${urlService.kubotaemployee}/getempMasterdepartmentForOrgHier`

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${TrainingApi.module}/${TrainingApi.controller}`

    static readonly getTrainingZoneRegion = `${TrainingApi.apiController}/getZoneRegionForTrainingReport`;

    static readonly getTrainingRegion = `${TrainingApi.apiController}/getTrainingRegion`;

    

    static readonly getTSMName = `${environment.baseUrl}/${environment.api}${urlService.kubotaemployee}/employeeNameAuto`

    static readonly getDealerDesignation = `${environment.baseUrl}/${environment.api}/dealerDepartmentMaster/departmentCodeAndName`

    static readonly trainingReportSearch = `${TrainingApi.apiController}/trainingReportSearch`;

    //static readonly downloadTrainingReportExcel = `${environment.baseUrl}/${environment.api}/dealerDepartmentMaster/downloadTrainingReportExcel`
    static readonly downloadTrainingReportExcel = `${TrainingApi.apiController}/downloadTrainingReportExcel`;

    static readonly allZone = `${environment.baseUrl}${urlService.api}/crm/crmmodule/surveysummaryreport/getZone`

    static readonly getRegions = `${environment.baseUrl}${urlService.api}/crm/crmmodule/surveysummaryreport/getRegions`

    static readonly getLevelDropUrl= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.getLevelByDepartment}`
    static readonly getHierDropUrl= `${environment.baseUrl}${urlService.api}${urlService.master}${urlService.kaicommonmaster}${urlService.assignOrgHierarchyToDealer}${urlService.getHierarchyByLevel}`

    

  
   
}
