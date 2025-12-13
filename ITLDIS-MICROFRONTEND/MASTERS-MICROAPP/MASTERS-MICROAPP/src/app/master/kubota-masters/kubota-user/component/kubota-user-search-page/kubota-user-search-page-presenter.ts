import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { itldisUserSearchPageStore } from './itldis-user-search-page.store';

@Injectable()
export class itldisUserSearchPagePresenter {

    readonly searchitldisUserForm: FormGroup

    constructor(
        private itldisUserSearchPageStore: itldisUserSearchPageStore
    ) {
        this.searchitldisUserForm = this.itldisUserSearchPageStore.searchitldisUserForm
    }

    get itldisUserForm() {
        return this.searchitldisUserForm.get('itldisUserForm') as FormGroup
    }

}