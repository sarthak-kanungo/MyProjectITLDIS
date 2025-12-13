import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ReInstallationSearchPageStore } from './re-installtion-search-page.store';

@Injectable()
export class ReInstallationSearchPagePresenter {

    readonly reInstallationSearchForm: FormGroup

    constructor(
        private reInstallationSearchPageStore: ReInstallationSearchPageStore
    ) {
        this.reInstallationSearchForm = this.reInstallationSearchPageStore.reInstallationSearchForm
    }

    get detailsForm() {
        return this.reInstallationSearchForm.get('reInstallationSearchDetailsForm') as FormGroup
    }

}