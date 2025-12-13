import { Injectable } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class QaReportPageStore {


    private readonly _qaReportForm: FormGroup;
    constructor(private formBuilder: FormBuilder) {
        this._qaReportForm = this.formBuilder.group({
            qaReportFormForm:this.buildQaReportForm(),
            
        });
    }

    get qaReportFormForm() {
        return this._qaReportForm;
    }

   private buildQaReportForm(){
        return this.formBuilder.group({
            surveyType: [],
            fromDate: [null, Validators.compose([])],
            toDate: [null, Validators.compose([])],
            dealer: [null, Validators.compose([])],
            product:[],
            model: [null, Validators.compose([])],
            subModel: [null, Validators.compose([])],
            series:[],
            variant:[],
            question:[],
            hoUser:[]
        })
    }
    
}