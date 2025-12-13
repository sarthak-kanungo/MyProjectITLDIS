import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ViewEditActivityClaimDomain } from 'ActivityClaimModule';

@Component({
  selector: 'app-claim-approval-dealer-info',
  templateUrl: './claim-approval-dealer-info.component.html',
  styleUrls: ['./claim-approval-dealer-info.component.scss']
})
export class ClaimApprovalDealerInfoComponent implements OnInit, OnChanges {

  dealerDetailsFrom : FormGroup
  @Input() viewActivityClaim : ViewEditActivityClaimDomain

  constructor(
    private fb : FormBuilder
  ) { }

  ngOnChanges(){
     if(this.viewActivityClaim){
       this.dealerDetailsFrom.patchValue(this.viewActivityClaim.dealerInfo)
     }
  }
  

  ngOnInit() {
    this.createActivityApproval()
  }

  createActivityApproval() {
    this.dealerDetailsFrom = this.fb.group({
      dealerCode: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      state: [{ value: '', disabled: true }],
    })
  }

}
