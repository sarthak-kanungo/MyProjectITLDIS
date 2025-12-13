import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class JobCardSearchStore {
    private readonly _jobCardSearchForm: FormGroup
    constructor(private formBuilder: FormBuilder) {
        this._jobCardSearchForm = this.formBuilder.group({
            basicJobCardSearch: this.buildBasicJobCardSearch()
        })
    }
    get jobCardSearch() {
        return this._jobCardSearchForm
    }
    private buildBasicJobCardSearch() {
        return this.formBuilder.group({
            chassisNo: [null,],
            jobCardNo: [null,],
            engineNo: [null,],
            csbNo: [null,],
            bookingNo: [null,],
            jobCardDateFrom: [null,],
            jobCardDateTo: [null,],
            excelView: [null,],
            dealerShip:[],
            branch:[],
            fromMachineInDate:[],
            toMachineInDate:[],
            status:[],
            product:[],
            series:[],
            model:[],
            subModel:[],
            variant:[],
            partNo:[null],
            orgHierLevel1: [null],
            orgHierLevel2: [null],
            orgHierLevel3: [null],
            orgHierLevel4: [null],
            orgHierLevel5: [null]
        })
    }
}