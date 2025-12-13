import { Component, OnInit, Input, OnChanges, Output, EventEmitter } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ClaimApprovalCreateService } from './claim-approval-create.service';
import { ViewEditActivityClaimDomain, ActivityEffectivenessDomain } from 'ActivityClaimModule';
import { BaseDto } from 'BaseDto';
import { ActivityClaimCreationService } from '../../../activity-claim/component/activity-claim-creation/activity-claim-creation.service';
import { ActivityClaimApprovalCommonWebService } from '../../activity-claim-approval-common-web.service';

@Component({
  selector: 'app-claim-approval-create',
  templateUrl: './claim-approval-create.component.html',
  styleUrls: ['./claim-approval-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    ClaimApprovalCreateService
  ]
})
export class ClaimApprovalCreateComponent implements OnInit, OnChanges {
  disable = true;
  status: string[] = [];
  dropdownActivityEffectiveness: BaseDto<Array<ActivityEffectivenessDomain>>
  activityTypes: string[] = [];
  activityClaimForm: FormGroup
  searchActivityNumber : any
  @Output() getFormStatusandData = new EventEmitter<object>()
  @Input() viewActivityClaim : ViewEditActivityClaimDomain

  constructor(
    private fb: FormBuilder,
    private activityClaimCreationService: ActivityClaimCreationService,
    private activityClaimApprovalCommonWebService : ActivityClaimApprovalCommonWebService
  ) { }

  ngOnChanges(){
    if(this.viewActivityClaim){
      this.activityClaimForm.patchValue(this.viewActivityClaim.activityClaimHeaderData)
      this.activityClaimForm.controls.hotEnquiry.patchValue(this.viewActivityClaim.activityClaimHeaderData.hotEnquiries)
      this.activityClaimForm.controls.warmEnquiry.patchValue(this.viewActivityClaim.activityClaimHeaderData.warmEnquiries)
      this.activityClaimForm.controls.coldEnquiry.patchValue(this.viewActivityClaim.activityClaimHeaderData.coldEnquiries)
      this.activityClaimForm.controls['fromDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.fromDate))
      this.activityClaimForm.controls['toDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.toDate))
      this.activityClaimForm.controls['actualFromDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.actualFromDate))
      this.activityClaimForm.controls['actualToDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.actualToDate))
      this.activityClaimForm.controls['activityCreationDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.activityCreationDate))
      this.activityClaimForm.controls['claimDate'].patchValue(new Date(this.viewActivityClaim.activityClaimHeaderData.claimDate))
      this.activityClaimForm.controls.activityEffectiveness.patchValue({effectiveness : this.viewActivityClaim.activityClaimHeaderData.effectiveness})
    }
  }

  ngOnInit() {
    this.createActivityClaimForm()
    this.getActivityEffectiveness();
  }
  
  createActivityClaimForm() {
    this.activityClaimForm = this.fb.group({
      claimNumber: [{ value: '', disabled: true }],
      activityNumber: [{ value: '', disabled: true }],
      claimStatus: [{ value: '', disabled: true }],
      claimDate: [{ value: '', disabled: true }],
      activityCreationDate: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      numberOfDays: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      actualFromDate: [{ value: '', disabled: true }],
      actualToDate: [{ value: '', disabled: true }],
      location: [{ value: '', disabled: true }],
      totalEnquiries: [{ value: '', disabled: true }],
      costPerEnquiry: [{ value: '', disabled: true }],
      hotEnquiry: [{ value: '', disabled: true }],
      costPerHotEnquiry: [{ value: '', disabled: true }],
      warmEnquiry: [{ value: '', disabled: true }],
      costPerBooking: [{ value: '', disabled: true }],
      coldEnquiry: [{ value: '', disabled: true }],
      costPerRetail: [{ value: '', disabled: true }],
      totalBookings: [{ value: '', disabled: true }],
      activityEffectiveness: [{ value: '', disabled: true }],
      totalRetails: [{ value: '', disabled: true }],
      approvedAmount: [null]
    })

    this.activityClaimApprovalCommonWebService.approveActivityClaimFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'approve') {
        this.getFormStatusandData.emit({ validStatus: this.activityClaimForm.valid, formData: this.activityClaimForm.getRawValue() });
      } 
    });

  }

  private getActivityEffectiveness() {
    this.activityClaimCreationService.getActivityEffectiveness().subscribe(res => {
      this.dropdownActivityEffectiveness = res as BaseDto<Array<ActivityEffectivenessDomain>>
    })
  }

  compareFnActivityEffectiveness(c1: ActivityEffectivenessDomain, c2: ActivityEffectivenessDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.effectiveness === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.effectiveness;
    }
    return c1 && c2 ? c1.effectiveness === c2.effectiveness : c1 === c2;
  }
}




