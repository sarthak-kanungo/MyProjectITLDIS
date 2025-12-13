import { Injectable } from '@angular/core';
import { MrcCreatePageStore } from './mrc-create-page.store';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { MrcPageService } from './mrc-page-web.service';
import { MrcCheckPoint, ServiceMrcDiscrepancySet } from '../../domain/machine-receipt-checking.domain';
import { THIS_EXPR, ThrowStmt } from '@angular/compiler/src/output/output_ast';

@Injectable()
export class MachineReceiptCheckingPresenter {
    readonly mrcForm: FormGroup

    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    deletedParts = []
    constructor(private mrcPageStore: MrcCreatePageStore,
    ) {
        this.mrcForm = this.mrcPageStore.mrcFormGroup
    }
    markFormAsTouched() {
        this.mrcForm.markAllAsTouched();
    }
    get mrcDetails() {
        return this.mrcForm as FormGroup
    }
    get basicMrcDetails() {
        return this.mrcForm.get('basicMrcDetails') as FormGroup
    }
    get editableTableData() {
        return this.mrcForm.get('defectShortageDamageForm')
    }
    get mrcCheckPointTableData() {
        return this.mrcForm.get('mrcCheckPointForm') as FormGroup
    }
    get photoUploadFileForm() {
        return this.mrcForm.get('photoUploadFileForm') as FormGroup
    }
    kaiInvoiceNoSetValidation() {
        this.basicMrcDetails.get('kaiInvoiceNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    kaiInvoiceNoRemoveValidation() {
        this.basicMrcDetails.get('kaiInvoiceNo').updateValueAndValidity();
    }
    chassisNoSetValidation() {
        this.basicMrcDetails.get('chassisNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }
    itemNoSetValidation() {

    }


    get checkListTableRow() {
        return this.mrcForm.get('tableData') as FormGroup
    }
    createCheckPointTableRow(data: MrcCheckPoint) {
        return this.mrcPageStore.createCheckPointTableRow(data)
    }
    addRow(checklist: any) {
        let dataTable = this.checkListTableRow.get('dataTable') as FormArray;
        dataTable.push(this.createCheckPointTableRow(checklist));
    }
    addRowForView(checklist: any) {
        let dataTable = this.checkListTableRow.get('dataTable') as FormArray;
        let addedNewForm = this.createCheckPointTableRow(checklist)
        addedNewForm.get('remarks').disable();
        addedNewForm.get('defaultTick').disable();
        dataTable.push(addedNewForm);
    }
    toResetCheckPointDetails() {
        let itemDetails = this.checkListTableRow.get('dataTable') as FormArray
        console.log("itemDetails ", itemDetails);
        itemDetails.clear();
    }
    setValidationForCheckBox() {
        let dataTable = this.checkListTableRow.get('dataTable') as FormArray;
        console.log("dataTable ", dataTable);
        dataTable.controls.forEach(fg => {
            console.log("fg ", fg);
            console.log("fg.get('defaultTick').value) ", fg.get('defaultTick'));
            if (fg.get('defaultTick').value) {
                // fg.get('remarks').enable();
                fg.get('remarks').clearValidators()
                fg.get('remarks').updateValueAndValidity()
            } else {
                // fg.get('remarks').disable();
                // fg.get('remarks').markAsTouched({ onlySelf: true })
                fg.get('remarks').setValidators(Validators.required)
                fg.get('remarks').updateValueAndValidity()
            }
        })
    }


    get itemDetailsTableRow() {
        return this.mrcForm.get('defectShortageDamageFormTable') as FormGroup
    }

    createIemDetailsTableRow(data?: any) {
        return this.mrcPageStore.itemDetailsTableRow(data)
    }

    addItemRow(data?: any) {
        let dataTable = this.itemDetailsTableRow.get('defectShortageDamageData') as FormArray;
        dataTable.push(this.createIemDetailsTableRow(data));
    }

    deleteRow() {
        const itemDetails = this.itemDetailsTableRow.get('defectShortageDamageData') as FormArray;
        const selected = itemDetails.controls.filter(element => element.value.isSelected);
        console.log("selected ", selected);
        selected.forEach(ele => {
            console.log("ele ", ele.value);
            if (ele.value.rowId) {
                this.deletedParts.push(ele.value);
            }
        })
        console.log("this.deletedParts ", this.deletedParts);
        const nonSelected = itemDetails.controls.filter(element => !element.value.isSelected);
        console.log("nonSelected ", nonSelected);
        itemDetails.clear()
        nonSelected.forEach(el => itemDetails.push(el))
    }
    toResetItemDetails() {
        let itemDetails = this.itemDetailsTableRow.get('defectShortageDamageData') as FormArray
        console.log("itemDetails ", itemDetails);
        itemDetails.clear();
    }
    addItemRowForView(data: any) {
        let dataTable = this.itemDetailsTableRow.get('defectShortageDamageData') as FormArray;
        let addedNewForm = this.createIemDetailsTableRow(data)
        // addedNewForm.get('remarks').disable();
        // addedNewForm.get('defaultTick').disable();
        dataTable.push(addedNewForm);
    }

}