import { FormBuilder, FormGroup, FormArray, Validators, AbstractControl } from '@angular/forms';
import { CustomValidators } from '../../../../../utils/custom-validators';

export class PartReturnPageStore {
    private readonly _partReturnPageForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._partReturnPageForm = this.createPartReturnPageForm();
    }
    private createPartReturnPageForm() {
        return this.fb.group({
            partReturn: this.createPartReturnForm(),
            partReturnItem: this.fb.array([])
        });
    }
    private createPartReturnForm(data?) {
        return this.fb.group({
            id: [{ value: data ? data.id : null, disabled: true }],
            returnNo: [{ value: data ? data.returnNo : null, disabled: true }],
            returnDate: [{ value: data ? data.returnDate : null, disabled: true }],
            partReturnStatus: [{ value: data ? data.partReturnStatus : null, disabled: true }],
            reasonForReturn: [data ? data.reasonForReturn : null, Validators.compose([Validators.required])],
            sparePartRequisition: [{value :data ? { id: data.requisitionId, code: data.sparePartRequisition } : null,disabled: true }],
            serviceJobCard: [{ value: data ? { id: data.jobCardId, code: data.serviceJobCard } : null, disabled: true }],
            requisitionDate: [{ value: data ? data.requisitionDate : null, disabled: true }],
            jobCardDate: [{ value: data ? data.jobCardDate : null, disabled: true }],
            requisitionPurpose: [{ value: data ? data.requisitionPurpose : null, disabled: true }],
            requisitionType: [{ value: data ? data.requisitionType : null, disabled: false }],
            returnRequestBy: [{ value: data ? data.returnRequestBy : null, disabled: true }],
            partsReceivedBy: [{ value: null, disabled: true }],
            partReceivedById: null
        });
    }
    createItemDetailTableRow(data?): FormGroup {
        let fg:FormGroup = this.fb.group({
            id: null,
            itemNo: [{ value: data?data.itemNo: null, disabled: true}],
            itemDescription: [{ value: data?data.itemDescription:null, disabled: true }],
            uom: [{ value: data?data.uom:null, disabled: true }],
            reqQuantity: [{ value: data?data.reqQuantity:null, disabled: true }],
            returnQuantity: [{ value: data?data.returnQuantity:null, disabled: false }],
            issuedQuantity: [{ value: data?data.issuedQuantity:null, disabled: true }],
            pendingQuantity: [{ value: data?data.pendingQuantity:null, disabled: true }],
            isSelected: [{ value: null, disabled: false }],
            sparePartMaster: data ? data.sparePartMaster : null,
            store: [{ value: data?data.store:null, disabled: true }],
            binLocation: [{ value: data?data.binLocation:null, disabled: true }],
            storeMaster: data ? data.storeMaster : null,
            binLocationMaster: data ? data.binLocationMaster : null,  
            sparePartIssue : data ? data.sparePartIssue : null,        
            unitPrice: data ? data.mrp : 0,
            mrp: data ? data.mrp : 0,
            spegst: 0,
            spmgst: 0,
            remark:data ? data.remark : null
        })
        fg.controls.returnQuantity.setValidators(Validators.compose([CustomValidators.numberOnly, Validators.required, CustomValidators.max(fg.controls.issuedQuantity,"Return Qty can't exceed Issued Qty")]))
        return fg;
    }
    public get partReturnPageForm(): FormGroup {
        return this._partReturnPageForm;
    }
    public get partReturnForm(): FormGroup {
        return this._partReturnPageForm.get('partReturn') as FormGroup;
    }
    public get partReturnTableControl(): FormArray {
        return this._partReturnPageForm.get('partReturnItem') as FormArray;
    }
}