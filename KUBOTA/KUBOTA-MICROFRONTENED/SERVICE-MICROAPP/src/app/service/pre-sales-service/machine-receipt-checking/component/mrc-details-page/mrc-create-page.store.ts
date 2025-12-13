
import { Injectable } from '@angular/core';
import { FormGroup, FormArray, Validators, FormBuilder, FormControl } from '@angular/forms';
import { TableConfig } from 'editable-table';
import { CustomValidators } from '../../../../../utils/custom-validators';

@Injectable()
export class MrcCreatePageStore {
    private readonly __mrcFormGroup: FormGroup
    // private materialTableForm: FormGroup
    // materialTableFormArray: FormArray
    constructor(
        private formBuilder: FormBuilder,
    ) {
        this.__mrcFormGroup = this.formBuilder.group({
            basicMrcDetails: this.buildingMRCDetailsForm(),
            // mrcCheckPointForm: this.createMaterialTableForm(),
            tableData: this.formBuilder.group({
                dataTable: this.formBuilder.array([])
            }),
            defectShortageDamageFormTable: this.formBuilder.group({
                defectShortageDamageData: this.formBuilder.array([]),
            }),
            photoUploadFileForm: this.buildingPhotoForm(),
            defectShortageDamageForm: [null],
        })
    }
    get mrcFormGroup() {
        return this.__mrcFormGroup
    }
    private buildingMRCDetailsForm(): FormGroup {
        return this.formBuilder.group({
            mrcNo: [{ value: null, disabled: true }],
            kaiInvoiceNo: [null, Validators.required],
            mrcDate: [{ value: null, disabled: true }],
            chassisNo: [null, Validators.required],
            transporterName: [{ value: null, disabled: true }],
            lrNo: [{ value: null, disabled: true }],
            lrDate: [{ value: null, disabled: true }],
            machineModel: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            PendingMRC: [{ value: null, disabled: true }],
            CompletedMRC: [{ value: null, disabled: true }],
        })
    }
    getControlsConfigForTableFormGroup() {
        return {
            id: [null],
            isSelected: [{ value: false, disabled: false }],
            itemNo: [{ value: null, disabled: false }],
            itemDescription: [{ value: null, disabled: true }],
            quantity: [{ value: null, disabled: false }, CustomValidators.numberOnly],
            remarks: [{ value: null, disabled: false }],
            type: [{ value: null, disabled: false }],
            raiseComplaint: [{ value: null, disabled: false }],
            deleteFlag: [false]
        }
    }
    itemDetailsTableRow(data?) {
        let fg:FormGroup = this.formBuilder.group({
            id: [data ? data.id : null],
            sparePartMasterId: [data ? data.sparePartMasterId : null],
            isSelected: [{ value: false, disabled: false }],
            itemNo: [{ value: data ? data.itemNo : null, disabled: false }, CustomValidators.selectFromListDynamic()],
            itemDescription: [{ value: data ? data.itemDescription : null, disabled: true }],
            quantity: [{ value: data ? data.quantity : null, disabled: false }, CustomValidators.numberOnly],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
            type: [{ value: data ? data.type : null, disabled: false }],
            raiseComplaint: [{ value: data ? data.raiseComplaint : null, disabled: true }],
            deleteFlag: [false],
            rowId: [data ? data.rowId : null]
        })
        if(data){
            fg.controls.itemNo.patchValue({'itemNo':data.itemNo})
        }
      return fg;
    }

    // createMaterialTableForm() {
    //     this.materialTableForm = this.formBuilder.group({
    //         table: this.formBuilder.array([
    //             // this.createCheckPointTableRow()
    //         ])
    //     });
    //     this.materialTableFormArray = this.materialTableForm.get('table') as FormArray;
    //     return this.materialTableForm;
    // }

    createCheckPointTableRow(data = null) {
        return this.formBuilder.group({
            id: [{ value: data ? data.id : null }],
            defaultTick: [{ value: data ?  (/true/i).test(data.defaultTick): null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: true }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: true }],
            checkpointDesc: [{ value: data ? data.checkpointDesc : null, disabled: true }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: true }],
            specification: [{ value: data ? data.specification : null, disabled: true }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null }],
            checkpointId: [{ value: data ? data.checkpointId : null }],
            fieldType: [{ value: data ? data.fieldType : null }]
        })
    }
    buildingPhotoForm() {
        return this.formBuilder.group({
            uploadFile: [null, Validators.required]
        })
    }
}