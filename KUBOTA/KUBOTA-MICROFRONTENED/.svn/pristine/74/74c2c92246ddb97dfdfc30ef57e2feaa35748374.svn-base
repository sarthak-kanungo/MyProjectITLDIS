import { Injectable } from '@angular/core';
import { TableConfig } from 'editable-table';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidateMax, ValidateInt } from '../../../../../utils/custom-validators';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';
import * as uuid from 'uuid';
import { MaterialDetailApiService } from './material-detail-api.service';
import { map } from 'rxjs/operators';
import { InvoiceStoreService } from '../../invoice-store.service';
@Injectable()
export class MaterialDetailsChargesService {
  etFormArray: FormArray;
  private materialTableFormArray: FormArray;
  private materialTableForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private materialDetailApiService: MaterialDetailApiService,
    private invoiceStoreService: InvoiceStoreService
  ) { }
  createMaterialTableForm() {
    this.materialTableForm = this.fb.group({
      table: this.fb.array([])
    });
    this.materialTableFormArray = this.materialTableForm.get('table') as FormArray;
    this.invoiceStoreService.saveMaterialTable(this.materialTableForm);
    return this.materialTableForm;
  }
  createTableRow(data?: InvoiceMaterialByDc, isApplyValueChanges = true): FormGroup {
    const fg = this.fb.group(this.getControlsConfigForTableFormArray(data));
    // if (!isApplyValueChanges) {
    //   return fg
    // }
    this.rateValueChanges(fg);
    this.discountValueChanges(fg);
    // this.gstPercentValueChanges(fg);
    this.gstAmountValueChanges(fg);
    this.basicAmountValueChanges(fg);
    this.setDiscountCustomValidators(fg);
    return fg
  }
  private gstAmountValueChanges(fg: FormGroup) {
    fg.get('gstAmount').valueChanges.subscribe(val => {
      const basicAmount = parseFloat(fg.get('basicAmount').value) || 0;
      const gstAmount = parseFloat(val) || 0;
      this.invoiceStoreService.calcTotalInvoiceAmount('gstAmount');
      fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
    });
  }
  private basicAmountValueChanges(fg: FormGroup) {
    fg.get('basicAmount').valueChanges.subscribe(val => {
      // const gstAmount = parseFloat(fg.get('basicAmount').value) || 0;
      const basicAmount = parseFloat(val) || 0;
      const selectedGst = fg.get('gstPercent').value;
      const gstPercent = (selectedGst) || 0;

      const gstAmount = this.calcGstAmount(basicAmount, gstPercent);
      fg.get('gstAmount').patchValue(gstAmount);
      fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
      this.invoiceStoreService.calcTotalInvoiceAmount('basicAmount');
    });
  }
  private gstPercentValueChanges(fg: FormGroup) {
    // fg.get('gstPercent').valueChanges.subscribe(val => {
    const basicAmount = parseFloat(fg.get('basicAmount').value) || 0;
    const gstPercent = parseFloat(fg.get('gstPercent').value) || 0;
    const gstAmount = this.calcGstAmount(basicAmount, gstPercent);
    fg.get('gstAmount').patchValue(gstAmount);
    fg.get('totalAmount').patchValue(this.calcTotalAmount(basicAmount, gstAmount));
    // });
  }
  private rateValueChanges(fg: FormGroup) {
    fg.get('rate').valueChanges.subscribe(val => {
      // this.setDiscountCustomValidators(fg);
      const discount = parseFloat(fg.get('discount').value) || 0;
      const rate = parseFloat(val) || 0;
      fg.get('basicAmount').patchValue(this.calcBasicAmount(rate, discount));
      this.setCustomErrorToDiscount(fg);
      this.gstPercentValueChanges(fg)
    });
  }

  private setCustomErrorToDiscount(fg: FormGroup) {
    if (fg.get('discount').value!=null && fg.get('discount').value!='' && fg.get('rate').value < fg.get('discount').value) {
      fg.get('discount').setErrors({ discountExceed: true, msg: 'Discount should not exceed than rate' }, { emitEvent: false });
      return;
    }
    fg.get('discount').setErrors(null, { emitEvent: false });
  }
  private discountValueChanges(fg: FormGroup) {
    fg.get('discount').valueChanges.subscribe(val => {
      const rate = parseFloat(fg.get('rate').value) || 0;
      const discount = parseFloat(val) || 0;
      fg.get('basicAmount').patchValue(this.calcBasicAmount(rate, discount));
      this.gstPercentValueChanges(fg)
    });
  }
  private setDiscountCustomValidators(fg: FormGroup) {
    fg.get('discount').setValidators(ValidateMax(fg.get('rate'), 'Discount should not exceed than rate'));
    fg.get('discount').updateValueAndValidity({ onlySelf: true });
  }
  private getControlsConfigForTableFormArray(data = null) {
    return {
      // id: [data ? data.id : null],
      machineInventoryId: [{ value: data ? data.machineInventoryId : null, disabled: false }],
      dcId: [{ value: data ? data.dcId : null, disabled: false }],
      uuid: [uuid.v4()],
      dcuuid: [data ? data.dcuuid : null],
      itemNumber: [{ value: data ? data.itemNumber : null, disabled: false }, Validators.compose([Validators.required])],
      itemDescription: [{ value: data ? data.itemDescription : null, disabled: false }, Validators.compose([Validators.required])],
      hsnCode: [{ value: data ? data.hsnCode : null, disabled: false }, Validators.compose([Validators.required])],
      chassisNumber: [{ value: data ? data.chassisNumber : null, disabled: false }, Validators.compose([Validators.required])],
      engineNumber: [{ value: data ? data.engineNumber : null, disabled: true }],
      gstPercent: [{ value: data ? data.gstPercent : null, disabled: true }, Validators.compose([])],
      qty: [{ value: data ? data.quantity : null, disabled: false }, Validators.compose([Validators.required])],
      rate: [{ value: data ? data.rate : null, disabled: false }, Validators.compose([Validators.required])],
      discount: [{ value: data ? data.discount : null, disabled: false }, Validators.compose([])],
      basicAmount: [{ value: data ? data.basicAmount : null, disabled: true }, Validators.compose([Validators.required])],
      gstAmount: [{ value: data ? data.gstAmount : null, disabled: false }, Validators.compose([])],
      totalAmount: [{ value: data ? data.totalAmount : null, disabled: false }, Validators.compose([Validators.required])]
    }
  }
  getGSTPercentList() {
    return this.materialDetailApiService.getGSTPercentList().pipe(map(res => res.result));
  }
  calcBasicAmount = (rate: number, discount: number) => rate - discount;
  calcGstAmount = (basicAmount: number, gst: number) => (basicAmount * gst) / 100;
  calcTotalAmount = (basicAmount: number, gstAmount: number) => (basicAmount + gstAmount);
}
