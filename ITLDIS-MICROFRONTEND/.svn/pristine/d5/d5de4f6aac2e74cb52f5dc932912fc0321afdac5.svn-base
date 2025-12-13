import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { PdcPageStore } from './pdc-page.store';
import { CheckpointListByModel } from '../../domain/pdc-domain';

@Injectable()
export class PdcPagePresenter {

    readonly pdcForm: FormGroup
    private _operation: string
    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private pdcPageStore: PdcPageStore
    ) {
        this.pdcForm = this.pdcPageStore.pdcForm
    }

    get detailsForm() {
        return this.pdcForm.get('pdcDetailsForm') as FormGroup
    }

    setErrorForChassisNo() {
        this.detailsForm.get('chassisNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    get pdcCheckListTableRow() {
        return this.pdcForm.get('tableData') as FormGroup
    }

    get pdcStaticTable() {
        return this.pdcForm.get('staticTable') as FormGroup
    }

    createPdcCheckListTableRow(data: CheckpointListByModel) {

        return this.pdcPageStore.createPdcCheckListTableRow(data)
    }

    addRow(checklist: CheckpointListByModel) {
        const dataTable = this.pdcCheckListTableRow.get('dataTable') as FormArray;
        dataTable.push(this.createPdcCheckListTableRow(checklist))
    }

    addRowForView(checklist: CheckpointListByModel) {
        const dataTable = this.pdcCheckListTableRow.get('dataTable') as FormArray;
        const addedNewForm = this.createPdcCheckListTableRow(checklist)
        addedNewForm.get('remarks').disable();
        addedNewForm.get('defaultTick').disable();
        addedNewForm.get('observedSpecification').disable();
        dataTable.push(addedNewForm)
    }

    setValidationForCheckBox() {
        const dataTable = this.pdcCheckListTableRow.get('dataTable') as FormArray;
        dataTable.controls.forEach(value => {
            if (value.get('defaultTick').value) {
                value.get('remarks').clearValidators()
                value.get('remarks').updateValueAndValidity()
            } else if (value.get('fieldType').value === 'DROP DOWN') {
                value.get('remarks').clearValidators()
                value.get('remarks').updateValueAndValidity()
            } else {
                value.get('remarks').markAsTouched({ onlySelf: true })
                value.get('remarks').setValidators(Validators.required)
                value.get('remarks').updateValueAndValidity()
            }
        })
    }

    setValidationForStaticCheckBox() {
        console.log(this.pdcStaticTable);
        const pdcStaticTable = this.pdcStaticTable
        if (pdcStaticTable.get('staticChecked').value) {
            pdcStaticTable.get('staticRemark').clearValidators()
            pdcStaticTable.get('staticRemark').updateValueAndValidity()
        } else {
            pdcStaticTable.get('staticRemark').markAsTouched({ onlySelf: true })
            pdcStaticTable.get('staticRemark').setValidators(Validators.required)
            pdcStaticTable.get('staticRemark').updateValueAndValidity()
        }
    }

    resetForChasssisNo() {
        this.detailsForm.get('engineNo').reset()
        this.detailsForm.get('model').reset()
        this.detailsForm.get('name').reset()
    }

    resetCheckPoint() {
        const dataTable = this.pdcCheckListTableRow.get('dataTable') as FormArray;
        dataTable.clear()
    }

    markFormAsTouched() {
        this.pdcForm.markAllAsTouched();
    }

}