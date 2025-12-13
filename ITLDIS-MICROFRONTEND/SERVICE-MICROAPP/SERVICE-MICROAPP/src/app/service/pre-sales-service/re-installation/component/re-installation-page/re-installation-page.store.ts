import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as uuid from 'uuid';

@Injectable()
export class ReInstallationPageStore {

    private readonly _reInstallationForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._reInstallationForm = this.formBuilder.group({
            reInstallationDetailsForm: this.buildReInstallationForm(),
            tableData: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),
            machineDetailstableData: this.formBuilder.group({
                machineDetailsdataTable: this.formBuilder.array([])
            }),
            representativesDetailstableData: this.formBuilder.group({
                representativesDetailsdataTable: this.formBuilder.array([])
            }),
            representativesForm: this.buildRepresentativesForm(),
        })
    }

    get reInstallationForm() {
        return this._reInstallationForm
    }

    private buildReInstallationForm() {
        return this.formBuilder.group({
            series: [null, Validators.compose([])],
            serviceStaffName: [null, Validators.compose([Validators.required])],
            reinstallationNo: [{ value: null, disabled: true }],
            reinstallationDate: [{ value: null, disabled: true }],
        })
    }

    private buildRepresentativesForm() {
        return this.formBuilder.group({
            representativeName: [null],
            representativeType: [null],
        })
    }

    createReInstallationCheckListTableRow(data = null) {
        return this.formBuilder.group({
            //riAggregateId: [{ value: data ? data.riAggregateId : null, disabled: false }],
            //aggregateCheckpointMappingId: [{ value: data ? data.aggregateCheckpointMappingId : null, disabled: false }],
            checkpointId: [{ value: data ? data.checkpointId : null, disabled: false }],
            riCheckpointId: [{ value: data ? data.riCheckpointId : null, disabled: false }],
            //seriesId: [{ value: data ? data.seriesId : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
            riCheckpoint: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            aggregateSequenceNo : [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            defaultTick: [{ value: data ? (/true/i).test(data.defaultTick) : null, disabled: false }],
        })
    }

    createMachineDetailsTableRow(data = null) {
        return this.formBuilder.group({
            uuid: [uuid.v4()],
            chassisNo: [{  value: data ? data.code : null, disabled: false },Validators.compose([Validators.required])],
            engineNo: [{  value: data ? data.engineNo : null, disabled: true }],
            customerName: [{  value: data ? data.customerName : null, disabled: true }],
            representativeCount: [{ value: data ? data.representativeCount : null, disabled: true }],
            isSelected: [false],
        })
    }

    createRepresentativesDetailsTableRow(data = null) {
        return this.formBuilder.group({
            uuid: [data.uuid ],
            representativeName: [{ value: data ? data.representativeName : null, disabled: true }],
            representativeType: [{ value: data ? data.representativeType : null, disabled: true }],
            chassisNo: [{ value: data ? data.chassisNo : null, disabled: true }],
            chassisId: [data.chassisId ],
        })
    }

}