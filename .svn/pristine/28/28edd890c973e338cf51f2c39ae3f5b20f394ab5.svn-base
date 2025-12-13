import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownBrand } from 'CurrentMachineDetails';
import { BaseDto } from 'BaseDto';
import { ProspectDetailsObj } from 'ProspectDetails';

@Component({
  selector: 'app-current-machine-details-container',
  templateUrl: './current-machine-details-container.component.html',
  styleUrls: ['./current-machine-details-container.component.scss'],
  providers: [EnquiryCommonService]
})
export class CurrentMachineDetailsContainerComponent implements OnInit {
  dropDownBrand: BaseDto<Array<DropDownBrand>>
  @Input() dataAutoPopulateByMobileNumber : ProspectDetailsObj
@Output() submitCurrenMachineDetailsForm = new EventEmitter<object>()
  constructor(
    private enquiryCommonService : EnquiryCommonService
  ) { }

  ngOnInit() {
    this.dropdownBrand()
   // this.enquiryCommonService.emCommon.subscribe( ev => this.enquiryCommonService.emCommon.emit(ev))
  }

  dropdownBrand() {
    this.enquiryCommonService.dropdownbrand().subscribe(response => {
      // console.log('currentbrands =>', response);
      this.dropDownBrand = response as BaseDto<Array<DropDownBrand>>
    })
  }

  getFormStatusandFormDataWithFormName(event){
    //console.log('event1', event)
      this.submitCurrenMachineDetailsForm.emit({form: 'Current Machine details', event: event})
  }
}
