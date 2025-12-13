import { FormGroup } from '@angular/forms';
import { Observable, BehaviorSubject } from 'rxjs';
import { MatDialog, MatAutocompleteSelectedEvent } from '@angular/material';
import { Component, OnInit, Input, ChangeDetectionStrategy, ChangeDetectorRef, AfterViewInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr'
import { BaseDto } from 'BaseDto';
import { SearchAutocomplete } from 'CommonSupportDto';
import { SparesSalesInvoiceWebService } from './spares-sales-invoice-web.service';
import { SparesSalesInvoice } from '../../domain/spares-sales-invoice.domain';
import { SparesSalesInvoiceCreatePresenter } from '../spares-sales-invoice-create-page/spares-sales-invoice-create-page.presenter';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-spares-sales-invoice',
  templateUrl: './spares-sales-invoice.component.html',
  styleUrls: ['./spares-sales-invoice.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparesSalesInvoiceWebService]
})
export class SparesSalesInvoiceComponent implements OnInit, AfterViewInit {
  @Input() public markAllAsTouchedObserv: BehaviorSubject<boolean>;
  @Input() public sparesSalesInvoiceForm: FormGroup;
  @Input() public isSalesOrderSelected: boolean;
  @Input() public isView: boolean;
  @Input() public isEdit: boolean;

  public filteredSalesOrderOrJobCardList: Observable<string | object[]>;
  // public filteredJobcCardList: Observable<string | object[]>;
  public modeOfTransportList = [];
  public saleTypeList = [];
  public referenceDocList = [];
  public transporterList = [];
  public paymentTypes = ['Cash','Credit']
  public jobCardNumber = '';
  public claimType: string;
  referenceType:string
  constructor(
    public toastr : ToastrService,      
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private _changeDetectorRef: ChangeDetectorRef,
    private sparesSalesInvoiceWebService: SparesSalesInvoiceWebService,
    private sparesSalesInvoiceCreatePresenter: SparesSalesInvoiceCreatePresenter
  ) { }
  ngAfterViewInit(){
    this. fromJcForSericePlace()
  }

  ngOnInit() {
    this.getReferenceDocuments();
    this.getServerDate();
    this.getAllModesOfTransport();
    this.getAllTransporter();
    this.getSalesType();

    this.checkRouteForPreInvoiceFromJobCard();
    
    this.formValueChanges();
    this.markAllAsTouchedObserv.subscribe((value: boolean) => {
      if (value) {
        this._changeDetectorRef.markForCheck();
      }
    });
    if(this.sparesSalesInvoiceForm.get('paymentType').value){

    }else{
      this.sparesSalesInvoiceForm.get('paymentType').patchValue("Cash");
    }

    this.sparesSalesInvoiceCreatePresenter.refDocSubject.subscribe(res=>{
      if(res!=null){
        this.referenceDocList =[{'value':'Sale Order','id':1}]
        this.referenceDocumentChanges({'value':'Sale Order'});
        let CustNo = res;
        this.sparesSalesInvoiceWebService.getSalesOrderAndJobCardAutoData(res, 'Sale Order').subscribe(res => {
          this.sparesSalesInvoiceForm.controls.saleOrderOrJobCard.patchValue(res[0]);
          this.getSaleOrderDataBySaleOrderId(res[0]['id']);
        });
      }
    })
  }
  paymentSelection(event){
    this.sparesSalesInvoiceForm.get('paymentType').patchValue(event.value);
  }
  private checkRouteForPreInvoiceFromJobCard() {
    this.activatedRoute.queryParamMap.subscribe((queryMap: ParamMap) => {
      if (queryMap && Object.keys(queryMap['params']).length > 0) {
        if (queryMap && queryMap.get('jobCardNumber') && queryMap.get('id')) {
          this.jobCardNumber =  queryMap.get('jobCardNumber');
          this.isSalesOrderSelected = false;
          this.sparesSalesInvoiceForm.get('referenceDocument').patchValue('Job Card');
          this.sparesSalesInvoiceForm.get('referenceDocument').disable();
          this.sparesSalesInvoiceForm.get('salesType').patchValue('Service');
          this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').patchValue({ id: queryMap.get('id'), code: queryMap.get('jobCardNumber') });
          this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').disable();
          this.getJobCardDetailsByJobCardId(parseInt(queryMap.get('id')))
        }else  if (queryMap && queryMap.get('wcrNo') && queryMap.get('id')) {
          this.claimType = queryMap.get('claimType');
          this.isSalesOrderSelected = false;
          
          this.sparesSalesInvoiceForm.get('referenceDocument').patchValue('WCR');
          this.sparesSalesInvoiceCreatePresenter.refernceDocChange.next('WCR');
          if(this.claimType === 'Dealer Paid')
              this.sparesSalesInvoiceForm.get('salesType').patchValue('Dealer Paid');
          if(this.claimType === 'KAI')
              this.sparesSalesInvoiceForm.get('salesType').patchValue('KAI');
          
          this.sparesSalesInvoiceForm.get('referenceDocument').disable();
          this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').patchValue({ id: queryMap.get('id'), code: queryMap.get('wcrNo') });
          this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').disable();
          this.getWcrDetailsByWcrId(parseInt(queryMap.get('id')), this.claimType)
        }
      }else{
          this.referenceDocList = this.referenceDocList.filter(doc => doc['value'] != 'WCR' );
      }
    });
  }
  private getServerDate() {
    this.sparesSalesInvoiceWebService.getServerDate().subscribe(res => {
      if (res && !this.isView) {
        this.sparesSalesInvoiceForm.get('salesInvoiceDate').patchValue(res.result)
      }
    })
  }
  public referenceDocumentChanges(event: object) {
    if (event && event['value']) {
      this.referenceType = event['value'];
      this.isSalesOrderSelected = event['value'].toLowerCase() === 'job card' ? false : true;
      this.sparesSalesInvoiceForm.reset();
      this.sparesSalesInvoiceForm.get('referenceDocument').patchValue(event['value']);
      this.getServerDate();
      this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').enable();
      
      if(event['value'].toLowerCase() === 'job card') {
          this.sparesSalesInvoiceForm.get('salesType').patchValue('Service');
      } else {
          this.sparesSalesInvoiceForm.get('salesType').patchValue('Counter');
      }
      this.sparesSalesInvoiceCreatePresenter.resetItemList();
      //this.sparesSalesInvoiceCreatePresenter.refernceDocChange.next(event['value']);
    }
  }
  private formValueChanges() {
    this.salesOrderOrJobCardValueChanges();
  }
  private salesOrderOrJobCardValueChanges() {
    this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').valueChanges.subscribe((value: string) => {
      this.getSalesOrderAndJobCardAutoData(value);
    })
  }

  private getSalesOrderAndJobCardAutoData(changedValue: string) {
    const documentType = this.sparesSalesInvoiceForm.get('referenceDocument').value;
    this.filteredSalesOrderOrJobCardList = this.sparesSalesInvoiceWebService.getSalesOrderAndJobCardAutoData(changedValue, documentType);
  }
  public salesOrderSelection(event: MatAutocompleteSelectedEvent) {
    if (event && event.option) {
      if (this.isSalesOrderSelected) {
        this.getSaleOrderDataBySaleOrderId(event.option.value.id);
      } else {
        this.getJobCardDetailsByJobCardId(event.option.value.id);
      }
    }
  }
  private getJobCardDetailsByJobCardId(jobCardId: number) {
    this.sparesSalesInvoiceWebService.getJobCardetailById(jobCardId).subscribe((res: BaseDto<SparesSalesInvoice>) => {
      if (res && res.result) {
        this.setInvoiceDataFromRefDocument(res);
        if (res.result.invoiceDetails.placeOfService === 'Dealer Workshop') {
          this.sparesSalesInvoiceForm.get('transportMode').setValidators(null)
          this.sparesSalesInvoiceForm.get('transportMode').setErrors(null)
          this.sparesSalesInvoiceForm.get('transporter').setValidators(null)
          this.sparesSalesInvoiceForm.get('transporter').setErrors(null)
          this.sparesSalesInvoiceForm.get('lrNo').setValidators(null)
          this.sparesSalesInvoiceForm.get('lrNo').setErrors(null)
        }
        /* if (res.result.outsideChargeDetails[0].placeOfService === 'Dealer Workshop') condition is added by vinay to reste field incase of Dealer Workshop */
      }else{
          this.toastr.error(res.message);
      }
    })
  }
  
  
  private getWcrDetailsByWcrId(wcrId: number, claimType:string) {
      this.referenceType = "WCR";
      this.sparesSalesInvoiceWebService.getWcrDetailsById(wcrId, claimType).subscribe((res: BaseDto<SparesSalesInvoice>) => {
        if (res && res.result) {
          this.setInvoiceDataFromRefDocument(res);
        }else{
            this.toastr.error(res.message);
        }
      })
    }
  
  
  private getSaleOrderDataBySaleOrderId(saleOrderId: number) {
    this.sparesSalesInvoiceWebService.getSaleOrderDetailById(saleOrderId).subscribe((res: BaseDto<SparesSalesInvoice>) => {
      if (res && res.result) {
        this.sparesSalesInvoiceForm.get('saleOrderOrJobCard').disable();
        this.setInvoiceDataFromRefDocument(res);
      }
    })
  }
  private setInvoiceDataFromRefDocument(res: BaseDto<SparesSalesInvoice>) {
    //res.result.forEach((ele, index) => ele['serialNumber'] = index + 1)
    res.result.invoiceDetails.referenceDocumentDate = new Date(res.result.invoiceDetails.referenceDocumentDate);
    this.sparesSalesInvoiceCreatePresenter.patchValueToCreateForm(res.result.invoiceDetails);
    this.sparesSalesInvoiceForm.get('machineDetailsFormControl').patchValue({ resetForm: true });
    this.sparesSalesInvoiceForm.get('machineDetailsFormControl').patchValue(res.result.invoiceDetails);
    let tbaseamount:number = 0;
    let tgstamount:number = 0;
    let tinvoiceamount:number = 0;
    this.sparesSalesInvoiceCreatePresenter.resetItemList();
    //debugger;
    res.result.itemDetails && res.result.itemDetails.forEach( partDetail => {
       partDetail.id=null;
       partDetail.subTotal = partDetail.netAmount;
       this.sparesSalesInvoiceCreatePresenter.addNewRowInItemDetails(partDetail); 
       tbaseamount = tbaseamount + partDetail.netAmount
       tgstamount = tgstamount + partDetail.gstAmount
       tinvoiceamount = tbaseamount + tgstamount;
   });
    res.result.labourDetails && res.result.labourDetails.forEach( labourDetail => {
        labourDetail.subTotal = labourDetail.netAmount; 
        this.sparesSalesInvoiceCreatePresenter.addNewRowInLabourDetails(labourDetail);
        tbaseamount = tbaseamount + labourDetail.netAmount
        tgstamount = tgstamount + labourDetail.gstAmount
        tinvoiceamount = tbaseamount + tgstamount;
   });
   
    res.result.outsideChargeDetails && res.result.outsideChargeDetails.forEach( outsideChargeDetail => {
        outsideChargeDetail.subTotal = outsideChargeDetail.netAmount;
       this.sparesSalesInvoiceCreatePresenter.addNewRowInOutsideChargeDetails(outsideChargeDetail);
       tbaseamount = tbaseamount + outsideChargeDetail.netAmount
       tgstamount = tgstamount + outsideChargeDetail.gstAmount
       tinvoiceamount = tbaseamount + tgstamount;
       
   });
   
   let tcsPerc:number = this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm.get('invoiceDetails').get('tcsPercent').value;
   let tcsAmount:number = 0.0;
   if(tcsPerc!=null){
       tcsAmount = tinvoiceamount * tcsPerc/100;
       tinvoiceamount = tinvoiceamount + tcsAmount;
   }
   this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm.get('invoiceDetails').get('tcsAmount').patchValue(tcsAmount.toFixed(2));
   this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm.get('invoiceDetails').get('totalBaseAmount').patchValue(tbaseamount.toFixed(2));
   this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm.get('invoiceDetails').get('totalTaxAmount').patchValue(tgstamount.toFixed(2));
   this.sparesSalesInvoiceCreatePresenter.getCreateInvoiceForm.get('invoiceDetails').get('totalInvoiceAmount').patchValue(tinvoiceamount.toFixed(2));
    
  }
  private getAllModesOfTransport() {
    this.sparesSalesInvoiceWebService.getModesOfTransport().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.modeOfTransportList = res.result;
      }
    })
  }
  private getAllTransporter() {
    this.sparesSalesInvoiceWebService.getTransporter().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.transporterList = res.result;
      }
    })
  }
  private getSalesType() {
    this.sparesSalesInvoiceWebService.getSalesTypeDropdown().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.saleTypeList = res.result;
      }
    })
  }
  private getReferenceDocuments() {
    this.sparesSalesInvoiceWebService.getReferenceDocumentsType().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.referenceDocList = res.result;
        if(!this.claimType && !this.isView){
            this.referenceDocList = this.referenceDocList.filter(doc => doc['value'] != 'WCR' );
        }
      }
    })
  }
  public displayAutocompleteValueFn(data: SearchAutocomplete) {
    return data ? data['code'] : undefined
  }

  fromJcForSericePlace() {
    this.activatedRoute.queryParams.subscribe(qparams => {
      if (qparams.jcToInvoice === 'Dealer Workshop') {
        this.sparesSalesInvoiceForm.get('transportMode').setValidators(null)
        this.sparesSalesInvoiceForm.get('transportMode').setErrors(null)
        this.sparesSalesInvoiceForm.get('transporter').setValidators(null)
        this.sparesSalesInvoiceForm.get('transporter').setErrors(null)
        this.sparesSalesInvoiceForm.get('lrNo').setValidators(null)
        this.sparesSalesInvoiceForm.get('lrNo').setErrors(null)
      }
    })
  }
}
