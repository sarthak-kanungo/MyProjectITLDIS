import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ActivityHead } from 'approval';

@Component({
  selector: 'app-heads',
  templateUrl: './heads.component.html',
  styleUrls: ['./heads.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})

export class HeadsComponent implements OnInit {

  @Input()
  heads: Array<ActivityHead>

  constructor() { }

  ngOnInit() {
  }

}
