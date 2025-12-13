import { Injectable } from "@angular/core";
import { FormGroup, FormArray, } from "@angular/forms";
import { ServiceActivityClaimPageStore } from './service-activity-claim-page.store';
import { HeadsDetailsByActivityNumber, ActivityDetailsByActivityNo, ViewActivityClaim, SubActivityDetailsByActivityNumber } from '../../domain/service-activity-claim.domain';

@Injectable()
export class ServiceActivityClaimPagePresenter {
    readonly serviceActivityClaimForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    constructor(
        private serviceActivityClaimPageStore: ServiceActivityClaimPageStore,
    ) {
        this.serviceActivityClaimForm = this.serviceActivityClaimPageStore.serviceActivityClaimFormGroup
    }

    get activityClaimForm() {
        return this.serviceActivityClaimForm.get('serviceActivityClaimForm') as FormGroup
    }

    get headDetailstableData() {
        return this.serviceActivityClaimForm.get('headDetailstableData') as FormGroup
    }

    get subActivityDetailstableData() {
        return this.serviceActivityClaimForm.get('subActivityDetailstableData') as FormGroup
    }

    get totalServiceActivityClaim() {
        return this.serviceActivityClaimForm.get('totalServiceActivityClaim') as FormGroup
    }

    createHeadsDetailsTableRow(list: HeadsDetailsByActivityNumber) {
        return this.serviceActivityClaimPageStore.createHeadsTableRow(list)
    }

    createSubActivityDetailsTableRow(list: SubActivityDetailsByActivityNumber) {
        return this.serviceActivityClaimPageStore.createSubActivityTableRow(list)
    }

    addHeadsDetails(data: HeadsDetailsByActivityNumber) {
        const dataTable = this.headDetailstableData.get('headDetailsdataTable') as FormArray;
        dataTable.push(this.createHeadsDetailsTableRow(data));
    }

    addSubActivityDetails(data: SubActivityDetailsByActivityNumber) {
        const dataTable = this.subActivityDetailstableData.get('subActivityDetailsdataTable') as FormArray;
        dataTable.push(this.createSubActivityDetailsTableRow(data));
    }

    setErrorForActivityNo() {
        this.activityClaimForm.get('activityNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    calculationForProposedAmount() {
        const headTable = this.headDetailstableData.get('headDetailsdataTable') as FormArray
        let total = 0

        headTable.controls.forEach(element => {
            let totalAmount = element.get('amount').value || 0
            total += totalAmount
        });
        
        const subactivityTable = this.subActivityDetailstableData.get('subActivityDetailsdataTable') as FormArray
        
        subactivityTable && subactivityTable.controls && subactivityTable.controls.forEach(element => {
            let totalAmount = element.get('amount').value || 0
            total += totalAmount
        });


        return total
    }

    calculationForClaimAmount() {
        const headTable = this.headDetailstableData.get('headDetailsdataTable') as FormArray
        const subActivityTable = this.subActivityDetailstableData.get('subActivityDetailsdataTable') as FormArray
        let total = 0
        let totalHeadAmount = 0
        let totalSubActivityAmount = 0
        subActivityTable.controls.forEach((ele: FormGroup) => {
            let totalClaimAmount = (ele.get('actualClaimAmount').value && parseFloat(ele.get('actualClaimAmount').value))
            totalSubActivityAmount += totalClaimAmount
        })
        headTable.controls.forEach((element: FormGroup) => {
            let totalAmount = (element.get('actualClaimAmount').value && parseFloat(element.get('actualClaimAmount').value))
            totalHeadAmount += totalAmount
        });
        total = totalHeadAmount + totalSubActivityAmount
        this.totalServiceActivityClaim.get('totalClaimAmount').patchValue(total)
        return total
    }

    validateIssueQty(fg: FormGroup) {
        const orderQty = parseInt(fg.get('amount').value) || 0;
        const issueQty = parseInt(fg.get('actualClaimAmount').value) || 0;
        if (fg.get('actualClaimAmount').value && issueQty > orderQty) {
            fg.get('actualClaimAmount').setErrors({ errors: 'Shoudn\'t More than Proposed Amount' });
            return;
        }
        fg.get('actualClaimAmount').setErrors(null);
    }

    markFormAsTouched() {
        this.serviceActivityClaimForm.markAllAsTouched();
    }

    patchValueForActivityNo(response: ActivityDetailsByActivityNo) {
        this.activityClaimForm.patchValue(response)
        this.activityClaimForm.get('activityCreationDate').patchValue(new Date(response.createdDate))
        this.activityClaimForm.get('fromDate').patchValue(new Date(response.fromDate))
        this.activityClaimForm.get('actualFromDate').patchValue(new Date(response.actualFromDate))
        this.activityClaimForm.get('toDate').patchValue(new Date(response.toDate))
        this.activityClaimForm.get('actualToDate').patchValue(new Date(response.actualToDate))
        this.patchApprovedAmount(response.approvedAmount)
    }

    patchApprovedAmount(approvedAmount: number) {
        if (approvedAmount) {
            return approvedAmount
        } else {
            return 0.0
        }

    }

    resetForActivityNumber() {
        this.activityClaimForm.get('activityNo').reset()
        this.activityClaimForm.get('activityCreationDate').reset()
        this.activityClaimForm.get('activityType').reset()
        this.activityClaimForm.get('fromDate').reset()
        this.activityClaimForm.get('actualFromDate').reset()
        this.activityClaimForm.get('toDate').reset()
        this.activityClaimForm.get('noOfDays').reset()
        this.activityClaimForm.get('actualToDate').reset()
        this.activityClaimForm.get('location').reset()
        this.activityClaimForm.get('activityEffectiveness').reset()
    }

    patchValueForViewActivityClaim(response: ViewActivityClaim) {
        this.activityClaimForm.patchValue(response.activityClaimHeaderData)
        this.activityClaimForm.get('activityNo').patchValue({ id: response.activityClaimHeaderData.activityNumberId, activityNumber: response.activityClaimHeaderData.activityNumber })
        this.activityClaimForm.get('activityCreationDate').patchValue(new Date(response.activityClaimHeaderData.createdDate))
        this.activityClaimForm.get('activityClaimDate').patchValue(new Date(response.activityClaimHeaderData.claimDate))
        
        const activityClaimHeads = []
        response.activityClaimHeads.forEach(head => {
            console.log(head,'heas')
            let filename = head.headImage;
            let fileType = '';
            if(filename){
                fileType = (filename.toString()).substring(filename.toString().lastIndexOf('.')+1);
            }
           
            activityClaimHeads.push({
                id: head.id,
                head: head.head,
                amount: head.amount,
                actualClaimAmount : head.actualClaimAmount,
                headImage: head.headImage,
                imageType: fileType,
                claimApprovedAmount:head.claimApprovedAmount,
                vendorName:head.vendorName,
                billNo:head.billNo?head.billNo:null,
                billDate:head.billDate
            })
        })
        activityClaimHeads.forEach(data => {
            this.addHeadsDetails(data)
        })
        const subActivities = []
        response.subActivities.forEach(subActivity => {
            subActivities.push({
                id: subActivity.id,
                subActivity: subActivity.subActivity,
                amount: subActivity.amount,
                actualClaimAmount : subActivity.actualClaimAmount,
                claimApprovedAmount:subActivity.claimApprovedAmount,
                headImage: subActivity['subActivityImage'],
                vendorName:subActivity.vendorName,
                billNo:subActivity.billNo,
                billDate:subActivity.billDate
            })   
        })
        subActivities.forEach(data => {
            this.addSubActivityDetails(data)
        })
    }


}