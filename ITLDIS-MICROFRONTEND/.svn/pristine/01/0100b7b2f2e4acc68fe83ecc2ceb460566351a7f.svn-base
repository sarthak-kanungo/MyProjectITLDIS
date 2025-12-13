import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { KubotaUserCreatePageStore } from './kubota-user-create-page.store';
import { KubotaUserMasterDomain } from '../../domain/kubota-user-domain';



@Injectable()
export class KubotaUserMasterCreatePagePresenter {

    readonly createUserMasterMainForm: FormGroup

    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private kubotaUserCreatePageStore: KubotaUserCreatePageStore
    ) {
        this.createUserMasterMainForm = this.kubotaUserCreatePageStore.createUserMasterMainForm
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

    createFunctionRow(list : KubotaUserMasterDomain) {
        return this.kubotaUserCreatePageStore.buildAssignFunctionTableForm(list)
    }

    addRow(list? : KubotaUserMasterDomain){
        const details = this.functionTableForm.get('tableData') as FormArray
        details.push(this.createFunctionRow(list))
    }  


}