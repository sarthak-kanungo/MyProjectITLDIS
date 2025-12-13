import { MatDialog } from '@angular/material';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, FormArray } from '@angular/forms';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { GeneralInfoService } from './general-info.service';
import { SaveCFI, DealerEmployeeMaster, ChannelFinanceIntentDetail } from 'channel-finance-indent';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { ValidateFloat } from '../../../../../utils/custom-validators';
import { Console } from 'console';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-general-info',
  templateUrl: './general-info.component.html',
  styleUrls: ['./general-info.component.scss'],
  providers: [GeneralInfoService]
})
export class GeneralInfoComponent implements OnInit {
  public channelFinanceIndentForm: FormGroup;
  public showInvoiceButton: boolean = false;
  public availableInvoicesList = [];
  public disableInvoiceBtn = false;
  private dealerCode: string;
  private dealerId: number;
  public bankNamesList = [];
  public approvalButtonList = []
  private recordId: string;
  public isView: boolean;
  public invoiceDetailsTable: FormGroup;
  isSubmitDisable:boolean = false;
  constructor(
    private router: Router,
    private fb: FormBuilder,
    public dialog: MatDialog,
    private loginService: LoginFormService,
    private toasterService: ToastrService,
    private activatedRoute: ActivatedRoute,
    private generalInfoService: GeneralInfoService,
  ) {
    this.dealerCode = this.loginService.getLoginUserDealerCode();
  }
  
  clear(){
      this.channelFinanceIndentForm.reset();
      this.resetIndentAmount();
  }
  ngOnInit() {
    this.checkFormIsForView();
    this.createChannelFinanceIndentForm();
    this.getBanksFromDealer();
    this.createinvoiceDetailsTable();
  }
  
  private checkFormIsForView() {
    this.activatedRoute.paramMap.subscribe(params => {
      console.log("params ", params);
      if (params && params['params']['id']) {
        this.recordId = atob(params['params']['id']);
        this.isView = true;
        this.getDataForView();
      }
    })
  }
  getDataForView() {
    this.generalInfoService.getCFIById(this.recordId).subscribe(res => {
      this.channelFinanceIndentForm.disable();
      if (res) {
        this.showInvoiceButton = true;
        this.channelFinanceIndentForm.patchValue(res['result']);
        this.availableInvoicesList = res['result']['channelFinanceIntentDetail'];
        this.approvalButtonList = res['result']['approvalButtonList'];
        if(this.approvalButtonList!=null){
            this.approvalButtonList.forEach((element,index) => {
                if(element==='NA')
                    this.approvalButtonList = null;
            })
        }
      }
    })
  }
  approveRejectIndent(event){
      this.openApprovalConfirmDialog(event);
  }
  private openApprovalConfirmDialog(event): void | any {
      let message = 'Do you want to '+event+' Indent?';
      const confirmationData = this.setApprovalConfirmationModalData(message);
      // //console.log ('confirmationData', confirmationData);
      const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
        width: '500px',
        panelClass: 'confirmation_modal',
        data: confirmationData,
      });

      dialogRef.afterClosed().subscribe((result: DialogResult) => {
        console.log('The dialog was closed', result);
        if (result) {
          if (result.button === 'Confirm') {
              this.isSubmitDisable = true;
              this.generalInfoService
                  .updateIndentStatus(this.channelFinanceIndentForm.controls.indentNumber.value,event,result.remarkText)
                  .subscribe(response => {
                      this.toasterService.success(response['message'])
                      this.router.navigate(['../../../channel-finance-indent'], { relativeTo: this.activatedRoute });
              },error => {
                this.toasterService.error("Error generated while saving","Transaction Failed");
                this.isSubmitDisable = false;
              });
          }
        }
      });
    }

    private setApprovalConfirmationModalData(message: string) {
      const confirmationData = {} as ConfirmDialogData;
      confirmationData.buttonAction = [] as Array<ButtonAction>;
      confirmationData.title = 'Confirmation';
      confirmationData.message = message;
      confirmationData.buttonName = ['Confirm', 'Cancel'];
      confirmationData.remarkConfig = {
        source: Source.APPROVE
      }
      return confirmationData;
    }
    
  private createChannelFinanceIndentForm() {
    this.channelFinanceIndentForm = this.fb.group({
      dealerCode: [{ value: null, disabled: true }],
      bankName: [null, Validators.compose([Validators.required])],
      flexiLoanAccountNumber: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      indentNumber: [{ value: null, disabled: true }, Validators.compose([Validators.required])],
      indentDate: [{ value: null, disabled: true }],
      dealerCategory: [{ value: null, disabled: true }, Validators.compose([])],
      numberOfDays: [{ value: null, disabled: true }, Validators.compose([])],
      indentAmount: [{ value: null, disabled: true }, Validators.compose([Validators.required, this.exceedsAvailableLimit.bind(this),ValidateFloat])],
      limit: [{ value: null, disabled: true }, Validators.compose([])],
      utilized: [{ value: null, disabled: true }, Validators.compose([])],
      available: [{ value: null, disabled: true }, Validators.compose([])],
    });
    /*this.invoiceDetailsTable = this.fb.group({
        invoiceDetails: this.fb.array([
           this.fb.group({
                invoiceNumber: [{ value: null, disabled: false }],
                invoiceDate: [{ value: null, disabled: false }],
                invoiceAmount: [{ value: null, disabled: false }],
                ageing: [{ value: null, disabled: false }],
                status: [{ value: null, disabled: false }],
                utilisedAmount:  [{ value: null, disabled: false }], 
                channelFinanceAmount: [0.0, Validators.compose([Validators.required])]
           })
        ])
    });*/
  }

  private getBanksFromDealer() {
    this.generalInfoService.getBanksFromDealerCode(this.dealerCode).subscribe(res => {
      console.log("res ", res);
      this.bankNamesList = res['result'];
    })
  }
  getUserDetailsFromBank(event) {
    console.log("event ", event);
    this.generalInfoService.getDealerDetailsFromBank(this.dealerCode, event.value).subscribe(res => {
      console.log('bank', res);
      if (res) {
        this.channelFinanceIndentForm.controls.indentAmount.enable();
        this.channelFinanceIndentForm.patchValue(res['result']);
        this.channelFinanceIndentForm.controls.dealerCategory.patchValue(res['result']['category'])
        this.channelFinanceIndentForm.controls.available.patchValue(res['result']['availableLimit'])
        this.channelFinanceIndentForm.controls.limit.patchValue(res['result']['cfCreditLimit'])
        this.channelFinanceIndentForm.controls.utilized.patchValue(res['result']['utilisedLimit'])
      }
    })
  }
  getAvailableInvoices() {
    this.generalInfoService.getAvailableInvoices(this.dealerCode, this.channelFinanceIndentForm.getRawValue()).subscribe(res => {
      console.log("res ", res);
      if (res) {
        this.availableInvoicesList = res['result'];
        this.getChannelFinanceTable(this.availableInvoicesList);
        this.disableInvoiceBtn = true;
        this.channelFinanceIndentForm.controls.indentAmount.disable();
      }
    })
  }
  public resetIndentAmount() {
    this.disableInvoiceBtn = false;
    this.showInvoiceButton = false;
    let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
    invoiceDetails.clear();
    this.channelFinanceIndentForm.controls.indentAmount.enable();
  }
  private exceedsAvailableLimit(control: AbstractControl) {
    if (control && control.value) {
      let availableLimit = this.channelFinanceIndentForm.getRawValue().available;
      if (parseFloat(control.value) > parseFloat(availableLimit)) {
        this.showInvoiceButton = false;
        return { 'moreThan': true }
      }
      this.showInvoiceButton = true;
      return null
      // return parseFloat(control.value) > parseFloat(availableLimit) ? { 'moreThan': true } : null;
    }
  }
  validateForm() {
    console.log(this.channelFinanceIndentForm);
    this.markFormAsTouched();
    
    if (this.channelFinanceIndentForm.valid && this.invoiceTableValidation()) {
      this.openConfirmDialog();
    }else{
      let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
      if(invoiceDetails===undefined || invoiceDetails.length==0){
          this.toasterService.error("No Invoice found");
      }else{
          this.toasterService.error("Channel Finance Amount can not exceed Indent Amount.");
      }
    }
  }
  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit channel finance indent?';
    
    let indentAmt = this.channelFinanceIndentForm.controls.indentAmount.value;
    let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray;
    let amount:number = 0;
    invoiceDetails.controls.forEach(column =>{
        amount = amount + parseFloat(column.value.channelFinanceAmount); 
    });
    
    if(indentAmt>amount){
        message = 'Total Finance Amount is less than indent Amount. Do you want to submit channel finance indent?';
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
        this.channelFinanceIndentForm.controls.indentAmount.patchValue(amount);  
        this.isSubmitDisable = true;
        this.submitData();
      }
    });
  }

  private invoiceDataArray(){
    let invoiceData: Array<ChannelFinanceIntentDetail>=[];
    let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
    invoiceDetails.controls.forEach(element => { 
      let d:ChannelFinanceIntentDetail={} as ChannelFinanceIntentDetail;
      d.ageing=element['controls']['ageing'].value;
      d.channelFinanceAmount=element['controls']['channelFinanceAmount'].value;
      d.invoiceAmount=element['controls']['invoiceAmount'].value;
      d.invoiceDate=element['controls']['invoiceDate'].value;
      d.invoiceNumber=element['controls']['invoiceNumber'].value;
      d.status=element['controls']['status'].value;
      d.utilisedAmount=element['controls']['utilisedAmount'].value;
      invoiceData.push(d);
    });
    return invoiceData;
  }

  private submitData() {
    let finalData: SaveCFI = {} as SaveCFI;
    finalData = this.channelFinanceIndentForm.getRawValue() as SaveCFI;
    finalData.dealerEmployeeMaster = {} as DealerEmployeeMaster;
    finalData.dealerEmployeeMaster.id = this.dealerId;
    let invoiceData = this.invoiceDataArray();
    console.log("invoice data ---- ", invoiceData);
    finalData.channelFinanceIntentDetail = invoiceData;
    console.log("final Data----", finalData);
    this.generalInfoService.saveCFIData(finalData).subscribe(res => {
      if (res) {
        this.channelFinanceIndentForm.reset();
        this.toasterService.success('Channel finance saved successfully !', 'Success')
        this.router.navigate(['../../channel-finance-indent'], { relativeTo: this.activatedRoute })
      }else{
        this.isSubmitDisable = false;
      }
    },error => {
      this.toasterService.error("Error generated while saving","Transaction Failed")
      this.isSubmitDisable = false;
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

  private markFormAsTouched() {
    this.channelFinanceIndentForm.markAllAsTouched();
  }

  private getChannelFinanceTable(availableInvoicesList: any[]){
    let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
    invoiceDetails.clear();
    availableInvoicesList.forEach(row => {
      invoiceDetails.push(
      this.fb.group({
        invoiceNumber: [{ value: row.invoiceNumber, disabled: true }],
        invoiceDate: [{ value: row.invoiceDate, disabled: true }],
        invoiceAmount: [{ value: row.invoiceAmount, disabled: true }],
        ageing: [{ value: row.ageing, disabled: true }],
        status: [{ value: row.status, disabled: true }],
        utilisedAmount: [{ value: row.totalUtilisedAmount, disabled: true }],
        channelFinanceAmount: [{ value: row.channelFinanceAmount, disabled: false }]
      }))     
    });
  }

  private createinvoiceDetailsTable() {
    this.invoiceDetailsTable = this.fb.group({
      invoiceDetails: this.fb.array([])
    });
  }
  validateFinanceAmount(event){
      let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
      invoiceDetails.controls.forEach(column =>{
         let financeAmount=(parseFloat(column['controls']['invoiceAmount'].value)-parseFloat(column['controls']['utilisedAmount'].value));
         if(parseFloat(column['controls']['channelFinanceAmount'].value) > financeAmount){
            this.toasterService.error("Channel Finance Amount should be less than equal to Invoice Amount - Utilised Amount");
            column['controls']['channelFinanceAmount'].patchValue(financeAmount);
            return false;
         } 
      });
  }
  invoiceTableValidation(){
    let amount = 0;
    let validIndentAmt = false;
    let invoiceDetails = this.invoiceDetailsTable.controls.invoiceDetails as FormArray
    if(invoiceDetails){
        invoiceDetails.controls.forEach(column =>{
          amount = amount + parseFloat(column.value.channelFinanceAmount); 
        });
        let indentAmt = this.channelFinanceIndentForm.controls.indentAmount.value;
        if (amount>0 && amount <= indentAmt) {
          validIndentAmt = true;
        }
    }
    return validIndentAmt;
  }
  
  viewPrint(printStatus:string){
      this.generalInfoService.printIndent(this.channelFinanceIndentForm.controls.indentNumber.value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
