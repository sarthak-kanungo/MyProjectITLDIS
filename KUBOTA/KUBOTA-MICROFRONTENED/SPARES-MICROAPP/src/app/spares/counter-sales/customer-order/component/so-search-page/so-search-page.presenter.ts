import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SoSearchPageStore } from './so-search-page.store';

@Injectable()
export class SoSearchPagePresenter {
    readonly soSearchForm: FormGroup

    constructor(private store: SoSearchPageStore) {
        this.soSearchForm = this.store.soSearchFormGroup
    }

    get soSearch() {
        return this.soSearchForm.get('soSearch') as FormGroup
    }
}