import { Injectable } from "@angular/core";
import { FormGroup } from '@angular/forms';
import { DealerMasterSearchPageStore } from "./dealer-master-search.store";

@Injectable()
export class DealerMasterSearchPagePresenter {

    constructor(
        private dealerMasterSearchPageStore: DealerMasterSearchPageStore,

    ) {}

    get SearchForm(): FormGroup {
        return this.dealerMasterSearchPageStore.dealerMasterSearchForm;
    }
}    