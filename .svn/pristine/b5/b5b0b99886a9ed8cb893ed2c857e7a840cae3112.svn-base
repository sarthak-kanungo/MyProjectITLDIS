import { Injectable } from '@angular/core';
import { MrcSearchPageStore } from './mrc-search-page.strore';
import { FormGroup } from '@angular/forms';

@Injectable()
export class MrcSearchPage {
   readonly mrcSearchForm: FormGroup

    constructor(private store: MrcSearchPageStore) {
        this.mrcSearchForm = this.store.mrcSearchFormGroup
    }

    get mrcSearch() {
        return this.mrcSearchForm.get('mrcSearch') as FormGroup
    }
}