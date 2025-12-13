import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators,  } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../../../customer-master/component/customer-master-create-page/customerMasterCreatePage.presenter';
import { AddressDetailsService } from '../../../customer-master/component/address-details/address-details.service';
import { AddressTypeDropdown, AutoCompPinCode, DealerInformation } from '../../../customer-master/domain/customer-master-domain';
import { MatSelectChange, MatAutocompleteSelectedEvent } from "@angular/material";
import { Observable } from 'rxjs'
import { CustomerMasterChangeRequestPresenter } from '../customerMasterChangeRequest.presenter'

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.scss'],
  providers: [AddressDetailsService]
})
export class AddressDetailsComponent implements OnInit {
  preferedaddress: Array<AddressTypeDropdown> = [
    'Residential Address', 'Office Address',
  ];
  customerMasterAddressDetailsForm: FormGroup;
  customerMasterAddressDetailsFormReq  : FormGroup;
  pinCode$: Observable<Array<AutoCompPinCode>>;
  localitys: string[] = []
  changeName: string;
  hasTable: boolean;
  districtId:number;
  isView:boolean
  @Input() dealerInformation: Array<DealerInformation> = []
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private customerMasterChangeRequestPresenter: CustomerMasterChangeRequestPresenter,
    private addressDetailsService: AddressDetailsService
  ) { }

  ngOnInit() {
    this.customerMasterAddressDetailsForm = this.customerMasterCreatePagePresenter.createCustomerAddressDetails
    this.customerMasterAddressDetailsFormReq = this.customerMasterChangeRequestPresenter.createCustomerAddressDetails
  }
}
