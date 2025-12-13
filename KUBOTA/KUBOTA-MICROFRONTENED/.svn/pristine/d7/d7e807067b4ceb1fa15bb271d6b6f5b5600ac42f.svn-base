import { Injectable, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as uuid from 'uuid';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { SparesPOPartDetails } from '../../domain/spares-purchase-order.domain';

@Injectable()
export class PurchaseOrderCreatePageStore {
    private createPoForm: FormGroup;

    constructor(
        private _changeDetectorRef: ChangeDetectorRef,
        private fb: FormBuilder
    ) { }

    private createForm() {
        console.log('------FORM CREATED**----');
        this.createPoForm = this.fb.group({
            partsOrdering: this.createPartOrderingForm(),
            itemDetails: this.createItemDetailsForm(),
            poTotal: this.createPoTotal()
        })
    }

    get getCreatePoForm(): FormGroup {
        if (this.createPoForm) {
            return this.createPoForm as FormGroup;
        }
        this.createForm();
        return this.createPoForm as FormGroup;
    }
    private createPartOrderingForm() {
        return this.fb.group({
            purchaseOrderNumber: [{ value: null, disabled: true }],
            supplierType: [{ value: null, disabled: false }, Validators.required],
            supplierName: [{ value: null, disabled: false }],
            orderPlanningNo: [{ value: null, disabled: false }],
            serviceJobCard: [{ value: null, disabled: false }],
            orderType: [{ value: null, disabled: false }, Validators.required],
            transportMode: [{ value: null, disabled: false }],
            transporter: [{ value: null, disabled: false }],
            purchaseOrderStatus: [{ value: null, disabled: true }],
            coDealerMaster: [{ value: null, disabled: false }],
            coDealerName: [{ value: null, disabled: true }],
            priceType: [{ value: null, disabled: false }],
            gstNumber: [{ value: null, disabled: true }],
            createdBy: [{ value: null, disabled: true }],
            model: [{ value: null, disabled: true }],
            mobileNumber: [{ value: null, disabled: true }],
            totalHour: [{ value: null, disabled: true }],
            chassisNumber: [{ value: null, disabled: true }],
            customerName: [{ value: null, disabled: true }],
            engineNumber: [{ value: null, disabled: true }],
            freightBorneBy: [{ value: null, disabled: true }],
            creditLimit: [{ value: null, disabled: true }],
            currentOutStanding: [{ value: null, disabled: true }],
            paymentUnderProcess: [{ value: null, disabled: true }],
            availableLimit: [{ value: null, disabled: true }],
            remarks: [{ value: null, disabled: false }],
            ordersUnderProcess: [{ value: null, disabled: true }],
            overduesOutStanding: [{ value: null, disabled: true }],
            netAmountPayable: [{ value: null, disabled: true }],
            purchaseOrderDate: [{ value: null, disabled: true }],
            orderTypeId:[{value:null,disabled:true}],
            opSheetNoId:[{value:null,disabled:true}]
        })
    }
    private createPoTotal() {
        return this.fb.group({
            totalBasicAmount: [{ value: null, disabled: true }, Validators.compose([Validators.required, CustomValidators.maxDecimalPoint(2)])],
            totalGstAmount: [{ value: null, disabled: true }, Validators.compose([Validators.required, CustomValidators.maxDecimalPoint(2)])],
            totalPOAmount: [{ value: null, disabled: true }, Validators.compose([Validators.required, CustomValidators.maxDecimalPoint(2)])],
            tcsPerc: [{ value: null, disabled: true },Validators.required]
        })
    }
    private createItemDetailsForm() {
        return this.fb.array([
            // this.initItemDetailsForm()
        ])
    }
    private initItemDetailsForm(dataForPatch?: SparesPOPartDetails): FormGroup {
        let machineForm = this.fb.group({
            uuid: [uuid.v4()],
            id: [{ value: null, disabled: true }],
            sparePartMaster: [{ value: null, disabled: dataForPatch ? dataForPatch.isJobCardItem : false }, Validators.compose([Validators.required, CustomValidators.selectFromAutocompleteList()])],
            itemDescription: [{ value: null, disabled: true }],
            // requisitionQty: [{ value: dataForPatch?dataForPatch.:null, disabled: true }],
            quantity: [{ value: null, disabled: false }, Validators.compose([Validators.required, CustomValidators.maxDecimalPoint(1), CustomValidators.validateNumberExceptStartingZero])],
            currentStock: [{ value: null, disabled: true }],
            backOrderAtKai: [{ value: null, disabled: true }],
            transitFromKai: [{ value: null, disabled: true }],
            unitPrice: [{ value: null, disabled: true }],
            baseAmount: [{ value: null, disabled: true }],
            gstPercent: [{ value: null, disabled: true }],
            gstAmount: [{ value: null, disabled: true }, Validators.compose([CustomValidators.maxDecimalPoint(4)])],
            totalAmount: [{ value: null, disabled: true }, Validators.compose([CustomValidators.maxDecimalPoint(4)])],
            accpacOrderNumber: [{ value: null, disabled: true }],
            moq : [null],
            isSelected: [{ value: false, disabled: false }]
        });
        if (dataForPatch) {
            machineForm.patchValue(dataForPatch);  //Patch data for edit and view
            if(dataForPatch.sparePartMaster==null){
                machineForm.controls.sparePartMaster.patchValue({'itemNo':dataForPatch.itemNo, 'id':dataForPatch.itemId})
            }
            this._changeDetectorRef.markForCheck();
        }
        return machineForm;
    }
    public initializeItemDetailsForm(dataForPatch?: SparesPOPartDetails): FormGroup {
        return this.initItemDetailsForm(dataForPatch);
    }
}