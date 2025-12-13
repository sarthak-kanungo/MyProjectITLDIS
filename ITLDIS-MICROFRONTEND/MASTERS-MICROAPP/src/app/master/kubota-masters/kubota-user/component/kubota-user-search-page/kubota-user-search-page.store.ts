import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class itldisUserSearchPageStore {

    private readonly _searchitldisUserForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._searchitldisUserForm = this.formBuilder.group({
            itldisUserForm: this.builditldisUserForm(),
        })
    }

    get searchitldisUserForm() {
        return this._searchitldisUserForm
    }

    private builditldisUserForm() {
        return this.formBuilder.group({
            employeeCode: [null],
            employeeName: [null],
            page: [0],
            size: [10]           
        })
    }

}