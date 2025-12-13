import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-transporter-details',
  templateUrl: './transporter-details.component.html',
  styleUrls: ['./transporter-details.component.scss']
})
export class TransporterDetailsComponent implements OnInit {
  
  titles: string[] = [
    'Mr.', 'Miss', 'Mrs.'
  ];
  transporterDeatilsForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchtransporterDeatilsForm();
  }

  searchtransporterDeatilsForm() {
    this.transporterDeatilsForm = this.fb.group({
      
      code: ['', Validators.compose([])],
      transporterName: ['', Validators.compose([Validators.required])],
      title: ['', Validators.compose([Validators.required])],
      designation: ['', Validators.compose([])],
      transporterLocation: ['', Validators.compose([Validators.required])],
      firstName: ['', Validators.compose([Validators.required])],
      middleName: ['', Validators.compose([])],
      lastName: ['', Validators.compose([])],
      mobile: ['', Validators.compose([Validators.required])],
      emailId: ['', Validators.compose([])],
      gstNo: ['', Validators.compose([])],
      panNo: ['', Validators.compose([])],
      aadharCardNo: ['', Validators.compose([])],
    })
  }

}
