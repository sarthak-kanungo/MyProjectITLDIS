import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';
import * as uuid from 'uuid';
import { BehaviorSubject, Subject, Subscription } from 'rxjs';
import { EtDataHandlerService, ColumnRecord } from 'editable-table';
import { QuotationCreateStoreService } from '../../pages/quotation-create/quotation-create-store.service';
import { ValidateFloat, ValidateMaxDecimalPoint } from '../../../../../utils/custom-validators';
import { delay, tap } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';


@Injectable()
export class AddImplementsService {

  implementsDetailsForm: FormGroup;
  implementsDisplayForm: FormGroup;
  chargesForm: FormGroup;
  calcDisplayForm: FormGroup;
  isShowAddImplementsTable: boolean
  calcBasePrice = (qty: number, rate: number) => qty * rate;
  // calcAmountAfterDiscount = (qty: number, rate: number, discount: number) => (qty * rate) - discount;
  calcAmountAfterDiscount = (basePrice: number, discount: number) => basePrice - discount;
  calcGstAmt = (base: number, gst: number) => (base * gst) / 100;
  calcTotalAmt = (base: number, gst: number) => (base + gst);
  subject$: Subject<object> = new Subject();
  private resetAddImplementsTable$ = new Subject<boolean>();
  private subscription = new Subscription();
  private subscriptionStore = new Map<string, Subscription>();
  patchBaseValue: any;
  patchDiscount: any;

  constructor(
    private fb: FormBuilder,
    private etDataHandlerService: EtDataHandlerService,
    private toastr: ToastrService,
    private quotationCreateStoreService: QuotationCreateStoreService
  ) {
    this.subscribeResetAllForm();
  }

  storeSubcription(name: string, subscription: Subscription) {
    if (!!name && !!subscription) {
      this.subscriptionStore.set(name, subscription);
      this.subscription.add(subscription);
    }
  }
  unsubscribeAll() {
    this.subscription.unsubscribe();
  }
  unsubscribeOne(name: string) {
    if (this.subscriptionStore.has(name)) {
      const unsubscribe = this.subscriptionStore.get(name);
      unsubscribe.unsubscribe();
      this.subscriptionStore.delete(name);
      this.subscription.remove(unsubscribe);
    }
  }
  checkSubsriptionWithName(name: string) {
    return this.subscriptionStore.has(name) ? false : true;
  }
  implementsDisplayFormCreate(isView:boolean) {
    this.implementsDisplayForm = this.fb.group({
      itemNo: [{ value: null, disabled: true }, Validators.compose([])],
      itemDescription: [{ value: null, disabled: true }, Validators.compose([])],
      color: [{ value: null, disabled: true }, Validators.compose([])],
      qty: [{ value: null, disabled: true }, Validators.compose([])],
      rate: [{ value: null, disabled: false }, Validators.compose([Validators.required, ValidateMaxDecimalPoint(2)])],
      basicPrice: [{ value: null, disabled: true }],
      discount: [null, Validators.compose([ValidateMaxDecimalPoint(2)])],
      amountAfterDiscount: [{ value: null, disabled: true }, Validators.compose([])],
      igst: [{ value: null, disabled: true }, Validators.compose([])],
      sgst: [{ value: null, disabled: true }, Validators.compose([])],
      cgst: [{ value: null, disabled: true }, Validators.compose([])],
      gstamt: [{ value: null, disabled: true }, Validators.compose([])],
      total: [{ value: null, disabled: true }, Validators.compose([])],
    });
    if (isView) {
      return this.implementsDisplayForm;
    }
    this.rateValueChangesInImplementsDisplayForm();
    this.qtyValueChangesInImplementsDisplayForm();
    this.basicPriceValueChangesInImplementsDisplayForm();
    this.amountAfterDiscountValueChangesInImplementsDisplayForm();
    this.discountValueChangesInImplementsDisplayForm();
    this.gstAmtValueChangesInImplementsDisplayForm();
    return this.implementsDisplayForm;
  }
  private gstAmtValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls.gstamt.valueChanges.subscribe(gstamt => {
      if (this.isShowAddImplementsTable) {
        this.etDataHandlerService.sendColumnNameRecord$.next('gstAmount');
      } else {
        this.calcTotalGSTAmountAndPatchValueInCalcDisplayForm([]);
      }
    })
  }
  private rateValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls.rate.valueChanges.subscribe(rate => {
      let frmDt = this.implementsDisplayForm.getRawValue();
      if (frmDt.discount !== 0 && frmDt.discount !== null && !this.checkDiscountIsLessThanRate(frmDt.discount, frmDt.rate)) {
        this.implementsDisplayForm.get('discount').patchValue(0);
        return;
      }
      const basicPrice = this.calcBasePrice(frmDt['qty'], parseFloat(rate || 0));
      this.implementsDisplayForm.controls.basicPrice.patchValue(basicPrice);
    })
  }
  private qtyValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls.qty.valueChanges.subscribe(qty => {
      let frmDt = this.implementsDisplayForm.getRawValue()
      const basicPrice = this.calcBasePrice(parseFloat(qty || 0), frmDt['rate']);
      this.implementsDisplayForm.controls.basicPrice.patchValue(basicPrice);
    });
  }
  private basicPriceValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls.basicPrice.valueChanges.subscribe(basicPrice => {
      let frmDt = this.implementsDisplayForm.getRawValue()
      const amountAfterDiscount = this.calcAmountAfterDiscount(basicPrice || 0, parseFloat(frmDt['discount'] || 0));
      this.implementsDisplayForm.controls.amountAfterDiscount.patchValue(amountAfterDiscount);
      let gstAmt: number;
      if (frmDt.igst) {
        gstAmt = this.calcGstAmt(amountAfterDiscount, frmDt.igst);
      } else {
        gstAmt = this.calcGstAmt(amountAfterDiscount, (frmDt.cgst + frmDt.sgst));
      }
      this.implementsDisplayForm.controls['gstamt'].setValue(gstAmt)
      this.implementsDisplayForm.controls['total'].setValue(
        this.calcTotalAmt(amountAfterDiscount, gstAmt || 0)
      )
      if (this.isShowAddImplementsTable) {
        this.etDataHandlerService.sendColumnNameRecord$.next('basicPrice');
      } else {
        this.calcTotalBasicValueAndPatchValueInCalcDisplayForm([]);
      }
    })
  }
  private amountAfterDiscountValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls.amountAfterDiscount.valueChanges.subscribe(amountAfterDiscount => {
      if (this.isShowAddImplementsTable) {
        this.etDataHandlerService.sendColumnNameRecord$.next('amountAfterDiscount');
      } else {
        this.calcTotalBasicValueAndPatchValueInCalcDisplayForm([]);
      }
    })
  }
  private discountValueChangesInImplementsDisplayForm() {
    this.implementsDisplayForm.controls['discount'].valueChanges.subscribe(frmVal => {
      let frmDt = this.implementsDisplayForm.getRawValue();

      if (frmVal !== 0 && frmVal !== null && !this.checkDiscountIsLessThanRate(frmDt.discount, frmDt.rate)) {
        this.implementsDisplayForm.get('discount').patchValue(0);
        return;
      }
      const basicPrice = this.calcBasePrice(frmDt['qty'], parseFloat(frmDt['rate'] || 0));
      const amountAfterDiscount = this.calcAmountAfterDiscount(basicPrice, parseFloat(frmVal || 0))
      this.implementsDisplayForm.controls.amountAfterDiscount.patchValue(amountAfterDiscount);
      let gstAmt: number;
      if (frmDt.igst) {
        gstAmt = this.calcGstAmt(amountAfterDiscount, frmDt.igst);
      } else {
        gstAmt = this.calcGstAmt(amountAfterDiscount, (frmDt.cgst || 0) + (frmDt.sgst || 0));
      }
      this.implementsDisplayForm.controls['gstamt'].setValue(Number.parseFloat(gstAmt.toFixed(2)))
      this.implementsDisplayForm.controls['total'].setValue(
        this.calcTotalAmt(amountAfterDiscount, gstAmt || 0)
      )
      if (this.isShowAddImplementsTable) {
        this.etDataHandlerService.sendColumnNameRecord$.next('grossDiscount');
      } else {
        this.calcTotalDiscountAndPatchValueInCalcDisplayForm([]);
      }
    })
  }
  checkDiscountIsLessThanRate(discount: any, rate: any) {
    if (parseFloat(discount) <= parseFloat(rate)) {
      return true;
    }
    this.toastr.warning('The discount should not greater than rate', 'Wrong Value');
    return false;
  }

  chargesFormCreate() {
    this.chargesForm = this.fb.group({
      insurance: [null, Validators.compose([ValidateMaxDecimalPoint(2)])],
      rto: [null, Validators.compose([ValidateMaxDecimalPoint(2)])]
    });
    this.chargesForm.controls.insurance.valueChanges.subscribe(insuranceAmt => {
      let rtoAmt = this.chargesForm.controls.rto.value;
      rtoAmt = parseFloat(rtoAmt) ? parseFloat(rtoAmt) : 0;
      if (insuranceAmt) {
        let totalAmt = parseFloat(insuranceAmt) + parseFloat(rtoAmt);
        this.calcDisplayForm.controls.charges.patchValue(totalAmt);
        return;
      }
      this.calcDisplayForm.controls.charges.patchValue(rtoAmt);
    });
    this.chargesForm.controls.rto.valueChanges.subscribe(rtoAmt => {
      let insuranceAmt = this.chargesForm.controls.insurance.value;
      insuranceAmt = parseFloat(insuranceAmt) ? parseFloat(insuranceAmt) : 0;
      if (rtoAmt) {
        let totalAmt = parseFloat(insuranceAmt) + parseFloat(rtoAmt);
        this.calcDisplayForm.controls.charges.patchValue(totalAmt);
        return;
      }
      this.calcDisplayForm.controls.charges.patchValue(insuranceAmt);
    });
    return this.chargesForm;
  }

  calcDisplayFormCreate() {
    this.calcDisplayForm = this.fb.group({
      basicValue: [{ value: '', disabled: true }, Validators.compose([])],
      discount: [{ value: '', disabled: true }, Validators.compose([])],
      taxableAmount: [{ value: '', disabled: true }, Validators.compose([])],
      gstAmt: [{ value: '', disabled: true }, Validators.compose([ValidateMaxDecimalPoint(2)])],
      charges: [{ value: '', disabled: true }, Validators.compose([])],
      totalAmt: [{ value: '', disabled: true }, Validators.compose([])],
      // grossTotal: [{ value: '', disabled: true }, Validators.compose([])],
    });
    this.calcDisplayForm.controls.basicValue.valueChanges.subscribe(basicValue => {
      let discount = this.calcDisplayForm.controls.discount.value;
      let gstAmt = this.calcDisplayForm.controls.gstAmt.value;
      let charges = this.calcDisplayForm.controls.charges.value;
      this.calcTotalAmountAndPatchValueAtTotalAmtInCalcDisplayForm(basicValue, discount, gstAmt, charges);
      this.patchBaseValue = basicValue;
      this.patchDiscount = discount;
      this.calcTaxableAmountInCalcDisplayForm();
    });
    this.calcDisplayForm.controls.discount.valueChanges.subscribe(discount => {
      let basicValue = this.calcDisplayForm.controls.basicValue.value;
      let gstAmt = this.calcDisplayForm.controls.gstAmt.value;
      let charges = this.calcDisplayForm.controls.charges.value;
      this.calcTotalAmountAndPatchValueAtTotalAmtInCalcDisplayForm(basicValue, discount, gstAmt, charges);
      this.patchBaseValue = basicValue;
      this.patchDiscount = discount;
      this.calcTaxableAmountInCalcDisplayForm();
    });
    this.calcDisplayForm.controls.gstAmt.valueChanges.subscribe(gstAmt => {
      let basicValue = this.calcDisplayForm.controls.basicValue.value;
      let discount = this.calcDisplayForm.controls.discount.value;
      let charges = this.calcDisplayForm.controls.charges.value;
      this.calcTotalAmountAndPatchValueAtTotalAmtInCalcDisplayForm(basicValue, discount, gstAmt, charges);
    });
    this.calcDisplayForm.controls.charges.valueChanges.subscribe(charges => {
      let basicValue = this.calcDisplayForm.controls.basicValue.value;
      let discount = this.calcDisplayForm.controls.discount.value;
      let gstAmt = this.calcDisplayForm.controls.gstAmt.value;
      this.calcTotalAmountAndPatchValueAtTotalAmtInCalcDisplayForm(basicValue, discount, gstAmt, charges);
    });
    
    return this.calcDisplayForm;
  }
  private subscribeResetAllForm() {
    if (!this.checkSubsriptionWithName('restAllForm')) {
      return;
    }
    const subscrb = this.quotationCreateStoreService.resetAll()
      .pipe(
        delay(150),
        tap(() => {
          this.calcDisplayForm.reset();
        }),
      )
      .subscribe(res => {
        if (res) {
          this.resetAddImplementsTable$.next(true);
          this.implementsDisplayForm.reset();
          setTimeout(() => {
            this.chargesForm.reset();
          }, 1000);
        }
      });
    this.storeSubcription('restAllForm', subscrb);
  }
  calcTotalAmountAndPatchValueAtTotalAmtInCalcDisplayForm(basicValue, discount, gstAmt, charges) {
    basicValue = parseFloat(basicValue) ? parseFloat(basicValue) : 0;
    discount = parseFloat(discount) ? parseFloat(discount) : 0;
    gstAmt = parseFloat(gstAmt) ? parseFloat(gstAmt) : 0;
    charges = parseFloat(charges) ? parseFloat(charges) : 0;
    let totalAmt = basicValue + gstAmt + charges - discount;
    this.calcDisplayForm.controls.totalAmt.patchValue(totalAmt);
  }
  addRow(row?: object) {
  }
  deleteAllRow() {
  }
  deleteRow() {
  }

  private makeCalculationsForCalcDisplayForm(isGetDataFromAddImplementsTable: boolean) {
    if (isGetDataFromAddImplementsTable) {
      this.getEditableTableDataByColumnName();
    } else {
      this.etDataHandlerService.sendColumnNameRecord$.complete();
      this.calcTotalDiscountAndPatchValueInCalcDisplayForm([]);
      this.calcTotalBasicValueAndPatchValueInCalcDisplayForm([]);
      this.calcTotalGSTAmountAndPatchValueInCalcDisplayForm([]);
    }
  }
  checkChange(isShowAddImplementsTable: boolean) {
    this.isShowAddImplementsTable = isShowAddImplementsTable;
    this.makeCalculationsForCalcDisplayForm(isShowAddImplementsTable);
    return this.resetAddImplementsTable$.asObservable();
  }
  private getEditableTableDataByColumnName() {
    this.etDataHandlerService.sendColumnRecord$.subscribe((columnData: ColumnRecord) => {
      if (columnData.recordList.length > -1) {
        if (columnData.formControlName === 'grossDiscount') {
          this.calcTotalDiscountAndPatchValueInCalcDisplayForm(columnData['recordList']);
        }
        if (columnData.formControlName === 'basicPrice') {
          this.calcTotalBasicValueAndPatchValueInCalcDisplayForm(columnData['recordList']);
        }
        if (columnData.formControlName === 'gstAmount') {
          this.calcTotalGSTAmountAndPatchValueInCalcDisplayForm(columnData['recordList']);
        }
      }
    });
  }
  private calcTotalDiscountAndPatchValueInCalcDisplayForm(columnData) {
    let discount = 0;
    let discountInImplementsDisplayForm = this.implementsDisplayForm.controls.discount.value;
    if (!!`${ discountInImplementsDisplayForm }`) {
      discount = parseFloat(discountInImplementsDisplayForm || 0);
    }
    columnData.forEach(des => {
      if (des) {
        discount = discount + parseFloat(des);
      }
    });
    this.patchDiscount = discount;
    this.calcDisplayForm.patchValue({ discount })
    this.calcTaxableAmountInCalcDisplayForm()
  }
  private calcTotalBasicValueAndPatchValueInCalcDisplayForm(columnData) {
    let basicValue = 0;
    basicValue = this.implementsDisplayForm.controls.basicPrice.value || 0;
    columnData.forEach(des => {
      if (des) {
        basicValue = basicValue + parseFloat(des);
      }
    });
    this.patchBaseValue = basicValue;
    this.calcDisplayForm.controls.basicValue.patchValue(this.patchBaseValue);
    this.calcTaxableAmountInCalcDisplayForm()
  }
  private calcTotalGSTAmountAndPatchValueInCalcDisplayForm(gstAmtColumnData) {
    let calcGSTAmt = 0;
    let calGstAmtInImplementsDisplayForm = this.implementsDisplayForm.controls.gstamt.value;
    if (!!`${ calGstAmtInImplementsDisplayForm }` && calGstAmtInImplementsDisplayForm !== null) {
      calcGSTAmt = calGstAmtInImplementsDisplayForm;
    }
    gstAmtColumnData.forEach(gstAmt => {
      if (gstAmt) {
        calcGSTAmt = calcGSTAmt + parseFloat(gstAmt);
      }
    });
    this.calcDisplayForm.patchValue({ gstAmt: Number.parseFloat(calcGSTAmt.toFixed(2)) });
  }
  getQuotationDataForView(callBack: (val) => void) {
    this.quotationCreateStoreService.getQuotionaRecordForView().subscribe(res => {
      callBack(res['quotationImplements'])
      // callBack([{grossDiscount:10}])
      this.implementsDisplayForm.patchValue(res, { onlySelf: true, emitEvent: false });
      this.implementsDisplayForm.controls.gstamt.patchValue(res['gstAmount']);

      this.chargesForm.patchValue(res, { onlySelf: true, emitEvent: false });

      this.calcDisplayForm.patchValue(res, { onlySelf: true, emitEvent: false });
      this.calcDisplayForm.controls.discount.patchValue(res['grossDiscount'], { onlySelf: true, emitEvent: false });
      this.calcDisplayForm.controls.gstAmt.patchValue(res['totalGstAmount'], { onlySelf: true, emitEvent: false });
      this.calcDisplayForm.controls.totalAmt.patchValue(res['totalAmount'], { onlySelf: true, emitEvent: false });
      /*this.patchBaseValue = res.basicValue;
      this.patchDiscount = res.discount;
      this.calcTaxableAmountInCalcDisplayForm();*/
      this.calcDisplayForm.controls.taxableAmount.patchValue(res.totalAmount - (res.totalGstAmount==null?0:res.totalGstAmount) - (res.charges==null?0:res.charges));
    });
  }

  calcTaxableAmountInCalcDisplayForm(){
      let basicValue1 = parseFloat(this.patchBaseValue) ? parseFloat(this.patchBaseValue) : 0;
      let discount1 = parseFloat(this.patchDiscount) ? parseFloat(this.patchDiscount) : 0;
      let taxableAmount = basicValue1 - discount1;
      this.calcDisplayForm.controls.taxableAmount.patchValue(taxableAmount);
  }
}
