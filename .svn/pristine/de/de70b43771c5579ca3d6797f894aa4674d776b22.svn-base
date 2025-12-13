import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-branch-sub-dealer-address',
  templateUrl: './branch-sub-dealer-address.component.html',
  styleUrls: ['./branch-sub-dealer-address.component.scss']
})
export class BranchSubDealerAddressComponent implements OnInit {
  localities: string[] = [
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
      tehsil: [{value:'', disabled: true}, Validators.compose([])],
      city: [{value:'', disabled: true}, Validators.compose([])],
      state: [{value:'', disabled: true}, Validators.compose([])],
      country: [{value:'', disabled: true}, Validators.compose([])],
      std: ['', Validators.compose([])],
      telephoneNo: ['', Validators.compose([])],
      emailId: ['', Validators.compose([])],
    })
  }


}
