import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SourceSearchPageStore } from './source-search-page.store';

@Injectable()
export class SourceSearchPagePresenter {

    readonly sourceSearchForm: FormGroup

    constructor(
        private sourceSearchPageStore: SourceSearchPageStore
    ) {
        this.sourceSearchForm = this.sourceSearchPageStore.sourceSearchForm
    }

    get sourceSearchDetailsForm() {
        return this.sourceSearchForm.get('sourceSearchDetailsForm') as FormGroup
    }

}