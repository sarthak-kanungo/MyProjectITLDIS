import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { KubotaPatterns } from '../../../../../utils/kubota-patterns';
import { FollowUpType, EnquirySource, RetailConversionActivityType, PurposeOfPurchase, ActivationType, SalePerson, ActivityNumber, ViewEnquiry, GenerationActivationType } from '../../domains/enquiry';
import { Observable } from 'rxjs';
import { GeneralInfoWebService } from '../../services/general-info-web.service';
import { DatePipe } from '@angular/common';
import { MatDatepickerInputEvent, MatSelectChange, MatAutocompleteSelectedEvent } from '@angular/material';
import { DateFormat } from '../../../../../utils/date-format-util';
import { Operation } from '../../../../../utils/operation-util';

@Component({
  selector: 'app-general-info',
  templateUrl: './general-info.component.html',
  styleUrls: ['./general-info.component.scss'],
  providers: [GeneralInfoWebService, DatePipe]
})
export class GeneralInfoComponent implements OnInit, OnChanges {

  generalInfo: FormGroup
  followupTypes: Array<FollowUpType> = []
  source: Array<EnquirySource> = []
  retailConversionActivityType: Array<RetailConversionActivityType>
  purposeOfPurchase: Array<PurposeOfPurchase>
  activationType: Array<ActivationType>
  generationActivationType: Array<GenerationActivationType>
  salesPersons: Array<SalePerson> = []
  activityNumber$: Observable<Array<ActivityNumber>>
  conversionactivityNumber$: Observable<Array<ActivityNumber>>
  sourceName: string
  conversionSourceName : string
  activityType: string =''
  conversionActivityType:string = ''
  isShowForViewAndViewMobile: boolean
  isShowForView: boolean
  isReferal: boolean
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  @Input() enquiryByEnquiryNumber: ViewEnquiry
  constructor(
    private presenter: EnquiryPresenter,
    private kubotaPatterns: KubotaPatterns,
    private generalInfoWebService: GeneralInfoWebService,
    public datePipe: DatePipe,
    private dateFormat: DateFormat
  ) { }

  ngOnChanges() {
    if (this.enquiryByEnquiryNumber) {
      if (this.presenter.operation === Operation.VIEW_MOBILE) {
        Object.keys(this.enquiryByEnquiryNumber).map(key => this.enquiryByEnquiryNumber[key] = this.enquiryByEnquiryNumber[key] == 'NA' ? null : this.enquiryByEnquiryNumber[key])
      }
      this.generalInfo.patchValue(this.enquiryByEnquiryNumber)
      if (this.presenter.operation === Operation.VIEW) {
        this.generalInfo.get('validationDate').patchValue(this.dateFormat.convertToPatchFormat(this.enquiryByEnquiryNumber.validationDate))
      }
      this.activityType = this.enquiryByEnquiryNumber.generationActivityType;
      this.conversionActivityType = this.enquiryByEnquiryNumber.conversionActivityType;
      this.conversionSourceName = this.enquiryByEnquiryNumber.retailConversionActivity;
      this.sourceName=this.enquiryByEnquiryNumber.source;
      
      this.generalInfo.get('source').patchValue({source : this.enquiryByEnquiryNumber.source})
      
      if(this.enquiryByEnquiryNumber.generationActivityNumber!=null && this.enquiryByEnquiryNumber.generationActivityNumber.activityNumber != ''){
          this.generalInfo.get('generationActivityNumber').disable();
          this.generalInfo.get('generationActivityType').disable();
      }
      this.activityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Generation', this.sourceName,this.enquiryByEnquiryNumber.generationActivityType, '');
      
      if(this.enquiryByEnquiryNumber.conversionActivityNumber!=null && this.enquiryByEnquiryNumber.conversionActivityNumber.activityNumber != ''){
          this.generalInfo.get('conversionActivityNumber').disable();
          this.generalInfo.get('conversionActivityType').disable();
          this.generalInfo.get('retailConversionActivity').disable();
      }
      this.conversionactivityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Conversion', this.conversionSourceName,this.enquiryByEnquiryNumber.conversionActivityType, this.enquiryByEnquiryNumber.enquiryNumber)
      
      this.generalInfo.get('generationActivityNumber').patchValue(this.enquiryByEnquiryNumber.generationActivityNumber ? this.enquiryByEnquiryNumber.generationActivityNumber : null)
      this.generalInfo.get('followUpType').patchValue(this.enquiryByEnquiryNumber.followUpType ? { followUpType: this.enquiryByEnquiryNumber.followUpType } : null)
      this.generalInfo.get('purposeOfPurchase').patchValue(this.enquiryByEnquiryNumber.purposeOfPurchase ? { purposeOfPurchase: this.enquiryByEnquiryNumber.purposeOfPurchase } : null)
      this.generalInfo.get('generationActivityType').patchValue({ generationActivityType: this.enquiryByEnquiryNumber.generationActivityType })
      this.generalInfo.get('enquiryDate').patchValue(this.dateFormat.convertToPatchFormat(this.enquiryByEnquiryNumber.enquiryDate))
      this.generalInfo.get('nextFollowUpDate').patchValue(this.dateFormat.convertToPatchFormat(this.enquiryByEnquiryNumber.nextFollowUpDate))
      this.generalInfo.get('tentativePurchaseDate').patchValue(this.dateFormat.convertToPatchFormat(this.enquiryByEnquiryNumber.tentativePurchaseDate))
      this.generalInfo.get('generationActivityNumber').setErrors(null);
      this.generalInfo.get('salesPerson').patchValue(this.enquiryByEnquiryNumber.salesPerson)

      this.patchValueForView()
      this.patchValueForViewApp()
    }
  }

  patchValueForView() {
    if (this.enquiryByEnquiryNumber.conversionActivityType) {
      this.generalInfo.get('conversionActivityType').patchValue({ conversionActivityType: this.enquiryByEnquiryNumber.conversionActivityType })
    }
    if (this.enquiryByEnquiryNumber.retailConversionActivity) {
      this.generalInfo.get('retailConversionActivity').patchValue({ retailConversionActivity: this.enquiryByEnquiryNumber.retailConversionActivity })
    }
    if (this.enquiryByEnquiryNumber.conversionActivityNumber) {
      this.generalInfo.get('conversionActivityNumber').patchValue({ activityNumber:this.enquiryByEnquiryNumber.conversionActivityNumber.activityNumber,activityProposalId:this.enquiryByEnquiryNumber.conversionActivityNumber.activityProposalId})
      this.generalInfo.get('conversionActivityNumber').setErrors(null);
    }
    
  }

  patchValueForViewApp() {
    if (this.presenter.operation === Operation.VIEW_MOBILE) {
      this.generalInfo.get('validationDate').patchValue(new Date())
      console.log(this.generalInfo.get('validationDate').value);

    }
  }

  ngOnInit() {
    this.patchOrCreate()
    this.hideAndShowFieldForViewAndCreate()
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.loadAllDropDown()
    this.valueChangesGenerationActivityNumber()
  }

  private patchOrCreate() {
    this.generalInfo = this.presenter.enquiryForm.get('generalInfo') as FormGroup
  }

  private hideAndShowFieldForViewAndCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isShowForView = true
      this.isShowForViewAndViewMobile = true
      this.valueChangesConversionActivityNumber()
    } else if (this.presenter.operation === Operation.VIEW_MOBILE) {
      this.isShowForView = false
      this.isShowForViewAndViewMobile = true
    } else if (this.presenter.operation === Operation.CREATE) {
      this.isShowForView = false
      this.isShowForViewAndViewMobile = false
    }
  }

  onKeyPressReferralPersonName(event: KeyboardEvent) {
    console.log(event);
    this.kubotaPatterns.allowCharactersWithSpace(event)
  }

  loadAllDropDown() {
    this.generalInfoWebService.getFollowUpType().subscribe(response => {
      this.followupTypes = response.result as Array<FollowUpType>
    })
    this.generalInfoWebService.getRetailConversionActivityType().subscribe(response => {
      this.retailConversionActivityType = response as Array<RetailConversionActivityType>
    })
    this.generalInfoWebService.getPurposeOfPurchase().subscribe(response => {
      this.purposeOfPurchase = response.result as Array<PurposeOfPurchase>
    })
    this.generalInfoWebService.getActivationType().subscribe(response => {
      this.activationType = response.result as Array<ActivationType>
    })
    this.generalInfoWebService.getGenerationActivityType().subscribe(response => {
      this.generationActivationType = response.result as Array<GenerationActivationType>
    })
    this.generalInfoWebService.getSource().subscribe(response => {
      this.source = response.result as Array<EnquirySource>
    })
    this.generalInfoWebService.getSalePerson().subscribe(response => {
      this.salesPersons = response.result as Array<SalePerson>
    })
  }

  selectionDateForEnquiryType(event: MatDatepickerInputEvent<Date>) {
    let tentativeDate = this.datePipe.transform(this.generalInfo.controls.tentativePurchaseDate.value, "yyyy-MM-dd")
    let enqDate = this.datePipe.transform(this.generalInfo.controls.enquiryDate.value, "yyyy-MM-dd")
    console.log(`tentativeDate = ${tentativeDate} enqDate = ${enqDate}`);
    if (tentativeDate && enqDate) {
      this.generalInfoWebService.setEnquiryType(enqDate, tentativeDate).subscribe(response => {
        console.log('enquiryType', response);
        this.generalInfo.controls.enquiryType.patchValue(response.enquiryType)
      })
    }
  }

  selectSource(event: MatSelectChange) {
    console.log(event.value);
    this.sourceName = event.value.source
    this.generalInfo.get('generationActivityNumber').reset();
    if(this.activityType !='' && this.sourceName != ''){
        this.activityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Generation', this.sourceName, this.activityType,'')
    }else{
        this.activityNumber$ = null;
    }
    if (event.value.sourceName == "REFERRAL") {
      this.isReferal = true
    } else {
      this.isReferal = false
    }
  }
  activityTypeEvent(event: MatSelectChange){
      this.activityType = event.value.generationActivityType
      this.generalInfo.get('generationActivityNumber').reset();
      if(this.activityType != '' && this.sourceName != ''){
          this.activityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Generation', this.sourceName, this.activityType, '');
      }else{
          this.activityNumber$ = null;
      }
  }
  valueChangesGenerationActivityNumber() {
    this.generalInfo.get('generationActivityNumber').valueChanges.subscribe(value => {
      if (value !== null) {
        this.generalInfo.get('generationActivityNumber').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
  }

  valueChangesConversionActivityNumber() {
    this.generalInfo.get('conversionActivityNumber').valueChanges.subscribe(value => {
      if (value !== null) {
        this.generalInfo.get('conversionActivityNumber').setErrors({
          selectFromList: 'Please select from list',
        });
      }
    })
  }

  displayFnForGenerationActivityNo(actNum: ActivityNumber) {
    return actNum ? actNum.activityNumber : undefined
  }

  selectionGenerationActivityType(event: MatSelectChange) {
    console.log(event.value);
    if (event.value.generationActivityType === 'KAI Supported') {
      this.presenter.mandatoryFieldForGenerationActivityType(true)
    } else {
      this.presenter.mandatoryFieldForGenerationActivityType(false)
    }
    this.activityTypeEvent(event);
  }

  selectedGenerationActivityNumber(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.generalInfo.get('generationActivityNumber').setErrors(null);
    }
  }

  selectedConversionActivityNumber(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.generalInfo.get('conversionActivityNumber').setErrors(null);
    }
  }

  selectionConverstionActivityType(event: MatSelectChange) {
    this.conversionActivityType =event.value.conversionActivityType;   
    if (event.value.conversionActivityType === 'KAI Supported') {
      this.presenter.mandatoryFieldForConversionActivityType(true)
    } else {
      this.presenter.mandatoryFieldForConversionActivityType(false)
    }
    this.generalInfo.get('conversionActivityNumber').reset();
    if(this.conversionActivityType && this.conversionSourceName){
        this.conversionactivityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Conversion', this.conversionSourceName ,this.conversionActivityType, this.enquiryByEnquiryNumber.enquiryNumber);
    }
  }

  selectionRetailConversionActivity(event: MatSelectChange) {
      this.conversionSourceName = event.value.retailConversionActivity;
      this.generalInfo.get('conversionActivityNumber').reset();
      if(this.conversionActivityType && this.conversionSourceName){
          this.conversionactivityNumber$ = this.generalInfoWebService.searchMarketingActivityNumber(null, 'Conversion', this.conversionSourceName ,this.conversionActivityType,this.enquiryByEnquiryNumber.enquiryNumber);
      }
  }

  displayFnConversionActivityNo(actNum: ActivityNumber) {
    return actNum ? actNum.activityNumber : undefined
  }

  compareFnFollowUpType(c1: FollowUpType, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.followUpType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.followUpType;
    }
    return c1 && c2 ? c1.followUpType === c2.followUpType : c1 === c2;
  }

  compareFnPurposeOfPurchase(c1: PurposeOfPurchase, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.purposeOfPurchase === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.purposeOfPurchase;
    }
    return c1 && c2 ? c1.purposeOfPurchase === c2.purposeOfPurchase : c1 === c2;
  }

  compareFnRetailConversionActivity(c1: RetailConversionActivityType, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.retailConversionActivity === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.retailConversionActivity;
    }
    return c1 && c2 ? c1.retailConversionActivity === c2.retailConversionActivity : c1 === c2;
  }

  compareFnConversionActivityType(c1: ActivationType, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.conversionActivityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.conversionActivityType;
    }
    return c1 && c2 ? c1.conversionActivityType === c2.conversionActivityType : c1 === c2;
  }

  compareFnGenerationActivityType(c1: GenerationActivationType, c2: ViewEnquiry): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.generationActivityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.generationActivityType;
    }
    return c1 && c2 ? c1.generationActivityType === c2.generationActivityType : c1 === c2;
  }

  compareFnSource(c1: EnquirySource, c2: EnquirySource): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.source === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.source;
    }
    return c1 && c2 ? c1.source === c2.source : c1 === c2;
  }
  compareFnSalesPerson(c1: SalePerson, c2: SalePerson): boolean {
    console.log("typeof c1 !== typeof c2 ", c1, c2, typeof c1 !== typeof c2);
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.salesPerson === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.salesPerson;
    }
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }
}