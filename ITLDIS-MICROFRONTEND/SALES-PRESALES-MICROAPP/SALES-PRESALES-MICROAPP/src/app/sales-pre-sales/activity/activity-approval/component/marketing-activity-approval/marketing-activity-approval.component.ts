import { ApprovalService } from './../approval.service';
import { Component, OnInit, Input, Output } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { MarketingActivityCreateContainerService } from '../../../activity-proposal/component/marketing-activity-create-container/marketing-activity-create-container.service';
import { BaseDto } from 'BaseDto';
import { ActivityHead, Approval, DealerMaster, Enquiry } from 'approval';
import { ToastrService } from 'ngx-toastr';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { PurposeActivityDomain, ActivityTypeDomain,ActivityCategoryDomain } from 'ActivityProposalModule';

@Component({
  selector: 'app-marketing-activity-approval',
  templateUrl: './marketing-activity-approval.component.html',
  styleUrls: ['./marketing-activity-approval.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    MarketingActivityCreateContainerService,
    ApprovalService
  ]
})
export class MarketingActivityApprovalComponent implements OnInit {

  isEdit: boolean;
  isCreate: boolean
  isView: boolean;
  isActivityCategoryKai: boolean
  data: Object;
  disable = true;
  isActivityPurposeConversion: boolean
  total: number
  getPurposeActivity: BaseDto<Array<PurposeActivityDomain>>
  getActivityType: BaseDto<Array<ActivityTypeDomain>>
  activityCategoryList : BaseDto<Array<ActivityCategoryDomain>>
  heads: Array<ActivityHead> = []
  dealer: DealerMaster
  enquiries: Array<Enquiry> = []
  @Input() approvalHierDetails = []
  approvalStatus : string
  activityApprovalFrom: FormGroup
  activitystatus:string
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private router: Router,
    private actRt: ActivatedRoute,
    private marketingActivityCreateContainerService: MarketingActivityCreateContainerService,
    private approvalService: ApprovalService,
    private login: LoginFormService,
    private toast: ToastrService,
  ) { }

  ngOnInit() {
    this.createActivityApproval()
    this.parseIdAndPatchViewForm()    
  }

  createActivityApproval() {
    this.activityApprovalFrom = this.fb.group({
      activityPurpose: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      location: [{ value: '', disabled: true }],
      activityNumber: [{ value: '', disabled: true }],
      numberOfDays: [{ value: '', disabled: true }],
      proposedBudget: [{ value: '', disabled: true }],
      maxAllowedBudget: [{ value: '', disabled: true }],
      activityStatus: [{ value: '', disabled: true }],
      claimableAmount: [{ value: '', disabled: true }],
      activityCreationDate: [{ value: '', disabled: true }],
      approvedAmount: [{value:'', disabled:true}],
      activityCategory : [{value:'', disabled:true}]
    })
  }

  validateForm(approvalStatus:string) {
    let claimableAmount = this.activityApprovalFrom.controls.proposedBudget.value;
    let approvedAmount = this.activityApprovalFrom.controls.approvedAmount.value;
    
    if(approvalStatus==='Approved'){
        if(approvedAmount=='' || parseInt(approvedAmount)==0){
            this.toast.error("Enter Approved Amount");
            return;
        }else if(parseFloat(claimableAmount) < parseFloat(approvedAmount)){
            this.toast.error("Approved Amount can't be more than Proposed Budget");
            return;
        }
    }else if(approvalStatus==='Reject'){
        this.activityApprovalFrom.controls.approvedAmount.patchValue(0);
    }
    this.approvalStatus = approvalStatus;
    this.openConfirmDialog();
    
  }

  submitData(result: DialogResult) {
    this.actRt.params.subscribe(prms =>
      this.approvalService.approveProposalOrClaim({
        approvedAmount: (this.activityApprovalFrom.get('approvedAmount').value && parseFloat(this.activityApprovalFrom.get('approvedAmount').value)),
        activityProposalId: Number.parseInt(atob(prms['actPrpId'])),
        remark: result.remarkText,
        approvalStatus : this.approvalStatus
      }).subscribe(res => {
        console.log(res)
        this.toast.success(res.message, 'Approved')
        this.back()
      }, err => {
        console.log(err)
        this.toast.error(err.error.result, 'Rejected')
      })
    )
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Submit  Activity Approval?';
    if (this.isEdit) {
      message = 'Do you want to update  Activity Approval?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });

    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      console.log('The dialog was closed', result);
      if (result) {
        if (result.button === 'Confirm') {
          this.submitData(result);
        }
      }
    });
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }
    return confirmationData;
  }

  private parseIdAndPatchViewForm() {
    this.actRt.params.subscribe(prms => this.fetchDataForId(atob(prms['actPrpId'])))
  }

  private fetchDataForId(actPrpId) {
    this.marketingActivityCreateContainerService.getActivityProposalById(actPrpId).subscribe(dto => {
      let domain = dto as BaseDto<Approval>
      this.formForViewSetup(domain.result)
    })
  }

  private formForViewSetup(domain: Approval) {
    if (domain) {
      this.activityApprovalFrom.patchValue(domain.activityProposalHeaderData)
      this.activitystatus = domain.activityProposalHeaderData.activityStatus
      this.patchDates(domain)
      this.patchDropdowns(domain)
      this.heads = domain.activityProposalHeads
      if (domain.activityProposalHeaderData.activityPurpose === 'Conversion') {
        this.isActivityPurposeConversion = true
      }
      this.enquiries = domain.activityProposalEnquiries
      this.dealer = domain.dealerInfo
      this.approvalHierDetails = domain.approvalHierDetails
      
      if(this.activitystatus === 'Approved'){
          this.activityApprovalFrom.controls['approvedAmount'].disable();
      }else{
          if(domain.activityProposalHeaderData.approvedAmount==null || domain.activityProposalHeaderData.approvedAmount == 0)
              this.activityApprovalFrom.controls['approvedAmount'].patchValue(domain.activityProposalHeaderData.claimableAmount);
          
          this.activityApprovalFrom.controls['approvedAmount'].enable();
      }
      if(domain.activityProposalHeaderData.activityCategory === 'Dealer Own'){
          this.isActivityCategoryKai = false;
      }else{
          this.isActivityCategoryKai = true;
      }
    }
  }

  private patchDates(domain: Approval) {
    this.activityApprovalFrom.controls['activityCreationDate'].patchValue(new Date(domain.activityProposalHeaderData.createdDate))
    this.activityApprovalFrom.controls['fromDate'].patchValue(new Date(domain.activityProposalHeaderData.fromDate))
    this.activityApprovalFrom.controls['toDate'].patchValue(new Date(domain.activityProposalHeaderData.toDate))
  }

  private patchDropdowns(domain: Approval) {
    this.marketingActivityCreateContainerService.getPurposeActivity().subscribe(drpDt => {
      this.getPurposeActivity = drpDt as BaseDto<Array<PurposeActivityDomain>>
      console.log(this.getPurposeActivity)
    })
    this.activityApprovalFrom.controls.activityPurpose.patchValue({ id: domain.activityProposalHeaderData.sourcePurposeId, purpose: domain.activityProposalHeaderData.activityPurpose })
    this.marketingActivityCreateContainerService.getActivityTypeByPurpose(domain.activityProposalHeaderData.sourcePurposeId).subscribe(response => {
      console.log("response ", response);
      this.getActivityType = response as BaseDto<Array<ActivityTypeDomain>>
    })
    this.activityApprovalFrom.controls['activityType'].patchValue({ activityType: domain.activityProposalHeaderData.activityType, id: domain.activityProposalHeaderData.activityTypeId })
    this.marketingActivityCreateContainerService.getActivityCategoryList().subscribe(response => {
      this.activityCategoryList = response as BaseDto<Array<ActivityCategoryDomain>>
    })
    this.activityApprovalFrom.controls['activityCategory'].patchValue({ category: domain.activityProposalHeaderData.activityCategory, id: domain.activityProposalHeaderData.activityCategoryId })
  }
  compareFnActivityPurpose(c1: PurposeActivityDomain, c2: PurposeActivityDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.purpose === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.purpose;
    }
    return c1 && c2 ? c1.purpose === c2.purpose : c1 === c2;
  }

  compareFnActivityType(c1: ActivityTypeDomain, c2: ActivityTypeDomain): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.activityType === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.activityType;
    }
    return c1 && c2 ? c1.activityType === c2.activityType : c1 === c2;
  }
  compareFnActivityCategory(c1: ActivityCategoryDomain, c2: ActivityCategoryDomain): boolean {
      if (typeof c1 !== typeof c2) {
        if (typeof c1 === 'object' && typeof c2 === 'string') return c1.category === c2;
        if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.category;
      }
      return c1 && c2 ? c1.category === c2.category : c1 === c2;
    }
  back() {
    this.router.navigate(['../../../activity-proposal'], { relativeTo: this.actRt })
  }
}
