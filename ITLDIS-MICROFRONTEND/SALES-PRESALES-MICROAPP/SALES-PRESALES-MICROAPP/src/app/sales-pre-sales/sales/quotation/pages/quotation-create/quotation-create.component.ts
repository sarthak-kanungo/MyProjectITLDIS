import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { PrintStatus, QuotationSearchDomain } from 'quotation-dto';
import { CreateQuotation, QuotationImplements, CreateQuotationSubFormStatus } from './create-quotation';
import { SendValidAddImplementsFormDomain } from 'add-implements-dto';
import { QuotationCreateService } from './quotation-create.service';
import { of, Observable, BehaviorSubject, Subject } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { CreateQuotationFormService } from './create-quotation-form.service';
import { QuotationCreateStoreService } from './quotation-create-store.service';
import { ToastrService } from 'ngx-toastr';
import { StorageLoginUser } from 'LoginDto';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-quotation-create',
  templateUrl: './quotation-create.component.html',
  styleUrls: ['./quotation-create.component.scss'],
  providers: [QuotationCreateService, CreateQuotationFormService, QuotationCreateStoreService]
})
export class QuotationCreateComponent implements OnInit {
  isEdit: boolean;
  showDrop: any
  dataAutoPopulatebyEnquiryNo = {} as QuotationSearchDomain;
  isValidateAllForms: Observable<boolean>;
  finalQuotaionFormValue: CreateQuotation;
  validSubFormStatus = {} as CreateQuotationSubFormStatus;
  getFormStatus = {} as CreateQuotationSubFormStatus;
  isView: boolean;
  quotationNumber:string;
  isSubmitDisable:boolean=false;
  constructor(
    public dialog: MatDialog,
    private quotationCreateService: QuotationCreateService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private createQuotationFormService: CreateQuotationFormService,
    private toastr: ToastrService,
    private quotationCreateStoreService: QuotationCreateStoreService
  ) { }

  ngOnInit() {

    this.createQuotationFormService.createViewQuotationForm();
    this.activatedRoute.paramMap.subscribe(param => {
      console.log('param', param);
      if (param.has('viewId')) {
        this.isView = true;
        this.getViewQuotationData(atob(param.get('viewId')));
      }
    })
  }
  clearForm() {
    // const currentValue = this.currentUserValue;
    // currentValue.name = 'New User'
    // this.currentUserSubject$.next(currentValue);
    this.quotationCreateStoreService.resetForm();
  }
  private getViewQuotationData(quotationNumber: string) {
    this.quotationCreateService.getQuotationByQuotationNumber(quotationNumber).subscribe(quotationData => {
      console.log(quotationData);
      // this.dataAutoPopulatebyEnquiryNo = quotationData
      this.quotationNumber = quotationData['result'].quotationNumber;
      this.quotationCreateStoreService.createPromiseOfQuotionaRecord(quotationData['result']);
    }, err => {
      this.quotationCreateStoreService.createPromiseOfQuotionaRecord(err);
    })
  }

  autoPopulateDatabyEnquiryNo(event: QuotationSearchDomain) {
    // event.itemNo = ''
    console.log('test', !`${ event.itemNo }`, event, event.itemNo);

    if (!event || !event.itemNo && `${ event.itemNo }` !== '0') {
      this.invalidEnquiry();
    }
    this.dataAutoPopulatebyEnquiryNo = event;

    console.log('this.dataAutoPopulatebyEnquiryNo', this.dataAutoPopulatebyEnquiryNo);

  }

  validateForm() {
    this.getFormStatus = { quotation: true, prospectDetails: true, addImplements: true }

    this.openConfirmDialog();
  }
  private resetGetFormStatus(key: string) {
    setTimeout(() => {
      this.getFormStatus[key] = false;
    }, 100);
  }
  private checkStatusOfSubForm() {
    if (this.validSubFormStatus.addImplements && this.validSubFormStatus.prospectDetails && this.validSubFormStatus.quotation) {
      return true;
    }
    return false;
  }
  private resetSubFormStatus() {
    this.validSubFormStatus.addImplements = false; this.validSubFormStatus.prospectDetails = false; this.validSubFormStatus.quotation = false;
  }
  checkAllFormValid(formData, formName) {
    console.log('checkAllFormValid !!! formData, formName', formData, formName);
    console.log('this.validSubFormStatus', this.validSubFormStatus);

    if (formName === 'quotationForm') {
      if (formData.isValid) {
        // console.log('final quotationForm data', formData);
        this.insertQuotationFormFieldsIntoSaveQuotaion(formData.value);
      }
      this.validSubFormStatus.quotation = formData.isValid;
      this.resetGetFormStatus('quotation');
    }
    if (formName === 'prospectDetailsForm') {
      if (formData.isValid) {
        // console.log('final prospectDetailsForm data', formData);
      }
      this.validSubFormStatus.prospectDetails = formData.isValid;
      this.resetGetFormStatus('prospectDetails');
    }
    if (formName === 'addImplementsForm') {
      if (formData.isValid) {
        // console.log('final addImplementsForm data', formData);
        this.insertAddImplementsFormIntoSaveQuotaion(formData.value)
      }
      this.validSubFormStatus.addImplements = formData.isValid;
      this.resetGetFormStatus('addImplements');
    }
    // if (this.checkStatusOfSubForm()) {
    //   this.openConfirmDialog();
    // }
  }
  private insertQuotationFormFieldsIntoSaveQuotaion(quotationFormValues) {
    let finalQuotaionFormValue = {} as CreateQuotation;
    finalQuotaionFormValue.enquiry = {} as { id: number };
    finalQuotaionFormValue.enquiry.id = quotationFormValues.enquiryNo.id;
    if (quotationFormValues.quotationNumber) {
      finalQuotaionFormValue.quotationNumber = quotationFormValues.quotationNumber;
    }
    this.finalQuotaionFormValue = {
      ...this.finalQuotaionFormValue,
      ...finalQuotaionFormValue
    } as CreateQuotation;
  }
  private insertAddImplementsFormIntoSaveQuotaion(addImplementsFormValue: SendValidAddImplementsFormDomain<QuotationImplements>) {
    let finalQuotaionFormValue = {};
    finalQuotaionFormValue = {
      ...addImplementsFormValue.calcDisplayForm,
      ...addImplementsFormValue.chargesForm,
      ...addImplementsFormValue.implementsDisplayForm,
      createdBy: { id: 2 },
      gstAmount: parseFloat(addImplementsFormValue.implementsDisplayForm.gstamt),
      grossDiscount: parseFloat(addImplementsFormValue.calcDisplayForm.discount),
      totalAmount: parseFloat(addImplementsFormValue.calcDisplayForm.totalAmt),
      quotationImplements: (addImplementsFormValue.editableTableData as QuotationImplements[]),
      totalGstAmount: addImplementsFormValue.calcDisplayForm.gstAmt
    }
    finalQuotaionFormValue['quotationImplements'] = addImplementsFormValue.editableTableData;
    this.finalQuotaionFormValue = {
      ...this.finalQuotaionFormValue,
      ...finalQuotaionFormValue
    } as CreateQuotation;
  }
  submitForm() {
    // console.log('this.finalQuotaionFormValue', this.finalQuotaionFormValue);
    if (this.finalQuotaionFormValue) {
      this.finalQuotaionFormValue.quotationImplements = this.finalQuotaionFormValue.quotationImplements.map(impl => {
        impl.itemNo = impl.itemNo['code'];
        return impl;
      })
      this.quotationCreateService.addQuotation(this.finalQuotaionFormValue).subscribe(res => {
        // console.log('Quotation Create success', res);
        if(res){
          this.toastr.success(`Quotation Saved Successfully with quotation number ${ res['result'].quotationNumber }`, 'Success');
          this.router.navigate(['../'], { relativeTo: this.activatedRoute });
        } else {
          this.toastr.error('Error generated while saving', 'Error');
          this.isSubmitDisable = false;
        }
      }, err => {
        this.toastr.error('Error generated while saving', 'Error');
        this.isSubmitDisable = false;
      }, () => {
        // console.log('Quotation Create completed!!!!!!!!!!!');
        // this.isValidateAllForms = false
      });
    }
  }
  private invalidEnquiry(): void {
    console.log('opening openConfirmDialog!!!!!!!!!!');

    let message = 'Item number is not present in enquiry.Please add item number';
    const confirmationData = this.setConfirmationModalData(message);
    confirmationData.buttonName = ['Ok']
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      // if (result === 'Confirm') {
      //   this.router.navigate(['../'], { relativeTo: this.activatedRoute });
      // }

    });
  }

  private openConfirmDialog(): void | any {
    // console.log('opening openConfirmDialog!!!!!!!!!!');

    let message = 'Do you want to submit Quotation?';
    if (this.isEdit) {
      message = 'Do you want to update Quotation?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      // console.log('The dialog was closed', result);
      this.resetSubFormStatus();
      if (result === 'Confirm' && !this.isEdit) {
        this.isSubmitDisable = true;
        this.submitForm();
      }
      if (result === 'Confirm' && this.isEdit) {
        this.isSubmitDisable = true;
        this.submitForm();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
    // confirmationData.buttonAction.push(this.cancelButtonAction());
    return confirmationData;
  }
  exit() {
    if (this.isEdit || this.isView) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }

  gstStatuses: PrintStatus[] = [
    {printValue: 'withgst', viewValue: 'With GST'},
    {printValue: 'withoutgst', viewValue: 'Without GST'}
  ];

  viewPrint(printStatus:string,value){
    if(value === 'withgst'){
      this.showDrop='withgst'
    }
    else if (value === 'withoutgst') {
      this.showDrop='withoutgst'
    }
      this.quotationCreateService.printQuotation(this.quotationNumber, printStatus, this.showDrop).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
          })
  }
}
