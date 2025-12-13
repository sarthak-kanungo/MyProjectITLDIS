import { BehaviorSubject } from 'rxjs';
import { FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { SparePaymentReceiptPagePresenter } from '../spare-payment-receipt-page/spare-payment-receipt-page.presenter';

import { BaseDto } from 'BaseDto';
import { PaymentDetailService } from './payment-detail.service';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { CheckDdBank, Cards, Bank } from 'SparePaymentReceiptModule';

@Component({
  selector: 'app-payment-detail',
  templateUrl: './payment-detail.component.html',
  styleUrls: ['./payment-detail.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [PaymentDetailService]
})
export class PaymentDetailComponent implements OnInit {
  public paymentDetailForm: FormGroup = this.paymentReceiptPagePresenter.paymentDetailForm;
  @Input() public markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public isView: boolean;

  public showRtgs: boolean = false;
  public showCheque: boolean = false;
  public showCredit: boolean = false;
  public showWallet: boolean = false;

  public checkDdBankTypeList: Array<CheckDdBank> = [];
  private receiptModePanelFormControls: string[] = [];
  public cardTypeList: Array<Cards> = [];
  public bankTypeList: Array<Bank> = [];
  public todaysDate = new Date();

  constructor(
    private _changeDetectorRef: ChangeDetectorRef,
    private paymentDetailService: PaymentDetailService,
    private paymentReceiptPagePresenter: SparePaymentReceiptPagePresenter
  ) { }

  ngOnInit() {
    this.checkReceiptMode();
    this.getCheckDdBankType();
    this.getCardType();
    this.getBankType();
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    });
    this.setReceiptModeControls();
  }
  private checkReceiptMode() {
    const receiptMode = this.paymentReceiptPagePresenter.receiptForm.get('receiptMode').value;
    this.paymentModeSelected(receiptMode);
    this.paymentReceiptPagePresenter.receiptForm.get('receiptMode').valueChanges.subscribe((value: string) => {
      if (value) {
        this.paymentModeSelected(value)
      }
    })
  }
  private setReceiptModeControls() {
    this.receiptModePanelFormControls = ['chequeDdNo', 'chequeDdDate',
      'chequeDdBank', 'transactionNo', 'transactionDate', 'bank',
      'cardNo', 'cardType', 'cardName', 'serviceProvides'
    ]
  }

  private getCheckDdBankType() {
    this.paymentDetailService.getCheckDdBankType().subscribe((response: BaseDto<Array<CheckDdBank>>) => {
      this.checkDdBankTypeList = response.result;
    })
  }

  private getCardType() {
    this.paymentDetailService.getCardType().subscribe((response: BaseDto<Array<Cards>>) => {
      this.cardTypeList = response.result;
    })
  }
  private getBankType() {
    this.paymentDetailService.getBankType().subscribe((response: BaseDto<Array<Bank>>) => {
      this.bankTypeList = response.result;
    })
  }

  public selectionReceiptType(receiptObject: object) {
    if (receiptObject) {
      this.paymentDetailForm.get('receiptAmount').patchValue(receiptObject['amount'])
    }
  }
  public paymentModeSelected(value: string) {
    this._changeDetectorRef.markForCheck();
    if (value) {
      this.hidePanelsAndRemoveValidations();
      if (value.toLocaleLowerCase() === 'cash') {
        this.setPanelValidations([]);
        return
      }
      if (value.toLocaleLowerCase() === 'cheque/dd') {
        this.showCheque = true;
        this.setPanelValidations(['chequeDdNo', 'chequeDdDate', 'chequeDdBank']);
        this.paymentDetailForm.get('chequeDdNo').setValidators([Validators.required, CustomValidators.maxLength(6), CustomValidators.numberOnly])
        return
      }
      if (value.toLocaleLowerCase() === 'credit/debit card') {
        this.showCredit = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'cardNo',
          'cardType', 'cardName']);
        this.paymentDetailForm.get('cardNo').setValidators([Validators.required, CustomValidators.maxLength(16), CustomValidators.numberOnly])
        return
      }
      if (value.toLocaleLowerCase() === 'e-wallet') {
        this.showWallet = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'serviceProvides']);
        return
      }
      if (value.toLocaleLowerCase() === 'rtgs/neft/imps') {
        this.showRtgs = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'bank']);
        return
      }
    }
  }
  private hidePanelsAndRemoveValidations() {
    this.setPanelValidations([]);
    this.showCheque = false;
    this.showCredit = false;
    this.showWallet = false;
    this.showRtgs = false;
  }
  private setPanelValidations(controlsToSet: Array<string>) {
    this.receiptModePanelFormControls.forEach(control => {
      if (controlsToSet.includes(control)) {
        this.paymentDetailForm.get(control).setValidators(Validators.required);
        this.paymentDetailForm.get(control).updateValueAndValidity();
      } else {
        this.paymentDetailForm.get(control).setValidators(Validators.nullValidator)
        this.paymentDetailForm.get(control).updateValueAndValidity();
      }
    })
  }
}
