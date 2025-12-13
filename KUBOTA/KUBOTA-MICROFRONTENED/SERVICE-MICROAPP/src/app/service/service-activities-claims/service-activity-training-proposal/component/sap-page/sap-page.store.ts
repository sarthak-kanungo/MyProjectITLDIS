import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class SapPageStore {
    private readonly _sapFormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._sapFormGroup = this.formBuilder.group({
            activityTrainingProposalForm: this.buildingActivityForm(),
            subActivityRow: this.formBuilder.group({
                subActivityTable: this.formBuilder.array([])
            }),
            headForm: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),
            headsTotal: this.createHeadsTotal()
        })
    }
    get sapFormGroup() {
        return this._sapFormGroup
    }

    private buildingActivityForm(): FormGroup {
        return this.formBuilder.group({
            activityNumber: [{ value: null, disabled: true }],
            activityCreationDate: [{ value: null, disabled: true }],
            status: [{ value: null, disabled: true }],
            activityType: [null, Validators.compose([Validators.required])],
            subActivity: [{ value: null, disabled: false }],
            location: [null, Validators.compose([])],
            fromDate: [null, Validators.compose([])],
            toDate: [null, Validators.compose([])],
            noOfDays: [{ value: null, disabled: true }],
            proposedBudget: [{ value: null, disabled: true }],
            maxAllowedBudget: [{ value: null, disabled: true }],
            targetedNumbers: [null, Validators.compose([CustomValidators.numberOnly])],
            numberOfPerson: [null, Validators.compose([CustomValidators.numberOnly])],
            targetedProduct: [null, Validators.compose([])],
            remarks: [null, Validators.compose([Validators.required])],
            totalSubActivity: [null],
            totalHeads: [null],
            kaiRemark:[{ value: null, disabled: true }],
            approvedAmount:[null, CustomValidators.numberOnly],
            // add dealer name
            dealerName:[{value:null,disabled:true}]
        })
    }

    subActivityRow(data = null) {
        return this.formBuilder.group({
            id: [data ? data.id : null],
            subActivity: [data ? data.subActivity : null],
            amount: [data ? data.amount : null, CustomValidators.numberOnly],
            isSelected: [false]
        })
    }

    headsRow(data = null) {
        return this.formBuilder.group({
            heads: [data ? data.head : null],
            amount: [data ? data.amount : null, CustomValidators.numberOnly],
            isSelected: [false],
            id: [data ? data.id : null]
        })
    }

    private createHeadsTotal() {
        return this.formBuilder.group({
            totalAmount: [{ value: null, disabled: true }],
        })
    }

}