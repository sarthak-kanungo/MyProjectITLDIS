import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-service-claim',
  templateUrl: './search-service-claim.component.html',
  styleUrls: ['./search-service-claim.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class SearchServiceClaimComponent implements OnInit {

  searchform = false;
  status : string[] = [
    'Claim Approval Pending', 'Claim Approved',
  ]

  starcategories : string[] = [
    '1 Start','2 Start','3 Start','4 Start','5 Start'
  ]

  serviceClaimForm: FormGroup;
  advanceSearchForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchaserviceClaimForm();
    this.searchForm();
  }

  searchaserviceClaimForm() {
    this.serviceClaimForm = this.fb.group({
      
      claimNo: ['', Validators.compose([])],
      claimStatus: ['', Validators.compose([])],
      claimdateFrom: ['', Validators.compose([])],
      claimDateTo: ['', Validators.compose([])],
    })
  }

  searchForm() {
    this.advanceSearchForm = this.fb.group({ 
      
      dealerCode: ['', Validators.compose([])],
      dealerName: ['', Validators.compose([])],
      starcategory: ['', Validators.compose([])],
    })
  }

}
