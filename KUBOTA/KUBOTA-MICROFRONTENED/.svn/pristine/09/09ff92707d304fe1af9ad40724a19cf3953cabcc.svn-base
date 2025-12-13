import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { PscPageStore } from './psc-page.store';
import { CheckListByChassisNo } from '../../domain/psc-domain';

@Injectable()
export class PscPagePresenter {

    readonly pscForm: FormGroup
    private _operation: string
    spans = [];

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private pscPageStore: PscPageStore
    ) {
        this.pscForm = this.pscPageStore.pscForm
    }

    get detailsForm() {
        return this.pscForm.get('pscDetailsForm') as FormGroup
    }

    get pscCheckListTableRow() {
        return this.pscForm.get('tableData') as FormGroup
    }

    createPscCheckListTableRow(data: CheckListByChassisNo) {
        return this.pscPageStore.createPscCheckListTableRow(data)
    }

    addRow(checklist: CheckListByChassisNo) {
        const dataTable = this.pscCheckListTableRow.get('dataTable') as FormArray;
        dataTable.push(this.createPscCheckListTableRow(checklist));
    }

    setValidationForCheckBox() {
        const dataTable = this.pscCheckListTableRow.get('dataTable') as FormArray;
        dataTable.controls.forEach(value => {
            if (value.get('defaultTick').value) {
                value.get('remarks').clearValidators()
                value.get('remarks').updateValueAndValidity()
            } else {
                value.get('remarks').markAsTouched({ onlySelf: true })
                value.get('remarks').setValidators(Validators.required)
                value.get('remarks').updateValueAndValidity()
            }
        })
    }
    getRowSpan(col: object, index: number) {
        return this.spans[index] && this.spans[index]['aggregate'];
    }
    setErrorForChassisNo() {
        this.detailsForm.get('chassisNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    resetForChasssisNo(){
        this.detailsForm.get('engineNo').reset()
        this.detailsForm.get('model').reset()
        let control = this.pscCheckListTableRow.get('dataTable') as FormArray
        control.clear();
        console.log('control',control);
        
    }


    markFormAsTouched() {
        this.pscForm.markAllAsTouched();
    }

}