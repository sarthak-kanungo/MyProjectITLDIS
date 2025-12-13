import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable()
export class RetailFinanceService {
  private retailFinanceForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  createretailFinanceForm() {
    this.retailFinanceForm = this.fb.group({
      cashLoan: [null, Validators.compose([Validators.required])],
      assetValue: [0.0],
      financier: [null],
      financeLoggedInDate: [null],
      estimatedFinanceAmount: [{ value: 0.0, disabled: false }],
      financeStatus: [null],
      disbursedDate: [{ value: null, disabled: true }],
      disbursedFinanceAmount: [{ value: 0.0, disabled: true }],
      finalExchangePrice: [{ value: 0.0, disabled: true }],
      subsidy: [null,Validators.compose([Validators.required])],
      subsidyAmount: [0.0],
      marginAmount: [{ value: 0.0, disabled: true }],
    });
    return this.retailFinanceForm
  }

  ViewretailFinanceForm() {
    this.retailFinanceForm = this.fb.group({
      cashLoan: this.fb.control({ value: null, disabled: false }),
      assetValue: this.fb.control(0.0),
      financier: this.fb.control(null),
      financeLoggedInDate: this.fb.control(null),
      estimatedFinanceAmount: this.fb.control(0.0),
      financeStatus: this.fb.control(null),
      disbursedDate: this.fb.control({ value: null, disabled: true }),
      disbursedFinanceAmount: this.fb.control({ value: 0.0, disabled: true }),
      finalExchangePrice: this.fb.control({ value: 0.0, disabled: true }),
      subsidy: this.fb.control({ value: null, disabled: true }),
      subsidyAmount: this.fb.control(0.0),
      marginAmount: this.fb.control({ value: 0.0, disabled: true }),
    });
    return this.retailFinanceForm
  }

  ViewMobileretailFinanceForm() {
    this.retailFinanceForm = this.fb.group({
      cashLoan: this.fb.control({ value: null, disabled: false }),
      assetValue: this.fb.control({ value: 0.0, disabled: false }),
      financier: this.fb.control({ value: null, disabled: false }),
      financeLoggedInDate: this.fb.control({ value: null, disabled: false }),
      estimatedFinanceAmount: this.fb.control({ value: 0.0, disabled: false }),
      financeStatus: this.fb.control({ value: null, disabled: false }),
      disbursedDate: this.fb.control({ value: null, disabled: false }),
      disbursedFinanceAmount: this.fb.control({ value: 0.0, disabled: false }),
      finalExchangePrice: this.fb.control({ value: 0.0, disabled: true }),
      subsidy: this.fb.control({ value: null, disabled: false }),
      subsidyAmount: this.fb.control({ value: 0.0, disabled: false }),
      marginAmount: this.fb.control({ value: 0.0, disabled: true }),
    });
    return this.retailFinanceForm
  }


}
