import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class DealerMasterSearchPageStore {
    private readonly searchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.searchForm = this.fb.group({
            zone: [null],
            region: [null],
            area: [null],
            territoryLevel: [null],
            dealerCode: [null, CustomValidators.selectValueFromList()],
            dealerName: [null, CustomValidators.selectValueFromList()],
            activeStatus: [null],
            subsidyDealer: [null],
            page: [0],
            size:[10]
          })
    }

    get dealerMasterSearchForm() {
        if(this.searchForm) {
            return this.searchForm as FormGroup;
        }
    }
}