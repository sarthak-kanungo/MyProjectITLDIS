import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class GoodwillSearchPageStore {
    private searchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {}

    createForm() {
        this.searchForm = this.fb.group({
            goodwillNo: [{value: null, disabled: false}],
            goodwillType: [{value: null, disabled: false}],
            goodwillStatus: [{value: null, disabled: false}],
            pcrNo: [{value: null, disabled: false}],
            jobCardNo: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}],
            goodwillFromDate: [{value: null, disabled: false}],
            goodwillToDate: [{value: null, disabled: false}],
            machineModel: [{value: null, disabled: false}],
            failureType: [{value: null, disabled: false}],
            mobileNo: [{value: null, disabled: false}],
            registrationNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            orgHierarchyId: [],
            branch: [],
            state: [],
            dealerShip: [],
            page:[0],
            size:[10]
        });
    }

    get goodwillSearchForm() {
        if(this.searchForm) {
            return this.searchForm as FormGroup;
        }
        this.createForm();
        return this.searchForm as FormGroup;
    }
}