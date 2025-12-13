import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { InvoiceStoreService } from '../../invoice-store.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SaveInvoiceAdaptorService } from '../../model/save-invoice-adaptor.service';
import { InvoiceCreateApiService } from './invoice-create-api.service';
import { ToastrService } from 'ngx-toastr';
import { CancelInvoiceAdaptor, CancelInvoice } from '../../model/cancel-invoice.adaptor';
import { InsuranceDetail } from '../../model/insurance-detail';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.scss'],
  providers: [
    InvoiceStoreService,
    SaveInvoiceAdaptorService,
    InvoiceCreateApiService
  ]
})
export class InvoiceCreateComponent implements OnInit {

  isEdit: boolean;
  isCancelInvoice: boolean;
  isView: boolean;

  cancelRequestDetail: any;
  cancelInvoiceId: string;
  customerDetail: any;
  deliveryChallanDetail: any;
  materialDetail: any;
  insuranceCompanyDetail: InsuranceDetail;
  invoiceDetail: object;
  isSubmitDisable: boolean = false;
  isCancelDisable: boolean = false;

  constructor(
    public dialog: MatDialog,
    private invoiceStoreService: InvoiceStoreService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private invoiceCreateApiService: InvoiceCreateApiService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(param => {
      console.log('param', param)
      if (param.has('cancelInvoiceId')) {
        this.isCancelInvoice = true;
        this.cancelInvoiceId = atob(param.get('cancelInvoiceId'));
        this.getInvoiceById(atob(param.get('cancelInvoiceId')));
      }else if (param.has('viewId')) {
        this.isView = true;
        this.getInvoiceById(atob(param.get('viewId')));
      }else if (param.has('editId')) {
        this.isEdit = true;
        this.getInvoiceById(atob(param.get('editId')));
      }
    });
  }
  private getInvoiceById(invoiceId: string) {
    this.invoiceCreateApiService.getInvoiceById(invoiceId).subscribe(invoiceRes => {
      this.customerDetail = invoiceRes['result']['customerDetails'];
      this.deliveryChallanDetail = invoiceRes['result']['deliveryChallanDetails'];
      this.materialDetail = invoiceRes['result'].materialDetails;
      this.insuranceCompanyDetail = invoiceRes['result'].insuranceCompanyDetails;
      this.invoiceDetail = invoiceRes['result'].invoice;
      this.cancelRequestDetail = invoiceRes['result']['invoiceCancelRequest'];
    });
  }
  validateInvoiceForm() {
    if (this.invoiceStoreService.validateInvoice()) {
      this.openConfirmDialog();
    }
  }
  validateCancelInvoiceForm() {
    if (this.invoiceStoreService.validateCancelInvoice()) {
      this.openConfirmDialog();
    }
  }

  submitInvoice() {
    const invoiceRecord = this.invoiceStoreService.getFinalInvoiceRecord();
    this.invoiceCreateApiService.saveSalesInvoice(invoiceRecord).subscribe(res => {
      this.toastr.success('Invoice saved successfully.', 'Success');
      if (this.isEdit) {
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
      }else {
          this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }
    }, err => {
      this.toastr.error('Somthing goes wrong.', 'Error');
      this.isSubmitDisable = false;
    })
  }
  cancelInvoice() {
    const cancelInvoiceAdaptor = new CancelInvoiceAdaptor();
    const cancelInvoiceForm = this.invoiceStoreService.getInvoiceCancellationFormData();
    const finalCancelInvoice = cancelInvoiceAdaptor.saveAdapt<CancelInvoice>({
      ...cancelInvoiceForm,
      ...{ invoiceId: this.cancelInvoiceId }
    });
    console.log('finalCancelInvoice',finalCancelInvoice);
    this.invoiceCreateApiService.cancelInvoice(finalCancelInvoice).subscribe(res => {
      this.toastr.success('Invoice saved successfully.', 'Success');
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
    }, err => {
      this.toastr.error('Somthing goes wrong.', 'Error');
      this.isCancelDisable = false;
    })

  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to submit invoice?';
    if (this.isCancelInvoice) {
      message = 'Do you want to cancel this invoice?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    // //console.log ('confirmationData', confirmationData);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'Confirm' && !this.isCancelInvoice) {
        this.isSubmitDisable = true;
        this.submitInvoice();
      }
      if (result === 'Confirm' && this.isCancelInvoice) {
        this.isCancelDisable = true;
        this.cancelInvoice();
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
  clearForm() {
    if (this.isCancelInvoice) {
      this.invoiceStoreService.clearInvoiceCancellationFrom();
      return;
    }
    this.invoiceStoreService.resetAllForm();
  }
  exit() {
    if (this.isCancelInvoice || this.isView || this.isEdit) {
      this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
      return;
    }
    this.router.navigate(['../'], { relativeTo: this.activatedRoute });
  }
  
  printPdf(printStatus:string){
   this.invoiceCreateApiService.printInvoice(this.invoiceDetail['invoiceNumber'], printStatus).subscribe((resp: HttpResponse<Blob>) => {
      if (resp) {
          let headerContentDispostion = resp.headers.get('content-disposition');
          let fileName = headerContentDispostion.split("=")[1].trim();
          const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
          saveAs(file);
        }
      })
   }
}
