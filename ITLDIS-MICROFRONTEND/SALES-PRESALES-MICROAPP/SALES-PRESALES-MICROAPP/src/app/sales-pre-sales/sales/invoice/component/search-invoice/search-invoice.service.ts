import { Injectable } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router, ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { SelectList, SelectListAdapter } from '../../../../../core/model/select-list.model';
import { SearchInvoiceApiService } from './search-invoice-api.service';
import { SearchAllotmentDeAllotmentService } from '../../../de-allotment/component/search-allotment-de-allotment/search-allotment-de-allotment.service';

@Injectable()
export class SearchInvoiceService {

  private searchInvoiceForm: FormGroup;
  private invoiceNumberList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getInvoiceNumberList$(): Observable<SelectList[]> {
    return this.invoiceNumberList$.asObservable();
  }
  private enquiryNumberList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getEnquiryNumberList$(): Observable<SelectList[]> {
    return this.enquiryNumberList$.asObservable();
  }
  private chassisNumberList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getChassisNumberList$(): Observable<SelectList[]> {
    return this.chassisNumberList$.asObservable();
  }
  private customerNameList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getCustomerNameList$(): Observable<SelectList[]> {
    return this.customerNameList$.asObservable();
  }
  private mobileNumberList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getMobileNumberList$(): Observable<SelectList[]> {
    return this.mobileNumberList$.asObservable();
  }
  private productTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getProductTypeList$(): Observable<SelectList[]> {
    this.getAllProduct();
    return this.productTypeList$.asObservable();
  }
  private seriesList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getSeriesList$(): Observable<SelectList[]> {
    return this.seriesList$.asObservable();
  }
  private subModelList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getSubModelList$(): Observable<SelectList[]> {
    return this.subModelList$.asObservable();
  }
  private variantList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getVariantList$(): Observable<SelectList[]> {
    return this.variantList$.asObservable();
  }
  private modelList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getModelList$(): Observable<SelectList[]> {
    return this.modelList$.asObservable();
  }
  private itemNoList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getItemNoList$(): Observable<SelectList[]> {
    return this.itemNoList$.asObservable();
  }
  private enquiryTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getEnquiryTypeList$(): Observable<SelectList[]> {
    this.getEnquiryType();
    return this.enquiryTypeList$.asObservable();
  }
  private invoiceStatusList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getInvoiceStatusList$(): Observable<SelectList[]> {
    this.getInvoiceStatus();
    return this.invoiceStatusList$.asObservable();
  }
  private invoiceTypeList$ = new BehaviorSubject<SelectList[]>(undefined);
  public get getInvoiceTypeList$(): Observable<SelectList[]> {
    this.getInvoiceType();
    return this.invoiceTypeList$.asObservable();
  }

  constructor(
    private fb: FormBuilder,
    private selectListAdapter: SelectListAdapter,
    private searchAllotmentDeAllotmentApi: SearchAllotmentDeAllotmentService,
    private searchInvoiceApiService: SearchInvoiceApiService
  ) { }

  createSearchInvoice() {
    this.searchInvoiceForm = this.fb.group({
      invoiceNumber: ['', Validators.compose([])],
      chassisNo: ['', Validators.compose([])],
      customerName: ['', Validators.compose([])],
      mobileNo: ['', Validators.compose([])],
      fromDate: ['', Validators.compose([])],
      toDate: ['', Validators.compose([])],
      enquiryNumber: ['', Validators.compose([])],
      enquiryType: ['', Validators.compose([])],
      invoiceStatus: ['', Validators.compose([])],
      invoiceType: ['', Validators.compose([])],
      product: ['', Validators.compose([])],
      series: ['', Validators.compose([])],
      model: ['', Validators.compose([])],
      subModel: ['', Validators.compose([])],
      variant: ['', Validators.compose([])],
      itemNo: ['', Validators.compose([])],
      engineNo: ['', Validators.compose([])],
    });
    this.invoiceNumberValueChanges();
    this.chassisNumberValueChanges();
    this.customerNameValueChanges();
    this.mobileNoValueChanges();
    this.productValueChanges();
    this.seriesValueChanges();
    this.modelValueChanges();
    this.subModelValueChanges();
    this.itemNoValueChanges();
    this.enquiryNumberValueChanges();
    return this.searchInvoiceForm;
  }
  private itemNoValueChanges() {
    this.searchInvoiceForm.get('itemNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchItemNo(val);
        return;
      }
      this.invoiceNumberList$.next(undefined);
    });
  }
  private invoiceNumberValueChanges() {
    this.searchInvoiceForm.get('invoiceNumber').valueChanges.subscribe(val => {
      if (val) {
        this.searchInvoiceApiService.searchByInvoiceNumber(val).subscribe(res => {
          this.invoiceNumberList$.next(res.result);
        });
        return;
      }
      this.invoiceNumberList$.next(undefined);
    });
  }
  private enquiryNumberValueChanges() {
    this.searchInvoiceForm.get('enquiryNumber').valueChanges.subscribe(val => {
      if (val) {
        this.searchInvoiceApiService.searchByEnquiryNumber(val).subscribe(res => {
          this.enquiryNumberList$.next(res.result);
        });
        return;
      }
      this.enquiryNumberList$.next(undefined);
    });
  }
  private chassisNumberValueChanges() {
    this.searchInvoiceForm.get('chassisNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchInvoiceApiService.searchByChassisNumber(val).subscribe(res => {
          this.chassisNumberList$.next(res.result);
        });
        return;
      }
      this.chassisNumberList$.next(null);
    });
  }
  private customerNameValueChanges() {
    this.searchInvoiceForm.get('customerName').valueChanges.subscribe(val => {
      if (val) {
        this.searchInvoiceApiService.searchByCustomerName(val).subscribe(res => {
          this.customerNameList$.next(res.result);
        });
        return;
      }
      this.customerNameList$.next(null);
    });
  }
  private mobileNoValueChanges() {
    this.searchInvoiceForm.get('mobileNo').valueChanges.subscribe(val => {
      if (val) {
        this.searchInvoiceApiService.searchByMobileNumber(val).subscribe(res => {
          this.mobileNumberList$.next(res.result);
        });
        return;
      }
      this.mobileNumberList$.next(null);
    });
  }
  private productValueChanges() {
    this.searchInvoiceForm.get('product').valueChanges.subscribe(val => {
      if (val) {
        this.getAllSeries();
        return;
      }
      this.seriesList$.next(null);
    });
  }
  private seriesValueChanges() {
    this.searchInvoiceForm.get('series').valueChanges.subscribe(val => {
      if (val) {
        this.getAllModel();
        return;
      }
      this.modelList$.next(null);
    });
  }
  private modelValueChanges() {
    this.searchInvoiceForm.get('model').valueChanges.subscribe(val => {
      if (val) {
        this.getAllSubModel();
        return;
      }
      this.subModelList$.next(null);
    });
  }
  private subModelValueChanges() {
    this.searchInvoiceForm.get('subModel').valueChanges.subscribe(val => {
      if (val) {
        this.getAllVariant();
        return;
      }
      this.variantList$.next(null);
    });
  }
  private getAllProduct() {
    this.searchAllotmentDeAllotmentApi.dropdownGetAllProductType().subscribe(response => {
      this.productTypeList$.next(response.result)
    })
  }
  private getAllSeries() {
    const product = this.searchInvoiceForm.get('product').value;
    this.searchAllotmentDeAllotmentApi.getSeriesByProduct(product).subscribe(response => {
      this.seriesList$.next(response.result);
    })
  }
  private getAllModel() {
    const series = this.searchInvoiceForm.get('series').value;
    this.searchAllotmentDeAllotmentApi.getModelBySeries(series).subscribe(response => {
      this.modelList$.next(response.result);
    })
  }
  private getAllSubModel() {
    const model = this.searchInvoiceForm.get('model').value;
    this.searchAllotmentDeAllotmentApi.getSubModelByModel(model).subscribe(response => {
      this.subModelList$.next(response.result);
    })
  }
  private getAllVariant() {
    const subModel = this.searchInvoiceForm.get('subModel').value;
    this.searchAllotmentDeAllotmentApi.getAllVariant(subModel).subscribe(response => {
      this.variantList$.next(response.result);
    })
  }
  private searchItemNo(itemNo: string) {
    this.searchInvoiceApiService.searchItemNo(itemNo).subscribe(itemNoRes => {
      this.itemNoList$.next(itemNoRes.result);
    })
  }
  private getEnquiryType() {
    this.searchInvoiceApiService.getEnquiryType().subscribe(response => {
      const keyMap = { value: 'enquiryType' }
      const result = this.selectListAdapter.adapt(response.result, keyMap);
      this.enquiryTypeList$.next(result)
    })
  }
  private getInvoiceStatus() {
    this.searchInvoiceApiService.getInvoiceStatus().subscribe(response => {
      this.invoiceStatusList$.next(response.result)
    })
  }
  private getInvoiceType() {
    this.searchInvoiceApiService.getInvoiceType().subscribe(response => {
      const keyMap = { value: 'invoiceType', id: 'id' }
      const result = this.selectListAdapter.adapt(response.result, keyMap);
      this.invoiceTypeList$.next(result)
    })
  }
}
