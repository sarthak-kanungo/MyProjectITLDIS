import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { SpareDescripancyClaimPresenter } from '../store-presenter/spare-descripancy-claim-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { SpareDescripancyClaimStore } from '../store-presenter/spare-descripancy-claim-store';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from 'src/app/confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { SpareClaimService } from '../service/spare-claim.service';
import { LocalStorageService } from 'src/app/root-service/local-storage.service';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { DatePipe } from '@angular/common';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { error } from 'console';
@Component({
  selector: 'app-create-spare-descripancy-claim',
  templateUrl: './create-spare-descripancy-claim.component.html',
  styleUrls: ['./create-spare-descripancy-claim.component.css'],
  providers: [SpareDescripancyClaimPresenter, SpareDescripancyClaimStore,DatePipe]
})
export class CreateSpareDescripancyClaimComponent implements OnInit {
  createSpareDescripancyForm: FormGroup;
  attachementForm: FormGroup;
  partDetailsForm: FormArray;
  spareDescApprovalForm: FormGroup;
  isCreate: boolean = false;
  isView: boolean = false;
  isEdit: boolean = false;
  isReject: boolean = false;
  isApprove: boolean = false;
  grnId: number;
  claimType: string;
  btnName: String;
  userType: any;
  dealerEmployeeId: number;
  invoiceId: number;
  dealerId: number;
  draftFlag: Boolean = false;
  // isApprove:boolean=false;
  editSpareGRNId: any;
  editSpareInvoiceId: number
  headerId: number;
  editGrnDealerId: any;
  attachmentList: any;
  viewClaimType: boolean = false;
  dialogMsg: any;
  loginKubota:any;
  showDownloadPdf:boolean=false
  constructor(
    private presenter: SpareDescripancyClaimPresenter,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toaster: ToastrService,
    private dialog: MatDialog,
    private service: SpareClaimService,
    private localStorage: LocalStorageService,
    private store: SpareDescripancyClaimStore,
    private datePipe:DatePipe
  ) { }

  ngOnInit() {
    this.checkForOperation();
    this.createSpareDescripancyForm = this.presenter.createSpareDesClaimForm;
    this.partDetailsForm = this.presenter.partDetailForm;
    this.spareDescApprovalForm = this.presenter.approvalForm;
    this.attachementForm=this.presenter.createAttachmentForm;
    this.userType = this.localStorage.getLoginUser();
    if(this.userType.userType=="kubota"){
      this.loginKubota=true;
    }else{
      this.loginKubota=false;
    }
    this.dealerEmployeeId = this.userType.id;
    if(this.isCreate){
      const date=new Date()
      this.createSpareDescripancyForm.get('claimDate').patchValue(this.datePipe.transform(date,'dd-MM-yyyy'))
    }
   
  }

  checkForOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      this.activeRoute();
      this.presenter.createSpareDesClaimForm.disable();
      this.presenter.approvalForm.disable();
    }

    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      this.presenter.createSpareDesClaimForm.disable()
      this.activeRoute();
    } else if (this.presenter.operation == Operation.APPROVE) {
      this.isApprove = true
      this.presenter.createSpareDesClaimForm.disable()
      this.activeRoute();
    }
    else if (this.presenter.operation == Operation.REJECT) {
      this.isReject = true
      this.presenter.createSpareDesClaimForm.disable()
      this.activeRoute();
    }
    else {
      this.isCreate = true
    }

  }

  private activeRoute() {
    this.activatedRoute.queryParamMap.subscribe(qParam => {
      if (qParam.has('id')) {
         const decodedClaimNo = atob(qParam['params'].id);
        this.viewSpareDescripancyClaim(decodedClaimNo)
      }

    });
  }
// View For Spare Descripancy Claim
  viewSpareDescripancyClaim(id: any) {
    this.service.viewSpareDescripancy(id).subscribe(res => {
      if (res) {
        
        this.editSpareGRNId = res['claimViewDto']['sparePartGrn'].id? res['claimViewDto']['sparePartGrn'].id:null;
        this.editSpareInvoiceId = res['claimViewDto']['accpacSparePartInvoice'].id?res['claimViewDto']['accpacSparePartInvoice'].id:null
        this.headerId = res['claimViewDto'].id?res['claimViewDto'].id:null;
        this.editGrnDealerId = res['claimViewDto']['dealerMaster'].id?res['claimViewDto']['dealerMaster'].id:null;
        this.createSpareDescripancyForm.get('claimType').patchValue(res['claimViewDto'].claimType);
        this.createSpareDescripancyForm.get('claimNo').patchValue(res['claimViewDto'].claimReqNo);
        this.createSpareDescripancyForm.get('claimDate').patchValue(res['claimViewDto'].claimDate);
        this.createSpareDescripancyForm.get('claimRaisedBy').patchValue( res['claimViewDto']['dealerEmployeeMaster'].employeeCode? res['claimViewDto']['dealerEmployeeMaster'].employeeCode:null);
        this.createSpareDescripancyForm.get('status').patchValue(res['claimViewDto'].status);
        this.createSpareDescripancyForm.get('grnNo').patchValue(res['claimViewDto']['sparePartGrn'].spareGrnNumber);
        this.createSpareDescripancyForm.get('grnDate').patchValue(res['claimViewDto']['sparePartGrn'].grnDate);
        this.createSpareDescripancyForm.get('noOfBoxSent').patchValue(res['claimViewDto']['sparePartGrn'].noOfBoxesSent);
        this.createSpareDescripancyForm.get('kaiInvoiceNo').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].accpacInvoiceNo);
        this.createSpareDescripancyForm.get('invoiceDate').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].accpacInvoiceDate);
        this.createSpareDescripancyForm.get('noofBoxesRecieved').patchValue(res['claimViewDto']['sparePartGrn'].noOfBoxesReceived);
        this.createSpareDescripancyForm.get('mode').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].grnDate);
        this.createSpareDescripancyForm.get('transporter').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].transporter);
        this.createSpareDescripancyForm.get('lrNo').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].lrNo);
        this.createSpareDescripancyForm.get('mobileNo').patchValue(res['claimViewDto']['dealerEmployeeMaster'].mobileNo);
        this.createSpareDescripancyForm.get('dealerCode').patchValue(res['claimViewDto']['sparePartGrn']['invoiceNumber'].dealerCode);
        if (res['claimViewDto'].claimType === "MRR") {
          this.viewClaimType = true;
        } else {
          this.viewClaimType = false
        }
        if(res['claimViewDto'].status=='Approved' || res['claimViewDto'].status=='Reject'){
          this.showDownloadPdf=true
        }else{
             this.showDownloadPdf=false
        }

        
      }
      if (res && res['claimViewDto'].discrepancyClaimDtls !=null ||res && res['claimViewDto'].discrepancyClaimDtls.length>0 ) {
        res['claimViewDto'].discrepancyClaimDtls.forEach((ele: any) => {
          const group = this.store.partDetails();
          Object.keys(group.controls).forEach((key) => {
            group.patchValue({
              invoiceQty: ele.invoiceQty ? ele.invoiceQty : null,
              receiptQty: ele.receiptQty ? ele.receiptQty : null,
              shortQty: ele.shortQty ? ele.shortQty : null,
              damageQty: ele.damageQty ? ele.damageQty : null,
              partId: ele.partMaster.id ? ele.partMaster.id : null,
              partMaster: ele.partMaster.itemNo ? ele.partMaster.itemNo : null,
              itemDesc: ele.partMaster.itemDescription ? ele.partMaster.itemDescription : null,
              returnQty: ele.returnQty ? ele.returnQty : null,
              value: ele.value ? ele.value : 0,
              dealerRemarkReasons: ele.dealerRemarkReasons ? ele.dealerRemarkReasons : null,
              kaiAcceptedQty: ele.kaiAcceptedQty ? ele.kaiAcceptedQty : null,
              kaiRemarkReason: ele.kaiRemarkReason ? ele.kaiRemarkReason : null,
              id: ele.id ? ele.id : null,
            });
          });
          if (this.isView ) {
            group.disable();
            // group.controls.kaiAcceptedQty.enable();
            // group.controls.kaiRemarkReason.enable();
          }else if(this.isApprove){
            group.disable();
            group.controls.kaiAcceptedQty.enable();
            group.controls.kaiRemarkReason.enable();
          }
          this.partDetailsForm.controls.push(group);
        }
        )
      }
      if (res && res.claimAttachment) {
        this.attachmentList = res.claimAttachment
      }
      if(res && res['claimViewDto'].status=='Approved'){
        const submitAcceptanceDate = this.datePipe.transform(res['kaiRemarks'].kaiAcceptanceDate, 'dd-MM-yyyy');
        const settlementAcceptanceDate = this.datePipe.transform(res['kaiRemarks'].kaiSettlementDate, 'dd-MM-yyyy');
        this.spareDescApprovalForm.get('remarkForDealer').patchValue(res['kaiRemarks'].remarkForDealer);
        this.spareDescApprovalForm.get('internalKaiRemark').patchValue(res['kaiRemarks'].internalKaiRemark);
        this.spareDescApprovalForm.get('creditNoteNo').patchValue(res['kaiRemarks'].creditNoteNo);
        this.spareDescApprovalForm.get('creditNoteDate').patchValue(res['kaiRemarks'].creditNoteDate);
        this.spareDescApprovalForm.get('creditNoteAmount').patchValue(res['kaiRemarks'].creditNoteAmount);
        this.spareDescApprovalForm.get('kaiAcceptanceDate').patchValue(submitAcceptanceDate);
        this.spareDescApprovalForm.get('kaiSettlementDate').patchValue(settlementAcceptanceDate);
      }
    }
    )

  }



  exitForm() {
    this.router.navigate(['../'], { relativeTo: this.activatedRoute })
  }
  validateForm(btnType: any) {
    let flag: boolean = false;
    this.btnName = btnType;

    if (this.btnName === 'save') {
      this.draftFlag = true;
    } else {
      this.draftFlag = false;
    }

    if (this.createSpareDescripancyForm.invalid) {
      this.createSpareDescripancyForm.markAllAsTouched();
      this.toaster.error("Please Fill All Required Fields !");
      return false;
    }
   
    if (this.partDetailsForm) {
      const hasInvalidPartDetails = this.partDetailsForm.controls.some((ele: any) => {
        if (ele.status === 'INVALID') {
          this.toaster.error('Please enter Part Details!');
          ele.markAllAsTouched();
          return true; // Control is invalid
        }
        return false; // Control is valid
      });

      if (hasInvalidPartDetails) {
        return false; // Exit the function if any part detail is invalid
      }
    } else {

    }
    if (flag) {
      return false;
    }
    this.openConfirmDialog();
  }


  private openConfirmDialog() {
    let message = `Do you want to ${this.btnName} Spare Descripancy Claim?`
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result == 'Confirm') {
        this.submitSpareDescripancyClaims()
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }


  

  submitSpareDescripancyClaims() {
    const submitSpareClaim = {} as any;
    submitSpareClaim.discrepancyClaim = this.createSpareDescripancyForm.value;
    delete submitSpareClaim.discrepancyClaim.grnNo
    if (this.isEdit) {
      submitSpareClaim.discrepancyClaim.id = this.headerId;
    }
    submitSpareClaim.discrepancyClaim['accpacSparePartInvoice'] = { 'id': this.invoiceId ? this.invoiceId : this.editSpareInvoiceId };
    submitSpareClaim.discrepancyClaim['sparePartGrn'] = { 'id': this.grnId ? this.grnId : this.editSpareGRNId };
    submitSpareClaim.discrepancyClaim['dealerEmployeeMaster'] = { 'id': this.dealerEmployeeId };
    submitSpareClaim.discrepancyClaim['dealerMaster'] = {'id':this.dealerId ? this.dealerId : this.editGrnDealerId};
    
    submitSpareClaim.discrepancyClaim.draftFlag = this.draftFlag;
    submitSpareClaim.multipartFileList = this.presenter.photo1;
    submitSpareClaim.discrepancyClaim.discrepancyClaimDtls = this.partDetailsForm.getRawValue();
    submitSpareClaim.discrepancyClaim.discrepancyClaimDtls.forEach((res, index) => {

      if (typeof res.partMaster !== 'object') {
        submitSpareClaim.discrepancyClaim.discrepancyClaimDtls[index].partMaster = {
          'id': res.partId,
          // 'dealerCode': '22222'
        };
      }
    });

    this.service.saveSpareDescripancyClaim(submitSpareClaim).subscribe(
      (res) => {
        if (res['status'] == '200') {
          this.toaster.success(res['message']);
          this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        } else if (res && res['status'] === '403') {
          this.toaster.error('Access denied. Please check your permissions.');
        } else {
          console.error('Error during Create', res);
          this.toaster.error('An error occurred during Create');
        }
      },
      (error) => {
        this.toaster.error(error);
      }
    );
    
  }


  

  clearForm() {
    this.createSpareDescripancyForm.reset();
    this.partDetailsForm.clear();
  }
  downloadPdf() {
    const postData={
       "claimId":this.headerId,
       "claimNo":this.createSpareDescripancyForm.get('claimNo').value,
       "printStatus":false
    }

    this.service.printSpareDescClaim(postData).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
   })
    // this.service.printSpareDescClaim(postData).subscribe(res=>{
    //   if(res){
    //     this.toaster.success("Pdf Download Successfully")
    //   }else{
    //     this.toaster.error("Pdf Download Failed!")
    //   }
    // })
  }

  getGrnId(id: any) {
    this.grnId = id;
  }
  getClaimType(type: any) {
    this.claimType = type;
  }
  grnDealerId(dealerId: any) {
    this.dealerId = dealerId
  }

  getInvoiceId(invoiceId: any) {
    this.invoiceId = invoiceId
  }

  approveOrReject(approvalType: any) {
    let flag: boolean = false;
    if (this.createSpareDescripancyForm.invalid) {
      this.createSpareDescripancyForm.markAllAsTouched();
      this.toaster.error("Please Fill All Required Fields !");
      return false;
    }
    if (this.spareDescApprovalForm.invalid) {
      this.spareDescApprovalForm.markAllAsTouched();
      this.toaster.error("Please Fill Approval Details!");
      return false;
    }
    if (this.partDetailsForm) {
      const hasInvalidPartDetails = this.partDetailsForm.controls.some((ele: any) => {
        if (ele.status === 'INVALID') {
          this.toaster.error('Please enter Part Details!');
          ele.markAllAsTouched();
          return true; // Control is invalid
        }
        return false; // Control is valid
      });

      if (hasInvalidPartDetails) {
        return false; // Exit the function if any part detail is invalid
      }
    } else {

    }
    if (flag) {
      return false;
    }
    this.openApprovalConfirmationBox(approvalType);
  }


  private openApprovalConfirmationBox(approvalType) {
    this.dialogMsg = approvalType;
    let message = 'Do you want to ' + approvalType + ' Spare Descripancy Claim?';
    const confirmationData = this.setConfirmationModalDatas(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result: DialogResult) => {
      if (result && result.button === 'Confirm') {
        this.approveSpareDescClaim(result.remarkText, approvalType);
      }
    });
  }
  private setConfirmationModalDatas(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;

    confirmationData.buttonName = ['Confirm', 'Cancel'];

    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }

    if (this.dialogMsg.toLocaleLowerCase() === 'Reject') {
      confirmationData.remarkConfig = {
        source: Source.REJECT
      }
    }
    return confirmationData;
  }
  private approveSpareDescClaim(remark, approvalStatus) {
    const approvesubmitSpareClaim = {} as any;
    approvesubmitSpareClaim.kaiAdditionalRemarks = this.spareDescApprovalForm.value;
    approvesubmitSpareClaim.claimId = this.headerId,
      approvesubmitSpareClaim.remark = remark
    approvesubmitSpareClaim.approvalStatus = approvalStatus
    // approvesubmitSpareClaim.discrepancyClaimDtl = this.partDetailsForm.getRawValue();
      const partDetails=this.partDetailsForm.getRawValue();
      const specificValuesList = partDetails.map((detail: any) => ({
        id: detail.id,
        kaiAcceptedQty:Number(detail.kaiAcceptedQty),
        kaiRemarkReason:detail.kaiRemarkReason,
        // Add other specific properties as needed
      }));
      approvesubmitSpareClaim.discrepancyClaimDtl = specificValuesList;
      this.service.approveOrReject(approvesubmitSpareClaim).subscribe(
        (approveData) => {
          if (approveData) {
            this.toaster.success(approveData['message']); 
            this.router.navigate(['../'], { relativeTo: this.activatedRoute });
            // Replace with your actual success message
          } else {
            this.toaster.error('Approval failed'); // Replace with your actual error message
          }
        },
        (error) => {
          console.error('Error during approval', error);
          this.toaster.error('An error occurred during approval'); // Replace with your actual error message
        }
      );
      
  }
}
