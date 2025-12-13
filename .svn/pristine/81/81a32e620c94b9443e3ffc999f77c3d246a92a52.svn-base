import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { IncentiveSchemeClaimService } from '../../pages/incentive-schemes-claim-create/incentive-scheme-claim.service';
import { RetailIncentiveSchemeDetails, WholesaleIncentiveSchemeDetails } from 'IncentiveSchemeClaimModule';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { DateService } from 'src/app/root-service/date.service';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';

@Component({
  selector: 'app-incentive-scheme-claim-create',
  templateUrl: './incentive-scheme-claim-create.component.html',
  styleUrls: ['./incentive-scheme-claim-create.component.scss'],
  providers: [IncentiveSchemeClaimService]
})
export class IncentiveSchemeClaimCreateComponent implements OnInit {
  isView: boolean
  isEdit: boolean;
  isApprove: boolean;
  isCreate: boolean;
  incentiveSchemeClaim: FormGroup
  disable = true;
  display: boolean;
  claimId: any;
  userType:string;
  months: any[] = [
    {id:1, value:'January'}, {id:2, value:'February'}, {id:3, value:'March'}, 
    {id:4, value:'April'}, {id:5, value:'May'}, {id:6, value:'June'}, 
    {id:7, value:'July'}, {id:8, value:'August'}, {id:9, value:'September'}, 
    {id:10, value:'October'}, {id:11, value:'November'}, {id:12, value:'December'}
  ];
  wholesaleIncentiveSchemeDetails: WholesaleIncentiveSchemeDetails;
  retailIncentiveSchemeDetails: RetailIncentiveSchemeDetails[];
  approvalList:any[];
  constructor(private fb: FormBuilder,
    public dialog: MatDialog,
    private claimRt: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private claimService: IncentiveSchemeClaimService,
    private loginService: LoginFormService,
    private dateService: DateService) { }

  ngOnInit() {
    this.userType = this.loginService.getLoginUserType();
    this.checkOperationType();
    this.intiOperationForm();
    this.patchOrCreate();
    this.isCreate && this.incentiveSchemeClaim.get('month').valueChanges.subscribe(value => {
      this.retailIncentiveSchemeDetails = [];
      if(value){
        this.claimService.getWholesaleIncentiveSchemeDetails(value).subscribe(response => {
          if(response && response['result']){
            this.wholesaleIncentiveSchemeDetails = {
              dealerId: response['result']['dealerId'],
              dealerCode: this.loginService.getLoginUserDealerCode(),
              dealerName: this.loginService.getLoginUserName(),
              wholesaleQty: response['result']['wholesaleQty'],
              incentiveValue: response['result']['incentiveValue'],
              retails: response['result']['retails'],
              claimAmount: response['result']['claimAmount'],
              totalAmount: response['result']['totalAmount']
            }
            this.claimService.getRetailsIncentiveSchemeDetails(value).subscribe(response => {
              if(response){
                response['result'].forEach( obj => 
                  this.retailIncentiveSchemeDetails.push({
                    schemeId: obj['schemeId'],
                    customerName: obj['customerName'],
                    mobileNo: obj['mobileNo'],
                    address1: obj['address1'],
                    pincode: obj['pincode'],
                    postOffice: obj['postOffice'],
                    tehsil: obj['tehsil'],
                    district: obj['district'],
                    state: obj['state'],
                    city: obj['city'],
                    village: obj['village'],
                    dcNo: obj['dcNo'],
                    dcDate: obj['dcDate'],
                    modelVariant: obj['modelVariant'],
                    scheme: obj['scheme'],
                    dse: obj['dse'],
                    tm: obj['tm'],
                    gm: obj['gm'],
                    amount: obj['amount']
                  })
                );
              }
            });
          } else {
            this.incentiveSchemeClaim.get('month').reset();
          }
        });
      }
    });
  }

  private patchOrCreate() {
    if (this.isView) {
      this.display = true
    } else {
      this.display = false
    }
  }
  createIncentiveSchemeClaim() {
    this.incentiveSchemeClaim = this.fb.group({
      claimNo: ['', Validators.compose([])],
      claimDate: [{ value: new Date(), disabled: true }, Validators.compose([])],
      month: ['', Validators.compose([Validators.required])],
    });
  }

  viewIncentiveSchemeClaim() {
    this.incentiveSchemeClaim = this.fb.group({
      claimNo: [{ value: '', disabled: true }],
      claimDate: [{ value: '', disabled: true }],
      month: [{ value: '', disabled: true }],
    })
    this.fetchClaimDetails(this.claimId);
  }

  private checkOperationType() {
    this.isView = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'view'
    this.isCreate = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'create'
    this.isApprove = this.claimRt.snapshot.routeConfig.path.split('/')[0] == 'approve'
    if(this.isApprove)
      this.isView = true;
      
    this.claimRt.queryParamMap.subscribe((queryMap: ParamMap) => {
      if(queryMap && Object.keys(queryMap['params']).length > 0){
        if (queryMap['params']['id']) {
          this.claimId = atob(queryMap['params']['id']);
        }
      }
    });
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
        this.claimService.approveClaim(approvalDetails).subscribe(response => {
          this.toastr.success(response['message']);
          this.router.navigate(['../'], { relativeTo: this.claimRt });
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
  private intiOperationForm() {
    if (this.isView) {
      this.viewIncentiveSchemeClaim()
    } else {
      this.createIncentiveSchemeClaim()
    }
  }

  validateForm() {
    this.markFormAsTouched()
    if (this.incentiveSchemeClaim.valid) {
      this.openConfirmDialog();
    } else {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }
  submitData() {
    var claimDetails = this.incentiveSchemeClaim.getRawValue();
    delete claimDetails['claimNo'];
    claimDetails['claimDate'] = this.dateService.getDateIntoDDMMYYYY(claimDetails['claimDate']);
    claimDetails['dealerMaster'] = {id:this.wholesaleIncentiveSchemeDetails.dealerId};
    claimDetails = {...claimDetails, ...this.wholesaleIncentiveSchemeDetails, incentiveSchemeClaimDetails: this.retailIncentiveSchemeDetails};
    
    this.claimService.submitClaim(claimDetails).subscribe(response => {
      if(response){
        this.toastr.success('Incentive Scheme Claim Saved Successfully', 'Success');
        this.router.navigate(['../'], { relativeTo: this.claimRt })
      }else{
        this.toastr.error('Error while saving incentive scheme claim details','Transaction Failed');
      }
    }, error => {
      this.toastr.error('Error while saving incentive scheme claim details','Transaction Failed');
    });
  }

  fetchClaimDetails(id:any){
    this.claimService.fetchClaimDetails(id).subscribe(response => {
      if(response && response['result']){
        var claimdetails = response['result'];
        var claimDate = claimdetails['claimDate'].split('-');
        this.incentiveSchemeClaim.get('claimNo').patchValue(claimdetails['claimNo']);
        this.incentiveSchemeClaim.get('claimDate').patchValue(new Date(claimDate[2]+'-'+claimDate[1]+'-'+claimDate[0]));
        this.incentiveSchemeClaim.get('month').patchValue(claimdetails['month']);
        this.wholesaleIncentiveSchemeDetails = claimdetails;
        this.wholesaleIncentiveSchemeDetails.dealerCode = claimdetails['dealerMaster']['dealerCode'];
        this.wholesaleIncentiveSchemeDetails.dealerName = claimdetails['dealerMaster']['dealerName'];
        this.retailIncentiveSchemeDetails = claimdetails['incentiveSchemeClaimDetails'];
        this.approvalList = claimdetails['approvalDetails'];
      }else{
        this.toastr.error('No Record Found','Transaction Failed');
      }
    }, error => {
      this.toastr.error('Error in fetching details','Transaction Failed');
    });
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Incentive Scheme Claim?';
    if (this.isEdit) {
      message = 'Do you want to update Incentive Scheme Claim?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isEdit) {
        this.submitData();
      }
      if (result === 'Confirm' && this.isEdit) {
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
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }

  private markFormAsTouched() {
    for (const key in this.incentiveSchemeClaim.controls) {
      if (this.incentiveSchemeClaim.controls.hasOwnProperty(key)) {
        this.incentiveSchemeClaim.controls[key].markAsTouched();
      }
    }
  }

  viewInvoice(){
    
  }
}
