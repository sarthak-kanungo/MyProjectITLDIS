import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class FunctionMasterPageStore {

    private readonly _functionMasterForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this._functionMasterForm = this.formBuilder.group({
            functionTableForm:this.formBuilder.group({
                tableData:this.formBuilder.array([])
            }),
        });
    }

    get functionMasterForm() {
        return this._functionMasterForm;
    }

    buildFunctionTableForm(data = null){
        return this.formBuilder.group({
            function : [{value : data ? data.function : null, disbled : false}],
            itldis: [{value : data ? data.itldis : null, disbled : false}],
            dealer: [{value : data ? data.dealer : null, disbled : false}],
        })
    }

}
