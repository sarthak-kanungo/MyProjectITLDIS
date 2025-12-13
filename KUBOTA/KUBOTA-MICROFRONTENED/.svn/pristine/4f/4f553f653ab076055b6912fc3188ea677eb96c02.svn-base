import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { CurrentEnquiryFollowupService } from './current-enquiry-followup.service';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DropDownFollowupType, ViewEnquiryDomain } from 'EnquiryCreation';
import { EnquiryFollowupService } from '../../enquiry-followup.service';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { CurrentEnquiryFollowupDomain } from 'CurrentEnquiryFolloeup';
import { BaseDto } from 'BaseDto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-current-enquiry-followup',
  templateUrl: './current-enquiry-followup.component.html',
  styleUrls: ['./current-enquiry-followup.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    EnquiryCommonService, CurrentEnquiryFollowupService]
})

export class CurrentEnquiryFollowupComponent implements OnInit {
  currentFolloeUpsForm: FormGroup;
  isAddFolloUp: boolean
  enquiryDate: string
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  
  currentFollowupDate:string
  minNextFollowupDate:string

  @Input() dropDownFollowupType: BaseDto<Array<DropDownFollowupType>>
  @Output() getCurrentFollwupFormStatusAndData = new EventEmitter<object>()
  constructor(
    public dialog: MatDialog,
    private currentEnquiryFollowupService: CurrentEnquiryFollowupService,
    private enquiryFollowupService: EnquiryFollowupService,
    private enqRt: ActivatedRoute,
    private enquiryCommonService: EnquiryCommonService
  ) { }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.checkOperationType()
    this.intiOperationForm()
    this.addFollowUp()
    this.currentFolloeUpsForm.controls.currentFollowUpDate.valueChanges.subscribe(value => {
        this.minNextFollowupDate = value;
    })
    console.log("this.currentFolloeUpsForm--->", this.currentFolloeUpsForm)
  }

  private addFollowUp() {
    if (this.isAddFolloUp) {
      console.log('AddFollowUp Form');
      this.parseEnqNoAndFollowUpForm()
    }
  }

  private parseEnqNoAndFollowUpForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enq']))
    this.currentFolloeUpsForm = this.currentEnquiryFollowupService.createcurrentFolloeUpsForm()
    this.enquiryFollowupService.submitOrResetEnquiryFollowupSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let curFollowup: CurrentEnquiryFollowupDomain = this.currentFolloeUpsForm.getRawValue()
        curFollowup.currentFollowUpDate = this.convertDateToServerFormat(curFollowup.currentFollowUpDate)
        curFollowup.nextFollowUpDate = this.convertDateToServerFormat(curFollowup.nextFollowUpDate)
        curFollowup.tentativePurchaseDate = this.convertDateToServerFormat(curFollowup.tentativePurchaseDate)
        curFollowup.updationDate = this.convertDateToServerFormat(curFollowup.updationDate)
        console.log("this.currentFolloeUpsForm.valid--->", this.currentFolloeUpsForm.valid)
        if (!this.currentFolloeUpsForm.valid ) {
          if(curFollowup.followUpType === undefined || curFollowup.followUpType === null || curFollowup.followUpType === ''){
          this.currentFolloeUpsForm.get('followUpType').setErrors({
            error : 'Follow Up Type is Required'
          })
          
        }else{
          this.currentFolloeUpsForm.get('followUpType').setErrors(null);
        }
        if(curFollowup.nextFollowUpDate === undefined || curFollowup.nextFollowUpDate === null || curFollowup.nextFollowUpDate === ''){
          this.currentFolloeUpsForm.get('nextFollowUpDate').setErrors({
            error : 'Next Follow Up Date is Required'
          })
        
        }else{
          this.currentFolloeUpsForm.get('nextFollowUpDate').setErrors(null);
        }if(curFollowup.remarks == undefined || curFollowup.remarks == null || curFollowup.remarks == ''){
          console.log("curFollowup.Remarks--->", this.currentFolloeUpsForm)
          this.currentFolloeUpsForm.get('remarks').setErrors({
            error : 'Remarks is Required'
          })
        
        }else{
          this.currentFolloeUpsForm.get('remarks').setErrors(null);
        }
        }
        this.getCurrentFollwupFormStatusAndData.emit({ validStatus: this.currentFolloeUpsForm.valid, formData: curFollowup });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.currentFolloeUpsForm.reset();
      }
    })
  }
  private formForAddFollowUp(domain: ViewEnquiryDomain) {
    console.log('domain', domain);
    if (domain) {
      this.enquiryDate = this.convertToPatchFormat(domain.enquiryDate)
      this.currentFollowupDate = domain.currentFollowUpDate;
      this.minNextFollowupDate = domain.currentFollowUpDate;
      this.currentFolloeUpsForm.controls.tentativePurchaseDate.patchValue(this.convertToPatchFormat(domain.tentativePurchaseDate))
    }
  }

  private fatchDataForEnqNo(enq: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enq).subscribe(dto => { this.formForAddFollowUp(dto.result) })
  }


  private markFormAsTouched() {
    for (const key in this.currentFolloeUpsForm.controls) {
      if (this.currentFolloeUpsForm.controls.hasOwnProperty(key)) {
        this.currentFolloeUpsForm.controls[key].markAsTouched();
      }
    }
  }

  private checkOperationType() {
    this.isAddFolloUp = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'followup'
    console.log(`followup = ${this.isAddFolloUp}`);
  }
  private intiOperationForm() {
    if (this.isAddFolloUp) {
      this.currentFolloeUpsForm = this.currentEnquiryFollowupService.createcurrentFolloeUpsForm()
    }
  }

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear()
      console.log(formattedDate)
      return formattedDate
    }
    return null
  }

  private convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return ''
  }
}