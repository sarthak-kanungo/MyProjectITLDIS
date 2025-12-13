import { Injectable } from "@angular/core";
import { FormGroup, FormBuilder, Validators, FormArray, AbstractControl } from '@angular/forms';
import { JobCardHistory, JobCardPart, WarrantyPcrImplement, FailurePart, OutSideLabourCharge,LabourCharge } from '../../domain/product-concern-report.domain';
import { CustomValidators } from '../../../../../utils/custom-validators';
import * as uuid from 'uuid';
import { ObjectUtil } from '../../../../../utils/object-util';

@Injectable()
export class PcrPageStore {
    private readonly pcrForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        console.log('---------PCR Form Created---------------');
        this.pcrForm = this.fb.group({
            pcr: this.createPCR(),
            warrantyPcrImplements: this.fb.array([]),
            serviceHistory: this.fb.array([]),
            failurePart: this.fb.array([]),
            uploadFileForm: this.uploadFileForm(),
            outsideChargeForm : this.fb.array([]),
            labourChargeForm : this.fb.array([])
        });
    }

    /* private createForm() {
        console.log('---------PCR Form Created---------------');
        this.pcrForm = this.fb.group({
            pcr: this.createPCR(),
            warrantyPcrImplements: this.fb.array([]),
            serviceHistory: this.fb.array([]),
            failurePart: this.fb.array([]),
        });
    } */

    private createPCR() {
        const pcr = this.fb.group({
            pcrNo: [{value: '', disabled: true}],
            pcrDate: [{value: '', disabled: true}],
            status: [{value: '', disabled: true}],
            jobCardNo: [{value: '', disabled: true}],
            jobCardDate: [{value: '', disabled: true}],
            dateOfInstallation: [{value: '', disabled: true}],
            customerName: [{value: '', disabled: true}],
            model: [{value: '', disabled: true}],
            dateOfFailure: [{value: '', disabled: true}],
            address: [{value: '', disabled: true}],
            mobileNo: [{value: '', disabled: true}],
            registrationNumber: [{value: '', disabled: true}],
            chassisNo: [{value: '', disabled: true}],
            engineNo: [{value: '', disabled: true}],
            totalHour: [{value: '', disabled: true}],
            soldToDealer: [{value: '', disabled: true}],
            serviceDealer: [{value: '', disabled: true}],
            serviceDealerAddress: [{value: '', disabled: true}],
            crop: [{value: '', disabled: false}],
            cropCondition: [{value: '', disabled: false}],
            failureType: [{value: '', disabled: false}, Validators.required],
            soilType: [{value: '', disabled: false}],
            fieldCondition: [{value: '', disabled: false}],
            failureCount:[{value: '', disabled: false}, Validators.compose([Validators.min(2), CustomValidators.numberOnly])],
            repeatFailureFlag: [{value: '', disabled: false}],
            customerConcern: [{value: '', disabled: true}],
            mechanicObservation: [{value: '', disabled: true}],
            dealerObservation: [{value: '', disabled: false}, Validators.required],
            actionTaken: [{value: '', disabled: false}, Validators.required],
            dealerRemarks: [{value: '', disabled: false}, Validators.required],
            kaiRemarks: [{value: '', disabled: true}],
            specialApvRemarks: [{value: '', disabled: true}],
            allowVideoUpload:[{value: false, disabled: true}],
            warrantyPcrPhotos:[null],
            warrantyType:[{value: null, disabled: true}],
            warrantyEndDate:[{value: null, disabled: true}],
            delayReason:[{value: null, disabled: true}],
            delayReasonSelect:[{value: null, disabled: true}],
            totalWarrantyHour:[{value: null, disabled: true}],
            isApprovalRequired:[{value: false, disabled: false}],
        });
        return pcr;
    }
    
    private createImplementForm(addImplement?: WarrantyPcrImplement) {
        const form = this.pcrForm.controls.warrantyPcrImplements as FormArray;
        const maxPercentage = 100-ObjectUtil.sum(form.controls, 'percentOfUsage');
            return this.fb.group({
                implement: [{value: addImplement == undefined? null : addImplement.implement, disabled: false}, Validators.required],
                implementCategory: [{value: addImplement == undefined? null : addImplement.implementCategory, disabled: false}, Validators.required],
                implementMake: [{value: addImplement == undefined? null : addImplement.implementMake, disabled: false}, Validators.required],
                implementSerialNumber: [{value: addImplement == undefined? null : addImplement.implementSerialNumber, disabled: false}, Validators.compose([CustomValidators.serialNumber])],
                // gearRatio: [{value: addImplement == undefined? null : addImplement.gearRatio, disabled: false}, Validators.pattern('^[1-9]{1,3}([/:][1-9]{1,3})?$')],
                gearRatio: [{value: addImplement == undefined? null : addImplement.gearRatio, disabled: false}],
                engineRpm: [{value: addImplement == undefined? null : addImplement.engineRpm, disabled: false}, Validators.compose([CustomValidators.numberOnly])],
                percentOfUsage: [{value: addImplement == undefined? null : addImplement.percentOfUsage, disabled: false}, Validators.compose([CustomValidators.floatNumber, Validators.min(1), Validators.max(maxPercentage)])],
                usageFailure: [{value: addImplement == undefined? null : addImplement.usageFailure, disabled: false}],
                isSelected: [{value: null, disabled: false}],
                uuid: [uuid.v4()]
            });

    }

    private createServiceHistoryForm(addServiceHistory: JobCardHistory) {
        const serviceHistoryForm = this.fb.group({
            serviceType: [{value: addServiceHistory.serviceType, disabled: true}],
            dateIn: [{value: addServiceHistory.dateIn, disabled: true}],
            jobCardNo: [{value: addServiceHistory.jobCardNo, disabled: true}],
            jobCardDate: [{value: addServiceHistory.jobCardDate, disabled: true}],
            hours: [{value: addServiceHistory.hours, disabled: true}],
            pcrNo: [{value: addServiceHistory.pcrNo, disabled: true}],
            pcrDate: [{value: addServiceHistory.pcrDate, disabled: true}],
            status: [{value: addServiceHistory.status, disabled: true}],
        });
        return serviceHistoryForm;
    }
    private createFailurePartForm(addFailurePart: JobCardPart) {
        const failureCodeObj = {} as FailurePart;
        failureCodeObj.code = addFailurePart.code;
        failureCodeObj.id = addFailurePart.failureId;
        failureCodeObj.description = addFailurePart.failureDescription;
        failureCodeObj.value = addFailurePart.description;
        
        const failurePartForm = this.fb.group({
            partNo: [{value: addFailurePart.partNo, disabled: true}],
            claimQuantity: [{value: addFailurePart.claimQuantity, disabled: true}, CustomValidators.numberOnly],
            failureDescription: [{value: addFailurePart == undefined ? null:addFailurePart.failureDescription, disabled: true}],
            description: [{value: addFailurePart.description, disabled: true}],
            approvedQuantity: [{value: addFailurePart.approvedQuantity, disabled: false}, CustomValidators.numberOnly],
            failureCode: [{value: addFailurePart.code == undefined ? null:failureCodeObj, disabled: false}, Validators.compose([Validators.required, CustomValidators.selectFromList()]) ],
            id: [addFailurePart.id],
        });
        return failurePartForm;
    }
    private uploadFileForm() {
        const uploadFileForm = this.fb.group({
          uploadFile: [null, Validators.required],
          pcrVideo: [null],
        });
        return uploadFileForm;
      }

    /**
     * Following form will be used in future.
     */
    public createOutsideLabourChargeForm(outSideLabourCharge?:OutSideLabourCharge) {
        const outsideLabourChargeForm = this.fb.group({
            jobcode: [{value: outSideLabourCharge?outSideLabourCharge.jobcode:null, disabled: true}],
            jobcodeId: outSideLabourCharge?outSideLabourCharge.jobcodeId:null,
            jobcodeDesc: [{value: outSideLabourCharge?outSideLabourCharge.jobcodeDesc:null, disabled: true}],
            claimAmount: [{value: outSideLabourCharge?outSideLabourCharge.claimAmount:null, disabled: true}],
            pcrAmount: [{value: outSideLabourCharge?outSideLabourCharge.pcrAmount:null, disabled: true}],
            approvedAmount : [{value: outSideLabourCharge?outSideLabourCharge.approvedAmount:null, disabled: true}],
        });
        return outsideLabourChargeForm;
    }
    public createLabourChargeForm(labourCharge?:LabourCharge) {
        const labourChargeForm = this.fb.group({
            labourCode: [{value: labourCharge?labourCharge.labourCode:null, disabled: true}],
            labourChargeId: labourCharge?labourCharge.labourChargeId:null,
            labourDesc: [{value: labourCharge?labourCharge.labourDesc:null, disabled: true}],
            labourHour: [{value: labourCharge?labourCharge.labourHour:null, disabled: true}],
            claimAmount: [{value: labourCharge?labourCharge.claimAmount:null, disabled: true}],
            pcrAmount: [{value: labourCharge?labourCharge.pcrAmount:null, disabled: true}],
            approvedAmount: [{value: labourCharge?labourCharge.approvedAmount:null, disabled: true}],
        });
        return labourChargeForm;
    }

    initializeCreateImplementForm(addImplement?: WarrantyPcrImplement) {
        return this.createImplementForm(addImplement);
    }
    initializeCreateServiceHistoryForm(addServiceHistory: JobCardHistory) {
        return this.createServiceHistoryForm(addServiceHistory);
    }
    initializeCreateFailurePartForm(addFailurePart: JobCardPart) {
        return this.createFailurePartForm(addFailurePart);
    }

    get pcrFormAll() {
        if(this.pcrForm) {
            return this.pcrForm as FormGroup;
        }
        // this.createForm();
        // return this.pcrForm as FormGroup;
    }

}