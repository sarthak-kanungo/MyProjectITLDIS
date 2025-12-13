import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { DropDownResult, DropDownLostDrop, DropDownReason } from 'LostDropEnquiry';
import { DropDownBrand } from 'CurrentMachineDetails';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { LostDropEnquiryContainerService } from './lost-drop-enquiry-container.service';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-lost-drop-enquiry-container',
  templateUrl: './lost-drop-enquiry-container.component.html',
  styleUrls: ['./lost-drop-enquiry-container.component.scss'],
  providers: [EnquiryCommonService, LostDropEnquiryContainerService]
})
export class LostDropEnquiryContainerComponent implements OnInit {

  dropDownResult : BaseDto<Array<DropDownResult>>
  dropDownLostDrop : BaseDto<Array<DropDownLostDrop>>
  dropDownBrand: BaseDto<Array<DropDownBrand>>
  dropDownReason : BaseDto<Array<DropDownReason>>
  @Output() sendLostDropFormData =  new EventEmitter<object>()
  constructor(
    private enquiryCommonService : EnquiryCommonService,
    private lostDropEnquiryContainerService : LostDropEnquiryContainerService
  ) { }
  

  ngOnInit() {
    this.dropdownResult()
    this.dropdownReason()
    this.dropdownBrand()
  }

  dropdownResult(){
    this.lostDropEnquiryContainerService.getResult().subscribe(response => {
      console.log('result', response);
      this.dropDownResult = response as BaseDto<Array<DropDownResult>>
    })
  }

  selectResultValue(event){
    this.lostDropEnquiryContainerService.getLostDropReason(event).subscribe(response => {
      this.dropDownLostDrop = response as BaseDto<Array<DropDownLostDrop>>
    })
  }

  dropdownReason(){
    this.lostDropEnquiryContainerService.getReason().subscribe(response => {
      this.dropDownReason = response as BaseDto<Array<DropDownReason>>
    })
  }

  dropdownBrand() {
    this.enquiryCommonService.dropdownbrand().subscribe(response => {
      // console.log('currentbrands =>', response);
      this.dropDownBrand = response as BaseDto<Array<DropDownBrand>>
    })
  }

  getLostDropFormStatusAndData(event){
    console.log('event1 =>', event);
    this.sendLostDropFormData.emit({form: 'lost/drop enquiry', event: event})
  }

}
