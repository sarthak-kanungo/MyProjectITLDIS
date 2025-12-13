import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TollFreeService } from '../../service/toll-free.service';
import { MobileNo } from '../../domain/toll-free-domain';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';

@Component({
  selector: 'app-existing-customer-detail',
  templateUrl: './existing-customer-detail.component.html',
  styleUrls: ['./existing-customer-detail.component.scss']
})
export class ExistingCustomerDetailComponent implements OnInit {
  @Input()
  customerDetailsForm: FormGroup
  customerCodeList: any;
  mobileNo$: Observable<Array<MobileNo>>;
  @Input()
  isView:boolean
  
  constructor(private tollFreePagePresenter: TollFreePagePresenter,
    private service: TollFreeService,
    private activityRoute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.customerDetailsForm.get('mobileNumber').valueChanges.subscribe(searchValue => {
      this.customerDetailsForm.get('mobileNumber').setErrors({'selectFromList':'Select From List'});
      if(typeof searchValue === 'object'){
        this.customerDetailsForm.get('mobileNumber').setErrors(null);
        searchValue = searchValue.mobileNumber;
      }
      if(searchValue.length > 2){
        this.service.getAutoCompleteMobileNumber(searchValue,'').subscribe(response => {
          this.customerCodeList = response['result'];
        })
      }else{
        this.customerCodeList = null;
      }
    });
    this.service.fetchDealerDtlSubject.subscribe(response => {
      if(response!=null){
        this.customerDetailsForm.get('soldDealerName').patchValue(response['soldToDealer']);
        this.customerDetailsForm.get('contactPersonMobile').patchValue(response['dealerContactNo']);
        this.customerDetailsForm.get('contactPersonName').patchValue(response['dealerContactName']);
      }
    })
  }
    
  onKeyPressAllowNumbersOnly(event: KeyboardEvent) {
    this.tollFreePagePresenter.allowNumbersOnly(event);
  }

  selectedCustomerCode(event){
    const customerCode = event.option.value.customerCode?event.option.value.customerCode:(event.option.value.oldCustomerCode?event.option.value.oldCustomerCode:event.option.value.prospectCode)
    this.service.getCustomerDtl(customerCode).subscribe(response => {
      delete response['result']['mobileNumber'];
      this.customerDetailsForm.patchValue(response['result']);
      this.service.fetchCustomerMachineDtlSubject.next({customerId:response['result']['customerMasterId'],vinId:null});
      
    })
  }

  displayFnCustomerCode(mobileNumber:any){
    return (mobileNumber && typeof mobileNumber==='object')?mobileNumber.mobileNumber:undefined;
  }
}
