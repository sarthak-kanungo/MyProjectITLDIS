import { Observable } from 'rxjs';
import { FormArray, FormGroup } from '@angular/forms';
import { Component, OnInit, Input, ChangeDetectionStrategy, ChangeDetectorRef, AfterViewInit } from '@angular/core';

import { SparesSalesInvoiceWebService } from '../spares-sales-invoice/spares-sales-invoice-web.service';
import { SparesSalesInvoiceSearchWebService } from './spares-sales-invoice-search-web.service';
import { BaseDto } from 'BaseDto';
import { ActivatedRoute, ParamMap } from '@angular/router';
import {  SearchPCRAutoComplete, SparesSalesInvoice } from '../../domain/spares-sales-invoice.domain';

@Component({
  selector: 'app-spares-sales-invoice-search',
  templateUrl: './spares-sales-invoice-search.component.html',
  styleUrls: ['./spares-sales-invoice-search.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [SparesSalesInvoiceSearchWebService, SparesSalesInvoiceWebService]
})
export class SparesSalesInvoiceSearchComponent implements OnInit,AfterViewInit {
  @Input() public searchSparesInvoiceForm: FormGroup;
  uuid: any;

  @Input() set filterState(state: object) {
    if (state) {
      this.patchSearchFilter();
    }
  }
  public todaysDate: Date = new Date();
  minDate: Date;
  maxDate: Date
  public invoiceNumberList: Observable<object[]>;
  public selectedFromDate: Date;
  public modeOfTransportList: object[] = [];
  public referenceDocList: object[] = [];
  public transporterList: object[] = [];
  public salesTypeList: object[] = [];
  wcrData: SearchPCRAutoComplete;
  jobCodeData: SearchPCRAutoComplete;
  constructor(
    private activatedRoute: ActivatedRoute,
    private changeDetectorRef: ChangeDetectorRef,
    private sparesSalesInvoiceWebService: SparesSalesInvoiceWebService,
    private sparesSalesInvoiceSearchWebService: SparesSalesInvoiceSearchWebService
  ) { }

  ngOnInit() {
    this.getReferenceDocuments();
    this.getAllModesOfTransport();
    this.getAllTransporter();
    this.getSalesType();
    this.formFieldValueChanges();
    this.searchJobCardNumber();
    this.searchWcrNumber();
  }
  ngAfterViewInit(){
    
  }
  //  for job card no
  
  private patchSearchFilter() {
    this.activatedRoute.queryParamMap.subscribe((queyMap: ParamMap) => {
      if (queyMap && Object.keys(queyMap['params']).length > 0) {
        this.searchSparesInvoiceForm.patchValue(JSON.parse(queyMap.get('searchFilter')));
        this.searchSparesInvoiceForm.get('salesInvoice').patchValue((JSON.parse(queyMap.get('searchFilter')) as Object)['salesInvoice']);
        setTimeout(() => {
          this.changeDetectorRef.markForCheck();
        }, 1000);
      }
    })
  }
   
  searchJobCardNumber(){
    this.searchSparesInvoiceForm.get('jobCardNumber').valueChanges.subscribe(val => {
      if (val && typeof val =='string') {
        this.sparesSalesInvoiceSearchWebService.searchAutoCompleteJobCode(val).subscribe(res => {
          this.jobCodeData = res;
          console.log(this.jobCodeData,'code')
        });
      }
      this.searchSparesInvoiceForm.get('jobCardNumber').setErrors(null);
    });
  }

  searchWcrNumber(){
    this.searchSparesInvoiceForm.get('wcrNo').valueChanges.subscribe(val => {
      if (val && typeof val =='string') {
        this.sparesSalesInvoiceSearchWebService.autoCompleteWcrNo(val).subscribe(res => {
          this.wcrData = res;
        });
      }
      this.searchSparesInvoiceForm.get('wcrNo').setErrors(null);
    });

  }

  public displayInvoiceNumberFn(selectedOption?: object): string | undefined {
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
 

  // displayInvoiceNumberFn(salesInvoice?: SparesSalesInvoice) {
  //   return salesInvoice ? salesInvoice.salesInvoice: undefined
  // }

 fromDateChange(event) {
    // if (event && event['value']) {
    //   this.selectedFromDate = new Date(event['value']);
    if (event && event['value']) {
      this.minDate = new Date(event['value']);
      let maxDate = new Date(event['value']);
      maxDate.setMonth(maxDate.getMonth() + 1);
      if (maxDate > this.todaysDate)
        this.maxDate = this.todaysDate;
         
      else
        this.maxDate = maxDate;
      if (this.searchSparesInvoiceForm.get('toDate').value > this.maxDate)
      this.invoiceNumberValueChanges()
        this.searchSparesInvoiceForm.get('toDate').patchValue(this.maxDate);
    }
    // }
  }

  private formFieldValueChanges() {
    this.invoiceNumberValueChanges();
  }
  private invoiceNumberValueChanges() {
    this.searchSparesInvoiceForm.get('salesInvoice').valueChanges.subscribe((changedValue: string) => {
      if (typeof changedValue === 'string') {
        this.invoiceNumberList = this.sparesSalesInvoiceSearchWebService.searchByInvoiceNumber(changedValue);
        // this.searchSparesInvoiceForm.get('salesInvoice').setErrors({ selectFromList: 'Please select from list' });
        // return
      }
      this.searchSparesInvoiceForm.get('salesInvoice').setErrors(null);
    })
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
  private getReferenceDocuments() {
    this.sparesSalesInvoiceWebService.getReferenceDocumentsType().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.referenceDocList = res.result;
      }
    })
  }
  private getSalesType() {
    this.sparesSalesInvoiceWebService.getSalesTypeDropdown().subscribe((res: BaseDto<Array<object>>) => {
      if (res) {
        this.salesTypeList = res.result;
      }
    })
  }
}
