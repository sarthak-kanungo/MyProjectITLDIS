import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, AbstractControl } from '@angular/forms';
import { SparesGrnPageStore } from './spares-grn-page.store';
import { Observable, BehaviorSubject, Subject, defer } from 'rxjs';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { map } from 'rxjs/operators';
import { SparesGrnApiService } from '../spares-grn/spares-grn-api.service';

export interface ISparesGrnPagePresenter {
    hideFieldsAtItemDetailWhenGrnTypeIsOthers;
    readonly getHideFieldsAtItemDetailWhenGrnTypeIsOthers$: Observable<boolean>;
    readonly grnForm: FormGroup
    readonly itemDeatailTotalForm: FormGroup
    readonly getItemDetail$: Observable<FormArray>
    readonly itemDetailFa: FormArray
    resetItemDetailToatlForm(): void;
    insertRowIntoItemDetail(): void;
    insertRowIntoItemDetailWhereGrnTypeIsOthers(): void;
    defaultEnableAndDisableFieldsOfGrnForm(): void;
    defaultEnableAndDisableFieldsOfGrnFormForGrnTypeOthers(): void;
    clearAllItemDetail(): void;
    resetSupplierInfo(): void;
    resetGrnFormWhenGrnTypeChange(): void;
    disable(): void;
    patchValue(grnData): void;
}
@Injectable()
export class SparesGrnPagePresenter {
    public readonly goodsReceiptNoteForm: FormGroup;
    private sparesGrnPageStore: SparesGrnPageStore;
    private makeBinLocationRequired$ = new BehaviorSubject<boolean>(false);
    private isBinLocationRequired = false;
    private _operation: string
    set operation(type: string) {
        this._operation = type
    }
    get operation() {
        return this._operation
    }
    constructor(
        fb: FormBuilder
    ) {
        this.sparesGrnPageStore = new SparesGrnPageStore(fb);
        this.goodsReceiptNoteForm = this.sparesGrnPageStore.getGoodsReceiptNoteForm;
    }

    public set hideFieldsAtItemDetailWhenGrnTypeIsOthers(v: boolean) {
        this.sparesGrnPageStore.hideFieldsAtItemDetailWhenGrnTypeIsOthers = v;
    }

    public get getHideFieldsAtItemDetailWhenGrnTypeIsOthers$(): Observable<boolean> {
        return this.sparesGrnPageStore.getHideFieldsAtItemDetailWhenGrnTypeIsOthers$;
    }
    public get grnForm(): FormGroup {
        if (this.goodsReceiptNoteForm) {
            return this.goodsReceiptNoteForm.get('grn') as FormGroup;
        }
        // this.goodsReceiptNoteForm = this.sparesGrnPageStore.getGoodsReceiptNoteForm;
        return this.goodsReceiptNoteForm.get('grn') as FormGroup;
    }

    public get itemDeatailTotalForm(): FormGroup {
        return this.sparesGrnPageStore.itemDetailTotalForm;
    }

    public get getItemDetail$(): Observable<FormArray> {
        return this.sparesGrnPageStore.itemDetail$;
    }
    public get itemDetailFa(): FormArray {
        return this.sparesGrnPageStore.getItemDetail
    }

    public set setBinLocationRequired(isRequired: boolean) {
        this.makeBinLocationRequired$.next(isRequired);
        this.isBinLocationRequired = isRequired;
    }

    insertRowIntoItemDetail(data?, isEdit?) {
        const newFg = this.sparesGrnPageStore.insertRowIntoItemDetail(data, isEdit)
        this.setValidatorsToItemDetail(newFg);
    }
    insertRowIntoItemDetailWhereGrnTypeIsOthers(data?) {
        // const newFg = this.sparesGrnPageStore.insertRowIntoItemDetailWhereGrnTypeIsOthers();
        const newFg = this.sparesGrnPageStore.insertRowIntoItemDetail(data)
        //newFg.removeControl('accPacOrderNumber');
        //newFg.removeControl('dmsPoNumber');
        this.sparesGrnPageStore.defaultEnableAndDisableFieldsOfItemDetailForGrnTypeOther(newFg);
        this.setValidatorsToItemDetail(newFg);
    }
    defaultEnableAndDisableFieldsOfGrnForm() {
        this.sparesGrnPageStore.defaultEnableAndDisableFieldsOfGrnForm();
    }
    defaultEnableAndDisableFieldsOfGrnFormForGrnTypeOthers() {
        this.sparesGrnPageStore.defaultEnableAndDisableFieldsOfGrnFormForGrnTypeOthers();
    }
    clearAllItemDetail() {
        if (this.sparesGrnPageStore.getItemDetail) {
            this.sparesGrnPageStore.getItemDetail.clear();
            this.sparesGrnPageStore.itemDetail$ = [];
        }
    }
    reset(){
        this.clearAllItemDetail();
        const {grnDoneBy,grnDate}=this.grnForm.getRawValue();
        this.grnForm.reset();
        this.grnForm.patchValue({grnDoneBy,grnDate});
    }
    resetSupplierInfo() {
        this.grnForm.get('supplierName').reset();
        this.grnForm.get('supplierGstNo').reset();
        this.grnForm.get('supplierAddress1').reset();
        this.grnForm.get('supplierAddress2').reset();
        this.grnForm.get('supplierState').reset();
    }
    resetItemDetailToatlForm() {
        if (this.sparesGrnPageStore.itemDetailTotalForm) {
            this.sparesGrnPageStore.itemDetailTotalForm.reset();
        }
    }
    validateGrn(isBinLocationRequired = false) {
        

        this.setBinLocationRequired = isBinLocationRequired;
        // setTimeout(() => {
        //     // this.sparesGrnPageStore.getItemDetail.controls[0].get('binLocation').updateValueAndValidity();
        this.itemDetailFa.patchValue(this.itemDetailFa.getRawValue());
        //     this.setBinLocationRequired = false;
        //     this.goodsReceiptNoteForm.markAllAsTouched();
        // }, 10);
        /* if (isBinLocationRequired) {
            this.sparesGrnPageStore.getItemDetail.controls.forEach(fg => {
                fg.get('binLocation').setErrors({ required: true })
                // fg.get('binLocation').updateValueAndValidity();
                
            });
        } else {
            this.sparesGrnPageStore.getItemDetail.controls.forEach(fg => {
                fg.get('binLocation').setErrors(null)
                // fg.get('binLocation').updateValueAndValidity();
                
            });
        } */
        this.goodsReceiptNoteForm.markAllAsTouched();
        return this.goodsReceiptNoteForm.valid;
    }
    public get getRowValueOfGoodsReceiptNoteForm() {
        return this.goodsReceiptNoteForm.getRawValue();
    }
    resetGrnFormWhenGrnTypeChange() {
        for (const key in this.grnForm.controls) {
            if (this.grnForm.controls.hasOwnProperty(key)) {
                if (key === 'grnDoneBy' || key === 'grnType' || key === 'supplierType' || key === 'grnDate') {
                    continue;
                }
                const element = this.grnForm.controls[key];
                element.reset();
            }
        }
    }
    setValidatorsToItemDetail(fg: FormGroup) {
        fg.get('receivedDamageQty').setValidators([CustomValidators.max(fg.get('totalReceivedQty')), CustomValidators.numberOnly, Validators.required]);
        fg.get('totalReceivedQty').setValidators([CustomValidators.max(fg.get('balancedQty')), CustomValidators.numberOnly, Validators.required]);
        fg.get('totalReceivedQty').updateValueAndValidity({ onlySelf: true, emitEvent: false });
        fg.get('receivedDamageQty').updateValueAndValidity({ onlySelf: true, emitEvent: false });
        const selfRef = this;
        fg.get('binLocation').setValidators([this.customRequiredValidatorForBinLocation({
            requiredValidatorForBinLocation: function (ref) { return ref.checkBinLocationRequired.bind(ref) }(selfRef),
            stopUpdateValueAndValidity: function (ref) { return ref.stopUpdateValueAndValidity.bind(ref) }(selfRef),
            updateValueAndValidity: function (ref) { return ref.updateValueAndValidity.bind(ref) }(selfRef)
        })]);
        fg.get('binLocation').updateValueAndValidity({ onlySelf: true, emitEvent: false });
        fg.get('unitPrice').setValidators([CustomValidators.floatNumber, Validators.required]);
        fg.get('unitPrice').updateValueAndValidity({ onlySelf: true, emitEvent: false });
        fg.get('invoiceQty').setValidators([CustomValidators.numberOnly, Validators.required]);
        fg.get('invoiceQty').updateValueAndValidity({ onlySelf: true, emitEvent: false });
    }
    checkBinLocationRequired() {
        
        // const selfRef = this;
        // return defer(async function () {
        //     return new Promise(resolve => {
        //         setTimeout(() => resolve(selfRef.makeBinLocationRequired$.value), 1);
        //     });
        // })
        return this.makeBinLocationRequired$.value;
    }
    stopUpdateValueAndValidity$ = new BehaviorSubject<boolean>(false);
    stopUpdateValueAndValidity() {
        this.stopUpdateValueAndValidity$.next(false);
    }
    validateBinLocation$ = new Subject<object>();
    updateValueAndValidity() {
        // this.stopUpdateValueAndValidity$.next(isStop);
        const selfRef = this;
        // async function makeFalse() {

        //     return await selfRef.stopUpdateValueAndValidity$.toPromise();
        // }
        // makeFalse()
        // return this.validateBinLocation$.value;
        return defer(async function () {
            return new Promise(resolve => {
                let err;
                if (selfRef.makeBinLocationRequired$.value) {
                    err = { required: true };
                } else {
                    err = null;
                }
                setTimeout(() => {
                    
                    resolve(err)
                }, 1);
            });
        })
    }
    disable() {
        this.goodsReceiptNoteForm.disable();
    }
    patchValue(grnData, options?: {
        onlySelf?: boolean;
        emitEvent?: boolean;
        isEdit?: boolean;
    }): void {
        const grnForm = { ...grnData['grn'] };
        this.grnForm.get('grnType').patchValue(grnForm.grnType, options);
        delete grnForm.grnType;
        this.grnForm.patchValue(grnForm, options);
        this.grnForm.patchValue({
            invoiceNumber: grnForm.invoiceId ? { id: grnForm.invoiceId, value: grnForm.invoiceNumber } : grnForm.invoiceNumber || null,
            store: { id: grnForm.storeId, value: grnForm.store },
            sparePurchaseOrder: grnData['grn'].grnType === 'Others' ? { value: grnData['grnItems'][0]['dmsPoNo'], id: grnData['grnItems'][0]['sparePurchaseOrderId'] } : null,
            supplierName: grnForm.supplierId ? { value: grnForm.supplierName, id: grnForm.supplierId } : null
        }, options);
        grnData['grnItems'].forEach(element => {
            if (grnData['grn'].grnType === 'Others') {
                this.insertRowIntoItemDetailWhereGrnTypeIsOthers(element);
                return;
            }
            this.insertRowIntoItemDetail(element);
        });
        this.itemDeatailTotalForm.patchValue(grnData['grn']);
        this.goodsReceiptNoteForm.updateValueAndValidity({ emitEvent: true });
        
    }
    private customRequiredValidatorForBinLocation(validateRequired) {
        return (control: AbstractControl) => {
            
            /*   return validateRequired.requiredValidatorForBinLocation().pipe(map(res => {
                  
                  
                  
                  if (res) {
                      // if (validateRequired.updateValueAndValidity()) {
                      //     validateRequired.stopUpdateValueAndValidity();
                      //     // control.setValidators(Validators.required);
                      //     setTimeout(() => {
                      //         control.updateValueAndValidity({ onlySelf: true });
                      //     }, 1);
                      //     return { required: true };
                      // }
                      return validateRequired.updateValueAndValidity().pipe(map(res => res))
                  }
                  // control.clearValidators();
                  // control.setErrors(null)
                  // if (validateRequired.updateValueAndValidity()) {
                  //     validateRequired.stopUpdateValueAndValidity();
                  //     // control.setValidators(Validators.required);
                  //     setTimeout(() => {
                  //         control.updateValueAndValidity({ onlySelf: true });
                  //     }, 1);
                  // }
                  return null;
              })); */
            

            if (validateRequired.requiredValidatorForBinLocation() && !control.value) {
                // control.setValidators(Validators.required);
                // control.updateValueAndValidity();
                control.setErrors({ required: true });
                return { required: true };
            }
            // control.setValidators(null);
            // control.updateValueAndValidity();
            control.setErrors(null);
            return null;
        }
    }
}
