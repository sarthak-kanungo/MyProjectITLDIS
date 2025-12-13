import { Component, OnInit, Input } from '@angular/core';
import { InvoiceAccessoryDetailService } from './invoice-accessory-detail.service';
import { MaterialDetailApiService } from '../material-details-charges/material-detail-api.service';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';
import { BehaviorSubject } from 'rxjs';
import { FormGroup, FormArray } from '@angular/forms';
import { SelectList } from '../../../../../core/model/select-list.model';

@Component({
  selector: 'app-invoice-accessory-detail',
  templateUrl: './invoice-accessory-detail.component.html',
  styleUrls: ['./invoice-accessory-detail.component.scss'],
  providers: [InvoiceAccessoryDetailService, MaterialDetailApiService]
})
export class InvoiceAccessoryDetailComponent implements OnInit {

  totalInvoiceCalcForm: FormGroup;
  private accessoryDetailList = [] as InvoiceMaterialByDc[];
  accessoryDetailList$ = new BehaviorSubject<InvoiceMaterialByDc[]>(undefined);
  invoiceMaterialByDc$: BehaviorSubject<InvoiceMaterialByDc[]>;
  accessoryTableForm: any;
  table: FormArray;
  gstPercentList: SelectList[];
  totalInvoiceAmountInWords$: BehaviorSubject<string>;
  @Input() isCancelInvoice: boolean;
  @Input() invoiceDetail: object;

  constructor(
    private accessoryDetailService: InvoiceAccessoryDetailService,
    private invoiceStoreService: InvoiceStoreService
  ) { }

  ngOnInit() {
    this.totalInvoiceAmountInWords$ = this.accessoryDetailService.totalInvoiceAmountInWords$;
    this.accessoryTableForm = this.accessoryDetailService.createAccessoryTableForm();
    this.totalInvoiceCalcForm = this.accessoryDetailService.createTotalInvoiceCalcForm();
    this.getAccessoryDetail();
    this.invoiceMaterialByDc$ = this.invoiceStoreService.invoiceMaterialByDc$;
    this.unselectedDeliveryChallanTableId();
    // this.getGSTPercentList();
    this.patchValueCalcValueToTotalInvoiceCalcForm()
    this.disableTotalInvoiceCalcFormAtCancelInvoice();
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    //if (!!changes.isCancelInvoice && changes.isCancelInvoice.currentValue) {
      if (this.accessoryTableForm) {
        this.accessoryTableForm.disable();
      }
      if (this.totalInvoiceCalcForm) {
        this.totalInvoiceCalcForm.disable();
      }
    //}
    if (!!changes.invoiceDetail && changes.invoiceDetail.currentValue) {
      if (this.totalInvoiceCalcForm) {
        this.patchValueToTotalInvoiceCalcFormForCancelInvoice(changes.invoiceDetail.currentValue);
      }
    }
  }
  disableTotalInvoiceCalcFormAtCancelInvoice() {
    //if (this.isCancelInvoice) {
      this.totalInvoiceCalcForm.disable();
    //}
  }
  private patchValueToTotalInvoiceCalcFormForCancelInvoice(invoiceDetail) {
    this.totalInvoiceCalcForm.get('rtoCharges').patchValue(invoiceDetail.rtoCharges || null);
    this.totalInvoiceCalcForm.get('others').patchValue(invoiceDetail.others || null);
    this.totalInvoiceCalcForm.get('insuranceCharges').patchValue(invoiceDetail.insuranceCharges || null);

  }
  private patchValueCalcValueToTotalInvoiceCalcForm() {
    this.invoiceStoreService.updateTotalInvoiceCalcForm$.subscribe(res => {
      if (!res) {
        return;
      }
      this.totalInvoiceCalcForm.patchValue(res)
    });
  }
  private getGSTPercentList() {
    this.accessoryDetailService.getGSTPercentList().subscribe(res => {
      this.gstPercentList = res;
    })
  }
  private unselectedDeliveryChallanTableId() {
    this.invoiceStoreService.unselectedDeliveryChallanTableId$.subscribe(uuid => {
      this.accessoryDetailList = this.accessoryDetailList.filter(ele => ele.dcuuid !== uuid);
      this.deleteRow(uuid);
      if (uuid) {
        this.invoiceStoreService.calcTotalInvoiceAmount('basicAmount');
        this.invoiceStoreService.calcTotalInvoiceAmount('gstAmount');
      }
    })
  }
  getAccessoryDetail() {
    this.invoiceStoreService.invoiceMaterialByDc$.subscribe(accessoryDetail => {
      if (accessoryDetail) {
        this.accessoryDetailList = [...this.accessoryDetailList, ...accessoryDetail];
        // accessoryDetail[0].productGroup = 'implements';
        // accessoryDetail[0]['qty'] = 1
        accessoryDetail.forEach(material => {
          // material.productGroup = 'implements'
          if (material.productGroup.toLowerCase() === 'implements') {
            this.addRow(material)
          }
        })
        return;
      }
      // this.accessoryDetailList$.next([...this.accessoryDetailList]);
    })
  }
  disableAccessoryFormTableAtCancelInvoice(table: FormArray) {
    if (table) {
      table.disable();
    }
  }
  addRow(material: InvoiceMaterialByDc): void {
    let table = this.accessoryTableForm.get('table') as FormArray;
    table.push(this.accessoryDetailService.createTableRow(material));
    this.disableAccessoryFormTableAtCancelInvoice(table);
  }
  deleteRow(dcuid: string) {
    const accessoryTable = this.accessoryTableForm.controls.table as FormArray;
    let deleteRecordList = [];
    let nonSelected = accessoryTable.controls.filter((material: FormGroup) => {
      if (material.value.isSelected) {
        deleteRecordList.push(material.getRawValue());
      }
      return material.value.dcuuid !== dcuid
    });
    accessoryTable.clear()
    nonSelected.forEach(el => accessoryTable.push(el));
  }
  rateChange(rate) {
    // console.log('rate', rate, this.accessoryDetailList);
  }
  contenteditableValuechange(value, rowFormGroup: FormGroup, formControlName) {
    rowFormGroup.get(formControlName).patchValue(value);
  }
  removeBottomPadingOfMatFormField(formControlName) {
    if (!formControlName) {
      return false;
    }
    return (((this.totalInvoiceCalcForm.get(formControlName).touched && !this.totalInvoiceCalcForm.get(formControlName).errors) || (!this.totalInvoiceCalcForm.get(formControlName).touched && !this.totalInvoiceCalcForm.get(formControlName).errors)) || !this.totalInvoiceCalcForm.get(formControlName).touched)
  }
}
