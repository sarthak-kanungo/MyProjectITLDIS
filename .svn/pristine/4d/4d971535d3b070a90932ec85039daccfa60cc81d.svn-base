import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, AbstractControl,FormArray } from '@angular/forms';
import { EnquiryPresenter } from '../../services/enquiry-presenter';
import { SaveEnquiry, GenerateEnquiryNumber, ViewEnquiry, FollowupHistory } from '../../domains/enquiry';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { EnquiryV2CreateWebService } from '../../services/enquiry-v2-create-web.service';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { ActivatedRoute, Router } from '@angular/router';
import { KubotaPatterns } from '../../../../../utils/kubota-patterns';
import { DateService } from '../../../../../root-service/date.service';
import { Operation, OperationsUtil } from '../../../../../utils/operation-util';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { FollowupHistoryDialogComponent } from '../followup-history-dialog/followup-history-dialog.component';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { urlService } from './../../../../../webservice-config/baseurl';
import { environment } from './../../../../../../environments/environment';
import { EnquiryCreateCommonService } from './enquiry-create-common.service';

@Component({
  selector: 'app-enquiry-v2-create',
  templateUrl: './enquiry-v2-create.component.html',
  styleUrls: ['./enquiry-v2-create.component.scss'],
  providers: [KubotaPatterns, EnquiryV2CreateWebService, EnquiryPresenter,
    {
      provide: DateAdapter, useClass: AppDateAdapter,
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }],
})
export class EnquiryV2CreateComponent implements OnInit {
  enquiryForm: FormGroup
  isEdit: boolean
  isView: boolean
  isView1: boolean
  isExit: boolean
  isViewForUpdate: boolean
  isViewMobile: boolean
  isShowBtn: boolean
  isShowSubmitBtn: boolean
  isShowSaveBtn: boolean
  isShowClearBtn: boolean
  enquiryByEnquiryNumber: ViewEnquiry
  id: number
  autoPopulateByMobileNo: SaveEnquiry
  prospectMasterId: number
  customerMasterId: number
  followupHistory: Array<FollowupHistory>
  isSubmitDisable:boolean = false;

  constructor(
    private presenter: EnquiryPresenter,
    private dateService: DateService,
    private enquiryV2CreateWebService: EnquiryV2CreateWebService,
    public dialog: MatDialog,
    private loginFormService: LoginFormService,
    private toastr: ToastrService,
    private actRt: ActivatedRoute,
    private router: Router,
    private enquiryCreateCommonService: EnquiryCreateCommonService,
  ) { }

  ngOnInit() {
    localStorage.removeItem('searchFilterPo')
    this.presenter.operation = OperationsUtil.operation(this.actRt)
    this.ViewOrViewMibileOrCreate()
  }

  private ViewOrViewMibileOrCreate() {
    console.log(this.presenter.operation);
    if (this.presenter.operation === Operation.VIEW) {
      console.log(`Viewing Form`)
      this.isView = true
      this.isView1 = true
      this.isExit = false
      this.isShowSubmitBtn = false
      this.isShowSaveBtn = false
      this.presenter.isCashSelected = true
      this.isShowClearBtn = false
      this.enquiryForm = this.presenter.viewEnquiryForm
      this.parseEnqNoAndViewAndViewMobileForm()
      this.formValueChangesForUpdateBtn()
    } else if (this.presenter.operation === Operation.VIEW_MOBILE) {
      console.log(`viewMobile Form`);
      this.isView = false
      this.isView1 = false
      this.isExit = false
      this.isShowSubmitBtn = true
      this.isShowSaveBtn = false
      this.presenter.isCashSelected = true
      this.isShowClearBtn = true
      this.enquiryForm = this.presenter.viewMobileEnquiryForm
      this.parseEnqNoAndViewAndViewMobileForm()
    } else if (this.presenter.operation === Operation.CREATE) {
      console.log(`creating Form`);
      this.isView = false
      this.isView1 = false
      this.isExit = false
      this.isShowSubmitBtn = true
      this.isShowSaveBtn = true
      this.isShowClearBtn = true
      this.presenter.isCashSelected = true
      this.presenter.isFinanceStatus = false
      this.presenter.isSubSidySelect = false
      this.presenter.isExchangerequired = false
      this.enquiryForm = this.presenter.createEnquiryForm
    }
  }

  formValueChangesForUpdateBtn() {
    this.enquiryForm.valueChanges.subscribe(valueChanges => {
      // console.log("valueChanges ", valueChanges);
    })
  }

  private parseEnqNoAndViewAndViewMobileForm() {
    this.actRt.params.subscribe(prms => {
      let enqNo = prms['enqNo'];
      // console.log(enqNo,'enqNo,djbdbdbdbhdhbjdhbjhjbdbhj')

      this.enquiryV2CreateWebService.getEnquiryByEnquiryNumber(`` + atob(prms['enqNo'])).subscribe(response => {
        console.log('enquiryByEnquiryNumber : ', response);
        if (response) {
          this.enquiryByEnquiryNumber = response
          this.id = this.enquiryByEnquiryNumber.id
          /*if (this.enquiryByEnquiryNumber.prospectMaster) {
            this.prospectMasterId = this.enquiryByEnquiryNumber.prospectMaster ? this.enquiryByEnquiryNumber.prospectMaster.id : null
          }*/
          if (this.enquiryByEnquiryNumber.customerMaster) {
            this.customerMasterId = this.enquiryByEnquiryNumber.customerMaster ? this.enquiryByEnquiryNumber.customerMaster.id : null
          }
          if (response.enquiryStatus === 'LOST' || response.enquiryStatus === 'DROP') {
            this.isView = false
            this.isView1 = true
            this.isExit = true
            this.isShowClearBtn = false
            this.enquiryForm.disable()
          }
          if (response.enquiryStatus === 'RETAILED' || response.enquiryStatus === 'ALLOTTED') {
            this.presenter.enquiryForm.get('productIntrested').get('itemNo').disable()
            this.isView = false   /* added by Vinay to not view Update button in case of RETAILED/ALLOTTED */
          }
          this.enquiryV2CreateWebService.followupHistorye(response.enquiryNumber).subscribe(dto => {
            console.log('dto', dto);
            this.followupHistory = dto.result
          })
          this.patchViewImages(response);
        }
      })
    })
    this.presenter.enquiryForm.get('productIntrested').get('model').enable();
  }

  private patchViewImages(domain: any) {
    if (domain) {
      domain.enquiryAttachmentsImages.forEach(img => {
        console.log(`previewUrl`, img.fileName.toString());
        this.enquiryCreateCommonService.files.push({ previewUrl: `${environment.baseUrl}/${environment.api}${urlService.staticPath}/${img.fileName.toString()}`, id: '' + img.id, file: undefined })
        console.log('path', `${environment.baseUrl}/${environment.api}${urlService.staticPath}/${img.fileName.toString()}`);
      })
    }
  }

  dataAutoPopulateByMobileNo(event) {
    this.autoPopulateByMobileNo = event
  }

  validateForm() {
    console.log(this.enquiryForm.getRawValue());
    let retailFinanceDetails = this.presenter.enquiryForm.getRawValue().retailFinanceDetails
    let retailFinanceForm = this.enquiryForm.get('retailFinanceDetails')
    let prospectDetailsForm = this.enquiryForm.get('prospectDetails');
    let productIntrested = this.enquiryForm.get('productIntrested')
    let currentMachineryDetails = this.enquiryForm.get('currentMachineryDetails')
    const amt = retailFinanceDetails.finalExchangePrice + retailFinanceDetails.estimatedFinanceAmount + retailFinanceDetails.subsidyAmount
    if(prospectDetailsForm.get('district') == null || prospectDetailsForm.get('district').value === null){
        this.toastr.error('District is Required');
        prospectDetailsForm.get('district').setErrors({
            error : 'District is Required'
        })
       return ; 
    }else{
        prospectDetailsForm.get('district').setErrors(null);
    }
    console.log("this.enquiryForm--->", this.enquiryForm);
    if (this.enquiryForm.valid) {
        /*if(currentMachineryDetails){
            let machineryDetails = currentMachineryDetails.get('machineryDetails') as FormArray
            if(machineryDetails.getRawValue()[0]===undefined){
                this.enquiryForm.invalid;
                this.toastr.error("Add Machinery Details");
                return;
            }
        }*/
        
        if(retailFinanceDetails.cashLoan.cashLoan === 'Loan'){
          // if(retailFinanceDetails.financierId === undefined || retailFinanceDetails.financierId === null){
          //     retailFinanceForm.get('financier').setErrors({
          //         error : 'Select Financier from List'
          //     })
          //     return;
          // }else{
          //     retailFinanceForm.get('financier').setErrors(null);
          // }
          // if(retailFinanceDetails.financeLoggedInDate === undefined || retailFinanceDetails.financeLoggedInDate === null){
          //     retailFinanceForm.get('financeLoggedInDate').setErrors({
          //         error : 'Select Finance Logged Date'
          //     })
          //     return;
          // }else{
          //     retailFinanceForm.get('financeLoggedInDate').setErrors(null);
          // }
          if(retailFinanceDetails.estimatedFinanceAmount === undefined || retailFinanceDetails.estimatedFinanceAmount === null || retailFinanceDetails.estimatedFinanceAmount === ''){
              retailFinanceForm.get('estimatedFinanceAmount').setErrors({
                  error : 'Enter Estimated Finance Amount'
              })
              return;
          }else{
              retailFinanceForm.get('estimatedFinanceAmount').setErrors(null);
          }
          // if(retailFinanceDetails.financeStatus.financeStatus === undefined || retailFinanceDetails.financeStatus.financeStatus === null){
          //     retailFinanceForm.get('financeStatus').setErrors({
          //         error : 'Select Finance Status'
          //     })
          //     return;
          // }else{
          //     retailFinanceForm.get('financeStatus').setErrors(null);
              
              /*if(retailFinanceDetails.financeStatus.financeStatus === 'Payment Disbursed'){
                  
                  if(retailFinanceDetails.disbursedDate === undefined || retailFinanceDetails.disbursedDate === null){
                      retailFinanceForm.get('disbursedDate').setErrors({
                          error : 'Select Disbursed Date'
                      })
                      return;
                  }else{
                      retailFinanceForm.get('disbursedDate').setErrors(null);
                  }
                  if(retailFinanceDetails.disbursedFinanceAmount === undefined || retailFinanceDetails.disbursedFinanceAmount === null || retailFinanceDetails.disbursedFinanceAmount === ''){
                      retailFinanceForm.get('disbursedFinanceAmount').setErrors({
                          error : 'Enter Disbursed Finance Amount'
                      })
                      return;
                  }else{
                      retailFinanceForm.get('disbursedFinanceAmount').setErrors(null);
                  }
              }else{
                  retailFinanceForm.get('disbursedDate').setErrors(null);
                  retailFinanceForm.get('disbursedFinanceAmount').setErrors(null);
              }*/
          //}
          
      }  
      
      if(retailFinanceDetails.subsidy.subsidy === 'YES'){
          if(retailFinanceDetails.subsidyAmount === undefined || retailFinanceDetails.subsidyAmount === null || retailFinanceDetails.subsidyAmount === ''){
              retailFinanceForm.get('subsidyAmount').setErrors({
                error: 'Enter Subsidy Amount'
              })
              return;
          }else{
              retailFinanceForm.get('subsidyAmount').setErrors(null);
          }
      }
      if (retailFinanceDetails.marginAmount >= 0) {
        retailFinanceForm.get('estimatedFinanceAmount').setErrors(null)
        retailFinanceForm.get('subsidyAmount').setErrors(null)
        productIntrested.get('estimatedExchangePrice').setErrors(null)
        this.openConfirmDialog()
      } 
      else {
        if (retailFinanceDetails.assetValue < amt) {
          this.toastr.error(` Amount exceeds Asset Value`)
          if (retailFinanceForm.get('estimatedFinanceAmount').value > 0) {
            retailFinanceForm.get('estimatedFinanceAmount').setErrors({
              error: 'Amount exceeds Asset Value'
            })
          }
          if (retailFinanceForm.get('subsidyAmount').value > 0) {
            retailFinanceForm.get('subsidyAmount').setErrors({
              error: 'Amount exceeds Asset Value'
            })
          }
          if (productIntrested.get('estimatedExchangePrice').value > 0) {
            productIntrested.get('estimatedExchangePrice').setErrors({
              error: 'Amount exceeds Asset Value'
            })
          }
        }
      }
    } else {
      console.log(this.presenter.enquiryForm.getRawValue());
      this.presenter.markFormAsTouched()
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  reset() {
    this.presenter.resetAll()
    this.presenter.enquiryForm.get('prospectDetails').get("mobileNumber").enable();
  }

  submitEnquiry() {
    if (this.presenter.operation === Operation.VIEW) {
      this.updateEnquiryForView()
    }
    if (this.presenter.operation === Operation.VIEW_MOBILE) {
      this.validateEnquiryForViewMobile()
    }
    if (this.presenter.operation === Operation.CREATE) {
      this.submitEnquiryForCreate()
    }

  }

  submitEnquiryForCreate() {
    this.enquiryV2CreateWebService.postSaveEnquiry(this.buildJsonForSaveEnquiry()).subscribe(formData => {
      console.log(formData);
      let generateEnquiryNumber = formData as BaseDto<GenerateEnquiryNumber>
      if (formData) {
        this.toastr.success(`Enquiry(${generateEnquiryNumber.result.enquiryNumber}) Created Successfully`, 'Success')
        this.router.navigate(['../'], { relativeTo: this.actRt })
      }else{
        this.toastr.error('Error generated while saving','Transaction Failed')
        this.isSubmitDisable = false;
      }
    }, error => {
        this.toastr.error('Error generated while saving','Transaction Failed')
        this.isSubmitDisable = false;
    })
  }

  updateEnquiryForView() {
    this.enquiryV2CreateWebService.updateEnquiry(this.buildJsonForSaveEnquiry()).subscribe(formData => {
        if (formData) {
          this.toastr.success(`Enquiry Updated Successfully`, 'Success')
          this.router.navigate(['../../'], { relativeTo: this.actRt })
        }else{
          this.toastr.error('Error generated while saving','Transaction Failed')
          this.isSubmitDisable = false;
        }
      }, error => {
        this.toastr.error('Error generated while saving','Transaction Failed')
        this.isSubmitDisable = false;
    })
  }

  validateEnquiryForViewMobile() {
    this.enquiryV2CreateWebService.postSaveEnquiry(this.buildJsonForSaveEnquiryApp()).subscribe(formData => {
      console.log(formData);
      if (formData) {
        this.toastr.success(`Enquiry Validate Successfully`, 'Success')
        this.router.navigate(['../../'], { relativeTo: this.actRt })
      }else{
        this.toastr.error('Error generated while saving','Transaction Failed')
        this.isSubmitDisable = false;
      }
    }, error => {
      this.toastr.error('Error generated while saving','Transaction Failed')
      this.isSubmitDisable = false;
  })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Enquiry?';
    if (this.presenter.operation === Operation.VIEW) {
      message = 'Do you want to update Enquiry?';
    } else if (this.presenter.operation === Operation.VIEW_MOBILE) {
      message = 'Do you want to validate Enquiry?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitEnquiry()
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitEnquiry()
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

  private buildJsonForSaveEnquiry() {
    const enquiry = {
      ...this.buildCreateById(),
      ...this.buildJsonSetForGeneralInfo(),
      ...this.buildJsonSetForProductIntrested(),
      ...this.buildJsonSetForProspectDetails(),
      ...this.buildJsonSetForProspectBackGround(),
      ...this.buildJsonSetForcurrentMachineryDetails(),
      ...this.buildJsonSetForRetailFinanceDetails(),
    }
    let formData: FormData = new FormData();
    formData.append('enquiry', new Blob([JSON.stringify(enquiry)], { type: 'application/json' }))
    this.enquiryCreateCommonService.files.forEach(markImg => {
      formData.append('multipartFileList', markImg['file'])
    })
    console.log('update enquiry--->', enquiry);
    return formData
  }
  private buildJsonForSaveEnquiryApp() {
    const enquiry = {
      ...this.buildCreateById(),
      ...this.buildJsonSetForGeneralInfo(),
      ...this.buildJsonSetForProductIntrested(),
      ...this.buildJsonSetForProspectDetails(),
      ...this.buildJsonSetForProspectBackGround(),
      ...this.buildJsonSetForcurrentMachineryDetails(),
      ...this.buildJsonSetForRetailFinanceDetails(),
      ...this.buildAppenquiryFlag()
    }
    let formData: FormData = new FormData();
    formData.append('enquiry', new Blob([JSON.stringify(enquiry)], { type: 'application/json' }))
    this.enquiryCreateCommonService.files.forEach(markImg => {
      formData.append('multipartFileList', markImg['file'])
    })
    console.log('submit enquiry--->', enquiry);

    return formData
  }
  private buildAppenquiryFlag()
  {
    let saveEnquiry = {} as SaveEnquiry
    // saveEnquiry.postOffice="karishma check";
    saveEnquiry.appEnquiryFlag=true;
    return saveEnquiry;
  }
  private buildJsonSetForGeneralInfo() {
    let generalInfo = this.presenter.enquiryForm.getRawValue().generalInfo
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry = generalInfo
    saveEnquiry.salesPerson = {
      id: generalInfo.salesPerson ? generalInfo.salesPerson.id : null
    }
    saveEnquiry.followUpType = generalInfo.followUpType ? generalInfo.followUpType.followUpType : null
    saveEnquiry.source = generalInfo.source ? generalInfo.source.source : null
    saveEnquiry.purposeOfPurchase = generalInfo.purposeOfPurchase ? generalInfo.purposeOfPurchase.purposeOfPurchase : null
    saveEnquiry.generationActivityType = generalInfo.generationActivityType ? generalInfo.generationActivityType.generationActivityType : null
    if (generalInfo.generationActivityNumber) {
      saveEnquiry.generationActivityNumber = generalInfo.generationActivityNumber ? {
        activityProposalId: generalInfo.generationActivityNumber ? generalInfo.generationActivityNumber.activityProposalId : null
      } : null
    }
    saveEnquiry.enquiryDate = this.dateService.getDateIntoDDMMYYYY(generalInfo.enquiryDate)
    saveEnquiry.validationDate = this.dateService.getDateIntoDDMMYYYY(generalInfo.validationDate)
    saveEnquiry.tentativePurchaseDate = this.dateService.getDateIntoDDMMYYYY(generalInfo.tentativePurchaseDate)
    saveEnquiry.nextFollowUpDate = this.dateService.getDateIntoDDMMYYYY(generalInfo.nextFollowUpDate)
    if (this.presenter.operation === Operation.VIEW) {
      saveEnquiry.conversionActivityType = generalInfo.conversionActivityType ? generalInfo.conversionActivityType.conversionActivityType : null
      if (generalInfo.conversionActivityNumber) {
        saveEnquiry.conversionActivityNumber = generalInfo.conversionActivityNumber ? {
          activityProposalId: generalInfo.conversionActivityNumber ? generalInfo.conversionActivityNumber.activityProposalId : null
        } : null
      } /*else {
        saveEnquiry.conversionActivityNumber = generalInfo.conversionActivityNumber ? {
          activityProposalId: generalInfo.conversionActivityNumber ? generalInfo.conversionActivityNumber.activityProposalId : null
        } : null
      }*/
      saveEnquiry.retailConversionActivity = generalInfo.retailConversionActivity ? generalInfo.retailConversionActivity.retailConversionActivity : null

    }
    
    saveEnquiry.multipartFileList = this.enquiryCreateCommonService.files;
    if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.VIEW_MOBILE) {
      if (this.enquiryByEnquiryNumber.generationActivityNumber) {
        saveEnquiry.generationActivityNumber = {
          activityProposalId: this.enquiryByEnquiryNumber.generationActivityNumber.activityProposalId
        }
      }
    }
;
    return saveEnquiry
  }

  private buildCreateById() {
    let saveEnquiry = {} as SaveEnquiry
    let loginUserId = this.loginFormService.getLoginUserId()
    /*saveEnquiry.createdBy = {
      id: loginUserId
    }*/
    if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.VIEW_MOBILE) {
      saveEnquiry.id = this.id
    }

    return saveEnquiry
  }

  private buildJsonSetForProductIntrested() {
    let productIntrested = this.presenter.enquiryForm.getRawValue().productIntrested
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry = productIntrested
    saveEnquiry.itemNo = productIntrested.itemNo ? productIntrested.itemNo.itemNo : null
    saveEnquiry.model = productIntrested.model ? productIntrested.model.model : null
    saveEnquiry.subModel = productIntrested.subModel ? productIntrested.subModel.subModel : null
    saveEnquiry.variant = productIntrested.variant ? productIntrested.variant.variant : null
    saveEnquiry.exchangeRequired = productIntrested.exchangeRequired ? productIntrested.exchangeRequired.exchangeRequired : null
            
    saveEnquiry.exchangeBrand = productIntrested.exchangeBrand ? productIntrested.exchangeBrand.exchangeBrand : null
    
    saveEnquiry.exchangeReceived = productIntrested.exchangeReceived?'Y':'N';
    return saveEnquiry
  }

  private buildJsonSetForProspectDetails() {
    let prospectDetails = this.presenter.enquiryForm.getRawValue().prospectDetails
    console.log(prospectDetails);
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry = prospectDetails
    saveEnquiry.prospectCode = prospectDetails ? prospectDetails.prospectCode : null
    saveEnquiry.prospectType = prospectDetails.prospectType ? prospectDetails.prospectType.prospectType : null
    saveEnquiry.pinCode = prospectDetails.pinCode ? prospectDetails.pinCode.pinCode : null
            
    saveEnquiry.district = prospectDetails.district ?(typeof prospectDetails.district === 'string'?prospectDetails.district:prospectDetails.district.district) : null
    saveEnquiry.language = prospectDetails.language ? prospectDetails.language.language : null
    if (prospectDetails.mobileNumber.mobileNumber) {
      saveEnquiry.mobileNumber = prospectDetails.mobileNumber.mobileNumber
    }
    if (prospectDetails.dob) {
      saveEnquiry.dob = this.dateService.getDateIntoDDMMYYYY(prospectDetails.dob)
    }
    if (prospectDetails.anniversaryDate) {
      saveEnquiry.anniversaryDate = this.dateService.getDateIntoDDMMYYYY(prospectDetails.anniversaryDate)
    }
    if (prospectDetails.prospectCode) {
      saveEnquiry.customerMaster = {
        id: prospectDetails.prosId,
        customerCode: prospectDetails.prospectCode,
        customerType : prospectDetails.customerType,
        recordType:prospectDetails.recordType
      }
    }
    /*if (prospectDetails.customerCode) {
      saveEnquiry.customerMaster = prospectDetails.customerCode ? {
        id: prospectDetails.prosId,
        customerCode: prospectDetails.customerCode
      } : null
    }*/
    if (this.presenter.operation === Operation.VIEW || this.presenter.operation === Operation.VIEW_MOBILE) {
      /*if (prospectDetails.prospectCode) {
        saveEnquiry.prospectMaster = {
          id: prospectDetails.prosId ? prospectDetails.prosId : this.prospectMasterId,
          prospectCode: prospectDetails.prospectCode
        }
      }*/
      if (prospectDetails.prospectCode) {
        saveEnquiry.customerMaster = {
          id: this.customerMasterId,
          customerCode: prospectDetails.prospectCode,
          customerType : prospectDetails.customerType,
          recordType:prospectDetails.recordType
        }
      }
    }

    return saveEnquiry
  }

  private buildJsonSetForProspectBackGround() {
    let prospectBackground = this.presenter.enquiryForm.getRawValue().prospectBackground
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry.occupation = prospectBackground.occupation ? prospectBackground.occupation.occupation : null
    saveEnquiry.landSize = prospectBackground ? prospectBackground.landSize : null
    saveEnquiry.enquirySoilTypes = prospectBackground ? prospectBackground.soilName : null
    saveEnquiry.enquiryCropGrows = prospectBackground ? prospectBackground.cropName : null

    return saveEnquiry
  }

  private buildJsonSetForcurrentMachineryDetails() {
    let currentMachineryDetails = this.presenter.enquiryForm.value.currentMachineryDetails
    let machineryDetails = currentMachineryDetails.machineryDetails
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry.enquiryMachineryDetails = machineryDetails
    if (this.presenter.operation === Operation.VIEW) {
      const currentMachine = [
        ...machineryDetails,
        ...this.presenter.deleteDataRow
      ]
      saveEnquiry.enquiryMachineryDetails = currentMachine ? currentMachine : null
    }
  
    return saveEnquiry
  }

  private buildJsonSetForRetailFinanceDetails() {
    let retailFinanceDetails = this.presenter.enquiryForm.getRawValue().retailFinanceDetails
    let saveEnquiry = {} as SaveEnquiry
    saveEnquiry = retailFinanceDetails
    saveEnquiry.financier =  retailFinanceDetails.financier?retailFinanceDetails.financier.financierCode:null
    saveEnquiry.cashLoan = retailFinanceDetails.cashLoan ? retailFinanceDetails.cashLoan.cashLoan : null
    saveEnquiry.financeStatus = retailFinanceDetails.financeStatus ? retailFinanceDetails.financeStatus.financeStatus : null
    saveEnquiry.subsidy = retailFinanceDetails.subsidy ? retailFinanceDetails.subsidy.subsidy : null
    if (retailFinanceDetails.financeLoggedInDate) {
      saveEnquiry.financeLoggedInDate = this.dateService.getDateIntoDDMMYYYY(retailFinanceDetails.financeLoggedInDate)
    }
    if (retailFinanceDetails.disbursedDate) {
      saveEnquiry.disbursedDate = this.dateService.getDateIntoDDMMYYYY(retailFinanceDetails.disbursedDate)
    }
    return saveEnquiry
  }

  getOperationType() {
    console.log(this.actRt.snapshot.routeConfig.path.split('/')[0]);

    return this.actRt.snapshot.routeConfig.path.split('/')[0]
  }

  onClickViewFollowupHistory() {
    this.openFollowupHistoryDialog()
  }

  private openFollowupHistoryDialog(): void | any {
    const dialogRef = this.dialog.open(FollowupHistoryDialogComponent, {
      width: '80%',
      height: '80vh',
      panelClass: 'confirmation_modal',
      data: {
        followupHistory: this.followupHistory
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
    });
  }

  exitForm(){
    this.router.navigate(['../../'], { relativeTo: this.actRt });
  }

  viewPrint(printStatus:string){
      this.enquiryV2CreateWebService.printEnquiry(this.enquiryByEnquiryNumber.enquiryNumber, printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
      })
  }
}