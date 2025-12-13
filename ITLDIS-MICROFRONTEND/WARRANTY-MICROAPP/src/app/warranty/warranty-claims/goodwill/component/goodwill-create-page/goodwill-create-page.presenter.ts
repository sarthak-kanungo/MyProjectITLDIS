import { Injectable } from '@angular/core';
import { GoodwillPageStore } from './goodwill-create-page.store';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { JobCardView, ViewPCRDomain, WarrantyPcrImplement, JobCardHistory, WarrantyPart,OutSideLabourCharge,LabourCharge } from '../../../product-concern-report/domain/product-concern-report.domain';
import { Operation } from '../../../../../utils/operation-util';
import { GoodwillView } from '../../domain/goodwill.domain';
import { LocalStorageService } from '../../../../../root-service/local-storage.service';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { UploadableFile } from 'src/app/ui/file-upload/file-upload';

@Injectable()
export class GoodwillPagePresenter {
    private _operation: string;
    collectFiles: UploadableFile[];

    constructor(
        private goodwillPageStore: GoodwillPageStore,
        private localStorageService: LocalStorageService
    ) {}
    get loginUser() {
        return this.localStorageService.getLoginUser();
    }
    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    get uploadFileForm(): FormGroup {
        return this.goodwillPageStore.allForm.get('uploadFileForm') as FormGroup;
      }
    get goodwillForm(): FormGroup {
        return this.goodwillPageStore.allForm.get('goodwillForm') as FormGroup;
    }
    get implementForm(): FormArray {
        return this.goodwillPageStore.allForm.get('implementForm') as FormArray;
    }
    get serviceHistoryForm(): FormArray {
        return this.goodwillPageStore.allForm.get('serviceHistoryForm') as FormArray;
    }
    get concernForm(): FormGroup{
        return this.goodwillPageStore.allForm.get('concernForm') as FormGroup;
    }
    get failurePartsForm(): FormArray {
        return this.goodwillPageStore.allForm.get('failurePartsForm') as FormArray;
    }
    get remarkForm():FormGroup {
        return this.goodwillPageStore.allForm.get('remarkForm') as FormGroup;
    }
    get OutsideLabourChargeForm(): FormArray {
        return this.goodwillPageStore.allForm.get('outsideChargeForm') as FormArray;
    }
    get LabourChargeForm(): FormArray {
        return this.goodwillPageStore.allForm.get('labourChargeForm') as FormArray;
    }
    addRowInImplement(implementDetails?: WarrantyPcrImplement) {
        const control = this.implementForm;
        const newForm = this.goodwillPageStore.initializeImplementForm(implementDetails);
        control.push(newForm);
    }
    deleteRowInImplement() {
        const deleteRow = this.implementForm.value.filter(val => val.isSelected);;
        if ((this.implementForm.value.length-deleteRow.length) > 0 ) {
            const implementControl = this.implementForm;
            const notSelected =  implementControl.controls.filter(ctrl => !ctrl.value.isSelected);
            implementControl.clear();
            notSelected.forEach(elt => {implementControl.push(elt)});
        }
    }
    addRowInServiceHistory(serviceHistoryDetails?: JobCardHistory) {
        const control = this.serviceHistoryForm;
        const newForm = this.goodwillPageStore.initializeServiceHistoryForm(serviceHistoryDetails);
        control.push(newForm);
    }
    addRowInFailurePart(failurePartDetails?: WarrantyPart,viewName?: string) {
        const control = this.failurePartsForm;
        const newForm = this.goodwillPageStore.initializeFailurePartForm(failurePartDetails);

        if(viewName == 'approve'){
            newForm.controls.goodwillClaimAcceptedQuantity.enable();
            newForm.controls.goodwillClaimAcceptedQuantity.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly, CustomValidators.max(newForm.controls.goodwillClaimQuantity,''), Validators.min(0)]));
            if(failurePartDetails && failurePartDetails.hodLogin){
                newForm.controls.acceptedPercentage.enable();
                newForm.controls.priceType.enable();
                newForm.controls.acceptedPercentage.setValidators(Validators.compose([CustomValidators.numberOnly,Validators.max(100)]))
            }
        }
        if(this.operation === Operation.CREATE){
            newForm.controls.goodwillClaimQuantity.enable();
            //newForm.controls.priceType.enable();
            newForm.controls.goodwillClaimQuantity.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly ,CustomValidators.max(newForm.controls.rejectedQuantity,''), Validators.min(0)]));
        }
        
        control.push(newForm);

    }
    addRowInOutsideCharge(outSideLabourCharge: OutSideLabourCharge,viewName?: string) {
        let chargeControls =  this.OutsideLabourChargeForm;
        let newForm = this.goodwillPageStore.createOutsideLabourChargeForm(outSideLabourCharge);
        if(viewName == 'approve'){
            newForm.controls.goodwillApprovedAmount.enable();
            newForm.controls.goodwillApprovedAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly, CustomValidators.max(newForm.controls.goodwillClaimAmount), Validators.min(0)]));
        }
        if(this.operation === Operation.CREATE){
            newForm.controls.goodwillClaimAmount.enable();
            newForm.controls.goodwillClaimAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly ,CustomValidators.max(newForm.controls.rejectedAmount), Validators.min(0)]));
        }
        chargeControls.push(newForm);
    }
    addRowInLabourCharge(labourCharge: LabourCharge,viewName?: string) {
        let chargeControls =  this.LabourChargeForm;
        let newForm = this.goodwillPageStore.createLabourChargeForm(labourCharge);
        if(viewName == 'approve'){
            newForm.controls.goodwillApprovedAmount.enable();
            newForm.controls.goodwillApprovedAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly ,CustomValidators.zeroOrEqual(newForm.controls.goodwillClaimAmount,'Amount should be either zero or equal to claim amount')]));
        }
        if(this.operation === Operation.CREATE){
            newForm.controls.goodwillClaimAmount.enable();
            newForm.controls.goodwillClaimAmount.setValidators(Validators.compose([Validators.required, CustomValidators.numberOnly ,CustomValidators.zeroOrEqual(newForm.controls.rejectedAmount,'Amount should be either zero or equal to rejected amount')]));
        }
        chargeControls.push(newForm);
    }
    disableForm(viewName) {
        this.goodwillForm.disable();
        this.concernForm.disable()
        this.remarkForm.disable()
        if(viewName === 'approve'){
            this.remarkForm.controls.kaiRemarks.enable();
            this.remarkForm.controls.kaiRemarks.setValidators(Validators.required);
            this.goodwillForm.controls.budgetConsumed.enable();
        }
    }

    collectPCRFiles(files: UploadableFile[]) {
        this.collectFiles = files;
    }
    patchFormVal(patchObj: JobCardView) {
        this.goodwillForm.patchValue(patchObj);
        this.concernForm.patchValue(patchObj);
        this.remarkForm.patchValue(patchObj);
    }

    patchViewPCRForm(viewPCRDetails: ViewPCRDomain, editForm?: GoodwillView) {
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
        patchForm.pcrNo = viewPCRDetails.pcrNo;
        patchForm.id = viewPCRDetails.id;
        patchForm.repeatFailureFlag = viewPCRDetails.repeatFailure;
        patchForm.failureCount = viewPCRDetails.noOfTimes;
        patchForm.jobCardDate = viewPCRDetails.serviceJobCard.jobCardDate;
        patchForm.jobCardNo = viewPCRDetails.serviceJobCard.jobCardNo;
        patchForm.registrationNumber = viewPCRDetails.serviceJobCard.machineInventory.registrationNumber;
        patchForm.serviceDealer = viewPCRDetails.serviceDealer;
        patchForm.serviceDealerAddress = viewPCRDetails.serviceDealerAddress;
        patchForm.soldToDealer = viewPCRDetails.soldToDealer;
        patchForm.chassisNo = viewPCRDetails.serviceJobCard.machineInventory.chassisNo;
        patchForm.engineNo = viewPCRDetails.serviceJobCard.machineInventory.engineNo;
        patchForm.model = viewPCRDetails.serviceJobCard.machineInventory.machineMaster.model;
        patchForm.warrantyPcrImplements = viewPCRDetails.warrantyPcrImplements;
        patchForm.warrantyPcrPhotos = viewPCRDetails.warrantyPcrPhotos;

        patchForm.dealerRemarks = viewPCRDetails.dealerRemarks;
        patchForm.pcrRemark = viewPCRDetails.kaiRemarks;
        if(editForm)
            patchForm.kaiRemarks = editForm.kaiRemark;
        
        patchForm.customerConcern = viewPCRDetails.serviceJobCard.customerConcern;
        patchForm.mechanicObservation = viewPCRDetails.serviceJobCard.mechanicObservation;
        patchForm.dealerObservation = viewPCRDetails.dealerObservation;
        patchForm.actionTaken = viewPCRDetails.actionTaken;

        if(viewPCRDetails.serviceJobCard.customerMaster != null) {
            patchForm.dateOfFailure = viewPCRDetails.serviceJobCard.dateOfFailure;
            patchForm.customerName = `${viewPCRDetails.serviceJobCard.customerMaster.customerName} ${viewPCRDetails.serviceJobCard.customerMaster.lastName}`;
            patchForm.mobileNo = viewPCRDetails.serviceJobCard.customerMaster.mobileNo;
            patchForm.address = viewPCRDetails.serviceJobCard.customerMaster.address;
            patchForm.totalHour = viewPCRDetails.serviceJobCard.totalHour;
        }

        if(this.operation == Operation.VIEW) {
            patchForm.goodwillNo = editForm.goodwillNo;
            patchForm.goodwillDate = editForm.goodwillDate;
            patchForm.status = editForm.status;
            patchForm.goodwillType = editForm.goodwillType;
            console.log('editForm.goodwillType', editForm.goodwillType);
            console.log('patchForm.goodwillType', patchForm.goodwillType);
            patchForm.goodwillReason = editForm.goodwillReason;
        }
        return patchForm;
    }
}