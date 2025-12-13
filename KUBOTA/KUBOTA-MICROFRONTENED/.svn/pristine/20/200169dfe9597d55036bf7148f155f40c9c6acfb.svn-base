import { Injectable } from "@angular/core";
import { PcrPageStore } from './pcr-page.store';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { JobCardView, JobCardHistory, JobCardPart, ViewPCRDomain, WarrantyPcrImplement,OutSideLabourCharge,LabourCharge } from '../../domain/product-concern-report.domain';

import { Operation } from '../../../../../utils/operation-util';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { UploadableFile } from "src/app/ui/file-upload/file-upload";

@Injectable()
export class PcrPagePresenter {
    collectFiles: UploadableFile[];
    collectVideo: UploadableFile[];
    viewName: string;
    private _operation: string;
    currentDate: string;
    constructor(
        private pcrPageStore: PcrPageStore,
        private localStorageService: LocalStorageService
    ) {

    }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    get PCRForm(): FormGroup {
        return this.pcrPageStore.pcrFormAll.get('pcr') as FormGroup;
    }
    get ImplementForm(): FormArray {
        return this.pcrPageStore.pcrFormAll.get('warrantyPcrImplements') as FormArray;
    }
    get ServiceHistoryForm(): FormArray {
        return this.pcrPageStore.pcrFormAll.get('serviceHistory') as FormArray;
    }
    get OutsideLabourChargeForm(): FormArray {
        return this.pcrPageStore.pcrFormAll.get('outsideChargeForm') as FormArray;
    }
    get LabourChargeForm(): FormArray {
        return this.pcrPageStore.pcrFormAll.get('labourChargeForm') as FormArray;
    }
    get FailurePartForm(): FormArray {
        return this.pcrPageStore.pcrFormAll.get('failurePart') as FormArray;
    }

    get uploadFileForm(): FormGroup {
        return this.pcrPageStore.pcrFormAll.get('uploadFileForm') as FormGroup;
      }

    get loginUser() {
        return this.localStorageService.getLoginUser();
    }

    patchFormVal(patchObj: JobCardView) {
        this.PCRForm.patchValue(patchObj);
    }
    addRowInImplement(addImplement?: WarrantyPcrImplement) {
        const newForm = this.pcrPageStore.initializeCreateImplementForm(addImplement);
        if(this.PCRForm.get('customerName').value == null || this.PCRForm.get('customerName').value == ' ') {
            this.clearImplementValidation(newForm);
        }
        this.PCRForm.get('customerName').valueChanges.forEach(val => {     
            if(val == ' ' || val == null) {
              this.clearImplementValidation(newForm);
            }
        });
        this.ImplementForm.push(newForm);
        return newForm;
    }

    deleteRowInImplement() {
        const deleteRow = this.ImplementForm.value.filter(val => val.isSelected);;
        if ((this.ImplementForm.value.length-deleteRow.length) > 0 ) {
            const implementControl = this.ImplementForm;
            const notSelected =  implementControl.controls.filter(ctrl => !ctrl.value.isSelected);
            implementControl.clear();
            notSelected.forEach(elt => {implementControl.push(elt)});
        }
    }

    clearImplementValidation(newForm: FormGroup) {
        newForm.get('implement').clearValidators();
        newForm.get('implement').updateValueAndValidity({emitEvent: false});
        newForm.get('implementCategory').clearValidators();
        newForm.get('implementCategory').updateValueAndValidity({emitEvent: false});
        newForm.get('implementMake').clearValidators();
        newForm.get('implementMake').updateValueAndValidity({emitEvent: false});
    }
    setImplemntValidation(newForm: FormGroup) {
        newForm.get('implement').setValidators(Validators.required);
        newForm.get('implementCategory').setValidators(Validators.required);
        newForm.get('implementMake').setValidators(Validators.required);
    }
    implementFormValueChanges(newForm: FormGroup) {
        console.log('implementFormValueChanges');
        newForm.get('implement').valueChanges.subscribe(val => {
            console.log('val', val)
            val ? this.setImplemntValidation(newForm) : this.clearImplementValidation(newForm);
        })
    }

    clearPcrFormValidation() {
        this.PCRForm.get('crop').clearValidators();
        this.PCRForm.get('crop').updateValueAndValidity();
        this.PCRForm.get('cropCondition').clearValidators();
        this.PCRForm.get('cropCondition').updateValueAndValidity();
        this.PCRForm.get('soilType').clearValidators();
        this.PCRForm.get('soilType').updateValueAndValidity();
        this.PCRForm.get('fieldCondition').clearValidators();
        this.PCRForm.get('fieldCondition').updateValueAndValidity();
    }

    addRowInServiceHistory(addServiceHistory: JobCardHistory) {
        const implementControl = this.ServiceHistoryForm;
        const newForm = this.pcrPageStore.initializeCreateServiceHistoryForm(addServiceHistory);
        implementControl.push(newForm);
    }
    addRowInOutsideCharge(outSideLabourCharge: OutSideLabourCharge,viewName?: string) {
        let chargeControls =  this.OutsideLabourChargeForm;
        let newForm = this.pcrPageStore.createOutsideLabourChargeForm(outSideLabourCharge);
        if(viewName == 'approve'){
            newForm.controls.approvedAmount.enable();
            newForm.controls.approvedAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly, CustomValidators.max(newForm.controls.claimAmount,'Amount should be less than or equal to claim amount')]));
        }
        chargeControls.push(newForm);
    }
    addRowInLabourCharge(labourCharge: LabourCharge,viewName?: string) {
        let chargeControls =  this.LabourChargeForm;
        let newForm = this.pcrPageStore.createLabourChargeForm(labourCharge);
        if(viewName == 'approve'){
            newForm.controls.approvedAmount.enable();
            newForm.controls.approvedAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly ,CustomValidators.zeroOrEqual(newForm.controls.claimAmount,'Amount should be either zero or equal to claim amount')]));
        }
        chargeControls.push(newForm);
    }
    addRowInFailurePart(addFailurePart: JobCardPart, viewName?: string) {
        if(viewName && viewName === 'approve'){
            addFailurePart.approvedQuantity == null ? addFailurePart.approvedQuantity = addFailurePart.claimQuantity : addFailurePart.approvedQuantity;
        }
        const implementControl = this.FailurePartForm;
        const newForm = this.pcrPageStore.initializeCreateFailurePartForm(addFailurePart);
        if(viewName == 'approve') {
            newForm.get('approvedQuantity').enable();
           // newForm.get('failureCode').disable();
        }
        else if(viewName == 'pcrNo') {
            newForm.get('approvedQuantity').disable();
            newForm.get('failureCode').disable();
        }
        
        /*if(newForm.get('approvedQuantity').value===null || newForm.get('approvedQuantity').value===undefined) {
            newForm.get('approvedQuantity').patchValue(newForm.get('claimQuantity').value); 
        }*/
        if (this.operation == Operation.EDIT) {
            newForm.get('approvedQuantity').disable();
            
            if(addFailurePart.code == null || addFailurePart.code == '') {
                newForm.get('failureCode').enable();
               // newForm.get('claimQuantity').enable();
            } 
            else {
                newForm.get('failureCode').disable();
              //  newForm.get('claimQuantity').disable();
            }
        }
        implementControl.push(newForm);
    }

    collectPCRFiles(files: UploadableFile[]) {
        this.collectFiles = files;
    }
    collectPCRVideo(files: UploadableFile[]) {
        this.collectVideo = files;
    }

    patchViewPCRForm(viewPCRDetails: ViewPCRDomain) {
        const patchForm = {} as JobCardView;
        patchForm.pcrNo = viewPCRDetails.pcrNo;
        patchForm.pcrDate = viewPCRDetails.pcrDate;
        patchForm.status = viewPCRDetails.status;
        patchForm.crop = viewPCRDetails.crop;
        patchForm.dateOfInstallation = viewPCRDetails.serviceJobCard.machineInventory.dateOfInstallation;
        patchForm.cropCondition = viewPCRDetails.cropCondition;
        patchForm.failureType = viewPCRDetails.failureType;
        patchForm.fieldCondition = viewPCRDetails.fieldCondition;
        patchForm.soilType = viewPCRDetails.soilType;
        patchForm.dealerObservation = viewPCRDetails.dealerObservation;
        patchForm.actionTaken = viewPCRDetails.actionTaken;
        patchForm.dealerRemarks = viewPCRDetails.dealerRemarks;
        patchForm.kaiRemarks = viewPCRDetails.kaiRemarks;
        patchForm.specialApvRemarks = viewPCRDetails.specialApvRemarks;
        patchForm.pcrNo = viewPCRDetails.pcrNo;
        patchForm.id = viewPCRDetails.id;
        patchForm.repeatFailureFlag = viewPCRDetails.repeatFailure;
        patchForm.failureCount = viewPCRDetails.noOfTimes;
        patchForm.delayReason = viewPCRDetails.delayReason;
        patchForm.jobCardDate = viewPCRDetails.serviceJobCard.jobCardDate;
        patchForm.jobCardNo = viewPCRDetails.serviceJobCard.jobCardNo;
        patchForm.registrationNumber = viewPCRDetails.serviceJobCard.machineInventory.registrationNumber;
        patchForm.customerConcern = viewPCRDetails.serviceJobCard.customerConcern;
        patchForm.mechanicObservation = viewPCRDetails.serviceJobCard.mechanicObservation;
        patchForm.dateOfFailure = viewPCRDetails.serviceJobCard.dateOfFailure;
        patchForm.allowVideoUpload = viewPCRDetails.allowVideoUpload;
        patchForm.serviceDealer = viewPCRDetails.serviceDealer;
        patchForm.serviceDealerAddress = viewPCRDetails.serviceDealerAddress;
        patchForm.soldToDealer = viewPCRDetails.soldToDealer;

        patchForm.chassisNo = viewPCRDetails.serviceJobCard.machineInventory.chassisNo;
        patchForm.engineNo = viewPCRDetails.serviceJobCard.machineInventory.engineNo;
        patchForm.model = viewPCRDetails.serviceJobCard.machineInventory.machineMaster.model;
        
        patchForm.warrantyPcrImplements = viewPCRDetails.warrantyPcrImplements;
        patchForm.warrantyPcrPhotos = viewPCRDetails.warrantyPcrPhotos;
        this.uploadFileForm.get('uploadFile').patchValue(viewPCRDetails.warrantyPcrPhotos);

        if(viewPCRDetails.serviceJobCard.customerMaster != null) {
            patchForm.customerName = `${viewPCRDetails.serviceJobCard.customerMaster.customerName} ${viewPCRDetails.serviceJobCard.customerMaster.lastName}`;
            patchForm.mobileNo = viewPCRDetails.serviceJobCard.customerMaster.mobileNo;
            patchForm.address = viewPCRDetails.serviceJobCard.customerMaster.address;
            patchForm.totalHour = viewPCRDetails.serviceJobCard.totalHour;
        }

        // this.PCRForm.get('failureCount').patchValue(viewPCRDetails.noOfTimes);
        // this.PCRForm.get('repeatFailureFlag').patchValue(viewPCRDetails.repeatFailure);
        return patchForm;
    }

}
