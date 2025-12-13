import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { MobileNo } from '../../domain/delear-customer-care-ex-call-domain';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';

@Component({
  selector: 'app-enquiry',
  templateUrl: './enquiry.component.html',
  styleUrls: ['./enquiry.component.css']
})
export class EnquiryComponent implements OnInit {
  @Input()
  enquiryDetailsForm: FormGroup
  villageList: any;
  @Input()
  districtId:number;
  @Input()
  isView:boolean
  constructor(private delearCustomerCareExCallPagePresenter: DelearCustomerCareExCallPagePresenter,
    private delearCustomerCareExCallService: DelearCustomerCareExCallService,
    private activityRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.enquiryDetailsForm.get('pincode').valueChanges.subscribe(searchValue => {
      this.enquiryDetailsForm.get('pincode').setErrors({'selectFromList':'Select From List'});
      if(typeof searchValue === 'object'){
        this.enquiryDetailsForm.get('pincode').setErrors(null);
        searchValue = searchValue.pinCode;
      }
      this.delearCustomerCareExCallService.getVillageListAutoSearch(searchValue, this.districtId).subscribe(response => {
        this.villageList = response['result'];
      });
      
    })
  }

  displayFnVillage(pincodeDtl){
    return pincodeDtl ? pincodeDtl.pinCode : undefined;
  }

  selectedPinCode(pincodeDtl:any){
    console.log(pincodeDtl)
    this.delearCustomerCareExCallService.getPinDtl(pincodeDtl.option.value.cityId, pincodeDtl.option.value.pinID).subscribe(response => {
      this.enquiryDetailsForm.patchValue(response['result']);
    });
  }
}
