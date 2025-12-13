import { Component, OnInit, Output, EventEmitter,Input } from '@angular/core';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownFollowupType } from 'EnquiryCreation';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-current-enquiry-followup-container',
  templateUrl: './current-enquiry-followup-container.component.html',
  styleUrls: ['./current-enquiry-followup-container.component.scss'],
  providers: [EnquiryCommonService]
})
export class CurrentEnquiryFollowupContainerComponent implements OnInit {
  dropDownFollowupType: BaseDto<Array<DropDownFollowupType>>
  @Output() sendCurrentFollowupData =  new EventEmitter<object>()
  constructor(
    private enquiryCommonService: EnquiryCommonService
  ) { }

  ngOnInit() {
    this.dropdownfollowupType()
  }

  dropdownfollowupType() {
    this.enquiryCommonService.dropdownfollowupType().subscribe(response => {
      // console.log('follows =>', response)
      this.dropDownFollowupType = response as BaseDto<Array<DropDownFollowupType>>
    })
  }

  getCurrentFollwupFormStatusAndData(event){
    console.log('event1 =>', event);
    this.sendCurrentFollowupData.emit({form: 'Current Enquiry Followup', event: event})
  }

}
