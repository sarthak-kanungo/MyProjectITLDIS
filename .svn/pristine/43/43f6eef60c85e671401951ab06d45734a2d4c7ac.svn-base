import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AddEnquiryFollowupService } from './add-enquiry-followup.service';
import { CurrentEnquiryFollowupService } from '../current-enquiry-followup/current-enquiry-followup.service';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { EnquiryFollowupService } from '../../enquiry-followup.service';
import { ActivatedRoute } from '@angular/router';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { EnquiryFollowUpDomain } from 'AddEnquiryFollowup';

@Component({
  selector: 'app-add-enquiry-followup',
  templateUrl: './add-enquiry-followup.component.html',
  styleUrls: ['./add-enquiry-followup.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
     AddEnquiryFollowupService, CurrentEnquiryFollowupService,EnquiryCommonService]
})

export class AddEnquiryFollowupComponent implements OnInit {
   isAddFolloUp : boolean
  enquiryFolloeUpsForm: FormGroup;
  id: number
  selectOptions: string[] =[
    'Follow Up', 'Lost/Drop'
  ]
  @Output() getFormStatusAndData = new EventEmitter<object>()
 @Output() SelectForDisplayCard = new EventEmitter<string>()
  constructor(
    private addEnquiryFollowupService : AddEnquiryFollowupService,
     private enquiryFollowupService : EnquiryFollowupService,
     private enqRt: ActivatedRoute,
     private enquiryCommonService : EnquiryCommonService
    ) { }
   
  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.addFollowUp()
  }

  private addFollowUp() {
    if (this.isAddFolloUp) {
      console.log('AddFollowUp Form');
      this.parseEnqNoAndFollowUpForm()
    }
  }

  private parseEnqNoAndFollowUpForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enq']))
      this.enquiryFolloeUpsForm = this.addEnquiryFollowupService.createenquiryFolloeUpsForm()
    this.enquiryFollowupService.submitOrResetEnquiryFollowupSubscribe((value: string) => {
      if(value.toLowerCase() === 'submit'){
        let addFollowup : EnquiryFollowUpDomain = this.enquiryFolloeUpsForm.getRawValue()
        addFollowup.enquiryDate = this.convertDateToOurFormat( addFollowup.enquiryDate)
       this.getFormStatusAndData.emit({formData : addFollowup, id: this.id});
      // this.markFormAsTouched();
      }else if (value.toLowerCase() === 'clear') {
        this.enquiryFolloeUpsForm.reset();
      }

    })
  }

  private formForAddFollowUp(domain: ViewEnquiryDomain) {
    console.log('domain', domain);
    if(domain){
      console.log('id',domain.id);
      this.id = domain.id
     this.enquiryFolloeUpsForm.patchValue(domain)
     this.enquiryFolloeUpsForm.controls.enquiryDate.patchValue(this.convertToPatchFormat(domain.enquiryDate))
    }
  }
  private fatchDataForEnqNo(enq: string) {
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enq).subscribe(dto => { this.formForAddFollowUp(dto.result) })
  }

  selection(value){
    console.log(value);
    this.SelectForDisplayCard.emit(value)
  }

  private checkOperationType() {
    this.isAddFolloUp = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'followup'
    console.log(`followup = ${this.isAddFolloUp}`);
  }

  private intiOperationForm() {
    if (this.isAddFolloUp) {
      this.enquiryFolloeUpsForm = this.addEnquiryFollowupService.createenquiryFolloeUpsForm()
    }
  }
  private convertDateToOurFormat(dt: string) {
    if (dt) {
      return new Date(dt).toJSON().slice(0, 10).split('-').reverse().join('-')
    }
    return ''
  }
  private convertToPatchFormat(dt: string) {
    if (dt) {
      return dt.split('-').reverse().join('-')
    }
    return ''
  }

}
