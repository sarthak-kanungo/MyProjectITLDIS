import { Component, OnInit } from '@angular/core';
import { ServiceActivityClaimPageStore } from './service-activity-claim-page.store';
import { ServiceActivityClaimPagePresenter } from './service-activity-claim-page-presenter';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { SubmitServiceActivityClaim, ViewActivityClaim, ApproveServiceActivityClaim } from '../../domain/service-activity-claim.domain';
import { ServiceActivityClaimPageWebService } from './service-activity-claim-page-web.service';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { DatePipe } from '@angular/common';
import { ServiceActivityClaimPhotoWebService } from '../service-activity-claim-photo/service-activity-claim-photo-web.service';

@Component({
  selector: 'app-service-activity-claim-page',
  templateUrl: './service-activity-claim-page.component.html',
  styleUrls: ['./service-activity-claim-page.component.scss'],
  providers: [ServiceActivityClaimPageStore,DatePipe,ServiceActivityClaimPhotoWebService, ServiceActivityClaimPagePresenter, ServiceActivityClaimPageWebService]
})
export class ServiceActivityClaimPageComponent implements OnInit {

  activityClaimForm: FormGroup
  activityClaimDetailsForm: FormGroup
  activityHeadsDetailsForm: FormGroup
  activitySubActivityDetailsForm: FormGroup
  isButtonsShow: boolean
  dialogMsg: string
  isApproval: boolean
  approvalDetails:[]
  viewActivityClaim: ViewActivityClaim
  id: number
  isSubmitDisable:boolean = false
  isEdit:boolean=false;
  claimStatus:any;
  activityTypeId:any;
  reportPhoto:any;
  proposalId:any
  constructor(
    private serviceActivityClaimPagePresenter: ServiceActivityClaimPagePresenter,
    private activateRoute: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private router: Router,
    private serviceActivityClaimPageWebService: ServiceActivityClaimPageWebService,
    private serviceActivityClaimPhotoWebService:ServiceActivityClaimPhotoWebService,
    private datePipe:DatePipe
  ) { }

  ngOnInit() {
    this.serviceActivityClaimPagePresenter.operation = OperationsUtil.operation(this.activateRoute)
    this.activityClaimForm = this.serviceActivityClaimPagePresenter.serviceActivityClaimForm
    this.activityClaimDetailsForm = this.serviceActivityClaimPagePresenter.activityClaimForm
    this.activityHeadsDetailsForm = this.serviceActivityClaimPagePresenter.headDetailstableData
    this.activitySubActivityDetailsForm = this.serviceActivityClaimPagePresenter.subActivityDetailstableData
    this.viewOrEditOrCreate()
  }

  private viewOrEditOrCreate() {
    if (this.serviceActivityClaimPagePresenter.operation === Operation.VIEW) {
      this.isButtonsShow = false
      this.isApproval = false
      this.activityClaimDetailsForm.disable();
    this.activityHeadsDetailsForm.disable();
      this.parseIdAndViewOrEditForm()
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE) {
      this.isApproval = false
      this.isButtonsShow = true
    } else if (this.serviceActivityClaimPagePresenter.operation === Operation.APPROVAL) {
      this.isApproval = true
      this.isButtonsShow = false;
     
      this.parseIdAndViewOrEditForm()
      this.activityClaimForm.disable();
    } else if(this.serviceActivityClaimPagePresenter.operation === Operation.EDIT){
       this.isEdit=true
      this.isApproval = false
      this.parseIdAndViewOrEditForm();
      this.activityClaimForm.disable();

    }
  }

  private parseIdAndViewOrEditForm() {
    this.activateRoute.params.subscribe(prms => {
      this.serviceActivityClaimPageWebService.getActivityClaimById(`` + prms['id']).subscribe(response => {
        this.id = response.activityClaimHeaderData.id,
        this.claimStatus=response.activityClaimHeaderData.claimStatus,
        this.activityTypeId=response.activityClaimHeaderData.activityTypeId,
        this.serviceActivityClaimPagePresenter.patchValueForViewActivityClaim(response);
        this.viewActivityClaim = response;
        this.proposalId=response.activityClaimHeaderData.proposalId
        this.approvalDetails = this.viewActivityClaim.approvalDetails;
        // this.reportPhoto=response.reportPhotos;
      })      
    })
    
  }

  saveAndSubmitActivityClaim() {
    this.dialogMsg = 'submit'
    if (this.activityClaimForm.status === 'VALID') {
     
      const subsctivityheads = this.activitySubActivityDetailsForm.getRawValue();
      let flag:boolean=true;
      subsctivityheads && subsctivityheads.subActivityDetailsdataTable && subsctivityheads.subActivityDetailsdataTable.forEach(element => {
        if(typeof element.image == 'string'){
          flag = false;
          return false;
        }
      });
      if(!flag){
        this.toastr.error('Please upload document','Required Field')
        return false;
      }

      const heads = this.activityHeadsDetailsForm.getRawValue();
      heads.headDetailsdataTable.forEach(element => {
        if(typeof element.image == 'string'){
          flag = false;
          return false;
        }
      });
      if(!flag){
        this.toastr.error('Please upload document','Required Field')
        return false;
      }
      this.openConfirmDialog();
    } else {
      this.serviceActivityClaimPagePresenter.markFormAsTouched();
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  approveServiceActivityClaim(approvalType) {
    this.dialogMsg = approvalType;
    if(this.activitySubActivityDetailsForm.invalid || this.activityHeadsDetailsForm.invalid){
      this.toastr.error('Enter Mandatory Fields','Required Fields')
    }else{
      this.openConfirmDialog()
    }
  }

  approveData(remark) {
    const activityClaim = this.activityClaimForm.getRawValue()
    const approveServiceActivityClaim = {} as ApproveServiceActivityClaim
    approveServiceActivityClaim.serviceActivityClaimId = this.id
    approveServiceActivityClaim.approvedAmount = (activityClaim.totalServiceActivityClaim.totalClaimApprovedAmount && parseFloat(activityClaim.totalServiceActivityClaim.totalClaimApprovedAmount))
    approveServiceActivityClaim.approvalType=this.dialogMsg;
    approveServiceActivityClaim.remark=remark;

    approveServiceActivityClaim.subActivityDetails=this.activitySubActivityDetailsForm.getRawValue().subActivityDetailsdataTable;
    approveServiceActivityClaim.headDetails=this.activityHeadsDetailsForm.getRawValue().headDetailsdataTable;

    this.serviceActivityClaimPageWebService.approveClaim(approveServiceActivityClaim).subscribe(res => {
      if(res){
        const displayMsg = res['message']
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate(['../../'], { relativeTo: this.activateRoute })      
      }else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
    })
  }

  submitData() {
    // this.buildJsonForServiceActivityClaim()
    this.serviceActivityClaimPageWebService.saveServiceActivityClaim(this.buildJsonForServiceActivityClaim()).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate(['../'], { relativeTo: this.activateRoute })
      } else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
    })
  }

  private buildJsonForServiceActivityClaim() {
    const activityClaim = this.activityClaimForm.getRawValue();
    console.log(activityClaim,'activityClaim')
    const submitServiceActivityClaim = {} as SubmitServiceActivityClaim
    submitServiceActivityClaim.totalClaimAmount = activityClaim.totalServiceActivityClaim.totalClaimAmount
    submitServiceActivityClaim.approvedAmount = activityClaim.totalServiceActivityClaim.approvedAmount
    submitServiceActivityClaim.serviceActivityProposalId = activityClaim.serviceActivityClaimForm.activityNo.id
    submitServiceActivityClaim.claimHeads = activityClaim.headDetailstableData.headDetailsdataTable
    submitServiceActivityClaim.subActivities = activityClaim.subActivityDetailstableData.subActivityDetailsdataTable
    Object.keys(submitServiceActivityClaim).forEach(key => (submitServiceActivityClaim[key] == null) && delete submitServiceActivityClaim[key]);
    return this.appendActivityClaim(submitServiceActivityClaim)
  }

  private openConfirmDialog(): void | any {
 
    const message = `Do you want to ${this.dialogMsg} Service Activity Claim?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      
      if (this.serviceActivityClaimPagePresenter.operation === Operation.CREATE && result === 'Confirm') {
        this.isSubmitDisable = true;
        this.submitData();
      }
      
      if (this.serviceActivityClaimPagePresenter.operation === Operation.APPROVAL) {
        let dialogResult = result as DialogResult;
        if(dialogResult.button === 'Confirm'){
          this.isSubmitDisable = true;
          this.approveData(dialogResult.remarkText);
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
    if(this.dialogMsg.toLocaleLowerCase()=='reject'){
      confirmationData.remarkConfig = {
        source : Source.REJECT
      }
    }
    if(this.dialogMsg.toLocaleLowerCase()=='approve'){
      confirmationData.remarkConfig = {
        source : Source.APPROVE
      }
    }

    //Suraj--23-01-2024
    if(this.dialogMsg.toLocaleLowerCase()=='hold'){
      confirmationData.remarkConfig = {
        source : Source.HOLD
      }
    }
    return confirmationData;
  }
  onClickExit() {
    this.router.navigate(['../'], { relativeTo: this.activateRoute })
  }

  private appendActivityClaim = (obj, form?, namespace?) => {
    const fd = form || new FormData();
    let formKey;

    for (const property in obj) {
      if (obj.hasOwnProperty(property)) {
        if (namespace) {
          formKey = namespace + '[' + property + ']';
        } else {
          formKey = property;
        }

        if (obj[property] instanceof Date) {
          fd.append(formKey, obj[property].toISOString());
        } else if (typeof obj[property] === 'object'
          && !(obj[property] instanceof File)
          && !(obj[property] instanceof Blob)) {
          this.appendActivityClaim(obj[property], fd, formKey);
        } else {

          fd.append(formKey, obj[property]);
        }
      }
    }

    return fd;
  }
  private buildJsonForEditServiceAcitvity():FormData {
    const activityClaim = this.activityClaimForm.getRawValue();
    console.log(activityClaim,'activityClaim')
  const formData = new FormData();
  formData.append('claimId', this.id.toString()); // Assuming claimId is a string, convert if needed
  formData.append('claimStatus', this.claimStatus);
  formData.append('activityTypeId',this.activityTypeId);
  activityClaim.headDetailstableData.headDetailsdataTable.forEach((head, index) => {
    if(head.vendorName!==null && head.vendorName!==''){
      formData.append(`activityProposalHeads[${index}].vendorName`, head.vendorName);
    }
    if(head.billNo!==null && head.billNo!==''){
      formData.append(`activityProposalHeads[${index}].billNo`, head.billNo);
    }
    if(head.billDate!==null && head.billDate!==''){
      const dateObject = new Date(head.billDate);
      formData.append(`activityProposalHeads[${index}].billDateTran`, this.datePipe.transform(dateObject, 'yyyy-MM-dd h:mm:ssZZZZZ')); 
    }
     
      formData.append(`activityProposalHeads[${index}].id`, String(head.id));
      if (head.image instanceof File) {
        formData.append(`activityProposalHeads[${index}].image`, head.image);
      }
    
   

  });
    
    

  activityClaim.subActivityDetailstableData.subActivityDetailsdataTable.forEach((subHead, index) => {
  if(subHead.vendorName!==null && subHead.vendorName!==''){
    formData.append(`activityProposalSubActivities[${index}].vendorName`, subHead.vendorName);
  }
  if(subHead.billNo!==null && subHead.billNo!==''){
    formData.append(`activityProposalSubActivities[${index}].billNo`, subHead.billNo);
  }
  if(subHead.billDate!==null && subHead.billDate!==''){
    const dateObject = new Date(subHead.billDate);
    formData.append(`activityProposalSubActivities[${index}].billDateTran`, this.datePipe.transform(dateObject, 'yyyy-MM-dd h:mm:ssZZZZZ'));
  }
    formData.append(`activityProposalSubActivities[${index}].id`, String(subHead.id));
   
  
  
  //  formData.append(`activityProposalSubActivities[${index}].billDateTran`, subHead.billDate);
   if (subHead.image instanceof File) {
     formData.append(`activityProposalSubActivities[${index}].image`, subHead.image);
   }
 
 });
 
 
  return formData;
  }
  updateSubmitActivityClaim(){
    if(this.activitySubActivityDetailsForm.invalid || this.activityHeadsDetailsForm.invalid){
      this.toastr.error('Enter Mandatory Fields','Required Fields')
    }else{
      this.openConfirmDialogForUpdate()
    }
    }
    private openConfirmDialogForUpdate(): void | any {
      const message = `Do you want to Update Service Activity Claim?`;
      const confirmationData = this.setConfirmationModalDataForUpdate(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe(result => {      
        if (this.serviceActivityClaimPagePresenter.operation === Operation.EDIT && result === 'Confirm') {
          this.isSubmitDisable = true;
          this.updateServiceActivityClaim();
        }
      });
    }
    private setConfirmationModalDataForUpdate(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Confirm', 'Cancel'];
      return confirmationData;
    }

    updateServiceActivityClaim(){
       
    this.serviceActivityClaimPageWebService.updateServiceActivityClaim(this.buildJsonForEditServiceAcitvity()).subscribe(response => {
      if(response){
        this.toastr.success(response['message']);
        this.router.navigate(['../../'], { relativeTo: this.activateRoute }) 
      }else{
        this.toastr.error(response['message'])
      }
    })
    }
 
}