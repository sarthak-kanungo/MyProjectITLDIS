import { Component, OnInit, ChangeDetectionStrategy, Input, ChangeDetectorRef } from '@angular/core';
import { SparePaymentReceiptSearchPagePresenter } from '../spare-payment-receipt-search-page/spare-payment-receipt-search-page.presenter';
import { Observable } from 'rxjs';
import { SparePaymentReceiptSearchService } from './spare-payment-receipt-search.service';
import { ParamMap, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-spare-payment-receipt-search',
  templateUrl: './spare-payment-receipt-search.component.html',
  styleUrls: ['./spare-payment-receipt-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparePaymentReceiptSearchService]
})
export class SparePaymentReceiptSearchComponent implements OnInit {
  public searchPaymentReceiptForm = this.sparePaymentReceiptSearchPagePresenter.getSearchPaymentReceiptForm;
  public customerMobileList: Observable<object[]>;
  public receiptNumberList: Observable<object[]>;
  public customerNameList: Observable<object[]>;
  public receiptModeList: object[] = [];
  public todaysDate = new Date();
  today = new Date();
  minDate: Date;
  maxDate: Date
  @Input() set filterState(state: object) {
    if (state) {
      this.patchSearchFilter();
    }
  }
  constructor(
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private sparePaymentReceiptSearchService: SparePaymentReceiptSearchService,
    private sparePaymentReceiptSearchPagePresenter: SparePaymentReceiptSearchPagePresenter
  ) { }

  ngOnInit() {
    this.getReceiptMode();
    this.formFieldChanges();
  }
  ngAfterViewInit() {
   
  }
  fromDateSelected(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    // }
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.searchPaymentReceiptForm.get('toDate').value > this.maxDate)
        this.formFieldChanges()
      this.searchPaymentReceiptForm.get('toDate').patchValue(this.maxDate);
    }
  }
  private formFieldChanges() {
    this.receiptNumberChanges();
    this.customerNameChanges();
    this.customerMobileChanges();
  }
  private receiptNumberChanges() {
    if (this.searchPaymentReceiptForm.get('receiptNumber') != null) {
      this.searchPaymentReceiptForm.get('receiptNumber').valueChanges.subscribe((value: string) => {
        this.searchByReceiptNumber(value);
      })
    }
  }
  private searchByReceiptNumber(changedValue: string) {
    if (changedValue != null) {
      this.receiptNumberList = this.sparePaymentReceiptSearchService.searchByReceiptNumber(changedValue)
    }
  }
  private getReceiptMode() {
    this.sparePaymentReceiptSearchService.getReceiptMode().subscribe(res => {
      this.receiptModeList = res.result;
    })
  }
  private customerNameChanges() {
    this.searchPaymentReceiptForm.get('customerName').valueChanges.subscribe((value: string) => {
      this.searchByCustomerName(value);
    })
  }
  private customerMobileChanges() {
    this.searchPaymentReceiptForm.get('customerContactNumber').valueChanges.subscribe((value: string) => {
      this.searchByCustomerMobileNumber(value);
    })
  }
  private searchByCustomerName(value: string) {
    this.customerNameList = this.sparePaymentReceiptSearchService.searchByCustomerName(value)
  }
  private searchByCustomerMobileNumber(value: string) {
    this.customerMobileList = this.sparePaymentReceiptSearchService.searchByCustomerMobileNumber(value)
  }
  private patchSearchFilter() {
    this.activatedRoute.queryParamMap.subscribe((queyMap: ParamMap) => {
      if (queyMap && Object.keys(queyMap['params']).length > 0) {
        this.searchPaymentReceiptForm.patchValue(JSON.parse(queyMap.get('searchFilter')));
        setTimeout(() => {
          this.changeDetectorRef.markForCheck();
        }, 1000);
      }
    })
  }
}
