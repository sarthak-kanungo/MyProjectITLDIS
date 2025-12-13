import { Injectable } from "@angular/core";
import { LogSheetPageStore } from './log-sheet-page.store';
import { FormGroup, FormArray } from '@angular/forms';
import { LogSheet, JobCardPart, ViewLogSheet, LogsheetImplements } from '../../domain/log-sheet.domain';
import { JobCardHistory } from '../../../product-concern-report/domain/product-concern-report.domain';
import { Operation } from '../../../../../utils/operation-util';
import { UploadableFile } from "src/app/ui/file-upload/file-upload";


@Injectable()
export class LogSheetPagePresenter {
    collectFiles: UploadableFile[] = [];
    collectVideo: UploadableFile[] = [];
    formLength: number;
    machineInventoryId: number
    private _operation: string;

    constructor(
        private logSheetPageStore: LogSheetPageStore,
    ) { }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    get LogSheetForm(): FormGroup {
        return this.logSheetPageStore.logSheetForm.get('logsheetForm') as FormGroup;
    }
    get ImplementForm(): FormArray {
        return this.logSheetPageStore.logSheetForm.get('implement') as FormArray;
    }
    get FailureForm(): FormArray {
        return this.logSheetPageStore.logSheetForm.get('failurePart') as FormArray;;
    }
    get ServiceHistoryForm(): FormArray {
        return this.logSheetPageStore.logSheetForm.get('serviceHistory') as FormArray;;
    }
    get RemarkForm(): FormGroup {
        return this.logSheetPageStore.logSheetForm.get('remark') as FormGroup;
    }
    patchFormVal(patchObj: LogSheet) {
        // if (patchObj.jobCardNo) {
        //     delete patchObj.jobCardNo;
        // }
        // console.log('patchObj', patchObj);
        this.LogSheetForm.patchValue(patchObj);
        console.log(patchObj,'obj')
    }
    patchVinId(patchObj: LogSheet) {
        console.log('patchObj--machineInventoryId', patchObj.machineInventoryId);
        this.machineInventoryId = patchObj.machineInventoryId
    }
    get machineInventory() {
        return this.machineInventoryId
    }


    addRowInServiceHistory(addServiceHistory: JobCardHistory) {
        const implementControl = this.logSheetPageStore.logSheetForm.get('serviceHistory') as FormArray;
        const newForm = this.logSheetPageStore.initializeCreateServiceHistoryForm(addServiceHistory);
        implementControl.push(newForm);
    }

    addRowInImplement(implementDetails?: LogsheetImplements) {
        const implementControl = this.logSheetPageStore.logSheetForm.get('implement') as FormArray;
        const newForm = this.logSheetPageStore.initializeCreateImplementForm(implementDetails);
        implementControl.push(newForm);
        this.formLength = implementControl.length;
        if (this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }

    deleteRowInImplement() {
        const deleteRow = this.logSheetPageStore.logSheetForm.get('implement').value.filter(val => val.isSelected);
        if ((this.logSheetPageStore.logSheetForm.get('implement').value.length - deleteRow.length) > 0) {
            let implementControl = this.logSheetPageStore.logSheetForm.get('implement') as FormArray;
            let notSelected = implementControl.controls.filter(ctrl => !ctrl.value.isSelected);
            implementControl.clear();
            notSelected.forEach(elt => { implementControl.push(elt) });
            this.formLength = implementControl.length;
        }
    }
    clearRowFailerPart() {
        const control = this.logSheetPageStore.logSheetForm.get('failurePart') as FormArray;
        control.clear();
    }
    clearRowServiceHistory() {
        const control = this.logSheetPageStore.logSheetForm.get('serviceHistory') as FormArray;
        control.clear();
    }
    addRowInFailurePart(failurePart?: JobCardPart) {
        const remarkControl = this.logSheetPageStore.logSheetForm.get('failurePart') as FormArray;
        const newForm = this.logSheetPageStore.initializeFailurePartForm(failurePart);
    // push the form 
         remarkControl.push(newForm);
        
        if (this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }
    deleteRowInFailurePart() {
        const deleteRow = this.logSheetPageStore.logSheetForm.get('failurePart').value.filter(val => val.isSelected);
        if ((this.logSheetPageStore.logSheetForm.get('failurePart').value.length - deleteRow.length) > 0) {
            const failurePartControl = this.logSheetPageStore.logSheetForm.get('failurePart') as FormArray;
            const notSelected = failurePartControl.controls.filter(ctrl => !ctrl.value.isSelected);
            failurePartControl.clear();
            notSelected.forEach(elt => { failurePartControl.push(elt) });
        }
    }

    addRowInRemark() {
        const remarkControl = this.logSheetPageStore.logSheetForm.get('remark').get('attachFile') as FormArray;
        const newForm = this.logSheetPageStore.initializeRemarkUploadFile();
        remarkControl.push(newForm);
    }

    collectPCRFiles(files: UploadableFile[]) {
        this.collectFiles = files;
    }

    collectPCRVideo(files: UploadableFile[]) {
        this.collectVideo = files;
    }
    // patchViewLogsheetForm(viewDetails: ViewLogSheet) {
    //     const patchForm = {} as LogSheet;
    //     patchForm.id = viewDetails.id;
    //     patchForm.logsheetNo = viewDetails.logsheetNo;
    //     patchForm.failureDate = viewDetails.failureDate;
    //     patchForm.logsheetDate = viewDetails.logsheetDate;
    //     patchForm.status = viewDetails.status;
    //     patchForm.logsheetType = viewDetails.logsheetType;
    //     patchForm.installationDate = viewDetails.serviceJobCard.installationDate;

    //     patchForm.registrationNumber = viewDetails.serviceJobCard.registrationNumber;

    //     patchForm.hours = viewDetails.hours;
    //     patchForm.crop = viewDetails.crop;
    //     patchForm.cropCondition = viewDetails.cropCondition;
    //     patchForm.failureType = viewDetails.failureType;
    //     patchForm.soilType = viewDetails.soilType;
    //     patchForm.fieldCondition = viewDetails.fieldCondition;
    //     // patchForm.noOfTime
    //     // patchForm.repeatFailure
    //     patchForm.probableCause = viewDetails.probableCause;
    //     patchForm.resultOfConfirmation = viewDetails.resultOfConfirmation;
    //     patchForm.tentativeAction = viewDetails.tentativeAction;
    //     patchForm.remarks = viewDetails.remarks;
    //     patchForm.qaRemarks = viewDetails.qaRemarks;
    //     patchForm.serviceRemarks = viewDetails.serviceRemarks;

    //     console.log('viewDetails.serviceJobCard', viewDetails.serviceJobCard);
    //     if (viewDetails.serviceJobCard != null) {
    //        let jobcardobj = {code: viewDetails.serviceJobCard.jobCardNo,
    //                 value: viewDetails.serviceJobCard.jobCardNo,
    //                 machineInvetoryId: viewDetails.serviceJobCard.machineInventory.vinId,
    //                 id:viewDetails.serviceJobCard.id
    //             }

    //         if(patchForm.logsheetType == 'Manual Entry'){
    //             patchForm.linkjobCardNo = jobcardobj;
            
    //         }  else {
    //             patchForm.jobCardNo = jobcardobj;
    //             patchForm.jobCardDate = viewDetails.serviceJobCard.jobCardDate;
    //             patchForm.pcrNumber = viewDetails.serviceJobCard.pcrNo;
    //         }  
    //         patchForm.customerName = `${viewDetails.serviceJobCard.customerMaster.firstName} ${viewDetails.serviceJobCard.customerMaster.lastName}`;
    //         patchForm.model = viewDetails.serviceJobCard.machineInventory.machineMaster.model;
    //         patchForm.chassisNo = viewDetails.serviceJobCard.machineInventory.chassisNo;
    //         patchForm.engineNo = viewDetails.serviceJobCard.machineInventory.engineNo;
    //         patchForm.soldToDealer = viewDetails.serviceJobCard.soldDealer;
    //         patchForm.serviceDealer = viewDetails.serviceJobCard.serviceDealer;
    //         patchForm.serviceDealerAddress = viewDetails.serviceJobCard.serviceDealerCity;
    //         patchForm.address = viewDetails.serviceJobCard.customerMaster.address1;
    //         // patchForm.mobileNumber = viewDetails.serviceJobCard.customerMaster.
    //         // patchForm.registrationNumber
    //     } else {
    //         patchForm.model = viewDetails.machineInventory.machineMaster.model;
    //         patchForm.chassisNo = viewDetails.machineInventory.chassisNo;
    //         patchForm.engineNo = viewDetails.machineInventory.engineNo;
    //     }

    //     return patchForm;
    // }
    patchViewLogsheetForm(viewDetails: ViewLogSheet) {
        console.log(viewDetails,'viewDateils')
        const patchForm = {} as LogSheet;
        patchForm.id = viewDetails.id;
        patchForm.logsheetNo = viewDetails.logsheetNo;
        patchForm.failureDate = viewDetails.failureDate;
        patchForm.logsheetDate = viewDetails.logsheetDate;
        patchForm.status = viewDetails.status;
        console.log( patchForm.status,' patchForm.status')
        patchForm.logsheetType = viewDetails.logsheetType;
        patchForm.hours = viewDetails.hours;
        patchForm.crop = viewDetails.crop;
        patchForm.cropCondition = viewDetails.cropCondition;
        patchForm.failureType = viewDetails.failureType;
        patchForm.soilType = viewDetails.soilType;
        patchForm.fieldCondition = viewDetails.fieldCondition;
        patchForm.probableCause = viewDetails.probableCause;
        patchForm.resultOfConfirmation = viewDetails.resultOfConfirmation;
        patchForm.tentativeAction = viewDetails.tentativeAction;
        patchForm.remarks = viewDetails.remarks;
        patchForm.qaRemarks = viewDetails.qaRemarks;
        patchForm.serviceRemarks = viewDetails.serviceRemarks;

        console.log('viewDetails.serviceJobCard', viewDetails.serviceJobCard);
        if (viewDetails.serviceJobCard != null) {
           let jobcardobj = {
                    code: viewDetails.serviceJobCard.jobCardNo,
                    value: viewDetails.serviceJobCard.jobCardNo,
                    machineInvetoryId: viewDetails.serviceJobCard.machineInventory.vinId,
                    id:viewDetails.serviceJobCard.id
                }

            if(patchForm.logsheetType == 'Manual Entry'){
                patchForm.linkjobCardNo = jobcardobj;
            
            }  else {
                patchForm.jobCardNo = jobcardobj;
                patchForm.jobCardDate = viewDetails.serviceJobCard.jobCardDate;
                patchForm.pcrNumber = viewDetails.serviceJobCard.pcrNo;
            }  
            patchForm.customerName = `${viewDetails.serviceJobCard.customerMaster.firstName} ${viewDetails.serviceJobCard.customerMaster.lastName}`;
            patchForm.model = viewDetails.serviceJobCard.machineInventory.machineMaster.model;
            patchForm.chassisNo = viewDetails.serviceJobCard.machineInventory.chassisNo;
            patchForm.engineNo = viewDetails.serviceJobCard.machineInventory.engineNo;
            patchForm.soldToDealer = viewDetails.serviceJobCard.soldDealer;
            patchForm.serviceDealer = viewDetails.serviceJobCard.serviceDealer;
            patchForm.serviceDealerAddress = viewDetails.serviceJobCard.serviceDealerCity;
            patchForm.address = viewDetails.serviceJobCard.customerMaster.address1;
            
        patchForm.installationDate = viewDetails.serviceJobCard.installationDate;
        patchForm.pcrNumber = viewDetails.serviceJobCard.pcrNo;
        patchForm.registrationNumber = viewDetails.serviceJobCard.registrationNumber;
            
        } else {
            patchForm.model = viewDetails.machineInventory.machineMaster.model;
            patchForm.chassisNo = viewDetails.machineInventory.chassisNo;
            patchForm.engineNo = viewDetails.machineInventory.engineNo;
        }

        return patchForm;
    }

}
