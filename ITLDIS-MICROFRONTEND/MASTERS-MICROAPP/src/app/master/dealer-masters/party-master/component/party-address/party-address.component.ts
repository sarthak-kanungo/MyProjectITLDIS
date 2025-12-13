import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PartyMasterPagePresenter } from '../party-master-page/partyMasterPage.presenter';
import { PartyAddressService } from './party-address.service';
import { AutoCompPinCode, CityDetails, DealerInformation, DistrictDetails, LocalityDetails, LocalityList, PinCodeDetails, PostOffice, PostOfficeDetails, StateDetails, TehsilDetails } from '../../domain/party-master-domain';
import { BaseDto } from 'BaseDto';
import { PartyMasterPageService } from '../party-master-page/party-master-page.service';
import { Observable } from 'rxjs';
import { MatAutocompleteSelectedEvent, MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-party-address',
  templateUrl: './party-address.component.html',
  styleUrls: ['./party-address.component.scss'],
  providers: [PartyAddressService, PartyMasterPageService]
})
export class PartyAddressComponent implements OnInit {
  disable = true;
  // localities: Array<LocalityList> = [
  //   'Wakad', 'Kothrud', 'Swargate',
  // ];
  pinCode$: Observable<Array<AutoCompPinCode>>;
  postOffice$: Observable<Array<PostOffice>>;
  dealerInformation: Array<DealerInformation>;

  countryId: number;
  stateId: number;
  districtId: number;
  tehsilId: number;
  cityId: number;
  pinCodeId: number;
  postOfficeId: number;
  // localityId: number;
  stateList$: Observable<Array<StateDetails>>
  districtList$: Observable<Array<DistrictDetails>>
  tehsilList$: Observable<Array<TehsilDetails>>
  cityList$: Observable<Array<CityDetails>>
  pinCodeList$: Observable<Array<PinCodeDetails>>
  postOfficeList$: Observable<Array<PostOfficeDetails>>
  // localityList$: Observable<Array<LocalityDetails>>

  partyMasterAddressForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private partyMasterPagePresenter: PartyMasterPagePresenter,
    private partyAddressService: PartyAddressService
  ) { }

  ngOnInit() {
    this.partyMasterAddressForm = this.partyMasterPagePresenter.partyCreateAddressDetailsForm
    // this.pinValueChange()
    // this.valueChangesForAutoComplate();
    // this.getDealerInfo();
    this.countryToPostOfficeList()

  }

  countryToPostOfficeList() {
    this.getCountry()
    this.getStateList()
    this.getDistrictList()
    this.getTehsilList()
    this.getVillageList()
    this.getPinCodeList()
    this.getPostOfficeList()
    // this.getLocalityList()
  }
  ngOnChange() {

  }
  getStateList() {
    this.partyMasterAddressForm.get('state').valueChanges.subscribe(value => {
      if (value) {
        const state = typeof value == 'object' ? value.state : value;
        this.autoState(state)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.stateValidation();
      }
    })
  }

  getDistrictList() {
    this.partyMasterAddressForm.get('district').valueChanges.subscribe(value => {
      if (value) {
        const district = typeof value == 'object' ? value.district : value;
        this.autoDistrict(this.stateId, district)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.districtValidation();
      }
    })
  }
  getTehsilList() {
    this.partyMasterAddressForm.get('tehsil').valueChanges.subscribe(value => {
      if (value) {
        const tehsil = typeof value == 'object' ? value.tehsil : value
        this.autoTehsil(this.districtId, tehsil)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.tehsilValidation();
      }
    })
  }
  getVillageList() {
    this.partyMasterAddressForm.get('city').valueChanges.subscribe(value => {
      if (value) {
        const city = typeof value == 'object' ? value.city : value
        this.autoCity(this.tehsilId, city)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.villageValidation();
      }
    })
  }
  getPinCodeList() {
    this.partyMasterAddressForm.get('pinCode').valueChanges.subscribe(value => {
      if (value) {
        const pinCode = typeof value == 'object' ? value.pinCode : value
        this.autoPin(this.districtId, pinCode)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.pinCodeValidation();
      }
    })
  }
  getPostOfficeList() {
    this.partyMasterAddressForm.get('postOffice').valueChanges.subscribe(value => {
      if (value) {
        const postOffice = typeof value == 'object' ? value.postOffice : value
        this.autoPostOffice(this.pinCodeId, postOffice)
      }
      if (value !== null) {
        this.partyMasterPagePresenter.postOfficeValidation();
      }
    })
  }
  postOfficeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('postOffice').setErrors(null);
    }
  }
  // getLocalityList() {
  //   this.partyMasterAddressForm.get('locality').valueChanges.subscribe(value => {
  //     if (value) {
  //       const locality = typeof value == 'object' ? value.locality : value
  //       this.autoLocality()
  //     }
  //     if (value !== null) {
  //       this.partyMasterPagePresenter.localityValidation();
  //     }
  //   })
  // }
  // localitySelect(event: MatAutocompleteSelectedEvent) {
  //   if (event instanceof MatAutocompleteSelectedEvent) {
  //     this.partyMasterAddressForm.get('locality').setErrors(null);
  //   }
  // }

  getCountry() {
    this.partyAddressService.getCountry().subscribe(res => {
      this.countryId = res.id;
      this.partyMasterAddressForm.get('country').patchValue(res.country);
      this.partyMasterAddressForm.get('country').disable();
    });
  }

  autoState(stateName: string) {
    if (this.countryId) {
      this.stateList$ = this.partyAddressService.getState(this.countryId, stateName)
    }
  }

  selectedState(event: MatAutocompleteSelectedEvent) {
    this.stateId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('state').setErrors(null);
    }
  }

  displayFnState(state: StateDetails) {
    return state ? state.state : undefined;
  }

  autoDistrict(id: number, name: string) {
    if (this.stateId) {
      this.districtList$ = this.partyAddressService.getDistrict(id, name)
    }
  }
  selectedDistrict(event: MatAutocompleteSelectedEvent) {
    this.districtId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('district').setErrors(null);
    }
  }
  displayFnDistrict(district: DistrictDetails) {
    return district ? district.district : undefined;
  }
  autoTehsil(id: number, name: string) {
    if (this.districtId) {
      this.tehsilList$ = this.partyAddressService.getTehsil(id, name);
    }
  }
  selectedTehsil(event: MatAutocompleteSelectedEvent) {
    this.tehsilId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('tehsil').setErrors(null);
    }
  }
  displayFnTehsil(tehsil: TehsilDetails) {
    return tehsil ? tehsil.tehsil : undefined;
  }
  autoCity(id: number, name: string) {
    if (this.tehsilId) {
      this.cityList$ = this.partyAddressService.getCity(id, name)
    }
  }
  selectedCity(event: MatAutocompleteSelectedEvent) {
    this.cityId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('city').setErrors(null);
    }
  }
  displayFnCity(city: CityDetails) {
    return city ? city.city : undefined;
  }
  autoPin(id: number, name: string) {
    if (this.cityId) {
      this.pinCodeList$ = this.partyAddressService.getPinCode(id, name)
    }
  }
  selectedPin(event: MatAutocompleteSelectedEvent) {
    this.pinCodeId = event.option.value.pinID;
    this.partyMasterAddressForm.controls.pinId.patchValue(this.pinCodeId)
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('pinCode').setErrors(null);
    }
  }
  displayFnPin(pinCode: PinCodeDetails) {
    return pinCode ? pinCode.pinCode : undefined;
  }
  autoPostOffice(id: number, name: string) {
    if (this.pinCodeId) {
      this.postOfficeList$ = this.partyAddressService.getPostOffice(id, name)
    }
  }

  selectedPostOffice(event: MatAutocompleteSelectedEvent) {
    this.postOfficeId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.partyMasterAddressForm.get('postOffice').setErrors(null);
    }
  }
  displayFnPostOffice(postOffice: PostOfficeDetails) {
    return postOffice ? postOffice.postOffice : undefined;
  }


  // autoLocality() {
  //   if (this.postOfficeId) {
  //     this.localityList$ = this.partyAddressService.getLocality(this.partyMasterAddressForm.get('postOffice').value.postOffice)
  //   }
  // }

  // selectedLocality(event: MatAutocompleteSelectedEvent) {
  //   this.localityId = event.option.value.id;
  //   if (event instanceof MatAutocompleteSelectedEvent) {
  //     this.partyMasterAddressForm.get('locality').setErrors(null);
  //   }
  // }
  // displayFnLocality(locality: LocalityDetails) {
  //   return locality ? locality.locality : undefined;
  // }



  onKeyPressMobileNo(event: any) {
    //console.log('event', event);
    this.partyAddressService.keyPress(event)
  }

  keyStd(event: any) {
    this.partyAddressService.keyStd(event)
  }
  keytel(event: any) {
    this.partyAddressService.keytel(event)
  }

  keyPressFirstName(event: any) {
    this.partyAddressService.keyPressFirstName(event)
  }

  keyPressMiddleName(event: any) {
    this.partyAddressService.keyPressMiddleName(event)
  }

  keyPressLastName(event: any) {
    this.partyAddressService.keyPressLastName(event)
  }

  keyPressFatherName(event: any) {
    this.partyAddressService.keyPressFatherName(event)
  }
  keyPressPanNumber(event: any) {
    this.partyAddressService.keyPressPanNumber(event)
  }
}
