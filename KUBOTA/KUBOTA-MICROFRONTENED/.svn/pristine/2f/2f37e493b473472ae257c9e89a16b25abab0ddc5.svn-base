import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SapCommonWebService } from '../../service-activity-training-proposal/service/sap-common-web.service';
import { CustomValidators } from 'src/app/utils/custom-validators';
import { ToastrService } from 'ngx-toastr';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-service-activity-proposal-bulk-approval',
  templateUrl: './service-activity-proposal-bulk-approval.component.html',
  styleUrls: ['./service-activity-proposal-bulk-approval.component.css'],
  providers:[SapCommonWebService]
})
export class ServiceActivityProposalBulkApprovalComponent implements OnInit {
  serviceActivityProposalApproval:FormGroup;
  approvalStatus:string;
  allChecked:boolean;
  statusList: string[] = [];
  resultSet:any;
  btnType:any
  constructor(
    private fb:FormBuilder,
    private sapCommonWebService:SapCommonWebService,
    private taoster:ToastrService,
    private dialog:MatDialog,
    private router:Router,
    private activateRouter:ActivatedRoute
  ) { }

  ngOnInit() {
    this.createServiceActivityProposalApprovalForm();
    this.getServiceProposalApproveData();
  }

  getServiceProposalApproveData(){
    (this.serviceActivityProposalApproval.controls.activities as FormArray).clear();
    this.sapCommonWebService.getProposalBulkApprovalData().subscribe(response => {
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

  createServiceActivityProposalApprovalForm() {
    this.serviceActivityProposalApproval = this.fb.group({
      approverName: [null],
      activities: new FormArray([])
    });
  }
  addActivity(data){
    let FG  = this.fb.group({
      isSelect:[null],
      activityClaimId:[null],
      activityProposalId:[null],
      activityProposalNo: [{ value: '', disabled: true }],
      activityProposalDate: [{ value: '', disabled: true }],
      activityStatus: [{ value: '', disabled: true }],
      activityType: [{ value: '', disabled: true }],
      approvedAmount: [{ value: '', disabled: true }],
      fromDate:[{value:null,disabled:true}],
      toDate: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      proposedBudget: [{ value: '', disabled: true }],
      lastApproverName: [{ value: '', disabled: true }],
      lastApprovedAmount: [{ value: '', disabled: true }],
      approvedAmounts:[{value:null,disabled:false},CustomValidators.numberOnly],
      remark : [{value:'', disabled:false}],
      trColor: [{value:'', disabled:true}],
    });
    FG.patchValue(data);
    FG.controls.activityProposalId.patchValue(data.id);
    (this.serviceActivityProposalApproval.controls.activities as FormArray).push(FG);
  }

  approve(approvalStatus:string){
    this.btnType=approvalStatus;
    if(this.btnType==='Approve' || this.btnType==='Reject'){
      let flag = false;
      (this.serviceActivityProposalApproval.controls.activities as FormArray).controls.forEach(element => {  
        if(element.get('isSelect').value==true){
          // element.get('approvalStatus').setValue(this.btnType)
          flag = true;
          element.get('approvedAmounts').setValidators(Validators.compose([Validators.required,CustomValidators.numberOnly]));
          element.get('approvedAmounts').updateValueAndValidity();
          element.markAllAsTouched();
        }
      });
      if(!flag){
        this.taoster.error("Please check Activity Proposal for Approval");
        return false;
      }
    }
    if(this.serviceActivityProposalApproval.valid){

    }else{
      this.taoster.error("Please Enter Required Filed","Mandatory Fields");
      return false;
    }
    this.approvalStatus = approvalStatus;
    this.openBulkApprovalConfirmDialog(approvalStatus);
  }


  private openBulkApprovalConfirmDialog(approvalType): void | any {
    let message = 'Do you want to '+approvalType+' Service Activity Proposal?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
      if (result && result.button === 'Confirm' ){
         this.serviceActivityProposalApprove();
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

checkAll(eve:any){
   this.allChecked=!this.allChecked;
   (this.serviceActivityProposalApproval.controls.activities as FormArray).controls.forEach(element => {
    if(eve.checked){
      element.get('isSelect').setValue(true);
    }else{
      element.get('isSelect').setValue(false);
    }
  })
}

serviceActivityProposalApprove(){
  const selectedActivities = [];
(this.serviceActivityProposalApproval.controls.activities as FormArray).controls.forEach(ele=>{
      if(ele.get('isSelect').value===true){
        selectedActivities.push({
          isSelect:ele.get('isSelect').value,
          activityProposalId:ele.get('activityProposalId').value,
          remark:ele.get('remark').value,
          approvedAmount: ele.get('approvedAmounts').value, 
          approvalStatus:this.btnType,
        
        });
      }
    });
    this.sapCommonWebService.bulkApprovalForServiceActivityProposal(selectedActivities)
    .subscribe(
      (res) => {
        this.taoster.success(res['message'])
        // this.taoster.success("Approved Successfully");
        this.getServiceProposalApproveData();
      },
      (err) => {
        this.taoster.error('An error occurred during approval');
      }
    );
  
}



selectionApproverName(event:any){
  (this.serviceActivityProposalApproval.controls.activities as FormArray).clear();
  this.resultSet.filter(element => element['lastApproverName']==event.value).forEach(element => {
    this.addActivity(element);
  });
}

gotoActivityPage(activityNumber:any){
  this.router.navigate(['../service-activities-claims/service-activity-training-proposal/view'], {
    queryParams: { activityNumber: activityNumber },
    // relativeTo: this.activateRouter
  });
  // this.router.navigate(['../service-activities-claims/service-activity-training-proposal/view'],queryParams: { activityNumber:'2222' },)
  // this.router.navigate(['service-activities-claims/service-activity-training-proposal/view'], {
  //   queryParams: { activityNumber:'2222' },
  //   relativeTo: this.activateRouter
  // })
  // this.router.navigate(['../../vactivityNumber/view', btoa(claimId)], { relativeTo: this.activateRouter });
}

}
