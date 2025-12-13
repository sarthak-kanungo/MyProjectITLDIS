import { AfterViewInit, Component, Inject,OnDestroy, OnInit,ViewChild } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatAutocompleteSelectedEvent, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { UploadableFile } from 'itldis-file-upload';
import { ToastrService } from 'ngx-toastr';
import { SubscriptionLike } from 'rxjs';
import { ButtonAction, ConfirmationBoxComponent, ConfirmDialogData } from 'src/app/confirmation-box/confirmation-box.component';
import { DateService } from 'src/app/root-service/date.service';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { FileUploadService } from 'src/app/ui/file-upload/file-upload.service';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { DirectSurveyService } from '../../direct-survey.service';
import { CallAttempt, QuestinAns, SoldToDealer, SubmitDirectSurveyForm, SurveyHeader, VillageSearch } from '../../domain/direct-survey-domain';
import { DirectSurveyApi } from '../../url-util/direct-survey-urls';
import { CallAttemptDetailsComponent } from '../call-attempt-details/call-attempt-details.component';
import { ComplainComponent } from '../complain/complain.component';
import { DirectSurveyCustomerDetailComponent } from '../direct-survey-customer-detail/direct-survey-customer-detail.component';
import { DirectSurveyPagePresenter } from './direct-survey-page.presenter';
import { DirectSurveyPageStore } from './direct-survey-page.store';

@Component({
  selector: 'app-create-direct-survey',
  templateUrl: './create-direct-survey.component.html',
  styleUrls: ['./create-direct-survey.component.scss'],
  providers: [DirectSurveyPagePresenter, DirectSurveyPageStore, DirectSurveyService]
})
export class CreateDirectSurveyComponent implements OnInit,AfterViewInit,OnDestroy {

  surveyMainForm: FormGroup
  public fileStaticPath: string = DirectSurveyApi.staticPath;
  public fileStaticPath1: string = DirectSurveyApi.staticPath;


  directSurveyCreateDetailsFrom: FormGroup
  customerDetailsForm: FormGroup
  surveyMachineDetailsForm: FormGroup
  callAttemptForm: FormGroup
  complaintForm: FormArray
  complaintRecordingForm: FormGroup
  freeServiceHistoryForm: FormArray
  surveyHistoryForm: FormArray
  contactMachineImplementForm: FormGroup
  otherMachineDeatilsForm: FormArray
  referenceDetailsForm: FormGroup
  cropForm: FormGroup
  currentSurveyForm: FormArray


  isEdit: boolean
  isView: boolean
  isCreate: boolean
  surveyType: Array<any> = []
  surveyStatus: Array<any> = []
  systemDate = new Date();
  isCallAttempt: boolean = true
  surveyTypeSelection: string;
  isCurrentSurvey: boolean
  isServiceHistory: boolean
  isCallHistory: boolean
  forButton: boolean
  reminderId: number
  @ViewChild(DirectSurveyCustomerDetailComponent,{static: false}) customer:DirectSurveyCustomerDetailComponent
  @ViewChild(CallAttemptDetailsComponent,{static: false}) callview:CallAttemptDetailsComponent
  @ViewChild(ComplainComponent,{static: false}) complaint:ComplainComponent
  

  quesAnsListForView:any[]=[]
  subscription: SubscriptionLike;

  villageListView: VillageSearch[]=[]
  surveyNo:string
  isDialogView:boolean = false;
  viewFiles: Array<UploadableFile>  = new Array();
  dealerList: SoldToDealer[]=[]

  dataFromSummery:any
  answerValidation: any;
  b: boolean;

  constructor(private directSurveyPagePresenter: DirectSurveyPagePresenter,
    private service: DirectSurveyService,
    private dateService: DateService,
    private activityRoute: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    public dialog: MatDialog,
    private loginService: LoginFormService,
    private fileUploadService: FileUploadService,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) { 
      if(data && data.surveyNo){
        this.isDialogView = true;
        this.isView = true;
        this.surveyNo = data.surveyNo;
      }

    }

  ngOnInit(): void {
    this.getSurveyType()
    this.directSurveyCreateDetailsFrom = this.directSurveyPagePresenter.searchDirectSurveyCreateDetailsFrom
    this.customerDetailsForm = this.directSurveyPagePresenter.searchDirectSurveyCreateCustomerDetailsForm
    this.surveyMachineDetailsForm = this.directSurveyPagePresenter.searchDirectSurveyCreateMachineDetailsForm
    this.callAttemptForm = this.directSurveyPagePresenter.calAttemptDetails
    this.complaintForm = this.directSurveyPagePresenter.complainForm
    this.complaintRecordingForm = this.directSurveyPagePresenter.complainRecordingForm
    this.freeServiceHistoryForm = this.directSurveyPagePresenter.freeServiceForm
    this.surveyHistoryForm = this.directSurveyPagePresenter.surveyHistoryForm
    this.contactMachineImplementForm = this.directSurveyPagePresenter.contactMachineImplementForm
    this.cropForm = this.directSurveyPagePresenter.cropForm
    this.otherMachineDeatilsForm = this.directSurveyPagePresenter.otherMachineDetailsForm
    this.referenceDetailsForm = this.directSurveyPagePresenter.referenceDetails
    this.currentSurveyForm = this.directSurveyPagePresenter.currentSurveyForm
    this.surveyMainForm = this.directSurveyPagePresenter.createDirectSurveyForm
    
    if(this.isDialogView){
      this.patchData(this.surveyNo);
    }
    this.directSurveyPagePresenter.operation = OperationsUtil.operation(this.activityRoute)
    this.directSurveyCreateDetailsFrom.get('directSurveyDate').patchValue(this.systemDate)
    this.getSurveyStatus()
    this.viewOrEditOrCreate()
    if (this.isEdit || this.isView  ) {
      this.activityRoute.queryParamMap.subscribe(param => {
        if (param.has('surveyNo')) {
          this.patchData(param.get('surveyNo'))
        }
      })
    }

  }
  ngAfterViewInit(): void {
    // if (this.isEdit || this.isView  ) {
    //   this.patchData()
    // }
  }

  private viewOrEditOrCreate() {
    if (this.directSurveyPagePresenter.operation === Operation.CREATE) {
      this.isCreate = true
    }
    if (this.directSurveyPagePresenter.operation === Operation.EDIT) {
      this.isEdit = true

    }
    if (this.directSurveyPagePresenter.operation === Operation.VIEW) {
      this.isView = true
      if (this.surveyTypeSelection === 'TELEPHONIC') {
        this.isCallAttempt = true
      }
    }
  }

  showAndViewSurveyType(type) {
    //if (typeof type === 'object'){}
    // this.surveyTypeSelection=type.Survey_type_desc
    if (type === 'TELEPHONIC') {
      this.forButton = true
    } else if (type === 'DIRECT') {
      this.forButton = false
    }

  }

  getSurveyType() {
    this.service.getSurveyType().subscribe(res => {
      this.surveyType = res['result']
      this.activityRoute.queryParamMap.subscribe(param => {
        this.dataFromSummery = param
        this.reminderId = parseInt(param.get('reminderId'))
        if (param.has('survetType') === true) {
          this.surveyTypeSelection = param.get('survetType')
          this.directSurveyCreateDetailsFrom.get('status').patchValue(param.get('status'))
          let typeFromSS = this.surveyType.filter(obj => obj['Survey_type_desc'] === this.surveyTypeSelection)[0]
          this.directSurveyCreateDetailsFrom.get('surveyType').patchValue(typeFromSS)
          if (this.isCreate) {
            this.directSurveyCreateDetailsFrom.get('surveyDoneBye').patchValue(this.loginService.getLoginUser().name)
          }
          this.showAndViewSurveyType(this.surveyTypeSelection)
        }
      })

    })
  }

  getSurveyStatus() {
    this.service.getSurveyStatus().subscribe(res => {
      this.surveyStatus = res['result']
    })
  }

  buildJsonForsurveyHeader() {
    let submitDirectSurveyForm = {} as SubmitDirectSurveyForm
    const headerFormValue = this.directSurveyCreateDetailsFrom.getRawValue()
    let directSurveyHeader = {} as SurveyHeader
    directSurveyHeader.surveyTypeId = headerFormValue.surveyType ? headerFormValue.surveyType.Survey_type_id : null
    directSurveyHeader.surveyDate = this.dateService.getDateIntoDDMMYYYY(headerFormValue.directSurveyDate)
    directSurveyHeader.surveyStatus = headerFormValue.status ? headerFormValue.status.lookupId : null
    directSurveyHeader.surveyDoneBy = this.loginService.getLoginUser().loginUserId
    directSurveyHeader.surveyRemdId = this.reminderId
    directSurveyHeader.soldDealerName = this.customerDetailsForm.get('soldDealerName').value
    directSurveyHeader.soldToDealerId = this.directSurveyCreateDetailsFrom.get('soldToDealer').value ? this.directSurveyCreateDetailsFrom.get('soldToDealer').value.id:null

    // directSurveyHeader.customerCode =
    this.service.fetchCustomerId.subscribe(id => {
      directSurveyHeader.customerMasterId = parseInt(id)
    })
    directSurveyHeader.customerName = this.customerDetailsForm.get('firstName').value
    directSurveyHeader.customerMobileNumber = this.customerDetailsForm.get('mobileNumber').value
    directSurveyHeader.customerAlternateNo = this.customerDetailsForm.get('alternateMobileNo').value
    directSurveyHeader.customerAddress = this.customerDetailsForm.get('address1').value
    directSurveyHeader.country = this.customerDetailsForm.get('country').value
    directSurveyHeader.state = this.customerDetailsForm.get('state').value
    directSurveyHeader.district = this.customerDetailsForm.get('district').value
    directSurveyHeader.tehsil = this.customerDetailsForm.get('tehsil').value
    directSurveyHeader.city = this.customerDetailsForm.get('city').value
    directSurveyHeader.pinCode = this.customerDetailsForm.get('pinCode').value
    directSurveyHeader.panNumber = this.customerDetailsForm.get('panNo').value
    directSurveyHeader.gstNo = this.customerDetailsForm.get('gstin').value
    directSurveyHeader.preferedLanguage = this.customerDetailsForm.get('preferedLanguage').value
    directSurveyHeader.satisfactionLevel = this.customerDetailsForm.get('satisfactionLevel').value
    directSurveyHeader.pinCode = this.customerDetailsForm.get('pinCode').value
    this.service.fetchVinId.subscribe(vin => {
      directSurveyHeader.vinId = vin
    })
    directSurveyHeader.dateOfInstallation = this.dateService.getDateIntoDDMMYYYY(this.surveyMachineDetailsForm.get('dateOfInstallation').value)
    directSurveyHeader.warranyValidTill = this.dateService.getDateIntoDDMMYYYY(this.surveyMachineDetailsForm.get('warrantyValidTill').value)

    if (this.surveyTypeSelection === 'DIRECT') {
      directSurveyHeader.contactPersonName = this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('name').value
      directSurveyHeader.contactPersonProfileId = this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('profile').value ? this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('profile').value.lookupId : null
      directSurveyHeader.contactPersonMobileNo = this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('mobileNo').value
      directSurveyHeader.contactPersonAge = this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('age').value

      directSurveyHeader.ageOfMachineInMonth = this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('ageOfMachine').value
      directSurveyHeader.hoursMeterReading = this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('houseMeterReading').value
      directSurveyHeader.firstTimeBuyer = this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('firstTimeBuyer').value
      directSurveyHeader.brandModel = this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('brand').value
      directSurveyHeader.factorInfluencedId = this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('factorInfluenced').value ? this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('factorInfluenced').value.lookupId : null

    }


    directSurveyHeader.refCustomerName1 = this.referenceDetailsForm.get('customerName1').value
    directSurveyHeader.refCustomerMobileNo1 = (this.referenceDetailsForm.get('mobileNo1').value)
    directSurveyHeader.refCustomerAddress1 = this.referenceDetailsForm.get('address1').value
    directSurveyHeader.refCustomerVillage1 = this.referenceDetailsForm.get('village1').value ? this.referenceDetailsForm.get('village1').value.villageName : null
    directSurveyHeader.refCustomerName2 = this.referenceDetailsForm.get('customerName2').value
    directSurveyHeader.refCustomerMobileNo2 = this.referenceDetailsForm.get('mobileNo2').value
    directSurveyHeader.refCustomerAddress2 = this.referenceDetailsForm.get('address2').value
    directSurveyHeader.refCustomerVillage2 = this.referenceDetailsForm.get('village2').value ? this.referenceDetailsForm.get('village2').value.villageName : null


    directSurveyHeader.additionalComments = this.referenceDetailsForm.get('additionalComment').value

    // header.push(directSurveyHeader)
    submitDirectSurveyForm.surveyHeader = directSurveyHeader
    // directSurveyHeader.callAttempt = this.buildJsonForCallAttempt()
    directSurveyHeader.surveyQuestions = this.buildJsonForCurrentSurvey()
    directSurveyHeader.complaint = this.complaintForm.getRawValue()
    directSurveyHeader.implementDetails = this.contactMachineImplementForm.get('majorImplement').value as Array<any>
    directSurveyHeader.crops = this.buildJsonForImplementCrops()
    directSurveyHeader.otherPurchaseDetails = this.otherMachineDeatilsForm.getRawValue()

    console.log('submit---', directSurveyHeader);

    return directSurveyHeader
  }



  buildJsonForCurrentSurvey() {
    let quesAns:QuestinAns[]=[]
    let mainList:any[]=[]
    let inCaseDirect:any=[]
    const currentSurvey= this.currentSurveyForm.getRawValue();
    currentSurvey.forEach(list=>{
      quesAns.push({
        questionDesc:list.question,
        quesId:list.quesId,
        mainAnswer:list.answer ? list.answer.mainAnsDesc : null,
        quesDtlId:list.answer ? list.answer.quesDtlId:null,
        remarks:list.remarks,
        // subAnswer:((list.subAnswer as []).filter(ans => ans['subAnswer']==true).map(ans => ans['subAnswerDesc']))
        subAnswer:list.subAnswer
      })
    })
    
    quesAns.forEach(qs=>{
      if (qs.subAnswer) {
        if(qs.subAnswer.length==0){
          mainList.push({
            questionDesc: qs.questionDesc,
            quesId: qs.quesId,
            mainAnswer: qs.mainAnswer,
            quesDtlId:  qs.quesDtlId,
            remarks: qs.remarks
            })
        }
        else{
          let flag:boolean = false;
          qs.subAnswer.forEach(val=>{
            if (val.subAnswer) {
              mainList.push({
                questionDesc: qs.questionDesc,
                quesId: qs.quesId,
                mainAnswer: qs.mainAnswer,
                quesDtlId:  qs.quesDtlId,
                subAnswer: val.subAnswerDesc,
                quesSubDtlId: val.quesSubDTLID,
                remarks: qs.remarks
                })
                flag = true;
            }
          });
          if(!flag){
            mainList.push({
                questionDesc: qs.questionDesc,
                quesId: qs.quesId,
                mainAnswer: qs.mainAnswer,
                quesDtlId:  qs.quesDtlId,
                remarks: qs.remarks
            })
          }
        }
      }
    })

    currentSurvey.forEach(dir=>{
      inCaseDirect.push({
        questionDesc:dir.question,
        mainAnswer:dir.answer?dir.answer.mainAnsDesc:null,
        remarks:dir.remarks
      })
    })
    if (this.surveyTypeSelection==='TELEPHONIC') {
      return mainList
    }else if (this.surveyTypeSelection==='DIRECT') {
      return inCaseDirect
    }


  }

  buildJsonForCallAttempt() {
    let callAttemptForm = {} as CallAttempt
    let call: any[] = []
    callAttemptForm.surveyRemdId = this.reminderId
    callAttemptForm.responseTypeId = this.callAttemptForm.get('responseType').value ? this.callAttemptForm.get('responseType').value.lookupId : null
    callAttemptForm.callDate = this.callAttemptForm.get('callAttemptDate').value ? (this.dateService.getDateIntoDDMMYYYY(this.callAttemptForm.get('callAttemptDate').value)) : null
    callAttemptForm.callTime = this.callAttemptForm.get('callAttemptTime').value
    callAttemptForm.callRemarks = this.callAttemptForm.get('additinalComments').value
    // call.push(callAttemptForm)

    if (this.surveyTypeSelection === 'TELEPHONIC') {
      return callAttemptForm
    }
  }

  buildJsonForComplaint() {
    let submitDirectSurveyForm = {} as SubmitDirectSurveyForm
    const complaint = this.complaintForm.getRawValue()
    submitDirectSurveyForm.complaint = complaint
    if (this.surveyTypeSelection === 'TELEPHONIC') {
      return complaint
    }

  }

  buildJsonForRecordingFile() {
    let submitDirectSurveyForm = {} as SubmitDirectSurveyForm
    if (this.isCallAttempt) {
      submitDirectSurveyForm.callRecording = this.fileUploadService.listUploadableFiles()
    }
    if (this.isCurrentSurvey) {
      submitDirectSurveyForm.complaintRecording = this.fileUploadService.complaintlistUploadableFiles()
    }
    return submitDirectSurveyForm
  }



  buildJsonForImplementCrops() {
    let submitDirectSurveyForm = {} as SubmitDirectSurveyForm
    submitDirectSurveyForm.implementDetails = this.contactMachineImplementForm.get('majorImplement').value

    let cropArray: any[] = []
    const crops = this.cropForm.getRawValue()
    if (crops.crop) {
      crops.crop.forEach(element => {
        cropArray.push({ 'cropGrown': element.cropGrown })
      });
    }
    submitDirectSurveyForm.crops = cropArray

    if (this.surveyTypeSelection === 'DIRECT') {
      return cropArray
    }
  }


  buildJsonForOtherPurchaseDetails() {
    let submitDirectSurveyForm = {} as SubmitDirectSurveyForm
    const otherPurchaseDetails = this.otherMachineDeatilsForm.getRawValue()
    submitDirectSurveyForm.otherPurchaseDetails = otherPurchaseDetails

    if (this.surveyTypeSelection === 'DIRECT') {
      return submitDirectSurveyForm
    }
  }



  validateForm() { 

    this.directSurveyCreateDetailsFrom.markAllAsTouched();
    if(!this.directSurveyCreateDetailsFrom.valid){
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
      return;
    }
    if(!this.directSurveyCreateDetailsFrom.valid){
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
       
      return;
    }
    if(this.currentSurveyForm.valid && this.surveyTypeSelection === 'DIRECT' && this.isCreate ||this.surveyTypeSelection === 'TELEPHONIC'){
      // console.log(this.currentSurveyForm,'form')
     if(this.currentSurveyForm.controls.length==0){
      this.toastr.error('Please Click On Current Survey First')
      return;
     }

    }
    if(!this.currentSurveyForm.valid && this.surveyTypeSelection === 'DIRECT'){
        this.currentSurveyForm.controls.forEach(ele=>{
          this.answerValidation=ele.get('answer')
        })
        if(this.answerValidation.status==='INVALID'){
          this.toastr.error('Please Fill All Mandatory field')
          this.currentSurveyForm.markAllAsTouched()
           return;
        }
  
    } 
     
    

    let flag:boolean=true;
    (this.complaintForm.controls as FormGroup[]).forEach(fg => {
      // if ((fg.get('typeOfComplaint').value !=null || fg.get('description').value !=null) && fg.get('department').value==null) {
      //   fg.get('department').setErrors({
      //     deptError:'Please Select Department'
      //   })
      // }

        if(fg.controls.department.value || fg.controls.assignTo.value
            || fg.controls.typeOfComplaint.value || fg.controls.description.value){
            
              if(fg.controls.department.value && fg.controls.assignTo.value
                && fg.controls.typeOfComplaint.value && fg.controls.description.value){

                } else {
                  flag = false;
                  return;
                }
            }
    })
    if(!flag){
      this.toastr.error('Please Enter valid complaint details', "Mandatory Field")
      return false;
    }
    if ((this.complaintForm.status === 'INVALID' || this.currentSurveyForm.status === 'INVALID') && this.surveyTypeSelection === 'TELEPHONIC') {
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
      this.currentSurveyForm.markAllAsTouched()
      this.complaintForm.markAllAsTouched()
      
    }
    else if ((this.callAttemptForm.status === 'INVALID')  && this.surveyTypeSelection === 'TELEPHONIC' && this.isCallAttempt) {
      this.callAttemptForm.markAllAsTouched()
      // if (this.callAttemptForm.get('attachRecording').value == null) {
      //   this.toastr.warning('Please add Call Recording File')
      //   this.callAttemptForm.get('attachRecording').setErrors({
      //     callError:' Please add Recording File'
      //   })
      // }
    }
    else if ((this.otherMachineDeatilsForm.status === 'INVALID' || this.contactMachineImplementForm.status === 'INVALID') && this.surveyTypeSelection === 'DIRECT') {
      this.toastr.error('Please check Mandatory Fields', "Mandatory Field")
      this.otherMachineDeatilsForm.markAllAsTouched()
      this.contactMachineImplementForm.markAllAsTouched()

    }
    else {
      this.openConfirmDialog()
    }
  }



  submitForm() {
    if (this.isCallAttempt && this.surveyTypeSelection === 'TELEPHONIC') {
      this.service.submitCallAttemptData(this.buildJsonForCallAttempt(), this.buildJsonForRecordingFile()).subscribe(res => {
        if (res.status == 200) {
          this.toastr.success(res.message, 'Success');
          if (this.surveyTypeSelection === 'TELEPHONIC') {
            this.router.navigate(['../../survey-summary-report-telephonic'], { relativeTo: this.activityRoute })
          }else if (this.surveyTypeSelection === 'DIRECT') {
            this.router.navigate(['../../survey-summary-report-direct'], { relativeTo: this.activityRoute })
          }
        } else {
          this.toastr.error(res.message)
        }
      })
    }
    else {
      this.service.submitDirectSurvey(this.buildJsonForsurveyHeader(), this.buildJsonForRecordingFile()).subscribe(res => {
        if (res.status == 200) {
          this.toastr.success(res.message, 'Success');
          if (this.surveyTypeSelection === 'TELEPHONIC') {
            this.router.navigate(['../../survey-summary-report-telephonic'], { relativeTo: this.activityRoute })
          }else if (this.surveyTypeSelection === 'DIRECT') {
            this.router.navigate(['../../survey-summary-report-direct'], { relativeTo: this.activityRoute })
          }
        } else {
          this.toastr.error(res.message)
        }
      })
    }

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

  reset() {
    //this.surveyMainForm.reset()
    this.callAttemptForm.reset()
    this.currentSurveyForm.reset()
    this.complaintForm.reset()
    this.complaintRecordingForm.reset()
   }


  showCallAttempt() {
    if (this.surveyTypeSelection === 'TELEPHONIC') {
      this.isCallAttempt = true
      this.isCurrentSurvey = false
    }
  }
  showCurrnet() {
    if (this.surveyTypeSelection === 'TELEPHONIC') {
      this.isCurrentSurvey = true
      this.isCallAttempt = false
    }
    if (this.surveyTypeSelection === 'DIRECT') {
      this.isCurrentSurvey = !this.isCurrentSurvey
    }


  }

  showServiceHistory() {
    this.isServiceHistory = !this.isServiceHistory
  }

  showCallHistory() {
    this.isCallHistory = !this.isCallHistory
  }


   patchData(surveyNo){
    this.autoSoldToDealer('')
      this.service.viewEditSurvey(surveyNo).subscribe(res=>{
        if (res) {
          this.directSurveyCreateDetailsFrom.get('directSurveyNo').patchValue(res['result']['survey'].surveyNo)
          this.directSurveyCreateDetailsFrom.get('surveyDoneBye').patchValue(res['result']['surveyDoneBy'])
          this.directSurveyCreateDetailsFrom.get('directSurveyDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(res['result']['survey'].surveyDate))
          if ( this.directSurveyCreateDetailsFrom.get('directSurveyNo').value==null) {
            this.directSurveyCreateDetailsFrom.get('directSurveyNo').setErrors({
              surveyNoError:'Survey No. is Mandatory'
            })
          }
          this.directSurveyCreateDetailsFrom.get('soldToDealer').patchValue(this.dealerList.filter(dealer=>dealer.id == res['result']['survey'].surveyDoneBy)[0])
          this.directSurveyCreateDetailsFrom.get('soldToDealer').disable()
          //this.customer.getCustomerDetails(res['result']['survey'].customerMobileNumber)
          this.customerDetailsForm.get('soldDealerName').patchValue(res['result']['survey'].soldDealerName)
          this.customerDetailsForm.get('firstName').patchValue(res['result']['survey'].customerName)
          this.customerDetailsForm.get('mobileNumber').patchValue(res['result']['survey'].customerMobileNumber)
          this.customerDetailsForm.get('alternateMobileNo').patchValue(res['result']['survey'].customerAlternateNo)
          this.customerDetailsForm.get('address1').patchValue(res['result']['survey'].customerAddress)
          this.customerDetailsForm.get('country').patchValue(res['result']['survey'].country)
          this.customerDetailsForm.get('state').patchValue(res['result']['survey'].state)
          this.customerDetailsForm.get('district').patchValue(res['result']['survey'].district)
          this.customerDetailsForm.get('tehsil').patchValue(res['result']['survey'].tehsil)
          this.customerDetailsForm.get('city').patchValue(res['result']['survey'].city)
          this.customerDetailsForm.get('pinCode').patchValue(res['result']['survey'].pinCode)
          this.customerDetailsForm.get('panNo').patchValue(res['result']['survey'].panNumber)
          this.customerDetailsForm.get('gstin').patchValue(res['result']['survey'].gstNo)
          this.customerDetailsForm.get('preferedLanguage').patchValue(res['result']['survey'].preferedLanguage)
          this.customerDetailsForm.get('satisfactionLevel').patchValue(res['result']['survey'].satisfactionLevel)
          this.customerDetailsForm.get('pinCode').patchValue(res['result']['survey'].pinCode)

              
              this.service.viewProfile.subscribe(profile=>{
                if (profile) {
                  this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('name').patchValue(res['result']['survey'].contactPersonName)
                  this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('profile').patchValue(profile.filter(pro=> pro.lookupId ==res['result']['survey'].contactPersonProfileId)[0])
                  this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('mobileNo').patchValue(res['result']['survey'].contactPersonMobileNo)
                  this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('age').patchValue(res['result']['survey'].contactPersonAge)
                }
              })
              this.service.viewFactorInfluenced.subscribe(factor=>{
                if (factor) {
                  this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('ageOfMachine').patchValue(res['result']['survey'].ageOfMachineInMonth)
                  this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('houseMeterReading').patchValue(res['result']['survey'].hoursMeterReading)
                  this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('firstTimeBuyer').patchValue(this.directSurveyPagePresenter.buyerType.filter(buy => buy.value == res['result']['survey'].firstTimeBuyer))
                  this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('brand').patchValue(res['result']['survey'].brandModel)
                  this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('factorInfluenced').patchValue(factor.filter(factor=> factor.lookupId ==res['result']['survey'].factorInfluencedId)[0])
                }
              })
              
              
  
              this.patchMachineData( res['result']['survey'].vinId)
              // if (res['result']['survey'].callAttempt[0]) {
              //   this.patchCallAttemptData(res['result']['survey'].callAttempt[0])
              // }
  
              this.patchComplaintData(res['result']['survey'].complaint,res['result']['complaintRecording'])
  
              // this.patchFreeService(res['result']['survey'].customerMasterId,res['result']['survey'].vinId)
  
              // this.patchSurveyHistory( res['result']['survey'].vinId, res['result']['survey'].customerMasterId)
  
              this.patchImplementDetails(res['result']['survey'].implementDetails)
              this.patchCropList(res['result']['survey'].crops)
              this.patchOtherMachineDeatails(res['result']['survey'].otherPurchaseDetails)
  
              this.referenceDetailsForm.get('customerName1').patchValue(res['result']['survey'].refCustomerName1)
              this.referenceDetailsForm.get('customerName2').patchValue(res['result']['survey'].refCustomerName2)
  
              this.referenceDetailsForm.get('mobileNo1').patchValue(res['result']['survey'].refCustomerMobileNo1)
              this.referenceDetailsForm.get('mobileNo2').patchValue(res['result']['survey'].refCustomerMobileNo2)
  
              this.referenceDetailsForm.get('address1').patchValue(res['result']['survey'].refCustomerAddress1)
              this.referenceDetailsForm.get('address2').patchValue(res['result']['survey'].refCustomerAddress2)

          
          this.service.viewProfile.subscribe(profile=>{
            if (profile) {
              this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('name').patchValue(res['result']['survey'].contactPersonName)
              this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('profile').patchValue(profile.filter(pro=> pro.lookupId ==res['result']['survey'].contactPersonProfileId)[0])
              this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('mobileNo').patchValue(res['result']['survey'].contactPersonMobileNo)
              this.contactMachineImplementForm.get('searchCurrentSurveyContactPersonDetailsDetails').get('age').patchValue(res['result']['survey'].contactPersonAge)
            }
          })
          this.service.viewFactorInfluenced.subscribe(factor=>{
            if (factor) {
              this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('ageOfMachine').patchValue(res['result']['survey'].ageOfMachineInMonth)
              this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('houseMeterReading').patchValue(res['result']['survey'].hoursMeterReading)
              this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('firstTimeBuyer').patchValue(this.directSurveyPagePresenter.buyerType.filter(buy => buy.value == res['result']['survey'].firstTimeBuyer))
              this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('brand').patchValue(res['result']['survey'].brandModel)
              this.contactMachineImplementForm.get('searchCurrentSurveyMachineDetailsDetails').get('factorInfluenced').patchValue(factor.filter(factor=> factor.lookupId ==res['result']['survey'].factorInfluencedId)[0])
            }
          })
          // if (res['result']['survey'].callAttempt[0]) {
          //   this.patchCallAttemptData(res['result']['survey'].callAttempt[0])
          // }

          // this.patchComplaintData(res['result']['survey'].complaint,res['result']['complaintRecording'])

          // this.patchFreeService(res['result']['survey'].customerMasterId,res['result']['survey'].vinId)

          // this.patchSurveyHistory( res['result']['survey'].vinId, res['result']['survey'].customerMasterId)

          this.patchImplementDetails(res['result']['survey'].implementDetails)
          this.patchCropList(res['result']['survey'].crops)
          this.patchOtherMachineDeatails(res['result']['survey'].otherPurchaseDetails)

          this.referenceDetailsForm.get('customerName1').patchValue(res['result']['survey'].refCustomerName1)
          this.referenceDetailsForm.get('customerName2').patchValue(res['result']['survey'].refCustomerName2)

          this.referenceDetailsForm.get('mobileNo1').patchValue(res['result']['survey'].refCustomerMobileNo1)
          this.referenceDetailsForm.get('mobileNo2').patchValue(res['result']['survey'].refCustomerMobileNo2)

          this.referenceDetailsForm.get('address1').patchValue(res['result']['survey'].refCustomerAddress1)
          this.referenceDetailsForm.get('address2').patchValue(res['result']['survey'].refCustomerAddress2)

          let Village1: VillageSearch={
            villageId: '',
            villageName: res['result']['survey'].refCustomerAddress1,
            value:''
          }
          this.referenceDetailsForm.get('village1').patchValue(Village1)
          
          let Village2: VillageSearch={
            villageId: '',
            villageName: res['result']['survey'].refCustomerAddress2,
            value:''
          }
          this.referenceDetailsForm.get('village2').patchValue(Village2)

          this.referenceDetailsForm.get('additionalComment').patchValue(res['result']['survey'].additionalComments)

          this.patchQuesAns(res['result']['survey'].surveyQuestions);
        }
      })

      this.contactMachineImplementForm.disable()
      this.cropForm.disable()
      this.referenceDetailsForm.disable()
      
    }

    patchCallAttemptData(call:any){
      this.isCallAttempt =true
      this.service.viewCall.subscribe(callList=>{
        this.callAttemptForm.disable()
        this.callAttemptForm.get('callAttemptDate').patchValue(this.dateService.stringToDateIntoDDMMYYYY(call.callDate))
        this.callAttemptForm.get('callAttemptTime').patchValue(call.callTime)
        this.callAttemptForm.get('additinalComments').patchValue(call.callRemarks)
        if (callList!=undefined) {
          if (call.recordingFileName) {
            if(this.callview ) {
              this.callview.viewTable = true
              this.callview.files.push({
                  id:'',
                  previewUrl:this.fileStaticPath+call.recordingFileName,
                  file:call.recordingFileName
                })
            }
          }
          this.callAttemptForm.get('responseType').patchValue(callList.filter(response=> response.lookupId == call.responseTypeId)[0]) 
          
        }
      })
    }

  patchMachineData(vinId:number){
    this.service.viewMachine.subscribe(machine => {
      if (machine && machine.length >0) {
        this.surveyMachineDetailsForm.patchValue(machine.filter(machine => machine.vinId == vinId)[0])
        this.service.viewMachine.complete()
      }

    })
  }

  patchComplaintData(complaintData,complaintRecording){ 
    complaintData.forEach(element => {
      this.directSurveyPagePresenter.addComplain(element)
    });
    this.complaintForm.disable()
    if (complaintRecording) {
      if(this.complaint ) {
        this.complaint.viewTable = true
        this.complaint.files.push({
            id:complaintRecording.surveyRecordingId,
            previewUrl:this.fileStaticPath1+complaintRecording.recording,
            file:complaintRecording.recording
          })
      }
    }
  }


  // patchFreeService(custMstId:number,vinId){
  //   let res:any[]=[]
  //   this.freeServiceHistoryForm.clear()
  //   if (res.length == 0) {
  //     this.service.getServiceHistory(custMstId,vinId).subscribe(response => {
  //       res = response['result']
  //       response['result'].forEach(element => {
  //         this.directSurveyPagePresenter.addRowForFreeServiceSurvey(element);
  //       });
  //     })
  //   }
  // }
  // patchSurveyHistory(vinId, customerId){
  //   let res:any[]=[]
  //   if (res.length == 0 && this.surveyTypeSelection!=undefined) {
  //     this.service.getDirectSurveyDetails(this.surveyTypeSelection, vinId, customerId).subscribe(response => {
  //       this.surveyHistoryForm.clear();
  //       response['result'].forEach(element => {
  //         this.directSurveyPagePresenter.addRowForSurveyHistory(element);
  //       });
  //     })
  //   }
  // }

  patchImplementDetails(data){
    data.forEach(element => {
      this.directSurveyPagePresenter.addRowImplement(element)
    });
    this.contactMachineImplementForm.disable()
  }

  patchCropList(data){
    this.service.viewCropSelected.subscribe(crop => {
      if (data && crop) {
        let selectedCropGrown = [];
        data.forEach(type => {
          selectedCropGrown.push(crop[crop.findIndex(res => res['cropGrown'] === type.cropGrown)]);
        })
        this.cropForm.get('cropGrown').setValue(selectedCropGrown)
      }
    })
  }

  patchOtherMachineDeatails(data){
    data.forEach(element => {
      this.directSurveyPagePresenter.addRowForOtherMachineDetails(element)
    });
    
    this.otherMachineDeatilsForm.disable()
  }
  patchQuesAns(data){
    this.quesAnsListForView = data
    // console.log(this.quesAnsListForView,'quesAnsListForView')
    //this.isCurrentSurvey = true
  }


  exitForm(){
    if (this.surveyTypeSelection === 'TELEPHONIC') {
      this.router.navigate(['../../survey-summary-report-telephonic'], { relativeTo: this.activityRoute })
    }else if (this.surveyTypeSelection === 'DIRECT') {
      this.router.navigate(['../../survey-summary-report-direct'], { relativeTo: this.activityRoute })
    }
  }
  viewPrint(){}

  selectSoldToDealer(event: MatAutocompleteSelectedEvent) {
    if (event instanceof MatAutocompleteSelectedEvent) {
      this.directSurveyCreateDetailsFrom.get('soldToDealer').setErrors(null);
    }
  }

  displayFnSoldToDealer(soldTodealer: SoldToDealer) {
    return soldTodealer ? soldTodealer.code : undefined;
  }

  autoSoldToDealer(value){
    if (value!=null && value!=undefined && typeof value !== 'object') {
      this.directSurveyCreateDetailsFrom.controls.soldToDealer.setErrors({'selectFromList':'Select from List'});
      this.service.autoSoldToDealer (value).subscribe(res=>{
        this.dealerList=res
      })
    } else if(value!=null && value!=undefined && typeof value === 'object'){
      this.directSurveyCreateDetailsFrom.controls.soldToDealer.setErrors(null)
    }
  }


  ngOnDestroy(){
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

 
}
