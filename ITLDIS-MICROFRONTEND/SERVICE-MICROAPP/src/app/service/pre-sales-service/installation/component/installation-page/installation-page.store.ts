import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class InstallationPageStore {

    private readonly _installationForm: FormGroup

    constructor(
        private formBuilder: FormBuilder
    ) {
        this._installationForm = this.formBuilder.group({
            installationDetailsForm: this.buildInstallationForm(),
            diTableData: this.formBuilder.group({
                diDataTable: this.formBuilder.array([])
            }),
            fiTableData: this.formBuilder.group({
                fiDataTable: this.formBuilder.array([])
            }),
            uploadPhotosForm : this.buildUploadPhotosForm()
        })
    }

    get installationForm() {
        return this._installationForm
    }

    private buildInstallationForm() {
        return this.formBuilder.group({
            chassisNo: [null, Validators.compose([])],
            installationType: [{ value: null, disabled: true }],
            installationId: [null],
            installationNumber: [{ value: null, disabled: true }],
            installationDate: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            searviceStaffName: [null, Validators.compose([])],
            customerRepName: [null, Validators.compose([])],
            customerName: [{ value: null, disabled: true }],
            representativeType: [null, Validators.compose([])],
            csbNumber: [{value:null, disabled: true}],
        })
    }

    createDeliveryInstallationCheckListTableRow(data = null) {
        return this.formBuilder.group({
            //aggregateCheckpointMappingActiveStatus: [{ value: data ? data.aggregateCheckpointMappingActiveStatus : null, disabled: false }],
            //modelId: [{ value: data ? data.modelId : null, disabled: false }],
            checkpointId: [{ value: data ? data.checkpointId : null, disabled: false }],
            //aggregateCheckpointMappingId: [{ value: data ? data.aggregateCheckpointMappingId : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null, disabled: false }],
            diCheckpoint: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            //diAggregateId: [{ value: data ? data.diAggregateId : null, disabled: false }],
            //diCheckpointId: [{ value: data ? data.diCheckpointId : null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            //specification : [{ value: data ? data.specification : null, disabled: false }],
            //aggregateActiveStatus: [{ value: data ? data.aggregateActiveStatus : null, disabled: false }],
            //checkpointActiveStatus: [{ value: data ? data.checkpointActiveStatus : null, disabled: false }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            defaultTick: [{ value: data ? data.defaultTick : null, disabled: false }],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
        })
    }

    createFieldInstallationCheckListTableRow(data = null) {
        return this.formBuilder.group({
            //aggregateCheckpointMappingActiveStatus: [{ value: data ? data.aggregateCheckpointMappingActiveStatus : null, disabled: false }],
            //modelId: [{ value: data ? data.modelId : null, disabled: false }],
            checkpointId: [{ value: data ? data.checkpointId : null, disabled: false }],
            //aggregateCheckpointMappingId: [{ value: data ? data.aggregateCheckpointMappingId : null, disabled: false }],
            aggregateId: [{ value: data ? data.aggregateId : null, disabled: false }],
            fiCheckpoint: [{ value: data ? data.checkpointDesc : null, disabled: false }],
            checkpointSequenceNo: [{ value: data ? data.checkpointSequenceNo : null, disabled: false }],
            //fiAggregateId: [{ value: data ? data.fiAggregateId : null, disabled: false }],
            //fiCheckpointId: [{ value: data ? data.fiCheckpointId : null, disabled: false }],
            aggregate: [{ value: data ? data.aggregate : null, disabled: false }],
            //specification : [{ value: data ? data.specification : null, disabled: false }],
            //aggregateActiveStatus: [{ value: data ? data.aggregateActiveStatus : null, disabled: false }],
            //checkpointActiveStatus: [{ value: data ? data.checkpointActiveStatus : null, disabled: false }],
            aggregateSequenceNo: [{ value: data ? data.aggregateSequenceNo : null, disabled: false }],
            fieldType: [{ value: data ? data.fieldType : null, disabled: false }],
            defaultTick: [{ value: data ?  (/true/i).test(data.defaultTick): null, disabled: false }],
            remarks: [{ value: data ? data.remarks : null, disabled: false }],
            observedSpecification: [{ value: data ? data.observedSpecification : null, disabled: false }],
        })
    }

    private buildUploadPhotosForm(){
        return this.formBuilder.group({
            photos: [null],
        })
    }

}