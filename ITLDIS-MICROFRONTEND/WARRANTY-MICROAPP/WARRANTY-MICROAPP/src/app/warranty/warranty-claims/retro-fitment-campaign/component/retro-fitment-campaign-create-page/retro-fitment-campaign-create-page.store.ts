import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { LabourDetails, MaterialDetails } from '../../domain/retro-fitment-campaign.domain';
@Injectable()
export class RfcPageStore {
    private readonly rfcForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        console.log('---------Rfc Form Created---------------');
        this.rfcForm = this.fb.group({
            formRFC: this.createRFCForm(),
            materialDetail: this.fb.array([]),
            // labourCharge: this.fb.array([this.createLabourForm()])
              labourCharge:this.fb.array([])
        });
    }

    /* private createForm() {
        console.log('---------Rfc Form Created---------------');
        this.rfcForm = this.fb.group({
            formRFC: this.createRFCForm(),
            materialDetail: this.fb.array([]),
            labourCharge: this.fb.array([this.createLabourChargesForm()])
        });
    } */

    private createRFCForm() {
        const formRFC = this.fb.group({
            retrofitmentNo: [{ value: '', disabled: false }],
            retrofitmentDate: [{ value: '', disabled: true }],
            campaignName: [{ value: '', disabled: false }, Validators.required],
            startDate: [{ value: '', disabled: false }, Validators.required],
            endDate: [{ value: '', disabled: false }, Validators.required],
            chassisNo: [{ value: '', disabled: false }],
        });

        return formRFC;
    }
    private createMaterialDetailsForm(addMaterialDetails?: MaterialDetails) {
        const materialDetailsForm = this.fb.group({
            sparePartMaster: [{ value: addMaterialDetails == undefined ? null : addMaterialDetails.sparePartMaster, disabled: false },Validators.compose([Validators.required, CustomValidators.selectFromList()])],
            description: [{ value: addMaterialDetails == undefined ? null : addMaterialDetails.description, disabled: true }],
            quantity: [{ value: addMaterialDetails == undefined ? null : addMaterialDetails.quantity, disabled: false }, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            isSelected: [{ value: '', disabled: false }]
        });

        return materialDetailsForm;
    }
    private createLabourForm(labourDetails?: LabourDetails) {
        const labourChargesForm = this.fb.group({
            serviceMtJobcode: [{ value: labourDetails == undefined ? null : labourDetails.serviceMtJobcode, disabled: false },Validators.compose([Validators.required, CustomValidators.selectFromList()])],
            description: [{ value: labourDetails == undefined ? null : labourDetails.description, disabled: true }],
            hours: [{ value: labourDetails == undefined ? null : labourDetails.hours, disabled: false }, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            isSelected: [{ value: '', disabled: false }],
            claimAmount: [{ value: labourDetails == undefined ? null : labourDetails.claimAmount, disabled: false }, Validators.compose([Validators.required, CustomValidators.numberOnly])],
            warrantyRetrofitmentCampaignPhoto: ['']
        });
        //   console.log('viwwwww')
        return labourChargesForm;
    }
    // private createLabourChargesForm() {
    //     const labourChargesForm = this.fb.group({
    //         jobCodeNo: [{value: '', disabled: false}],
    //         description: [{value: '', disabled: false}],
    //         hours: [{value: '', disabled: false}],
    //         quantity: [{value: '', disabled: false}],
    //         warrantyRetrofitmentCampaignPhoto: ['']
    //     });
    //     console.log(labourChargesForm,'form')

    //     return labourChargesForm;
    // }

    get rfcFormAll() {
        if (this.rfcForm) {
            return this.rfcForm as FormGroup;
        }
        /* this.createForm();
        return this.rfcForm as FormGroup; */
    }

    initializeCreateMaterialDetailsForm(addMaterialDetails?: MaterialDetails) {
        return this.createMaterialDetailsForm(addMaterialDetails);
    }
    initializeCreateLabourChargesForm(labourDetails?: LabourDetails) {
        return this.createLabourForm(labourDetails);
    }
}