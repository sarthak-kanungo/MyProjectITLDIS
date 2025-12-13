import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators } from '@angular/forms';
import { ReInstallationPageStore } from './re-installation-page.store';
import { RepresentativeData, ReInstallationCheckPoint, ViewReInstallation, ReInstallationMachineDetailsList } from '../../domain/re-installation-domain';

@Injectable()
export class ReInstallationPagePresenter {

    readonly reInstallationForm: FormGroup
    private _operation: string

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }

    constructor(
        private reInstallationPageStore: ReInstallationPageStore
    ) {
        this.reInstallationForm = this.reInstallationPageStore.reInstallationForm
    }

    get detailsForm() {
        return this.reInstallationForm.get('reInstallationDetailsForm') as FormGroup
    }

    get representativesForm() {
        return this.reInstallationForm.get('representativesForm') as FormGroup
    }


    get reInstallationCheckListTableRow() {
        return this.reInstallationForm.get('tableData') as FormGroup
    }

    createReInstallationCheckListTableRow(data: ReInstallationCheckPoint) {
        return this.reInstallationPageStore.createReInstallationCheckListTableRow(data)
    }

    get machineDetailsTableRow() {
        return this.reInstallationForm.get('machineDetailstableData') as FormGroup
    }

    createMachineDetailsTableRow(list: ReInstallationMachineDetailsList) {
        return this.reInstallationPageStore.createMachineDetailsTableRow(list)
    }

    get representativesDetailsTableRow() {
        return this.reInstallationForm.get('representativesDetailstableData') as FormGroup
    }

    createRepresentativesDetailsTableRow(data: RepresentativeData) {
        return this.reInstallationPageStore.createRepresentativesDetailsTableRow(data)
    }

    setErrorForServiceStaffName() {
        this.detailsForm.get('serviceStaffName').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    addChecklist(checklist: ReInstallationCheckPoint) {
        const dataTable = this.reInstallationCheckListTableRow.get('dataTable') as FormArray;
        dataTable.push(this.createReInstallationCheckListTableRow(checklist));
    }

    addRepresentativesDetails(data: RepresentativeData) {
        const dataTable = this.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
        dataTable.push(this.createRepresentativesDetailsTableRow(data));
    }

    addRow(list?: ReInstallationMachineDetailsList) {
        const machineDetails = this.machineDetailsTableRow.get('machineDetailsdataTable') as FormArray
        machineDetails.push(this.createMachineDetailsTableRow(list))
    }

    deleteRow() {
        const machineDetails = this.machineDetailsTableRow.get('machineDetailsdataTable') as FormArray
        const nonSelected = machineDetails.controls.filter(machinery => !machinery.value.isSelected)
        const selected = machineDetails.controls.filter(machinery => machinery.value.isSelected)
        machineDetails.clear()
        nonSelected.forEach(el => machineDetails.push(el))
        const dataTable = this.representativesDetailsTableRow.get('representativesDetailsdataTable') as FormArray;
        selected.forEach(element => {
            const deleteData = dataTable.controls.filter(ele => ele.value.uuid === element.value.uuid || ele.value.chassisId === element.value.chassisNo.id)
            console.log("deleteData ", deleteData);
            deleteData.forEach(el => dataTable.removeAt(el.value))

        })
    }

    resetForSeries() {
        let control = this.reInstallationCheckListTableRow.get('dataTable') as FormArray
        control.clear();
        console.log("control ", control);

    }

    setValidatorsForRepresentativesDetails() {
        this.representativesForm.get('representativeName').setValidators(Validators.required)
        this.representativesForm.get('representativeName').updateValueAndValidity()
        this.representativesForm.get('representativeType').setValidators(Validators.required)
        this.representativesForm.get('representativeType').updateValueAndValidity()
    }

    clearValidatorsForRepresentativesDetails() {
        this.representativesForm.get('representativeName').clearValidators()
        this.representativesForm.get('representativeName').updateValueAndValidity()
        this.representativesForm.get('representativeType').clearValidators()
        this.representativesForm.get('representativeType').updateValueAndValidity()
    }

    patchValueForViewReInstallation(response: ViewReInstallation) {
        this.detailsForm.get('series').patchValue({ id: response.riViewHeaderData.seriesId, series: response.riViewHeaderData.series })
        this.detailsForm.get('serviceStaffName').patchValue({ id: response.riViewHeaderData.serviceStaffId, employeeName: response.riViewHeaderData.serviceStaffName })
        this.detailsForm.get('reinstallationNo').patchValue(response.riViewHeaderData.reInstallationNumber)
        this.detailsForm.get('reinstallationDate').patchValue(response.riViewHeaderData.reInstallationDate ? new Date(response.riViewHeaderData.reInstallationDate) : null)
        const reInstallationMachineDetails = []
        response.reInstallationMachineDetailsList.forEach(data => {
            reInstallationMachineDetails.push({
                code: { id: data.chassisId, code: data.chassisNo },
                engineNo: data.engineNo,
                customerName: data.customerName,
                representativeCount: data.representativeCount
            })
        })
        reInstallationMachineDetails.forEach(ele => {
            this.addRow(ele)
        })
        this.detailsForm.get('serviceStaffName').setErrors(null);
    }

    markFormAsTouched() {
        this.reInstallationForm.markAllAsTouched();
    }

}