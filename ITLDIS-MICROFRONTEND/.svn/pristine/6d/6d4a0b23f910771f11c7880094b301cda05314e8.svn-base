import { Injectable } from "@angular/core";
import { WcrPageStore } from "./warrenty-claim-request-create-page.store";
import { FormGroup, FormArray } from '@angular/forms';
import { Wcr, FailurePart, ViewWcrByWcrNo, OutSideLabourCharge, LabourCharge } from '../../domain/warrenty-claim-request.domain';
import { JobCardHistory, WarrantyPcrImplement } from '../../../product-concern-report/domain/product-concern-report.domain';
import { Operation } from '../../../../../utils/operation-util';
import { LocalStorageService } from '../../../../../root-service/local-storage.service'
@Injectable()
export class WcrPagePresenter {
   private _operation: string;

    constructor(
        private wcrPageStore: WcrPageStore,
        private localStorageService: LocalStorageService
    ) {}

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    
    get wcrForm(): FormGroup {
        return this.wcrPageStore.allForm.get('wcrForm') as FormGroup;
    }
    get implementForm(): FormArray {
        return this.wcrPageStore.allForm.get('implementForm') as FormArray;
    }
    get serviceHistoryForm(): FormArray {
        return this.wcrPageStore.allForm.get('serviceHistoryForm') as FormArray;
    }
    get concernForm(): FormGroup{
        return this.wcrPageStore.allForm.get('concernForm') as FormGroup;
    }
    get failurePartsForm(): FormArray {
        return this.wcrPageStore.allForm.get('failurePartsForm') as FormArray;
    }
    

    get outsideLabourChargeForm(): FormArray {
        return this.wcrPageStore.allForm.get('outsideChargeForm') as FormArray;
    }
    get labourChargeForm(): FormArray {
        return this.wcrPageStore.allForm.get('labourChargeForm') as FormArray;
    }
    
    get remarkForm():FormGroup {
        return this.wcrPageStore.allForm.get('remarkForm') as FormGroup;
    }

    get uploadForm():FormGroup {
        return this.wcrPageStore.allForm.get('uploadForm') as FormGroup;
    }
    get kaiInvoiceForm():FormGroup {
        return this.wcrPageStore.allForm.get('kaiVerifyForm') as FormGroup;
    }
    get loginUser() {
        return this.localStorageService.getLoginUser();
    }

    addRowInImplement(implementDetails?: WarrantyPcrImplement) {
        const control = this.implementForm;
        const newForm = this.wcrPageStore.initializeImplementForm(implementDetails);
        control.push(newForm);
    }
    addRowInServiceHistory(serviceHistoryDetails?: JobCardHistory) {
        const control = this.serviceHistoryForm;
        const newForm = this.wcrPageStore.initializeServiceHistoryForm(serviceHistoryDetails);
        control.push(newForm);
    }
    addRowInFailurePart(failurePartDetails?: FailurePart) {
        const control = this.failurePartsForm;
        const newForm = this.wcrPageStore.initializeFailurePartForm(failurePartDetails);
        control.push(newForm);
        if(this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }
    addRowInLabourCharge(labourCharge:LabourCharge){
        const control = this.labourChargeForm;
        const newForm = this.wcrPageStore.createLabourChargeForm(labourCharge);
        control.push(newForm);
        if(this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }
    
    addRowInOutsideCharge(outSideCharge:OutSideLabourCharge){
        const control = this.outsideLabourChargeForm;
        const newForm = this.wcrPageStore.createOutsideLabourChargeForm(outSideCharge);
        control.push(newForm);
        if(this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }

    patchWcrForm(pcrData: Wcr, extraData?: ViewWcrByWcrNo) {
        this.wcrForm.get('dealerName').patchValue(pcrData.serviceDealer);
        this.wcrForm.get('dealerAddress').patchValue(pcrData.serviceDealerAddress);
        this.wcrForm.get('dealerEmail').patchValue(pcrData.emailId);
        this.wcrForm.get('dealerCode').patchValue(pcrData.dealerCode);
        this.wcrForm.get('dealerMobNo').patchValue(pcrData.mobileNumber);
        this.wcrForm.get('soldToDealer').patchValue(pcrData.soldToDealer)
        
        this.wcrForm.get('jobCardNo').patchValue(pcrData.serviceJobCard.jobCardNo);
        this.wcrForm.get('jobCardDate').patchValue(pcrData.serviceJobCard.jobCardDate);
        this.wcrForm.get('pcrNo').patchValue(pcrData.pcrNo);
        this.wcrForm.get('pcrDate').patchValue(pcrData.pcrDate);
        this.wcrForm.get('goodwillNo').patchValue(pcrData.goodwillNo);
        this.wcrForm.get('goodwillDate').patchValue(pcrData.goodwillDate);
        this.wcrForm.get('dateofFailure').patchValue(pcrData.serviceJobCard.dateOfFailure);
        
        this.wcrForm.get('machineModel').patchValue(pcrData.serviceJobCard.machineInventory.machineMaster.model);
        this.wcrForm.get('chassisNo').patchValue(pcrData.serviceJobCard.machineInventory.chassisNo);
        this.wcrForm.get('engineNo').patchValue(pcrData.serviceJobCard.machineInventory.engineNo);
        
        this.wcrForm.get('failureType').patchValue(pcrData.failureType);
       
        // this.wcrForm.get('retroFitmentNo').patchValue();
        this.wcrForm.get('dateofInstallation').patchValue(pcrData.serviceJobCard.machineInventory.installationDate);
        this.wcrForm.get('dateofRepair').patchValue(pcrData.serviceJobCard.dateOfRepair);
        this.concernForm.get('customerConcern').patchValue(pcrData.serviceJobCard.customerConcern);
        this.concernForm.get('dealerObservation').patchValue(pcrData.dealerObservation);
        this.concernForm.get('actionTaken').patchValue(pcrData.actionTaken);
        
                
        if(pcrData.serviceJobCard.customerMaster != null) {
            if(pcrData.serviceJobCard.customerMaster.customerName == null)
                pcrData.serviceJobCard.customerMaster.customerName = ''
            if(pcrData.serviceJobCard.customerMaster.lastName == null)
                pcrData.serviceJobCard.customerMaster.lastName = ''
            this.wcrForm.get('customerName').patchValue(`${pcrData.serviceJobCard.customerMaster.customerName} ${pcrData.serviceJobCard.customerMaster.lastName}`);
            this.wcrForm.get('customerAddress').patchValue(pcrData.serviceJobCard.customerMaster.address);
            this.wcrForm.get('customerMobNo').patchValue(pcrData.serviceJobCard.customerMaster.mobileNo);
        }
        this.wcrForm.get('hours').patchValue(pcrData.serviceJobCard.totalHour);
        if(extraData) {
            this.wcrForm.get('wcrNo').patchValue(extraData.wcrNo);
            this.wcrForm.get('wcrDate').patchValue(extraData.wcrDate);
            this.wcrForm.get('wcrType').patchValue(extraData.wcrType);
            this.wcrForm.get('wcrStatus').patchValue(extraData.wcrStatus);
            this.remarkForm.get('kaiRemark').patchValue(extraData.kaiRemark);
            this.remarkForm.get('inspectionRemark').patchValue(extraData.inspectionRemark);
        }
    }


    getIncoiceInputValue(event) 
    {
    const inputValue = event.target.value;
  }
    

}