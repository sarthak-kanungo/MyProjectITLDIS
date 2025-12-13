import { Injectable } from "@angular/core";
import { RfcPageStore } from './retro-fitment-campaign-create-page.store';
import { FormGroup, FormArray } from '@angular/forms';
import { Rfc, MaterialDetails, LabourDetails } from '../../domain/retro-fitment-campaign.domain';
import { Operation } from '../../../../../utils/operation-util';
import { UploadableFile } from "src/app/ui/file-upload/file-upload";

@Injectable()
export class RfcPagePresenter {
    collectFiles: UploadableFile[];
    chassisFile: File;
    private _operation: string;
    constructor(
        private rfcPageStore: RfcPageStore,
    ) { }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    get RFCForm(): FormGroup {
        return this.rfcPageStore.rfcFormAll.get('formRFC') as FormGroup;
    }
    get MaterialDetailsForm(): FormArray {
        return this.rfcPageStore.rfcFormAll.get('materialDetail') as FormArray;
    }
    get LabourChargesForm(): FormArray {
        // console.log(this.rfcPageStore.rfcFormAll.get('labourCharge'), 'labourrr')
        return this.rfcPageStore.rfcFormAll.get('labourCharge') as FormArray;
    }

    addRowInMaterialDetails(addMaterialDetails?: MaterialDetails) {
        const contrl = this.rfcPageStore.rfcFormAll.get('materialDetail') as FormArray;
        const newForm = this.rfcPageStore.initializeCreateMaterialDetailsForm(addMaterialDetails);
        contrl.push(newForm);
        if (this._operation == Operation.VIEW) {
            newForm.disable();
        }
    }

    deleteRowInMaterialDetails() {
        const deleteRow = this.rfcPageStore.rfcFormAll.get('materialDetail').value.filter(val => val.isSelected);
        if ((this.rfcPageStore.rfcFormAll.get('materialDetail').value.length - deleteRow.length) > 0) {
            const control = this.rfcPageStore.rfcFormAll.get('materialDetail') as FormArray;
            const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelected);
            control.clear();
            notSelected.forEach(elt => { control.push(elt) });
        }
    }

    addRowInLabourCharges(labourDetails?: LabourDetails) {
        const contrls = this.rfcPageStore.rfcFormAll.get('labourCharge') as FormArray;
        const newForms = this.rfcPageStore.initializeCreateLabourChargesForm(labourDetails);
        contrls.push(newForms);
        // if (this._operation == Operation.CREATE) {
        //     
        // }

        if (this._operation == Operation.VIEW) {
            newForms.disable();
        }

    }
    deleteRowInLabourCharges() {
        const deleteRow = this.rfcPageStore.rfcFormAll.get('labourCharge').value.filter(val => val.isSelected);
        if ((this.rfcPageStore.rfcFormAll.get('labourCharge').value.length - deleteRow.length) > 0) {
            const control = this.rfcPageStore.rfcFormAll.get('labourCharge') as FormArray;
            const notSelected = control.controls.filter(ctrl => !ctrl.value.isSelected);
            control.clear();
            notSelected.forEach(elt => { control.push(elt) });
        }
    }
    collectRFCFiles(files: UploadableFile[]) {
        this.collectFiles = files;
    }
    collectChassisFiles(file: File) {
        this.chassisFile = file;
    }

    patchFormVal(patchObj: Rfc) {
        this.RFCForm.patchValue(patchObj);
        this.RFCForm.get('chassisNo').patchValue(patchObj.dataFilePath);
    }
}