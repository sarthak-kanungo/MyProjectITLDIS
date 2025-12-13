import { Injectable } from "@angular/core"
import { FormBuilder, FormGroup, Validators } from "@angular/forms"
import { CustomValidators } from "src/app/utils/custom-validators";

@Injectable()
export class SearchDelearCustomerCareExCallStore {

    private readonly _delearCustomerCareExCallSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._delearCustomerCareExCallSearchForm = this.formBuilder.group({
            mobileNo : [{value:null,disabled:false},Validators.compose([CustomValidators.mobileNumber])],
            fromDate : [{value:null,disabled:false}],
            toDate : [{value:null,disabled:false}],
            customerName : [{value:null,disabled:false}],
            callType : [{value:null,disabled:false}],
            callStatus : [{value:null,disabled:false}],
        })
    }

    get callSerchForm(){
        return this._delearCustomerCareExCallSearchForm;
    }
}