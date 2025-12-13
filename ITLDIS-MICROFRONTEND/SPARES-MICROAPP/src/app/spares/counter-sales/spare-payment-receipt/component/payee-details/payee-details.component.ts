import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { BehaviorSubject, Observable } from 'rxjs';
import { SearchAutocomplete } from 'CommonSupportDto';
import { Component, OnInit, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { SparePaymentReceiptPagePresenter } from '../spare-payment-receipt-page/spare-payment-receipt-page.presenter';

@Component({
  selector: 'app-payee-details',
  templateUrl: './payee-details.component.html',
  styleUrls: ['./payee-details.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PayeeDetailsComponent implements OnInit {
  public payeeDetailsForm: FormGroup = this.paymentReceiptPagePresenter.payeeDetailForm;
  @Input() public markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public isView: boolean;
  public payeeCodeList: Observable<(string | object)[]>;
  public chagedLabel: string;

  constructor(
    public dialog: MatDialog,
    private _changeDetectorRef: ChangeDetectorRef,
    private paymentReceiptPagePresenter: SparePaymentReceiptPagePresenter
  ) { }

  ngOnInit() {
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    })
    this.customerTypeValueChanges();
  }
  public displayAutocompleteValueFn(data: SearchAutocomplete) {
    return data ? data['value'] : undefined
  }
  private customerTypeValueChanges() {
    this.paymentReceiptPagePresenter.receiptForm.get('payeeType').valueChanges.subscribe((value: any) => {
      if (value) {
        this.changeLabelForCustomerCode(value);
      }
    })
  }
  private changeLabelForCustomerCode(label: string) {
    this.chagedLabel = label && label.toLocaleLowerCase() !== 'new/existing' ? label : 'Customer';
    this._changeDetectorRef.markForCheck();
  }
}
