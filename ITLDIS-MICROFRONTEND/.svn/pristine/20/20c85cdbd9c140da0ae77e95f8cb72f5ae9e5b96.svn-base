import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators,  } from '@angular/forms';
import { CustomerMasterCreatePagePresenter } from '../customer-master-create-page/customerMasterCreatePage.presenter';
import { AddressDetailsService } from './address-details.service';
import { AddressTypeDropdown, AutoCompPinCode, DealerInformation } from '../../domain/customer-master-domain';
import { MatSelectChange, MatAutocompleteSelectedEvent } from "@angular/material";
import { Observable } from 'rxjs'  
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
  pinCode$: Observable<Array<AutoCompPinCode>>;
  localitys: string[] = []
  changeName: string;
  hasTable: boolean;
  districtId:number;
  @Input() dealerInformation: Array<DealerInformation> = []
  constructor(
    private customerMasterCreatePagePresenter: CustomerMasterCreatePagePresenter,
    private addressDetailsService: AddressDetailsService
  ) { }

  ngOnInit() {
    this.customerMasterAddressDetailsForm = this.customerMasterCreatePagePresenter.createCustomerAddressDetails
    
    this.customerMasterAddressDetailsForm.get("pinCode").valueChanges.subscribe((value) => {
        if (value && typeof value !== "object") {
          let pinCode = value;
          this.autoPincode(pinCode);
        }
        if (value !== null && typeof value !== "object") {
          this.customerMasterAddressDetailsForm.get("pinCode").setErrors({
            selectFromList: "Please select from list",
          });
        }
     });
    
  }

  selectionDistrict(event: MatSelectChange) {
      this.districtId = event.value.districtId;
      this.customerMasterAddressDetailsForm.get("pinCode").reset();
      this.customerMasterAddressDetailsForm.get("pinId").reset();
      this.customerMasterAddressDetailsForm.get("state").reset();
      this.customerMasterAddressDetailsForm.get("tehsil").reset();
      this.customerMasterAddressDetailsForm.get("city").reset();
      this.customerMasterAddressDetailsForm.get("country").reset();
  }

    autoPincode(autoPincode) {
      if (autoPincode && this.districtId) {
          this.pinCode$ = this.addressDetailsService.autoCompleteTehsilCityPincode(
            this.districtId,
            autoPincode
          );
          this.customerMasterAddressDetailsForm.get("pinId").reset();
          this.customerMasterAddressDetailsForm.get("state").reset();
          this.customerMasterAddressDetailsForm.get("tehsil").reset();
          this.customerMasterAddressDetailsForm.get("city").reset();
          this.customerMasterAddressDetailsForm.get("country").reset();
        }
    }

    displayFnForPinCode(code: AutoCompPinCode) {
      return code ? code.pinCode : undefined;
    }

    optionSelectedPinCode(event) {
      if (event instanceof MatAutocompleteSelectedEvent) {
        this.customerMasterAddressDetailsForm.get("pinCode").setErrors(null);
      }
      this.addressDetailsService
        .getPincodeDetail(
          event.option.value.pinID,
          event.option.value.cityId
        ).subscribe((response) => {
          //this.customerMasterAddressDetailsForm.patchValue(response);
          this.customerMasterAddressDetailsForm.get("pinId").patchValue(response.pinID);
          this.customerMasterAddressDetailsForm.get("state").patchValue(response.state);
          this.customerMasterAddressDetailsForm.get("tehsil").patchValue(response.tehsil);
          this.customerMasterAddressDetailsForm.get("city").patchValue(response.city);
          this.customerMasterAddressDetailsForm.get("country").patchValue(response.country);
        });
    }


  selection(value) {
    console.log("value ", value);
    this.hasTable = true
    if (value === 'Residential Address') {
      this.changeName = 'Residential Address'
    } else

      if (value === 'Office Address') {
        this.changeName = 'Office Address'

      }
  }

}
