import { Component, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { InvoiceStoreService } from '../../invoice-store.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SaveInvoiceAdaptorService } from '../../model/save-invoice-adaptor.service';
import { InvoiceCreateApiService } from './invoice-create-api.service';
import { ToastrService } from 'ngx-toastr';
import { CancelInvoiceAdaptor, CancelInvoice } from '../../model/cancel-invoice.adaptor';
import { InsuranceDetail,ApprovalHierDetails } from '../../model/insurance-detail';
import { Source, DialogResult } from '../../../../../confirmation-box/confirmation-data';
import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';

@Component({
  selector: 'app-invoice-cancellation-approval-create',
  templateUrl: './invoice-cancellation-approval-create.component.html',
  styleUrls: ['./invoice-cancellation-approval-create.component.scss'],
  providers: [
              InvoiceStoreService,
              SaveInvoiceAdaptorService,
              InvoiceCreateApiService
            ]
})
export class InvoiceCancellationApprovalCreateComponent implements OnInit {
  isEdit: boolean;
  isCancelInvoice: boolean;
  customerDetail: any;
  deliveryChallanDetail: any;
  cancelRequestDetail: any;
  materialDetail: any;
  insuranceCompanyDetail: InsuranceDetail;
  isApprove:boolean
  isView: boolean;
  invoiceId:string
  invoiceDetail: object;
  isSubmitDisable:boolean = false;
  @Output()
  public approvalHierDetails : ApprovalHierDetails[]
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
      if (param.has('viewId')) {
          this.isView = true;
          this.getInvoiceById(atob(param.get('viewId')));
      }else if (param.has('approveId')) {
          this.isApprove = true;
          this.getInvoiceById(atob(param.get('approveId')));
          this.invoiceId = atob(param.get('approveId'));
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
      this.approvalHierDetails = invoiceRes['result']['approvalHier'];
      this.cancelRequestDetail = invoiceRes['result']['invoiceCancelRequest'];
    });
  }
  validateInvoiceForm(flag) {
    
      this.openConfirmDialog(flag);
    
  }

  approve(remark, flag) {
    this.invoiceCreateApiService.approveSalesInvoice(remark, this.invoiceId, flag).subscribe(res => {
      if(res){
        this.toastr.success(res['message'], 'Success');
        this.router.navigate(['../../'], { relativeTo: this.activatedRoute })
      }else{
        this.toastr.error('Error generated while saving','Transaction Failed');
        this.isSubmitDisable = false;  
      }
    }, err => {
      this.toastr.error('Error generated while saving', 'Error');
      this.isSubmitDisable = false;
    })
  }

  private openConfirmDialog(flag): void | any {
    let message = `Do you want to ${flag} invoice`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe((result:DialogResult) => {
        if (result.button === 'Confirm') {
            this.isSubmitDisable = true;
            this.approve(result.remarkText, flag);
         }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    confirmationData.remarkConfig = {
            source: Source.APPROVE
        }
    return confirmationData;
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
