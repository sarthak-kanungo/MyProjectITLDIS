import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { CustomerOrderNumber, CustomerName, CustomerType } from '../../domain/so.domain';
import { SoSearchWebService } from './so-search-web.service';
import { CustomerOrderWebService } from '../customer-order/customer-order-web.service';

@Component({
  selector: 'app-so-search',
  templateUrl: './so-search.component.html',
  styleUrls: ['./so-search.component.scss'],
  providers: [SoSearchWebService, CustomerOrderWebService]
})
export class SoSearchComponent implements OnInit {
  @Input() soSearchForm: FormGroup
  searchForm: boolean = false
  customerOrderNoList$: Observable<Array<CustomerOrderNumber>>
  customerNameList$: Observable<Array<CustomerName>>
  customersList: Array<CustomerType>
  public statusList = ['Draft','Open','Closed'];
  today = new Date();
  minDate: Date;
  maxDate: Date
  constructor(
    private soSearchWebService: SoSearchWebService,
    private customerOrderWebService: CustomerOrderWebService
  ) { }

  ngOnInit() {
    this.valueChangesForAutoComplete()
    this.getCustomerTypeDropDown()
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
      if (this.soSearchForm.get('orderToDate').value > this.maxDate)
        this.soSearchForm.get('orderToDate').patchValue(this.maxDate);
    }
  }
  valueChangesForAutoComplete() {
    this.soSearchForm.get('customerOrderNo').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        let saleOrderNumber = typeof value == 'object' ? value.saleOrderNumber : value
        console.log("saleOrderNumber ", saleOrderNumber);
        this.autoCustomerOrderNo(saleOrderNumber)
      }
    })
    this.soSearchForm.get('customerName').valueChanges.subscribe(value => {
      console.log("value ", value);
      if (value) {
        let customerName = typeof value == 'object' ? value.customerName : value
        console.log("customerName ", customerName);
        this.autoCustomerName(customerName)
      }
    })
  }
  autoCustomerOrderNo(saleOrderNumber: string) {
    this.customerOrderNoList$ = this.soSearchWebService.getCustomerOrderNo(saleOrderNumber)
  }
  displayFnCustomerOrderNo(customerOrderNo: CustomerOrderNumber) {
    return customerOrderNo ? customerOrderNo.saleOrderNumber : undefined
  }


  autoCustomerName(customerName: string) {
    this.customerNameList$ = this.soSearchWebService.getCustomerName(customerName)
  }
  displayFnCustomerName(customerName: CustomerName) {
    return customerName ? customerName.customerName : undefined
  }
  getCustomerTypeDropDown() {
    let documentType = 'SaleOrder'
    this.customerOrderWebService.getCustomerType(documentType).subscribe(res => {
      this.customersList = res
    })
  }
}
