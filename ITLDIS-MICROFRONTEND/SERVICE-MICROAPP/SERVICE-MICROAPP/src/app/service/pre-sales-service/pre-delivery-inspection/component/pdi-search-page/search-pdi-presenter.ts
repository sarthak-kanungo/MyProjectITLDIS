import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { SearchPdiStore } from './search-pdi-page.store'
@Injectable()
export class PdiSearchPresenter {
    searchPdiForm: FormGroup
    constructor(searchPdiStore: SearchPdiStore) {
        this.searchPdiForm = searchPdiStore.searchPdiForm
    }
    get SearchBasicPdi() {
        return this.searchPdiForm.get('createSearchPdi') as FormGroup
    }
}