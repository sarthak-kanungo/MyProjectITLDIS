import { Component, OnInit } from '@angular/core';
import { SapPageStore } from './sap-page.store';
import { SapPagePresenter } from './sap-page-presenter';
import { FormGroup, AbstractControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { Router, ActivatedRoute } from '@angular/router';
import { SaveAndSubmitServiceActivityProposal, ViewServiceActivityProposal, ApproveServiceActivityProposal } from '../../domain/sap.domain';
import { SapPageWebService } from './sap-page-web.service';
import { DateService } from '../../../../../root-service/date.service';
import { Source, DialogResult } from 'src/app/confirmation-box/confirmation-data';


@Component({
  selector: 'app-sap-page',
  templateUrl: './sap-page.component.html',
  styleUrls: ['./sap-page.component.scss'],
  providers: [SapPageStore, SapPagePresenter, SapPageWebService]
})
export class SapPageComponent implements OnInit {
  isView:boolean = false;
  isCreate:boolean = false;
  activityProposalForm: FormGroup
  activityProposalDetailsForm: FormGroup
  headTableForm: FormGroup
  subActivityForm: FormGroup
  isApproval: boolean
  headsTotalForm: AbstractControl
  isDraft: boolean;
  dialogMsg: string;
  isButtonsShow: boolean = true;
  id: number
  serviceActivityProposalDetails: ViewServiceActivityProposal
  isSubmitDisable:boolean = false;
  constructor(
    private presenter: SapPagePresenter,
    private toastr: ToastrService,
    public dialog: MatDialog,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private dateService: DateService,
    private sapPageWebService: SapPageWebService
  ) { }

  ngOnInit() {
    this.presenter.operation = OperationsUtil.operation(this.activateRoute)
    this.activityProposalForm = this.presenter.sapForm
    this.activityProposalDetailsForm = this.presenter.activityTrainingProposal
    this.headTableForm = this.presenter.headTable
    this.subActivityForm = this.presenter.subActivityForm
    this.headsTotalForm = this.presenter.headsTotalForm
    this.viewOrEditOrCreate();
  }
  private viewOrEditOrCreate() {
    if (this.presenter.operation === Operation.VIEW) {
      this.isButtonsShow = false
      this.isApproval = false
      this.isView = true;
      this.parseIdAndViewOrEditForm()
    } else if (this.presenter.operation === Operation.EDIT) {
      this.isButtonsShow = true
      this.isApproval = false
      this.parseIdAndViewOrEditForm()
    } else if (this.presenter.operation === Operation.CREATE) {
      this.isApproval = false
      this.isCreate = true;
    } else if (this.presenter.operation === Operation.APPROVAL) {
      this.isApproval = true
      this.isButtonsShow = false
      this.parseIdAndViewOrEditForm()
    }
  }

  private parseIdAndViewOrEditForm() {
    this.activateRoute.queryParamMap.subscribe(prms => {
      this.sapPageWebService.getServiceActivityProposalByActivityNumber(prms.get('activityNumber')).subscribe(response => {
        
        this.serviceActivityProposalDetails = response
        this.id = response.id
        this.presenter.patchValueForViewServiceActivityProposal(response)
        if (this.presenter.operation === Operation.VIEW) {
          this.activityProposalForm.disable()
        } else if (this.presenter.operation === Operation.APPROVAL) {
          this.activityProposalForm.disable()
          this.activityProposalDetailsForm.get('approvedAmount').patchValue(response.maxAllowedBudget)
          this.activityProposalDetailsForm.get('approvedAmount').enable()
        }
      })
    })
  }
  saveAndSubmitForm(isSave?: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    this.activityProposalDetailsForm.get('targetedNumbers').setErrors(null);
    const sapDetails = this.activityProposalForm.getRawValue();
    console.log('sapDetails', sapDetails)
    if (this.activityProposalForm.status === 'VALID') {

      if(sapDetails.activityTrainingProposalForm.activityType.activityType === 'DTD Campaign'){
        if( sapDetails.activityTrainingProposalForm.targetedNumbers < 10 ){
          this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
          this.activityProposalDetailsForm.get('targetedNumbers').setErrors({'MinimumMachineount':'Min number of machines must be 10'});
          return false;
        }
      }else if(sapDetails.activityTrainingProposalForm.activityType.activityType === 'Service Camp'){
        if( sapDetails.activityTrainingProposalForm.targetedNumbers <20 ){
          this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
          this.activityProposalDetailsForm.get('targetedNumbers').setErrors({'MinimumMachineount':'Min number of machines must be 20'});
          return false;
        }
      }


      if (sapDetails.activityTrainingProposalForm.activityType.activityType === '5 in 1 Camp') {
        if (sapDetails.subActivityRow.subActivityTable.length < 1) {
          this.toastr.error(`Please Select Sub Activity`, 'Report Sub Activity')
        } else {
          this.openConfirmDialog() 
        }
      } else {
        this.openConfirmDialog()
      }
    } else {
      this.presenter.markFormAsTouched();
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  approveActivityProposal(isApprove: boolean) {
    this.isDraft = isApprove
    this.dialogMsg = isApprove ? 'approve' : 'reject'
    this.openConfirmDialog()
  }
  back() {
    this.router.navigate(['../'], { relativeTo: this.activateRoute })
  }
  submitData() {
    this.sapPageWebService.saveServiceActivityProposal(this.buildJsonForServiceActivityProposal()).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        this.router.navigate(['../'], {relativeTo : this.activateRoute})
      } else {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving','Transaction failed');
    })
  }
  approveData(remark) {
    const sapDetails = this.activityProposalForm.getRawValue();
    const approveServiceActivityProposal = {} as ApproveServiceActivityProposal
    approveServiceActivityProposal.serviceActivityProposalId = this.id
    if (this.isDraft === true) {
      approveServiceActivityProposal.approvedFlag = "Approve";
    } else {
      approveServiceActivityProposal.approvedFlag = "Reject"
    }
    approveServiceActivityProposal.approvedAmount = sapDetails.activityTrainingProposalForm.approvedAmount
    //approveServiceActivityProposal.remark = sapDetails.activityTrainingProposalForm.remarks
    approveServiceActivityProposal.remark = remark;

    this.sapPageWebService.approveServiceActivityProposal(approveServiceActivityProposal).subscribe(response => {
        if (response) {
          const displayMsg = response['message']
          this.toastr.success(displayMsg, 'Success')
          this.router.navigate(['../'], {relativeTo : this.activateRoute})
        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving','Transaction failed');
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction failed');
      })
  }
  buildJsonForServiceActivityProposal() {
    const serviceActivityProposal = {
      ...this.buildJsonForActivityProposal(),
      ...this.buildJsonForActivityProposalDetails(),
      ...this.buildJsonForHeadsDetails(),
      ...this.buildJsonForSubActivityDetails()
    }
    
    return serviceActivityProposal
  }
  buildJsonForActivityProposal() {
    const sapDetails = this.activityProposalForm.getRawValue();
    const saveAndSubmitServiceActivityProposal = {} as SaveAndSubmitServiceActivityProposal
    if (this.isDraft === true) {
      saveAndSubmitServiceActivityProposal.draftFlag = true
    } else {
      saveAndSubmitServiceActivityProposal.draftFlag = false
    }
    saveAndSubmitServiceActivityProposal.serviceMtActivityType = {
      id: sapDetails.activityTrainingProposalForm.activityType.id
    }
    if (this.presenter.operation === Operation.EDIT) {
      saveAndSubmitServiceActivityProposal.id = this.id
    }
    return saveAndSubmitServiceActivityProposal
  }
  buildJsonForActivityProposalDetails() {
    const sapDetails = this.activityProposalForm.getRawValue();
    const saveAndSubmitServiceActivityProposal = {} as SaveAndSubmitServiceActivityProposal
    saveAndSubmitServiceActivityProposal.location = sapDetails.activityTrainingProposalForm.location
    saveAndSubmitServiceActivityProposal.fromDate = this.dateService.getDateIntoDDMMYYYY(sapDetails.activityTrainingProposalForm.fromDate)
    saveAndSubmitServiceActivityProposal.toDate = this.dateService.getDateIntoDDMMYYYY(sapDetails.activityTrainingProposalForm.toDate)
    saveAndSubmitServiceActivityProposal.noOfDays = sapDetails.activityTrainingProposalForm.noOfDays
    saveAndSubmitServiceActivityProposal.proposedBudget = sapDetails.activityTrainingProposalForm.proposedBudget
    saveAndSubmitServiceActivityProposal.maxAllowedBudget = sapDetails.activityTrainingProposalForm.maxAllowedBudget
    if (sapDetails.activityTrainingProposalForm.activityType.activityType === 'USS') {
      saveAndSubmitServiceActivityProposal.targetedNumbers = (sapDetails.activityTrainingProposalForm.numberOfPerson && parseFloat(sapDetails.activityTrainingProposalForm.numberOfPerson))
    } else {
      saveAndSubmitServiceActivityProposal.targetedNumbers = (sapDetails.activityTrainingProposalForm.targetedNumbers && parseFloat(sapDetails.activityTrainingProposalForm.targetedNumbers))
    }
    saveAndSubmitServiceActivityProposal.remarks = sapDetails.activityTrainingProposalForm.remarks
    const targetedProducts = []
    sapDetails.activityTrainingProposalForm.targetedProduct && sapDetails.activityTrainingProposalForm.targetedProduct.forEach(element => {
      targetedProducts.push({
        id: element.id
      })
    })
    saveAndSubmitServiceActivityProposal.targetedProducts = targetedProducts
    return saveAndSubmitServiceActivityProposal
  }
  buildJsonForHeadsDetails() {
    const sapDetails = this.activityProposalForm.getRawValue();
    const saveAndSubmitServiceActivityProposal = {} as SaveAndSubmitServiceActivityProposal
    const serviceActivityProposalHeads = []
    if (sapDetails.activityTrainingProposalForm.activityType.activityType === 'Service Camp' ||
      sapDetails.activityTrainingProposalForm.activityType.activityType === 'DTD Campaign' ||
      sapDetails.activityTrainingProposalForm.activityType.activityType === '5 in 1 Camp') {
      sapDetails.headForm.dataTable.forEach(element => {
        serviceActivityProposalHeads.push({
          id: element.id,
          head: element.heads.head,
          amount: element.amount
        })
      });
    }
    saveAndSubmitServiceActivityProposal.serviceActivityProposalHeads = serviceActivityProposalHeads
    return saveAndSubmitServiceActivityProposal
  }
  buildJsonForSubActivityDetails() {
    const sapDetails = this.activityProposalForm.getRawValue();
    const saveAndSubmitServiceActivityProposal = {} as SaveAndSubmitServiceActivityProposal
    const serviceActivityProposalSubActivity = []
    if (sapDetails.activityTrainingProposalForm.activityType.activityType === '5 in 1 Camp') {
      sapDetails.subActivityRow.subActivityTable.forEach(element => {
        serviceActivityProposalSubActivity.push({
          id: element.id,
          subActivity: element.subActivity.subActivity,
          amount: element.amount,
        })
      });
    }
    saveAndSubmitServiceActivityProposal.ServiceActivityProposalSubActivity = serviceActivityProposalSubActivity
    return saveAndSubmitServiceActivityProposal
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Service Activity Proposal?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if ((this.presenter.operation === Operation.CREATE || this.presenter.operation === Operation.EDIT) && result === 'Confirm') {
        this.isSubmitDisable = true;
        this.submitData();
      }
      if (this.presenter.operation === Operation.APPROVAL) {
        let dialogReult = result as DialogResult;
        if(result.button === 'Confirm'){
          this.isSubmitDisable = true;
          this.approveData(result.remarkText)
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
    return confirmationData;
  }
}