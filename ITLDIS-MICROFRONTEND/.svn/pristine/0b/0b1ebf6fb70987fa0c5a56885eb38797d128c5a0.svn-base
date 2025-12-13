import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog, MatExpansionPanel, MatCheckbox } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MachineDescripancyCiaimCreateService } from './machine-descripancy-ciaim-create.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { DiscrepancyComplaints, MachineDescripancyComplaintDetail, ResponseBySaveDomain, ViewAndEditDescripancyClaimDetailDomain } from 'MachineDescripancyClaim';
import { LoginFormService } from '../../../../../root-service/login-form.service';import { BaseDto } from 'BaseDto';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-machine-descripancy-claim-create',
  templateUrl: './machine-descripancy-claim-create.component.html',
  styleUrls: ['./machine-descripancy-claim-create.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    MachineDescripancyCiaimCreateService
  ]
})
export class MachineDescripancyClaimCreateComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  isCreate: boolean;
  isApprove: boolean;
  data: Object;
  displayField: boolean
  isExpanded: boolean
  loginUserId: number;
  isChecked: boolean
  isShowCheckbox : boolean
  isClaimQtyDisabled : boolean
  dialogMsg: string;
  isDraft: boolean;
  machineDescripancyClaimForm: FormGroup
  discrepancyComplaints: Array<DiscrepancyComplaints>
  responseBySaveDomain: BaseDto<ResponseBySaveDomain>
  claimId: any
  complaintId: number
  approvalList: any[]
  userType:any
  constructor(
    private machineDescripancyCiaimCreateService: MachineDescripancyCiaimCreateService,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private desclaimRt: ActivatedRoute,
    private loginFormService: LoginFormService,
    private router: Router
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
    this.userType = this.loginFormService.getLoginUserType();
  }

  ngOnInit() {
    this.checkOperationType()
    this.intiOperationForm()
    this.patchOrCreate()
  }
  private patchOrCreate() {

    if (this.isView) {
      this.isView = true
      this.displayField = true
      this.isApprove = false
      this.isExpanded = true
      this.isShowCheckbox = false
      this.isClaimQtyDisabled = true
      this.parseIdAndViewForm()
    } else if (this.isEdit) {
      this.displayField = true
      this.isApprove = false
      this.isView = false
      this.isShowCheckbox = true
      this.isClaimQtyDisabled = false
      //this.parseIdAndEditForm()
    } else if (this.isApprove) {
      this.isApprove = true
      this.isView = true
      this.isShowCheckbox = false
      this.isClaimQtyDisabled = false
      this.parseIdAndViewForm()
    } else {
      this.displayField = false
      this.isApprove = false
      this.isView = false
      this.isShowCheckbox = true
      this.isClaimQtyDisabled = false
      this.getComplaintDetailsList()
    }
  }

  onKeyPressClaimQty(event: KeyboardEvent) {
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  getComplaintDetailsList() {
    this.machineDescripancyCiaimCreateService.getComplaintDetails(this.loginUserId).subscribe(response => {
      console.log('ComplaintDetails', response);
      this.discrepancyComplaints = response['result'] 
    })
  }

  onSelectComplaint(checkState: MatCheckbox, expand: MatExpansionPanel, complaint: DiscrepancyComplaints) {
    console.log(checkState.checked);
    expand.disabled = !checkState.checked
    expand.expanded = checkState.checked
    console.log(complaint);
    if (checkState.checked) {
      complaint.machineDescripancyComplaintDetails.forEach(dtl => {
        dtl.machineDescripancyComplaint = {complaintId:complaint.complaintId}
      })
      this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.machineDescripancyClaim.push({ complaintId: complaint.complaintId, machineDescripancyComplaintDetails: complaint.machineDescripancyComplaintDetails })
    } else {
      this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.machineDescripancyClaim = this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.machineDescripancyClaim.filter(cmp => cmp.complaintId !== complaint.complaintId)
      complaint.machineDescripancyComplaintDetails.forEach(dtl => {
        dtl.claimQuantity = 0;
      })
    }
  }

  changeClaimQty(el: HTMLInputElement, data: MachineDescripancyComplaintDetail) {
    console.log(el);
    data.claimQuantity = Number.parseInt(el.value)
  }

  private parseIdAndViewForm() {
    //this.desclaimRt.params.subscribe(prms => this.fatchDataForClaimId(prms['claimId']))
    
    this.desclaimRt.queryParamMap.subscribe((queryMap: ParamMap) => {
      if(queryMap && Object.keys(queryMap['params']).length > 0){
        if (queryMap['params']['id']) {
          this.claimId = atob(queryMap['params']['id']);
          this.fatchDataForClaimId(this.claimId);
        }
      }
    });

  }

  private parseIdAndEditForm() {
    this.desclaimRt.params.subscribe(prms => this.fatchDataForClaimIdForEdit(prms['claimId']))
  }

  private fatchDataForClaimId(claimId: string) {
   this.machineDescripancyCiaimCreateService.getByClaimId(`` + claimId).subscribe(dto => { this.formForViewSetup(dto['result']) })
  }

  private fatchDataForClaimIdForEdit(claimId: string) {
    this.machineDescripancyCiaimCreateService.getByClaimId(`` + claimId).subscribe(dto => { this.formForEditSetup(dto['result']) })
  }

  private formForViewSetup(domain: ViewAndEditDescripancyClaimDetailDomain) {
    console.log('domain', domain);
    if(domain){
      domain['claimDate'] = new Date(domain['claimDate'].split('-').reverse().join('-'))
      this.machineDescripancyClaimForm.patchValue(domain)
      this.discrepancyComplaints = domain.machineDescripancyClaim
      this.approvalList = domain.approvalDetails;
    }
  }

  private formForEditSetup(domain: ViewAndEditDescripancyClaimDetailDomain) {
    console.log('domain', domain);
    if(domain){
      this.machineDescripancyClaimForm.patchValue(domain)
      this.discrepancyComplaints = domain.machineDescripancyClaim
    }
  }

  private checkOperationType() {
    this.isView = this.desclaimRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isEdit = this.desclaimRt.snapshot.routeConfig.path.split('/')[0] == 'edit'
    this.isApprove = this.desclaimRt.snapshot.routeConfig.path.split('/')[0] == 'approve'
    this.isCreate = this.desclaimRt.snapshot.routeConfig.path.split('/')[0] == 'create'
  }
  private intiOperationForm() {
    if (this.isView || this.isApprove) {
      this.machineDescripancyClaimForm = this.machineDescripancyCiaimCreateService.viewMachineDescripancyClaimForm();
    } else if (this.isEdit) {
      this.machineDescripancyClaimForm = this.machineDescripancyCiaimCreateService.editMachineDescripancyClaimForm();
    } else {
      this.machineDescripancyClaimForm = this.machineDescripancyCiaimCreateService.createMachineDescripancyClaimForm();
    }
  }

  saveAndSubmitForm(isSave?: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.openConfirmDialog();
  }

  
  private openApprovalConfirmationModal(approvalType){
    let message = `Are you sure you want to ${approvalType}?`
    const confirmationData = this.setApprovalConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
       if (result.button === 'Confirm') {
        const approvalDetails = {
          'approvalStatus' : approvalType,
          'kaiRemarks' : result.remarkText,
          'claimId' : this.claimId
        }
        this.machineDescripancyCiaimCreateService.approveClaim(approvalDetails).subscribe(response => {
          this.toastr.success(response['message']);
          this.router.navigate(['../'], { relativeTo: this.desclaimRt });
        });
       }
    })
  }
  private setApprovalConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    if(this.isApprove){
      confirmationData.remarkConfig = {
          source: Source.REQUIRED
      }
  }
    return confirmationData;
  }

  approveRejectClaim(approvalType){
    this.openApprovalConfirmationModal(approvalType);
  }
  
  saveDescripancyClaim() {
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.createdBy = this.loginUserId
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.draftMode = true
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.remarks = this.machineDescripancyClaimForm.controls.remarks.value
    this.machineDescripancyCiaimCreateService.saveAndSubmitMachineDescripancyClaim(this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain).subscribe(formData => {
      console.log(formData);
      this.responseBySaveDomain = formData as BaseDto<ResponseBySaveDomain>
      this.claimId = this.responseBySaveDomain.result.claimId
      if (formData) {
        this.toastr.success('Machine Descripancy Claim Saved Successfully', 'Success')
        this.router.navigate(['../'],{relativeTo: this.desclaimRt})
      }
    })
  }

  submitDescripancyClaim() {
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.createdBy = this.loginUserId
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.draftMode = false
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.claimId = this.claimId
    this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain.remarks = this.machineDescripancyClaimForm.controls.remarks.value
    console.log(this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain);
    this.machineDescripancyCiaimCreateService.saveAndSubmitMachineDescripancyClaim(this.machineDescripancyCiaimCreateService.saveAndSubmitDescripancyClaimDomain).subscribe(formData => {
      console.log(formData);
      if (formData) {
        this.toastr.success('Machine Descripancy Claim Submited Successfully', 'Success')
        this.router.navigate(['../'],{relativeTo: this.desclaimRt})
      }
    })
  }

  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Machine Descripancy Claim?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        if (this.isDraft === true) {
          this.saveDescripancyClaim()
        } else {
          this.submitDescripancyClaim();
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
    return confirmationData;
  }

/* Added by vinay*/

  clearRemarks() {
    this.machineDescripancyClaimForm.controls.remarks.reset();
  }

/* end by vinay*/
}
