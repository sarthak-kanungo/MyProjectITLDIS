import { Component, OnInit } from '@angular/core';
import { EnquiryCreateService } from './enquiry-create.service';
import { SaveProspectDetailsObj, ProspectDetailsObj } from 'ProspectDetails';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { EnquiryCreationService } from '../../component/enquiry-creation-component/enquiry-creation.service';
import { ViewEnquiryDomain } from 'EnquiryCreation';
import { EnquiryCreationContainerService } from '../../component/enquiry-creation-container/enquiry-creation-container.service';
import { EnquiryProductInstrestedContainerService } from '../../component/enquiry-product-interested-container/enquiry-product-instrested-container.service';
import { ProspectDetailsContainerService } from '../../component/prospect-details-container/prospect-details-container.service';
import { ProspectBackgroundContainerService } from '../../component/prospect-background-container/prospect-background-container.service';
import { CurrentMachineDetailsContainerService } from '../../component/current-machine-details-container/current-machine-details-container.service';
import { RetailFinanceContainerService } from '../../component/retail-finance-container/retail-finance-container.service';
import { ProductIntrestedObj } from 'EnquiryProductIntrested';
import { ProspectBackgroundObj, EnquirySoilType, EnquiryCropGrow } from 'ProspectBackground';
import { CurrentMachineObj } from 'CurrentMachineDetails';
import { RetailFinanceObj } from 'RetailFinance';
import { EnquiryService } from '../../enquiry.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { BaseDto } from 'BaseDto';
import { GenerateEnquiryNumberDomain } from 'EnquiryCreate';
import { LoginFormService } from '../../../../../root-service/login-form.service';
@Component({
  selector: 'app-enquiry-create',
  templateUrl: './enquiry-create.component.html',
  styleUrls: ['./enquiry-create.component.scss'],
  providers: [EnquiryCreationContainerService, EnquiryCreateService, EnquiryProductInstrestedContainerService, EnquiryCreationService,
    ProspectDetailsContainerService, ProspectBackgroundContainerService, CurrentMachineDetailsContainerService, RetailFinanceContainerService,]
})
export class EnquiryCreateComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  enqyuiryNumber: string
  loginUserId: number;
  isNewEnquiryValidStatus: boolean
  isProductIntrestedValidStatus: boolean
  isProspectDetailsValidStatus: boolean
  isProspectBackgroundValidStatus: boolean
  isCurrentMachineValidStatus: boolean
  isretailFinanceValidStatus: boolean
  postOffice : string
  viewPatchEnquiryData: ViewEnquiryDomain
  generateEnquiryNumberDomain: BaseDto<GenerateEnquiryNumberDomain>
  dataAutoPopulateByMobileNumber : ProspectDetailsObj
  getmodelValue : string
  getMobileNumber : string
  keyDownMobileNo : string
  constructor(
    private enquiryCreationContainerService: EnquiryCreationContainerService,
    public dialog: MatDialog,
    private enquiryCreateService: EnquiryCreateService,
    private enquiryProductInstrestedContainerService: EnquiryProductInstrestedContainerService,
    private prospectDetailsContainerService: ProspectDetailsContainerService,
    private prospectBackgroundContainerService: ProspectBackgroundContainerService,
    private currentMachineDetailsContainerService: CurrentMachineDetailsContainerService,
    private retailFinanceContainerService: RetailFinanceContainerService,
    private enquiryService: EnquiryService,
    private enqRt: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private loginFormService: LoginFormService
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
  }

  ngOnInit() {
    this.checkOperationType()
    this.patchOrCreate()
  }

  private patchOrCreate() {
    if (this.isView) {
      this.isView = true
    } else {
    }
  }

  autoPopulatebyMobileNo(event){
   // console.log('data', event);
    this.dataAutoPopulateByMobileNumber = event
  }

  modelValue(event){
    this.getmodelValue = event
  }

  getMobileNo(event){
   this.getMobileNumber = event
  }

  keyDownMobileNumber(event){
    this.keyDownMobileNo = event
  }

  validateForm(functionalKey: string) {
    this.enquiryService.submitOrResetEnquiryForm(functionalKey)
    if (this.isNewEnquiryValidStatus === true && this.isProductIntrestedValidStatus === true && this.isProspectDetailsValidStatus === true &&
      this.isProspectBackgroundValidStatus === true && this.isCurrentMachineValidStatus === true && this.isretailFinanceValidStatus === true) {
      this.openConfirmDialog();
    }else {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitFormAndReset() {
    if (this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode !== null) {
      this.saveEnquiryWithProspectMasterDomain()
      console.log("submit ", this.enquiryCreationContainerService.enquiryWithProspectMasterDomain);

      this.enquiryCreateService.postCreateEnquiry(this.enquiryCreationContainerService.enquiryWithProspectMasterDomain).subscribe(fromData => {
        console.log('fromdata', fromData);
        this.generateEnquiryNumberDomain = fromData as BaseDto<GenerateEnquiryNumberDomain>
        this.enqyuiryNumber = this.generateEnquiryNumberDomain.result.enquiryNumber
        console.log(this.enqyuiryNumber);
        if (fromData) {
          this.toastr.success(`Enquiry(${this.enqyuiryNumber}) created Successfully`, 'Success')
        }
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
      })
    }
    else if (this.prospectDetailsContainerService.prospectDetailsObj.customerCode !== (null || undefined)) {
      this.submitEnquiryWithCustomerMasterDomain()
      console.log("submit ", this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain);
      this.enquiryCreateService.postCreateEnquiry(this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain).subscribe(fromData => {
        console.log('fromdata', fromData);
        this.generateEnquiryNumberDomain = fromData as BaseDto<GenerateEnquiryNumberDomain>
        this.enqyuiryNumber = this.generateEnquiryNumberDomain.result.enquiryNumber
        console.log(this.enqyuiryNumber);
        if (fromData) {
          this.toastr.success(`Enquiry(${this.enqyuiryNumber}) created Successfully`, 'Success')
        }
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
      })
    }
    else if (this.prospectDetailsContainerService.prospectDetailsObj.oldCustomerCode !== (null || undefined)) {
      this.submitEnquiryWithCustomerMasterDomain()
      console.log("submit ", this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain);
      this.enquiryCreateService.postCreateEnquiry(this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain).subscribe(fromData => {
        console.log('fromdata', fromData);
        this.generateEnquiryNumberDomain = fromData as BaseDto<GenerateEnquiryNumberDomain>
        this.enqyuiryNumber = this.generateEnquiryNumberDomain.result.enquiryNumber
        console.log(this.enqyuiryNumber);
        if (fromData) {
          this.toastr.success(`Enquiry(${this.enqyuiryNumber}) created Successfully`, 'Success')
        }
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
      })
    }
    else {
      this.submitEnquiryDetailsDomain()
      console.log("submit ", this.enquiryCreationContainerService.enquiryDetailsDomain);
      this.enquiryCreateService.postCreateEnquiry(this.enquiryCreationContainerService.enquiryDetailsDomain).subscribe(fromData => {
        console.log('fromdata', fromData);
        this.generateEnquiryNumberDomain = fromData as BaseDto<GenerateEnquiryNumberDomain>
        this.enqyuiryNumber = this.generateEnquiryNumberDomain.result.enquiryNumber
        console.log(this.enqyuiryNumber);
        if (fromData) {
          this.toastr.success(`Enquiry(${this.enqyuiryNumber}) created Successfully`, 'Success')
        }
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
      })
    }
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit Enquiry?';
    if (this.isEdit) {
      message = 'Do you want to update Enquiry?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm' && !this.isEdit) {
        //this.submitData();
        this.submitFormAndReset()
      }
      if (result === 'Confirm' && this.isEdit) {
        //this.submitData();
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
    this.isView = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'view'
  }

  getNewEnquiryFormData(event) {
    console.log('newEnquiry', event);
    this.isNewEnquiryValidStatus = event.event.validStatus
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.generationActivityNumber = event.event.formData.generationActivityNumber ? {
      activityProposalId: event.event.formData.generationActivityNumber ? event.event.formData.generationActivityNumber.id : null
    } : null


    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }

    this.enquiryCreationContainerService.enquiryDetailsDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryDetailsDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }
  }

  getProductInterestedFormData(event) {
    console.log('productInterested', event);
    this.isProductIntrestedValidStatus = event.event.validStatus

    this.enquiryProductInstrestedContainerService.productIntrestedObj = event.event.formData as ProductIntrestedObj
    console.log('productInterested', this.enquiryProductInstrestedContainerService.productIntrestedObj);
    this.enquiryProductInstrestedContainerService.productIntrestedObj.itemNo = event.event.formData.itemNo ? event.event.formData.itemNo.itemNo : null


  }
  getProspectDetailsFormData(event) {
    console.log('getProspectDetailsFormDataevent', event)
    this.isProspectDetailsValidStatus = event.event.validStatus
    console.log('this.isProspectDetailsValidStatus', this.isProspectDetailsValidStatus)
    this.prospectDetailsContainerService.saveProspectDetailsObj = event.event.formData as SaveProspectDetailsObj;
    this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode = event.event.formData ? event.event.formData.prospectCode : null
    this.prospectDetailsContainerService.saveProspectDetailsObj.pinCode = event.event.formData.pinCode ? event.event.formData.pinCode.pinCode : null
    this.prospectDetailsContainerService.saveProspectDetailsObj.postOffice = event.event.formData.postOffice ? event.event.formData.postOffice.postOffice : null
    this.prospectDetailsContainerService.prospectDetailsObj.customerCode = event.customerCode
    this.prospectDetailsContainerService.prospectDetailsObj.oldCustomerCode = event.oldCustomerCode
    this.prospectDetailsContainerService.prospectDetailsObj.id = event.id
    if (event.event.formData.mobileNumber.mobileNumber) {
      this.prospectDetailsContainerService.saveProspectDetailsObj.mobileNumber = event.event.formData.mobileNumber.mobileNumber
    }
    console.log('prospectDetails', this.prospectDetailsContainerService.saveProspectDetailsObj);
  }
  getProspectBacgroundFormData(event) {
    this.isProspectBackgroundValidStatus = event.event.validStatus
    this.prospectBackgroundContainerService.prospectBackgroundObj = event.event.formData as ProspectBackgroundObj
    this.prospectBackgroundContainerService.enquirySoilType = event.event.formData.soilName as Array<EnquirySoilType>
    this.prospectBackgroundContainerService.enquiryCropGrow = event.event.formData.cropName as Array<EnquiryCropGrow>
    this.prospectBackgroundContainerService.enquiryCropGrow.forEach(data => {
        data.deleteFlag = false
    })
    this.prospectBackgroundContainerService.enquirySoilType.forEach(data => {
        data.deleteFlag = false
    })
    console.log('prospectBacgraund', this.prospectBackgroundContainerService.prospectBackgroundObj);
  }
  getCurrentMachineFormData(event) {
    this.isCurrentMachineValidStatus = event.event.validStatus
    this.currentMachineDetailsContainerService.currentMachines = event.event.formData as Array<CurrentMachineObj>
    console.log('currentMachine', this.currentMachineDetailsContainerService.currentMachines);

  }
  getRetailFinanceData(event) {
    this.isretailFinanceValidStatus = event.event.validStatus
    this.retailFinanceContainerService.retailFinanceObj = event.event.formData as RetailFinanceObj
    console.log('retailfince', this.retailFinanceContainerService.retailFinanceObj);
  }

  
  private saveEnquiryWithProspectMasterDomain() {
    const enquiry = {
      ...this.enquiryCreationContainerService.enquiryWithProspectMasterDomain,
      ...this.enquiryProductInstrestedContainerService.productIntrestedObj,
      ...this.prospectDetailsContainerService.saveProspectDetailsObj,
      //...this.prospectBackgroundContainerService.prospectBackgroundObj,
      ...this.retailFinanceContainerService.retailFinanceObj
    }
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain = enquiry
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.enquiryMachineryDetails = this.currentMachineDetailsContainerService.currentMachines
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.createdBy = {
      id: this.loginUserId
    }
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.prospectMaster = {
      id: this.prospectDetailsContainerService.prospectDetailsObj.id,
      prospectCode: this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode
    }
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.enquiryCropGrows = this.prospectBackgroundContainerService.enquiryCropGrow
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.enquirySoilTypes = this.prospectBackgroundContainerService.enquirySoilType
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.occupation = this.prospectBackgroundContainerService.prospectBackgroundObj.occupation
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.landSize = this.prospectBackgroundContainerService.prospectBackgroundObj.landSize
    console.log('enquiryWithProspectMasterDomain', this.enquiryCreationContainerService.enquiryWithProspectMasterDomain);
    console.log('prospect Id', this.prospectDetailsContainerService.prospectDetailsObj.id)
  }

  private submitEnquiryWithCustomerMasterDomain() {
    const enquiryobj = {
      ...this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain,
      ...this.enquiryProductInstrestedContainerService.productIntrestedObj,
      ...this.prospectDetailsContainerService.saveProspectDetailsObj,
      // ...this.prospectBackgroundContainerService.prospectBackgroundObj,
      ...this.retailFinanceContainerService.retailFinanceObj
    }
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain = enquiryobj
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.createdBy = {
      id: this.loginUserId
    }
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.prospectCode = this.prospectDetailsContainerService.prospectDetailsObj.customerCode
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.customerMaster = {
      id: this.prospectDetailsContainerService.prospectDetailsObj.id,
      customerCode: this.prospectDetailsContainerService.prospectDetailsObj.customerCode
    }
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.enquiryMachineryDetails = this.currentMachineDetailsContainerService.currentMachines
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.enquiryCropGrows = this.prospectBackgroundContainerService.enquiryCropGrow
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.enquirySoilTypes = this.prospectBackgroundContainerService.enquirySoilType
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.occupation = this.prospectBackgroundContainerService.prospectBackgroundObj.occupation
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.landSize = this.prospectBackgroundContainerService.prospectBackgroundObj.landSize
    // console.log('enquiryWithCustomerMasterDomain', this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain);
  }

  private submitEnquiryDetailsDomain() {
    const enquiryobj = {
      ...this.enquiryCreationContainerService.enquiryDetailsDomain,
      ...this.enquiryProductInstrestedContainerService.productIntrestedObj,
      ...this.prospectDetailsContainerService.saveProspectDetailsObj,
      // ...this.prospectBackgroundContainerService.prospectBackgroundObj,
      ...this.retailFinanceContainerService.retailFinanceObj
    }
    this.enquiryCreationContainerService.enquiryDetailsDomain = enquiryobj
    this.enquiryCreationContainerService.enquiryDetailsDomain.createdBy = {
      id: this.loginUserId
    }
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquiryMachineryDetails = this.currentMachineDetailsContainerService.currentMachines
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquiryCropGrows = this.prospectBackgroundContainerService.enquiryCropGrow
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquirySoilTypes = this.prospectBackgroundContainerService.enquirySoilType
    this.enquiryCreationContainerService.enquiryDetailsDomain.occupation = this.prospectBackgroundContainerService.prospectBackgroundObj.occupation
    this.enquiryCreationContainerService.enquiryDetailsDomain.landSize = this.prospectBackgroundContainerService.prospectBackgroundObj.landSize
    // console.log('enquiryDetailsDomain', this.enquiryCreationContainerService.enquiryDetailsDomain);
  }

  onClickExit(){
    this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
  }
}