import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DealerDepartmentMasterSearchPageStore } from './dealer-department-master-search-page.store';


@Injectable()
export class DealerDepartmentMasterSearchPagePresenter {

    readonly searchDealerDepartmentMasterForm: FormGroup

    constructor(
        private dealerDepartmentMasterSearchPageStore: DealerDepartmentMasterSearchPageStore
    ) {
        this.searchDealerDepartmentMasterForm = this.dealerDepartmentMasterSearchPageStore.dealerSearchForm
    }

    get dealerDepartmentSearchDetailsForm() {
        return this.searchDealerDepartmentMasterForm.get('dealerDepartmentSearchDetailsForm') as FormGroup
    }
}