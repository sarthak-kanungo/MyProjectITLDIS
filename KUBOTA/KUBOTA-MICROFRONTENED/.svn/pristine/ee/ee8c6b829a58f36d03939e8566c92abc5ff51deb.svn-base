import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Injectable } from '@angular/core';

@Injectable()
export class ServiceActivityReportCreatePageStore {
    private readonly _serviceActivityReportForm

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._serviceActivityReportForm = this.formBuilder.group({
            serviceActivityReportForm: this.buildServiceActivityReportForm(),
            machineDetailstableData: this.formBuilder.group({
                machineDetailsdataTable: this.formBuilder.array([])
            }),
            serviceDetailstableData: this.formBuilder.group({
                serviceDetailsdataTable: this.formBuilder.array([])
            }),
            JobCardDetailstableData: this.formBuilder.group({
                JobCardDetailsdataTable: this.formBuilder.array([])
            }),
            documentUploded : this.buildServiceActivityReportUploadImagesForm()
        })
    }
    get serviceActivityReportFormGroup() {
        return this._serviceActivityReportForm
    }

    private buildServiceActivityReportForm(): FormGroup {
        return this.formBuilder.group({
            activityNo: [null, Validators.compose([Validators.required])],
            activityReportDate: [{ value: null, disabled: true }],
            activityCreatedDate: [{ value: null, disabled: true }],
            activityType: [{ value: null, disabled: true }],
            fromDate: [{ value: null, disabled: true }],
            actualToDate: [null, Validators.compose([Validators.required])],
            actualFromDate: [null, Validators.compose([Validators.required])],
            status: [{ value: null, disabled: true }],
            toDate: [{ value: null, disabled: true }],
            targetedNumbers: [{ value: null, disabled: true }],
            product: [{ value: null, disabled: true }],
            activityEffectiveness: [null, Validators.compose([Validators.required])],
            noOfMachines: [{ value: null, disabled: true }],
            remarks: [null, Validators.compose([Validators.required])],
            location: [{ value: null, disabled: true }],
            dealerName:[{value:null,disabled:true}]
        })
    }

    createMachineDetailsTableRow(data = null) {
        return this.formBuilder.group({
            series: [data ? data.series : null],
            noOfMachines: [data ? data.noOfMachines : null],
            id: [data ? data.id : null],
        })
    }

    createServiceDetailsTableRow(data = null) {
        return this.formBuilder.group({
            serviceType: [data ? data.serviceType : null],
            serviceTypeCount: [data ? data.serviceTypeCount : null],
            id: [data ? data.id : null],
        })
    }

    createJobCardDetailsTableRow(data = null) {
        return this.formBuilder.group({
            customerCode: [data ? data.customerCode : null],
            customerName: [data ? data.customerName : null],
            jobCardNo: [data ? data.jobCardNo : null],
            jobCardDate: [data ? data.jobCardDate : null],
            closedDate: [data ? data.closedDate : null],
            chassisNo: [data ? data.chassisNo : null],
            model: [data ? data.model : null],
            serviceType: [data ? data.serviceType : null],
            labourCharges: [data ? data.labourCharges : null],
            sparePartSale: [data ? data.sparePartSale : null],
            lubricants: [data ? data.lubricants : null],
            mechanicName: [data ? data.mechanicName : null],
            id:[data ? data.id : null]
        })
    }

    private buildServiceActivityReportUploadImagesForm(): FormGroup {
        return this.formBuilder.group({
            photos: [null, Validators.compose([Validators.required])],
        })
    }

}