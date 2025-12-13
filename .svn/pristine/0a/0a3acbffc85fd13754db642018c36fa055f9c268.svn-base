import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class PdcPageStore {

    private readonly _pdcCreateForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._pdcCreateForm = this.formBuilder.group({
            pdcDetailsForm: this.buildPdcDetailsForm(),
            tableData: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),
            staticTable: this.formBuilder.group({
                staticChecked: [true],
                staticRemark: [{ value: null, disabled: false }]
            })
        })
    }

    get pdcForm() {
        return this._pdcCreateForm
    }

    private buildPdcDetailsForm() {
        return this.formBuilder.group({
            chassisNo: [null, Validators.compose([Validators.required])],
            pdcNo: [{ value: '', disabled: true }],
            pdcDate: [{ value: '', disabled: true }],
            engineNo: [{ value: '', disabled: true }],
            model: [{ value: '', disabled: true }],
            name: [{ value: '', disabled: true }],
        })
    }

    createPdcCheckListTableRow(data = null) {
        return this.formBuilder.group({
            pdcCheckpoint: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            defaultTick: [{ value: data ? (/true/i).test(data.defaultTick): null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null, disabled: false }],
            checkpointId: [{ value: data ? data.checkpointId : null, disabled: false }],
            modelId: [{ value: data ? data.modelId : null, disabled: false }],
            specification: [{ value: data ? data.specification : null, disabled: false }],
            observedSpecification : [{ value: data ? data.observedSpecification : null, disabled: false }],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
        })
    }

}