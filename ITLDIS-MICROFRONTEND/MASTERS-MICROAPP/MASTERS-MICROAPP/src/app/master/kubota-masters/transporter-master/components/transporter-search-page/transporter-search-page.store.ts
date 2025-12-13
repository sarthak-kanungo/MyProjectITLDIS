import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class TransporterSearchPageStore {

    private readonly _transporterSearchForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._transporterSearchForm = this.formBuilder.group({
            transporterSearchDetailsForm: this.buildTransporterSearcheDetailsForm(),
        })
    }

    get transporterSearchForm() {
        return this._transporterSearchForm
    }

    private buildTransporterSearcheDetailsForm() {
        return this.formBuilder.group({
            transporterCode: [null],
            transporterName: [null],
          //  purpose: [null],
        })
    }

}