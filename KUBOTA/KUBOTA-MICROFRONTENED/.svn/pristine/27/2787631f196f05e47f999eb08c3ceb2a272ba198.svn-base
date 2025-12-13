import { FormBuilder, Validators, FormGroup, FormArray } from '@angular/forms';
import { CustomValidators, ValidateInt } from '../../../../../utils/custom-validators';

export class PartRequisitionPageStore {
    private readonly _partRequisitionPageForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) {
        this._partRequisitionPageForm = this.createPartRequisitionPageForm();
    }

    public get partRequisitionPageForm(): FormGroup {
        return this._partRequisitionPageForm;
    }
    public get partRequisitionForm(): FormGroup {
        return this._partRequisitionPageForm.get('partRequisition') as FormGroup;
    }
    public get sparePartRequisitionItemControl(): FormArray {
        return this._partRequisitionPageForm.get('sparePartRequisitionItem') as FormArray;
    }
    private createPartRequisitionPageForm() {
        return this.fb.group({
            partRequisition: this.createPartRequisitionForm(),
            sparePartRequisitionItem: this.fb.array([])
        });
    }
    private createPartRequisitionForm() {
        return this.fb.group({
            id:[{ value: null, disabled: true }],
            requisitionPurpose: [{ value: null, disabled: false }, Validators.required],
            requestedBy: [{ value: null, disabled: true }],
            requisitionNo: [{ value: null, disabled: true }],
            requisitionDate: [{ value: null, disabled: true }],
            jobCardNo: [{ value: null, disabled: true }],
            jobCardDate: [{ value: null, disabled: true }],
        });
    }
    public itemDetail() {
        let fg = this.fb.group({
            id:[null],
            itemNo: [{ value: null, disabled: false }, Validators.required],
            itemDescription: [{ value: null, disabled: true }, Validators.required],
            uom: [{ value: null, disabled: true }],
            requisitionQty: [{ value: null, disabled: false }],
            priceUnit: [{ value: null, disabled: true }],
            isSelected: [null]
        });
        fg.controls.requisitionQty.setValidators(Validators.compose([Validators.required,ValidateInt]));
        return fg;
    }
}
