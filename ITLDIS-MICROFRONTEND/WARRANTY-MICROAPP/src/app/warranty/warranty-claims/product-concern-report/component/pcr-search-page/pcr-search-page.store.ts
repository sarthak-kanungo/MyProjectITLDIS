import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class PCRSearchPageStore {
    private readonly pcrSearchForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this.pcrSearchForm = this.fb.group({
            pcrNo: [{value: null, disabled: false}],
            status: [{value: null, disabled: false}],
            pcrFromDate: [{value: null, disabled: false}],
            pcrToDate: [{value: null, disabled: false}],
            jobCardNo: [{value: null, disabled: false}],
            machineModel: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}],
            engineNo: [{value: null, disabled: false}],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            product: [{value: null, disabled: false}],
            series: [{value: null, disabled: false}],
            subModel: [{value: null, disabled: false}],
            variant: [{value: null, disabled: false}],
            partNo: [{value: null, disabled: false}],
            orgHierarchyId: [{value: null, disabled: false}],
            dealerShip:[],
            branch:[],
            state:[]
        });
    }

    /* createForm() {
        this.pcrSearchForm = this.fb.group({
            pcrNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            status: [{value: null, disabled: false}],
            pcrFromDate: [{value: null, disabled: false}],
            pcrToDate: [{value: null, disabled: false}],
            jobCardNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            machineModel: [{value: null, disabled: false}],
            chassisNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            engineNo: [{value: null, disabled: false}, CustomValidators.selectFromList()],
            jobCardFromDate: [{value: null, disabled: false}],
            jobCardToDate: [{value: null, disabled: false}],
            page: [0],
            size: [10]
        });
    } */

    get pcrSearchFormAll() {
        if(this.pcrSearchForm) {
            return this.pcrSearchForm as FormGroup;
        }
        /* this.createForm();
        return this.pcrSearchForm as FormGroup; */
    }
}