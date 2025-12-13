import { ApprovalService } from './../approval.service';
import { Component, OnInit, Input, Output } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BaseDto } from 'BaseDto';
import { ToastrService } from 'ngx-toastr';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { PurposeActivityDomain, ActivityTypeDomain,ActivityCategoryDomain } from 'ActivityProposalModule';
import { CustomValidators } from 'src/app/utils/custom-validators';

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
 
  @Input() approvalHierDetails = []
  approvalStatus : string
  activityApprovalFrom: FormGroup
  activitystatus:string
  allChecked:boolean=false;
  resultSet:any[];
  statusList:any=[];
  btnType:any
  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private router: Router,
    private actRt: ActivatedRoute,
    private approvalService: ApprovalService,
    private login: LoginFormService,
    private toast: ToastrService,
  ) { }

  ngOnInit() {
    this.createActivityApproval();
    this.fetchActivityForApproval();
  }

  fetchActivityForApproval(){
    (this.activityApprovalFrom.controls.activities as FormArray).clear();
    this.approvalService.getProposalsPendingForApproval().subscribe(response => {
        if(response!=null && response['result']!=null){
          this.resultSet = response['result'];
          response['result'].forEach(element => {
            this.addActivity(element);
            if(this.statusList.indexOf(element['lastApproverName'])<0){
              this.statusList.push(element['lastApproverName']);
            }
          });
        }
    });
  }
  selectionApproverName(event){
    (this.activityApprovalFrom.controls.activities as FormArray).clear();
    this.resultSet.filter(element => element['lastApproverName']==event.value).forEach(element => {
      this.addActivity(element);
    });
  }
  createActivityApproval() {
    this.activityApprovalFrom = this.fb.group({
      approverName: [null],
      activities: new FormArray([])
    });
  }
  gotoActivityPage(activityId){
    //this.router.navigateByUrl("../activity-proposal/view/"+btoa(activityId));
    this.router.navigate(['../../activity-proposal/view', btoa(activityId)], { relativeTo: this.actRt });
  }
  checkAll(event){
    this.allChecked = !this.allChecked;
    // if(this.allChecked){
    //   (this.activityApprovalFrom.controls.activities as FormArray).controls.forEach(element => {
    //     element.get('isSelect').patchValue(true);
    //   });
    // }else{
    //   (this.activityApprovalFrom.controls.activities as FormArray).controls.forEach(element => {
    //     element.get('isSelect').patchValue(false);
    //   });
    // }
  }
  addActivity(data){
    let FG  = this.fb.group({
      isSelect:[null],
      activityProposalId:[null],
      activityPurposalNumber: [{ value: '', disabled: true }],
      activityPurposalDate: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      activityPurpose: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      dealerCode: [{ value: '', disabled: true }],
      proposedBudget: [{ value: '', disabled: true }],
      lastApproverName: [{ value: '', disabled: true }],
      lastApprovedAmount: [{ value: '', disabled: true }],
      approvedAmount: [{value:'', disabled:false},CustomValidators.numberOnly],
      remark : [{value:'', disabled:false}],
      approvalStatus:[{value:null,disabled:true}]
    });
    FG.patchValue(data);
    FG.controls.activityProposalId.patchValue(data.id);
    (this.activityApprovalFrom.controls.activities as FormArray).push(FG);
  }
  
  validateForm(approvalStatus:string) {

    this.btnType=approvalStatus;
    
    if(this.btnType==='Approve' || this.btnType ==='Reject'){
      let flag = false;
      (this.activityApprovalFrom.controls.activities as FormArray).controls.forEach(element => {
        
        element.get('approvedAmount').setValidators([CustomValidators.numberOnly]);
        element.get('approvedAmount').updateValueAndValidity();
        
        if(element.get('isSelect').value==true){
          flag = true;
          element.get('approvalStatus').setValue(this.btnType);
          element.get('approvedAmount').setValidators(Validators.compose([Validators.required,CustomValidators.numberOnly]));
          element.get('approvedAmount').updateValueAndValidity();
          element.markAllAsTouched();
        }
      });
      if(!flag){
        this.toast.error("Please check Activity Proposal for Approval");
        return false;
      }
      this.btnType = approvalStatus;
      this.openConfirmDialog();

    }
    // else if(approvalStatus==='Rejected'){ //added by Mahesh.Kumar on 10-01-2024
    //   let flag = false;
    //   (this.activityApprovalFrom.controls.activities as FormArray).controls.forEach(element => {
    //     // element.get('approvedAmount').setValidators([CustomValidators.numberOnly]);
    //     // element.get('approvedAmount').updateValueAndValidity();
    //      element.get('approvalStatus').setValue('Rejected')
    //     if(element.get('isSelect').value==true){
    //       flag = true;
    //       // element.get('approvedAmount').setValidators(Validators.compose([Validators.required,CustomValidators.numberOnly]));
    //       // element.get('approvedAmount').updateValueAndValidity();
    //       element.markAllAsTouched();
    //     }
    //   });
    //   if(!flag){
    //     this.toast.error("Please check Activity Proposal for Rejection");
    //     return false;
    //   }

    //   this.approvalStatus = approvalStatus;
    //   this.openConfirmDialogForReject();
    // }
    if(this.activityApprovalFrom.valid){

    }else{
      this.toast.error("Please Enter Required Filed","Mandatory Fields");
      return false;
    }
    // this.approvalStatus = approvalStatus;
    // this.openConfirmDialog();
    
  }

  submitData(result: DialogResult) {
    this.approvalService.approveProposal((this.activityApprovalFrom.controls.activities as FormArray).getRawValue()).subscribe(res => {
      this.toast.success(res.message);
      this.fetchActivityForApproval();
    }, err => {
      this.toast.error('Approval failed','Trnsaction Error');
    })
  
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to '+this.btnType+' Selected Activities?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        if (result === 'Confirm') {
          this.submitData(result);
        }
      }
    });
  }

  // Added by Mahesh.Kumar on 10-01-2024

  // private openConfirmDialogForReject(): void | any {
  //   let message = 'Do you want to Reject Selected Activities?';
  //   const confirmationData = this.setConfirmationModalData(message);
  //   const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
  //     width: '500px',
  //     panelClass: 'confirmation_modal',
  //     data: confirmationData,
  //   });

  //   dialogRef.afterClosed().subscribe((result) => {
  //     if (result) {
  //       if (result === 'Confirm') {
  //         this.submitData(result);
  //       }
  //     }
  //   });
  // }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

 
}
