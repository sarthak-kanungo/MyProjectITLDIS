import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';

@Component({
  selector: 'app-warrenty-claim-request-failure-parts',
  templateUrl: './warrenty-claim-request-failure-parts.component.html',
  styleUrls: ['./warrenty-claim-request-failure-parts.component.scss']
})
export class WarrentyClaimRequestFailurePartsComponent implements OnInit {
  @Input() 
  failurePartsForm: FormArray;
  totalValue:number = 0;
  totalClaimQty:number = 0;
  totalClaimApprovedQuantity:number = 0;
  totalClaimApprovedAmount:number = 0;

  failurePartHeading = ['Sl.No', 'Part No.', 'Description', 'Unit Price', 'Claim Qty', 'Claim Value'];
  dealerCode: string;
  // labourChargeHeading = ['Sl.No', 'Job Code(FRS)', 'Description', 'Hours', 'Claim Amount', 'Final Approved Amount'];
  // outsideLabourChargeHeading = ['Sl.No', 'Description', 'Claim Amount', 'Final Approved Amount'];
  constructor(
    private wcrPagePresenter: WcrPagePresenter,
  ) { }

  ngOnInit() {
    this.dealerCode = this.wcrPagePresenter.loginUser.dealerCode;
    if(this.dealerCode == null) {
      this.failurePartHeading[6] = 'Final Approved Qty';
      this.failurePartHeading[7] = 'Final Approved Amount';

    }

    if(this.failurePartsForm){
      this.failurePartsForm.controls.forEach(element => {
        this.totalClaimQty = this.totalClaimQty + (element.get('approvedQuantity').value?parseInt(element.get('approvedQuantity').value):0);
        this.totalValue = this.totalValue + (element.get('claimValue').value?parseInt(element.get('claimValue').value):0);
        this.totalClaimApprovedQuantity = this.totalClaimApprovedQuantity + element.get('claimApprovedQuantity').value?this.totalClaimApprovedQuantity + parseInt(element.get('claimApprovedQuantity').value):0;
        this.totalClaimApprovedAmount = this.totalClaimApprovedAmount + element.get('claimApprovedAmount').value?this.totalClaimApprovedAmount + parseInt(element.get('claimApprovedAmount').value):0;
      });
    }

  }

}
