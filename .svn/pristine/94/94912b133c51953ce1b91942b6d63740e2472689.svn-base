import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { MaterialDetailsChargesService } from './material-details-charges.service';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';
import { TableConfig } from 'editable-table';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';
import { SelectList } from '../../../../../core/model/select-list.model';
import { MaterialDetailApiService } from './material-detail-api.service';

@Component({
  selector: 'app-material-details-charges',
  templateUrl: './material-details-charges.component.html',
  styleUrls: ['./material-details-charges.component.scss'],
  providers: [MaterialDetailsChargesService, MaterialDetailApiService]
})
export class MaterialDetailsChargesComponent implements OnInit, OnChanges {
  private materialDetailList = [] as InvoiceMaterialByDc[];
  materialDetailList$ = new BehaviorSubject<InvoiceMaterialByDc[]>(undefined);
  invoiceMaterialByDc$: BehaviorSubject<InvoiceMaterialByDc[]>;
  materialTableForm: FormGroup;
  table: FormArray;
  csbNoList
  gstPercentList: SelectList[];
  isDealerTransfer:boolean=false;
  @Input() isCancelInvoice: boolean;
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  constructor(
    private materialDetailsChargesService: MaterialDetailsChargesService,
    private invoiceStoreService: InvoiceStoreService
  ) {
  }

  ngOnInit() {
    this.materialTableForm = this.materialDetailsChargesService.createMaterialTableForm();
    this.getMaterialDetail();
    this.invoiceMaterialByDc$ = this.invoiceStoreService.invoiceMaterialByDc$;
    this.unselectedDeliveryChallanTableId();
    this.getGSTPercentList();

  // if(invoiceDetail.csbNo){
  //   this.invoiceForm.get('csbNo').patchValue({ value: invoiceDetail.csbNo, code: invoiceDetail.csbNo , id: invoiceDetail.csbId });
  // }
    this.invoiceStoreService.invoiceTypeSelectionBehaviour.subscribe(response => {
      if(response=='Dealer Transfer Invoice'){
        this.isDealerTransfer = true;
      }else{
        this.isDealerTransfer = false;
      }
    })
  }
  fetchCSBNoList(fg,event){
    fg.get('csbNo').reset();
    fg.get('csbNo').clearValidators();
    if(fg.get('invoiceType').value=='Dealer Transfer Invoice'){
      this.isDealerTransfer = true;
      // fg.get('csbNo').setValidators(Validators.compose([Validators.required]))
    }else{
      this.isDealerTransfer = false;
    }
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    if ((changes.isCancelInvoice || changes.isView) && changes.isCancelInvoice.currentValue) {
      if (this.materialTableForm) {
        this.materialTableForm.disable();
      }
    }
  }
  private getGSTPercentList() {
    this.materialDetailsChargesService.getGSTPercentList().subscribe(res => {
      this.gstPercentList = res;
    });
  }
  private unselectedDeliveryChallanTableId() {
    this.invoiceStoreService.unselectedDeliveryChallanTableId$.subscribe(uuid => {
      this.materialDetailList = this.materialDetailList.filter(ele => ele.dcuuid !== uuid);
      this.deleteRow(uuid);
      if (uuid) {
        this.invoiceStoreService.calcTotalInvoiceAmount('basicAmount');
        this.invoiceStoreService.calcTotalInvoiceAmount('gstAmount');
      }
    })
  }
  getMaterialDetail() {
    this.invoiceStoreService.invoiceMaterialByDc$.subscribe(materialDetail => {
      if (materialDetail) {
        this.materialDetailList = [...this.materialDetailList, ...materialDetail];
        materialDetail.forEach(material => {
          if (material.productGroup.toLowerCase() === 'machinery') {
            let fg:FormGroup = this.addRow(material);
            if(material && material.csbNo){
              this.isDealerTransfer = true;
            }
            if(this.isDealerTransfer){
              fg.get('csbNo').setValidators(Validators.compose([Validators.required]))
            }
          }
        })
        return;
      }
      // this.materialDetailList$.next([...this.materialDetailList]);
    })
  }
  disableMaterialFormTableAtCancelInvoice(table: FormArray) {
    if ((this.isCancelInvoice || this.isView) && table) {
      table.disable();
    }
  }
  addRow(material: InvoiceMaterialByDc): FormGroup {
    let table = this.materialTableForm.get('table') as FormArray;
    let flag = this.isCancelInvoice || this.isView;
    let fg:FormGroup = this.materialDetailsChargesService.createTableRow(material, !flag)
    table.push(fg);
    this.disableMaterialFormTableAtCancelInvoice(table);

    fg.get('csbNo').valueChanges.subscribe(value => {
      if(value){
        if(typeof value=='string'){
          fg.get('csbNo').setErrors({'selectFromList':'Select From List'});
          this.materialDetailsChargesService.getCsbNolist(value, fg.get('model').value).subscribe(response => {
            this.csbNoList = response['result'];
          });
        }else{
          fg.get('csbNo').setErrors(null);
        }
      }
    });
    return fg;
  }
  deleteRow(dcuid: string) {
    const materialTable = this.materialTableForm.controls.table as FormArray;
    let deleteRecordList = [];
    let nonSelected = materialTable.controls.filter((material: FormGroup) => {
      if (material.value.isSelected) {
        deleteRecordList.push(material.getRawValue());
      }
      return material.value.dcuuid !== dcuid
    });
    materialTable.clear()
    nonSelected.forEach(el => materialTable.push(el));
  }
  rateChange(rate) {
    // console.log('rate', rate, this.materialDetailList);
  }
  contenteditableValuechange(value, rowFormGroup: FormGroup, formControlName) {
    rowFormGroup.get(formControlName).patchValue(value);
  }
  removeBottomPadingOfMatFormField(fg: FormGroup, formControlName) {
    if (!formControlName) {
      return false;
    }
    return (((fg.get(formControlName).touched && !fg.get(formControlName).errors) || (!fg.get(formControlName).touched && !fg.get(formControlName).errors)) || !fg.get(formControlName).touched)
  }
  
  public selectedCSB(selectedOption?: object): string | undefined{
    if (!!selectedOption && typeof selectedOption === 'string') {
      return selectedOption;
    }
    return selectedOption ? selectedOption['code'] : undefined;
  }
}
