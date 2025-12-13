import { Component, OnInit, Input } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { Enquiry } from 'approval';

@Component({
  selector: 'app-enquiry-information',
  templateUrl: './enquiry-information.component.html',
  styleUrls: ['./enquiry-information.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})

export class EnquiryInformationComponent implements OnInit {

  @Input() enquiries : Array<Enquiry>
 
  ngOnInit() {
    
  }
}
