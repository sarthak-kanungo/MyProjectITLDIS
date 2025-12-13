import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators, FormBuilder, ValidatorFn, ValidationErrors } from '@angular/forms';

@Injectable()
export class ViewStore {
    private readonly _pdiCreatePresenterFormGroup: FormGroup
    private readonly _materialTableForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._pdiCreatePresenterFormGroup = this.formBuilder.group({
            createBasicDetailsPdiForm: this.buildPdiFormGroup(),
            tableData: this.formBuilder.group({
                table: this.formBuilder.array([
                   // this.createCheckPointTableRow()
                ])
            }),
            staticTable: this.formBuilder.group({
                staticChecked: [true,],
                staticRemark: [{ value: null, disabled: false },]
            })

        })
    }
    get pdiForm() {
        return this._pdiCreatePresenterFormGroup
    }

    private buildPdiFormGroup() {
        return this.formBuilder.group({

            chassisNo: ['',],
            pdiNo: [{ value: null, disabled: true }],
            pdiDate: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            kaiInvoiceNo: [{ value: null, disabled: true }],
            machineModel: [{ value: null, disabled: true }],
            dmsgrnNo: [{ value: null, disabled: true }],
            dmsgrnDate: [{ value: null, disabled: true }]
        })
    }


    createCheckPointTableRow(data = null) {
        return this.formBuilder.group({
            modelId: [{ id: data ? data.modelId : null }],
            aggregateId: [{ id: data ? data.aggregateId : null }],
            checkpointId: [{ id: data ? data.checkpointId : null }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            defaultTick: [{ value: data ? (/true/i).test(data.defaultTick) : null, disabled: false }],
            remark: [{ value: data ? data.remark : null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            checkpointDesc: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            specification: [{ value: data ? data.specification : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
        })
    }
}