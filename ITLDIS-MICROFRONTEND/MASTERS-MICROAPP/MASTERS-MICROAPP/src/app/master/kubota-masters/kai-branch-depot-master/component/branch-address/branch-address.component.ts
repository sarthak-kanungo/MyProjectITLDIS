import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-branch-address',
  templateUrl: './branch-address.component.html',
  styleUrls: ['./branch-address.component.scss']
})
export class BranchAddressComponent implements OnInit {
  disable = true;
  vendors: string[] = [
    'YES', 'NO',  
  ];

  salesdealers: string[] = [
    'YES', 'NO', 
  ];

  Products: string[] = [
    'Tractor', 'Harvestor', 'Transplanter', 'Power Tillers', 'Rotary Tillers', 'Seeding Machines', 'Others'  
  ];
  status: string[] = [
    'ACTIVE', 'INACTIVE', 
  ];
  branchAddressForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.createbranchAddressForm();
  }

  createbranchAddressForm() {
    this.branchAddressForm = this.fb.group({
      
      address1: [{value:'', disabled:true}, Validators.compose([])],
      address2: [{value:'', disabled:true}, Validators.compose([])],
      address3: [{value:'', disabled:true}, Validators.compose([])],
      pinCode: [{value:'', disabled:true}, Validators.compose([])],
      tehsil: [{value:'', disabled:true}, Validators.compose([])],
      city: [{value:'', disabled:true}, Validators.compose([])],
      state: [{value:'', disabled:true}, Validators.compose([])],
      country: [{value:'', disabled:true}, Validators.compose([])],
      std: [{value:'', disabled:true}, Validators.compose([])],
      telephoneNo: [{value:'', disabled:true}, Validators.compose([])],
      email: [{value:'', disabled:true}, Validators.compose([])],
      fax: [{value:'', disabled:true}, Validators.compose([])],
    })
  }
}
