import { FormBuilder, FormGroup, Validators } from '@angular/forms';

export class SpareGrnSearchPageStore {
    private spareGrnSearchForm: FormGroup;
    constructor(private fb: FormBuilder) { }
    public get getSpareGrnSearchForm(): FormGroup {
        if (this.spareGrnSearchForm) {
            return this.spareGrnSearchForm;
        }
        this.createSpareGrnSearchForm();
        return this.spareGrnSearchForm;
    }
    private createSpareGrnSearchForm() {
        this.spareGrnSearchForm = this.fb.group({
            dmsGrnNumber: [null],
            grnType: [null],
            grnStatus: [null],
            invoiceNumber: [null],
            supplierType: [null,Validators.required],
            supplierName: [null,Validators.required],
            fromDate: [{ value: null, disabled: false }],
            toDate: [{ value: null, disabled: false }],
            page: [{ value: 0, disabled: true }],
            size: [{ value: 10, disabled: true }],
        })
        return this.spareGrnSearchForm;
    }
}
