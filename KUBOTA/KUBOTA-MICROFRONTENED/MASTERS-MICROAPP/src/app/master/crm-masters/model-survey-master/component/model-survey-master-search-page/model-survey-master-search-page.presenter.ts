import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { ModelSurveyMasterSearchPageStore } from "./model-survey-master-search-page.store";

@Injectable()
export class ModelSurveyMasterSearchPagePresenter{
    readonly searchModelSurveyMasterForm: FormGroup
    constructor(private pageStore:ModelSurveyMasterSearchPageStore){
        this.searchModelSurveyMasterForm=pageStore.modelSurveySearchForm
    }
    public get modelSurveyMasterSearchMForm(){
        return this.searchModelSurveyMasterForm.get('modelSurveyMasterSearchForm') as FormGroup
    }
}