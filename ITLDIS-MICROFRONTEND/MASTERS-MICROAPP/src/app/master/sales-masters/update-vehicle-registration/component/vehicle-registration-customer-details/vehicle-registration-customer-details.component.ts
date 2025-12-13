import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { BaseDto } from 'BaseDto';
import { AutoPopulateChassisNo } from 'VehicleRegistration';

@Component({
  selector: 'app-vehicle-registration-customer-details',
  templateUrl: './vehicle-registration-customer-details.component.html',
  styleUrls: ['./vehicle-registration-customer-details.component.scss']
})
export class VehicleRegistrationCustomerDetailsComponent implements OnInit {
  customerDetailsForm: FormGroup;
  //value patch from another components
  @Input() set autoPopulateChassisNumber(list:BaseDto<AutoPopulateChassisNo>){
    if(this.customerDetailsForm){
      this.customerDetailsForm.patchValue(list.result)
    }
  }

  disable = true;
 
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.createcustomerDetailsForm();
  }

  createcustomerDetailsForm() {
    this.customerDetailsForm = this.fb.group({
      
      customerCode:[{value:'', disabled:true}, Validators.compose([])],
      title: [{value:'', disabled:true}, Validators.compose([])],
      firstName: [{value:'', disabled:true}, Validators.compose([])],
      middleName: [{value:'', disabled:true}, Validators.compose([])],
      lastName: [{value:'', disabled:true}, Validators.compose([])],
      mobilenumber: [{value:'', disabled:true}, Validators.compose([])],
      pancardNo: [{value:'', disabled:true}, Validators.compose([])],
      gstNo: [{value:'', disabled:true}, Validators.compose([])],
    
    })
  }

}
