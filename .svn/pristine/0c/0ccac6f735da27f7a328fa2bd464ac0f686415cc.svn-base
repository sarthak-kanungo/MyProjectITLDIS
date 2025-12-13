import { Component, OnInit, Input, ChangeDetectionStrategy, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatSelectChange, MatAutocompleteSelectedEvent } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable, of } from 'rxjs';
import { SparesGrnPagePresenter, ISparesGrnPagePresenter } from '../spares-grn-page/spares-grn-page.presenter';
import { SparesGrnApiService } from './spares-grn-api.service';
import { SparesGrnService } from './spares-grn.service';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { StorageLoginUser } from 'LoginDto';
import { SpareAccpacInvoiceItem } from '../../domain/spare-grn.domain';
import { AutocompleteService } from '../../../../../root-service/autocomplete.service';
import { SpareGrnPageApiService } from '../spares-grn-page/spare-grn-page-api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-spares-grn',
  templateUrl: './spares-grn.component.html',
  styleUrls: ['./spares-grn.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    },
    SparesGrnApiService,
    SparesGrnService
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SparesGrnComponent implements OnInit, OnChanges {
  @Input() isEdit: boolean;
  @Input() isView: boolean;
  data: Object;
  grnType: string;
  @Input() grnForm: FormGroup;
  grnTypeList$: Observable<SelectList[]>;
  supplierTypeList$: Observable<SelectList[]>;
  supplierNameList$: Observable<SelectList[]>;
  invoiceNumberList$: Observable<SelectList[]>;
  storeList$: Observable<SelectList[]>;
  sparePurchaseOrderList$: Observable<SelectList[]>;
  isShowSupplierDetail: boolean;
  sparesGrnPagePresenter: ISparesGrnPagePresenter;
  isShowSupplierName: boolean;
  @Input() markForCheck: Observable<boolean>;
  loginUser: StorageLoginUser;
  isShowPoSearchField: boolean;
  isShowInvoiceNumberSearchIcon: boolean = true;
  currentDate: Date;
  invoiceDate: Date = new Date();
  
  constructor(
    sparesGrnPagePresenter: SparesGrnPagePresenter,
    private sparesGrnApiService: SparesGrnApiService,
    private sparesGrnService: SparesGrnService,
    private changeDetectorRef: ChangeDetectorRef,
    private loginForm: LoginFormService,
    private autocompleteService: AutocompleteService,
    private spareGrnPageApiService:SpareGrnPageApiService,
    private toasterService: ToastrService
  ) {
    this.sparesGrnPagePresenter = sparesGrnPagePresenter;
  }

  ngOnInit() {
    this.spareGrnPageApiService.getServerDate().subscribe(sevrDate => {
      this.currentDate = new Date(sevrDate.result);
      this.grnForm.get('grnDate').patchValue(sevrDate.result);
      this.grnForm.get('goodsReceiptDate').patchValue(sevrDate.result);
    });
    this.getGrnType();
    this.getStoresName();
    //this.changeDetectorMarkForCheck();
    this.loginUser = this.loginForm.getLoginUser();
    this.grnForm && this.grnForm.get('grnDoneBy').patchValue(this.loginUser.name);
    if(this.isEdit){
        this.grnForm.disable();
    }
    
  }
  
  getInvoiceNumberFun(val){
      if(!this.isView && !this.isEdit && val){
    
          if (this.grnForm.get('supplierType').value === 'Oil and Lubricants' || this.grnForm.get('supplierType').value === 'Other Suppliers') {
           return;
          }
          if(this.grnForm.get('grnType').value) {
          } else {
            this.toasterService.warning("Please Select GRN Type.")
            this.grnForm.get('grnType').markAsTouched();
            return false;
          }

          if(this.grnForm.get('supplierType').value) {
          } else {
            this.toasterService.warning("Please Select Supplier Type.")
            this.grnForm.get('supplierType').markAsTouched();
            return false;
          }

          if(this.grnForm.get('store').value) {
          }else{
            this.toasterService.warning("Please Select Store.")
            this.grnForm.get('store').markAsTouched();
            return false;
          }
        

          this.invoiceNumberList$ = this.sparesGrnApiService.searchInvoiceNumber(val, (this.grnForm.get('supplierName').value!=null?this.grnForm.get('supplierName').value.id:0), this.grnForm.get('supplierType').value, this.grnForm.get('store').value.id);
      }
  }
  getPoNumberFun(val){
      if (this.grnForm.get('supplierName').value && typeof this.grnForm.get('supplierName').value === 'object') {
          if(this.grnForm.get('store').value) {
            this.sparePurchaseOrderList$ = this.sparesGrnApiService.searchPoNumberForGrn(this.grnForm.get('supplierName').value.id, val, this.grnForm.get('supplierType').value);
          } else {
            if (val) {
              this.toasterService.warning("Please Select Store.")
            }
          }
        }
  }
  ngOnChanges(changes: SimpleChanges): void {
      /*if (changes.grnForm && changes.grnForm.firstChange && changes.grnForm.currentValue) { */
      if(!this.isView && !this.isEdit){
          this.grnForm.get('invoiceNumber').valueChanges.subscribe((val: string) => {
              /*if (this.grnForm.get('supplierType').value === 'Oil and Lubricants' || this.grnForm.get('supplierType').value === 'Other Suppliers') {
                return;
              }
              if (typeof val === 'string' && val) {
                if(this.grnForm.get('store').value) {
                  this.invoiceNumberList$ = this.sparesGrnApiService.searchInvoiceNumber(val, (this.grnForm.get('supplierName').value!=null?this.grnForm.get('supplierName').value.id:0), this.grnForm.get('supplierType').value);
                } else {
                     this.toasterService.warning("Please Select Store.")
                   }
              }*/
              this.getInvoiceNumberFun(val);
              //this.autocompleteService.validateSelectedFromList(val, this.invoiceNumberList$, this.grnForm.get('invoiceNumber'));
          });
          this.grnForm.get('supplierName').valueChanges.subscribe((val: string) => {
              if (typeof val === 'string' && val) {
                this.supplierNameList$ = this.sparesGrnApiService.searchSupplierName(this.grnForm.get('supplierType').value, val);
              }
              //this.autocompleteService.validateSelectedFromList(val, this.supplierNameList$, this.grnForm.get('supplierName'));
          });
          this.grnForm.get('sparePurchaseOrder').valueChanges.subscribe((val: string) => {
              //if(this.grnForm.get('store').value) {
             this.getPoNumberFun(val);
            // } else {
            //   this.toasterService.warning("Please Select Store.")
            // }
              //this.autocompleteService.validateSelectedFromList(val, this.sparePurchaseOrderList$, this.grnForm.get('sparePurchaseOrder'));
          });
          this.grnForm.get('grnType').valueChanges.subscribe((val: string) => {
              if (val === '') {
                return;
              }

              this.invoiceNumberList$ = of([]);
              this.sparePurchaseOrderList$ = of([]);

              if (typeof val === 'string' && val) {
                this.defaultConfigForGrnWhenGrnTypeChange(val);
                this.checkToShowSupplierDetail(val);
                this.checkToShowSuppilerName(val);
                this.checkToShowInvoiceNumberSearchIcon(val);
              }
           });

          
          this.grnForm.get('invoiceDate').valueChanges.subscribe((val: Date) => {
              this.invoiceDate = val;
              this.grnForm.get('goodsReceiptDate').reset();
          });
          
      }else{
          
          this.grnForm.get('grnType').valueChanges.subscribe((val: string) => {
              if (typeof val === 'string' && val) {
                  this.checkToShowSupplierDetail(val);
                  this.checkToShowSuppilerName(val);
                  this.checkToShowInvoiceNumberSearchIcon(val);
                  
                  this.grnType = val;
                  this.supplierTypeList$ = this.sparesGrnApiService.getSuppilerType(this.grnType);
                  
                  if(val === 'Others'){
                      this.sparesGrnPagePresenter.hideFieldsAtItemDetailWhenGrnTypeIsOthers = true;
                      this.isShowPoSearchField = true;
                  }else
                      this.sparesGrnPagePresenter.hideFieldsAtItemDetailWhenGrnTypeIsOthers = false;
              }
          });
          

          
          this.grnForm.get('invoiceDate').valueChanges.subscribe((val: Date) => {
              this.invoiceDate = val;
          });
      }
  }
  private changeDetectorMarkForCheck() {
    if (this.markForCheck) {
      this.markForCheck.subscribe(res => {
        if (res) {
          
          this.changeDetectorRef.markForCheck();
        }
      });
    }
  }
  private getGrnType() {
    this.grnTypeList$ = this.sparesGrnApiService.getGrnType();
  }
  private getSuppilerType() {
    this.supplierTypeList$ = this.sparesGrnApiService.getSuppilerType(this.grnType);
    this.grnForm.controls.supplierName.patchValue(null);
    this.supplierNameList$ = null;
  }
  private getStoresName() {
    this.storeList$ = this.sparesGrnApiService.getStoresName();
  }
  private invoiceNumberValueChanges(grnFg: FormGroup) {
    
  }
  private supplierNameValueChanges(grnFg: FormGroup) {
   
  }
  private sparePurchaseOrderValueChanges(grnFg: FormGroup) {
    
  }
  private grnTypeValueChanges(grnFg: FormGroup) {
    
  }
  displayFn(obj: SelectList): string | number | undefined {
    if (obj && typeof obj === 'string') {
      return obj;
    }
    return obj ? obj.value : undefined;
  }
  displayCodeFn(obj: SelectList): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  compareFn(c1: SelectList, c2: SelectList): boolean {
    if (typeof c1 !== typeof c2) {
      if (typeof c1 === 'object' && typeof c2 === 'string') return c1.value === c2;
      if (typeof c2 === 'object' && typeof c1 === 'string') return c1 === c2.value;
    }
    return c1 && c2 ? c1.value === c2.value : c1 === c2;
  }
  grnTypeSelectionChange(event: MatSelectChange) {
    console.log("grnTypeSelectionChange--->", event)
    if (typeof event.value === 'object') {
      this.defaultConfigForGrnWhenGrnTypeChange(event.value.value);
      this.checkToShowSupplierDetail(event.value.value);
      this.checkToShowSuppilerName(event.value.value);
      this.grnType = event.value.value;
      this.getSuppilerType();
      this.grnForm.controls.supplierName.patchValue(null);
      //this.patchValueToSuppilerType(event.value.value);
      this.checkToShowInvoiceNumberSearchIcon(event.value.value);
    }
  }
  private checkToShowInvoiceNumberSearchIcon(grnType: string) {
    if (grnType === 'Others') {
      this.isShowInvoiceNumberSearchIcon = false;
      return;
    }
    this.isShowInvoiceNumberSearchIcon = true;
  }
  private checkToShowSuppilerName(grnType: string) {
    if (grnType !== 'KAI') {
      this.isShowSupplierName = true;
      return;
    }
    this.isShowSupplierName = false;
  }
  private defaultConfigForGrnWhenGrnTypeChange(grnType: string) {
    this.sparesGrnPagePresenter.resetGrnFormWhenGrnTypeChange();
    this.invoiceNumberList$ = of([]);
    if (grnType === 'Others') {
      this.sparesGrnPagePresenter.defaultEnableAndDisableFieldsOfGrnFormForGrnTypeOthers();
      this.isShowPoSearchField = true;
      this.sparesGrnPagePresenter.clearAllItemDetail();
      this.sparesGrnPagePresenter.resetItemDetailToatlForm();
      // this.sparesGrnPagePresenter.insertRowIntoItemDetailWhereGrnTypeIsOthers();
      this.sparesGrnPagePresenter.hideFieldsAtItemDetailWhenGrnTypeIsOthers = true;
      this.grnForm.get('transportMode').enable()
      this.grnForm.get('transporter').enable()
      return;
    }
    this.isShowPoSearchField = false;
    this.sparesGrnPagePresenter.defaultEnableAndDisableFieldsOfGrnForm();
    this.sparesGrnPagePresenter.clearAllItemDetail();
    this.sparesGrnPagePresenter.resetItemDetailToatlForm();
    this.sparesGrnPagePresenter.hideFieldsAtItemDetailWhenGrnTypeIsOthers = false;
  }
  // private patchValueToSuppilerType(grnType: string) {
  //   if (grnType === 'KAI') {
  //     this.sparesGrnPagePresenter.resetSupplierInfo();
  //     this.grnForm.get('supplierType').patchValue(grnType);
  //     this.grnForm.get('supplierType').disable();
  //     return;
  //   }
  //   if (grnType === 'Others') {
  //     this.sparesGrnPagePresenter.resetSupplierInfo();
  //     this.grnForm.get('supplierType').patchValue('THIRD PARTY SUPPLIER');
  //     this.grnForm.get('supplierType').disable();
  //     return;
  //   }
  //   this.grnForm.get('supplierType').patchValue(null);
  // }
  private checkToShowSupplierDetail(grnType: string) {
    if (grnType !== 'KAI') {
      this.isShowSupplierDetail = true;
      return;
    }
    this.isShowSupplierDetail = false;
  }
  supplierTypeSelectionChange(event: MatSelectChange) {
      this.grnForm.controls.supplierName.patchValue(null);
      this.supplierNameList$ = of([]); 
  }
  supplierNameOptionSelected(event: MatAutocompleteSelectedEvent) {
    if (event.option.value && event.option.value.id) {
      this.autocompleteService.removeSelectedFromListError(this.grnForm.get('supplierName'));
      this.getSupplierDetailsBySupplierId(event.option.value.id, this.grnForm.get('supplierType').value);
      return;
    }
  }
  private getSupplierDetailsBySupplierId(supplierId: number, supplierType:string) {
    this.sparesGrnApiService.getSupplierDetailsBySupplierId(supplierId, supplierType).subscribe(res => {
      
      this.grnForm.patchValue(res);
    });
  }
  sparePurchaseOrderOptionSelected(event: MatAutocompleteSelectedEvent) {
    this.grnForm.get("store").disable();
    if (event.option.value && event.option.value.id) {
      this.autocompleteService.removeSelectedFromListError(this.grnForm.get('sparePurchaseOrder'));
      this.getPoDetailsByPoNumberForGrn(event.option.value.id);
      this.sparePurchaseOrderList$ = null;
      return;
    }
  }
  private getPoDetailsByPoNumberForGrn(poId: number) {
    this.sparesGrnApiService.getPoDetailsByPoNumberForGrn(poId, this.grnForm.get('store').value.id).subscribe(res => {
      
      // this.grnForm.patchValue(res);
      this.sparesGrnService.patchInvoiceDetailToGrnForm(res.accpacInvoice);
      this.sparesGrnService.patchInvoiceDetailToItemDetail(res.accpacInvoiceItems);
    });
  }

  invoiceNumberOptionSelected(event: MatAutocompleteSelectedEvent) {
    console.log('invoiceNumberOptionSelected: ', event);
    this.grnForm.get("store").disable();
    this.invoiceNumberList$ = of([]);
    this.autocompleteService.removeSelectedFromListError(this.grnForm.get('invoiceNumber'));
    this.sparesGrnApiService.getInvoiceDetailByInvoiceNo(event.option.value.id, (this.grnForm.get('supplierName').value!=null?this.grnForm.get('supplierName').value.id:0), this.grnForm.get('supplierType').value, this.grnForm.get('store').value.id).subscribe(res => {
      console.log("getInvoiceDetail--->", res)
      this.sparesGrnService.patchInvoiceDetailToGrnForm(res.accpacInvoice);
      this.sparesGrnService.patchInvoiceDetailToItemDetail(res.accpacInvoiceItems);
      this.invoiceDate = res.accpacInvoice.invoiceDate;
      this.grnForm.get('goodsReceiptDate').reset();
    })
  }
}
