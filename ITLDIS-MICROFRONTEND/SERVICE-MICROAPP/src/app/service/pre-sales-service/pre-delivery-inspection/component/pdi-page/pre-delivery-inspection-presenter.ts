import { Injectable } from '@angular/core';
import { FormGroup, Validators, FormArray } from '@angular/forms';
import { ViewStore } from './view-page.store';
import { ModelNumberData, PdiCheckpointList, ViewChasisNumber, PdiHeaderData } from '../../domain/pdi-domain';
@Injectable()
export class PdiPresenter {
    pdiForm: FormGroup
    isDropDown: boolean
    private _operation: string

    constructor(
        private viewStore: ViewStore,
    ) {
        this.pdiForm = this.viewStore.pdiForm
    }

    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    createCheckPointTableRow(data: ModelNumberData) {
        return this.viewStore.createCheckPointTableRow(data)
    }
    checkPointvalue() {
        return this.viewStore.createCheckPointTableRow()
    }
    createCheckPointTableRowView(data: PdiCheckpointList) {
        return this.viewStore.createCheckPointTableRow(data)
    }
    disableFields() {
        this.basicDetails.disable()
        this.staticTable.disable()
        this.tableData.controls.table.disable()
    }
    patchValueTodetails(data: PdiHeaderData) {
        let chasisObj: ViewChasisNumber = {
            code: data.chassisNo, id: data.chassisId
        }
        let grnConvertedDate = new Date(data.dmsGrnDate)
        let pdiConvertedDate = new Date(data.pdiDate)
        this.basicDetails.get('chassisNo').disable(),
        this.basicDetails.get('chassisNo').patchValue(chasisObj)
        this.basicDetails.get('kaiInvoiceNo').patchValue(data.kaiInvoiceNumber),
        this.basicDetails.get('engineNo').patchValue(data.engineNumber),
        this.basicDetails.get('dmsgrnNo').patchValue(data.dmsGrnNumber),
        this.basicDetails.get('dmsgrnDate').patchValue(grnConvertedDate),
        this.basicDetails.get('pdiDate').patchValue(pdiConvertedDate),
        this.basicDetails.get('pdiNo').patchValue(data.pdiNumber),
        this.basicDetails.get('machineModel').patchValue(data.machineModel)
        this.staticTable.get('staticChecked').patchValue(data.okFlag)
        this.staticTable.get('staticRemark').patchValue(data.remarks)
    }


    setValidationForCheckBox() {
        let dataTable = this.tableData.get('table') as FormArray;
        //console.log('dataTable---', dataTable)
        dataTable.controls.forEach(value => {
           // console.log('value', value)
            if (value.get('defaultTick').value === true && value.get('fieldType').value == "CHECK BOX") {
                value.get('remark').clearValidators()
                value.get('remark').updateValueAndValidity()
            } else if (value.get('defaultTick').value === false && value.get('fieldType').value == "CHECK BOX") {
                value.get('remark').setValidators(Validators.required)
                value.get('remark').updateValueAndValidity()
                value.get('remark').markAllAsTouched();

            }
        })
    }
    get basicDetails() {
        return this.viewStore.pdiForm.get('createBasicDetailsPdiForm') as FormGroup
    }
    get staticTable() {
        return this.viewStore.pdiForm.get('staticTable') as FormGroup
    }
    get tableData() {
        return this.viewStore.pdiForm.get('tableData') as FormGroup
    }
    get formArrayCheckPoints() {
        return this.tableData.get('table') as FormArray
    }


}