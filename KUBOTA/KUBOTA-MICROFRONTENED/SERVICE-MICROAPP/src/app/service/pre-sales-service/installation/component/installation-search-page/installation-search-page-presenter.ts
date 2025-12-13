import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InstallationSearchPageStore } from './installation-search-page.store';

@Injectable()
export class InstallationSearchPagePresenter {

    readonly installationSearchForm: FormGroup

    constructor(
        private installationSearchPageStore: InstallationSearchPageStore
    ) {
        this.installationSearchForm = this.installationSearchPageStore.installationSearchForm
    }

    get detailsForm() {
        return this.installationSearchForm.get('installationSearchDetailsForm') as FormGroup
    }

}