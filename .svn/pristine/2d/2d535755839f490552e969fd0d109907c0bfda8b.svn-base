import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../../direct-survey/direct-survey.service';
import { TollSubmitForm } from '../../domain/toll-free-domain';
import { TollFreeService } from '../../service/toll-free.service';
import { TollFreePagePresenter } from './toll-free-page.prensenter';
import { TollFreePageStore } from './toll-free-page.store';

@Component({
  selector: 'app-create-toll-free',
  templateUrl: './create-toll-free.component.html',
  styleUrls: ['./create-toll-free.component.css'],
  providers: [TollFreePagePresenter, TollFreePageStore,TollFreeService, DirectSurveyService]

})
export class CreateTollFreeComponent implements OnInit {

  tollFreeCreateDetailsFrom: FormGroup
  isSurveyHistory:boolean
  surveyTypeSelection:string;
  isTollFreeHistory:boolean
  isFreeServiceHistory:boolean
  complaintDetailsForm: FormArray;
  surveyHistoryForm:FormArray;
  customerDetailsForm: FormGroup;
  enquiryDetailsForm: FormGroup;
  tollFreeHistoryForm: FormArray;
  freeServiceHistoryForm: FormArray;
  machineDetailsForm: FormGroup;
  customerType: any;
  isView:boolean = false;
  isCreate:boolean = false;
  isEdit:boolean = false;
  getcustomerId: string;
  systemDate = new Date();
  isSubmitDisable:boolean = false;
  callId:any;
  fileUploadedList:any[]
  isDialogView:boolean = false;
  dealerList:any[]
  constructor(private tollFreePagePresenter: TollFreePagePresenter,
    private router: Router,
    private activatedRoute : ActivatedRoute,
    private service: TollFreeService,
    private toastr : ToastrService,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<CreateTollFreeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      if(data && data.callId){
        this.isDialogView = true;
        this.isView = true;
        this.callId = data.callId;
        this.getDetailsForView();
      }
      this.service.fetchCustomerMachineDtlSubject.subscribe(obj => {
        this.getcustomerId = obj['customerId']
      })
     }

  closeDialog(){
    this.dialogRef.close();
  }
  ngOnInit() {
    this.tollFreeCreateDetailsFrom = this.tollFreePagePresenter.tollFreecallDetailsForm
    this.complaintDetailsForm = this.tollFreePagePresenter.complaintDetailsForm;
    this.customerDetailsForm = this.tollFreePagePresenter.customerDetailsForm;
    this.enquiryDetailsForm = this.tollFreePagePresenter.enquiryDetailsForm;
    this.tollFreeHistoryForm = this.tollFreePagePresenter.callHistoryDetailsForm;
    this.freeServiceHistoryForm = this.tollFreePagePresenter.freeServiceHistoryDetailsForm;
    this.machineDetailsForm = this.tollFreePagePresenter.machineDetailsForm;
    this.surveyHistoryForm = this.tollFreePagePresenter.serveyHistoryForm;
    this.tollFreeCreateDetailsFrom.get('callDate').patchValue(this.systemDate)

    this.tollFreePagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if(this.tollFreePagePresenter.operation === Operation.CREATE){
      this.isCreate = true;
      // this.service.getDealerDetails().subscribe(response => {
      //   if(response!=null){
      //     this.tollFreeCreateDetailsFrom.patchValue(response.result)
      //     // this.tollFreeCreateDetailsFrom.get('callDate').patchValue(new Date())
      //     this.districtId = response.result['districtId'];
      //   }
      // });
    }else if(this.tollFreePagePresenter.operation === Operation.EDIT){
      this.isEdit = true;
    }else if(this.tollFreePagePresenter.operation === Operation.VIEW){
      this.isView = true;
      this.activatedRoute.queryParamMap.subscribe(param => {
        this.callId = param.get('id');
        this.getDetailsForView();
      })
    }
  }
  disableFormForView(){
    this.tollFreeCreateDetailsFrom.disable();
    this.customerDetailsForm.disable();
    this.complaintDetailsForm.disable();
    this.enquiryDetailsForm.disable();
    this.machineDetailsForm.disable();
  }
  getDetailsForView(){
    this.service.fetchCallDetails(this.callId).subscribe(res => {
      if(res){
        this.tollFreeCreateDetailsFrom.patchValue(res['result']);
        (res['result']['complaintDtlList']).forEach(element => {
          this.tollFreePagePresenter.addRowComplaint(element);
        });
        if(res['result']['fileUploadList']){
          this.fileUploadedList = res['result']['fileUploadList'];
        }
        this.customerType = res['result']['customerType'];
        if(this.customerType=='Enquiry'){
          res['result']['enquiryDetails']['pincode']=res['result']['enquiryDetails']['pinCode']
          this.enquiryDetailsForm.patchValue(res['result']['enquiryDetails'])
          this.enquiryDetailsForm.get('enquiry').patchValue(res['result']['enquiry']);
          this.enquiryDetailsForm.controls.village.patchValue(res['result']['enquiryDetails']['postOffice']);
        }else{
          this.customerDetailsForm.patchValue(res['result']['customerDetails']);
        }
        this.machineDetailsForm.get('soldToDealer').patchValue({'displayString':res['result']['dealerCode'],code:res['result']['dealerCode'],id:res['result']['dealerId']});
        this.service.fetchCustomerMachineDtlSubject.next({customerId:res['result']['customerMasterId'], vinId:res['result']['vinId']});
        this.service.fetchServiceCallHistorySubject.next({customerId:res['result']['customerMasterId'], vinId:res['result']['vinId']});
      }else{
        this.toastr.error('Unable to fetch details','Error');
      }
      if(this.isView){
        this.disableFormForView();
      }
    })
  }
  onclick(value) {
    this.customerType = value;
  }
  clearForm() {
    this.enquiryDetailsForm.reset();
    this.customerDetailsForm.reset();
    this.machineDetailsForm.reset();
    this.complaintDetailsForm.reset();
    this.tollFreeCreateDetailsFrom.reset()
    this.tollFreeCreateDetailsFrom.get('tollfreecallDate').patchValue(this.systemDate)
  }
  exitForm() {
    this.router.navigate(['../crm/toll-free-call/'])
  }
  validateForm(){
    this.tollFreeCreateDetailsFrom.markAllAsTouched();
    if(this.customerType){
      if(this.customerType === 'Enquiry'){
        this.machineDetailsForm.controls.chassisNo.clearValidators();
        this.machineDetailsForm.controls.chassisNo.updateValueAndValidity();
        this.enquiryDetailsForm.markAllAsTouched();
      }else{
        this.machineDetailsForm.controls.chassisNo.setValidators(Validators.required);
        this.machineDetailsForm.controls.chassisNo.updateValueAndValidity();
        this.customerDetailsForm.markAllAsTouched();
        this.machineDetailsForm.markAllAsTouched();
        this.complaintDetailsForm.markAllAsTouched();
      }
    }
    if((this.tollFreeCreateDetailsFrom.valid && this.customerDetailsForm.valid && this.machineDetailsForm && this.customerType != 'Enquiry') ||
       (this.tollFreeCreateDetailsFrom.valid && this.enquiryDetailsForm && this.customerType == 'Enquiry') ){
      if(this.complaintDetailsForm.length==0 && this.customerType != 'Enquiry'){
        this.toastr.error("Please add Complaint details ","Mandatory")
      }else{
        this.openConfirmDialog()
      }
    }else{
      this.toastr.error('Please Enter Mandatory Fields','Mandatory')
    }
  }

  buildJsonToSubmit() {
    let tollSubmit = {} as TollSubmitForm
  }

  submitForm(){
      const saveData = {...this.tollFreeCreateDetailsFrom.value,
        customerMasterId:this.customerDetailsForm.getRawValue().customerMasterId,
        pinId:this.enquiryDetailsForm.getRawValue().pinID?this.enquiryDetailsForm.getRawValue().pinID:this.customerDetailsForm.getRawValue().pinCodeId,
        vinId:this.machineDetailsForm.get('chassisNo').value?this.machineDetailsForm.get('chassisNo').value.vinId:null,
        dealerId:this.enquiryDetailsForm.get('dealerName').value?this.enquiryDetailsForm.get('dealerName').value.id:(this.machineDetailsForm.get('soldToDealer').value?this.machineDetailsForm.get('soldToDealer').value.id:null),
        enquiry:this.customerType=='Enquiry'?this.enquiryDetailsForm.get('enquiry').value:'',
        complaintDtlList:this.complaintDetailsForm.getRawValue()
      }; 
      this.service.saveCallDetails(saveData, this.tollFreePagePresenter.tollComplaint).subscribe(response => {
        if(response){
          this.toastr.success(response['message']);
          this.router.navigate(['../'], {
            relativeTo: this.activatedRoute
          });
        }else{
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving','Transaction Failed')
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving','Transaction Failed')
      })
  }
  

  viewPrint(){
    
  }
  showFreeServiceHistory(){
    this.service.fetchCustomerMachineDtlSubject.subscribe(obj => {
      this.getcustomerId = obj['customerId']
      if(this.getcustomerId!=null){
        this.isFreeServiceHistory =! this.isFreeServiceHistory
      }
      else{
        this.isFreeServiceHistory=false
      }
    })
    
  }
  showSurveyHistory(){
      this.isSurveyHistory =! this.isSurveyHistory
   
  }
  showTollFreeHistory(){
      this.isTollFreeHistory = !this.isTollFreeHistory
  }

  private openConfirmDialog(): void | any {
    const message = `Are you sure you want to submit?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.submitForm()
      }
    })
  }

  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }


}
  
