import { Injectable } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormArray, FormControl, AbstractControl } from '@angular/forms';
import { ValidateMax, CustomValidators } from '../../../../../utils/custom-validators';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';
import { MaterialDetailApiService } from '../material-details-charges/material-detail-api.service';
import { map } from 'rxjs/operators';
import * as uuid from 'uuid';
import { InvoiceStoreService } from '../../invoice-store.service';
import { BehaviorSubject } from 'rxjs';
import { numberToWordConvertor, NumberToWords } from '../../../../../utils/number-to-word';

@Injectable()
export class InvoiceAccessoryDetailService {
  tableFormArray: FormArray;
  private accessoryTableForm: FormGroup;
  private totalInvoiceCalcForm: FormGroup;
  private totalValue = new FormControl(null);
  totalInvoiceAmountInWords$ = new BehaviorSubject<string>(null);
  private accessoryTableFormArray: FormArray;
  constructor(
    private fb: FormBuilder,
    private materialDetailApiService: MaterialDetailApiService,
    private invoiceStoreService: InvoiceStoreService
  ) { }
  createTotalInvoiceCalcForm() {
    this.totalInvoiceCalcForm = this.fb.group({
      baseAmount: [{ value: null, disabled: true }],
      totalGSTAmount: [{ value: null, disabled: true }],
      rtoCharges: [null, Validators.compose([CustomValidators.floatNumber])],
      insuranceCharges: [null, [CustomValidators.floatNumber]],
      others: [{ value: null, disabled: false }, Validators.compose([CustomValidators.floatNumber])],
      totalInvoiceAmount: [{ value: null, disabled: true }],
    });
    this.baseAmountChanges(this.totalInvoiceCalcForm.get('baseAmount'));
    this.totalGSTAmountChanges(this.totalInvoiceCalcForm.get('totalGSTAmount'));
    this.rtoChargesChanges(this.totalInvoiceCalcForm.get('rtoCharges'));
    this.insuranceChargesChanges(this.totalInvoiceCalcForm.get('insuranceCharges'));
    this.othersChanges(this.totalInvoiceCalcForm.get('others'));
    this.invoiceStoreService.saveTotalInvoiceCalcForm(this.totalInvoiceCalcForm);
    return this.totalInvoiceCalcForm;
  }
  private baseAmountChanges(fc: AbstractControl) {
    fc.valueChanges.subscribe(val => {
      const baseAmount = parseFloat(val) || 0;
      const totalGSTAmount = parseFloat(this.totalInvoiceCalcForm.get('totalGSTAmount').value) || 0;
      const rtoCharges = parseFloat(this.totalInvoiceCalcForm.get('totalGSTAmount').value) || 0;
      const insuranceCharges = parseFloat(this.totalInvoiceCalcForm.get('rtoCharges').value) || 0;
      const others = parseFloat(this.totalInvoiceCalcForm.get('others').value) || 0;
      const totalInvoiceAmount = this.calcTotalInvoiceAmount(baseAmount, totalGSTAmount, rtoCharges, insuranceCharges, others);
      this.totalInvoiceCalcForm.get('totalInvoiceAmount').patchValue(totalInvoiceAmount);
      //this.totalInvoiceAmountInWords$.next(numberToWordConvertor(totalInvoiceAmount, ''));
      this.totalInvoiceAmountInWords$.next(NumberToWords.rsPaise(totalInvoiceAmount));
    })
  }
  private totalGSTAmountChanges(fc: AbstractControl) {
    fc.valueChanges.subscribe(val => {
      const totalGSTAmount = parseFloat(val) || 0;
      const baseAmount = parseFloat(this.totalInvoiceCalcForm.get('baseAmount').value) || 0;
      const rtoCharges = parseFloat(this.totalInvoiceCalcForm.get('rtoCharges').value) || 0;
      const insuranceCharges = parseFloat(this.totalInvoiceCalcForm.get('insuranceCharges').value) || 0;
      const others = parseFloat(this.totalInvoiceCalcForm.get('others').value) || 0;
      const totalInvoiceAmount = this.calcTotalInvoiceAmount(baseAmount, totalGSTAmount, rtoCharges, insuranceCharges, others);
      this.totalInvoiceCalcForm.get('totalInvoiceAmount').patchValue(totalInvoiceAmount);
      // this.totalInvoiceAmountInWords$.next(numberToWordConvertor(totalInvoiceAmount, ''));
      this.totalInvoiceAmountInWords$.next(NumberToWords.rsPaise(totalInvoiceAmount));
    });
  }
  private rtoChargesChanges(fc: AbstractControl) {
    fc.valueChanges.subscribe(val => {
      const rtoCharges = parseFloat(val) || 0;
      const baseAmount = parseFloat(this.totalInvoiceCalcForm.get('baseAmount').value) || 0;
      const totalGSTAmount = parseFloat(this.totalInvoiceCalcForm.get('totalGSTAmount').value) || 0;
      const insuranceCharges = parseFloat(this.totalInvoiceCalcForm.get('insuranceCharges').value) || 0;
      const others = parseFloat(this.totalInvoiceCalcForm.get('others').value) || 0;
      const totalInvoiceAmount = this.calcTotalInvoiceAmount(baseAmount, totalGSTAmount, rtoCharges, insuranceCharges, others);
      this.totalInvoiceCalcForm.get('totalInvoiceAmount').patchValue(totalInvoiceAmount);
      // this.totalInvoiceAmountInWords$.next(numberToWordConvertor(totalInvoiceAmount, ''));
      this.totalInvoiceAmountInWords$.next(NumberToWords.rsPaise(totalInvoiceAmount));
    })
  }
  private insuranceChargesChanges(fc: AbstractControl) {
    fc.valueChanges.subscribe(val => {
      const insuranceCharges = parseFloat(val) || 0;
      const baseAmount = parseFloat(this.totalInvoiceCalcForm.get('baseAmount').value) || 0;
      const totalGSTAmount = parseFloat(this.totalInvoiceCalcForm.get('totalGSTAmount').value) || 0;
      const rtoCharges = parseFloat(this.totalInvoiceCalcForm.get('rtoCharges').value) || 0;
      const others = parseFloat(this.totalInvoiceCalcForm.get('others').value) || 0;
      const totalInvoiceAmount = this.calcTotalInvoiceAmount(baseAmount, totalGSTAmount, rtoCharges, insuranceCharges, others);
      this.totalInvoiceCalcForm.get('totalInvoiceAmount').patchValue(totalInvoiceAmount);
      //this.totalInvoiceAmountInWords$.next(numberToWordConvertor(totalInvoiceAmount, ''));
      this.totalInvoiceAmountInWords$.next(NumberToWords.rsPaise(totalInvoiceAmount));
    })
  }
  private othersChanges(fc: AbstractControl) {
    fc.valueChanges.subscribe(val => {
      const others = parseFloat(val) || 0;
      const baseAmount = parseFloat(this.totalInvoiceCalcForm.get('baseAmount').value) || 0;
      const totalGSTAmount = parseFloat(this.totalInvoiceCalcForm.get('totalGSTAmount').value) || 0;
      const rtoCharges = parseFloat(this.totalInvoiceCalcForm.get('rtoCharges').value) || 0;
      const insuranceCharges = parseFloat(this.totalInvoiceCalcForm.get('insuranceCharges').value) || 0;
      const totalInvoiceAmount = this.calcTotalInvoiceAmount(baseAmount, totalGSTAmount, rtoCharges, insuranceCharges, others);
      this.totalInvoiceCalcForm.get('totalInvoiceAmount').patchValue(totalInvoiceAmount);
      //this.totalInvoiceAmountInWords$.next(numberToWordConvertor(totalInvoiceAmount, ''));
      this.totalInvoiceAmountInWords$.next(NumberToWords.rsPaise(totalInvoiceAmount));
    })
  }

  createAccessoryTableForm() {
    this.accessoryTableForm = this.fb.group({
      table: this.fb.array([])
    });
    this.accessoryTableFormArray = this.accessoryTableForm.get('table') as FormArray;
    this.invoiceStoreService.saveAccessoryTable(this.accessoryTableForm);
    return this.accessoryTableForm;
  }
  createTableRow(data?: InvoiceMaterialByDc): FormGroup {
    const fg = this.fb.group(this.getControlsConfigForTableFormArray(data));
    this.rateValueChanges(fg);
    this.qtyValueChanges(fg);
    this.discountValueChanges(fg);
    //this.gstPercentValueChanges(fg);
    this.gstAmountValueChanges(fg);
    this.basicAmountValueChanges(fg);
    return fg
  }
  private gstAmountValueChanges(fg) {
    fg.get('gstAmount').valueChanges.subscribe(val => {
      const basicAmount = parseFloat(fg.get('basicAmount').value) || 0;
      const gstAmount = parseFloat(val) || 0;
      fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
      this.invoiceStoreService.calcTotalInvoiceAmount('gstAmount');
    });
  }
  private basicAmountValueChanges(fg) {
    fg.get('basicAmount').valueChanges.subscribe(val => {
      const gstAmount = parseFloat(fg.get('gstAmount').value) || 0;
      const basicAmount = parseFloat(val) || 0;
      fg.get('gstAmount').patchValue(gstAmount);
      fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
      this.invoiceStoreService.calcTotalInvoiceAmount('basicAmount');
    });
  }
  private gstPercentValueChanges(fg) {
    /*fg.get('gstPercent').valueChanges.subscribe(val => {
      const basicAmount = parseFloat(fg.get('basicAmount').value) || 0;
      const gstPercent = parseFloat(val) || 0;
      fg.get('gstAmount').patchValue(this.calcGstAmount(basicAmount, gstPercent));
    });*/
      
      const basicAmount = parseFloat(fg.get('basicAmount').value) || 0;
      const gstPercent = parseFloat(fg.get('gstPercent').value) || 0;
      const gstAmount = this.calcGstAmount(basicAmount, gstPercent);
      fg.get('gstAmount').patchValue(gstAmount);
      fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
  }
  private qtyValueChanges(fg) {
    fg.get('qty').valueChanges.subscribe(val => {
      const discount = parseFloat(fg.get('discount').value) || 0;
      const rate = parseFloat(fg.get('rate').value) || 0;
      const qty = parseFloat(val) || 0;
      this.totalValue.patchValue(qty * rate);
      fg.get('basicAmount').patchValue(this.calcBasicAmount(qty, rate, discount));
      this.setCustomErrorToRate(fg);
      this.gstPercentValueChanges(fg)
    });
  }
  private rateValueChanges(fg) {
    fg.get('rate').valueChanges.subscribe(val => {
      const discount = parseFloat(fg.get('discount').value) || 0;
      const qty = parseFloat(fg.get('qty').value) || 0;
      const rate = parseFloat(val) || 0;
      this.totalValue.patchValue(qty * rate);
      fg.get('basicAmount').patchValue(this.calcBasicAmount(qty, rate, discount));
      this.setCustomErrorToRate(fg);
      this.gstPercentValueChanges(fg)
    });
  }
  private setCustomErrorToRate(fg: FormGroup) {
    if (fg.get('discount').value!=null && fg.get('discount').value!='' && this.totalValue.value < fg.get('discount').value) {
      fg.get('discount').setErrors({ discountExceed: true, msg: 'Discount should not exceed than rate' }, { emitEvent: false });
      return;
    }
    fg.get('discount').setErrors(null, { emitEvent: false });
  }
  private discountValueChanges(fg) {
    fg.get('discount').valueChanges.subscribe(val => {
      this.setDiscountCustomValidators(fg);
      const rate = parseFloat(fg.get('rate').value) || 0;
      const qty = parseFloat(fg.get('qty').value) || 0;
      const discount = parseFloat(val) || 0;
      fg.get('basicAmount').patchValue(this.calcBasicAmount(qty, rate, discount));
      this.gstPercentValueChanges(fg)
    });
  }
  private setDiscountCustomValidators(fg: FormGroup) {
    fg.get('discount').setValidators(ValidateMax(this.totalValue, 'Discount value should not exceed to rate'));
    fg.get('discount').updateValueAndValidity({ emitEvent: false });
  }
  private getControlsConfigForTableFormArray(data = null) {
    return {
      id: [data ? data.id : null],
      machineInventoryId: [{ value: data ? data.machineInventoryId : null, disabled: false }],
      dcId: [{ value: data ? data.dcId : null, disabled: false }],
      uuid: [uuid.v4()],
      dcuuid: [data ? data.dcuuid : null],
      itemNumber: [{ value: data ? data.itemNumber : null, disabled: false }, Validators.compose([Validators.required])],
      itemDescription: [{ value: data ? data.itemDescription : null, disabled: false }, Validators.compose([Validators.required])],
      hsnCode: [{ value: data ? data.hsnCode : null, disabled: false }, Validators.compose([Validators.required])],
      qty: [{ value: data ? data.quantity : null, disabled: false }, Validators.compose([Validators.required])],
      // engineNumber: [{ value: data ? data.engineNumber : null, disabled: true }],
      rate: [{ value: data ? data.rate : null, disabled: false }, Validators.compose([Validators.required])],
      discount: [{ value: data ? data.discount : 0, disabled: false }, Validators.compose([])],
      basicAmount: [{ value: data ? data.basicAmount : null, disabled: true }, Validators.compose([Validators.required])],
      gstPercent: [{ value: data ? data.gstPercent : null, disabled: false }, Validators.compose([])],
      gstAmount: [{ value: data ? data.gstAmount : null, disabled: false }, Validators.compose([])],
      totalAmount: [{ value: data ? data.totalAmount : null, disabled: false }, Validators.compose([Validators.required])]
    }
  }
  getGSTPercentList() {
    return this.materialDetailApiService.getGSTPercentList().pipe(map(res => res.result));
  }
  calcBasicAmount = (qty: number, rate: number, discount: number) => (qty * rate) - discount;
  calcGstAmount = (basicAmount: number, gst: number) => (basicAmount * gst) / 100;
  calcTotalAmount = (basicAmount: number, gstAmount: number) => (basicAmount + gstAmount);
  calcTotalInvoiceAmount = (baseAmt: number, gstAmt: number, rtoCharges: number, insuranceCharges: number, other: number) => baseAmt + gstAmt + rtoCharges + insuranceCharges + other;
}
