import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-incentive-claim',
  templateUrl: './search-incentive-claim.component.html',
  styleUrls: ['./search-incentive-claim.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class SearchIncentiveClaimComponent implements OnInit {
  today = new Date();
  minDate: Date;
  maxDate: Date
  
  SearchIncentiveClaim:FormGroup
  constructor(private fb: FormBuilder) { }

  ngOnInit() {

    this.createSearchIncentiveClaim()
  }
  ngAfterViewInit() {
    this.maxDate = this.today
    let backDate = new Date();
    backDate.setMonth(this.today.getMonth() - 1);
    this.minDate = backDate;
    this.SearchIncentiveClaim.get('claimfromdate').patchValue(backDate);
    this.SearchIncentiveClaim.get('claimtodate').patchValue(new Date());
  }
  fromDateChange(event) {

    // console.log('event', event)
    // this.changedInDate = event.target.value
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.today)
        this.maxDate = this.today;
      else
        this.maxDate = maxDate;
      if (this.SearchIncentiveClaim.get('claimtodate').value > this.maxDate)
        this.SearchIncentiveClaim.get('claimtodate').patchValue(this.maxDate);
    }

  }
  createSearchIncentiveClaim() {
    this.SearchIncentiveClaim = this.fb.group({
      schemeno: ['', Validators.compose([])],
      
      dealercode: ['', Validators.compose([])],

      claimfromdate: ['', Validators.compose([])],
      claimtodate: ['', Validators.compose([])],


    })
  }
}
