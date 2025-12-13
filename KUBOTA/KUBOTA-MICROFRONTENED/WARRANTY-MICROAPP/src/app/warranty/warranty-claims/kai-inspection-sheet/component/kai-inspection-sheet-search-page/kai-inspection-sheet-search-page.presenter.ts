import { Injectable } from "@angular/core";
import { KisSearchPageStore } from './kai-inspection-sheet-search-page.store';
import { FormGroup } from '@angular/forms';

@Injectable()
export class KisSearchPagePresenter {

    constructor(
        private kisSearchPageStore: KisSearchPageStore,
    ) {}

    get kisSearchForm(): FormGroup {
        return this.kisSearchPageStore.kisSearchForm;
    }
}