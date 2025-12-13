import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';

@Component({
  selector: 'app-incentive-scheme-claim',
  templateUrl: './incentive-scheme-claim.component.html',
  styleUrls: ['./incentive-scheme-claim.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class IncentiveSchemeClaimComponent implements OnInit {
  disable = true;
  months: string[] = [
    'January', 'february', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October',
    'November', 'December'
  ];
  constructor() { }

  ngOnInit() {
  }

}
