import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-transporter-address-details',
  templateUrl: './transporter-address-details.component.html',
  styleUrls: ['./transporter-address-details.component.scss']
})
export class TransporterAddressDetailsComponent implements OnInit {

  localities: string[] = [
    'Kothrud', 'Wakad', 'Baner'
  ];
  addressDeatilsForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchaddressDeatilsForm();
  }

  searchaddressDeatilsForm() {
    this.addressDeatilsForm = this.fb.group({
      
      address1: ['', Validators.compose([Validators.required])],
      address2: ['', Validators.compose([])],
      address3: ['', Validators.compose([])],
      pinCode: ['', Validators.compose([])],
      locality: ['', Validators.compose([Validators.required])],
      tehsil: [{value:'', disabled: true}, Validators.compose([])],
      city: [{value:'', disabled: true}, Validators.compose([])],
      state: [{value:'', disabled: true}, Validators.compose([])],
      country: [{value:'', disabled: true}, Validators.compose([])],
      telephone: ['', Validators.compose([])],
      emailId: ['', Validators.compose([])],
    })
  }

}
