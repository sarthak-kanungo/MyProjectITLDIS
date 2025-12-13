import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  tehsils: string[] = [
    'Pune', 'Haveli', 'Khed', 'Baramati', 'Junnar', 'Shirur', 'Indapur', 'Daund', 'Mawal'
  ];
  cities: string[] = [
    'Pune', 'Mumbai', 'Chennai', 'Surat', 
  ];
  states: string[] = [
    'Maharashtra ', 'Odisha', 'Gujarat', 'Punjab', 
  ];
  countries: string[] = [
    'India ', 'Japan', 'China', 'Bangladesh' 
  ];
  // dealeraddressForm: FormGroup;
  @Input() formName: FormGroup;
  constructor(
  ) { }

  ngOnInit() {
  }

}
