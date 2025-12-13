import { Injectable } from "@angular/core";
import { WcrSearchPageStore } from './warrenty-claim-request-search-page.store';
import { FormGroup } from '@angular/forms';

@Injectable()
export class WcrSearchPagePresenter {

    constructor(
        private wcrSearchPageStore: WcrSearchPageStore,
    ) {}

    get wcrSearchForm(): FormGroup {
        return this.wcrSearchPageStore.wcrSearchForm;
    }
}