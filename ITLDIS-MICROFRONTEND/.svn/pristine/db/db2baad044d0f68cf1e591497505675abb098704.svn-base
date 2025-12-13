import { BehaviorSubject } from 'rxjs';
import { MatDialog } from '@angular/material';
import {FormControl, FormGroup} from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import {Observable} from 'rxjs';
import { BaseDto } from 'BaseDto';
import { ToastrService } from 'ngx-toastr';
import { SparesSalesInvoice, InvoiceDetails,ItemDetail } from '../../domain/spares-sales-invoice.domain';
import { SparesSalesInvoiceCreatePresenter } from './spares-sales-invoice-create-page.presenter';
import { SparesSalesInvoiceCreatePageWebService } from './spares-sales-invoice-create-page-web.service';
import { IdMaster } from '../../../../spares-purchase/spares-purchase-order/domain/spares-purchase-order.domain';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

import { HttpResponse } from '@angular/common/http';
import { saveAs } from 'file-saver';
import { errorObject } from 'rxjs/internal-compatibility';
import { Location } from '@angular/common';
@Component({
  selector: 'app-spares-sales-invoice-create-page',
  templateUrl: './spares-sales-invoice-create-page.component.html',
  styleUrls: ['./spares-sales-invoice-create-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparesSalesInvoiceCreatePresenter, SparesSalesInvoiceCreatePageWebService]
})
export class SparesSalesInvoiceCreatePageComponent implements OnInit {
  public markAllAsTouchedObserv: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public sparesSalesInvoiceForm : FormGroup;
  public invoiceForm : FormGroup;
  public isSalesOrderSelected: boolean;
  private searchFilter: string;
  private recordId: number;
  public isEdit: boolean = false;
  public isView: boolean = false;
  public poItemDetailForm: Observable<Array<FormGroup>>;
  public tcsPerc:number
  public draftFlag:boolean = true;
  public referenceDocType:string = "Sale Order";
  isSubmitDisable:boolean = false;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private toaster: ToastrService,
    private activatedRoute: ActivatedRoute,
    private sparesSalesInvoiceCreatePresenter: SparesSalesInvoiceCreatePresenter,
    private sparesSalesInvoiceCreatePageWebService: SparesSalesInvoiceCreatePageWebService,
    public location : Location
  ) { 
      this.invoiceForm = this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm;
      this.sparesSalesInvoiceForm = this.invoiceForm.get('invoiceDetails') as FormGroup;
      this.poItemDetailForm = sparesSalesInvoiceCreatePresenter.getItemDetailsFormObservable;
  }

  ngOnInit() { 
    this.activatedRoute.paramMap.subscribe((paramsMap: ParamMap) => {
      if (paramsMap && Object.keys(paramsMap['params']).length > 0) {
       
        if (paramsMap && paramsMap.get('editId')) {
          this.recordId = parseInt(atob(paramsMap.get('editId')))
          this.getSparesSalesInvoiceById(this.recordId);
          this.isEdit = true;
        }else if (paramsMap && paramsMap.get('viewId')) {
          this.recordId = parseInt(atob(paramsMap.get('viewId')))
          this.getSparesSalesInvoiceById(this.recordId);
          this.isView = true;
        }
      }
    });
    
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        //this.searchFilter = queryMap.get('searchFilter');
        if (queryMap && queryMap.get('custId')) {
          let custNo = atob(queryMap.get('custId'));
          this.sparesSalesInvoiceCreatePresenter.refDocSubject.next(custNo);
        }
      }
    });
  }
  backBtn(){
    this.location.back();
  }
  public validateForm(saveOrSubmit) {
    this.sparesSalesInvoiceForm.markAllAsTouched();
    this.markAllAsTouchedObserv.next(true);
    if (this.sparesSalesInvoiceForm.valid) {
      this.openConfirmationModal(saveOrSubmit);
    } else {
      this.toaster.error('Please fill all mandatory fields')
    }
  }
  private openConfirmationModal(saveOrSubmit) {
    let tt = 'Save';
    if(saveOrSubmit === 'submit')
        tt = 'Submit';
    let message = `Are you sure you want to ${tt}?`;
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });
    dialogRef.afterClosed().subscribe((result: string) => {
      
      if (result === 'Confirm') {
        this.isSubmitDisable = true;
        this.saveInvoice(saveOrSubmit);
      }
    })
  }
  private saveInvoice(saveOrSubmit) {
    let formData: SparesSalesInvoice = {} as SparesSalesInvoice;
    formData = this.invoiceForm.getRawValue();
    
    let saveInvoiceDetails:InvoiceDetails = {} as InvoiceDetails;
    saveInvoiceDetails = {...formData.invoiceDetails, ...{itemDetails:formData.itemDetails}, ...{labourDetails:formData.labourDetails}, ...{outsideChargeDetails:formData.outsideChargeDetails} };
    
    delete saveInvoiceDetails['invoiceNumber'];
    
    if(saveOrSubmit === 'submit')
        saveInvoiceDetails.draftFlag = false;
    else
        saveInvoiceDetails.draftFlag = true;
    
    if (saveInvoiceDetails.referenceDocument.toLocaleLowerCase() === 'sale order') {
       saveInvoiceDetails.sparesSalesOrderId = saveInvoiceDetails.saleOrderOrJobCard.id;
    }
    if (saveInvoiceDetails.referenceDocument.toLocaleLowerCase() === 'job card') {
        saveInvoiceDetails.serviceJobcardId = saveInvoiceDetails.saleOrderOrJobCard.id;
    }
    if (saveInvoiceDetails.referenceDocument.toLocaleLowerCase() === 'wcr') {
        saveInvoiceDetails.warrantyWcrId = saveInvoiceDetails.saleOrderOrJobCard.id;
    }
    
    this.sparesSalesInvoiceCreatePageWebService.saveInvoice(saveInvoiceDetails).subscribe((res: BaseDto<InvoiceDetails>) => {
      if (res) {
        if (this.isEdit) {
          this.toaster.success(`Spares invoice ${saveOrSubmit} successfully`, 'Success');
          this.router.navigate(['../../'], { relativeTo: this.activatedRoute });
          return;
        }  
        this.toaster.success(`Spares invoice ${saveOrSubmit} successfully`, 'Success');
        this.router.navigate(['../'], { relativeTo: this.activatedRoute })
      }else{
        this.isSubmitDisable = false;
        this.toaster.error('Error generated while saving', 'Transaction failed');
      }
    }, error => {
      this.isSubmitDisable = false;
      this.toaster.error('Error generated while saving', 'Transaction failed');
    })
  }
  private getSparesSalesInvoiceById(invoiceId: number) {
    this.sparesSalesInvoiceCreatePageWebService.getSparesSalesInvoiceById(invoiceId).subscribe(res => {
      
      if (res) {
        if(this.isView)  
            this.sparesSalesInvoiceForm.disable();
        else{
            this.sparesSalesInvoiceForm.get('referenceDocument').disable();
            this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').disable();
        }
        
        const patchData = res.result['invoiceDetails'];
        patchData.salesInvoiceDate = new Date(patchData.invoiceDate)
        this.tcsPerc = patchData.tcsPercent;
        
        this.draftFlag = patchData.draftFlag;
        this.sparesSalesInvoiceCreatePresenter.tcsPercChange.next(this.tcsPerc);
        if (patchData['saleOrderDate']) {  
          const str = patchData['saleOrderDate'].split('-');
          const year = Number(str[2]);
          const month = Number(str[1]) - 1;
          const date = Number(str[0]);  
          patchData.referenceDocumentDate = new Date(year, month, date);
          patchData.referenceDocument = 'Sale Order';
          patchData.saleOrderOrJobCard = {id:patchData['referenceId'], code:patchData['referenceDocumentNo']};
          this.isSalesOrderSelected = true;
          
          
        } else if(patchData['wcrDate']) { 
            const str = patchData['wcrDate'].split('-');
            const year = Number(str[2]);
            const month = Number(str[1]) - 1;
            const date = Number(str[0]);  
            patchData.referenceDocumentDate = new Date(year, month, date);
            patchData.referenceDocument = 'WCR';
            patchData.saleOrderOrJobCard = {id:patchData['referenceId'], code:patchData['referenceDocumentNo']};
            this.isSalesOrderSelected = false;
            
        } else{
            const str = patchData['jobCardDate'].split('-');
            const year = Number(str[2]);
            const month = Number(str[1]) - 1;
            const date = Number(str[0]);  
            patchData.referenceDocumentDate = new Date(year, month, date);
            patchData.referenceDocument = 'Job Card';
            patchData.saleOrderOrJobCard = {id:patchData['referenceId'], code:patchData['referenceDocumentNo']};
            this.isSalesOrderSelected = false;
        }
        
        this.sparesSalesInvoiceForm.patchValue(patchData);
        this.sparesSalesInvoiceForm.get('machineDetailsFormControl').patchValue(patchData);
        this.referenceDocType = patchData.referenceDocument;
        this.sparesSalesInvoiceCreatePresenter.refernceDocChange.next(patchData.referenceDocument);
        
        if (res.result['itemDetails'] && res.result['itemDetails'].length > 0) {
            res.result['itemDetails'].forEach(itemDtl => {
                this.sparesSalesInvoiceCreatePresenter.addNewRowInItemDetails(itemDtl);
            });
        }
        if (res.result['labourDetails'] && res.result['labourDetails'].length > 0) {
            res.result['labourDetails'].forEach(itemDtl => {
                this.sparesSalesInvoiceCreatePresenter.addNewRowInLabourDetails(itemDtl);
            });
        }
        if (res.result['outsideChargeDetails'] && res.result['outsideChargeDetails'].length > 0) {
            res.result['outsideChargeDetails'].forEach(itemDtl => {
                this.sparesSalesInvoiceCreatePresenter.addNewRowInOutsideChargeDetails(itemDtl);
            });
        }
      }
    }, error => this.sparesSalesInvoiceForm.disable())
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
    this.sparesSalesInvoiceForm.reset();
    this.sparesSalesInvoiceCreatePresenter.itemDetailsGroup.next(null);
    this.sparesSalesInvoiceForm.get('machineDetailsFormControl').patchValue({ resetForm: true });
  }
  public goBack() {
    this.router.navigate([this.isView || this.isEdit ? '../../' : '../'], { relativeTo: this.activatedRoute, queryParams: { searchFilter: this.searchFilter } })
  }
  viewPrint(printStatus:string, type:string){
      let docnum = this.sparesSalesInvoiceForm.get('invoiceNumber').value;
      let paramname = "invoiceNo";
      if(type==='Job Card'){
          paramname = "jobCardNo";
          //docnum = this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').value.code;
      }
      this.sparesSalesInvoiceCreatePageWebService.print(paramname,docnum, printStatus, type).subscribe((resp: HttpResponse<Blob>) => {
          if (resp) {
              let headerContentDispostion = resp.headers.get('content-disposition');
              let fileName = headerContentDispostion.split("=")[1].trim();
              const file = new File([resp.body], `${fileName}`, { type: 'application/pdf' });
              saveAs(file);
            }
       })
   }

}
