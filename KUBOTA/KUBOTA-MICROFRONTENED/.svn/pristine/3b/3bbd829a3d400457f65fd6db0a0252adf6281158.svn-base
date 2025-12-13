import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownFollowupType, DropDownSource, DropDownPurposeOfPurchase, DropDownGenerationActivationType, DropDownConversationActivationType, DropDownRetailConversionActivityType, ViewEnquiryDomain, GetActivityNumber } from 'EnquiryCreation';
import { EnquiryCreationContainerService } from './enquiry-creation-container.service';
import { DropDownSalePerson } from 'TransferSearchCriteria';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-enquiry-creation-container',
  templateUrl: './enquiry-creation-container.component.html',
  styleUrls: ['./enquiry-creation-container.component.scss'],
  providers: [EnquiryCommonService, EnquiryCreationContainerService]
})
export class EnquiryCreationContainerComponent implements OnInit {

  dropDownFollowupType: BaseDto<Array<DropDownFollowupType>>
  dropDownSource: BaseDto<Array<DropDownSource>>
  dropDownPurposeOfPurchase: BaseDto<Array<DropDownPurposeOfPurchase>>
  dropDownGenerationActivationType: BaseDto<Array<DropDownGenerationActivationType>>
  dropDownConversationActivationType: BaseDto<Array<DropDownConversationActivationType>>
  dropDownRetailConversionActivityType: BaseDto<Array<DropDownRetailConversionActivityType>>
  dropdownSalePerson: BaseDto<Array<DropDownSalePerson>>
  generationActivityNumber: BaseDto<Array<GetActivityNumber>>
  sourceType
  activityType
  generationActivityNo : string
  @Output() sendData = new EventEmitter<object>()
  @Output() viewEnquiryDatas = new EventEmitter<ViewEnquiryDomain>()
  constructor(
    private enquiryCommonService: EnquiryCommonService,
    private enquiryCreationContainerService: EnquiryCreationContainerService
  ) { }

  ngOnInit() {
    this.dropdownSource();
    this.purposeOfPurchaseDropdown();
    this.dropdownfollowupType();
    this.dropdowngenerationActivityType()
    this.dropdownConversionActivityType();
    this.dropdownRetailConversionActivityType();
    this.dropDownSalesPerson()
  }

  purposeOfPurchaseDropdown() {
    this.enquiryCreationContainerService.dropdownPurposeOfPurchase().subscribe(response => {
      //console.log('purchase =>', response)
      this.dropDownPurposeOfPurchase = response as BaseDto<Array<DropDownPurposeOfPurchase>>
    })
  }

  dropdownSource() {
    this.enquiryCommonService.dropdownSource().subscribe(response => {
      // console.log('source =>', response)
      this.dropDownSource = response as BaseDto<Array<DropDownSource>>
    })
  }

  dropdownfollowupType() {
    this.enquiryCommonService.dropdownfollowupType().subscribe(response => {
      // console.log('follows =>', response)
      this.dropDownFollowupType = response as BaseDto<Array<DropDownFollowupType>>
    })
  }

  dropdowngenerationActivityType() {
    this.enquiryCreationContainerService.dropdowngenerationActivityType().subscribe(response => {
      // console.log('generations =>', response);
      this.dropDownGenerationActivationType = response as BaseDto<Array<DropDownGenerationActivationType>>
    })
  }

  dropdownConversionActivityType() {
    this.enquiryCreationContainerService.dropdownconversionActivityType().subscribe(response => {
      // console.log('conversions => ', response);
      this.dropDownConversationActivationType = response as BaseDto<Array<DropDownConversationActivationType>>
    })
  }

  dropdownRetailConversionActivityType() {
    this.enquiryCommonService.dropdownretailconversionActivityType().subscribe(response => {
      // console.log('conversions => ', response);
      this.dropDownRetailConversionActivityType = response as BaseDto<Array<DropDownRetailConversionActivityType>>
    })
  }

  dropDownSalesPerson() {
    this.enquiryCommonService.getAllSalesPerson().subscribe(response => {
      this.dropdownSalePerson = response as BaseDto<Array<DropDownSalePerson>>
    })
  }

  autoGenerationActivityNo(event) {
    // this.enquiryCreationContainerService.searchMarketingActivityNumber(null, 'Generation', this.sourceType).subscribe(response => {
    //   this.generationActivityNumber = response as BaseDto<Array<GetActivityNumber>>
    // })
  }
  sourceName(event) {
    console.log('event', event)
    this.sourceType = event
    if(this.activityType === 'KAI Supported' && this.sourceType != ''){
        this.enquiryCreationContainerService.searchMarketingActivityNumber(null, 'Generation', this.sourceType, this.activityType).subscribe(response => {
          this.generationActivityNumber = response as BaseDto<Array<GetActivityNumber>>
        })
    }else{
        this.generationActivityNumber = {} as BaseDto<Array<GetActivityNumber>>;
    }
  }
  activityTypeEvent(event){
      this.activityType = event
      if(this.activityType === 'KAI Supported' && this.sourceType != ''){
          this.enquiryCreationContainerService.searchMarketingActivityNumber(null, 'Generation', this.sourceType, this.activityType).subscribe(response => {
            this.generationActivityNumber = response as BaseDto<Array<GetActivityNumber>>
          })
      }else{
          this.generationActivityNumber = {} as BaseDto<Array<GetActivityNumber>>;
      }
  }
  getFormStatusandDataWithFromName(event) {
    // console.log('event1', event);
    this.sendData.emit({ form: 'newEnquiry', event: event })
  }

  viewEnquiryData(event) {
    this.viewEnquiryDatas.emit(event)
  }
}
