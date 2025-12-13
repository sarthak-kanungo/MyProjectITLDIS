import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-claim-approval-dealer-info',
  templateUrl: './claim-approval-dealer-info.component.html',
  styleUrls: ['./claim-approval-dealer-info.component.scss']
})
export class ClaimApprovalDealerInfoComponent implements OnInit {

  dealerDetailsFrom : FormGroup
  @Input() dealerInfo : any

  constructor(
    private fb : FormBuilder
  ) { }
  

  ngOnInit() {
    this.createActivityApproval(this.dealerInfo)
  }

  createActivityApproval(dealerInfo) {
    this.dealerDetailsFrom = this.fb.group({
      dealerCode: [{ value: dealerInfo?dealerInfo.dealerCode:'', disabled: true }],
      dealerName: [{ value: dealerInfo?dealerInfo.dealerName:'', disabled: true }],
      state: [{ value: dealerInfo?dealerInfo.state:'', disabled: true }],
    })
  }

}
