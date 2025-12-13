import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-claim-approval-job-card-details',
  templateUrl: './claim-approval-job-card-details.component.html',
  styleUrls: ['./claim-approval-job-card-details.component.scss']
})
export class ClaimApprovalJobCardDetailsComponent implements OnInit {
  headsTable = [
    {
      customerCode: '',
      customerName: '',
      documentdate: '',
      chassisNo: '',
      model: '',
      typeOfService: '',
      dateofInstallation: '',
      amount: '',
      bonusAmount:''
    }
  ]
  constructor() { }

  ngOnInit() {
  }

}
