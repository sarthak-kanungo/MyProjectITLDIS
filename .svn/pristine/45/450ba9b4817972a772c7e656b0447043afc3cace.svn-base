import { FormGroup } from '@angular/forms';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { MatDialog, MatSelectChange, MatAutocompleteSelectedEvent } from '@angular/material';

import { SparePaymentReceiptPagePresenter } from '../spare-payment-receipt-page/spare-payment-receipt-page.presenter';
import { Component, OnInit, Input, ChangeDetectorRef, ChangeDetectionStrategy } from '@angular/core';
import { SparePaymentReceiptService } from './spare-payment-receipt.service';
import { SearchAutocomplete } from 'CommonSupportDto';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-spare-payment-receipt',
  templateUrl: './spare-payment-receipt.component.html',
  styleUrls: ['./spare-payment-receipt.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparePaymentReceiptService]
})
export class SparePaymentReceiptComponent implements OnInit {
  public paymentReceiptForm: FormGroup = this.paymentReceiptPagePresenter.receiptForm;
  public customerDealerMechanicCodeList: Observable<(string | object)[]>;
  @Input() public markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public isView: boolean;

  public receiptTypeList: object[] = [];
  public receiptModeList: object[] = [];
  public paymentTypeList: object[] = [];
  public customerTypeList: object[] = [];
  public changedLabel: string;

  constructor(
    public dialog: MatDialog,
    private _changeDetectorRef: ChangeDetectorRef,
    private sparePaymentReceiptService: SparePaymentReceiptService,
    private paymentReceiptPagePresenter: SparePaymentReceiptPagePresenter
  ) { }

  ngOnInit() {
    this.getCustomerTypeList();
    this.getReceiptTypeList();
    this.getReceiptModeList();

    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    })
    this.formFieldValueChanges();
  }
  public displayAutocompleteValueFn(data: SearchAutocomplete) {
    return data ? data['value'] : undefined
  }
  private getCustomerTypeList() {
    this.sparePaymentReceiptService.getCustomerTypeList().subscribe(res => {
      if (res) {
        this.customerTypeList = res.result;
      }
    })
  }
  private getReceiptTypeList() {
    this.sparePaymentReceiptService.getReceiptTypeList().subscribe(res => {
      if (res) {
        this.receiptTypeList = res.result;
      }
    })
  }
  private getReceiptModeList() {
    this.sparePaymentReceiptService.getReceiptModeList().subscribe(res => {
      if (res) {
        this.receiptModeList = res.result;
      }
    })
  }
  private formFieldValueChanges() {
    this.customerCodeValuechanges();
  }
  private customerCodeValuechanges() {
    this.paymentReceiptForm.get('customerDealerMechanicCode').valueChanges.subscribe((value: string) => {
      this.getCustomerAndMechanicCodeBasedOnCustomerType(value);
    })
  }
  public customerCodeSelected(event: MatAutocompleteSelectedEvent) {
    if (event && event instanceof MatAutocompleteSelectedEvent) {
      let payeeType = this.paymentReceiptForm.get('payeeType').value as string;
      this.getDataFromCustomerCodeBasedOnCustomerType(payeeType, event.option.value);
    }
  }
  private getDataFromCustomerCodeBasedOnCustomerType(payeeType: string, selectedObject: object) {
    switch (payeeType && payeeType.toLocaleLowerCase()) {
      case 'new/existing':
        this.getDetailsFromCustomerCode(selectedObject['prospectCode']);
        break;
      case 'co-dealer':
        this.getDetailsFromDealerCode(selectedObject['id']);
        break;
      default:
        this.getDetailsFromMechanicOrRetailerCode(selectedObject['id']);
        break;
    }
  }
  private getCustomerAndMechanicCodeBasedOnCustomerType(searchValue: string) {
    let payeeType = this.paymentReceiptForm.get('payeeType').value as string;
    switch (payeeType && payeeType.toLocaleLowerCase()) {
      case 'new/existing':
        this.searchByCustomerCode(searchValue);
        break;
      case 'co-dealer':
        this.searchByDealerCode(searchValue);
        break;
      default:
        this.searchByMechanicOrRetailerCode(searchValue, payeeType);
        break;
    }
  }
  public getDetailsFromCustomerCode(code: string) {
    this.sparePaymentReceiptService.getDetailsFromCustomerCode(code).subscribe((res: BaseDto<object[]>) => {
      let patchObject = res.result;
      this.patchValueToPayeeDetailForm(patchObject);
    })
  }
  public getDetailsFromDealerCode(code: string) {
    this.sparePaymentReceiptService.getDetailsFromDealerCode(code).subscribe((res: BaseDto<object[]>) => {
      let patchObject = res.result;
      patchObject['customerName'] = patchObject['dealerName'];
      this.patchValueToPayeeDetailForm(patchObject);
    })
  }
  public getDetailsFromMechanicOrRetailerCode(code: string) {
    this.sparePaymentReceiptService.getDetailsFromMechanicOrRetailerCode(code).subscribe((res: BaseDto<object[]>) => {
      let patchObject = res.result;
      patchObject['customerName'] = patchObject['retailerName'];
      this.patchValueToPayeeDetailForm(patchObject);
    })
  }
  private patchValueToPayeeDetailForm(patchObject:object) {
    this.paymentReceiptPagePresenter.payeeDetailForm.patchValue(patchObject);
  }
  public customerTypeChanges(event: MatSelectChange) {
    if (event && event instanceof MatSelectChange) {
      this.paymentReceiptForm.get('customerDealerMechanicCode').reset();
      this.customerDealerMechanicCodeList = of([]);
      this.changeLabelForCustomerCode(event.value);
    }
  }

  private changeLabelForCustomerCode(label: string) {
    this.changedLabel = label && label.toLocaleLowerCase() !== 'new/existing' ? label : 'Customer';
  }
  private searchByCustomerCode(searchValue: string) {
    this.customerDealerMechanicCodeList = this.sparePaymentReceiptService.searchByCustomerCode(searchValue)
  }
  private searchByMechanicOrRetailerCode(searchValue: string, customerType: string) {
    this.customerDealerMechanicCodeList = this.sparePaymentReceiptService.searchByMechanicOrRetailerCode(searchValue, customerType)
  }
  private searchByDealerCode(searchValue: string) {
    this.customerDealerMechanicCodeList = this.sparePaymentReceiptService.searchByDealerCode(searchValue)
  }
}
