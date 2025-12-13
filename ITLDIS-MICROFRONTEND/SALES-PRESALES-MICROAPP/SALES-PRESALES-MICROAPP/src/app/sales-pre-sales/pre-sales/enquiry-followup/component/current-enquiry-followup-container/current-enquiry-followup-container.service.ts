import { Injectable } from '@angular/core';
import { CurrentEnquiryFollowupDomain } from 'CurrentEnquiryFolloeup';

@Injectable()
export class CurrentEnquiryFollowupContainerService {
  currentEnquiryFollowupDomain = {} as CurrentEnquiryFollowupDomain
  constructor() { }
}
