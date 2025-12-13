import { Component, OnInit,Input } from '@angular/core';
import { Observable } from 'rxjs';
import { PcrPagePresenter } from './pcr-page.presenter';
import { FormGroup, FormArray,Validators } from '@angular/forms';
import { PcrPageStore } from './pcr-page.store';
import { ActivatedRoute, Router } from '@angular/router';
import { PcrWebService } from '../pcr/pcr-web.service';
import { PcrPageWebService } from '../pcr-page/pcr-page-web.service';
import { SubmitPCR, SparePartRequisitionItemList,LabourList,OutsideChargeList,ApprovalLabourORCharges ,JobCardPart, JobCardView, WarrantyPcrPhotos, ApproveQuantity, ViewPCRDomain, ViewPcr, UpdatePcr, UpdateServiceJobCard, SubmitUpdatePcr, WarrantyPcrImplement, WarrantyPcrVideos} from '../../domain/product-concern-report.domain';
import { MatDialog } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData } from '../../../../../confirmation-box/confirmation-box.component';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { IFrameMessageSource, IFrameService } from 'src/app/root-service/iFrame.service';
import { Location } from '@angular/common';
import { DateService } from 'src/app/root-service/date.service';


@Component({
  selector: 'app-pcr-page',
  templateUrl: './pcr-page.component.html',
  styleUrls: ['./pcr-page.component.scss'],
  providers: [PcrPageStore, PcrPagePresenter, PcrWebService, PcrPageWebService]
})
export class PcrPageComponent implements OnInit {
  machineMasterId: number;
  rejectList : [];
  private submitPCR = {} as SubmitPCR;
  failurePartDetails: JobCardPart[];
  private pcrDetails: JobCardView;
  allowVideoUpload:boolean=false;
  btnName: string;
  private jobId: any;
  isView:boolean=false;
  isApprove:boolean=false;
  viewWcr :boolean=false;
  warrantyPcrPhotos: WarrantyPcrPhotos[];
  failurePartForm: FormArray;
  uploadFileForm: FormGroup;
  viewName: any;
  rejectAllow:boolean=true
  holdAllow:boolean=true
  approveAllow:boolean=true
  approvalHierDetails : any[]
  approveQuantity = {} as ApproveQuantity;
  viewPCRDetails: ViewPcr;
  pcrForm: FormGroup;
  serviceHistoryForm: FormArray;
  implementForm: FormArray;
  outsideChargeForm: FormArray;
  labourChargeForm: FormArray;
  implementFormLength: boolean;
  showWcrBtn = false;
  showSaleGoodwillBtn = false;
  showServiceGoodwillBtn = false;
  isWarrantyPhoto: boolean;
  operation: string;
  searchFilter: any;
  showGoToJc:boolean
  videoUploadFlag:boolean
  // warrantyPcrVideos: WarrantyPcrVideos[];
  warrantyPcrVideos:any
  jcNofromWpcr:string
  isSubmitDisable:boolean = false;
  managementCheck:boolean = false;
  loginUser:any;
  isShowReason:boolean = true;
  isKai:boolean=false;
  constructor(
    private pcrPagePresenter: PcrPagePresenter,
    private activatedRoute: ActivatedRoute,
    private pcrPageWebService: PcrPageWebService,
    public dialog: MatDialog,
    private router: Router,
    private toastr: ToastrService,
    private iFrameService: IFrameService,  
    private location:Location,
    private dateService: DateService
  ) { 
    this.loginUser = localStorage.getItem('itldisUser');
    this.loginUser = JSON.parse(JSON.parse(JSON.stringify(this.loginUser)))
    if(this.loginUser.userType!='dealer'){
      this.isKai = true;
    }
  }

  ngOnInit() {

    this.pcrForm = this.pcrPagePresenter.PCRForm;
    this.implementForm = this.pcrPagePresenter.ImplementForm;
    this.serviceHistoryForm = this.pcrPagePresenter.ServiceHistoryForm;
    this.failurePartForm = this.pcrPagePresenter.FailurePartForm;
    this.uploadFileForm = this.pcrPagePresenter.uploadFileForm;
    this.outsideChargeForm = this.pcrPagePresenter.OutsideLabourChargeForm;
    this.labourChargeForm = this.pcrPagePresenter.LabourChargeForm;
    this.pcrPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    this.operation = this.pcrPagePresenter.operation;
    this.activeRoute();
    this.jcToPcr()
    
    this.pcrPageWebService.managementApprovalCheckSubject.subscribe(isCheck => {
      this.managementCheck = isCheck;
    })
  }

  allowVideoUplodEvent(isAllow){
      this.allowVideoUpload = isAllow;
      this.approveAllow = !isAllow;
      this.rejectAllow = !isAllow;
  
  }
  
  private activeRoute() {
    this.activatedRoute.queryParams.subscribe((qParam) => {
      this.viewName = qParam.name;
      this.searchFilter = qParam.searchFilter;
      if(this.viewName == 'approve'){
          this.isApprove = true;
      }
      if (this.pcrPagePresenter.operation === Operation.JOBCARD) {
        this.jobId = qParam.id
        this.getPCRDetails(qParam.id);
        
        this.implementFormLength =  this.implementForm.length == 0;
        
        this.pcrPagePresenter.addRowInImplement();
        this.pcrPageWebService.getSystemDateUrl().subscribe(res => {
          this.pcrPagePresenter.currentDate = res;
          this.pcrPagePresenter.PCRForm.get('pcrDate').patchValue(res);
        });
        // this.pcrPagePresenter.PCRForm.get('pcrDate').patchValue(this.pcrPagePresenter.currentDate);
        
      } else if(this.pcrPagePresenter.operation === Operation.VIEW) {
        this.warrantyPcrView(atob(qParam.pcrNo));
        this.isView = true;
        this.implementForm.valueChanges.subscribe(val => {
          if(val) {
            this.implementFormLength = this.implementForm.length > 0;
          }
        });
      } else if (this.pcrPagePresenter.operation === Operation.EDIT) {
          this.warrantyPcrView(atob(qParam.pcrNo));
          this.implementForm.valueChanges.subscribe(val => {
            if(val) {
              this.implementFormLength = this.implementForm.length > 0;
            }
          });
      }
    })
  }
  
 /*  private checkFormOperation() {
    if (this.pcrPagePresenter.operation === Operation.JOBCARD) {
      this.implementFormLength =  this.implementForm.length == 0;
      this.pcrPagePresenter.addRowInImplement();
      this.pcrService.getSystemDateUrl().subscribe(res => {
        this.pcrPagePresenter.PCRForm.get('pcrDate').patchValue(res);
      });
    } else if(this.pcrPagePresenter.operation === Operation.VIEW) {
      this.implementForm.valueChanges.subscribe(val => {
        if(val) {
          this.implementFormLength = this.implementForm.length > 0;
        }
      });
    }
    this.activeRoute();
  } */

  private warrantyPcrView(pcrNo: string) {

    const service = this.pcrPagePresenter.operation === Operation.VIEW ? this.pcrPageWebService.warrantyPcrView(pcrNo) : this.pcrPageWebService.updatePcrFromJobcard(pcrNo)
    
    service.subscribe(res => {
    this.jcNofromWpcr =res.warrantyPcrViewDto.serviceJobCard.jobCardNo   /* added by vinay to goToJobcard*/
    this.viewPCRDetails = res;
    const serviceJobCard = this.viewPCRDetails.warrantyPcrViewDto.serviceJobCard;
    const machineInventory = serviceJobCard.machineInventory;
    const sparePartRequisitionItem = this.viewPCRDetails.warrantyPart;
    this.approvalHierDetails = this.viewPCRDetails.approvalDetails;
    const addFailurePart = {} as JobCardPart;
    const goodwillEnable = this.viewPCRDetails.goodwillEnable;
    if(goodwillEnable){
        if(goodwillEnable['goodwillService']){
            this.showServiceGoodwillBtn = true;
        }
        if(goodwillEnable['goodwillSales']){
            this.showSaleGoodwillBtn = true;
        }
    }
    
    if(this.viewPCRDetails.warrantyPcrViewDto.status == 'Approved' && serviceJobCard.invoicedFlag && this.viewPCRDetails.warrantyPcrViewDto.createWcr) {
      
      this.showWcrBtn = true;

    }
    if(this.viewPCRDetails.warrantyPcrViewDto.wcrNo){
        this.viewWcr = true
        this.showWcrBtn = false;
    }
    this.viewPCRDetails.warrantyPcrViewDto.warrantyPcrImplements.forEach(elt => {
      this.pcrPagePresenter.addRowInImplement(elt);
    });
    if(this.viewPCRDetails.outSideLabourCharge){ 
        this.viewPCRDetails.outSideLabourCharge.forEach(elt => {
            this.pcrPagePresenter.addRowInOutsideCharge(elt, this.viewName);
        });
    }
    if(this.viewPCRDetails.labourCharge){
        this.viewPCRDetails.labourCharge.forEach(elt => {
            this.pcrPagePresenter.addRowInLabourCharge(elt, this.viewName);
        })
    }
    if(this.viewPCRDetails && this.viewPCRDetails.warrantyPeriod){
      this.pcrForm.get('warrantyEndDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(this.viewPCRDetails.warrantyPeriod['warrantyEndDate']));
      this.pcrForm.get('totalWarrantyHour').patchValue(this.viewPCRDetails.warrantyPeriod['totalWarrantyHour']);
      this.pcrForm.get('warrantyType').patchValue(this.viewPCRDetails.warrantyPeriod['warrantyType']);
    }
    this.pcrForm.disable();
    this.implementForm.disable();
    this.machineMasterId = serviceJobCard.machineInventory.machineMaster.id;

    if(this.viewName == 'approve') {
      this.pcrForm.get('kaiRemarks').enable();
      this.pcrForm.get('kaiRemarks').setValidators(Validators.required);
      this.pcrForm.get('allowVideoUpload').enable();

      if(this.pcrForm.get('totalHour').value > this.pcrForm.get('totalWarrantyHour').value || 
                this.pcrForm.get('warrantyEndDate').value < this.pcrPagePresenter.currentDate){
        this.btnName = 'EXPIRED'
      }
    } 
    if(this.pcrPagePresenter.operation == Operation.EDIT) {
      this.pcrForm.get('dealerRemarks').enable();
    } 

    sparePartRequisitionItem.forEach((elt) => {
      
      addFailurePart.claimQuantity = this.pcrPagePresenter.operation == Operation.EDIT ? elt.claimQuantity : elt.pcrQuantity;
      addFailurePart.approvedQuantity = elt.approvedQuantity;
      addFailurePart.description =  elt.description;
      addFailurePart.partNo = elt.partNo;
      addFailurePart.id = elt.sparePartRequisitionId;
      addFailurePart.failureId = elt.failureId;
      addFailurePart.failureDescription = elt.complaintDescription;
      addFailurePart.code = elt.code;
      this.pcrPagePresenter.addRowInFailurePart(addFailurePart, this.viewName);
    })

    this.warrantyPcrVideos = this.viewPCRDetails.warrantyPcrViewDto
    this.warrantyPcrPhotos = this.viewPCRDetails.warrantyPcrViewDto.warrantyPcrPhotos;
    
    this.isWarrantyPhoto = this.warrantyPcrPhotos.length > 0;
    if(this.viewPCRDetails.files && this.viewPCRDetails.files.length>0){
      this.viewPCRDetails.files.forEach(f => {
        this.warrantyPcrPhotos.push(f);
      })
    }
    
    const viewPCRDetails =  this.pcrPagePresenter.patchViewPCRForm(this.viewPCRDetails.warrantyPcrViewDto);
    this.serviceHistory(machineInventory.vinId, viewPCRDetails);
    
    if(this.viewPCRDetails.warrantyPcrViewDto.delayReason){
      this.isShowReason = false;
      //this.pcrForm.get('delayReasonSelect').patchValue(this.viewPCRDetails.warrantyPcrViewDto.delayReason);
    }
    
    if(this.isApprove){
      this.pcrPageWebService.getSystemDateUrl().subscribe(response => {
        var currentDate = new Date(response);
        var pcrDate:Date = new Date(this.viewPCRDetails.warrantyPcrViewDto.pcrDate);
        var diff = currentDate.valueOf() - pcrDate.valueOf();
        var diffInHours = diff/1000/60/60;
        if(diffInHours>24){
          this.pcrForm.controls.delayReasonSelect.enable();
        }
      });
    }
    }, err => {
      this.toastr.error('Error while fetching Goodwill details','Error');
    });
  }

  private getPCRDetails(id: string) {
    this.pcrPageWebService.getPCRDetails(id).subscribe(pcrResponse => {
      this.jcNofromWpcr = pcrResponse.jobCardViewDto.jobCardNo
      this.pcrDetails = pcrResponse.jobCardViewDto;
      this.machineMasterId = this.pcrDetails.machineMasterId;
      this.failurePartDetails = pcrResponse.jobCardPartDto;
      console.log(this.failurePartDetails,'failurePartDetails')
      this.isWarrantyPhoto = true;
      
      if(this.failurePartDetails.length > 0) {
        this.failurePartDetails.forEach(elt => {
          elt.id = elt.sparePartRequisitionId;
          this.pcrPagePresenter.addRowInFailurePart(elt);
        })
      }

      if(pcrResponse.outSideLabourCharge && pcrResponse.outSideLabourCharge.length>0){
            
          pcrResponse.outSideLabourCharge.forEach(elt => {
              this.pcrPagePresenter.addRowInOutsideCharge(elt, this.viewName);
          });
      }
      
      if(pcrResponse.labourCharge && pcrResponse.labourCharge.length>0){
          
          pcrResponse.labourCharge.forEach(elt => {
              this.pcrPagePresenter.addRowInLabourCharge(elt, this.viewName);
          });
      }
      
      this.serviceHistory(this.pcrDetails.machineInventoryId, this.pcrDetails);

      
      if(pcrResponse && pcrResponse.warrantyPeriod){
        this.pcrForm.get('warrantyEndDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(pcrResponse.warrantyPeriod['warrantyEndDate']));
        this.pcrForm.get('totalWarrantyHour').patchValue(pcrResponse.warrantyPeriod['totalWarrantyHour']);
        this.pcrForm.get('warrantyType').patchValue(pcrResponse.warrantyPeriod['warrantyType']);
      }

      this.pcrForm.get('customerName').valueChanges.forEach(val => {
        if(val == ' ' || val == null) {
          this.pcrPagePresenter.clearPcrFormValidation();
        }
      });
    }, err => {
      this.toastr.error('Error while fetching PCR details','Error');
    });
   }

  private serviceHistory(id: number, pcrData?: JobCardView) {
    this.pcrPageWebService.serviceHistory(id).subscribe(serviceHistoryResponse => {
        const serviceHistoryDetails = serviceHistoryResponse;
        if(serviceHistoryDetails.length > 0) {
          serviceHistoryDetails.forEach(elt => {
            this.pcrPagePresenter.addRowInServiceHistory(elt);
          })
        }
        this.pcrPagePresenter.patchFormVal({...pcrData, ...{jobCardHistory: serviceHistoryDetails}});
        
    }, err => {
      this.toastr.error('Error while fetching service history','Error');
    });
  }

  formValid(btnType: string) {
     this.btnName = btnType;
     this.pcrForm.controls.delayReasonSelect.setValidators(null);
     
    if(btnType.toLowerCase()=='approve'){
      if(this.pcrForm.controls.delayReasonSelect.enabled){
          if(this.pcrForm.controls.delayReason.value){
          }else{
            this.pcrForm.controls.delayReasonSelect.setValidators([Validators.required]);
            this.pcrForm.controls.delayReasonSelect.updateValueAndValidity();
            this.toastr.error('Select Reason for Delay');
            return false;
          }
        }
    }

     if(this.pcrForm.valid && this.failurePartForm.valid && this.uploadFileForm.valid && (this.implementForm.valid || this.implementForm.disabled) &&
             this.labourChargeForm.valid && this.outsideChargeForm.valid) {
        if(btnType!='reject' && (this.pcrForm.get('totalHour').value > this.pcrForm.get('totalWarrantyHour').value || 
                  this.pcrForm.get('warrantyEndDate').value < this.pcrPagePresenter.currentDate)){
          this.btnName = 'EXPIRED'
        }
        this.openConfirmDialog();
     }
     else {
      this.markFormAsTouched();
     }
  }

  markFormAsTouched() {
    this.pcrForm.markAllAsTouched();
    this.implementForm.markAllAsTouched();
    this.failurePartForm.markAllAsTouched();
    this.uploadFileForm.markAllAsTouched();
    this.toastr.error('Please fill all mandatory fields', 'Error');
  }


  openConfirmDialog(){

    let message = `Do you want to ${this.btnName} PCR?`;
    if(this.btnName == 'EXPIRED'){
      if(this.isApprove){
        message = "Warranty Period is lapsed for this machine. Do you really want approve this PCR?" ;
      }else{
        message = "Warranty Period is lapsed for this machine are you sure you want to proceed further?" ;
      }
    }
    const confirmationData = this.setConfirmationModalData(message, this.btnName);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if(result == 'Confirm' || result.button == 'Confirm') {
        this.isSubmitDisable = true;
        switch (this.btnName) {
          case 'submit':
            this.submitPCRForm();
            break;
          case 'approve':
            this.approve('Approve',result.rating, '');
            break;  
          case 'hold':
              this.approve('Hold',0,'');
              break;  
          case 'reject':
              this.approve('Reject',0, result.reason);
              break;      
          case 'update':
            this.updatePcr();
            break;      
          default:
            console.log('No such operation exist.');
            this.isSubmitDisable = false;
            break;
        }
      }
    });
  }
  private setConfirmationModalData(message: string, btnName?:string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    if(btnName && btnName=='reject'){
      confirmationData.buttonType='PCR_REJECT';
      this.pcrPageWebService.getRejectionReasonLov().subscribe(response => {
        this.rejectList = response['result'];
        confirmationData.rejectList = this.rejectList;
      });
    }
    else if(btnName && btnName=='approve')
      confirmationData.buttonType='PCR_APPROVE';  
      
    return confirmationData;
  }

  private checkImplementFormValue(formValue) {
    const implementForm = formValue.map(elt => {
      if(elt.implement == null) {
        return null
      }
      else {
        return elt;
      }
    })
    return implementForm;
  }

  private collectSparePartRequisitionItemList() {
    const sparePartRequisitionItemList: SparePartRequisitionItemList[] = [];
    const failureParts = this.failurePartForm.getRawValue();
    
    failureParts.forEach((elt) => {
      sparePartRequisitionItemList.push({
        id: elt.id,
        warrantyMtPartFailureCode: elt.failureCode,
        pcrQuantity: elt.claimQuantity
      })
    })
    return sparePartRequisitionItemList;
  }
  private collectLabourChargeList() {
      const labourList: LabourList[] = [];
      const labours = this.labourChargeForm.getRawValue();
      labours.forEach((elt) => {
          labourList.push({
          labourChargeId: elt.labourChargeId,
          pcrAmount: elt.claimAmount
        });
      })
      return labourList;
    }
  private collectOutsideChargeList() {
      const outsideChargeList: OutsideChargeList[] = [];
      const charges = this.outsideChargeForm.getRawValue();
      
      charges.forEach((elt) => {
          outsideChargeList.push({
          jobcodeId: elt.jobcodeId,
          pcrAmount: elt.claimAmount
        });
      })
      return outsideChargeList;
    }

    private collectAttachmentVideoList() {
      const attachement = this.uploadFileForm.getRawValue()
      
    }
  private submitPCRForm() {
    const pcrDetails = this.pcrForm.getRawValue();
    this.submitPCR.warrantyPcr = pcrDetails;
    this.submitPCR.warrantyPcr.serviceJobCard = {id:Number(this.jobId), ...{sparePartRequisitionItem:this.collectSparePartRequisitionItemList()}, ...{outsideJobCharge:this.collectOutsideChargeList()}, ...{labourCharge:this.collectLabourChargeList()}};
    
    this.submitPCR.warrantyPcr.warrantyPcrImplements = this.checkImplementFormValue(this.implementForm.value);
    this.submitPCR.warrantyPcr.draftFlag = this.btnName == 'save' ? true : false;
    if(this.pcrPagePresenter.collectFiles) {
      this.submitPCR.multipartFileList = this.pcrPagePresenter.collectFiles;
    }
    
    this.pcrPageWebService.saveWarrantyPcr(this.submitPCR).subscribe(saveRes => {
      if(saveRes){
        this.toastr.success(saveRes['message'], 'Success')
        this.router.navigate(['..'], {relativeTo: this.activatedRoute});
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving details', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving details', 'Transaction failed');
    })
  }

  private approve(approvalStatus, rating, reason) {
    this.approveQuantity.kaiRemarks = this.pcrForm.get('kaiRemarks').value;

    if(this.pcrForm.controls.delayReason.value){
      this.approveQuantity.delayReason = this.pcrForm.controls.delayReason.value;
    }
    this.approveQuantity.warrantyPcrId = this.viewPCRDetails.warrantyPcrViewDto.id;
    this.approveQuantity.rating = rating;
    this.approveQuantity.reason = reason;
    const labours:ApprovalLabourORCharges[] = [];
    const outside:ApprovalLabourORCharges[] = [];
    if(this.failurePartForm)
        this.approveQuantity.pcrApprovalParts = this.failurePartForm.value;
    if(this.outsideChargeForm){
        this.outsideChargeForm.getRawValue().forEach( elt => {
            outside.push({
                approvedAmount : elt.approvedAmount,
                id : elt.jobcodeId
            });
        });
    }
        
    if(this.labourChargeForm){
        this.labourChargeForm.getRawValue().forEach( elt => {
            labours.push({
                approvedAmount : elt.approvedAmount,
                id : elt.labourChargeId
            });
        });
    }
    this.approveQuantity.pcrApprovalOutsideCharges = outside;
    this.approveQuantity.pcrApprovalLabours = labours;
    
    this.approveQuantity.approvalStatus = approvalStatus;
    this.approveQuantity.allowVideoUpload = this.allowVideoUpload;
    this.approveQuantity.managementCheck = this.managementCheck;
    this.pcrPageWebService.approveWarrantyPcr(this.approveQuantity).subscribe(res => {
      if(res){
        this.toastr.success(res['message'], 'Success')
        this.router.navigate(['..'], {relativeTo: this.activatedRoute});
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving details', 'Transaction failed');
      }
    }, err => {
      this.isSubmitDisable = false;
      this.toastr.error('Error generated while saving details', 'Transaction failed');
    })
  }

  private updatePcr() {
    // debugger
    const submitUpdatePcr = {} as SubmitUpdatePcr;
    const updatePcr = {} as UpdatePcr;
    updatePcr.serviceJobCard = {} as UpdateServiceJobCard;
    updatePcr.id = this.viewPCRDetails.warrantyPcrViewDto.id;
    updatePcr.dealerRemarks = this.pcrForm.get('dealerRemarks').value;
    updatePcr.serviceJobCard.id = this.viewPCRDetails.warrantyPcrViewDto.serviceJobCard.id;
    
    
    updatePcr.serviceJobCard.sparePartRequisitionItem = this.collectSparePartRequisitionItemList();
    updatePcr.serviceJobCard.labourCharge = this.collectLabourChargeList();
    updatePcr.serviceJobCard.outsideJobCharge = this.collectOutsideChargeList();
    
    if(this.pcrPagePresenter.collectFiles) {
      submitUpdatePcr.multipartFileList = this.pcrPagePresenter.collectFiles;
    }
    if(this.pcrPagePresenter.collectVideo) {
      submitUpdatePcr.multipartVideoList = this.pcrPagePresenter.collectVideo;
    }
    submitUpdatePcr.warrantyPcr = updatePcr;
    
    this.pcrPageWebService.updatePcr(submitUpdatePcr).subscribe(
      res => {
        if(res){
          this.toastr.success(res['message'], 'Success')
          this.router.navigate(['..'], {relativeTo: this.activatedRoute});
        }else{
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while updating details', 'Transaction failed');
        }
      }, 
      err => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while updating details', 'Transaction failed');
      }
    );
    
  }

  navigateToWcr() {
    this.router.navigate(['../../warrenty-claim-request/create'], {relativeTo: this.activatedRoute, queryParams: {pcrNo: this.viewPCRDetails.warrantyPcrViewDto.pcrNo, id:this.viewPCRDetails.warrantyPcrViewDto.id}});
  }
  navigateToGoodwill(enableType) {
    //this.router.navigate(['../../goodwill/create'], {relativeTo: this.activatedRoute, queryParams: {pcrNo: this.viewPCRDetails.warrantyPcrViewDto.pcrNo}});
      
   this.openConfirmDialogForGoodwillEnable(enableType);   
  }
  openConfirmDialogForGoodwillEnable(enableType){
      const message = `Do you want to enbale PCR for GoodWill?`;
      const confirmationData = this.setConfirmationModalData(message);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData
      });
      dialogRef.afterClosed().subscribe(result => {
        if(result == 'Confirm') {
            this.isSubmitDisable = true;
            this.pcrPageWebService.enablePcrForGoowill(this.viewPCRDetails.warrantyPcrViewDto.id, enableType).subscribe(res => {
                if(res){
                  this.showSaleGoodwillBtn = false;
                  this.showServiceGoodwillBtn = false;
                  this.toastr.success(res["message"], "Success");  
                }else{
                  this.isSubmitDisable = false;
                  this.toastr.error('Error generated while saving details', 'Transaction failed');
                }
            },error => {
                this.isSubmitDisable = false;
                this.toastr.error('Error generated while saving details', 'Transaction failed'); 
            })
        }
      });
    }


  navigateTViewoWcr(){
      this.router.navigate(['../../warrenty-claim-request/view'], {relativeTo: this.activatedRoute, queryParams: {wcrNo: btoa(this.viewPCRDetails.warrantyPcrViewDto.wcrNo), name:'pcr'}});
  } 
  
  // back() {
  //   this.router.navigate(['../'], {relativeTo: this.activatedRoute, queryParams: {searchFilter: this.searchFilter}})
  // }
  
  viewPrint(printStatus:string){
      this.pcrPageWebService.printPCR(this.pcrForm.get('pcrNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              // let headerContentDispostion = resp.headers.get('content-disposition');
              // let fileName = headerContentDispostion.split("=")[1].trim();
              // const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              // saveAs(file);

              const blobUrl = window.URL.createObjectURL(resp.body);
              const iframe = document.createElement('iframe');
              iframe.style.display = 'none';
              iframe.src = blobUrl;
              document.body.appendChild(iframe);
              iframe.contentWindow.print();

            }
       })
   }

   jcToPcr() {
      this.activatedRoute.queryParams.subscribe(qparams => {
        
        if (qparams.fromJobCartToPcr==='jcToPcr') {
          this.showGoToJc=true
        }
      })
    }
    viewPcrClick(){
	    // const url = 'service/customerService/job-card';
      //   this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url})
      // const url = 'service/customerService/job-card/edit';
      // this.iFrameService.sendRouteChangeRequest(IFrameMessageSource.SERVICE, { url,queryParam: {jcNofromWpcr:this.jcNofromWpcr}})
      this.location.back();
	  }
  
}
