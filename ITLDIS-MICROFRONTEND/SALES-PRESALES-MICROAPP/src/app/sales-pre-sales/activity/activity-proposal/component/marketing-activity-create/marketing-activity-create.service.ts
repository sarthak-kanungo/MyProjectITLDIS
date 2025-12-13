import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ViewEditActivityProposalDomain, Head } from 'ActivityProposalModule';
import { environment } from '../../../../../../environments/environment';
import { urlService } from '../../../../../webservice-config/baseurl';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BaseDto } from 'BaseDto';
import { map } from 'rxjs/operators';

@Injectable()
export class MarketingActivityCreateService {
  constructor(
    private fb: FormBuilder,
    private httpClient : HttpClient) { }

  createactivityProposalForm() {
    return this.fb.group({
      activityPurpose: ['', Validators.compose([Validators.required])],
      activityType: ['', Validators.compose([Validators.required])],
      activityCategory: ['', Validators.compose([Validators.required])],
      fromDate: ['', Validators.compose([Validators.required])],
      toDate: ['', Validators.compose([Validators.required])],
      
      location: ['', Validators.compose([Validators.required])],
      // activityNumber: this.fb.control(''),
      activityNumber:[{ value: '', disabled: true }],
      activityCreationDate: [{ value: new Date(), disabled: true }],
      activityStatus: [{ value: '', disabled: true }],
      numberOfDays: this.fb.control({ value: '', disabled: true }),
      proposedBudget: this.fb.control({ value: '', disabled: true }),
      maxAllowedBudget: this.fb.control({ value: '', disabled: true }),
      claimableAmount: this.fb.control({ value: '', disabled: true }),
    })
  }

  editActivityProposalForm() {
    return this.fb.group({
      activityPurpose: ['', Validators.compose([Validators.required])],
      activityType: ['', Validators.compose([Validators.required])],
      activityCategory: ['', Validators.compose([Validators.required])],
      fromDate: ['', Validators.compose([Validators.required])],
      toDate: ['', Validators.compose([Validators.required])],
      location: ['', Validators.compose([Validators.required])],
      activityNumber: this.fb.control({ value: '', disabled: true }),
      activityCreationDate: [{ value: '', disabled: true }],
      activityStatus: [{ value: '', disabled: true }],
      numberOfDays: [{ value: '', disabled: true }],
      proposedBudget: [{ value: '', disabled: true }],
      maxAllowedBudget: [{ value: '', disabled: true }],
      claimableAmount : [{ value: '', disabled: true }]
    })
  }

  viewActivityProposalForm() {
    return this.fb.group({
      activityPurpose: this.fb.control({ value: '', disabled: true }),
      activityType: this.fb.control({ value: '', disabled: true }),
      activityCategory: this.fb.control({ value: '', disabled: true }),
      fromDate: this.fb.control({ value: '', disabled: true }),
      toDate: this.fb.control({ value: '', disabled: true }),
      location: this.fb.control({ value: '', disabled: true }),
      activityNumber: this.fb.control({ value: '', disabled: true }),
      activityCreationDate: this.fb.control({ value: '', disabled: true }),
      activityStatus: this.fb.control({ value: '', disabled: true }),
      numberOfDays: this.fb.control({ value: '', disabled: true }),
      proposedBudget: this.fb.control({ value: '', disabled: true }),
      maxAllowedBudget: this.fb.control({ value: '', disabled: true }),
      approvedAmount: this.fb.control({ value: '', disabled: true }),
      claimableAmount: this.fb.control({ value: '', disabled: true }),
    })
  }

}