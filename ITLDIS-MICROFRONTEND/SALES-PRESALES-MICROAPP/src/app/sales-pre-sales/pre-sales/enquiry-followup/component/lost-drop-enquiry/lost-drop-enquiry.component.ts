import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { LostDropEnquiryService } from './lost-drop-enquiry.service';
import { DropDownBrand } from 'CurrentMachineDetails';
import { DropDownResult, DropDownLostDrop, DropDownReason } from 'LostDropEnquiry';
import { EnquiryFollowupService } from '../../enquiry-followup.service';
import { BaseDto } from 'BaseDto';

@Component({
  selector: 'app-lost-drop-enquiry',
  templateUrl: './lost-drop-enquiry.component.html',
  styleUrls: ['./lost-drop-enquiry.component.scss'],
  providers: [LostDropEnquiryService]
})

export class LostDropEnquiryComponent implements OnInit {
  resultEnquiryForm: FormGroup;
  showLostDrop : boolean
  selected : any
 @Input() dropDownResult : BaseDto<Array<DropDownResult>>
 @Input() dropDownLostDrop : BaseDto<Array<DropDownLostDrop>>
 @Input() dropDownBrand: BaseDto<Array<DropDownBrand>>
 @Input() dropDownReason : BaseDto<Array<DropDownReason>>
 @Output() getLostDropFormStatusAndData = new EventEmitter<object>()
 @Output() selectResultValue = new EventEmitter<string>()
  constructor(
    public dialog: MatDialog,
    private lostDropEnquiryService : LostDropEnquiryService,
    private enquiryFollowupService : EnquiryFollowupService
    ) { }

  ngOnInit() {
    this.resultEnquiryForm = this.lostDropEnquiryService.createresultEnquiryForm()
    this.enquiryFollowupService.submitOrResetEnquiryFollowupSubscribe((value: string) => {
      if(value.toLowerCase() === 'submit'){
       this.getLostDropFormStatusAndData.emit({validStatus : this.resultEnquiryForm.valid, formData : this.resultEnquiryForm.value});
      this.markFormAsTouched();
      }else if (value.toLowerCase() === 'clear') {
        this.resultEnquiryForm.reset();
      }
    })
  }

  selectionResult(value){
    if(value === 'Drop'){
      this.showLostDrop = false
      this.resultEnquiryForm.controls.lostDrop.reset()
    }
    this.selectResultValue.emit(value)
  }

  selection(value) {
    if (value === 'Lost to competition') {
      this.showLostDrop = !this.showLostDrop
      this.resultEnquiryForm.controls.brand.setValidators(Validators.compose([Validators.required]))
      this.resultEnquiryForm.controls.brand.updateValueAndValidity();
      this.resultEnquiryForm.controls.model.setValidators(Validators.compose([Validators.required]))
      this.resultEnquiryForm.controls.model.updateValueAndValidity();
      this.resultEnquiryForm.controls.reason.setValidators(Validators.compose([Validators.required]))
      this.resultEnquiryForm.controls.reason.updateValueAndValidity();
      this.resultEnquiryForm.controls.brand.reset()
      this.resultEnquiryForm.controls.model.reset()
      this.resultEnquiryForm.controls.reason.reset()
    } else {
      this.showLostDrop = false
      this.resultEnquiryForm.controls.brand.clearValidators()
      this.resultEnquiryForm.controls.model.clearValidators()
      this.resultEnquiryForm.controls.reason.clearValidators()
      this.resultEnquiryForm.controls.brand.reset()
      this.resultEnquiryForm.controls.model.reset()
      this.resultEnquiryForm.controls.reason.reset()
    }
  }

  private markFormAsTouched() {
    for (const key in this.resultEnquiryForm.controls) {
      if (this.resultEnquiryForm.controls.hasOwnProperty(key)) {
        this.resultEnquiryForm.controls[key].markAsTouched();
      }
    }
  }

}
