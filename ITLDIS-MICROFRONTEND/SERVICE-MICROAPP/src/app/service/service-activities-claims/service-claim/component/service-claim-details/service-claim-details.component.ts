import { Component, OnInit, ViewChild } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { StorageLoginUser } from 'LoginDto';
import { ServiceClaimService } from '../../pages/service-claim.service';
import { ToastrService } from 'ngx-toastr';
import { Source } from 'src/app/confirmation-box/confirmation-data';
import { ClaimJobCardDetailsComponent } from '../claim-job-card-details/claim-job-card-details.component';

@Component({
  selector: 'app-service-claim-details',
  templateUrl: './service-claim-details.component.html',
  styleUrls: ['./service-claim-details.component.scss']
})
export class ServiceClaimDetailsComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  isApprove: boolean;
  isCreate: boolean;
  @ViewChild(ClaimJobCardDetailsComponent,{static : false})
  jobcardDeatils:ClaimJobCardDetailsComponent
  loginUSer: StorageLoginUser
  todayDate = new Date()
  documentDetails = []
  serviceClaimForm: FormGroup;
  isDisabled:boolean = false;
  claimId:string
  dtlsId:bigint[]=[];
  constructor(public dialog: MatDialog,
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private loginService: LoginFormService,
    private toastr : ToastrService,
    private claimService: ServiceClaimService) { }

  ngOnInit() {
    this.createserviceClaimForm();
    this.loginUSer = this.loginService.getLoginUser();
    const operation = OperationsUtil.operation(this.activatedRoute);
    if(operation == Operation.CREATE){
      this.isCreate = true;
    } else if(operation == Operation.VIEW){
      this.isView = true;
      this.activatedRoute.queryParamMap.subscribe(param => {
        const id = atob(param.get('id'));
        this.claimId = id;
        this.getClaimDetails(id);
      });
    } else if(operation == Operation.APPROVAL){
      this.isApprove = true;
      this.activatedRoute.queryParamMap.subscribe(param => {
        const id = atob(param.get('id'));
        this.claimId = id;
        this.getClaimDetails(id);
      });
    }
    if(this.isCreate){
      this.resetForm();
    }
    this.claimService.addIdForApprovalBehaviourSubject.subscribe(id => {
      if(id != undefined && id != null){
        if(this.dtlsId.indexOf(id)==-1)
          this.dtlsId.push(id)
      }
    })
    this.claimService.removeIdForApprovalBehaviourSubject.subscribe(id => {
      if(id != undefined && id != null){
        this.dtlsId = this.dtlsId.filter(id1 => id1 != id);
      }
    })
  }

  getClaimDetails(id){
    this.claimService.viewClaimDetails(id).subscribe(response => {
      this.serviceClaimForm.patchValue(response['result']['Header'][0])
      this.claimService.productServiceBehaviourSubject.next(response['result']['Product']);
      this.claimService.documentDetailsBehaviourSubject.next(response['result']['Documents']);  
    });
  }

  createserviceClaimForm() {
    this.serviceClaimForm = this.fb.group({

      claimNo: [{value: '', disabled:true }],
      claimDate: [{ value: '', disabled: true }],
      claimStatus: [{ value: '', disabled: true }],
      fromDate: [{ value: '', disabled: true }],
      toDate: [{ value: '', disabled: true }],
      dealerCode: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      starCategory: [{ value: '', disabled: true }],
    })
  }

  resetForm(){
    this.serviceClaimForm.get('dealerCode').patchValue(this.loginUSer.dealerCode);
    //this.serviceClaimForm.get('dealerName').patchValue(this.loginUSer.name);
    this.serviceClaimForm.get('claimDate').patchValue(this.todayDate);
    this.claimService.getClaimPeriod().subscribe(response => {
      this.serviceClaimForm.patchValue(response['result']['Header'])
      this.claimService.productServiceBehaviourSubject.next(response['result']['Product']);
      this.claimService.documentDetailsBehaviourSubject.next(response['result']['Documents']);
      this.documentDetails = response['result']['Documents'];
    })
  }

  validateForm() {
    if(this.documentDetails && this.documentDetails.length>0){
      this.openConfirmDialog();
    } else {
      this.toastr.error('Document Details not found','Required Field');
    }
  }

  submitData() {
    const claimHdr = this.serviceClaimForm.getRawValue();
    let claimDtl = [];
    this.documentDetails.forEach( dtl => {
      if(dtl.documentType == 'JOBCARD'){
        claimDtl.push({
          serviceJobCardId : dtl['id'],
          documentType : dtl['documentType'],
          installationId : null,
          claimAmount : dtl['amount'],
          bonusAmount : dtl['bonusAmount'],
          totalClaimAmount : dtl['amount'] + dtl['bonusAmount']
        })
      }else if(dtl.documentType == 'INSTALLATION'){
        claimDtl.push({
          serviceJobCardId : null,
          documentType : dtl['documentType'],
          installationId : dtl['id'],
          claimAmount : dtl['amount'],
          bonusAmount : dtl['bonusAmount'],
          totalClaimAmount : dtl['amount'] + dtl['bonusAmount']
        })
      }
    });

    let objecttosave = {...claimHdr, 'claimDtls':claimDtl};
    this.claimService.saveClaim(objecttosave).subscribe(response => {
        if(response){
          this.toastr.success("Claim Generated Successfuly","Success");
          this.router.navigate(["../"],{ relativeTo: this.activatedRoute})
        }else{
          this.isDisabled = false;
          this.toastr.error('Error generated while saving data','Error');
        }
    }, error => {
      this.isDisabled = false;
      this.toastr.error('Error generated while saving data','Error');
    });

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Service Claim?';
    if (this.isEdit) {
      message = 'Do you want to update Service Claim?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.isDisabled = true;
        this.submitData();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }
  private openConfirmDialogApproval(approvalType): void | any {
    let message = `Do you want to ${approvalType} Service Claim?`;
    const confirmationData = this.setConfirmationModalDataApprove(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.button === 'Confirm') {
        this.isDisabled = true;
        const approvalData = {
          'remarks' : result.remarkText,
          'approvalStatus' : approvalType,
          'claimId' : this.claimId,
          'documentsId' : this.dtlsId,
          'rejectionData' : this.jobcardDeatils.jobCardDetailsForm.getRawValue()
        };
        this.claimService.approveRejectClaim(approvalData).subscribe(response => {
          this.toastr.success(response['message']);
          this.router.navigate(['../'],{ relativeTo: this.activatedRoute})
        }, error => {
          this.isDisabled = false;
          this.toastr.error('Error while saving details','Transaction Failed');
        })
      }
    });
  }
  private setConfirmationModalDataApprove(message: string) {
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

  approveClaim(approvalType){
    this.jobcardDeatils.jobCardDetailsForm.markAllAsTouched();
    if(this.jobcardDeatils.jobCardDetailsForm.valid){
      this.openConfirmDialogApproval(approvalType);
    } else { 
      this.toastr.error('Please fill mandatory fields','Mandatory Fields')
    }
  }
}
