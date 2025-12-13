import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ActivityClaimCreationService } from '../../../activity-claim/component/activity-claim-creation/activity-claim-creation.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewEditActivityClaimDomain } from 'ActivityClaimModule';
import { ApprovalService } from '../../../activity-approval/component/approval.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
 import { ToastrService } from 'ngx-toastr';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { ActivityClaimApprovalCommonWebService } from '../../activity-claim-approval-common-web.service';

@Component({
  selector: 'app-activity-claim-approval-create',
  templateUrl: './activity-claim-approval-create.component.html',
  styleUrls: ['./activity-claim-approval-create.component.scss'],
  providers: [ActivityClaimCreationService, ApprovalService, LoginFormService]
})
export class ActivityClaimApprovalCreateComponent implements OnInit {

  ViewActivityClaimDomain: ViewEditActivityClaimDomain
  approvedAmount: string

  constructor(
    public dialog: MatDialog,
    private activityClaimCreationService: ActivityClaimCreationService,
    private actRt: ActivatedRoute,
    private approvalService: ApprovalService,
    private login: LoginFormService,
    private toast: ToastrService,
    private router: Router,
    private activityClaimApprovalCommonWebService: ActivityClaimApprovalCommonWebService
  ) { }

  ngOnInit() {
    this.parseIdAndPatchViewForm()
  }

  private parseIdAndPatchViewForm() {
    this.actRt.params.subscribe(prms => {
      console.log('prms',prms) 
      this.fetchDataForId(prms['claimId'])
    })
  }

  private fetchDataForId(claimId: number) {
    console.log('Activity Claim ID ', claimId)
    this.activityClaimCreationService.getActivityClaimById(claimId).subscribe(
      dto => {
        this.ViewActivityClaimDomain = dto['result'] as ViewEditActivityClaimDomain
        console.log(this.ViewActivityClaimDomain)
      }
    )
  }
  validateForm(functionalKey: string) {
    this.activityClaimApprovalCommonWebService.approveActivityClaimForm(functionalKey)
    this.openConfirmDialog();
  }

  aproveClaim(result: DialogResult) {
    this.actRt.params.subscribe(prms =>
      this.approvalService.approveClaim({
        approvedAmount : (this.approvedAmount && parseFloat(this.approvedAmount)),
        activityClaimId: Number.parseInt(prms['claimId']),
        remark: result.remarkText
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

  getFormStatusandData(event) {
    console.log("event ", event);
    this.approvedAmount = event.formData.approvedAmount
  }


  openConfirmDialog(): void | any {
    let message = 'Do you want to Approve Activity Claim?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result) {
        if (result.button === 'Confirm') {
          this.aproveClaim(result)
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

  back() {
    this.router.navigate(['../../../activity-claim'], { relativeTo: this.actRt })
  }
}
