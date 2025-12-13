import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { EmployeeMasterSearchPageStore } from './employee-master-page-search.store';



@Injectable()
export class EmployeeMasterSearchPagePresenter {

    readonly searchDealerEmployeeForm: FormGroup

    constructor(
        private EmployeeMasterPageStore: EmployeeMasterSearchPageStore
    ) {
        this.searchDealerEmployeeForm = this.EmployeeMasterPageStore.searchDealerEmployeeForm
    }

    markFormAsTouched() {
        this.searchDealerEmployeeForm.markAllAsTouched();
    }

    get employeeForm(){
        return this.searchDealerEmployeeForm.get('employeeForm') as FormGroup
    }


}