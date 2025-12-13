import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { itldisUserMasterDomain } from '../../domain/itldis-user-domain';

@Injectable()
export class itldisUserCreatePageStore {

    private readonly _createUserMasterMainForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this._createUserMasterMainForm = this.formBuilder.group({
            createNewUserForm: this.buildcreateNewUserForm(),
            functionTableForm:this.formBuilder.group({
                tableData:this.formBuilder.array([])
            }),

        });
    }

    get createUserMasterMainForm() {
        return this._createUserMasterMainForm;
    }

    private buildcreateNewUserForm() {
        return this.formBuilder.group({
            employeeId: [null, Validators.compose([])],
            password: [null, Validators.compose([Validators.required])],
            confirmPassword: [null, Validators.compose([Validators.required])],
            employeeName: [{ value: null, disabled: true }, Validators.compose([])],
            assignRole: [null],
            employeeStatus: [{ value: null, disabled: true }, Validators.compose([])],
            loginIdStatus: [null, Validators.compose([])],
        });
    }

    buildAssignFunctionTableForm(data: itldisUserMasterDomain) : FormGroup{
       // console.log("data--->", data)
        return this.formBuilder.group({     
            assignFunction: [{value : (data && data.assigned==='Y' ? true : false) , disabled : true}], 
            roleid: [{value : data ? data.id : null, disabled : true}],     
            role_name: [{value : data ? data.role_name : null, disabled : true}],
            role_code: [{value : data ? data.role_code : null, disabled : true}],
        })
    }

}



