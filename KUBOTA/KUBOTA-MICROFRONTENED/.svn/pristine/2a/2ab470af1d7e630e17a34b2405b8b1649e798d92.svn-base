import { Injectable } from '@angular/core';
import { FormGroup, FormArray } from '@angular/forms';
import { FunctionMasterPageStore } from './function-master-page.store';
import { functionMasterDomain } from '../../domain/function-master-domain';



@Injectable()
export class FunctionMasterPagePresenter {

    readonly functionMasterForm: FormGroup

    constructor(
        private functionMasterPageStore:FunctionMasterPageStore
    ) {
        this.functionMasterForm = this.functionMasterPageStore.functionMasterForm
    }

    markFormAsTouched() {
        this.functionMasterForm.markAllAsTouched();
    }

    get functionTableForm(){
        return this.functionMasterForm.get('functionTableForm') as FormGroup
    }

    createFunctionRow(list : functionMasterDomain) {
        return this.functionMasterPageStore.buildFunctionTableForm(list)
    }

    addRow(list? : functionMasterDomain){
        const details = this.functionTableForm.get('tableData') as FormArray
        details.push(this.createFunctionRow(list))
    }


}