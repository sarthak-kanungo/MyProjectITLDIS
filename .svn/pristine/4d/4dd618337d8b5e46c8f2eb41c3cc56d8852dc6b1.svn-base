import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';

@Component({
  selector: 'app-picking-slip',
  templateUrl: './picking-slip.component.html',
  styleUrls: ['./picking-slip.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class PickingSlipComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
