import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-transporter-address-details',
  templateUrl: './transporter-address-details.component.html',
  styleUrls: ['./transporter-address-details.component.scss']
})
export class TransporterAddressDetailsComponent implements OnInit {


  localities: string[] = [
    'Sangavi', 'N.W. College', 'Pune Cantt East', 'Pune New Bazar', 'Sachapir Street', 'Ghorpuri Bazar', 
    'Raviwar Peth', 'Bajirao Road', 'Khadki'
  ];

  @Input() addressDetailsForm: FormGroup;
  
  constructor() { }

  ngOnInit() {
  }

}
