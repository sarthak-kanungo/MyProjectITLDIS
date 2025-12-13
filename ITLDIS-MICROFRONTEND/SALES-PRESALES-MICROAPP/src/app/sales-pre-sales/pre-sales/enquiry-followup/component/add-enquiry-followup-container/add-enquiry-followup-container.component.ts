import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { EnquiryCode } from 'EnquiryCreation';
import { AddEnquiryFollowupContainerService } from './add-enquiry-followup-container.service';
import { EnquiryFollowUpDomain } from 'AddEnquiryFollowup';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-add-enquiry-followup-container',
  templateUrl: './add-enquiry-followup-container.component.html',
  styleUrls: ['./add-enquiry-followup-container.component.scss'],
  providers: [EnquiryCommonService, AddEnquiryFollowupContainerService]
})
export class AddEnquiryFollowupContainerComponent implements OnInit {
  enquiryCode: BaseDto<Array<EnquiryCode>>
  autoPopulateDatabyEnquiryCode : BaseDto<EnquiryFollowUpDomain>
  @Output() sendFollowupData = new EventEmitter<object>()
  @Output() SelectForDisplayCards = new EventEmitter<string>()
  constructor(
    private enquiryCommonService: EnquiryCommonService,
    private addEnquiryFollowupContainerService: AddEnquiryFollowupContainerService
  ) { }

  ngOnInit() {
  }

  // autoEnquiryNumber(event) {
  //   this.enquiryCommonService.searchEnquiryCode(event).subscribe(response => {
  //     console.log('enquiryCode', response);
  //     this.enquiryCode = response as BaseDto<Array<EnquiryCode>>
  //   });
  // }

  autoPopulatebyEnquiryCode(event) {
    this.addEnquiryFollowupContainerService.autoPupolateDataByEnquiryCode(event).subscribe(response => {
      console.log('autoPupolateDataByEnquiryCode', response);
      this.autoPopulateDatabyEnquiryCode = response as BaseDto<EnquiryFollowUpDomain>
    })
  }

  getFollowupFormStatusAndData(event){
    console.log('event1 =>', event);
      this.sendFollowupData.emit({form: 'Add Enquiry Followup', event: event})
  }

  SelectForDisplayCard(event){
     this.SelectForDisplayCards.emit(event)
  }

}
