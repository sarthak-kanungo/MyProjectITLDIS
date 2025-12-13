import { Injectable } from "@angular/core";
import { FormGroup } from "@angular/forms";
import { DealerSearchPageStore } from "./dealer-search-page.store";

@Injectable()
export class DealerSearchPagePresenter {

    readonly dealerSeacrchForm: FormGroup

    constructor(
        private dealerSearchPageStore: DealerSearchPageStore
    ) {
        this.dealerSeacrchForm = this.dealerSearchPageStore.searchDealerForm
    }

    markFormAsTouched() {
        this.dealerSeacrchForm.markAllAsTouched();
    }

    get dealerForm(){
        return this.dealerSeacrchForm.get('dealerForm') as FormGroup
    }


}