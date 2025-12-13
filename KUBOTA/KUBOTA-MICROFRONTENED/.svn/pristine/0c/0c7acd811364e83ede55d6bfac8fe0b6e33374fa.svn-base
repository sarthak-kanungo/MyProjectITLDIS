import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { KubotaUserSearchPageStore } from './kubota-user-search-page.store';

@Injectable()
export class KubotaUserSearchPagePresenter {

    readonly searchKubotaUserForm: FormGroup

    constructor(
        private kubotaUserSearchPageStore: KubotaUserSearchPageStore
    ) {
        this.searchKubotaUserForm = this.kubotaUserSearchPageStore.searchKubotaUserForm
    }

    get kubotaUserForm() {
        return this.searchKubotaUserForm.get('kubotaUserForm') as FormGroup
    }

}