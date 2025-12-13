import { Component, OnInit } from '@angular/core';
import { FormArray, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from './delear-customer-care-ex-call-page.prensenter';
import { DelearCustomerCareExCallPageStore } from './delear-customer-care-ex-call-page.store';
import { DatePipe } from '@angular/common';
import { DateService } from 'src/app/root-service/date.service';
import { feedbackDetails, finalPostService } from './create-dealer-customer-care-ex-call-model';
import { TollFreeService } from '../../../toll-free/service/toll-free.service';
import { CustomValidators } from 'src/app/utils/custom-validators';



@Component({
  selector: 'app-create-delear-customer-care-ex-call',
  templateUrl: './create-delear-customer-care-ex-call.component.html',
  styleUrls: ['./create-delear-customer-care-ex-call.component.css'],
  providers: [DelearCustomerCareExCallPagePresenter, DatePipe, DelearCustomerCareExCallPageStore, DelearCustomerCareExCallService]

})
export class CreateDelearCustomerCareExCallComponent implements OnInit {

  isView: boolean = false;
  isCreate: boolean = false;
  isEdit: boolean = false;
  callId: any;
  delearCustomerCareExCallCreateDetailsFrom: FormGroup
  customerDetailsForm: FormGroup
  enquiryDetailsForm: FormGroup
  createEnquiryForm: FormGroup
  bookingDetailsBasicForm: FormGroup
  bookingSearchForm: FormGroup
  callHistoryForm: FormArray
  freeServiceHistoryForm: FormArray
  machineDetailsForm: FormGroup
  complaintDetailsForm: FormArray
  postServiceFeedBackForm: FormArray
  postSalesFeedBackForm: FormArray
  districtId: number;
  customerType: any;
  isSubmitDisable: boolean = false;
  callList: any
  callStatusList: any;
  salesEnquiryFlag: boolean;
  serviceBookingFlag: boolean;
  postServiceFeedBackFlag: Boolean;
  postSalesFeedBack: any;
  enquiryFlag: boolean;
  salesEnquiry: any;
  serviceBooking: any;
  postServiceFeedBacks: any;
  currentDateTime: string;
  date: any;
  postServiceFeedBack: any;
  postServiceFeedBackBasedJobCard: any;
  typeOfCallId: any;
  startList: any;
  showAllServiceFeedbackForm: any;
  chassisNo: any;
  getchassisNo: any;
  postSalesFeedBackFlag: boolean;
  jcId: any;
  CustomerId: any;
  vinId: any;
  callNo: string;
  viewSalesEnquiry: boolean;
  viewPostServicefeedback: boolean;
  serviceFeedBackDetails: any;
  viewPostSalesfeedback: boolean;
  salesFeedBakDetails: any;
  callHistoryData: any;
  viewServiceBooking: boolean;
  showSalesFormData: any;
  dcID: any;
  viewSalesCallHistory: any;
  serviceJobCardId:any;
  salesJobCardId:any
  showServiceBookingForm: any;
  hideSubmitButton: boolean;
  hideButton: boolean;
  serviceBookingId:any;
  callStatus:string
  showValidation:boolean=false;
  constructor(private delearCustomerCareExCallPagePresenter: DelearCustomerCareExCallPagePresenter,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private customerCareExCallService: DelearCustomerCareExCallService,
    private toastr: ToastrService,
    public dialog: MatDialog,
    public datepipe: DatePipe,
    private dateService: DateService,
    private toaster: ToastrService,
    private tollFreeService:TollFreeService,
  
  ) {
    this.currentDateTime = this.datepipe.transform((new Date), 'H:mm');


  }

  ngOnInit() {
     

    this.delearCustomerCareExCallCreateDetailsFrom = this.delearCustomerCareExCallPagePresenter.callDetailsForm
    this.complaintDetailsForm = this.delearCustomerCareExCallPagePresenter.complaintDetailsForm;
    this.customerDetailsForm = this.delearCustomerCareExCallPagePresenter.customerDetailsForm;
    this.enquiryDetailsForm = this.delearCustomerCareExCallPagePresenter.enquiryDetailsForm;
     this.createEnquiryForm = this.delearCustomerCareExCallPagePresenter.createEnquiryForm;
    this.callHistoryForm = this.delearCustomerCareExCallPagePresenter.callHistoryDetailsForm;
    this.freeServiceHistoryForm = this.delearCustomerCareExCallPagePresenter.freeServiceHistoryDetailsForm;
    this.machineDetailsForm = this.delearCustomerCareExCallPagePresenter.machineDetailsForm;
    this.bookingDetailsBasicForm = this.delearCustomerCareExCallPagePresenter.serviceBookingForm;
    this.bookingSearchForm = this.delearCustomerCareExCallPagePresenter.serviceBookingSearchForm;
    this.postServiceFeedBackForm = this.delearCustomerCareExCallPagePresenter.postServiceFeedBackForm;
    this.postSalesFeedBackForm = this.delearCustomerCareExCallPagePresenter.postSalesFeedBackForm;
    this.customerCareExCallService.getDealerDetails().subscribe(response => {
      if (response != null) {
        this.delearCustomerCareExCallCreateDetailsFrom.patchValue(response.result)
        // this.delearCustomerCareExCallCreateDetailsFrom.get('callDate').patchValue(new Date())
        this.districtId = response.result['districtId'];
      }
    });
    // this.createEnquiryForm.get('enquiryDate').patchValue(new Date())
    this.delearCustomerCareExCallCreateDetailsFrom.get('startTime').patchValue(this.currentDateTime)
    this.delearCustomerCareExCallPagePresenter.operation = OperationsUtil.operation(this.activatedRoute);
    if (this.delearCustomerCareExCallPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true;
    } else if (this.delearCustomerCareExCallPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true;
    } else if (this.delearCustomerCareExCallPagePresenter.operation === Operation.VIEW) {
      this.isView = true;
      this.activatedRoute.queryParamMap.subscribe(param => {
          // console.log(param,'param')
          // const a=atob(param.get('id'))
          // console.log(a,'llaaa')
        this.callId = param.get('id');
        this.callNo = param.get('callNo');

        this.getDetailsForView();
      })
    }
    this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').disable()
    this.getCallTypeList();
    this.getSystemDates()

  }
  private getSystemDates() {
    this.customerCareExCallService.getSystemDate().subscribe(res => {

      this.date = res['result'];
      this.delearCustomerCareExCallCreateDetailsFrom.get('callDate').patchValue(this.date)
      this.createEnquiryForm.get('enquiryDate').patchValue(this.date)
    })
  }
  getCallTypeList() {
    this.customerCareExCallService.getCallTypeList().subscribe(res => {
      if (res) {
        this.callList = res['result']
      }
    })
  }
  // select Call Status
  selectCallStatus(event: any) {
   this.createEnquiryForm.reset();
   this.showServiceBookingForm=false;
   this.showAllServiceFeedbackForm=false;
   this.showSalesFormData=false;
  //  this.postServiceFeedBackFlag=true;
  
   this.createEnquiryForm.get('source').setValue("Dealer CCE");
   this.getSystemDates()
    if (event.value) {
      this.callStatus = event.value.callStatus;
      if (this.callStatus === "Completed" &&  this.salesEnquiry==="Sales Enquiry") {
        this.showValidation = true;
        this.addCompletedStatusValidators();
       
      } else {
        this.showValidation = false;
        this.removeCompletedStatusValidators();
      }
      
    }
  }
  
  addCompletedStatusValidators() {
    // Add specific validators required for "Completed" status
    if (this.salesEnquiryFlag == true) {
      this.setSalesEnquiryValidators();
    } 
  }

  
  // validation for Service booking
  

  // Code for post service  Booking 

  setServiceBookingValidators(){
    this.bookingDetailsBasicForm.get('mechanic').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('sourceOfBooking').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('currentHour').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('serviceCategory').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').setValidators([Validators.compose([Validators.required, CustomValidators.mobileNumber])]);
    this.bookingDetailsBasicForm.get('placeOfService').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('appointmentDate').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('appointmentTime').setValidators([Validators.required]);
    this.bookingDetailsBasicForm.get('remarks').setValidators([Validators.required]);
    // Update value and validity for all controls
    this.bookingDetailsBasicForm.get('mechanic').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('sourceOfBooking').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('currentHour').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('serviceCategory').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('placeOfService').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('appointmentDate').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('appointmentTime').updateValueAndValidity();
    this.bookingDetailsBasicForm.get('remarks').updateValueAndValidity();
  }

  removeValidationForServiceBooking(){
   
      // Remove validators when call status is not "Completed"
      this.bookingDetailsBasicForm.get('mechanic').clearValidators();
      this.bookingDetailsBasicForm.get('sourceOfBooking').clearValidators();
      this.bookingDetailsBasicForm.get('currentHour').clearValidators();
      this.bookingDetailsBasicForm.get('serviceCategory').clearValidators();
      this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').clearValidators();
      this.bookingDetailsBasicForm.get('placeOfService').clearValidators();
      this.bookingDetailsBasicForm.get('appointmentDate').clearValidators();
      this.bookingDetailsBasicForm.get('appointmentTime').clearValidators();
      this.bookingDetailsBasicForm.get('remarks').clearValidators();
      // Update value and validity for all controls
      this.bookingDetailsBasicForm.get('mechanic').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('sourceOfBooking').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('currentHour').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('serviceCategory').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('placeOfService').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('appointmentDate').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('appointmentTime').updateValueAndValidity();
      this.bookingDetailsBasicForm.get('remarks').updateValueAndValidity();
    
  }
  
  // code for service booking

  setPostServiceValidators(){
   
    if(this.delearCustomerCareExCallPagePresenter.postServiceFeedBackForm){
      this.delearCustomerCareExCallPagePresenter.postServiceFeedBackForm['controls'].forEach(element=>{
        element.get('rating').setValidators([Validators.required]);
        element.get('rating').updateValueAndValidity();
      })
    }
  }
  removePostServiceValidators(){

      this.delearCustomerCareExCallPagePresenter.postServiceFeedBackForm['controls'].forEach(element=>{
        element.get('rating').setValidators(null);
        element.get('rating').clearValidators();
        element.get('rating').updateValueAndValidity(); 
      })
  }
  // end

  // code for post Sales
  setPostSalesValidators(){
    if(this.delearCustomerCareExCallPagePresenter.postSalesFeedBackForm){
      this.delearCustomerCareExCallPagePresenter.postSalesFeedBackForm['controls'].forEach(element=>{
        element.get('rating').setValidators([Validators.required]);
        element.get('rating').updateValueAndValidity();
      })
    }
  }
  removePostSalesValidators(){
    this.delearCustomerCareExCallPagePresenter.postSalesFeedBackForm['controls'].forEach(element=>{
      element.get('rating').setValidators(null);
      element.get('rating').clearValidators();
      element.get('rating').updateValueAndValidity(); 
    })
  }
  
  removeCompletedStatusValidators() {
    // Remove validators when call status is not "Completed"
    this.createEnquiryForm.get('firstName').clearValidators();
    this.createEnquiryForm.get('model').clearValidators();
    this.createEnquiryForm.get('subModel').clearValidators();
    this.createEnquiryForm.get('variant').clearValidators();
    this.createEnquiryForm.get('mobileNumber').clearValidators();
    this.createEnquiryForm.get('tentativePurchaseDate').clearValidators();
    this.createEnquiryForm.get('nextFollowUpDate').clearValidators();
    this.createEnquiryForm.get('remarks').clearValidators();
  
    // Update value and validity for all controls
    this.createEnquiryForm.get('firstName').updateValueAndValidity();
    this.createEnquiryForm.get('model').updateValueAndValidity();
    this.createEnquiryForm.get('subModel').updateValueAndValidity();
    this.createEnquiryForm.get('variant').updateValueAndValidity();
    this.createEnquiryForm.get('mobileNumber').updateValueAndValidity();
    this.createEnquiryForm.get('tentativePurchaseDate').updateValueAndValidity();
    this.createEnquiryForm.get('nextFollowUpDate').updateValueAndValidity();
    this.createEnquiryForm.get('remarks').updateValueAndValidity();
  }
  
  selectedTypeOfCall(event: any) {
    // Common reset
    // this.resetFormValidations();
  
    // For Sales Enquiry
    if (event.value.typeOfCall === 'Sales Enquiry') {
      this.salesEnquiry = event.value.typeOfCall;
      this.salesEnquiryFlag = true;
      this.serviceBookingFlag = false;
      this.postSalesFeedBackFlag = false;
      this.postServiceFeedBackFlag = false;
    } else if (event.value.typeOfCall === 'Service Booking') {
     this.serviceBooking=event.value.typeOfCall;
      this.salesEnquiry = null;
      this.callStatusList = null;
      this.createEnquiryForm.reset();
      this.serviceBookingFlag = true;
      this.salesEnquiryFlag = false;
      this.postSalesFeedBackFlag = false;
      this.postServiceFeedBackFlag = false;
      
     
    } else if (event.value.typeOfCall === 'Post Service Feedback') {
      // For Post Service Feedback
      this.postServiceFeedBack = event.value.typeOfCall;
      this.postServiceFeedBackFlag = true;
      this.salesEnquiryFlag = false;
      this.serviceBookingFlag = false;
      this.postSalesFeedBackFlag = false;
    } else if (event.value.typeOfCall === 'Post Sales Feedback') {
      // For Post Sales Feedback
      this.postSalesFeedBack = event.value.typeOfCall;
      this.postSalesFeedBackFlag = true;
      this.salesEnquiryFlag = false;
      this.serviceBookingFlag = false;
      this.postServiceFeedBackFlag = false;
    }
  
    // Enable CRM Call Status and disable Call Type
    this.typeOfCallId = event.value.id;
    this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').enable();
    this.delearCustomerCareExCallCreateDetailsFrom.get('callType').disable();
    
    // Fetch call status list
    this.getCallStatusList();
  }
  
  setSalesEnquiryValidators() {
    this.createEnquiryForm.get('firstName').setValidators([Validators.required]);
    this.createEnquiryForm.get('model').setValidators([Validators.required]);
    this.createEnquiryForm.get('subModel').setValidators([Validators.required]);
    this.createEnquiryForm.get('variant').setValidators([Validators.required]);
    this.createEnquiryForm.get('mobileNumber').setValidators([Validators.compose([Validators.required, CustomValidators.mobileNumber])]);
    this.createEnquiryForm.get('tentativePurchaseDate').setValidators([Validators.required]);
    this.createEnquiryForm.get('nextFollowUpDate').setValidators([Validators.required]);
    this.createEnquiryForm.get('remarks').setValidators([Validators.required]);
    
    // Update value and validity for all controls
    this.createEnquiryForm.get('firstName').updateValueAndValidity();
    this.createEnquiryForm.get('model').updateValueAndValidity();
    this.createEnquiryForm.get('subModel').updateValueAndValidity();
    this.createEnquiryForm.get('variant').updateValueAndValidity();
    this.createEnquiryForm.get('mobileNumber').updateValueAndValidity();
    this.createEnquiryForm.get('tentativePurchaseDate').updateValueAndValidity();
    this.createEnquiryForm.get('nextFollowUpDate').updateValueAndValidity();
    this.createEnquiryForm.get('remarks').updateValueAndValidity();
  }
  
  getCallStatusList() {
    this.customerCareExCallService.getCallStatusList().subscribe(res => {
      this.callStatusList = res['result']
    })
  }



  disableFormForView() {
    this.delearCustomerCareExCallCreateDetailsFrom.disable();
    this.customerDetailsForm.disable();
    this.complaintDetailsForm.disable();
    this.enquiryDetailsForm.disable();
    this.machineDetailsForm.disable();
    this.createEnquiryForm.disable();
    this.postServiceFeedBackForm.disable();
    this.bookingDetailsBasicForm.disable()
  }
  getDetailsForView() {
    this.customerCareExCallService.getViewData(this.callNo, this.callId).subscribe(res => {
      
      if (res['result'].cceHdrViewDto.callType.typeOfCall === 'Sales Enquiry') {
        this.viewSalesEnquiry = true
      } else {
        this.viewSalesEnquiry = false
      }
      if (res) {
        if (res['result'].cceHdrViewDto.callType.typeOfCall === 'Sales Enquiry') {
          this.delearCustomerCareExCallCreateDetailsFrom.patchValue(res['result'].cceHdrViewDto)
          this.delearCustomerCareExCallCreateDetailsFrom.get('callType').patchValue(res['result'].cceHdrViewDto.callType.typeOfCall)
          this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').patchValue(res['result'].cceHdrViewDto.crmCallStatus.callStatus)
          this.createEnquiryForm.patchValue({
            remarks: res['result'].enquiryDetailDto.remarks ? res['result'].enquiryDetailDto.remarks : null,
            nextFollowUpDate: res['result'].enquiryDetailDto.nextFollowUpDate ? res['result'].enquiryDetailDto.nextFollowUpDate : null,
            tentativePurchaseDate: res['result'].enquiryDetailDto.tentativePurchaseDate ? res['result'].enquiryDetailDto.tentativePurchaseDate : null,
            lastName: res['result'].enquiryDetailDto.lastName ? res['result'].enquiryDetailDto.lastName : null,
            firstName: res['result'].enquiryDetailDto.firstName ? res['result'].enquiryDetailDto.firstName : null,
            mobileNumber: res['result'].enquiryDetailDto.mobileNumber ? res['result'].enquiryDetailDto.mobileNumber : null,
            variant: res['result'].enquiryDetailDto.variant ? res['result'].enquiryDetailDto.variant : null,
            subModel: res['result'].enquiryDetailDto.subModel ? res['result'].enquiryDetailDto.subModel : null,
            model: res['result'].enquiryDetailDto.model ? res['result'].enquiryDetailDto.model : null,
            source: res['result'].enquiryDetailDto.source ? res['result'].enquiryDetailDto.source : null,
            enquiryDate: res['result'].enquiryDate
          })
        } else {

        }

        if (res['result'].cceHdrViewDto.callType.typeOfCall === 'Service Booking') {
          this.viewServiceBooking = true
          this.delearCustomerCareExCallCreateDetailsFrom.patchValue(res['result'].cceHdrViewDto)
          this.delearCustomerCareExCallCreateDetailsFrom.get('callType').patchValue(res['result'].cceHdrViewDto.callType.typeOfCall)
          this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').patchValue(res['result'].cceHdrViewDto.crmCallStatus.callStatus)
          // console.log(res['result'].serviceBookingViewDto.serviceType,'dddddddddddddddddddd')
          // this.bookingDetailsBasicForm.get('serviceMtServiceTypeInfo').patchValue(res['result'].serviceBookingViewDto.serviceType ? res['result'].serviceBookingViewDto.serviceType : 'hii'),
          this.bookingDetailsBasicForm.patchValue({
            chassisNo: res['result'].serviceBookingViewDto.chassisNo ? res['result'].serviceBookingViewDto.chassisNo : null,
            remarks: res['result'].serviceBookingViewDto.remarks ? res['result'].serviceBookingViewDto.remarks : null,
            registrationNumber: res['result'].serviceBookingViewDto.reg ? res['result'].serviceBookingViewDto.reg : null,
            appointmentTime: res['result'].serviceBookingViewDto.appointmentTime ? res['result'].serviceBookingViewDto.appointmentTime : null,
            appointmentDate: res['result'].serviceBookingViewDto.appointmentDate ? res['result'].serviceBookingViewDto.appointmentDate : null,
            placeOfService: res['result'].serviceBookingViewDto.placeOfService ? res['result'].serviceBookingViewDto.placeOfService : null,
            serviceMtServiceTypeInfo: res['result'].serviceBookingViewDto.serviceType ? res['result'].serviceBookingViewDto.serviceType : null,
            serviceCategory: res['result'].serviceBookingViewDto.category ? res['result'].serviceBookingViewDto.category : null,
            totalHour: res['result'].serviceBookingViewDto.totalHour ? res['result'].serviceBookingViewDto.totalHour : null,
            previousHour: res['result'].serviceBookingViewDto.previousHour ? res['result'].serviceBookingViewDto.previousHour : null,
            currentHour: res['result'].serviceBookingViewDto.currentHour ? res['result'].serviceBookingViewDto.currentHour : null,
            bookingDate:res['result'].serviceBookingViewDto.bookingDate,
            status:res['result'].serviceBookingViewDto.status,
            customerName:res['result'].serviceBookingViewDto.customerName,
            engineNo:res['result'].serviceBookingViewDto.engineNo,
            model:res['result'].serviceBookingViewDto.model,
            dateofInstallation:res['result'].serviceBookingViewDto.dateOfInstallation,
            mechanic:res['result'].serviceBookingViewDto.name,
            sourceOfBooking:res['result'].serviceBookingViewDto.sourceOfBooking
          })
        } else {
          this.viewServiceBooking = false

        }

        if (res['result'].cceHdrViewDto.callType.typeOfCall === 'Post Service Feedback') {
          this.viewPostServicefeedback = true
          this.delearCustomerCareExCallCreateDetailsFrom.patchValue(res['result'].cceHdrViewDto)
          this.delearCustomerCareExCallCreateDetailsFrom.get('callType').patchValue(res['result'].cceHdrViewDto.callType.typeOfCall)
          this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').patchValue(res['result'].cceHdrViewDto.crmCallStatus.callStatus)
          this.patchQuesAns(res['result']['callFeedback']);

        } else {
          this.viewPostServicefeedback = false
        }
        if (res['result'].cceHdrViewDto.callType.typeOfCall === 'Post Sales Feedback') {
          this.viewPostSalesfeedback = true
          this.delearCustomerCareExCallCreateDetailsFrom.patchValue(res['result'].cceHdrViewDto)
          this.delearCustomerCareExCallCreateDetailsFrom.get('callType').patchValue(res['result'].cceHdrViewDto.callType.typeOfCall)
          this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').patchValue(res['result'].cceHdrViewDto.crmCallStatus.callStatus)
          this.patchForSales(res['result']['callFeedback']);

        } else {
          this.viewPostSalesfeedback = false

        }
        if(res['result'].customerCode!=null){
           const dealerId=res['result'].customerId
              this.getViewDataForMachineDetails(dealerId) 
             this.getViewDataForCustomerDetails(res['result'].customerCode)
            
             
        }
        
      }

     

      if (this.isView) {
        this.disableFormForView();
      }
    })
  }
  getViewDataForCustomerDetails(custCode){
    this.tollFreeService.getCustomerDtl(custCode).subscribe(res=>{
      if(res){
        this.customerDetailsForm.patchValue(res['result'])
      }

    })
  }

  getViewDataForMachineDetails(id){
    this.tollFreeService.getMachineDtl(id).subscribe(res=>{
      if(res){
       this.machineDetailsForm.patchValue(res['result'][0])
        this.customerDetailsForm.patchValue({
          soldDealerName:res['result'][0].dealerName,
      contactPersonMobile:res['result'][0].dealerContactNo,
      relationWithCustomer:res['result'][0].dealerContactName
        })
      }
     })
  }
  patchQuesAns(data) {
    this.serviceFeedBackDetails = data


  }
  patchForSales(data) {
    this.salesFeedBakDetails = data
  }
  onclick(value) {
    this.customerType = value;

    if (this.customerType === 'Enquiry') {
      this.enquiryFlag = true
    } else {
      this.enquiryFlag = false
    }
  }
  clearForm() {
    this.delearCustomerCareExCallCreateDetailsFrom.reset()
    this.createEnquiryForm.reset()
    this.getSystemDates()
    this.delearCustomerCareExCallCreateDetailsFrom.get('startTime').patchValue(this.currentDateTime)

     this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').disable()
  }
  exitForm() {
    this.router.navigate(['../crm/customer-care-executive-calls/'])
  }
  validateForm() {

    if (this.salesEnquiry === 'Sales Enquiry') {
      if (this.createEnquiryForm.invalid) {
        this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched()
        this.createEnquiryForm.markAllAsTouched()
        return false
      } else if (this.delearCustomerCareExCallCreateDetailsFrom.invalid) {
        this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched();
        this.toaster.error("Please Fill All Required Fields");
        return false
      }
      else {
        this.openConfirmDialog()
      }
    } else {

    }
  
    // service booking
    if (this.serviceBooking === 'Service Booking') {
      debugger
      if (this.delearCustomerCareExCallCreateDetailsFrom.invalid) {
          this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched();
          this.toaster.error("Please fill Call Status First");
          return false;
      } else if (this.bookingDetailsBasicForm) {
          const callType = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.callType.value.typeOfCall;
          const callStatus = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.crmCallStatus.value.callStatus;
  
          if (callType === "Service Booking" && callStatus === "Completed") {
              this.setServiceBookingValidators();
              this.bookingDetailsBasicForm.markAllAsTouched();
              this.toaster.warning("Please fill Service Booking Form")
              // Remove the line below to let the function continue after setting validators
              // return false;
          } else {
              this.removeValidationForServiceBooking();
              this.openConfirmDialog(); // Open the confirmation dialog for other call types
              // Ensure the function execution stops after opening the dialog
              return false;
          }
      } else {
          this.bookingDetailsBasicForm.markAllAsTouched(); // Add this line to mark all fields as touched if form is invalid
          this.toaster.error("Please fill the required details"); // Show an error message
          return false;
      }
  } else {
      // Additional logic for cases where serviceBooking is not 'Service Booking'
  }
  
  
  
    
   
  if (this.postServiceFeedBack === 'Post Service Feedback') {
    
    if (this.delearCustomerCareExCallCreateDetailsFrom.invalid) {
        this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched();
        this.toaster.error("Please Fill Call Status First");
        return false;
    } else if (this.postServiceFeedBackForm) {
        const callType = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.callType.value.typeOfCall;
        const callStatus = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.crmCallStatus.value.callStatus;

        if (callType === "Post Service Feedback" && callStatus === "Completed") {
            this.setPostServiceValidators();
            this.postServiceFeedBackForm.markAllAsTouched();

            let remarksRequired = false;
            this.postServiceFeedBackForm.controls.forEach((element) => {
                const ratingValue = element.get('rating').value;
                if (ratingValue == 1 || ratingValue == 2) {
                    remarksRequired = true;
                    element.get('remarks').setValidators(Validators.required);
                } else {
                    element.get('remarks').clearValidators();
                }
                element.get('remarks').updateValueAndValidity();
            });

            if (remarksRequired && this.postServiceFeedBackForm.invalid) {
                this.toaster.error("Please Add Remark for Selecting 1 or 2 Rating");
                return false;
            } else if (this.postServiceFeedBackForm.invalid) {
                this.toaster.warning("Please fill Rating!");
                return false;
            } else {
                this.openConfirmDialog();
                return false;
            }
        } else {
            this.removePostServiceValidators();
            this.openConfirmDialog();
            return false;
        }
    }
} else {
    // Handle other cases or log an error if needed
}


if (this.postServiceFeedBack === 'Post Sales Feedback') {
    
  if (this.delearCustomerCareExCallCreateDetailsFrom.invalid) {
      this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched();
      this.toaster.error("Please Fill Call Status First");
      return false;
  } else if (this.postSalesFeedBackForm) {
      const callType = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.callType.value.typeOfCall;
      const callStatus = this.delearCustomerCareExCallPagePresenter.callDetailsForm.controls.crmCallStatus.value.callStatus;

      if (callType === "Post Sales Feedback" && callStatus === "Completed") {
          this.setPostSalesValidators();
          this.postSalesFeedBackForm.markAllAsTouched();

          let remarksRequired = false;
          this.postSalesFeedBackForm.controls.forEach((element) => {
              const ratingValue = element.get('rating').value;
              if (ratingValue == 1 || ratingValue == 2) {
                  remarksRequired = true;
                  element.get('remarks').setValidators(Validators.required);
              } else {
                  element.get('remarks').clearValidators();
              }
              element.get('remarks').updateValueAndValidity();
          });

          if (remarksRequired && this.postSalesFeedBackForm.invalid) {
              this.toaster.error("Please Add Remark for Selecting 1 or 2 Rating");
              return false;
          } else if (this.postSalesFeedBackForm.invalid) {
              this.toaster.warning("Please fill Rating!");
              return false;
          } else {
              this.openConfirmDialog();
              return false;
          }
      } else {
          this.removePostSalesValidators();
          this.openConfirmDialog();
          return false;
      }
  }
} else {
  // Handle other cases or log an error if needed
}

    // if (this.postSalesFeedBack === 'Post Sales Feedback') { 
    //     if (this.delearCustomerCareExCallCreateDetailsFrom.invalid) {
    //     this.delearCustomerCareExCallCreateDetailsFrom.markAllAsTouched();
    //     this.toaster.error("Please Select Call Status First!");
    //     return false;
    //   }
    //    else {
    //     let remarksRequired = false;
    //     this.postSalesFeedBackForm.controls.forEach((element) => {
    //       const ratingValue = element.get('rating').value;
    //       console.log(ratingValue,'ratingValue')
    //       if (ratingValue == 1 || ratingValue == 2) {
    //         remarksRequired = true;
    //         element.get('remarks').setValidators(Validators.required);
    //       } else {
    //         element.get('remarks').clearValidators();
    //       }
    //       element.get('remarks').updateValueAndValidity();
    //     });

    //     if (remarksRequired && this.postSalesFeedBackForm.invalid) {
    //       this.toaster.error("Please Add Remark for Selecting 1 or 2 Rating");
    //       return false;
    //     }

    //     this.openConfirmDialog();
    //   }
    // } else {

    // }


  }
  viewPrint() {

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
  submitForm() {
  debugger
    if (this.salesEnquiry === 'Sales Enquiry') {
      const saveData = {
        ...this.delearCustomerCareExCallCreateDetailsFrom.getRawValue(),
      };
      delete saveData.callNo;
      saveData.callDate = this.dateService.getDateIntoYYYYMMDD(saveData.callDate);
      if (this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').value.callStatus === 'Completed') {
        saveData.enquiryDetailDto = this.createEnquiryForm.getRawValue();
        saveData.enquiryDetailDto.enquiryDate = this.dateService.getDateIntoYYYYMMDD(saveData.enquiryDetailDto.enquiryDate);
        saveData.enquiryDetailDto.nextFollowUpDate = saveData.enquiryDetailDto.nextFollowUpDate ? this.dateService.getDateIntoDDMMYYYY(saveData.enquiryDetailDto.nextFollowUpDate) : null;
        saveData.enquiryDetailDto.tentativePurchaseDate = saveData.enquiryDetailDto.tentativePurchaseDate ? this.dateService.getDateIntoDDMMYYYY(saveData.enquiryDetailDto.tentativePurchaseDate) : null;
      }
    
      this.customerCareExCallService.saveCallDetails(saveData).subscribe(response => {
        if (response) {
          this.toastr.success(response['message']);
          this.router.navigate(['../'], {
            relativeTo: this.activatedRoute
          });
        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving', 'Transaction Failed');
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction Failed');
      });
    }
    
    if (this.serviceBooking === 'Service Booking') {
      const saveData = {
        ...this.delearCustomerCareExCallCreateDetailsFrom.getRawValue(),
      };
      if (this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').value.callStatus === 'Completed') {
        // Add enquiryDetailDto to saveData
        saveData.serviceBooking = this.bookingDetailsBasicForm.getRawValue();
      
      }

      delete saveData.serviceBooking.machineInventoryId;
      delete saveData.serviceBooking.customerId;
      saveData.serviceBooking.machineInventory = {
        vinId: this.vinId
      }
      saveData.serviceBooking.customerMaster = {
        id: this.CustomerId
      }
      saveData.serviceBooking.draftFlag = 'false';
      delete saveData.callNo;
      delete saveData.endTime;
      delete saveData.status;
      delete saveData.callerMobile;
      delete saveData.callerName;
      delete saveData.serviceBooking.bookingNo;
      delete saveData.serviceBooking.bookingDate;
      delete saveData.serviceBooking.status;
      this.customerCareExCallService.saveCallDetails(saveData).subscribe(response => {

        if (response) {
          this.toastr.success(response['message']);
         this.showServiceBookingForm=false;
          // this.router.navigate(['../'], {
          //   relativeTo: this.activatedRoute
          // });
        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving', 'Transaction Failed')
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction Failed')
      })
    }

    if (this.postServiceFeedBack === 'Post Service Feedback') {
      let finalPostserviceSubmit = {} as finalPostService
      finalPostserviceSubmit = this.delearCustomerCareExCallCreateDetailsFrom.getRawValue();
     
      if (this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').value.callStatus === 'Completed') {
        finalPostserviceSubmit.callFeedback = {} as feedbackDetails
        finalPostserviceSubmit.callFeedback = this.sendPayloadForPostService()
      
      }
      
      delete finalPostserviceSubmit.callNo;
      delete finalPostserviceSubmit.callerMobile,
        delete finalPostserviceSubmit.callerName,
        delete finalPostserviceSubmit.status;
      this.customerCareExCallService.saveCallDetails(finalPostserviceSubmit).subscribe(response => {
        if (response) {
          this.toastr.success(response['message']);
          this.showAllServiceFeedbackForm=false
          

        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving', 'Transaction Failed')
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction Failed')
      })

    }

    if (this.postSalesFeedBack === 'Post Sales Feedback') {
      let finalPostserviceSubmit = {} as finalPostService
      finalPostserviceSubmit = this.delearCustomerCareExCallCreateDetailsFrom.getRawValue();
      
       if (this.delearCustomerCareExCallCreateDetailsFrom.get('crmCallStatus').value.callStatus === 'Completed') {
        finalPostserviceSubmit.callFeedback = {} as feedbackDetails
        finalPostserviceSubmit.callFeedback = this.sendPayloadForPostSales()
      
      }
      
      delete finalPostserviceSubmit.callNo;
      delete finalPostserviceSubmit.callerMobile,
        delete finalPostserviceSubmit.callerName,
        delete finalPostserviceSubmit.status;
      this.customerCareExCallService.saveCallDetails(finalPostserviceSubmit).subscribe(response => {
        if (response) {
          this.toastr.success(response['message']);
          this.showSalesFormData=false;
          // this.router.navigate(['../'], {
          //   relativeTo: this.activatedRoute
          // });

        } else {
          this.isSubmitDisable = false;
          this.toastr.error('Error generated while saving', 'Transaction Failed')
        }
      }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction Failed')
      })

    }



  }
  startRatingData(event) {

    this.startList = event

  }

  private sendPayloadForPostService() {
    const allDataFromTable = this.postServiceFeedBackForm.getRawValue()
    let feedbackDetailsSend = []
    const allPartsTogether = []
    allDataFromTable.forEach(element => {

      allPartsTogether.push({
        callQuesionnaire: {
          id: element.id ? element.id : null,
          quesionnaire: element.Quesionnaire ? element.Quesionnaire : null
        },
        rating: parseInt(element.rating ? element.rating : null),
        remarks: element.remarks ? element.remarks : null,
        jcId: this.jcId ? this.jcId : null

      })

    })

    feedbackDetailsSend = [...allPartsTogether]

    return feedbackDetailsSend ? feedbackDetailsSend : null
  }

  private sendPayloadForPostSales() {
    const allDataFromTable = this.postSalesFeedBackForm.getRawValue()
    let feedbackDetailsSend = []
    const allPartsTogether = []
    allDataFromTable.forEach(element => {

      allPartsTogether.push({
        callQuesionnaire: {
          id: element.id ? element.id : null,
          quesionnaire: element.Quesionnaire ? element.Quesionnaire : null
        },
        rating: parseInt(element.rating ? element.rating : null),
        remarks: element.remarks ? element.remarks : null,
        dcId: this.dcID ? this.dcID : null

      })

    })

    feedbackDetailsSend = [...allPartsTogether]

    return feedbackDetailsSend ? feedbackDetailsSend : null
  }


  notifyTicketBook(event) {
    this.postServiceFeedBackBasedJobCard = event
  }
  showAllForm(event) {
  
    this.showAllServiceFeedbackForm = event
  }
  getJcId(id) {
    this.jcId = id
  }
  getCustomerId(id) {
    this.CustomerId = id
  }
  getVin(id) {
    this.vinId = id
  }
  getHistoryData(event) {
    this.callHistoryData = event;
  }

  showSalesForm(event){
   
    this.showSalesFormData=event

  }

  getDcId(event){
  
    this.dcID=event
  }

  getHistoryDataforSales(event){
    this.viewSalesCallHistory=event
  }
  serviceBookingShowHide(event){
  
    if(event===true){
      this.showServiceBookingForm=true
   
    }else{
      this.showServiceBookingForm=false
    }
  }
  getcheckingServiceBookingDoneOrNotFlag(event){
   
   if(event===true){
    this.hideSubmitButton=true
    console.log('true')
   }else{
    this.hideSubmitButton=false
   }    
  }

  getCustomerDetails(customerDetails){
    
    
    this.customerDetailsForm.patchValue({
      address1: customerDetails.address1,
            address2: customerDetails.address2,
            address3:customerDetails.address3,
            locality: customerDetails.locality,
            village: customerDetails.city,
            tehsil:customerDetails.tehsil,
            city:customerDetails.city,
            district: customerDetails.district,
            state: customerDetails.state,
            country: customerDetails.country,
            pinCode: customerDetails.pinCode,
            title: customerDetails.title,
            firstName: customerDetails.firstName,
            middleName: customerDetails.lastName,
            lastName: customerDetails.lastName,
            custCode: customerDetails.custCode,
            // soldDealerName: [{value:null, disabled:true}],
            mobileNumber: customerDetails.mobileNumber,
            alternateMobileNo: customerDetails.alternateMobileNo,
            telephone: customerDetails.telephone,
            
            preferedLanguage: customerDetails.preferedLanguage,
            panNo:customerDetails.panNo,
            gstin: customerDetails.gstin,
            // contactPersonMobile: [{value:null, disabled:true}],
            // relationWithCustomer: [{value:null, disabled:true}],
    })
  }

  getMachineDetails(machineDetails){
      
    this.machineDetailsForm.patchValue({
      chassisNo: machineDetails[0].chassisNo,
            product: machineDetails[0].product,
            model: machineDetails[0].model,
            subModel:machineDetails[0].subModel,
            variant: machineDetails[0].variant,
            item: machineDetails[0].item,
            dateOfInstallation: machineDetails[0].dateOfInstallation,
            engineNo:machineDetails[0].engineNumber,
            warrantyValidTill: machineDetails[0].warrantyValidTill,
            // dealerId: [{ value: null, disabled: true }],
    })
    this.customerDetailsForm.patchValue({
      soldDealerName:machineDetails[0].dealerName,
      contactPersonMobile:machineDetails[0].dealerContactNo,
      relationWithCustomer:machineDetails[0].dealerContactName
    })
  }
 
  getServiceDetailsData(serviceDetailsData){
    this.machineDetailsForm.patchValue({
      chassisNo: serviceDetailsData[0].chassisNo,
            product: serviceDetailsData[0].product,
            model: serviceDetailsData[0].model,
            subModel:serviceDetailsData[0].subModel,
            variant: serviceDetailsData[0].variant,
            item: serviceDetailsData[0].item,
            dateOfInstallation: serviceDetailsData[0].dateOfInstallation,
            engineNo:serviceDetailsData[0].engineNumber,
            warrantyValidTill: serviceDetailsData[0].warrantyValidTill,
            // dealerId: [{ value: null, disabled: true }],
    })
    this.customerDetailsForm.patchValue({
      soldDealerName:serviceDetailsData[0].dealerName,
      contactPersonMobile:serviceDetailsData[0].dealerContactNo,
      relationWithCustomer:serviceDetailsData[0].dealerContactName

    })
  }
  getServiceCustomerDetails(serviceCustomerDetails){
    this.customerDetailsForm.patchValue({
      address1: serviceCustomerDetails.address1,
            address2: serviceCustomerDetails.address2,
            address3:serviceCustomerDetails.address3,
            locality: serviceCustomerDetails.locality,
            village: serviceCustomerDetails.city,
            tehsil:serviceCustomerDetails.tehsil,
            city:serviceCustomerDetails.city,
            district: serviceCustomerDetails.district,
            state: serviceCustomerDetails.state,
            country: serviceCustomerDetails.country,
            pinCode: serviceCustomerDetails.pinCode,
            title: serviceCustomerDetails.title,
            firstName: serviceCustomerDetails.firstName,
            middleName: serviceCustomerDetails.lastName,
            lastName: serviceCustomerDetails.lastName,
            custCode: serviceCustomerDetails.custCode,
            // soldDealerName: [{value:null, disabled:true}],
            mobileNumber: serviceCustomerDetails.mobileNumber,
            alternateMobileNo: serviceCustomerDetails.alternateMobileNo,
            telephone: serviceCustomerDetails.telephone,
            
            preferedLanguage: serviceCustomerDetails.preferedLanguage,
            panNo:serviceCustomerDetails.panNo,
            gstin: serviceCustomerDetails.gstin,
            // contactPersonMobile: [{value:null, disabled:true}],
            // relationWithCustomer: [{value:null, disabled:true}],
    })
  }
}



