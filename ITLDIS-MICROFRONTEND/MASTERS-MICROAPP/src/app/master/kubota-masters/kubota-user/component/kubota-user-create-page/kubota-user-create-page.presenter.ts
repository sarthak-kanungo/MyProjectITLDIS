import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { itldisUserCreatePageStore } from './itldis-user-create-page.store';
import { itldisUserMasterDomain } from '../../domain/itldis-user-domain';



@Injectable()
export class itldisUserMasterCreatePagePresenter {

    readonly createUserMasterMainForm: FormGroup

    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private itldisUserCreatePageStore: itldisUserCreatePageStore
    ) {
        this.createUserMasterMainForm = this.itldisUserCreatePageStore.createUserMasterMainForm
    }

    get createNewUserForm() {
        return this.createUserMasterMainForm.get('createNewUserForm') as FormGroup
    }

    

    markFormAsTouched() {
        this.createUserMasterMainForm.markAllAsTouched();
    }

    get functionTableForm(){
        return this.createUserMasterMainForm.get('functionTableForm') as FormGroup
    }

    createFunctionRow(list : itldisUserMasterDomain) {
        return this.itldisUserCreatePageStore.buildAssignFunctionTableForm(list)
    }

    addRow(list? : itldisUserMasterDomain){
        const details = this.functionTableForm.get('tableData') as FormArray
        details.push(this.createFunctionRow(list))
    }  


}