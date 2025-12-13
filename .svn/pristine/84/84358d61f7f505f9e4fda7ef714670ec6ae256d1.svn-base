import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerMasterSearchPagePresenter } from '../customer-master-search-page/customerMasterSearchPage.presenter';
import { SearchCustomerService } from './search-customer.service';

@Component({
  selector: 'app-search-customer',
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.scss'],
  providers: [SearchCustomerService]
})
export class SearchCustomerComponent implements OnInit {
  customerMasterSearchForm: FormGroup;
  customerCodeList = [];
  mobileNoList = [];
  constructor(
    private customerMasterSearchPagePresenter: CustomerMasterSearchPagePresenter,
    private searchCustomerService: SearchCustomerService
  ) { }

  ngOnInit() {
    this.customerMasterSearchForm = this.customerMasterSearchPagePresenter.customerSearchDetails;

    this.customerMasterSearchForm.get('customerCode').valueChanges.subscribe(value => {
        if(value && typeof value === 'object'){
            
        }else if(value=='' || value === undefined){
            this.customerMasterSearchForm.get('customerCode').setErrors(null);
        }else{
            this.customerMasterSearchForm.get('customerCode').setErrors({
                selectFromList: "Please select from list",
            });
        }
        if(value && typeof value != 'object'){
            this.searchCustomerService.customerCodeAuto(value).subscribe(response => {
                this.customerCodeList = response['result'];
            });
        }
    })
    
    this.customerMasterSearchForm.get('mobileNo').valueChanges.subscribe(value => {
        if(value && typeof value === 'object'){
            
        }else if(value=='' || value === undefined){
            this.customerMasterSearchForm.get('mobileNo').setErrors(null);
        }else{
            this.customerMasterSearchForm.get('mobileNo').setErrors({
                selectFromList: "Please select from list",
            });
        }
        if(value && typeof value != 'object'){
            this.searchCustomerService.customerMobileNo(value).subscribe(response => {
                this.mobileNoList = response['result'];
            });
        }
    })
    
  }

  customerCodeOptionSelect(event){
      this.customerMasterSearchForm.get('customerCode').setErrors(null);
  }
  displayFnForCustCode(code: any) {
      return code ? code['customerCode'] : undefined;
  }
  displayFnForMobile(code: any) {
      return code ? code['mobileNumber'] : undefined;
  }
}
