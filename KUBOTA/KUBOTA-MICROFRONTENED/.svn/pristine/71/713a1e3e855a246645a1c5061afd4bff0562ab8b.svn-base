import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { TransporterSearchPageStore } from './transporter-search-page.store';

@Injectable()
export class TransporterSearchPagePresenter {

    readonly transporterSearchForm: FormGroup

    constructor(
        private transporterSearchPageStore: TransporterSearchPageStore
    ) {
        this.transporterSearchForm = this.transporterSearchPageStore.transporterSearchForm
    }

    get transporterSearchDetailsForm() {
        return this.transporterSearchForm.get('transporterSearchDetailsForm') as FormGroup
    }

}