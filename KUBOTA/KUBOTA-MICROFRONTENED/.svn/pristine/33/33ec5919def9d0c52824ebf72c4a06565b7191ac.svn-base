import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Injectable()
export class PurchaseOrderSearchPageStore {
    private searchPoForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) { }

    private createForm() {
        console.log('------SEARCH FORM CREATED**----');
        this.searchPoForm = this.fb.group({
            fromDate: [null],
            toDate: [null],
            poNumber: [null],
            poType: [null],
            partNumber: [null],
            poStatus: [null],
            product: [null],
            series: [null],
            model: [null],
            subModel: [null],
            variant: [null],
            dealerCode: [{value:'',disabled:false}],
            dealerName : [{value:'', disabled:true}],
            orgHierLevel1 : [null],
            orgHierLevel2 : [null],
            orgHierLevel3 : [null],
            orgHierLevel4 : [null],
            orgHierLevel5 : [null],
            dealerId : [null],
            hierId: [null]
        })
    }

    get getSearchPoForm(): FormGroup {
        if (this.searchPoForm) {
            return this.searchPoForm as FormGroup;
        }
        this.createForm();
        return this.searchPoForm as FormGroup;
    }
}