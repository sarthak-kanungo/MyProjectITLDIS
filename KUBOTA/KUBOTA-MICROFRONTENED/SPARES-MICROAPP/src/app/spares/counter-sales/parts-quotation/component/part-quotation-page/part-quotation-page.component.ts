import { Component, OnInit, ChangeDetectionStrategy,Input } from '@angular/core';
import { FormGroup, AbstractControl } from '@angular/forms';
import { PartQuotationPagePresenter } from './part-quotation-page-presenter';
import { PartQuotationPageStore } from './part-quotation-page.store';
import { ConfirmDialogData, ButtonAction, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog } from '@angular/material';
import { OperationsUtil, Operation } from '../../../../../utils/operation-util';
import { ActivatedRoute, Router } from '@angular/router';
import { SaveAndSubmitQuotation, QuotationPartDetail } from '../../domain/part-quotation-domain';
import { PartQuotationPageWebService } from './part-quotation-page-web.service';
import { ToastrService } from 'ngx-toastr';
import {BehaviorSubject} from 'rxjs'


import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-part-quotation-page',
  templateUrl: './part-quotation-page.component.html',
  styleUrls: ['./part-quotation-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [PartQuotationPageStore, PartQuotationPagePresenter, PartQuotationPageWebService]
})
export class PartQuotationPageComponent implements OnInit {
  @Input() markForCheck: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  isView: boolean
  isEdit: boolean
  dialogMsg: string;
  isDraft: boolean;
  partQuotationForm: FormGroup
  partQuotationDetailsForm: FormGroup
  partQuotationItemDetailsForm: FormGroup
  partQuotationTotalForm: AbstractControl
  id: number
  sparePartMasterId: number
  headerData: SaveAndSubmitQuotation
  isQty : boolean
  viewItemDeatils :  QuotationPartDetail[]
  isClear: boolean
  isSubmitDisable:boolean = false;
  // State_id: number;
  // District_id: number;
  // Tehsil_id: number;
  // City_id: number;

  constructor(
    private partQuotationPagePresenter: PartQuotationPagePresenter,
    public dialog: MatDialog,
    private partQuotRt: ActivatedRoute,
    private partQuotationPageWebService: PartQuotationPageWebService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.partQuotationPagePresenter.operation = OperationsUtil.operation(this.partQuotRt)
    this.partQuotationForm = this.partQuotationPagePresenter.partQuotationForm
    this.partQuotationItemDetailsForm = this.partQuotationPagePresenter.partQuotationTableRow;
    this.partQuotationDetailsForm = this.partQuotationPagePresenter.detailsForm
    this.partQuotationTotalForm = this.partQuotationPagePresenter.partQuotationTotalForm
    this.viewOrEditOrCreate();
  }

  private viewOrEditOrCreate() {
    if (this.partQuotationPagePresenter.operation === Operation.VIEW) {
      this.parseIdAndViewOrEditForm()
      this.partQuotationDetailsForm.disable()
     // this.partQuotationPagePresenter.partQuotationTableRow.disable()
      this.isView = true
    } else if (this.partQuotationPagePresenter.operation === Operation.EDIT) {
      this.parseIdAndViewOrEditForm()
      this.partQuotationDetailsForm.get('customerType').disable();
      this.isEdit = true;
    }
  }

  private parseIdAndViewOrEditForm() {
    this.partQuotRt.params.subscribe(prms => {
      this.partQuotationPageWebService.getQuotationById(`` + atob(prms['id'])).subscribe(response => {
        console.log("getQuotationById--->", response)
        this.partQuotationPagePresenter.patchValueForViewQuotation(response)
        this.id = response.headerResponse.id
        this.headerData = response.headerResponse
        console.log("headerData--->", this.headerData)
        this.viewItemDeatils = response.partDetails
      })
    })
  }

  saveAndSubmitQuotation(isSave: boolean) {
    this.isDraft = isSave
    this.dialogMsg = isSave ? 'save' : 'submit'
    const quotationForm = this.partQuotationForm.getRawValue()
    quotationForm.itemDetails &&  (quotationForm.itemDetails as any[]).forEach(element => {
      if(element.quantity > 0){
        this.isQty = true
      }else {
        this.isQty = false
        return;
      }
    })
    if (this.partQuotationDetailsForm.status === 'VALID' && this.partQuotationItemDetailsForm.status === 'VALID') {
      if(this.partQuotationDetailsForm.get('pinCode').value === ''){
        this.toastr.error(`Pincode is required`, 'Report mandatory field')
        return false;
      }
      if( this.isQty === true){
        this.markForCheck.next(true);  
        this.openConfirmDialog();
      }else {
        this.toastr.error(`Quantity should not be 0`, 'Report Quantity field')
      }
     
    } else {
      this.partQuotationPagePresenter.markFormAsTouched()
      this.toastr.error(`Please fill mandatory field`, 'Report mandatory field')
    }
  }
  submitData() {
    console.log(this.partQuotationForm.getRawValue());
    // this.jsonForQuotation()
    this.partQuotationPageWebService.saveQuotation(this.jsonForQuotation()).subscribe(response => {
      const displayMsg = response['message']
      if (response) {
        this.toastr.success(displayMsg, 'Success')
        if(this.partQuotationPagePresenter.operation === Operation.EDIT){
          this.router.navigate(['../../'], {relativeTo : this.partQuotRt})
        }else {
          this.router.navigate(['../'], {relativeTo : this.partQuotRt})
        }
      }else{
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
      }
    }, error => {
        this.isSubmitDisable = false;
        this.toastr.error('Error generated while saving', 'Transaction failed');
    })
  }
  private jsonForQuotation() {
    const quotation = {
      ... this.buildJsonForQuotation(),
      ...this.buildJsonForQuotationDetails(),
      ...this.buildJsonForItemDetails()
    }
    console.log('submit', quotation);
    return quotation
  }
  private buildJsonForQuotation() {
    const quotationForm = this.partQuotationForm.getRawValue()
    const saveAndSubmitQuotation = {} as SaveAndSubmitQuotation
    if (this.isDraft === true) {
      saveAndSubmitQuotation.draftFlag = true
    } else {
      saveAndSubmitQuotation.draftFlag = false
    }
    if (quotationForm.partQuotationDetailsForm.customerMasterId) {
      saveAndSubmitQuotation.customerMaster = {
        id: quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.customerMasterId : null
      }
    }
    if (quotationForm.partQuotationDetailsForm.prospectMasterId) {
      saveAndSubmitQuotation.prospectMaster = {
        id: quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.prospectMasterId : null
      }
    }
    if (quotationForm.partQuotationDetailsForm.partyMasterId) {
      saveAndSubmitQuotation.partyMaster = {
        id: quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.partyMasterId : null
      }
    }
    saveAndSubmitQuotation.customerType = quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.customerType : null
    saveAndSubmitQuotation.discountRate = quotationForm.partQuotationDetailsForm.discountRate
    if (this.partQuotationPagePresenter.operation === Operation.EDIT) {
      saveAndSubmitQuotation.id = this.id
    }
    return saveAndSubmitQuotation
  }
  private buildJsonForQuotationDetails() {
    const quotationForm = this.partQuotationForm.getRawValue()
    console.log("quotationForm--->", quotationForm)
    const saveAndSubmitQuotation = {} as SaveAndSubmitQuotation
    if (quotationForm.partQuotationDetailsForm.customerName != null) {
       saveAndSubmitQuotation.customerName = quotationForm.partQuotationDetailsForm.customerName ? quotationForm.partQuotationDetailsForm.customerName : null
    }
    if (quotationForm.partQuotationDetailsForm.retailerName != null) {
       saveAndSubmitQuotation.customerName = quotationForm.partQuotationDetailsForm.retailerName ? quotationForm.partQuotationDetailsForm.retailerName : null
    }
    if (quotationForm.partQuotationDetailsForm.mechanicName != null) {
       saveAndSubmitQuotation.customerName = quotationForm.partQuotationDetailsForm.mechanicName ? quotationForm.partQuotationDetailsForm.mechanicName : null
    }
    saveAndSubmitQuotation.customerAddress1 = quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.address1 : null
    saveAndSubmitQuotation.customerAddress2 = quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.address2 : null
    saveAndSubmitQuotation.country = quotationForm.partQuotationDetailsForm ? quotationForm.partQuotationDetailsForm.country : null
    saveAndSubmitQuotation.state = quotationForm.partQuotationDetailsForm.state ? quotationForm.partQuotationDetailsForm.state.state : null
    saveAndSubmitQuotation.district = quotationForm.partQuotationDetailsForm.district ? quotationForm.partQuotationDetailsForm.district.district : null
    //saveAndSubmitQuotation.contactNumber = quotationForm.partQuotationDetailsForm.mobileNumber ? (quotationForm.partQuotationDetailsForm.mobileNumber.mobileNumber? quotationForm.partQuotationDetailsForm.mobileNumber.mobileNumber : quotationForm.partQuotationDetailsForm.mobileNumber) : quotationForm.partQuotationDetailsForm.mobileNumber.mobileNumber
    saveAndSubmitQuotation.contactNumber = quotationForm.partQuotationDetailsForm.mobileNumber ? (quotationForm.partQuotationDetailsForm.mobileNumber.mobileNumber? quotationForm.partQuotationDetailsForm.mobileNumber.mobileNumber : quotationForm.partQuotationDetailsForm.mobileNumber) : null
    saveAndSubmitQuotation.tehsil = quotationForm.partQuotationDetailsForm.tehsil ? quotationForm.partQuotationDetailsForm.tehsil.tehsil : null
    saveAndSubmitQuotation.village = quotationForm.partQuotationDetailsForm.city ? quotationForm.partQuotationDetailsForm.city.city : null
    saveAndSubmitQuotation.pinCode = quotationForm.partQuotationDetailsForm.pinCode ? quotationForm.partQuotationDetailsForm.pinCode.pinCode : null
    saveAndSubmitQuotation.pinId = quotationForm.partQuotationDetailsForm.pinCode.id ? quotationForm.partQuotationDetailsForm.pinCode.id : (this.headerData?this.headerData.pinId:null)
    saveAndSubmitQuotation.postOffice = quotationForm.partQuotationDetailsForm.postOffice ? quotationForm.partQuotationDetailsForm.postOffice.postOffice : null
    saveAndSubmitQuotation.totalDiscountValue = quotationForm.partQuotationDetailsForm ? (quotationForm.partQuotationDetailsForm.totalDiscountValue && parseFloat(quotationForm.partQuotationDetailsForm.totalDiscountValue)): null
    saveAndSubmitQuotation.totalBasicValue = quotationForm.partQuotationDetailsForm ? (quotationForm.partQuotationDetailsForm.totalBasicValue && parseFloat(quotationForm.partQuotationDetailsForm.totalBasicValue)) : null
    saveAndSubmitQuotation.totalTaxValue = quotationForm.partQuotationDetailsForm ? (quotationForm.partQuotationDetailsForm.totalTaxValue && parseFloat(quotationForm.partQuotationDetailsForm.totalTaxValue)) : null
    saveAndSubmitQuotation.totalQuotationAmount = quotationForm.partQuotationDetailsForm ? (quotationForm.partQuotationDetailsForm.totalQuotationAmount && parseFloat(quotationForm.partQuotationDetailsForm.totalQuotationAmount)) : null
    return saveAndSubmitQuotation
  }
  private buildJsonForItemDetails() {
    const quotationForm = this.partQuotationForm.getRawValue()
    const saveAndSubmitQuotation = {} as SaveAndSubmitQuotation
    const quotationPartDetail = []
    quotationForm.itemDetails.forEach(element => {
      console.log(element);
      quotationPartDetail.push({
        amount: (element.amount && parseFloat(element.amount)),
        cgstAmount: (element.cgstAmount && parseFloat(element.cgstAmount)) || null,
        cgstPercent: element ? element.cgstPercent : null,
        discountPercent: (element.discountPercent && parseFloat(element.discountPercent)),
        discountAmount: (element.discountAmount && parseFloat(element.discountAmount)),
        hsnCode: element.hsnCode,
        igstAmount: (element.igstAmount && parseFloat(element.igstAmount)) || null,
        igstpercent: element ? element.igstPercent : null,
        itemDescription: element.itemDescription,
        itemNumber: element.itemNo ? (element.itemNo.id ? element.itemNo.itemNo : element.itemNo) : element.itemNo.itemNo,
        netAmount: (element.netAmount && parseFloat(element.netAmount)),
        quantity: (element.quantity && parseFloat(element.quantity)),
        sgstAmount: (element.sgstAmount && parseFloat(element.sgstAmount)) || null,
        sgstPercent: element ? element.sgstPercent : null,
        
        gstAmount: (quotationForm.partQuotationTotal.gstAmount && parseFloat(quotationForm.partQuotationTotal.gstAmount)),
        subTotal: (quotationForm.partQuotationTotal.subTotal && parseFloat(quotationForm.partQuotationTotal.subTotal)),
        totalInvoiceAmount: (quotationForm.partQuotationTotal.totalInvoiceAmount && parseFloat(quotationForm.partQuotationTotal.totalInvoiceAmount)),
        
        unitPrice: element.unitPrice,
        sparePartMaster: {
          id: element.sparePartMasterId ? element.sparePartMasterId : element.itemNo.id,
          itemNo: element.itemNo ? (element.itemNo.id ? element.itemNo.itemNo : element.itemNo) : element.itemNo.itemNo
        },
        id: element.id
      })
    });
    saveAndSubmitQuotation.quotationPartDetail = quotationPartDetail

    return saveAndSubmitQuotation
  }
  private openConfirmDialog(): void | any {
    let message = `Do you want to ${this.dialogMsg} Parts Quotation`;
    if (this.partQuotationPagePresenter.operation === Operation.EDIT) {
      message = 'Do you want to update Parts Quotation?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
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
    return confirmationData;
  }
  onClickClear(){
      //this.partQuotationPagePresenter.resetAll();
      //this.partQuotationPagePresenter.onClickClearBehaviorCall.next('clear');
      //this.partQuotationForm.reset();
      //this.partQuotationTotalForm.get('gstAmount').patchValue('');
      //this.partQuotationPagePresenter.partQuotationTotalForm.reset();
     // this.partQuotationDetailsForm.reset();
      /*
      this.partQuotationPagePresenter.partQuotationForm.reset();
      
      
      this.partQuotationPagePresenter.enableFieldForMechanicNRetailer();
      this.partQuotationPagePresenter.enabledFieldForCustomerName();*/
      //this.router.navigate(['../'], {relativeTo : this.partQuotRt})
  }

  exitForm(){
    if(this.partQuotationPagePresenter.operation === Operation.EDIT || this.partQuotationPagePresenter.operation === Operation.VIEW){
      this.router.navigate(['../../'], {relativeTo : this.partQuotRt})
    }else {
      this.router.navigate(['../'], {relativeTo : this.partQuotRt})
    }
  }
  
  viewPrint(printStatus:string){
      this.partQuotationPageWebService.printQuotation(this.partQuotationDetailsForm.get('quotationNumber').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
