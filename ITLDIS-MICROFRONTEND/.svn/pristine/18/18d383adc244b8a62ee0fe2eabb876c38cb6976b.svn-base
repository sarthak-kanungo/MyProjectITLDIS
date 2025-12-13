import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ViewEditActivityClaimDomain } from 'ActivityClaimModule';

@Component({
  selector: 'app-claim-approval-heads',
  templateUrl: './claim-approval-heads.component.html',
  styleUrls: ['./claim-approval-heads.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})
export class ClaimApprovalHeadsComponent implements OnInit, OnChanges{

  @Input() viewHeads: ViewEditActivityClaimDomain
  totalAmount : number = 0

  constructor() {}

  ngOnChanges(){
    if(this.viewHeads){
      let total = 0
      this.viewHeads.activityClaimHeads.forEach(ele => {
        total = ele.actualClaimAmount + ele.gstAmount
        this.totalAmount += total
      })
    }
  }

  ngOnInit(){

  }
  
  // onActualClaimAmount(value: string, head: ActivityHead) {
  //   head.approvedAmount = Number.parseInt(value)
  // }


}


