import { Injectable } from '@angular/core';
import { SapPageStore } from './sap-page.store';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { ViewServiceActivityProposal, ServiceActivityProposalHead, ServiceActivityProposalSubActivity, MaxAllowedBudget } from '../../domain/sap.domain';

@Injectable()
export class SapPagePresenter {
    readonly sapForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    constructor(
        private sapPageStore: SapPageStore
    ) {
        this.sapForm = this.sapPageStore.sapFormGroup
    }
    markFormAsTouched() {
        this.sapForm.markAllAsTouched();
    }
    get activityTrainingProposal() {
        return this.sapForm.get('activityTrainingProposalForm') as FormGroup
    }
    get headTable() {
        return this.sapForm.get('headForm') as FormGroup
    }

    get subActivityForm() {
        return this.sapForm.get('subActivityRow') as FormGroup
    }

    get headsTotalForm() {
        return this.sapForm.get('headsTotal') as FormGroup
    }

    createHeadsTableRow(list: ServiceActivityProposalHead) {
        return this.sapPageStore.headsRow(list)
    }

    createSubActivityRow(list: ServiceActivityProposalSubActivity) {
        return this.sapPageStore.subActivityRow(list)
    }

    addRow(list: ServiceActivityProposalHead) {
        let headDetails = this.headTable.controls.dataTable as FormArray
        headDetails.push(this.createHeadsTableRow(list))
    }

    addRowForSubActivity(list: ServiceActivityProposalSubActivity) {
        let subActivityDetails = this.subActivityForm.controls.subActivityTable as FormArray
        subActivityDetails.push(this.createSubActivityRow(list))
    }

    deleteRow() {
        let headDetails = this.headTable.controls.dataTable as FormArray;
        let nonSelected = headDetails.controls.filter(machinery => !machinery.value.isSelected)
        headDetails.clear()
        nonSelected.forEach(el => headDetails.push(el))
    }

    deleteRowForSubActivity() {
        let subActivityDetails = this.subActivityForm.controls.subActivityTable as FormArray;
        let nonSelected = subActivityDetails.controls.filter(machinery => !machinery.value.isSelected)
        subActivityDetails.clear()
        nonSelected.forEach(el => subActivityDetails.push(el))
    }

    setValidationForSubActivityAmount() {
        const subActivityDetails = this.subActivityForm.controls.subActivityTable as FormArray
        subActivityDetails.controls.forEach(value => {
            value.get('amount').setValidators(Validators.required)
            value.get('amount').updateValueAndValidity()
        })
    }

    clearValidationForSubActivityAmount() {
        const subActivityDetails = this.subActivityForm.controls.subActivityTable as FormArray
        subActivityDetails.controls.forEach(value => {
            value.get('amount').clearValidators()
            value.get('amount').updateValueAndValidity()
        })
    }
    calculationForTotalHeads() {
        const headTable = this.headTable.controls.dataTable as FormArray
        let total = 0
        headTable.controls.forEach(element => {
            let totalAmount = (element.get('amount').value && parseFloat(element.get('amount').value)) || 0
            total += totalAmount
        });
        this.activityTrainingProposal.get('totalHeads').patchValue(total)
        return total
    }
    calculationForProposedBudget() {
        
        let totalSubActivity = this.activityTrainingProposal.get('totalSubActivity').value;
        totalSubActivity = totalSubActivity?totalSubActivity:0
        let totalHeads = this.activityTrainingProposal.get('totalHeads').value;
        totalHeads = totalHeads?totalHeads:0;
        this.activityTrainingProposal.get('proposedBudget').patchValue(totalHeads+totalSubActivity)
    }

    calculationForSubActivity() {
        const subActivityTable = this.subActivityForm.controls.subActivityTable as FormArray
        let total = 0
        subActivityTable.controls.forEach(element => {
            let totalAmount = (element.get('amount').value && parseFloat(element.get('amount').value)) || 0
            total += totalAmount
        });
        this.activityTrainingProposal.get('totalSubActivity').patchValue(total)
        return total
    }

    countNoOfDays() {
        this.activityTrainingProposal.get('fromDate').valueChanges.subscribe((res: Date) => {
            if (res) {
                let dateDiff = this.daysDateDiff(new Date(this.activityTrainingProposal.get('toDate').value), res)
                this.activityTrainingProposal.get('noOfDays').patchValue(Number.isNaN(dateDiff) ? 0 : dateDiff)
            } else {
                this.activityTrainingProposal.get('noOfDays').patchValue(0)
            }
        })

        this.activityTrainingProposal.get('toDate').valueChanges.subscribe((res: Date) => {
            if (res) {
                let dateDiff = this.daysDateDiff(res, new Date(this.activityTrainingProposal.get('fromDate').value))
                this.activityTrainingProposal.get('noOfDays').patchValue(Number.isNaN(dateDiff) ? 0 : dateDiff)
            } else {
                this.activityTrainingProposal.get('noOfDays').patchValue(0)
            }
        })
    }

    private daysDateDiff = (from, to) => Math.ceil((Math.abs(to - from) / (1000 * 60 * 60 * 24) + 1))

    patchValueForViewServiceActivityProposal(response: ViewServiceActivityProposal) {
        this.activityTrainingProposal.patchValue(response)
        this.activityTrainingProposal.get('dealerName').patchValue(response.dealerMaster.dealerName)
        this.activityTrainingProposal.get('activityType').patchValue({ id: response.serviceMtActivityType.id, activityType: response.serviceMtActivityType.activityType })
        this.activityTrainingProposal.get('targetedProduct').patchValue(response.targetedProducts)
        this.activityTrainingProposal.get('activityCreationDate').patchValue(new Date(response.createdDate ? response.createdDate : null))
        this.activityTrainingProposal.get('fromDate').patchValue(new Date(response.fromDate ? response.fromDate : null))
        this.activityTrainingProposal.get('toDate').patchValue(new Date(response.toDate ? response.toDate : null))
        this.headsTotalForm.get('totalAmount').patchValue(response.proposedBudget)
        const serviceActivityProposalHeads = []
        response.serviceActivityProposalHeads.forEach(value => {
            serviceActivityProposalHeads.push({
                id: value.id,
                amount: value.amount,
                head: { head: value.head }
            })
        })
        serviceActivityProposalHeads.forEach(value => {
            this.addRow(value)
        })
        const serviceActivityProposalSubActivity = []
        response.serviceActivityProposalSubActivity.forEach(value => {
            serviceActivityProposalSubActivity.push({
                subActivity: { subActivity: value.subActivity },
                amount: value.amount,
                id: value.id,
            })
        })
        serviceActivityProposalSubActivity.forEach(value => {
            this.addRowForSubActivity(value)
        })
        console.log(this.activityTrainingProposal);

    }

}