import { Component, OnInit, Input, EventEmitter, Output, ViewChild } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS, MatSelectChange, MatAutocomplete } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { EnquiryCreationService } from './enquiry-creation.service';
import { EnquiryCode, AutoPopulatebyEnqCode, DropDownFollowupType, DropDownSource, DropDownPurposeOfPurchase, DropDownGenerationActivationType, DropDownConversationActivationType, DropDownRetailConversionActivityType, ViewEnquiryDomain, EnquiryWithProspectMasterDomain, GetEnquiryType, GetActivityNumber, UpdationDate } from 'EnquiryCreation';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { EnquiryService } from '../../enquiry.service';
import { ActivatedRoute } from '@angular/router';
import { Observable, forkJoin } from 'rxjs';
import { DropDownSalePerson } from 'TransferSearchCriteria';
import { BaseDto } from 'BaseDto';
import { DatePipe } from '@angular/common';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';

@Component({
  selector: 'app-enquiry-creation-component',
  templateUrl: './enquiry-creation-component.component.html',
  styleUrls: ['./enquiry-creation-component.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    EnquiryCreationService,
    EnquiryCreationContainerService,
    DatePipe,
    AutocompleteService
  ]

})
export class EnquiryCreationComponent implements OnInit {
  newEnquiryForm: FormGroup;
  isEdit: boolean
  isView: boolean
  isViewMobile: boolean
  display: boolean
  isDisplayField: boolean
  salesPerson: string;
  isgenActivityTypeSelect: boolean
  id: number
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  @Input()
  enquiryCode: BaseDto<Array<EnquiryCode>>
  enquiryList: BaseDto<AutoPopulatebyEnqCode>
  dropdownSalePerson: BaseDto<Array<DropDownSalePerson>>
  dropDownFollowupType: BaseDto<Array<DropDownFollowupType>>
  dropDownSource: BaseDto<Array<DropDownSource>>
  dropDownPurposeOfPurchase: BaseDto<Array<DropDownPurposeOfPurchase>>
  dropDownGenerationActivationType: BaseDto<Array<DropDownGenerationActivationType>>
  dropDownConversationActivationType: BaseDto<Array<DropDownConversationActivationType>>
  dropDownRetailConversionActivityType: BaseDto<Array<DropDownRetailConversionActivityType>>
  getEnquiryType: BaseDto<GetEnquiryType>
  source : string
  retailConversionActivity : string
  @Output() getFormStatusandData = new EventEmitter<object>()
  @Output() viewEnquiryData = new EventEmitter<ViewEnquiryDomain>()
  @Output() viewMobileEnquiryData = new EventEmitter<ViewEnquiryDomain>()
  @Output() enqDateForEnquiryType = new EventEmitter<string>()
  @Output() tantiveDateForEnquiryType = new EventEmitter<string>()
  @Output() autoGenerationActivityNo = new EventEmitter<string>()
  @Output() sourceName = new EventEmitter<string>()
  @Output() activityType = new EventEmitter<string>()
  @Input() generationActivityNumber: BaseDto<Array<GetActivityNumber>>
  conversionActivityNumber: BaseDto<Array<GetActivityNumber>>
  activityProposalId: number
  proposalId: number
  conProposalId: number
  isReferal: boolean;
  cthis = this;
  constructor(
    private enquiryCreationService: EnquiryCreationService,
    private enquiryService: EnquiryService,
    private enquiryCommonService: EnquiryCommonService,
    private enquiryCreationContainerService: EnquiryCreationContainerService,
    private enqRt: ActivatedRoute,
    public datePipe: DatePipe,
    private loginFormService: LoginFormService,
    private autocompleteService: AutocompleteService
  ) {
    this.salesPerson = this.loginFormService.getLoginUser().name
  }

  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.loadAllDropDownData().subscribe(dt => {
      this.dropdownSalePerson = dt[0] as BaseDto<Array<DropDownSalePerson>>
      this.dropDownFollowupType = dt[1] as BaseDto<Array<DropDownFollowupType>>
      this.dropDownSource = dt[2] as BaseDto<Array<DropDownSource>>
      this.dropDownPurposeOfPurchase = dt[3] as BaseDto<Array<DropDownPurposeOfPurchase>>
      this.dropDownGenerationActivationType = dt[4] as BaseDto<Array<DropDownGenerationActivationType>>
      this.dropDownRetailConversionActivityType = dt[5] as BaseDto<Array<DropDownRetailConversionActivityType>>
      this.dropDownConversationActivationType = dt[6] as BaseDto<Array<DropDownConversationActivationType>>
      this.patchOrCreate()
    })
  }
  resetFormControlNotHavingObject = this.autocompleteService.resetFormControlNotHavingObject
  private patchOrCreate() {
    if (this.isView) {
      console.log(`Viewing Form`);
      this.parseIdAndViewForm()
      this.display = true
      this.isDisplayField = true
    } else if (this.isViewMobile) {
      console.log(`viewMobile Form`);
      this.parseIdAndViewMobileForm()
      this.display = true
      this.isDisplayField = false
      this.getUpdationDate()
    } else {
      console.log(`creating Form`);
      this.formForCreateSetup()
      this.display = false
      this.isDisplayField = false
    }
  }

  private formForViewSetup(domain: ViewEnquiryDomain) {
    console.log('domain', domain);
    //this.newEnquiryForm = this.enquiryCreationService.viewnewEnquiryForm()
    if (domain) {
      this.id = domain.id
      this.sourceName.emit(domain.source)
      this.source = domain.source
      this.retailConversionActivity = domain.retailConversionActivity
      this.proposalId = domain.generationActivityNumber ? domain.generationActivityNumber.activityProposalId : null
      this.conProposalId = domain.conversionActivityNumber ? domain.conversionActivityNumber.activityProposalId : null
      this.newEnquiryForm.patchValue(domain)
      this.newEnquiryForm.controls.enquiryDate.patchValue(this.convertToPatchFormat(domain.enquiryDate))
      this.newEnquiryForm.controls.validationDate.patchValue(this.convertToPatchFormat(domain.validationDate))
      this.newEnquiryForm.controls.tentativePurchaseDate.patchValue(this.convertToPatchFormat(domain.tentativePurchaseDate))
      this.newEnquiryForm.controls.nextFollowUpDate.patchValue(this.convertToPatchFormat(domain.nextFollowUpDate))
      let salesIndex = this.dropdownSalePerson.result.findIndex(ele => ele.id === domain.salesPerson.id);
      this.newEnquiryForm.controls.salesPerson.patchValue(this.dropdownSalePerson.result[salesIndex]);
      this.dropDownFollowupType.result.findIndex(res => res.followUpType === domain.followUpType)
      this.newEnquiryForm.controls.followUpType.patchValue(domain.followUpType)
      this.dropDownSource.result.findIndex(res => res.sourceName === domain.source)
      this.newEnquiryForm.controls.source.patchValue(domain.source)
      this.dropDownPurposeOfPurchase.result.findIndex(res => res.purposeOfPurchase === domain.purposeOfPurchase)
      this.newEnquiryForm.controls.purposeOfPurchase.patchValue(domain.purposeOfPurchase)
      this.dropDownGenerationActivationType.result.findIndex(res => res.generationActivityType === domain.generationActivityType)
      this.newEnquiryForm.controls.generationActivityType.patchValue(domain.generationActivityType)
      this.dropDownRetailConversionActivityType.result.findIndex(res => res.retailConversionActiviy === domain.retailConversionActivity)
      this.newEnquiryForm.controls.retailConversionActivity.patchValue(domain.retailConversionActivity)
      this.dropDownConversationActivationType.result.findIndex(res => res.conversionActivityType === domain.conversionActivityType)
      this.newEnquiryForm.controls.conversionActivityType.patchValue(domain.conversionActivityType)
    }
    let tentativeDate = this.convertToPatchFormat(domain.tentativePurchaseDate)
    let enqDate = this.convertToPatchFormat(domain.enquiryDate)
    console.log(`tentativeDate = ${tentativeDate} enqDate = ${enqDate}`);
    
    if (tentativeDate && enqDate) {
      this.enquiryCreationContainerService.setEnquiryType(enqDate, tentativeDate).subscribe(response => {
        console.log('enquiryType', response);
        this.getEnquiryType = response as BaseDto<GetEnquiryType>
        this.newEnquiryForm.controls.enquiryType.patchValue(this.getEnquiryType.result.enquiryType)
      })
    }
  }

  onSelectRetailConversionActivity(event : MatSelectChange) {
    console.log(event)
    this.retailConversionActivity = event.value
    this.enquiryCreationContainerService.searchMarketingActivityNumber(null, 'Conversion', this.retailConversionActivity, this.newEnquiryForm.controls.retailConversionActivity.value).subscribe(response => {
      console.log('Conversion', response);
      this.conversionActivityNumber = response as BaseDto<Array<GetActivityNumber>>
    })
  }

  private formForViewMobileSetup(domain: ViewEnquiryDomain) {
    if (domain) {
      Object.keys(domain).map(key => domain[key] = domain[key] == 'NA' ? null : domain[key])
      console.log("domain ============", domain);
      this.id = domain.id
      console.log('enqForid', this.id);
      this.newEnquiryForm.patchValue(domain)
      this.newEnquiryForm.controls.enquiryDate.patchValue(this.convertToPatchFormat(domain.enquiryDate))
      this.newEnquiryForm.controls.validationDate.patchValue(new Date())
      this.newEnquiryForm.controls.tentativePurchaseDate.patchValue(this.convertToPatchFormat(domain.tentativePurchaseDate))
      this.newEnquiryForm.controls.nextFollowUpDate.patchValue(this.convertToPatchFormat(domain.nextFollowUpDate))
      console.log("this.dropdownSalePerson ", this.dropdownSalePerson);
      let salesIndex = this.dropdownSalePerson.result.findIndex(ele => ele.id === domain.salesPerson.id);
      this.newEnquiryForm.controls.salesPerson.patchValue(this.dropdownSalePerson.result[salesIndex]);
      this.newEnquiryForm.controls.followUpType.patchValue(domain.followUpType)
      this.dropDownSource.result.findIndex(res => res.sourceName === domain.source)
      this.newEnquiryForm.controls.source.patchValue(domain.source)
      this.dropDownPurposeOfPurchase.result.findIndex(res => res.purposeOfPurchase === domain.purposeOfPurchase)
      this.newEnquiryForm.controls.purposeOfPurchase.patchValue(domain.purposeOfPurchase)
      this.dropDownGenerationActivationType.result.findIndex(res => res.generationActivityType === domain.generationActivityType)
      this.newEnquiryForm.controls.generationActivityType.patchValue(domain.generationActivityType)
      this.sourceName.emit(domain.source)
    }
  }
  
  private formForCreateSetup() {
    this.newEnquiryForm = this.enquiryCreationService.createnewEnquiryForm()
    // this.newEnquiryForm.controls.generationActivityNumber.valueChanges.subscribe(value => {
    //   this.autoGenerationActivityNo.emit(value)
    // })
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let enqDetails: EnquiryWithProspectMasterDomain = this.newEnquiryForm.getRawValue()
        console.log(enqDetails)
        enqDetails.enquiryDate = this.convertDateToServerFormat(enqDetails.enquiryDate)
        enqDetails.validationDate = this.convertDateToServerFormat(enqDetails.validationDate)
        enqDetails.tentativePurchaseDate = this.convertDateToServerFormat(enqDetails.tentativePurchaseDate)
        enqDetails.nextFollowUpDate = this.convertDateToServerFormat(enqDetails.nextFollowUpDate)
        this.getFormStatusandData.emit({ validStatus: this.newEnquiryForm.valid, formData: enqDetails });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.newEnquiryForm.reset();
      }
    })
  }

  selectionGenerationActivityType(value) {
    console.log(value);
    if (value === 'KAI Supported') {
      this.newEnquiryForm.controls.generationActivityNumber.setValidators(Validators.compose([Validators.required]))
      this.newEnquiryForm.controls.generationActivityNumber.updateValueAndValidity();
    } else {
      this.newEnquiryForm.controls.generationActivityNumber.clearValidators()
      this.newEnquiryForm.controls.generationActivityNumber.updateValueAndValidity();
    }
    this.activityType.emit(value)
  }

  selectionConverstionActivityType(event : MatSelectChange) {
    console.log(event)

    if (event.value === 'KAI Supported') {
      this.newEnquiryForm.controls.conversionActivityNumber.setValidators(Validators.compose([Validators.required]))
      this.newEnquiryForm.controls.conversionActivityNumber.updateValueAndValidity();

      this.newEnquiryForm.controls.retailConversionActivity.setValidators(Validators.compose([Validators.required]))
      this.newEnquiryForm.controls.retailConversionActivity.updateValueAndValidity();
    } else {
      this.newEnquiryForm.controls.conversionActivityNumber.clearValidators()
      this.newEnquiryForm.controls.conversionActivityNumber.updateValueAndValidity();

      this.newEnquiryForm.controls.retailConversionActivity.clearValidators()
      this.newEnquiryForm.controls.retailConversionActivity.updateValueAndValidity();
    }
  }
  selectSource(event) {
    console.log('event', event)
    this.sourceName.emit(event.value)

    this.source = event.value
    if (event.value == "REFERRAL") {
      this.isReferal = true
    } else {
      this.isReferal = false
    }
  }
  keyPress(event: any) {
    this.enquiryCreationService.keyPress(event)
  }

  displayFn(actNum: GetActivityNumber) {
    // console.log(actNum);
    return actNum ? actNum.activityNumber : undefined
  }

  private markFormAsTouched() {
    for (const key in this.newEnquiryForm.controls) {
      if (this.newEnquiryForm.controls.hasOwnProperty(key)) {
        this.newEnquiryForm.controls[key].markAsTouched();
      }
    }
  }

  private parseIdAndViewForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enqNo']))
    this.newEnquiryForm = this.enquiryCreationService.viewnewEnquiryForm()
    // this.newEnquiryForm.controls.generationActivityNumber.valueChanges.subscribe(value => {
    //   this.autoGenerationActivityNo.emit(value)
    // })
    // this.newEnquiryForm.controls.conversionActivityNumber.valueChanges.subscribe(value => {
    //   this.autoComConversionActivityNo(value)
    // })
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'update') {
        let enqDatas: EnquiryWithProspectMasterDomain = this.newEnquiryForm.getRawValue()
        enqDatas.enquiryDate = this.convertDateToServerFormat(enqDatas.enquiryDate)
        enqDatas.validationDate = this.convertDateToServerFormat(enqDatas.validationDate)
        enqDatas.tentativePurchaseDate = this.convertDateToServerFormat(enqDatas.tentativePurchaseDate)
        enqDatas.nextFollowUpDate = this.convertDateToServerFormat(enqDatas.nextFollowUpDate)
        this.getFormStatusandData.emit({ validStatus: this.newEnquiryForm.valid, formData: enqDatas, id: this.id, proposalId: this.proposalId, conProposalId: this.conProposalId });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.newEnquiryForm.reset();
      }
    })
  }

  displayFnCon(actNo: GetActivityNumber) {
    console.log(actNo);
    return actNo ? actNo.activityNumber : undefined
  }

  autoComConversionActivityNo(value) {
    // this.enquiryCreationContainerService.searchMarketingActivityNumber(value, 'Conversion', this.newEnquiryForm.controls.retailConversionActivity.value).subscribe(response => {
    //   console.log('Conversion', response);
    //   this.conversionActivityNumber = response as BaseDto<Array<GetActivityNumber>>
    // })
  }

  private parseIdAndViewMobileForm() {
    this.enqRt.params.subscribe(prms => this.fatchDataForViewMobileEnqNo(prms['mobenqNo']))
    // this.newEnquiryForm.controls.generationActivityNumber.valueChanges.subscribe(value => {
    //   this.autoGenerationActivityNo.emit(value)
    // })
    this.enquiryService.submitOrResetEnquiryFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let enqData: EnquiryWithProspectMasterDomain = this.newEnquiryForm.getRawValue()
        enqData.enquiryDate = this.convertDateToServerFormat(enqData.enquiryDate)
        enqData.validationDate = this.convertDateToServerFormat(enqData.validationDate)
        enqData.tentativePurchaseDate = this.convertDateToServerFormat(enqData.tentativePurchaseDate)
        enqData.nextFollowUpDate = this.convertDateToServerFormat(enqData.nextFollowUpDate)
        this.getFormStatusandData.emit({ validStatus: this.newEnquiryForm.valid, formData: enqData, id: this.id });
        this.markFormAsTouched();
      } else if (value.toLowerCase() === 'clear') {
        this.newEnquiryForm.reset();
      }
    })
  }
  private fatchDataForEnqNo(enqNo: string) {
    console.log('enqNo ==>', enqNo)
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + enqNo).subscribe(dto => {
      this.formForViewSetup(dto.result)
      this.viewEnquiryData.emit(dto.result)
    })
  }

  selectionDate(event) {
    //console.log(event.value);  
    let tentativeDate = this.datePipe.transform(this.newEnquiryForm.controls.tentativePurchaseDate.value, "yyyy-MM-dd")
    let enqDate = this.datePipe.transform(this.newEnquiryForm.controls.enquiryDate.value, "yyyy-MM-dd")
    console.log(`tentativeDate = ${tentativeDate} enqDate = ${enqDate}`);
    if (tentativeDate && enqDate) {
      this.enquiryCreationContainerService.setEnquiryType(enqDate, tentativeDate).subscribe(response => {
        console.log('enquiryType', response);
        this.getEnquiryType = response as BaseDto<GetEnquiryType>
        this.newEnquiryForm.controls.enquiryType.patchValue(this.getEnquiryType.result.enquiryType)
      })
    }
  }

  private fatchDataForViewMobileEnqNo(mobenqNo: string) {
    console.log('mobenqNo ==>', mobenqNo)
    this.enquiryCommonService.getEnquiryByeEnquiryNumber(`` + mobenqNo).subscribe(dto => { this.formForViewMobileSetup(dto.result) })
  }

  getUpdationDate(){
    this.enquiryCreationContainerService.systemGeneratedDate().subscribe(response => {
      console.log(response);
      let updationDate = response as BaseDto<UpdationDate>
      this.newEnquiryForm.controls.updationDate.patchValue(updationDate.result)
    })
  }


  private checkOperationType() {
    console.log(this.enqRt.snapshot.routeConfig.path);
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isViewMobile = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'viewMobile'
    console.log(`viewMobile = ${this.isViewMobile} view = ${this.isView}`);
  }

  private intiOperationForm() {
    if (this.isView) {
      this.newEnquiryForm = this.enquiryCreationService.viewnewEnquiryForm()
    } else if (this.isViewMobile) {
      this.newEnquiryForm = this.enquiryCreationService.viewMobilenewEnquiryForm()
    }
    else {
      this.newEnquiryForm = this.enquiryCreationService.createnewEnquiryForm()
    }
  }

  private loadAllDropDownData(): Observable<BaseDto<Array<Object>>> {
    let dropDownTask = [];
    dropDownTask.push(this.enquiryCommonService.getAllSalesPerson())
    dropDownTask.push(this.enquiryCommonService.dropdownfollowupType())
    dropDownTask.push(this.enquiryCommonService.dropdownSource())
    dropDownTask.push(this.enquiryCreationContainerService.dropdownPurposeOfPurchase())
    dropDownTask.push(this.enquiryCreationContainerService.dropdowngenerationActivityType())
    dropDownTask.push(this.enquiryCommonService.dropdownretailconversionActivityType())
    dropDownTask.push(this.enquiryCreationContainerService.dropdownconversionActivityType())
    return forkJoin(...dropDownTask)
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
    return null
  }
}