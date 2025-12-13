import { ActivityProposalService } from './../../activity-proposal.service';
import { Component, OnInit, Input, OnChanges, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ViewEditActivityProposalDomain, AutoEnquiryNo, EnquiryDetailDomain, EnquiryDetailsForConversion } from 'ActivityProposalModule';
import { ActivatedRoute } from '@angular/router';
import { EnquiryDetailsProposalService } from './enquiry-details-proposal.service';
import { BaseDto } from 'BaseDto';
import { MatSelectChange } from '@angular/material';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
@Component({
  selector: 'app-enquiry-details-proposal',
  templateUrl: './enquiry-details-proposal.component.html',
  styleUrls: ['./enquiry-details-proposal.component.scss'],
  providers: [EnquiryDetailsProposalService,EnquiryCommonService]
})
export class EnquiryDetailsProposalComponent implements OnInit, OnChanges {

  isActivityPurposeConversion: boolean
  enquiryDetailsForm: FormGroup
  isView: boolean
  isEdit: boolean
  activityType: string
  enquiryNo: BaseDto<Array<AutoEnquiryNo>>
  enquiryData: BaseDto<EnquiryDetailDomain>
  @Output() getFormStatusandData = new EventEmitter<object>()
  @Input() viewEditActivityProposal: ViewEditActivityProposalDomain

  constructor(
    private fb: FormBuilder,
    private toastr: ToastrService,
    private actRt: ActivatedRoute,
    private enquiryDetailsProposalService: EnquiryDetailsProposalService,
    private activityProposalService: ActivityProposalService,
    private enquiryCommonService : EnquiryCommonService
  ) { }

  ngOnChanges() {
    if (this.viewEditActivityProposal) {
      if (this.viewEditActivityProposal.activityProposalHeaderData.activityPurpose === 'Conversion') {
        this.isActivityPurposeConversion = true
      }
      const enquiries = []
      this.viewEditActivityProposal.activityProposalEnquiries.forEach(element => {
        enquiries.push({
          enquiryNumber: { enquiryNumber: element.enquiryNumber },
          firstName: element.firstName,
          mobileNumber: element.mobileNumber,
          city: element.city,
          model: element.model,
          tehsil: element.tehsil,
          enquiryType: element.enquiryType,
          id: element.id
        })
      })
      enquiries.forEach(data => {
        console.log("data ", data);
        this.addRow(data)
      })
    }
  }

  ngOnInit() {
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
  }

  private patchOrCreate() {
    if (this.isView) {
    } else {
      this.addRow()
      this.formForCreateSetup()
    }
  }

  private createEnquiryDetailsForm() {
    this.enquiryDetailsForm = this.fb.group({
      enquiryDetailsFormArray: this.fb.array([])
    });
  }
  private viewEnquiryDetailsForm() {
    this.enquiryDetailsForm = this.fb.group({
      enquiryDetailsFormArray: this.fb.array([])
    })
  }

  formForCreateSetup() {
    this.activityProposalService.activityProposeEvent.subscribe((event : MatSelectChange) => {
      console.log("event ", event);
      if (event.value.purpose === 'Conversion') {
        this.isActivityPurposeConversion = true
      }else{
          this.isActivityPurposeConversion = false
      }
    })
    //this.getEnquiryNumber()
    this.activityProposalService.submitOrResetActivityFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        this.markFormAsTouchedOfEnquiryDetails()
        console.log(this.enquiryDetailsForm.getRawValue().enquiryDetailsFormArray);
        
        this.getFormStatusandData.emit({ validStatus: this.enquiryDetailsForm.valid, formData: this.enquiryDetailsForm.getRawValue().enquiryDetailsFormArray, form: 'enquiryDetails' });
      } else if (value.toLowerCase() === 'clear') {
        this.clearEnquiryDetails()
      }
    })
  }
  private createEnquiryDetailsRow() {
    return this.fb.group({
      isSelected: this.fb.control(false),
      enquiryNumber: this.fb.control(null, Validators.required),
      firstName: this.fb.control({ value: null, disabled: true }),
      mobileNumber: this.fb.control({ value: null, disabled: true }),
      city: this.fb.control({ value: null, disabled: true }),
      tehsil: this.fb.control({ value: null, disabled: true }),
      model: this.fb.control({ value: null, disabled: true }),
      enquiryType: this.fb.control({ value: null, disabled: true })
    })
  }

  private viewEnquiryDetailsRow(product: EnquiryDetailsForConversion) {
    return this.fb.group({
      isSelected: this.fb.control({ value: false, disabled: true }),
      id: this.fb.control(null),
      enquiryNumber: this.fb.control({ value: product.enquiryNumber, disabled: true }),
      firstName: this.fb.control({ value: product.firstName, disabled: true }),
      mobileNumber: this.fb.control({ value: product.mobileNumber, disabled: true }),
      city: this.fb.control({ value: product.city, disabled: true }),
      tehsil: this.fb.control({ value: product.tehsil, disabled: true }),
      model: this.fb.control({ value: product.model, disabled: true }),
      enquiryType: this.fb.control({ value: product.enquiryType, disabled: true })
    })
  }

  getEnquiryNumber(enqNo) {
    console.log("enquiryDetailsForm----->",this.enquiryDetailsForm.getRawValue())
          let valueChange = enqNo.target.value;
          this.enquiryCommonService.searchEnquiryCode(valueChange, "ACT_PROPOSAL").subscribe(response => {
            this.enquiryNo = response as BaseDto<Array<AutoEnquiryNo>>
          })     
                 
          console.log("enquiryNo----->", this.enquiryNo);
        }
      // element.get('enquiryNumber').valueChanges.subscribe(valueChange => {      
      //   if (valueChange) {
      //     const enquiryNo = typeof valueChange == 'object' ? valueChange.enquiryNumber : valueChange
      //     this.autoEnquiryNumber(enquiryNo);          
      //   }
      // })
    
        

  // autoEnquiryNumber(enquiryNo) {
  //   /*this.enquiryDetailsProposalService.getEnquiryNumber(enquiryNo).subscribe(response => {
  //     if (response) {
  //       this.enquiryNo = response as BaseDto<Array<AutoEnquiryNo>>
  //     }else{
  //         this.enquiryNo = null;
  //     }
  //   })*/
  //     this.enquiryCommonService.searchEnquiryCode(enquiryNo, "ACT_PROPOSAL").subscribe(response => {
  //     this.enquiryNo = response as BaseDto<Array<AutoEnquiryNo>>
  //   })     

  // }

  displayEnquiryNoFn(value: AutoEnquiryNo) {
    return value ? value.enquiryNumber : undefined
  }

  enquiryNoSelection(event, index) {
    const enquiryDetails = this.enquiryDetailsForm.controls.enquiryDetailsFormArray as FormArray;
    const enquiryNo = enquiryDetails.value[index].enquiryNumber
    let row:number = 0;
    let duplicateRecord : boolean = false;
    enquiryDetails.controls.forEach((value: FormGroup) => {
        if (value.get('enquiryNumber').value!=undefined && value.get('enquiryNumber').value.enquiryNumber === enquiryNo.enquiryNumber && row < index) {
            duplicateRecord = true;
            return;
        }
        row++;
    })
    if(duplicateRecord){
        enquiryDetails.controls[index].get('enquiryNumber').reset();
        enquiryDetails.controls[index].reset();
        this.toastr.error('Enquiry can not be duplicate');
        return;
    }
    enquiryDetails.controls.forEach((value: FormGroup) => {
      if (value.get('enquiryNumber').value === enquiryNo) {
        this.enquiryDetailsProposalService.getEnquiryDetailsByEnquiryNo(event.option.value.id).subscribe(res => {
          console.log('Data----', res);
          this.enquiryData = res as BaseDto<EnquiryDetailDomain>
          value.patchValue(this.enquiryData.result)
          value.get('enquiryNumber').patchValue({enquiryNumber : this.enquiryData.result.enquiryNumber, id: event.option.value.id})
          value.get('enquiryNumber').disable();
        })        
      }
    })
    this.enquiryNo = null;  
  }

  addRow(product?: EnquiryDetailsForConversion) {
    let enquiryDetails = this.enquiryDetailsForm.controls.enquiryDetailsFormArray as FormArray
    if (this.isView) {
      enquiryDetails.push(this.viewEnquiryDetailsRow(product))
    } else {
      enquiryDetails.push(this.createEnquiryDetailsRow())
     // this.getEnquiryNumber()
    }
  }

  deleteRow() {
    let enquiryDetails = this.enquiryDetailsForm.controls.enquiryDetailsFormArray as FormArray;
    let nonSelected = enquiryDetails.controls.filter(machinery => !machinery.value.isSelected)
    if ((enquiryDetails.length - nonSelected.length)) {
      enquiryDetails.clear()
      nonSelected.forEach(el => enquiryDetails.push(el))
    } else {
      this.toastr.warning('Select Atleast One Row', 'Enquiry Details')
    }
  }

  private markFormAsTouchedOfEnquiryDetails() {
    let enquiryDetails = this.enquiryDetailsArray()
    enquiryDetails.markAllAsTouched()
  }

  enquiryDetailsArray() {
    return this.enquiryDetailsForm.controls.enquiryDetailsFormArray as FormArray;
  }

  private clearEnquiryDetails() {
    this.enquiryDetailsForm.reset();
    this.enquiryDetailsArray().controls.forEach(row => {
      row['controls'].isSelected.patchValue(true)
      console.log(row)
    })
    this.deleteRow()
    this.addRow()
  }
  private initOperationForm() {
    if (this.isView) {
      this.viewEnquiryDetailsForm()
    } else {
      this.createEnquiryDetailsForm()
    }
  }

  private checkOperationType() {
    console.log(this.actRt.snapshot.routeConfig.path)
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    console.log(`View = ${this.isView}`)
  }
}