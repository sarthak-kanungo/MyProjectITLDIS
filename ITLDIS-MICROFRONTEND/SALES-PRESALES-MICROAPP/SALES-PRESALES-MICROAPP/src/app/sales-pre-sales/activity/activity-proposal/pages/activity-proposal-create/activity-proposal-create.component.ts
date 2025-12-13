
import { Component, OnInit, Input } from '@angular/core';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatSelectChange } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { MarketingActivitySearchService } from '../../component/marketing-activity-search/marketing-activity-search.service';
import { ActivityProposalService } from '../../activity-proposal.service';
import { MarketingActivityCreateContainerService } from '../../component/marketing-activity-create-container/marketing-activity-create-container.service';
import { MarketingActivityAddproductContainerService } from '../../component/marketing-activity-addproduct-container/marketing-activity-addproduct-container.service';
import { MarketingActivityCreateDomain, MarketingActivityAddproductDomain, ViewEditActivityProposalDomain, SubmitActivityProposal, EnquiryDetailsForConversion, ApprovalHierDetails } from 'ActivityProposalModule';
import { ActivityProposalCreateService } from './activity-proposal-create.service';
import { ToastrService } from 'ngx-toastr';
import { EnquiryDetailsProposalService } from '../../component/enquiry-details-proposal/enquiry-details-proposal.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { Location } from '@angular/common';
@Component({
  selector: 'app-activity-proposal-create',
  templateUrl: './activity-proposal-create.component.html',
  styleUrls: ['./activity-proposal-create.component.scss'],
  providers: [MarketingActivityCreateContainerService, MarketingActivitySearchService, MarketingActivityAddproductContainerService, ActivityProposalCreateService, EnquiryDetailsProposalService]
})
export class ActivityProposalCreateComponent implements OnInit {
  isEdit: boolean;
  isCreate: boolean
  isApprove: boolean
  isView: boolean;
  budget: number;
  maxAllowedLimit:number
  claimableAmount : number
  activityProposalFrom: FormGroup
  itemDetailsFrom: FormGroup
  selectActivityTypeEvent: MatSelectChange
  validStatus: boolean
  validStatusDetails: boolean
  noRepeatedHead: boolean
  validForm: boolean = false;
  public loginUserType: string;
  viewEditActivityProposal: ViewEditActivityProposalDomain
  @Input()
  approvalHierDetails : ApprovalHierDetails[]
  isActivityCategoryKai : boolean
  isSubmitDisable:boolean = false
  constructor(private fb: FormBuilder,
    private actRt: ActivatedRoute,
    private marketingActivitySearchService: MarketingActivitySearchService,
    public dialog: MatDialog,
    //submit
    private activityProposalService: ActivityProposalService,
    private activityProposalCreateService: ActivityProposalCreateService,
    private marketingActivityCreateContainerService: MarketingActivityCreateContainerService,
    private marketingActivityAddproductContainerService: MarketingActivityAddproductContainerService,
    private enquiryDetailsProposalService: EnquiryDetailsProposalService,
    private toastr: ToastrService,
    private router: Router,
    private loginFormService: LoginFormService, 
    private location:Location
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
  }

  ngOnInit() {
    this.checkOperationType()
    this.patchOrCreate()
  }

  private patchOrCreate() {
    if (this.isView) {
      console.log(`Viweing Form`)
      this.parseIdAndPatchViewForm()
      this.isView = true
    } else {
      this.isCreate = true
      console.log(`CreatingForm`)
    }
  }

  sendMaximumLimitEvent(event){
      this.maxAllowedLimit = event;
      this.recalculateClaimableAmount();
  }
  
  selectActivityEvent(event) {
    this.selectActivityTypeEvent = event
  }

  fianlAmount(event) {
    this.budget = event
    this.recalculateClaimableAmount();
  }

  noHeadRepeat(event){
    this.noRepeatedHead = event;
  }
  recalculateClaimableAmount(){
      if(this.maxAllowedLimit === undefined || this.maxAllowedLimit === null)
          this.maxAllowedLimit = 0
      if(this.budget === undefined || this.budget === null)
          this.budget = 0    
      this.activityProposalService.recalculateClaimableAmount(this.selectActivityTypeEvent.value.id, this.maxAllowedLimit,this.budget)
          .subscribe(response => {
              this.claimableAmount = response.result;
          }) ;
  }
  //submit
  validateForm(functionalKey) {
    this.activityProposalService.submitOrResetActivityForm(functionalKey)

    if (functionalKey === 'submit') {
      if (this.validStatus && !this.noRepeatedHead /*&& this.validStatusDetails*/) {
        this.openConfirmDialog();
      } else {
        this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
      }
    }
    if (functionalKey === 'clear') {

    }
  }
  submitFormAndReset() {
    this.activityProposalCreateService.postCreateActivityProposal(this.buildJsonForActivityProposal()).subscribe(formData => {
      if(formData){
        this.toastr.success(formData['message'], 'Success')
        this.router.navigate(['..'], { relativeTo: this.actRt })
      }else{
        this.isSubmitDisable = false;
        this.toastr.error("Error generated while saving","Transaction Failed");
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toastr.error("Error generated while saving","Transaction Failed");
    })
  }

  getActivityProposalData(event) {

    console.log("event ", event);
    if (event.form == "activityProposal") {
      this.marketingActivityCreateContainerService.marketingActivityCreateDomain = event.event.formData as MarketingActivityCreateDomain
      this.validStatus = event.event.validStatus
      console.log("activityProposal valid:", this.validStatus)
    }
    if (event.form == "itemDetails" && event.event.isActivityCategoryKai == true) {
      this.marketingActivityAddproductContainerService.addProduct = event.event.formData as Array<MarketingActivityAddproductDomain>
      this.validStatus = event.event.validStatus
      console.log("itemDetails valid:", this.validStatus)
      /*this.validStatusDetails = event.event.validStatus*/
    }
    if (event.form === 'enquiryDetails') {
      this.enquiryDetailsProposalService.enquiryDetailsForConversion = event.formData as Array<EnquiryDetailsForConversion>
    }
  }

  private buildJsonForActivityProposal() {
    const submitActivityProposal = {} as SubmitActivityProposal
    const activityProposal = this.marketingActivityCreateContainerService.marketingActivityCreateDomain
    const itemDetails = this.marketingActivityAddproductContainerService.addProduct
    const enquirytails = this.enquiryDetailsProposalService.enquiryDetailsForConversion
    submitActivityProposal.activityPurpose = activityProposal.activityPurpose['purpose']
    submitActivityProposal.activityType = activityProposal.activityType['id']
    submitActivityProposal.activityCategory = activityProposal.activityCategory
    submitActivityProposal.location = activityProposal.location
    submitActivityProposal.fromDate = activityProposal.fromDate
    submitActivityProposal.toDate = activityProposal.toDate
    submitActivityProposal.proposedBudget = activityProposal.proposedBudget
    submitActivityProposal.maxAllowedBudget = activityProposal.maxAllowedBudget
    submitActivityProposal.claimableAmount = activityProposal.claimableAmount
    submitActivityProposal.numberOfDays = activityProposal.numberOfDays
    const activityHeads = []
    if(activityProposal.activityCategory == 2){
        itemDetails.forEach(element => {
          activityHeads.push({
            headName: element.headName['headName'],
            amount: element.amount,
            remark: element.remark 
          })
        })
    }
    submitActivityProposal.activityHeads = activityHeads
    if (activityProposal.activityPurpose['purpose'] === 'Conversion') {
      const enquiries = []
      enquirytails.forEach(element => {
        enquiries.push({
          id: element.enquiryNumber['id']
        })
      })
      submitActivityProposal.enquiries = enquiries
    }
    console.log("submitActivityProposal ", submitActivityProposal);
    return submitActivityProposal

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Submit Activity Proposal?';
    if (this.isEdit) {
      message = 'Do you want to update  Activity Proposal?';
    }
    const confirmationData = this.setConfirmationModalData(message);

    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData,
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitFormAndReset()
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitFormAndReset()
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

  private checkOperationType() {
    this.isView = this.actRt.snapshot.routeConfig.path.split('/')[0] == 'view'
  }

  private parseIdAndPatchViewForm() {
    this.actRt.params.subscribe(prms => this.fetchDataForId(atob(prms['actPrpId'])))
  }
  private fetchDataForId(actPrpId: any) {
    this.marketingActivityCreateContainerService.getActivityProposalById(`` + actPrpId).subscribe(
      dto => { this.formForViewSetup(dto['result']) }
    )
  }

  back(){
    this.location.back();
  }
  private formForViewSetup(domain: ViewEditActivityProposalDomain) {
    console.log(' ####------->', domain)
    this.viewEditActivityProposal = domain
    console.log("approvalHierDetails---->", domain.approvalHierDetails)
    this.approvalHierDetails = domain.approvalHierDetails;
    if(domain.activityProposalHeaderData.activityCategory === 'Dealer Own'){
        this.isActivityCategoryKai = false; 
    }else{
        this.isActivityCategoryKai = true;
    }
  }

  viewPrint(printStatus:string){
      this.activityProposalService.printActivityProposal(this.viewEditActivityProposal.activityProposalHeaderData.activityNumber, printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
      })
  }
}