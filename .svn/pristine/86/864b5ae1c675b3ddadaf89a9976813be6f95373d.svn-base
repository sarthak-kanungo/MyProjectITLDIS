import { Component, OnInit, Input, OnChanges, ChangeDetectionStrategy, ChangeDetectorRef, Output, EventEmitter } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';
import { Observable, Subject, Subscription, BehaviorSubject } from 'rxjs';
import { SparesGrnPagePresenter } from '../spares-grn-page/spares-grn-page.presenter';
import { SparesGrnItemDetailService } from './spares-grn-item-detail.service';
import { SparesGrnItemDetailApiService } from './spares-grn-item-detail-api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-spares-grn-item-details',
  templateUrl: './spares-grn-item-details.component.html',
  styleUrls: ['./spares-grn-item-details.component.scss'],
  viewProviders: [SparesGrnItemDetailService, SparesGrnItemDetailApiService],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SparesGrnItemDetailsComponent implements OnInit, OnChanges {

  @Input() itemDetail: Observable<FormArray>;
  @Input() itemDetailTotalForm: FormGroup;
  binLocationList$: Observable<SelectList[]>;
  binList:any
  isGrnTypeEqualToOthers: boolean;
  grnTypeIsOthers$: Observable<boolean>;
  @Input() markForCheck: Observable<boolean>;
  @Output() binLocationValid = new EventEmitter();
  binCheck: boolean = false;
  viewCheck
  constructor(
    private sparesGrnPagePresenter: SparesGrnPagePresenter,
    private changeDetectorRef: ChangeDetectorRef,
    private sparesGrnItemDetailService: SparesGrnItemDetailService,
    private toastrService: ToastrService,
    private sparesGrnItemDetailApiService:SparesGrnItemDetailApiService
  ) { }

  ngOnInit() {
    this.grnTypeIsOthers$ = this.sparesGrnPagePresenter.getHideFieldsAtItemDetailWhenGrnTypeIsOthers$;
    this.changeDetectorMarkForCheck();
    this.subscribeToGrnTypeIsOthers();
    this.viewCheck = this.sparesGrnPagePresenter.operation;
    this.subscribeToItemDetailFormArray(this.itemDetail);
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    if (changes.itemDetail && changes.itemDetail.currentValue) {
      this.subscribeToItemDetailFormArray(this.itemDetail);
    }

    let regex = new RegExp("^\\(?([a-zA-Z0-9]{3})\\)?[-]?([a-zA-Z0-9]{2})[-]?([a-zA-Z0-9]{2})$");
    this.itemDetail.subscribe(fm => {
        fm.controls.forEach((fg: FormGroup) => {
          fg.get('binLocation').valueChanges.subscribe((val:string) => {
            if (val.length <= 9) {
              if (regex.test(val) && val.length == 9) {
                this.binCheck = true;
              } else {
                this.binCheck = false;
              }
            } else {
              if (val.length > 9) {
                this.toastrService.warning("Invalid Bin Location")
                fg.get('binLocation').reset()
              }
            }
            this.binLocationValid.emit(this.binCheck);         
          })
        })
    })  
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
  displayFn(obj: SelectList): string | number | undefined {
    if (obj && typeof obj === 'string') {
      return obj;
    }
    return obj ? obj.value : undefined;
  }
  displayCodeFn(obj: SelectList): string | number | undefined {
    return obj ? obj.code : undefined;
  }
  private subscribeToGrnTypeIsOthers() {
    const subscriptionToItemDetailFormArray = new Subscription();
    this.grnTypeIsOthers$.subscribe(isOthers => {
      
      if (isOthers) {
        this.subscribeToItemDetailFormArrayWhereGrnTypeIsOthers(subscriptionToItemDetailFormArray);
      }
    })
  }
  private subscribeToItemDetailFormArray(itemDetail$: Observable<FormArray>) {    
    itemDetail$.subscribe(itemDetail => {
      this.sparesGrnItemDetailService.totalReceivedQtyValueChange(itemDetail, this.viewCheck);
      //this.binLocationList$ = this.sparesGrnItemDetailService.binLocationValueChange(itemDetail);  /* commented by vinay bcoz getBinOnFocus() call is written on Focus*/
      this.sparesGrnItemDetailService.receivedDamageQtyValueChange(itemDetail);
    });
  }
  private subscribeToItemDetailFormArrayWhereGrnTypeIsOthers(subscription: Subscription) {
    if (!this.itemDetail) {
      return;
    }
    this.itemDetail.subscribe(itemDetail => {
      this.sparesGrnItemDetailService.unitPriceValueChange(itemDetail);
      this.sparesGrnItemDetailService.invoiceQtyValueChange(itemDetail);
      this.sparesGrnItemDetailService.discountValueChange(itemDetail);
    });
  }
  binLocationSelected(event) { 
    if (event) {
      this.binCheck = true;
      this.binLocationValid.emit(this.binCheck);   
    }
  }
  contenteditableValuechange(value, rowFormGroup: FormGroup, formControlName) {
    if (value || value === 0 || value === false) {
      rowFormGroup.get(formControlName).patchValue(value);
    }
  }
  addRow() {
    this.sparesGrnPagePresenter.insertRowIntoItemDetailWhereGrnTypeIsOthers();
  }
  deleteRow() {
    // let implementsDetails = this.editableTableForm.controls.table as FormArray;
    // let deleteRecordList = [];
    // let nonSelected = implementsDetails.controls.filter((machinery: FormGroup) => {
    //   if (machinery.value.isSelected) {
    //     deleteRecordList.push(machinery.getRawValue());
    //   }
    //   return !machinery.value.isSelected
    // });
    // this.deletedTableRecords.emit(deleteRecordList);
    // implementsDetails.clear()
    // nonSelected.forEach(el => implementsDetails.push(el))
  }

  getBinOnFocus(itemNo: FormControl){
    const storeId = this.sparesGrnPagePresenter.grnForm.get('store').value;
    this.sparesGrnItemDetailApiService.searchBinLocationByStoreId(storeId.id,'',itemNo.get('itemNumber').value).subscribe(res=>{
      this.binList =res
    })
    
  }
}
