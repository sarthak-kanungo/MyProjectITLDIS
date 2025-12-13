import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class EmployeeMasterSearchPageStore {

    private readonly _searchDealerEmployeeForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this._searchDealerEmployeeForm = this.formBuilder.group({
            employeeForm:this.buildEmployeeForm(),
            
        });
    }

    get searchDealerEmployeeForm() {
        return this._searchDealerEmployeeForm;
    }

   private buildEmployeeForm(){
        return this.formBuilder.group({
            employeeCode: [null, Validators.compose([])],
            mobileNo: [null, Validators.compose([CustomValidators.mobileNumber])],
            employeeName:[{value:null,disabled:false}],
            state:[{value:null,disabled:false}],
            dealerCode:[{value:null,disabled:false}],
            departmentId:[{value:null,disabled:false}]
        })
    }

}
