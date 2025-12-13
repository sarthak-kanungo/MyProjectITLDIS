import { LoginFormService } from '../../../../../root-service/login-form.service';
import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatSelectChange } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { MarketingActivityCreateService } from './marketing-activity-create.service';
import { MarketingActivityCreateContainerService } from '../marketing-activity-create-container/marketing-activity-create-container.service';
import { BaseDto } from 'BaseDto';
import { PurposeActivityDomain, ActivityTypeDomain,ActivityCategoryDomain, MaxAllowedBudget, MarketingActivityCreateDomain, ViewEditActivityProposalDomain } from 'ActivityProposalModule';
import { ActivityProposalService } from '../../activity-proposal.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-marketing-activity-create',
  templateUrl: './marketing-activity-create.component.html',
  styleUrls: ['./marketing-activity-create.component.scss'],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
    MarketingActivityCreateService, MarketingActivityCreateContainerService
  ]
})

export class MarketingActivityCreateComponent implements OnInit, OnChanges {

  isEdit: boolean;
  isView: boolean;
  validStatus: boolean
  public minToDate = new Date();
  activityProposalFrom: FormGroup
  @Output() getActivityEvent = new EventEmitter<MatSelectChange>()
  @Input()
  max: Date | null
  tomorrow = new Date();
  @Input()
  min: Date | null
  today = new Date();
  isApprovedAmount : boolean
  isActivityCategoryKai : boolean
  @Input() set budget(bgt: number) {
    if (this.activityProposalFrom) {
      this.activityProposalFrom.controls.proposedBudget.patchValue(bgt)
    }
  }
  @Input() set claimableAmount(claimAmt:number){
    if (this.activityProposalFrom) {
      this.activityProposalFrom.controls.claimableAmount.patchValue(claimAmt)
    }  
  }
  getPurposeActivity: BaseDto<Array<PurposeActivityDomain>>
  getActivityType: BaseDto<Array<ActivityTypeDomain>>
  maxAllowedBudget: BaseDto<MaxAllowedBudget>
  activityCategoryList :any
  //submit
  @Output() getFormStatusandData = new EventEmitter<object>()
  @Input() viewEditActivityProposal: ViewEditActivityProposalDomain
  @Output() sendMaxLimitEvent = new EventEmitter<number>()
  
  constructor(
    private marketingActivityCreateService: MarketingActivityCreateService,
    private marketingActivityCreateContainerService: MarketingActivityCreateContainerService,
    private activityProposalService: ActivityProposalService,
    private actRt: ActivatedRoute,
    public dialog: MatDialog,
    private login: LoginFormService
  ) {
  }

  ngOnChanges() {
    if (this.viewEditActivityProposal) {
      this.activityProposalFrom.controls['activityStatus'].patchValue(this.viewEditActivityProposal.activityProposalHeaderData.activityStatus)
      this.activityProposalFrom.patchValue(this.viewEditActivityProposal.activityProposalHeaderData)
      this.activityProposalFrom.controls['fromDate'].patchValue(new Date(this.viewEditActivityProposal.activityProposalHeaderData.fromDate))
      this.activityProposalFrom.controls['toDate'].patchValue(new Date(this.viewEditActivityProposal.activityProposalHeaderData.toDate))
      this.activityProposalFrom.controls.activityPurpose.patchValue({ id: this.viewEditActivityProposal.activityProposalHeaderData.sourcePurposeId, purpose: this.viewEditActivityProposal.activityProposalHeaderData.activityPurpose })
      this.activityProposalFrom.controls['activityType'].patchValue({ activityType: this.viewEditActivityProposal.activityProposalHeaderData.activityType, id: this.viewEditActivityProposal.activityProposalHeaderData.activityTypeId })
      this.activityProposalFrom.controls.activityCategory.patchValue({ id: this.viewEditActivityProposal.activityProposalHeaderData.activityCategoryId, category: this.viewEditActivityProposal.activityProposalHeaderData.activityCategory })
      this.activityProposalFrom.controls['activityCreationDate'].patchValue(new Date(this.viewEditActivityProposal.activityProposalHeaderData.createdDate))
      this.marketingActivityCreateContainerService.getActivityTypeByPurpose(this.viewEditActivityProposal.activityProposalHeaderData.sourcePurposeId).subscribe(response => {
        this.getActivityType = response as BaseDto<Array<ActivityTypeDomain>>
      })
      /*if(this.viewEditActivityProposal.activityProposalHeaderData.approvedAmount){
        this.isApprovedAmount = true*/
      /*}*/
      if (this.viewEditActivityProposal.activityProposalHeaderData.activityCategory == 'KAI Supported') {
        this.isActivityCategoryKai = true
      }else{
        this.isActivityCategoryKai = false
      }
    }
  }

  ngOnInit() {
    this.tomorrow.setDate(this.tomorrow.getDate());
    this.today.setDate(this.today.getDate());
    this.checkOperationType()
    this.initOperationForm()
    this.patchOrCreate()
    this.loadActivityPurposeDropdown()
    this.getActivityCategoryList()
    this.isActivityCategoryKai = true
  }

  getActivityCategoryList(){
      this.marketingActivityCreateContainerService.getActivityCategoryList().subscribe(response => {
      this.activityCategoryList = response as BaseDto<Array<ActivityCategoryDomain>>
      console.log(response)
    })
  }
  
  private patchOrCreate() {
    if (this.isView) {

    } else if (this.isEdit) {

    } else {
      this.formForCreateSetup()
    }
  }

  private formForCreateSetup() {
    this.activityProposalFrom = this.marketingActivityCreateService.createactivityProposalForm()
    this.activityProposalService.submitOrResetActivityFormSubscribe((value: string) => {
      if (value.toLowerCase() === 'submit') {
        let rawFormData = this.activityProposalFrom.getRawValue()
        let dmn: MarketingActivityCreateDomain = rawFormData
        dmn.activityPurpose = rawFormData['activityPurpose']
        dmn.dealerEmployeeMaster = { id: this.login.getLoginUserId() }
        dmn.activityType = rawFormData['activityType']
        dmn.activityCategory = this.activityProposalFrom.controls.activityCategory.value.id;
        dmn.claimableAmount = this.activityProposalFrom.controls.claimableAmount.value;
        dmn.fromDate = this.convertDateToServerFormat(dmn.fromDate)
        dmn.toDate = this.convertDateToServerFormat(dmn.toDate)
        dmn.activityCreationDate = this.convertDateToOurFormat(dmn.activityCreationDate)
        this.getFormStatusandData.emit({ validStatus: this.activityProposalFrom.valid, formData: dmn });
        this.markFormAsTouched();
        console.log("numberOfDays : "+dmn.numberOfDays);
      } else if (value.toLowerCase() === 'clear') {
        this.activityProposalFrom.controls.activityPurpose.reset()
        this.activityProposalFrom.controls.activityType.reset()
        this.activityProposalFrom.controls.activityCategory.reset()
        this.activityProposalFrom.controls.location.reset()
        this.activityProposalFrom.controls.fromDate.reset()
        this.activityProposalFrom.controls.claimableAmount.reset()
        this.activityProposalFrom.controls.toDate.reset()
        this.activityProposalFrom.controls.numberOfDays.reset()
        this.activityProposalFrom.controls.proposedBudget.reset()
        this.activityProposalFrom.controls.maxAllowedBudget.reset()
        this.activityProposalFrom.controls.activityType.enable();
      }
    })
    this.activityProposalFrom.controls.fromDate.valueChanges.subscribe((res: Date) => {
      if (res) {
        let dateDiff = this.daysDateDiff(new Date(this.activityProposalFrom.controls.toDate.value), res)
        console.log('Date Diff', dateDiff)
        this.activityProposalFrom.controls.numberOfDays.patchValue(Number.isNaN(dateDiff) ? 0 : dateDiff)
      } else {
        this.activityProposalFrom.controls.numberOfDays.patchValue(0)
      }
    })
    this.activityProposalFrom.controls.toDate.valueChanges.subscribe((res: Date) => {
      if (res) {
        let dateDiff = this.daysDateDiff(res, new Date(this.activityProposalFrom.controls.fromDate.value))
        console.log('Date Diff', dateDiff)
        this.activityProposalFrom.controls.numberOfDays.patchValue(Number.isNaN(dateDiff) ? 0 : dateDiff)
      } else {
        this.activityProposalFrom.controls.numberOfDays.patchValue(0)
      }
    })
  }

  private loadActivityPurposeDropdown() {
    this.marketingActivityCreateContainerService.getPurposeActivity().subscribe(drpDt => {
      this.getPurposeActivity = drpDt as BaseDto<Array<PurposeActivityDomain>>
      console.log(this.getPurposeActivity)
    })
  }

  selectionActivityPurpose(event) {
    console.log(event);
    this.activityProposalService.activityProposeEvent.emit(event)
    this.marketingActivityCreateContainerService.getActivityTypeByPurpose(event.value.id).subscribe(response => {
      console.log('response---', response);
      this.getActivityType = response as BaseDto<Array<ActivityTypeDomain>>
    })
    this.activityProposalFrom.controls.fromDate.reset();
    this.activityProposalFrom.controls.toDate.reset();
    this.activityProposalFrom.controls.activityType.reset();
    this.activityProposalFrom.controls.numberOfDays.reset();
    this.activityProposalFrom.controls.maxAllowedBudget.reset();
    this.activityProposalFrom.controls.claimableAmount.reset();
    this.activityProposalFrom.controls.activityType.enable();
  }
  selectionActivityCategory(event){
      if (event.value.category === 'KAI Supported') {
          this.isActivityCategoryKai = true
      }else{
        this.isActivityCategoryKai = false
      }    
      this.activityProposalService.activityCategoryEvent.emit(event);
  }
  selectionActivityType(event) {
    /*this.marketingActivityCreateContainerService.maximumLimitByActivityType(event.value.id).subscribe(response => {
      this.maxAllowedBudget = response as BaseDto<MaxAllowedBudget>
      this.activityProposalFrom.controls.maxAllowedBudget.patchValue(this.maxAllowedBudget.result.maximumLimit)
      this.activityProposalFrom.controls.claimableAmount.patchValue(this.maxAllowedBudget.result.claimableAmount);
      console.log("this.activityProposalFrom.controls.maxAllowedBudget ", this.activityProposalFrom.controls.maxAllowedBudget.value);
    })*/
    this.activityProposalFrom.controls.fromDate.reset();
    this.activityProposalFrom.controls.toDate.reset();
    this.activityProposalFrom.controls.numberOfDays.reset()
    this.activityProposalFrom.controls.maxAllowedBudget.reset();
    this.activityProposalFrom.controls.claimableAmount.reset();
    this.activityProposalFrom.controls.activityType.disable();  
    this.getActivityEvent.emit(event)
  }

  private daysDateDiff = (from, to) => Math.ceil((Math.abs(to - from) / (1000 * 60 * 60 * 24) + 1))

  keyAlphabets(event: any) {
    const pattern = /[A-Za-z]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  public fromDateSelected(event) {
    if (event && event['value']) {
      this.minToDate = new Date(event['value']);
    }
    this.activityProposalFrom.controls.toDate.reset();
    this.activityProposalFrom.controls.numberOfDays.reset()
    this.activityProposalFrom.controls.maxAllowedBudget.reset();
    this.activityProposalFrom.controls.claimableAmount.reset();
  }

  public toDateSelected(event){
     let activityTypeId = this.activityProposalFrom.controls.activityType.value.id;  
     let fromDate = this.convertDateToServerFormat(this.activityProposalFrom.controls.fromDate.value);
     let toDate = this.convertDateToServerFormat(event.value);   
     
     if(fromDate!=undefined && fromDate!=null && fromDate!='' 
             && toDate!=undefined && toDate!=null && toDate!=''
                 && activityTypeId!=undefined && activityTypeId!=null && activityTypeId!=''){
         this.marketingActivityCreateContainerService.maximumLimitByActivityType(activityTypeId,fromDate,toDate).subscribe(response => {
              this.maxAllowedBudget = response as BaseDto<MaxAllowedBudget>
              console.log(this.maxAllowedBudget.result);
              this.activityProposalFrom.controls.maxAllowedBudget.patchValue(this.maxAllowedBudget.result.maximumLimit)
              this.activityProposalFrom.controls.claimableAmount.patchValue(this.maxAllowedBudget.result.claimableAmount);
              this.sendMaxLimitEvent.emit(this.maxAllowedBudget.result.maximumLimit);
         })
     }
  }
  private checkOperationType() {
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
  }

  private initOperationForm() {
    if (this.isView) {
      this.activityProposalFrom = this.marketingActivityCreateService.viewActivityProposalForm()
    } else if (this.isEdit) {
      this.activityProposalFrom = this.marketingActivityCreateService.editActivityProposalForm()
    } else {
      this.activityProposalFrom = this.marketingActivityCreateService.createactivityProposalForm()
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

  private convertDateToServerFormat(dt: string) {
    if (dt) {
      let date = new Date(dt)
      let formattedDate = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear()
      return formattedDate
    }
    return null
  }
  private markFormAsTouched() {
    for (const key in this.activityProposalFrom.controls) {
      if (this.activityProposalFrom.controls.hasOwnProperty(key)) {
        this.activityProposalFrom.controls[key].markAsTouched();
      }
    }
  }

  compareFnActivityPurpose(c1: PurposeActivityDomain, c2: PurposeActivityDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.purpose === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.purpose;
    }
    return c1 && c2 ? c1.purpose === c2.purpose : c1 === c2;
  }
  
  compareFnActivityCategory(c1: ActivityCategoryDomain, c2: ActivityCategoryDomain): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === 'object' && typeof c2 === 'string') return c1.category === c2;
        if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.category;
      }
      return c1 && c2 ? c1.category === c2.category : c1 === c2;
    }

  compareFnActivityType(c1: ActivityTypeDomain, c2: ActivityTypeDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.activityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.activityType;
    }
    return c1 && c2 ? c1.activityType === c2.activityType : c1 === c2;
  }

}

