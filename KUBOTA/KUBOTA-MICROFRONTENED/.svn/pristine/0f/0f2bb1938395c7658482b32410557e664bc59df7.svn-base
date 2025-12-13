import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Injectable } from '@angular/core';
import { CustomValidators, ValidateMax } from '../../../../../utils/custom-validators';

@Injectable()
export class ServiceActivityClaimPageStore {
    private readonly _serviceActivityClaimForm

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceActivityClaimForm = this.formBuilder.group({
            serviceActivityClaimForm: this.buildServiceActivityClaimForm(),
            subActivityDetailstableData: this.formBuilder.group({
                subActivityDetailsdataTable: this.formBuilder.array([])
            }),
            headDetailstableData: this.formBuilder.group({
                headDetailsdataTable: this.formBuilder.array([])
            }),
            totalServiceActivityClaim: this.buildTotalServiceActivityClaimForm(),
        })
    }
    get serviceActivityClaimFormGroup() {
        return this._serviceActivityClaimForm
    }

    private buildServiceActivityClaimForm(): FormGroup {
        return this.formBuilder.group({
            claimNumber: [{ value: null, disabled: true }],
            activityClaimDate: [{ value: null, disabled: true }],
            dealerName:[{value:null,disabled:true}],
            claimStatus: [{ value: null, disabled: true }],
            activityNo: [null, Validators.compose([Validators.required])],
            activityCreationDate: [{ value: null, disabled: true }],
            activityType: [{ value: null, disabled: true }],
            fromDate: [{ value: null, disabled: true }],
            actualFromDate: [{ value: null, disabled: true }],
            toDate: [{ value: null, disabled: true }],
            noOfDays: [{ value: null, disabled: true }],
            noOfMachines: [{ value: null, disabled: true }],
            actualToDate: [{ value: null, disabled: true }],
            location: [{ value: null, disabled: true }],
            activityEffectiveness: [{ value: null, disabled: true }],
            kaiRemark: [{ value: null, disabled: true }],
            
            //Suraj_01-02-2024
            approvedAmount: [{value: null, disabled: true}]
        })
    }

    createSubActivityTableRow(data = null) {
        let fg: FormGroup = this.formBuilder.group({
            subActivity: [data ? data.subActivity : null],
            amount: [data ? data.amount : null],
            actualClaimAmount: [data ? (data.actualClaimAmount ? data.actualClaimAmount : data.amount) : null, CustomValidators.numberOnly],
            image: [data ? data.headImage : null],
            id: [data ? data.id : null],
            claimApprovedAmount: [data ? data.claimApprovedAmount : null],
            remark: [data ? data.remark : null],
            vendorName:[{value:null,disabled:false}],
            billNo:[{value:null,disabled:false}],
            // vendorName:[data?data.vendorName:data.vendorName],
            // billNo:[data?data.billNo:data.billNo],
             billDate:[{value:null,disabled:false}]
        })
        if(data){
            fg.get('claimApprovedAmount').setValidators([Validators.max(fg.get('actualClaimAmount').value)]);
        }
        return fg;
    }

    createHeadsTableRow(data = null) {
        let fg: FormGroup = this.formBuilder.group({
            head: [data ? data.head : null],
            amount: [data ? data.amount : null],
            actualClaimAmount: [data ? (data.actualClaimAmount ? data.actualClaimAmount : data.amount) : null, CustomValidators.numberOnly],
            image: [data ? data.headImage : null],
            id: [data ? data.id : null],
            imageType: [data ? data.imageType : null],
            claimApprovedAmount: [data ? data.claimApprovedAmount : null],
            remark: [data ? data.remark : null],
            // vendorName:[data?data.vendorName:null],
            // billNo:[data?data.billNo:null],
            // billDate:[data?data.billDate:null]
            vendorName:[{value:null,disabled:false}],
            billNo:[{value:null,disabled:false}],
            // vendorName:[data?data.vendorName:data.vendorName],
            // billNo:[data?data.billNo:data.billNo],
             billDate:[{value:null,disabled:false}]
        });
        if(data){
            fg.get('claimApprovedAmount').setValidators([Validators.max(fg.get('actualClaimAmount').value)]);
        }
        return fg;
    }

    private buildTotalServiceActivityClaimForm(): FormGroup {
        return this.formBuilder.group({
            totalClaimAmount: [null],
            approvedAmount: [null],
            totalClaimApprovedAmount: [null],
        })
    }
}