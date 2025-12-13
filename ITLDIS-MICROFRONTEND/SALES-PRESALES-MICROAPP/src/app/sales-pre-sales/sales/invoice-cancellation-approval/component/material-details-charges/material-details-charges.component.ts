import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { MaterialDetailsChargesService } from './material-details-charges.service';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';
import { TableConfig } from 'editable-table';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
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
  gstPercentList: SelectList[];
  @Input() isCancelInvoice: boolean;
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
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    //if (!!changes.isCancelInvoice && changes.isCancelInvoice.currentValue) {
      if (this.materialTableForm) {
        this.materialTableForm.disable();
      }
    //}
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
            this.addRow(material)
          }
        })
        return;
      }
      // this.materialDetailList$.next([...this.materialDetailList]);
    })
  }
  disableMaterialFormTableAtCancelInvoice(table: FormArray) {
    if (table) {
      table.disable();
    }
  }
  addRow(material: InvoiceMaterialByDc): void {
    let table = this.materialTableForm.get('table') as FormArray;
    table.push(this.materialDetailsChargesService.createTableRow(material, false));
    this.disableMaterialFormTableAtCancelInvoice(table);
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
}
