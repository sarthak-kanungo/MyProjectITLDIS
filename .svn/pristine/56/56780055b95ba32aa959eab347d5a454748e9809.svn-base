import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerDesignationMasterSearchPageStore } from './dealer-designation-master-search-page.store';


@Injectable()
export class DealerDepartmentMasterSearchPagePresenter {

    readonly searchDealerDesignationMasterForm: FormGroup

    constructor(
        private dealerDesignationMasterSearchPageStore: DealerDesignationMasterSearchPageStore
    ) {
        this.searchDealerDesignationMasterForm = this.dealerDesignationMasterSearchPageStore.dealerSearchForm
        // console.log("this.searchDealerDesignationMasterForm ", this.searchDealerDesignationMasterForm);
    }

    get dealerDesignationSearchDetailsForm() {
        // return null
        // console.log("this.searchDealerDesignationMasterForm ", this.searchDealerDesignationMasterForm);
        return this.searchDealerDesignationMasterForm.get('dealerDesignationSearchDetailsForm') as FormGroup
    }
}