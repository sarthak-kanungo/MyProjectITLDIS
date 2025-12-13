import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { EnquiryCreationContainerService } from '../enquiry-creation-container/enquiry-creation-container.service';
import { EnquiryProductInstrestedContainerService } from '../enquiry-product-interested-container/enquiry-product-instrested-container.service';
import { ProspectDetailsContainerService } from '../prospect-details-container/prospect-details-container.service';
import { ProspectBackgroundContainerService } from '../prospect-background-container/prospect-background-container.service';
import { CurrentMachineDetailsContainerService } from '../current-machine-details-container/current-machine-details-container.service';
import { RetailFinanceContainerService } from '../retail-finance-container/retail-finance-container.service';
import { EnquiryService } from '../../enquiry.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { ProductIntrestedObj } from 'EnquiryProductIntrested';
import { SaveProspectDetailsObj } from 'ProspectDetails';
import { ProspectBackgroundObj, EnquirySoilType, EnquiryCropGrow } from 'ProspectBackground';
import { CurrentMachineObj } from 'CurrentMachineDetails';
import { RetailFinanceObj } from 'RetailFinance';
import { EnquiryCommonService } from '../../../enquiry-common-service/enquiry-common.service';
import { EnquiryEditService } from './enquiry-edit.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
@Component({
  selector: 'app-enquiry-edit',
  templateUrl: './enquiry-edit.component.html',
  styleUrls: ['./enquiry-edit.component.scss'],
  providers: [EnquiryCreationContainerService, EnquiryProductInstrestedContainerService, EnquiryCommonService, EnquiryEditService,
    ProspectDetailsContainerService, ProspectBackgroundContainerService, CurrentMachineDetailsContainerService, RetailFinanceContainerService]
})
export class EnquiryEditComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  loginUserId: number;
  isNewEnquiryValidStatus: boolean
  isProductIntrestedValidStatus: boolean
  isProspectDetailsValidStatus: boolean
  isProspectBackgroundValidStatus: boolean
  isCurrentMachineValidStatus: boolean
  isretailFinanceValidStatus: boolean

  constructor(
    private enquiryCreationContainerService: EnquiryCreationContainerService,
    public dialog: MatDialog,
    private enquiryProductInstrestedContainerService: EnquiryProductInstrestedContainerService,
    private prospectDetailsContainerService: ProspectDetailsContainerService,
    private prospectBackgroundContainerService: ProspectBackgroundContainerService,
    private currentMachineDetailsContainerService: CurrentMachineDetailsContainerService,
    private retailFinanceContainerService: RetailFinanceContainerService,
    private enquiryService: EnquiryService,
    private enqRt: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService,
    private enquiryEditService: EnquiryEditService,
    private loginFormService: LoginFormService
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
  }

  ngOnInit() {
  }

  validateForm(functionalKey: string) {
    this.enquiryService.submitOrResetEnquiryForm(functionalKey)
    if(this.isNewEnquiryValidStatus === true && this.isProductIntrestedValidStatus === true && this.isProspectDetailsValidStatus === true &&
      this.isProspectBackgroundValidStatus === true && this.isCurrentMachineValidStatus === true && this.isretailFinanceValidStatus === true) {
    this.openConfirmDialog();
    }else {
      this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')
    }
  }

  submitFormAndReset() {
    console.log('procpect', this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode);
    console.log('customer', this.prospectDetailsContainerService.prospectDetailsObj.customerCode);
    if (this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode !== (null)) {
      this.saveEnquiryDomain()
      console.log("submit ", this.enquiryCreationContainerService.enquiryWithProspectMasterDomain);
      this.enquiryEditService.postUpdateEnquiry(this.enquiryCreationContainerService.enquiryWithProspectMasterDomain).subscribe(fromData => {
        console.log('fromdata', fromData);
        if (fromData) {
          this.toastr.success('Enquiry Update Successfully', 'Success')
        }
        this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
      })
    }
    // else {
    //   this.submitEnquirySaveDomain()
    //   console.log("submit ", this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain);
    //   this.enquiryEditService.postUpdateEnquiry(this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain).subscribe(fromData => {
    //     console.log('fromdata', fromData);
    //     if (fromData) {
    //       this.toastr.success('Enquiry Update Successfully', 'Success')
    //     }
    //     this.router.navigate(['/sales-pre-sales/pre-sales/enquiry'])
    //   })
    // }
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to update Enquiry?';
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
        this.submitFormAndReset()
      }
      if (result === 'Confirm' && this.isEdit) {
        this.submitFormAndReset()
      }
    })
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Confirm', 'Cancel'];
    return confirmationData;
  }

  getNewEnquiryFormData(event) {
    console.log('newEnquiry', event);
    this.isNewEnquiryValidStatus = event.event.validStatus
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.id = event.event.id
    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.generationActivityNumber = event.event.formData.generationActivityNumber ? {
      activityProposalId: event.event.formData.generationActivityNumber ? event.event.formData.generationActivityNumber.id : null
    } : null

    if (event.event.proposalId) {
      this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.generationActivityNumber = {
        activityProposalId: event.event.proposalId
      }
    }

    this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.conversionActivityNumber = event.event.formData.conversionActivityNumber ? {
      activityProposalId: event.event.formData.conversionActivityNumber ? event.event.formData.conversionActivityNumber.id : null
    } : null

    if (event.event.conProposalId) {
      this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain.conversionActivityNumber = {
        activityProposalId: event.event.conProposalId
      }
    }

    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }
    this.enquiryCreationContainerService.enquiryWithProspectMasterDomain.id = event.event.id

    this.enquiryCreationContainerService.enquiryDetailsDomain = event.event.formData
    // this.enquiryCreationContainerService.enquiryDetailsDomain.salesPerson = {
    //   id: event.event.formData.salesPerson ?
    //     event.event.formData.salesPerson.id : null
    // }
    this.enquiryCreationContainerService.enquiryDetailsDomain.id = event.event.id
  }

  getProductInterestedFormData(event) {
    console.log('productInterested', event);
    this.isProductIntrestedValidStatus = event.event.validStatus
    this.enquiryProductInstrestedContainerService.productIntrestedObj = event.event.formData as ProductIntrestedObj
    console.log('productInterestedvalue', this.enquiryProductInstrestedContainerService.productIntrestedObj);
    if(event.event.formData.itemNo.itemNo){
      this.enquiryProductInstrestedContainerService.productIntrestedObj.itemNo = event.event.formData.itemNo.itemNo 
    }
   

  }
  getProspectDetailsFormData(event) {
    console.log('pros', event);
    this.isProspectDetailsValidStatus = event.event.validStatus
    this.prospectDetailsContainerService.saveProspectDetailsObj = event.event.formData as SaveProspectDetailsObj;
    this.prospectDetailsContainerService.saveProspectDetailsObj.mobileNumber = event.event.formData.mobileNumber.mobileNumber
    this.prospectDetailsContainerService.saveProspectDetailsObj.prospectCode = event.event.formData.prospectCode
    this.prospectDetailsContainerService.saveProspectDetailsObj.pinCode = event.event.formData.pinCode.pinCode
    this.prospectDetailsContainerService.saveProspectDetailsObj.postOffice = event.event.formData.postOffice.postOffice
    this.prospectDetailsContainerService.prospectDetailsObj.customerCode = event.customerCode
    this.prospectDetailsContainerService.prospectDetailsObj.oldCustomerCode = event.oldCustomerCode
    this.prospectDetailsContainerService.prospectDetailsObj.id = event.event.id
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
    const currentMachine = [
      ...event.event.formData,
      ...event.event.deleteRow
    ]
    this.currentMachineDetailsContainerService.currentMachines = currentMachine
    console.log('currentMachine', this.currentMachineDetailsContainerService.currentMachines);
  }
  getRetailFinanceData(event) {
    this.isretailFinanceValidStatus = event.event.validStatus
    this.retailFinanceContainerService.retailFinanceObj = event.event.formData as RetailFinanceObj
    console.log('retailfince', this.retailFinanceContainerService.retailFinanceObj);
  }

  private saveEnquiryDomain() {
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
  }

  private submitEnquirySaveDomain() {
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
    console.log('enquiryWithCustomerMasterDomain', this.enquiryCreationContainerService.enquiryWithCustomerMasterDomain);
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
    this.enquiryCreationContainerService.enquiryDetailsDomain.prospectCode = ""
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquiryMachineryDetails = this.currentMachineDetailsContainerService.currentMachines
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquiryCropGrows = this.prospectBackgroundContainerService.enquiryCropGrow
    this.enquiryCreationContainerService.enquiryDetailsDomain.enquirySoilTypes = this.prospectBackgroundContainerService.enquirySoilType
    this.enquiryCreationContainerService.enquiryDetailsDomain.occupation = this.prospectBackgroundContainerService.prospectBackgroundObj.occupation
    this.enquiryCreationContainerService.enquiryDetailsDomain.landSize = this.prospectBackgroundContainerService.prospectBackgroundObj.landSize
    console.log('enquiryDetailsDomain', this.enquiryCreationContainerService.enquiryDetailsDomain);
  }
}
