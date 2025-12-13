import { Component, OnInit, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { PartQuotationCommonWebService } from '../../service/part-quotation-common-web.service';
import { CustomerType, QuotationNumber, AutoCustomerName } from '../../domain/part-quotation-domain';
import { Observable } from 'rxjs';
import { PartQuotationSearchWebService } from './part-quotation-search-web.service';
import { EventEmitter } from 'events';

@Component({
  selector: 'app-part-quotation-search',
  templateUrl: './part-quotation-search.component.html',
  styleUrls: ['./part-quotation-search.component.scss'],
  providers: [PartQuotationSearchWebService]
})
export class PartQuotationSearchComponent implements OnInit {

  @Input() partQuotationSearchForm: FormGroup
  customersType: Array<CustomerType>
  quotationNumber$: Observable<Array<QuotationNumber>>
  customerName$: Observable<Array<AutoCustomerName>>
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private partQuotationCommonWebService: PartQuotationCommonWebService,
    private partQuotationSearchWebService: PartQuotationSearchWebService
  ) { }

  ngOnInit() {
    this.loadCustomerTypeDropDown()
    this.valueChangesForAutocomplete()
    this.today.setDate(this.today.getDate());
  }
  ngAfterViewInit(){
  
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
      if (this.partQuotationSearchForm.get('quotationToDate').value > this.maxDate)
        this.partQuotationSearchForm.get('quotationToDate').patchValue(this.maxDate);
    }
  }
  loadCustomerTypeDropDown() {
    this.partQuotationCommonWebService.getSpareCustomerTypeDropdown('Quotation').subscribe(response => {
      this.customersType = response
    })
  }

  private valueChangesForAutocomplete() {
    this.partQuotationSearchForm.get('quotationNumber').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const quotationNumber = typeof valueChange == 'object' ? valueChange.quotationNumber : valueChange
        this.autoQuotationNumber(quotationNumber)
      }
    })
    this.partQuotationSearchForm.get('customerName').valueChanges.subscribe(valueChange => {
      if (valueChange) {
        const customerName = typeof valueChange == 'object' ? valueChange.customerName : valueChange
        this.autoCustomerName(customerName)
      }
    })
  }
  autoQuotationNumber(quotationNumber: string) {
    this.quotationNumber$ = this.partQuotationSearchWebService.getQuotationNumberAutocomplete(quotationNumber)
  }
  displayFnQuotationNumber(value: QuotationNumber) {
    return value ? value.quotationNumber : undefined
  }

  autoCustomerName(customerName: string) {
    this.customerName$ = this.partQuotationSearchWebService.getCustomerNameAutocomplete(customerName)
  }


}
