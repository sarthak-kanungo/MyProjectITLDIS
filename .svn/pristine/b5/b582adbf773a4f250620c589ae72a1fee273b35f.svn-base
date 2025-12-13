import { Injectable } from '@angular/core';
import { SpareGrnSearchPageStore } from './spare-grn-search-page.store';
import { FormBuilder, FormGroup } from '@angular/forms';

@Injectable()
export class SpareGrnSearchPagePresenter {
    private spareGrnSearchPageStore: SpareGrnSearchPageStore
    constructor(private fb: FormBuilder) {
        this.spareGrnSearchPageStore = new SpareGrnSearchPageStore(this.fb);
    }

    public get getSpareGrnSearchForm(): FormGroup {
        return this.spareGrnSearchPageStore.getSpareGrnSearchForm;
    }

}
