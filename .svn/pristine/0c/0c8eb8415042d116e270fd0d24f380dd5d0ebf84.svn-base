import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer-address',
  templateUrl: './customer-address.component.html',
  styleUrls: ['./customer-address.component.scss']
})
export class CustomerAddressComponent implements OnInit {
  localitys: string[] = [
    'Wakad', 'Kothrud', 'Swargate',
  ];
  disable = true;
  addressDetailsForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.createaddressDetailsForm();
  }

  createaddressDetailsForm() {
    this.addressDetailsForm = this.fb.group({
      address1: ['', Validators.compose([Validators.required])],
      address2: ['', Validators.compose([])],
      address3: ['', Validators.compose([])],
      pinCode: ['', Validators.compose([])],
      locality: ['', Validators.compose([Validators.required])],
      village: ['', Validators.compose([Validators.required])],
      district: [{value:'', disabled: true}, Validators.compose([])],
      city: [{value:'', disabled: true}, Validators.compose([])],
      state: [{value:'', disabled: true}, Validators.compose([])],
      country: [{value:'', disabled: true}, Validators.compose([])],
    })
  }

}
