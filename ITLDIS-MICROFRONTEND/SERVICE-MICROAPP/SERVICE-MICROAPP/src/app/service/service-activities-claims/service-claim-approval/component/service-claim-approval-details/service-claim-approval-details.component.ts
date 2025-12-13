import { Component, OnInit } from '@angular/core';
import { DateAdapter, MatCheckboxChange, MatDatepickerInput, MatDialog, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ServiceClaimApprovalDetailsService } from './service-claim-approval-details.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DialogResult, Source } from 'src/app/confirmation-box/confirmation-data';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Observable } from 'rxjs';
import { DateService } from 'src/app/root-service/date.service';

@Component({
  selector: 'app-service-claim-approval-details',
  templateUrl: './service-claim-approval-details.component.html',
  styleUrls: ['./service-claim-approval-details.component.scss'],
  providers:[ServiceClaimApprovalDetailsService]
})
export class ServiceClaimApprovalDetailsComponent implements OnInit {

  claimList:any[]=[];
  allChecked:boolean = false;
  claimIds:number[]=[];
  isView:boolean=false;
  isApprove:boolean=false;
  claimDetailsForm:FormGroup;
  wcrClaimDetailsForm:FormGroup;
  isCreate:boolean=false;
  id;
  docType:string;
  dealercodeList:any;
  wcrData:any;
  pcrData:any;
  jobCodeData:any;
  wcrType:any;
  todayDate = new Date()
  minDate: Date;
  newdate= new Date();
  maxDate: Date;
  approvalDetails=[];
  // approvalDetails = [
  //   { id: 1, approvedBy: "",approvalStatus: "Pending At Service Manager",isFinalApprover: "N",approvalDate:'22-02-2023',approverRemarks: "Done " },
  //   { id: 2, approvedBy: "",approvalStatus: "Pending At Sales Manager",isFinalApprover: "N",approvalDate:'22-02-2023',approverRemarks: "Done " },
  //   { id: 3, approvedBy: "",approvalStatus: "Pending at Zonal sales Manager",isFinalApprover: "N",approvalDate:'22-02-2023',approverRemarks: "Done " },
  //   { id: 4, approvedBy: "",approvalStatus: "Pending At HOD",isFinalApprover: "N",approvalDate:'22-02-2023',approverRemarks: "Done " },
  // //   { id: 1,  },
  // //   { id: 1, },
  // //  { id:1,},
  // //   { id: 1,  },
    
  // ];
  dialogMsg: string;
  constructor( private claimApprovalService: ServiceClaimApprovalDetailsService ,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private dateService: DateService,
    private fb : FormBuilder,
    private dialog:MatDialog) { }

  ngOnInit() {
    this.claimDetailsForm = this.fb.group({
      docNo : [null],
      docDate: [null],
      status: [null]
    });
    this.wcrClaimDetailsForm = this.fb.group({
      requestFrom : ['WCR Claim'],
      dealerId: [null],
      claimNo: [null],
      pcrNumber : [null],
      wcrType: [null],
      jobcardNumber: [null],
      fromDate: [null],
      toDate: [null]
    });
    const operationType = this.router.url.split('/');
    if(operationType[operationType.length-2]=='service-claim-approval'){
        this.docType = 'Service Claim';
    } else if(operationType[operationType.length-2]=='wcr-claim-approval'){
      this.docType = 'WCR Claim';
    } else if(operationType[operationType.length-2]=='service-activity-claim-approval'){
      this.docType = 'Service Activity Claim';
    } else if(operationType[operationType.length-2]=='marketing-activity-claim-approval'){
      this.docType = 'Marketing Activity Claim';
    }

    
    let operation = OperationsUtil.operation(this.activatedRoute);

    if(operation===Operation.VIEW || operation ===Operation.APPROVAL){
      if(operation===Operation.VIEW)
        this.isView = true;
      else
        this.isApprove = true;

      this.activatedRoute.queryParamMap.subscribe(param => {
        this.id = atob(param.get('id'));
        this.claimApprovalService.getClaimDetails(this.docType, this.id).subscribe(response => {
          if(response && response['result']){
            this.claimList = response['result'];
            // console.log(this.claimList,'cliam list')
             
            if(this.claimList && this.claimList.length>0){
              this.claimDetailsForm.patchValue(this.claimList[0]);
              this.claimDetailsForm.disable();
            }
          }
        });
        // Add getHierData by Ankit Rai
        this.claimApprovalService.getHierData(this.id).subscribe(hiResponse => {
          if(hiResponse){
            this.approvalDetails=hiResponse
          }else{
            this.toastr.error("Data Failed to Load")
          }
          
          
        });
      });

    }else if(operation===Operation.CREATE){          
      this.isCreate=true;
      this.claimApprovalService.getClaimListForApproval(this.docType).subscribe(response => {
          if(response && response['result']){
            this.claimList = response['result'];
          }
      });
    }

    if(this.docType == 'WCR Claim' && operation===Operation.CREATE){

      this.wcrClaimDetailsForm.controls.dealerId.valueChanges.subscribe((res) => {
        if (res && typeof res != 'object' ) {
          this.wcrClaimDetailsForm.controls.dealerId.setErrors({'selectFromList':'Select From List'}) 
          this.claimApprovalService.getDealerCodeList(res).subscribe(response => {
            this.dealercodeList = response;
          })
        }else {
          this.wcrClaimDetailsForm.controls.dealerId.setErrors(null) 
        }
      });
      
      this.wcrClaimDetailsForm.controls.claimNo.valueChanges.subscribe((res) => {
        if (res && typeof res != 'object' ) {
          this.wcrClaimDetailsForm.controls.claimNo.setErrors({'selectFromList':'Select From List'}) 
          this.claimApprovalService.searchAutoCompleteWcrNo(res).subscribe(response => {
            this.wcrData = response ;
          })
        }else {
          this.wcrClaimDetailsForm.controls.claimNo.setErrors(null) 
        }
      });

      
      this.wcrClaimDetailsForm.controls.pcrNumber.valueChanges.subscribe((res) => {
        if (res && typeof res != 'object' ) {
          this.wcrClaimDetailsForm.controls.pcrNumber.setErrors({'selectFromList':'Select From List'}) 
          this.claimApprovalService.autoCompletePcrNo(res).subscribe(response => {
            this.pcrData = response ;
          })
        }else {
          this.wcrClaimDetailsForm.controls.pcrNumber.setErrors(null) 
        }
      });

      
      this.wcrClaimDetailsForm.controls.jobcardNumber.valueChanges.subscribe((res) => {
        if (res && typeof res != 'object' ) {
          this.wcrClaimDetailsForm.controls.jobcardNumber.setErrors({'selectFromList':'Select From List'}) 
          this.claimApprovalService.searchAutoCompleteJobCode(res).subscribe(response => {
            this.jobCodeData = response ;
          })
        }else {
          this.wcrClaimDetailsForm.controls.jobcardNumber.setErrors(null) 
        }
      });

      this.claimApprovalService.searchWcrType().subscribe(res => {
        this.wcrType = res;
      });
    }
  }
  searchData(){
    let searchObject = this.wcrClaimDetailsForm.getRawValue();
    if(searchObject['dealerId']){
      searchObject['dealerId'] = searchObject['dealerId']['id'];
    }
    if(searchObject['claimNo']){
      searchObject['claimNo'] = searchObject['claimNo']['code'];
    }
    if(searchObject['pcrNumber']){
      searchObject['pcrNumber'] = searchObject['pcrNumber']['code'];
    }
    if(searchObject['jobcardNumber']){
      searchObject['jobcardNumber'] = searchObject['jobcardNumber']['code'];
    }
    searchObject['requestFrom']="WCR Claim";

    if (searchObject['fromDate'] && searchObject['toDate']) {
      searchObject['fromDate'] = this.dateService.getDateIntoYYYYMMDD(searchObject['fromDate']);
      searchObject['toDate'] = this.dateService.getDateIntoYYYYMMDD(searchObject['toDate'])
    }else if (searchObject['fromDate'] || searchObject['toDate']) {
      this.toastr.error("Please Select Date Range");
      return;
    }

    this.claimApprovalService.searchClaimListForApproval(searchObject).subscribe(response => {
      if(response && response['result']){
        this.claimList = response['result'];
      }
    });
  }
  clearForm(){
    this.wcrClaimDetailsForm.reset();
    this.wcrClaimDetailsForm.controls.jobcardNumber.reset();
    this.wcrClaimDetailsForm.controls.pcrNumber.reset();
    this.wcrClaimDetailsForm.controls.wcrNo.reset();
    this.wcrClaimDetailsForm.controls.dealerId.reset();

  }
  displayDealerCodeFn(dealerId){
    return dealerId && typeof dealerId == 'object'?dealerId.code:dealerId;
  }
  displayPcrFn(dealerId){
    return dealerId && typeof dealerId == 'object'?dealerId.code:dealerId;
  }
  displayWcrFn(dealerId){
    return dealerId && typeof dealerId == 'object'?dealerId.code:dealerId;
  }
  displayJobcodeFn(dealerId){
    return dealerId && typeof dealerId == 'object'?dealerId.code:dealerId;
  }
  approveClaimCheck(approvalType){
    // this.dialogMsg=approvalType
    this.openConfirmDialog(this.id, approvalType);
  }
  
  claimSelection(id){
    if(this.claimIds.indexOf(id)>-1){
      this.claimIds = this.claimIds.filter(i=>i!=id);
    }else{
      this.claimIds.push(id);
    }
  }

  checkAll(event){
    this.allChecked = !this.allChecked;
    this.claimIds=[];
    if(this.allChecked){
      this.claimList.forEach(claim => {
        this.claimIds.push(claim['claimId']);
      })
    }
  }

  validateForm(){
    if(this.claimIds.length==0){
      this.toastr.error('No Claim have been checked for Approval');
      return false;
    }
    let finalClaimList = this.claimList.filter(claim => {
      return this.claimIds.indexOf(claim['claimId']) > -1
    });

    let saveObject = {
      docType : this.docType,
      claimDtls : finalClaimList
    }
    // console.log(saveObject,'saveObj')
    this.claimApprovalService.submitClaimApprovalApi(saveObject).subscribe(response => {
      this.toastr.success('Saved Successfully','Success');
      this.router.navigate(['../'],{ relativeTo: this.activatedRoute});
    }, error => {
      this.toastr.error('Error while submitting details','Transaction Failed')
    })
  }
  setToDate(event: MatDatepickerInput<Date>) {
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.newdate)
        this.maxDate = this.newdate;
      else
        this.maxDate = maxDate;
      if (this.claimDetailsForm.get('toDate').value > this.maxDate)
        this.claimDetailsForm.get('toDate').patchValue(this.maxDate);
    }
  }

  private openConfirmDialog(id, approvalType): void | any {
    let message = 'Do you want to '+approvalType+' Claim?';
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result:DialogResult) => {
      if (result && result.button === 'Confirm' ){
        this.approveClaim(id, result.remarkText, approvalType);
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.remarkConfig = {
      source: Source.APPROVE
    }
    // if(this.dialogMsg.toLocaleLowerCase()==='rejected'){
    //   confirmationData.remarkConfig = {
    //    source: Source.REJECT
    //  }
    // }
    
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  approveClaim(claimId:number, remark:string, approvalType:string){
    this.claimApprovalService.approveClaim(claimId, this.docType, remark, approvalType).subscribe(response =>{
      if(response){
        this.toastr.success(response['message']);
        if(response['status']=='200'){
          this.router.navigate(['../'],{ relativeTo: this.activatedRoute});
        }
      }else {
        this.toastr.error('Management Approval failed','Transaction Failed');
      }
    })
  }

}
