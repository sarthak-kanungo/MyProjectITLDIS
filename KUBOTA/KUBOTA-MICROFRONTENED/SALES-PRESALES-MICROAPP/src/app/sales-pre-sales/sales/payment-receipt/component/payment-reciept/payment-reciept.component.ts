import { MatDialog } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnquiryCommonService } from '../../../../pre-sales/enquiry-common-service/enquiry-common.service';
import { PaymentReceiptDomain, ReceiptModeDomain, CheckDdBankDomain, CardsDomain, BankDomain, EnquiryDataDomain, AddPaymentReceiptDataDomain, ReceiptTypeDomain } from 'CreatePaymentReceiptModule';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { SearchPaymentRecieptService } from '../search-payment-reciept/search-payment-reciept.service';
import { PaymentReceiptService } from './payment-receipt.service';
import { BaseDto } from 'BaseDto';
import { EnquiryCodeDomain } from 'quotation-dto';
import { ToastrService } from 'ngx-toastr';
import { CustomValidators } from '../../../../../utils/custom-validators';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-payment-reciept',
  templateUrl: './payment-reciept.component.html',
  styleUrls: ['./payment-reciept.component.scss'],
  providers: [PaymentReceiptService,EnquiryCommonService]
})
export class PaymentRecieptComponent implements OnInit {
  public dropdownPaymentReceiptType: BaseDto<Array<PaymentReceiptDomain>>;
  public dropdownReceiptModeType: BaseDto<Array<ReceiptModeDomain>>;
  public dropDownCheckDdBankType: BaseDto<Array<CheckDdBankDomain>>;
  public searchEnquiryCode: BaseDto<Array<EnquiryCodeDomain>>;
  public receiptTypeDomain: BaseDto<ReceiptTypeDomain>;
  public dropdownCardType: BaseDto<Array<CardsDomain>>;
  public dropdownBankType: BaseDto<Array<BankDomain>>;
  public receiptModePanelFormControls = [];
  public selectedEnquiryNumber: string
  public paymentReceiptForm: FormGroup;
  public todaysDate = new Date();
  public showCheque: boolean;
  public showCredit: boolean;
  public showWallet: boolean;
  public showRtgs: boolean;
  public isView: boolean;
  isSubmitDisable:boolean = false;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    public dialog: MatDialog,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private paymentReceiptService: PaymentReceiptService,
    private searchPaymentRecieptService: SearchPaymentRecieptService,
    private enquiryCommonService : EnquiryCommonService
  ) { }

  ngOnInit() {
    this.checkIsView();
    this.getReceiptMode();
    this.setRecepitModeControls();
    this.createPaymentReceiptForm();
  }
  private checkIsView() {
    this.activatedRoute.paramMap.subscribe(params => {
      if (params && params['params']['receiptNumber']) {
        this.isView = true;
        this.getDataForEdit(atob(params['params']['receiptNumber']))
      }
    })
  }
  private setRecepitModeControls() {
    this.receiptModePanelFormControls = ['chequeDdNo', 'chequeDdDate',
      'chequeDdBank', 'transactionNo', 'transactionDate',
      'cardNo', 'cardType', 'cardName', 'serviceProvides'
    ]
  }
  displayEnquiryNumberFn(enquiry): string | undefined {
    return (enquiry && typeof enquiry === 'object') ? enquiry['enquiryNumber'] : enquiry;
  }

  private createPaymentReceiptForm() {
    this.paymentReceiptForm = this.fb.group({
      receiptNo: [{ value: null, disabled: true }, Validators.compose([])],
      enquiry: [null, Validators.compose([Validators.required])],
      receiptType: [null, Validators.compose([Validators.required])],
      receiptDate: [{ value: new Date(), disabled: true }, Validators.compose([])],
      receiptMode: [null, Validators.compose([Validators.required])],
      receiptAmount: [null, Validators.compose([Validators.required])],
      enquiryStatus: [{ value: null, disabled: true }, Validators.compose([])],
      enquiryDate: [{ value: null, disabled: true }, Validators.compose([])],
      customerName: [{ value: null, disabled: true }, Validators.compose([])],
      mobileNumber: [{ value: null, disabled: true }, Validators.compose([])],
      variant: [{ value: null, disabled: true }, Validators.compose([])],
      address1: [{ value: null, disabled: true }, Validators.compose([])],
      pinCode: [{ value: null, disabled: true }, Validators.compose([])],
      postOffice: [{ value: null, disabled: true }, Validators.compose([])],
      city: [{ value: null, disabled: true }, Validators.compose([])],
      tehsil: [{ value: null, disabled: true }, Validators.compose([])],
      district: [{ value: null, disabled: true }, Validators.compose([])],
      state: [{ value: null, disabled: true }, Validators.compose([])],
      customerBalance: [{ value: null, disabled: true }, Validators.compose([])],
      remarks: [null, Validators.compose([])],

      chequeDdNo: [null, Validators.compose([])],
      chequeDdDate: [null, Validators.compose([])],
      chequeDdBank: [null, Validators.compose([])],

      transactionNo: [null, Validators.compose([])],
      transactionDate: [null, Validators.compose([])],
      bank: [null, Validators.compose([])],

      cardNo: [null, Validators.compose([])],
      cardType: [null, Validators.compose([])],
      cardName: [null, Validators.compose([])],
      serviceProvides: [null, Validators.compose([])],
    });
    this.enquiryNumberChanges();
  }

  private enquiryNumberChanges() {
    this.paymentReceiptForm.controls.enquiry.valueChanges.subscribe(value => {
      this.searchByEnquiryCode(value)
    })
  }
  private getDataForEdit(recordId: string) {
    this.searchPaymentRecieptService.getPaymentReceiptByReceiptNumber(recordId).subscribe(res => {
      if (res) {
        this.paymentReceiptForm.disable();
        this.paymentReceiptForm.patchValue(res['result']);
        this.paymentReceiptForm.controls.enquiry.patchValue(res['result']['enquiry'].enquiryNumber)
        this.getPaymentReceiptType(res['result']['enquiry'].enquiryNumber);
        this.getEnquiryDetailsFromNumber(res['result']['enquiry'].enquiryNumber);
        this.paymentReceiptForm.controls.receiptDate.patchValue(new Date(res['result'].receiptDate));
        this.paymentModeSelected(res['result']['receiptMode']);
        if (res['result'] && res['result']['transactionDate']) {
          this.paymentReceiptForm.controls.transactionDate.patchValue(new Date(res['result']['transactionDate']));
        }
        if (res['result'] && res['result']['chequeDdDate']) {
          this.paymentReceiptForm.controls.chequeDdDate.patchValue(new Date(res['result']['chequeDdDate']));
        }
      }
    })
  }

  private getReceiptMode() {
    this.paymentReceiptService.dropdownReceiptModeType().subscribe(response => {
      this.dropdownReceiptModeType = response as BaseDto<Array<ReceiptModeDomain>>
      this.getCheckDdBankType();
      this.getCardType();
      this.getBankType();
    })
  }
  private getCheckDdBankType() {
    this.paymentReceiptService.dropdownCheckDdBankType().subscribe(response => {
      this.dropDownCheckDdBankType = response as BaseDto<Array<CheckDdBankDomain>>
    })
  }
  private getCardType() {
    this.paymentReceiptService.dropdownCardType().subscribe(response => {
      this.dropdownCardType = response as BaseDto<Array<CardsDomain>>
    })
  }
  private getBankType() {
    this.paymentReceiptService.dropdownBankType().subscribe(response => {
      this.dropdownBankType = response as BaseDto<Array<BankDomain>>
    })
  }
  private searchByEnquiryCode(enquiryNo) {
    this.enquiryCommonService.searchEnquiryCode(enquiryNo, "PAYMENT_RECEIPT").subscribe(response => {
      this.searchEnquiryCode = response as BaseDto<Array<EnquiryCodeDomain>>
    })
  }
  enquiryNumberSelected(event) {
    if (event && event.option) {
      this.selectedEnquiryNumber = event.option.value.enquiryNumber;
      this.getPaymentReceiptType(event.option.value.enquiryNumber);
      this.getEnquiryDetailsFromNumber(event.option.value.enquiryNumber);
    }
  }
  private getEnquiryDetailsFromNumber(enqNumber: string) {
    this.paymentReceiptService.getEnquiryDetailsFromEnqNumber(enqNumber).subscribe((res: BaseDto<EnquiryDataDomain>) => {
      console.log('Data', res);
      this.paymentReceiptForm.patchValue(res.result)
      this.paymentReceiptForm.controls.enquiryDate.patchValue(new Date(res.result.enquiryDate))
    })
  }
  private getPaymentReceiptType(enqNumber: string) {
    this.paymentReceiptService.dropdownPaymentReceiptType(enqNumber).subscribe(response => {
      this.dropdownPaymentReceiptType = response as BaseDto<Array<PaymentReceiptDomain>>
    })
  }
  selectionReceiptType(receiptObject: object) {
    console.log("value ", receiptObject);
    if (receiptObject) {
      this.paymentReceiptForm.controls.receiptAmount.patchValue(receiptObject['amount'])
    }
  }

  paymentModeSelected(value: string) {
    console.log("value ", value);
    if (value) {
      this.hidePanelsAndRemoveValidations();
      if (value.toLocaleLowerCase() === 'cash') {
        this.setPanelValidations([]);
        return
      }
      if (value.toLocaleLowerCase() === 'cheque/dd') {
        this.showCheque = true;
        this.setPanelValidations(['chequeDdNo', 'chequeDdDate', 'chequeDdBank']);
        this.paymentReceiptForm.controls.chequeDdNo.setValidators([Validators.required, CustomValidators.maxLength(6), CustomValidators.numberOnly])
        return
      }
      if (value.toLocaleLowerCase() === 'credit/debit card') {
        this.showCredit = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'cardNo',
          'cardType', 'cardName']);
        this.paymentReceiptForm.controls.cardNo.setValidators([Validators.required, CustomValidators.maxLength(16), CustomValidators.numberOnly])
        return
      }
      if (value.toLocaleLowerCase() === 'e-wallet') {
        this.showWallet = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'serviceProvides']);
        return
      }
      if (value.toLocaleLowerCase() === 'rtgs/neft/imps') {
        this.showRtgs = true;
        this.setPanelValidations(['transactionNo', 'transactionDate', 'chequeDdBank']);
        return
      }
    }
  }
  validateForm() {
    this.paymentReceiptForm.markAllAsTouched();
    if (this.paymentReceiptForm.valid) {
      this.openConfirmDialog();
    }
  }
  private submitData() {
    let paymentReceiptFinal = {} as AddPaymentReceiptDataDomain;
    paymentReceiptFinal = this.paymentReceiptForm.getRawValue() as AddPaymentReceiptDataDomain;
    console.log("paymentReceiptFinal ", paymentReceiptFinal);
    this.paymentReceiptService.savePaymentReceipt(paymentReceiptFinal).subscribe(res => {
      if (res) {
        this.paymentReceiptForm.reset();
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
        this.toastr.success('Payment receipt saved successfully !', 'Success')
      }else{
        this.toastr.error('Error generated while saving',"Transaction Failed")
        this.isSubmitDisable = false;
      }
    },error => {
       this.toastr.error('Error generated while saving',"Transaction Failed")
      this.isSubmitDisable = false;
    })
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit payment receipt?';
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
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  private hidePanelsAndRemoveValidations() {
    this.setPanelValidations([]);
    this.showCheque = false;
    this.showCredit = false;
    this.showWallet = false;
    this.showRtgs = false;
  }
  clearForm() {
    this.paymentReceiptForm.reset();
    this.showCheque = false;
    this.showCredit = false;
    this.showWallet = false;
    this.showRtgs = false;
  }
  private setPanelValidations(controlsToSet: Array<string>) {
    this.receiptModePanelFormControls.forEach(control => {
      if (controlsToSet.includes(control)) {
        this.paymentReceiptForm.controls[control].setValidators(Validators.required);
        this.paymentReceiptForm.controls[control].updateValueAndValidity();
      } else {
        this.paymentReceiptForm.controls[control].setValidators(Validators.nullValidator)
        this.paymentReceiptForm.controls[control].updateValueAndValidity();
      }
    })
  }
  
  viewPrint(printStatus:string){
      this.paymentReceiptService.printReceipt(this.paymentReceiptForm.controls.receiptNo.value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
      })
  }
}
