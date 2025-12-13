import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-search-service-claim-invoice',
  templateUrl: './search-service-claim-invoice.component.html',
  styleUrls: ['./search-service-claim-invoice.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class SearchServiceClaimInvoiceComponent implements OnInit {

  dealernames : string[] = [
    'A', 'B', 'C'
  ]
  serviceClaimInvoiceForm: FormGroup;
  constructor( private fb: FormBuilder) { }

  ngOnInit() {
    this.searchaserviceClaimInvoiceForm();
  }

  searchaserviceClaimInvoiceForm() {
    this.serviceClaimInvoiceForm = this.fb.group({
      
      claimCoveringLetterNo: ['', Validators.compose([])],
      dealerName: ['', Validators.compose([])],
      dealerCode: ['', Validators.compose([])],
      claimCoveringdateFrom: ['', Validators.compose([])],
      claimCoveringdateTo: ['', Validators.compose([])],
    })
  }

}
