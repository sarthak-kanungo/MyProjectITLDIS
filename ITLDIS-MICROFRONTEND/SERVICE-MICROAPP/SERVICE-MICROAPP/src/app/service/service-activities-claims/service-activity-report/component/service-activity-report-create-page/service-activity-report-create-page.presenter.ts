import { Injectable } from "@angular/core";
import { ServiceActivityReportCreatePageStore } from "./service-activity-report-create-page.store";
import { FormGroup, FormArray } from "@angular/forms";
import { HeaderDetailsByActivityNo, MachineDetailsByActivityNo, ServiceDetailsByActivityNo, JobCardDetailsByActivityNo, ViewServiceActivityReport } from '../../domain/service-activity-report.domain';
import { UploadableFile } from 'itldis-file-upload';

@Injectable()
export class ServiceActivityReportCreatePagePresenter {
    readonly serviceActivityReportForm: FormGroup
    private _operation: string
    selectedPhotos: File[]

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    constructor(
        private serviceActivityReportCreatePageStore: ServiceActivityReportCreatePageStore,
    ) {
        this.serviceActivityReportForm = this.serviceActivityReportCreatePageStore.serviceActivityReportFormGroup
    }

    get activityReportForm() {
        return this.serviceActivityReportForm.get('serviceActivityReportForm') as FormGroup
    }

    get machineDetailsTableRow() {
        return this.serviceActivityReportForm.get('machineDetailstableData') as FormGroup
    }

    get serviceDetailsTableRow() {
        return this.serviceActivityReportForm.get('serviceDetailstableData') as FormGroup
    }

    get JobCardDetailsTableRow() {
        return this.serviceActivityReportForm.get('JobCardDetailstableData') as FormGroup
    }

    get documentUploded() {
        return this.serviceActivityReportForm.get('documentUploded') as FormGroup
    }

    createMachineDetailsTableRow(list: MachineDetailsByActivityNo) {
        return this.serviceActivityReportCreatePageStore.createMachineDetailsTableRow(list)
    }

    creatServiceDetailsTableRow(list: ServiceDetailsByActivityNo) {
        return this.serviceActivityReportCreatePageStore.createServiceDetailsTableRow(list)
    }

    createJobCardDetailsTableRow(list: JobCardDetailsByActivityNo) {
        return this.serviceActivityReportCreatePageStore.createJobCardDetailsTableRow(list)
    }

    addMachineDetails(data: MachineDetailsByActivityNo) {
        const dataTable = this.machineDetailsTableRow.get('machineDetailsdataTable') as FormArray;
        dataTable.push(this.createMachineDetailsTableRow(data));
    }

    addServiceDetails(data: ServiceDetailsByActivityNo) {
        const dataTable = this.serviceDetailsTableRow.get('serviceDetailsdataTable') as FormArray;
        dataTable.push(this.creatServiceDetailsTableRow(data));
    }

    addJobCardDetails(data: JobCardDetailsByActivityNo) {
        const dataTable = this.JobCardDetailsTableRow.get('JobCardDetailsdataTable') as FormArray;
        dataTable.push(this.createJobCardDetailsTableRow(data));
    }

    setErrorForActivityNo() {
        this.activityReportForm.get('activityNo').setErrors({
            selectFromList: 'Please select from list',
        });
    }

    patchValueForActivityNo(response: HeaderDetailsByActivityNo) {
        this.activityReportForm.patchValue(response)
    }

    fileSelectionChanges(files) {
        this.selectedPhotos = files
    }

    deleteImage(files) {
        this.selectedPhotos = files
    }

    resetForActivityNumber() {
        this.activityReportForm.get('activityNo').reset()
        this.activityReportForm.get('activityCreatedDate').reset()
        this.activityReportForm.get('activityType').reset()
        this.activityReportForm.get('status').reset()
        this.activityReportForm.get('fromDate').reset()
        this.activityReportForm.get('toDate').reset()
        this.activityReportForm.get('targetedNumbers').reset()
        this.activityReportForm.get('product').reset()
        this.activityReportForm.get('noOfMachines').reset()
        this.activityReportForm.get('location').reset()
    }

    patchValueForViewServiceActivityReport(response: ViewServiceActivityReport) {
        this.activityReportForm.patchValue(response.activityReportHeaderData)
        this.activityReportForm.get('activityNo').patchValue({ activityNumber: response.activityReportHeaderData.activityNumber })
        this.activityReportForm.get('activityEffectiveness').patchValue({ effectiveness: response.activityReportHeaderData.activityEffectiveness })
        this.activityReportForm.get('activityReportDate').patchValue(new Date(response.activityReportHeaderData.activityReportDate))
        this.activityReportForm.get('activityCreatedDate').patchValue(new Date(response.activityReportHeaderData.activiyCreationDate))
        this.activityReportForm.get('actualFromDate').patchValue(new Date(response.activityReportHeaderData.actualFromDate))
        this.activityReportForm.get('actualToDate').patchValue(new Date(response.activityReportHeaderData.actualToDate))
        this.activityReportForm.get('fromDate').patchValue(new Date(response.activityReportHeaderData.fromDate))
        this.activityReportForm.get('toDate').patchValue(new Date(response.activityReportHeaderData.toDate))
    }

    markFormAsTouched() {
        this.serviceActivityReportForm.markAllAsTouched();
    }


}