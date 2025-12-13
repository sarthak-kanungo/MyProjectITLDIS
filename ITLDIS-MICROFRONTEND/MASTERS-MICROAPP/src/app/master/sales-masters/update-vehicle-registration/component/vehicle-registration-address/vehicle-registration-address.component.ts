import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { AutoPopulateChassisNo } from 'VehicleRegistration';

@Component({
  selector: 'app-vehicle-registration-address',
  templateUrl: './vehicle-registration-address.component.html',
  styleUrls: ['./vehicle-registration-address.component.scss']
})
export class VehicleRegistrationAddressComponent implements OnInit {
 
  addressDetailsForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  @Input() set autoPopulateChassisNumber(list:BaseDto<AutoPopulateChassisNo>){
    if(this.addressDetailsForm){
      this.addressDetailsForm.patchValue(list.result)
    }
  }

  ngOnInit() {
    this.createaddressDetailsForm();
  }

  createaddressDetailsForm() {
    this.addressDetailsForm = this.fb.group({
      
      address1: [{value:'', disabled:true}, Validators.compose([])],
      address2: [{value:'', disabled:true}, Validators.compose([])],
      address3: [{value:'', disabled:true}, Validators.compose([])],
      pinCode: [{value:'', disabled:true}, Validators.compose([])],
      locality: [{value:'', disabled:true}, Validators.compose([])],
      village: [{value:'', disabled:true}, Validators.compose([])],
      district: [{value:'', disabled:true}, Validators.compose([])],
      city: [{value:'', disabled:true}, Validators.compose([])],
      state: [{value:'', disabled:true}, Validators.compose([])],
      country: [{value:'', disabled:true}, Validators.compose([])],
    })
  }

}
