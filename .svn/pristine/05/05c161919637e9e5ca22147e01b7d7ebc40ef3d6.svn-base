import { BehaviorSubject } from 'rxjs';
import { Validators } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';

import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { SparePaymentReceiptPagePresenter } from './spare-payment-receipt-page.presenter';
import { SparePaymentReceiptPageService } from './spare-payment-receipt-page.service';
import { AddPaymentReceiptData, IdMaster } from 'SparePaymentReceiptModule';
import { ToastrService } from 'ngx-toastr';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
@Component({
  selector: 'app-spare-payment-receipt-page',
  templateUrl: './spare-payment-receipt-page.component.html',
  styleUrls: ['./spare-payment-receipt-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparePaymentReceiptPageService]
})
export class SparePaymentReceiptPageComponent implements OnInit {
  public markAllAsTouchedObserv: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isReceiptModeSelected: boolean;
  private searchFilter: string;
  private recordId: number;
  public isView: boolean;
  isSubmitDisable:boolean = false;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toaster: ToastrService,
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private sparePaymentReceiptPageService: SparePaymentReceiptPageService,
    private sparePaymentReceiptPresenter: SparePaymentReceiptPagePresenter
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(paramsMap => {
      if (paramsMap && paramsMap['params']['id']) {
        this.isView = true;
        this.recordId = parseInt(atob(paramsMap['params']['id']));
        this.getPaymentReceiptById(this.recordId);
      }
    });
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        this.searchFilter = atob(queryMap.get('searchFilter'));
      }
    });
    this.checkReceiptMode();
  }
  private getPaymentReceiptById(editId: number) {
    this.sparePaymentReceiptPageService.getPaymentReceiptById(editId).subscribe(res => {
      if (res) {
        let formValues = this.formatDetailsToPatch(res.result)
        this.sparePaymentReceiptPresenter.disableMainForm();
        this.sparePaymentReceiptPresenter.patchValueToMainForm(formValues)
        this.changeDetectorRef.markForCheck();
      }
    })
  }
  private formatDetailsToPatch(responseDetails: object): object {
    responseDetails['transactionDate'] = new Date(responseDetails['transactionDate']);
    responseDetails['receiptDate'] = new Date(responseDetails['receiptDate']);
    responseDetails['chequeDdDate'] = new Date(responseDetails['chequeDdDate']);
    responseDetails['customerDealerMechanicCode'] = { value: responseDetails['customerCode'] };
    return responseDetails;
  }
  private checkReceiptMode() {
    this.sparePaymentReceiptPresenter.receiptForm.get('receiptMode').valueChanges.subscribe((value: string) => {
      this.isReceiptModeSelected = value && value.toLocaleLowerCase() !== 'cash' ? true : false;
      this.sparePaymentReceiptPresenter.paymentDetailForm.reset();
      this.sparePaymentReceiptPresenter.paymentDetailForm.setValidators(Validators.nullValidator)
    })
  }
  public validateForm() {
    this.sparePaymentReceiptPresenter.markAllAsTouched();
    this.markAllAsTouchedObserv.next(true);
    if (this.sparePaymentReceiptPresenter.isFormValid()) {
      this.openConfirmationModal();
    }
  }
  private openConfirmationModal() {
    let message = `Are you sure you want to submit?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.saveSparesPaymentReceipt();
      }
    })
  }
  private saveSparesPaymentReceipt() {
    let receiptFinalData = this.createSaveReceiptObject(this.sparePaymentReceiptPresenter.getFormValues);
    
    this.sparePaymentReceiptPageService.saveSparesPaymentReceipt(receiptFinalData).subscribe(res => {
      if (res) {
        this.clearForm();
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
        this.toaster.success('Payment receipt created successfully !', 'Success');
      }else{
        this.isSubmitDisable = false;
        this.toaster.error('Error generated while saving', 'Transaction failed');
      }
    }, error =>{
        this.isSubmitDisable = false;
        this.toaster.error('Error generated while saving', 'Transaction failed');
    })
  }
  private createSaveReceiptObject(receiptData: any) {
    let finalData: AddPaymentReceiptData = {} as AddPaymentReceiptData;
    finalData = { ...receiptData.receiptDetails, ...receiptData.payeeDetails, ...receiptData.paymentDetails } as AddPaymentReceiptData;
    delete finalData.receiptDate, receiptData.receiptNo;
    if (finalData.payeeType.toLocaleLowerCase() === 'new/existing') {
      finalData.customerMaster = {} as IdMaster;
      finalData.customerMaster = finalData.customerDealerMechanicCode;
    }
    if (finalData.payeeType.toLocaleLowerCase() === 'co-dealer') {
      finalData.coDealer = {} as IdMaster;
      finalData.coDealer = finalData.customerDealerMechanicCode;
    }
    if (finalData.payeeType.toLocaleLowerCase() === 'mechanic' || finalData.payeeType.toLocaleLowerCase() === 'retailer') {
      finalData.partyMaster = {} as IdMaster;
      finalData.partyMaster = finalData.customerDealerMechanicCode;
    }
    return finalData;
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }
  public clearForm() {
    this.sparePaymentReceiptPresenter.resetForm();
  }
  public goBack() {
    this.router.navigate([this.isView ? '../../' : '../'], { relativeTo: this.activatedRoute, queryParams: { searchFilter: this.searchFilter } })
  }
  
  viewPrint(printStatus:string){
      this.sparePaymentReceiptPageService.print(this.sparePaymentReceiptPresenter.receiptForm.get('receiptNo').value, printStatus).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }
}
