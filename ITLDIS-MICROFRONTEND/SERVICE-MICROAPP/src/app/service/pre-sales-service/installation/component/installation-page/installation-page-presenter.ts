import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { InstallationPageStore } from './installation-page.store';
import { InstallationCheckList, ViewInstallation } from '../../domain/installation-domain';
import { Operation } from '../../../../../utils/operation-util';
import { SameRowMearge } from '../../../../../utils/same-row-mearging';


@Injectable()
export class InstallationPagePresenter {

    readonly installationForm: FormGroup
    private _operation: string
    selectedPhotos: File[]

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private installationPageStore: InstallationPageStore
    ) {
        this.installationForm = this.installationPageStore.installationForm
    }

    get detailsForm() {
        return this.installationForm.get('installationDetailsForm') as FormGroup
    }
    get uploadPhotosForm() {
        return this.installationForm.get('uploadPhotosForm') as FormGroup
    }

    setErrorForChassisNo() {
        this.detailsForm.get('chassisNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }
    // setErrorForCsbNumber() {
    //     this.detailsForm.get('csbNumber').setErrors({
    //         selectFromList: 'Please select from list',
    //     });
    // }
    setErrorForSearviceStaffName() {
        this.detailsForm.get('searviceStaffName').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    get dInstallationCheckListTableRow() {
        return this.installationForm.get('diTableData') as FormGroup
    }

    createDeliveryInstallationCheckListTableRow(data: InstallationCheckList) {
        return this.installationPageStore.createDeliveryInstallationCheckListTableRow(data)
    }

    diAddRow(checklist: InstallationCheckList) {
        const dataTable = this.dInstallationCheckListTableRow.get('diDataTable') as FormArray;
        dataTable.push(this.createDeliveryInstallationCheckListTableRow(checklist));
    }

    diAddRowForView(checklist: InstallationCheckList) {
        const dataTable = this.dInstallationCheckListTableRow.get('diDataTable') as FormArray;
        const addedNewForm = this.createDeliveryInstallationCheckListTableRow(checklist)
        addedNewForm.get('remarks').disable();
        addedNewForm.get('defaultTick').disable();
        addedNewForm.get('observedSpecification').disable();
        dataTable.push(addedNewForm)
    }

    get fInstallationCheckListTableRow() {
        return this.installationForm.get('fiTableData') as FormGroup
    }

    createFieldInstallationCheckListTableRow(data: InstallationCheckList) {
        return this.installationPageStore.createFieldInstallationCheckListTableRow(data)
    }

    fiAddRow(checklist: InstallationCheckList) {
        const dataTable = this.fInstallationCheckListTableRow.get('fiDataTable') as FormArray;
        dataTable.push(this.createFieldInstallationCheckListTableRow(checklist));
    }

    fiAddRowForView(checklist: InstallationCheckList) {
        const dataTable = this.fInstallationCheckListTableRow.get('fiDataTable') as FormArray;
        const addedNewForm = this.createFieldInstallationCheckListTableRow(checklist)
        addedNewForm.get('remarks').disable();
        addedNewForm.get('defaultTick').disable();
        addedNewForm.get('observedSpecification').disable();
        dataTable.push(addedNewForm)
    }
  
    setValidationForDICheckBox() {
        const dataTable = this.dInstallationCheckListTableRow.get('diDataTable') as FormArray;
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

    setValidationForFICheckBox() {
        const dataTable = this.fInstallationCheckListTableRow.get('fiDataTable') as FormArray;
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

    fileSelectionChanges(files) {
        this.selectedPhotos = files
    }

    deleteImage(files) {
        this.selectedPhotos = files
    }

    markFormAsTouched() {
        this.installationForm.markAllAsTouched();
    }

    resetForChasssisNo(){
        this.detailsForm.get('installationType').reset()
        this.detailsForm.get('engineNo').reset()
        this.detailsForm.get('model').reset()
        this.detailsForm.get('customerName').reset()
        const dataTable = this.dInstallationCheckListTableRow.get('diDataTable') as FormArray;
        dataTable.clear()
        const fiDataTable = this.fInstallationCheckListTableRow.get('fiDataTable') as FormArray;
        fiDataTable.clear()
    }

    patchValueForViewAndEdit(response: ViewInstallation) {
        this.detailsForm.patchValue(response.installationViewHeaderData)
        this.detailsForm.get('installationDate').patchValue(new Date(response.installationViewHeaderData.installationDate))
        this.detailsForm.get('chassisNo').patchValue({ code: response.installationViewHeaderData.chassisNo })
        this.detailsForm.get('csbNumber').patchValue({ code: response.installationViewHeaderData.csbNumber })
        this.detailsForm.get('installationId').patchValue(response.installationViewHeaderData.installationTypeId)
        this.detailsForm.get('representativeType').patchValue({id:response.installationViewHeaderData.serviceStaffNameId,  representativeType: response.installationViewHeaderData.representativeType })
        this.detailsForm.get('searviceStaffName').patchValue({ employeeName: response.installationViewHeaderData.serviceStaffName, id:response.installationViewHeaderData.serviceStaffNameId })
        if (this.operation === Operation.VIEW) {
            this.dInstallationCheckListTableRow.disable()
            this.fInstallationCheckListTableRow.disable()
        }
        this.detailsForm.get('csbNumber').setErrors(null);
        this.detailsForm.get('searviceStaffName').setErrors(null);
    }

}