import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class PscPageStore {

    private readonly _pscCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._pscCreateForm = this.formBuilder.group({
            pscDetailsForm: this.buildPscDetailsForm(),
            tableData: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            })
        })
    }

    get pscForm() {
        return this._pscCreateForm
    }

    private buildPscDetailsForm() {
        return this.formBuilder.group({
            chassisNo: ['', Validators.compose([Validators.required])],
            pscNo: [{ value: '', disabled: true }],
            pscDate: [{ value: '', disabled: true }],
            engineNo: [{ value: '', disabled: true }],
            model: [{ value: '', disabled: true }],
        })
    }

    createPscCheckListTableRow(data = null) {
        return this.formBuilder.group({
            pscCheckpoint: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            defaultTick: [{ value: data ? data.defaultTick : null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null, disabled: false }],
            checkpointId: [{ value: data ? data.checkpointId : null, disabled: false }],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
        })
    }

}