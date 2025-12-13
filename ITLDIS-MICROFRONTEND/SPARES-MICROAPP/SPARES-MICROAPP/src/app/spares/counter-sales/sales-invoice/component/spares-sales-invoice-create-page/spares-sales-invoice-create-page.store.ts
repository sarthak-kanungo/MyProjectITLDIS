import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as uuid from 'uuid';

export class SparesSalesInvoiceCreatePageStore {
    public sparesInvoiceForm: FormGroup;

    constructor(
        private fb: FormBuilder
    ) { }

    private createForm() {
        this.sparesInvoiceForm = this.fb.group({
            invoiceDetails: this.createInvoiceDetailsForm(),
            itemDetails: this.createItemDetailsForm(),
            labourDetails : this.createLabourDetailsForm(),
            outsideChargeDetails : this.createOutsideChargeDetailsForm()
        })
    }

    get getCreateInvoiceForm(): FormGroup {
        if (this.sparesInvoiceForm) {
            return this.sparesInvoiceForm as FormGroup;
        }
        this.createForm();
        return this.sparesInvoiceForm as FormGroup;
    }
    
    private createInvoiceDetailsForm(){
        return this.fb.group({
            id: null,
            invoiceNumber: [{ value: null, disabled: true }],
            salesInvoiceDate: [{ value: null, disabled: true }],
            referenceDocument: [{ value: null, disabled: false }, [Validators.required]],
            referenceDocumentDate: [{ value: null, disabled: true }],
            saleOrderOrJobCard: [{ value: null, disabled: false }, [Validators.required]],
            customerType: [{ value: null, disabled: true }],
            customerCode: [{ value: null, disabled: true }],
            customerName: [{ value: null, disabled: true }],
            customerAddress1: [{ value: null, disabled: true }],
            customerAddress2: [{ value: null, disabled: true }],
            country: [{ value: null, disabled: true }],
            state: [{ value: null, disabled: true }],
            district: [{ value: null, disabled: true }],
            tehsil: [{ value: null, disabled: true }],
            village: [{ value: null, disabled: true }],
            pincode: [{ value: null, disabled: true }],
            locality: [{ value: null, disabled: true }],
            mobileNo: [{ value: null, disabled: true }],
            pricing: [{ value: null, disabled: true }],
            salesType: [{ value: null, disabled: true }, [Validators.required]],
            transportMode: [{ value: null, disabled: false }],
            transporter: [{ value: null, disabled: false }],
            lrNo: [{ value: null, disabled: false }],
            lrDate: [{ value: null, disabled: false }],
            paymentType: [{ value: null, disabled: false }],
            machineDetailsFormControl: [null],
            totalBaseAmount: [{ value: null, disabled: true }],
            totalTaxAmount: [{ value: null, disabled: true }],
            tcsPercent:[{ value: null, disabled: false }],
            tcsAmount:[{ value: null, disabled: true }],
            totalInvoiceAmount: [{ value: null, disabled: true }],
            chassisNo: [{ value: null, disabled: true }],
            engineNo: [{ value: null, disabled: true }],
            csbNo: [{ value: null, disabled: true }]
        })
    }
    
    private createItemDetailsForm(){
        return this.fb.array([
              
          ])
    }
    private createLabourDetailsForm(){
        return this.fb.array([
              
          ])
    }
    private createOutsideChargeDetailsForm(){
        return this.fb.array([
              
          ])
    }
    
    createLabourChargesRow(data?): FormGroup {
        return this.fb.group({
            uuid: [uuid.v4()],
            id: [data ? data.id : null],
            //isSelected: [{ value: false, disabled: true }],
            frsCode: [{ value: data ? data.frsCode : null, disabled: true }],
            frsCodeId: [{ value: data ? data.frsCodeId : null, disabled: true }],
            description: [{ value: data ? data.frsCodeDesc : null, disabled: true }],
            hour: [{ value: data ? data.laborHour : null, disabled: true }],
            amount: [{ value: data ? data.unitPrice : null, disabled: true }],
            discountPercent: [{value: data ? data.discountPercent : null, disabled: true}],
            discountAmount: [{value: data ? data.discountAmount : null, disabled: true}],
            netAmount: [{value: data ? data.netAmount : null, disabled: true}],
            cgstPercent: [{value: data ? data.cgstPercent : null, disabled: true}],
            cgstAmount: [{value: data ? data.cgstAmount : null, disabled: true}],
            sgstPercent: [{value: data ? data.sgstPercent : null, disabled: true}],
            sgstAmount: [{value: data ? data.sgstAmount : null, disabled: true}],
            igstPercent: [{value: data ? data.igstPercent : null, disabled: true}],
            igstAmount: [{value: data ? data.igstAmount : null, disabled: true}],
            gstAmount: [{value: data ? data.gstAmount : null, disabled: true}],
            subTotal: [{value: data ? data.subTotal : null, disabled: true}],
            totalAmount: [{value: data ? data.totalAmount : null, disabled: true}],
            hsnCode: [{value:  data ? data.hsnCode :null, disabled: true}],
        })
    }
    createJobCodeRow(data?): FormGroup {
        return this.fb.group({
            uuid: [uuid.v4()],
            id: [data ? data.id : null],
            //isSelected: [{ value: false, disabled: false }],
            serviceMtJobcodeId: [data ? data.jobCodeId : null],
            jobCode: [{ value: data ? data.jobCode : null, disabled: true }],
            description: [{ value: data ? data.jobCodeDesc : null, disabled: true }],
            amount: [{ value: data ? data.unitPrice : null, disabled: true }],
            discountPercent: [{value: data ? data.discountPercent : null, disabled: true}],
            discountAmount: [{value: data ? data.discountAmount : null, disabled: true}],
            netAmount: [{value: data ? data.netAmount : null, disabled: true}],
            cgstPercent: [{value: data ? data.cgstPercent : null, disabled: true}],
            cgstAmount: [{value: data ? data.cgstAmount : null, disabled: true}],
            sgstPercent: [{value: data ? data.sgstPercent : null, disabled: true}],
            sgstAmount: [{value: data ? data.sgstAmount : null, disabled: true}],
            igstPercent: [{value: data ? data.igstPercent : null, disabled: true}],
            igstAmount: [{value: data ? data.igstAmount : null, disabled: true}],
            gstAmount: [{value: data ? data.gstAmount : null, disabled: true}],
            subTotal: [{value: data ? data.subTotal : null, disabled: true}],
            totalAmount: [{value: data ? data.totalAmount : null, disabled: true}],
            hsnCode: [{value:  data ? data.hsnCode :null, disabled: true}],
            
        })
    }
    public createItemDetailsTableRow(data?): FormGroup {
        let itemControls = this.fb.group({
            uuid: [uuid.v4()],
            id: [null],
            isSelected : [{value: false, disabled: false}],
            picklistNumber:[{value: null, disabled: true}],
            itemNo: [{value: null, disabled: true}],
            itemDescription: [{value: null, disabled: true}],
            hsnCode: [{value: null, disabled: true}],
            unitPrice: [{value: null, disabled: true}],
            quantity: [{value: null, disabled: true}],
            amount: [{value: null, disabled: true}],
            discountPercent: [{value: null, disabled: true}],
            discountAmount: [{value: null, disabled: true}],
            netAmount: [{value: null, disabled: true}],
            cgstPercent: [{value: null, disabled: true}],
            cgstAmount: [{value: null, disabled: true}],
            sgstPercent: [{value: null, disabled: true}],
            sgstAmount: [{value: null, disabled: true}],
            igstPercent: [{value: null, disabled: true}],
            igstAmount: [{value: null, disabled: true}],
            gstAmount: [{value: null, disabled: true}],
            sparePartMasterId: [{value: null, disabled: true}],
            picklistDtlId: [{value: null, disabled: true}],
            subTotal: [{value: null, disabled: true}],
            totalAmount: [{value: null, disabled: true}],
            spmrp: [{value: null, disabled: true}]
        });
        if(data){
            itemControls.patchValue(data);
            itemControls.get('amount').patchValue(data.unitPrice * data.quantity);
            itemControls.get('sparePartMasterId').patchValue(data.itemNo);
            itemControls.get('spmrp').patchValue(data.unitPrice);
        }
        return itemControls;
    }
}