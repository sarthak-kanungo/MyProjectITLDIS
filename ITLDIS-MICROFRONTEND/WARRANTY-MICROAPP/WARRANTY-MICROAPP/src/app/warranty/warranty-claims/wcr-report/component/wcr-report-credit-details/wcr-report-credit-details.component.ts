import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-wcr-report-credit-details',
  templateUrl: './wcr-report-credit-details.component.html',
  styleUrls: ['./wcr-report-credit-details.component.scss']
})
export class WcrReportCreditDetailsComponent implements OnInit {
  @Input() wcrReportCreditDetailForm: FormArray;
  installationDetailsHeading = ['Date of Installation', 'Date of Failure', 'Invoice No.', 'Credit Note No.', 'Credit Note Date', 'Credit Req No.', 'Status', 'Service Dealer Name', 'Amount'];
  // 'Vendor Warranty Claim No.'

  constructor() { }

  ngOnInit() {
  }

}
