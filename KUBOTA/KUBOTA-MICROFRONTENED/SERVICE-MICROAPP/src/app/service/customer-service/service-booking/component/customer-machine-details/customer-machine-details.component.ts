import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-customer-machine-details',
  templateUrl: './customer-machine-details.component.html',
  styleUrls: ['./customer-machine-details.component.scss'],
})
export class CustomerMachineDetailsComponent implements OnInit {
  
  @Input() customerMachineDetailsForm: FormGroup;
  
  constructor( ) { }

  ngOnInit() {

  }

  
}
