import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material';
import { Observable } from 'rxjs';
import { CityDetails, DistrictDetails, PinCodeDetails, PostOfficeDetails, PostOfficeDropdown, StateDetails, TehsilDetails } from '../../domain/dealer-employee-domain';
import { EmployeeMasterCreatePagePresenter } from '../employee-master-create-page/employee-master-page.presenter';
import { EmployeeAddressService } from './employee-address.service';

@Component({
  selector: 'app-employee-address',
  templateUrl: './employee-address.component.html',
  styleUrls: ['./employee-address.component.scss'],
  providers: [EmployeeAddressService,EmployeeMasterCreatePagePresenter]
})
export class EmployeeAddressComponent implements OnInit {
  disable = true;
  posts:Array<PostOfficeDropdown> = [
    'Sangavi', 'N.W. College', 'Pune Cantt East', 'Pune New Bazar', 'Sachapir Street', 'Ghorpuri Bazar', 
    'Raviwar Peth', 'Bajirao Road', 'Khadki'
  ];
 @Input() employeeAddressForm: FormGroup;
  countryId: number;
  stateId: number;
  districtId: number;
  tehsilId: number;
  cityId: number;
  pinCodeId: number;
  postOfficeId: number;
  stateList$: Observable<Array<StateDetails>>
  districtList$: Observable<Array<DistrictDetails>>
  tehsilList$: Observable<Array<TehsilDetails>>
  cityList$: Observable<Array<CityDetails>>
  pinCodeList$: Observable<Array<PinCodeDetails>>
  postOfficeList$: Observable<Array<PostOfficeDetails>>





  constructor( private fb: FormBuilder,
    private employeeAddressService:EmployeeAddressService,
    private employeeMasterPagePresenter:EmployeeMasterCreatePagePresenter) { }

  ngOnInit() {
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
  }
  getStateList() {
    this.employeeAddressForm.get('state').valueChanges.subscribe(value => {
      if (value) {
        const state = typeof value == 'object' ? value.state : value;
        this.autoState(state)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.stateValidation();
      // }
    })
  }

  getDistrictList() {
    this.employeeAddressForm.get('district').valueChanges.subscribe(value => {
      if (value) {
        const district = typeof value == 'object' ? value.district : value;
        this.autoDistrict(this.stateId, district)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.districtValidation();
      // }
    })
  }
  getTehsilList() {
    this.employeeAddressForm.get('tehsil').valueChanges.subscribe(value => {
      if (value) {
        const tehsil = typeof value == 'object' ? value.tehsil : value
        this.autoTehsil(this.districtId, tehsil)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.tehsilValidation();
      // }
    })
  }
  getVillageList() {
    this.employeeAddressForm.get('city').valueChanges.subscribe(value => {
      if (value) {
        const city = typeof value == 'object' ? value.city : value
        this.autoCity(this.tehsilId, city)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.villageValidation();
      // }
    })
  }
  getPinCodeList() {
    this.employeeAddressForm.get('pinCode').valueChanges.subscribe(value => {
      if (value) {
        const pinCode = typeof value == 'object' ? value.pinCode : value
        this.autoPin(this.districtId, pinCode)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.pinCodeValidation();
      // }
    })
  }
  getPostOfficeList() {
    this.employeeAddressForm.get('postOffice').valueChanges.subscribe(value => {
      if (value) {
        const postOffice = typeof value == 'object' ? value.postOffice : value
        this.autoPostOffice(this.pinCodeId, postOffice)
      }
      // if (value !== null) {
      //   this.employeeMasterPagePresenter.postOfficeValidation();
      // }
    })
  }
  postOfficeSelect(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('postOffice').setErrors(null);
    }
  }


  getCountry() {
    this.employeeAddressService.getCountry().subscribe(res => {
      this.countryId = res.id;
      this.employeeAddressForm.get('country').patchValue(res.country);
      this.employeeAddressForm.get('country').disable();
    });
  }

  autoState(stateName: string) {
    if (this.countryId) {
      this.stateList$ = this.employeeAddressService.getState(this.countryId, stateName)
    }
  }

  stateValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('state').setErrors({
        stateError:'Please select from List'
      })
    }
  }

  selectedState(event: MatAutocompleteSelectedEvent) {
    this.stateId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('state').setErrors(null);
    }
  }

  displayFnState(state: StateDetails) {
    return state ? state.state : undefined;
  }

  autoDistrict(id: number, name: string) {
    if (this.stateId) {
      this.districtList$ = this.employeeAddressService.getDistrict(id, name)
    }
  }

  districtValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('district').setErrors({
        districtError:'Please select from List'
      })
    }
  }


  selectedDistrict(event: MatAutocompleteSelectedEvent) {
    this.districtId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('district').setErrors(null);
    }
  }
  displayFnDistrict(district: DistrictDetails) {
    return district ? district.district : undefined;
  }
  autoTehsil(id: number, name: string) {
    if (this.districtId) {
      this.tehsilList$ = this.employeeAddressService.getTehsil(id, name);
    }
  }

  tehsilValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('tehsil').setErrors({
        tehsilError:'Please select from List'
      })
    }
  }


  selectedTehsil(event: MatAutocompleteSelectedEvent) {
    this.tehsilId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('tehsil').setErrors(null);
    }
  }
  displayFnTehsil(tehsil: TehsilDetails) {
    return tehsil ? tehsil.tehsil : undefined;
  }
  autoCity(id: number, name: string) {
    if (this.tehsilId) {
      this.cityList$ = this.employeeAddressService.getCity(id, name)
    }
  }

  villageValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('city').setErrors({
        cityError:'Please select from List'
      })
    }
  }

  selectedCity(event: MatAutocompleteSelectedEvent) {
    this.cityId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('city').setErrors(null);
    }
  }
  displayFnCity(city: CityDetails) {
    return city ? city.city : undefined;
  }
  autoPin(id: number, name: string) {
    if (this.cityId) {
      this.pinCodeList$ = this.employeeAddressService.getPinCode(id, name)
    }
  }

  pinCodeValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('pinCode').setErrors({
        pinCodeError:'Please select from List'
      })
    }
  }

  selectedPin(event: MatAutocompleteSelectedEvent) {
    this.pinCodeId = event.option.value.pinID;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('pinCode').setErrors(null);
    }
  }
  displayFnPin(pinCode: PinCodeDetails) {
    return pinCode ? pinCode.pinCode : undefined;
  }
  autoPostOffice(id: number, name: string) {
    if (this.pinCodeId) {
      this.postOfficeList$ = this.employeeAddressService.getPostOffice(id, name)
    }
  }

  postOfficeValue(event){
    if (typeof event=='string') {
      this.employeeAddressForm.get('postOffice').setErrors({
        postOfficeError:'Please select from List'
      })
    }
  }


  selectedPostOffice(event: MatAutocompleteSelectedEvent) {
    this.postOfficeId = event.option.value.id;
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.employeeAddressForm.get('postOffice').setErrors(null);
    }
  }
  displayFnPostOffice(postOffice: PostOfficeDetails) {
    return postOffice ? postOffice.postOffice : undefined;
  }

  

}
