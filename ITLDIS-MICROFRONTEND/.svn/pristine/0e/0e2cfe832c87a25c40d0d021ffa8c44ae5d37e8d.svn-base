

import { Injectable } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { BtBtSearchStore } from './btbt-search.store';


@Injectable()
export class BtBtSearchPagePresenter {

    readonly btBtSearchForm: FormGroup;

    constructor(
        private btBtSearchStore: BtBtSearchStore
    ) {
        this.btBtSearchForm = this.btBtSearchStore.btBtFormGroup;
    }
    get btBtSearch() {
        return this.btBtSearchForm.get('btBtSearch') as FormGroup
    }
}