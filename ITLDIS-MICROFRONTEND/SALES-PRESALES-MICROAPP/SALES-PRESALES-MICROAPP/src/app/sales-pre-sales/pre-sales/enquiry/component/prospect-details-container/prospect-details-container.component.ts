import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { ProspectDetailsContainerService } from './prospect-details-container.service';
import { DropDownprospectType, AutoComplateMobileNo, ProspectDetailsObj, PinCode, AutocomplatePopulateDatabyPinCode, AutoComplatePostOffice } from 'ProspectDetails';
import { BaseDto } from 'BaseDto';
import { EnquiryService } from '../../enquiry.service';


@Component({
  selector: 'app-prospect-details-container',
  templateUrl: './prospect-details-container.component.html',
  styleUrls: ['./prospect-details-container.component.scss'],
  providers: [ProspectDetailsContainerService]
})
export class ProspectDetailsContainerComponent implements OnInit {
  dropDownprospectType: BaseDto<Array<DropDownprospectType>>
  autoComplateMobileNo: BaseDto<Array<AutoComplateMobileNo>>
  autoPopulateDatabyMobileNoList: BaseDto<ProspectDetailsObj>
  pinCode: BaseDto<Array<PinCode>>
  autocomplatePopulateDatabyPinCode: BaseDto<AutocomplatePopulateDatabyPinCode>
  autoComplatePostOffice: BaseDto<Array<AutoComplatePostOffice>>
  postOffice: string
  @Output() autoPopulatebyMobileNo = new EventEmitter<ProspectDetailsObj>()
  @Output() dataPopulatebyProspectCode = new EventEmitter<ProspectDetailsObj>()
  customerCode: string
  oldCustomerCode: string
  id: number
  @Input() getmodelValue: string
  @Output() getMobileNo = new EventEmitter<string>()
  @Output() keyDownMobileNumber = new EventEmitter<string>()
  @Output() keyDownProsCode = new EventEmitter<string>()
  @Output() sendProspectDetailsForm = new EventEmitter<object>()
  constructor(
    private prospectDetailsContainerService: ProspectDetailsContainerService,
    private enquiryService: EnquiryService
  ) { }

  ngOnInit() {
    this.dropdownProspectType();
  }

  dropdownProspectType() {
    this.prospectDetailsContainerService.dropdownprospectType().subscribe(response => {
      // console.log('prospects =>', response);
      this.dropDownprospectType = response as BaseDto<Array<DropDownprospectType>>
    })
  }

  autoMobileNo(event) {
    this.prospectDetailsContainerService.mobileNoData(event).subscribe(response => {
      // console.log('mobileNo',response);
      this.autoComplateMobileNo = response as BaseDto<Array<AutoComplateMobileNo>>;
    });
  }
  autoPopulateDatabyMobileNo(event) {
    this.prospectDetailsContainerService.searchbyMobileNo(event).subscribe(response => {
      console.log('autoPopulateData', response);
      this.autoPopulateDatabyMobileNoList = response as BaseDto<ProspectDetailsObj>
      this.prospectDetailsContainerService.prospectDetailsObj.customerCode = this.autoPopulateDatabyMobileNoList.result.customerCode
      this.customerCode = this.autoPopulateDatabyMobileNoList.result.customerCode
      this.oldCustomerCode = this.autoPopulateDatabyMobileNoList.result.oldCustomerCode
      this.id = this.autoPopulateDatabyMobileNoList.result.id
      this.autoPopulatebyMobileNo.emit(this.autoPopulateDatabyMobileNoList.result)
    })
    this.getMobileNo.emit(event)
  }

  autoPincode(event) {
    //console.log(event)
    this.prospectDetailsContainerService.searchPinCode(event).subscribe(response => {
      // console.log('pincode', response);
      this.pinCode = response as BaseDto<Array<PinCode>>
    });
  }

  autopopulatebyPincode(event) {
    this.prospectDetailsContainerService.searchByPinCode(event.pinCode, event.cityId).subscribe(response => {
      console.log('autopopulateDatas =>', response);
      this.autocomplatePopulateDatabyPinCode = response as BaseDto<AutocomplatePopulateDatabyPinCode>
    });

    this.autoPostOffice(event)
  }

  autoPostOffice(event) {
    this.prospectDetailsContainerService.postOfficeData(event.pinCode).subscribe(response => {
      console.log('postoffice', response);
      this.autoComplatePostOffice = response as BaseDto<Array<AutoComplatePostOffice>>
    });
  }


  getFormStatusandDataAndFormName(event) {
    // console.log('prospect', event);
    this.sendProspectDetailsForm.emit({ form: 'Prospect Details', event: event, customerCode: this.customerCode, oldCustomerCode: this.oldCustomerCode, id: this.id })
  }

  keyDownMobileNo(event){
   this.keyDownMobileNumber.emit(event)
  }

  keyDownProspectCode(event){
    this.keyDownProsCode.emit(event)
  }
}
