import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class SurveySummaryReportSearchPageStore {


    private readonly _searchSurveySummaryReportForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {
        this._searchSurveySummaryReportForm = this.formBuilder.group({
            surveySummaryReportForm:this.buildSurveySummaryReportSearchForm(),
            
        });
    }

    get searchSurveySummaryReportForm() {
        return this._searchSurveySummaryReportForm;
    }

   private buildSurveySummaryReportSearchForm(){
        return this.formBuilder.group({
            // surveyName: [{ value: null, disabled: true }, Validators.compose([])],
            surveyName: [{ value: null, disabled:true}, Validators.compose([])],
            surveyNo: [],
            surveyType: [],
            surveyStatus: [null, Validators.compose([])],
            asOnDate: [null, Validators.compose([])],
            fromDate: [null, Validators.compose([])],
            toDate: [null, Validators.compose([])],
            dealer: [null, Validators.compose([])],
            customerSatisfaction: [null, Validators.compose([])],
            model: [null, Validators.compose([])],
            subModel: [null, Validators.compose([])],
            page:[],
            chassisNo:[null],
            question:[],
            reportType:[],
            product:[],
            series:[],
            variant:[],
            fromDcDate:[],
            toDcDate:[],
        })
    }
    
}