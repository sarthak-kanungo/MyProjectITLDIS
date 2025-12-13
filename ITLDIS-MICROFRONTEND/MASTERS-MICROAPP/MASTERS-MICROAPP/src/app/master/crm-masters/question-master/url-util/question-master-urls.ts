import { environment } from "src/environments/environment"

export abstract class QuestionMasterApi {
    private static readonly module = 'master'
    private static readonly controller = 'crm-masters/question-master'
    static readonly apiController = `${environment.baseUrl}/${environment.api}/${QuestionMasterApi.module}/${QuestionMasterApi.controller}`



  
    static readonly getQuestionType = `${QuestionMasterApi.apiController}/getQuestionType`;
    static readonly saveQuestionMaster = `${QuestionMasterApi.apiController}/saveQuestionMaster`;

    static readonly getAutoQuestionCode = `${QuestionMasterApi.apiController}/getAutoQuestionCode`;
    static readonly searchQuestionMaster = `${QuestionMasterApi.apiController}/searchQuestionMaster`;
    static readonly getViewEditData = `${QuestionMasterApi.apiController}/getViewEditData`;

    static readonly updateQuestionMaster = `${QuestionMasterApi.apiController}/updateQuestionMaster`;
}