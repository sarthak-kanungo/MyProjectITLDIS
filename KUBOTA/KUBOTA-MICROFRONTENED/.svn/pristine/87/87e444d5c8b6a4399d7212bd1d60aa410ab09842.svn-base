import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { AutoCustomerOrder } from '../domain/pl.domain';
import { PlSearchService } from './pl-search.service';

@Component({
  selector: 'app-picklist-search',
  templateUrl: './picklist-search.component.html',
  styleUrls: ['./picklist-search.component.css'],
  providers: [PlSearchService,]
})
export class PicklistSearchComponent implements OnInit, OnChanges {

  @Input() plSearchForm: FormGroup
  searchForm: boolean = false
  public todaysDate: Date = new Date();
  public selectedFromDate: Date;
  customerOrderNoList$: Observable<Array<AutoCustomerOrder>>
  public statusList = ['Open','Closed'];
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor( private plSearchService: PlSearchService,) { }
  
  ngOnChanges() {
    //this.valueChangesForAutoComplete()
  }

  ngOnInit() {
    this.valueChangesForAutoComplete()
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
      if (this.plSearchForm.get('orderToDate').value > this.maxDate)
        this.plSearchForm.get('orderToDate').patchValue(this.maxDate);
    }
  }
  public fromDateChange(event: object) {
    if (event && event['value']) {
      this.selectedFromDate = new Date(event['value']);
    }
  }

  valueChangesForAutoComplete() {
    console.log('valueChangesForAutoComplete')
    this.plSearchForm.get('picklistNumber').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        let saleOrderNumber = typeof value == 'object' ? value.saleOrderNumber : value
        console.log("saleOrderNumber ", saleOrderNumber);
        this.autoCustomerOrderNo(saleOrderNumber)
      }
    })
  }

  autoCustomerOrderNo(saleOrderNumber: string) {
    this.customerOrderNoList$ = this.plSearchService.getCustomerOrderNo(saleOrderNumber)
  }

  displayFnCustomerOrderNo(picklistNumber: AutoCustomerOrder) {
    return picklistNumber ? picklistNumber.picklistNumber : undefined
  }

}
