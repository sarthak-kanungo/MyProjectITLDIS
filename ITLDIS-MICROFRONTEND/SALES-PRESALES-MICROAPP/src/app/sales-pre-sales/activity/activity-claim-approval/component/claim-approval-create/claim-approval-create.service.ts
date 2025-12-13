import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Injectable(
 
)
export class ClaimApprovalCreateService {

  private activityClaimFrom:FormGroup
  constructor(
    private fb:FormBuilder
  ) { }

  createactivityClaimFrom() {
    this.activityClaimFrom = this.fb.group({
      activityClaimNo:[''],
      activityNo:[''],
      activityclaimStatus: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      activityClaimDate: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      activitycreateDate: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      activityType:[{value:'', disabled:true}],
      noOfDays:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      fromDate:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      toDate:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      actualFromDate:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      actualToDate:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      location:[{value:'', disabled:true}, Validators.compose([Validators.required])],
      totalEnquiries: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      costPerEnquiries: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      hotEnquiry: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      costPerHotEnquiry: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      warmEnquiry: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      costPerBooking: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      coldEnquiry: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      costPerRetail: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      totalBooking: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      activityEffectiveness: [{value:'', disabled:true}, Validators.compose([Validators.required])],
      totalRetails: [{value:'', disabled:true}, Validators.compose([Validators.required])],
    })
    return this.activityClaimFrom;
  }
}
