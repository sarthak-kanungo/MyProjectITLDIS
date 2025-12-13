import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RfcSearchPageStore } from './retro-fitment-campaign-search-page.store';

@Injectable()
export class RfcSearchPagePresenter {
    constructor(
        private rfcSearchPageStore: RfcSearchPageStore
    ) { }

    public get rfcSearchForm(): FormGroup {
        return this.rfcSearchPageStore.rfcSearchForm;
    }
}