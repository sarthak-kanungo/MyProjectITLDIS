import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class KubotaUserSearchPageStore {

    private readonly _searchKubotaUserForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._searchKubotaUserForm = this.formBuilder.group({
            kubotaUserForm: this.buildkubotaUserForm(),
        })
    }

    get searchKubotaUserForm() {
        return this._searchKubotaUserForm
    }

    private buildkubotaUserForm() {
        return this.formBuilder.group({
            employeeCode: [null],
            employeeName: [null],
            page: [0],
            size: [10]           
        })
    }

}