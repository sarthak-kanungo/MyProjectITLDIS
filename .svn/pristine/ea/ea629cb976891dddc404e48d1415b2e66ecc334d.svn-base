import { Injectable } from "@angular/core";
import { WpdcSearchPageStore } from './wpdc-search-page.store';
import { FormGroup } from '@angular/forms';

@Injectable()
export class WpdcSearchPagePresenter {

    constructor(
        private wpdcSearchPageStore: WpdcSearchPageStore,
    ) {}

    get wpdcSearchForm(): FormGroup {
        return this.wpdcSearchPageStore.wpdcSearchForm;
    }
}