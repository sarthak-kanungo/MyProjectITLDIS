import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-incentive-scheme-approval',
  templateUrl: './incentive-scheme-approval.component.html',
  styleUrls: ['./incentive-scheme-approval.component.scss']
})
export class IncentiveSchemeApprovalComponent implements OnInit {
  labelPosition = 'before';
  disable = true
  submodels: string[] = [
    '2WD', '4WD', 'I-Trac', 'G-Plus', 'HK', 'STD', 'With Seat', 'Without Seat'
  ];
  regions: string[] = [
    'All', 'R1', 'R2'
  ];
  zones: string[] = [
    'All', 'Z1', 'Z2'
  ];

  status: string[] = [
    'Active', 'Inactive',
  ];
  schemetypes: string[] = [
    'Wholesale', 'Retail', 'Exchange', 'DSE', 'TM/BM/SM', 'GM', 'Loyalty', 'Referal', 'Booking Scheme'
  ];

  incentiveSchemeMaster : FormGroup

  constructor(private fb : FormBuilder,
    public dialog: MatDialog) { }

  ngOnInit() {
    this.createincentiveSchemeMaster()
  }

  createincentiveSchemeMaster(){
    this.incentiveSchemeMaster = this.fb.group({
      status: ['', Validators.compose([Validators.required])],
      referenceSchemeNo: ['', Validators.compose([Validators.required])],
      validTo: ['', Validators.compose([Validators.required])],
      validFrom: ['', Validators.compose([Validators.required])],
    })
  }

}
