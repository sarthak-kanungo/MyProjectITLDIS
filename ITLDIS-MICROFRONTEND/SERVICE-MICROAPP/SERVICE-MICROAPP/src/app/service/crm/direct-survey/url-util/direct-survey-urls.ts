import { environment } from '../../../../../environments/environment';

export abstract class DirectSurveyApi {
    
    private static readonly module = 'salesandpresales'
    private static readonly moduleForSurvey = 'crm'

    private static readonly controller = 'enquiry'
    private static readonly controllerForSurvey = 'crmmodule/directsurvey'

    private static readonly moduleMachine = 'crm/crmmodule'
    private static readonly controllerEx = 'customerCareExeCall'

    static readonly apiController = `${environment.baseUrl}/${environment.api}/${DirectSurveyApi.module}/${DirectSurveyApi.controller}`
    static readonly apiControllerEx = `${environment.baseUrl}/${environment.api}/${DirectSurveyApi.moduleMachine}/${DirectSurveyApi.controllerEx}`
    static readonly apiControllerForSurvey = `${environment.baseUrl}/${environment.api}/${DirectSurveyApi.moduleForSurvey}/${DirectSurveyApi.controllerForSurvey}`
   
    static readonly staticPath = `${environment.baseUrl}/${environment.api}/files`
    static readonly getMobileNumber = `${DirectSurveyApi.apiController}/getMobileNumber`;
    static readonly getDataByMobileNo = `${DirectSurveyApi.apiController}/getDataByMobileNo`
    static readonly saveDirectSurvey = `${DirectSurveyApi.apiControllerForSurvey}/submitSurveyForm`
    static readonly submitCallAttempt = `${DirectSurveyApi.apiControllerForSurvey}/submitCallAttempt`
    static readonly getSurveyType = `${DirectSurveyApi.apiControllerForSurvey}/getSurveyType`;
    static readonly getSurveyStatus = `${DirectSurveyApi.apiControllerForSurvey}/getSurveyStatus`;
    static readonly getSoldToDealerList = `${environment.baseUrl}/${environment.api}/salesandpresales/marketingActivityProposal/getDealerCodeAutoComplete`;
    static readonly getSatisfactionLevel = `${DirectSurveyApi.apiControllerForSurvey}/getSatisfactionLevel`

    static readonly getCustomerMachineDetails = `${DirectSurveyApi.apiControllerEx}/getCustomerMachineDetails`

    static readonly getManuFacturer = `${DirectSurveyApi.apiController}/getBrands`;

    static readonly getMajorCropGrown = `${DirectSurveyApi.apiController}/getMajorCropGrown`;

    static readonly getAgeOfMachine = `${DirectSurveyApi.apiControllerForSurvey}/getAgeOfMachine`;
    static readonly getMeterHours = `${DirectSurveyApi.apiControllerForSurvey}/getMeterHours`;
    static readonly getDirectSurveyDetails = `${DirectSurveyApi.apiControllerForSurvey}/getDirectSurveyDetails`;



    static readonly getSurveyQuestion = `${DirectSurveyApi.apiControllerForSurvey}/getSurveyQuestion`;
    static readonly getSurveyAnswer = `${DirectSurveyApi.apiControllerForSurvey}/getSurveyAnswer`;
    static readonly getSubAnswer = `${DirectSurveyApi.apiControllerForSurvey}/getSubAnswer`;

    static readonly getVillageAuto = `${DirectSurveyApi.apiControllerForSurvey}/getVillageAuto`;


    static readonly getViewEditData = `${DirectSurveyApi.apiControllerForSurvey}/getViewEditData`;

    static readonly getSurveyMachineDetails = `${DirectSurveyApi.apiControllerForSurvey}/getSurveyMachineDetails`;



      /* JC view */
    static readonly apiControllerJc = `${environment.baseUrl}/${environment.api}/${'service'}/${'jobcard'}`
    static readonly viewJobCard = `${DirectSurveyApi.apiControllerJc}/getJobCardByJobCardNo`

      /* JC view */
    static readonly apiControllerPcr = `${environment.baseUrl}/${environment.api}/${'warranty'}/${'pcr'}`;
    static readonly viewPcr = `${DirectSurveyApi.apiControllerPcr}/warrantyPcrView`
    static readonly serviceHistory = `${DirectSurveyApi.apiControllerPcr}/serviceHistory`;


    /* JC view */
    static readonly apiControllerWcr = `${environment.baseUrl}/${environment.api}/${'warranty'}/${'Wcr'}`;
    static readonly viewWcr = `${DirectSurveyApi.apiControllerWcr}/viewWarrantyWcr`
    static readonly wcrServiceHistory = `${DirectSurveyApi.apiControllerWcr}/serviceHistory`;


     static readonly getCallHistory = `${DirectSurveyApi.apiControllerForSurvey}/getCallHistory`;

     static readonly updateCallrecording = `${DirectSurveyApi.apiControllerForSurvey}/updateCallrecording`;

      

}