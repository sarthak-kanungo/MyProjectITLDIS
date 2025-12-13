import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TransporterPageStore } from './transporter-page.store';


@Injectable()
export class TransporterPagePresenter {

    readonly transporterCreateForm: FormGroup

    constructor(
        private transporterPageStore: TransporterPageStore
    ) {
        this.transporterCreateForm = this.transporterPageStore.transporterCreateForm
    }

    get transporterDetailsForm() {
        return this.transporterCreateForm.get('transporterDetailsForm') as FormGroup
    }
    get addressDetailsForm() {
        return this.transporterCreateForm.get('addressDetailsForm') as FormGroup
    }

    markFormAsTouched() {
        this.transporterCreateForm.markAllAsTouched();
    }


}