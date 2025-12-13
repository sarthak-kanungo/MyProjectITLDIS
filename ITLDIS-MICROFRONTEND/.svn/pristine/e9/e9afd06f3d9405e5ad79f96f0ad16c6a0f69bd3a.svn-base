import { Injectable } from '@angular/core';
import { Adapter } from '../../../../../core/adapter';
import { SaveSpareGrn as ISaveSpareGrn, sparePartGrnItem, Id, SpareGoodsReceiptNoteForm } from '../../domain/spare-grn.domain';
import { DateService } from '../../../../../root-service/date.service';
import { SelectList } from '../../../../../core/model/select-list.model';


type SaveSpareGrnPartial = {
  [P in keyof SaveSpareGrn]?: SaveSpareGrn[P];
};
class SaveSpareGrn implements ISaveSpareGrn {
  noOfBoxesReceived: number;
  store: Id;
  invoiceNumber: Id;
  basicAmount: number;
  draftFlag: boolean;
  goodsReceiptDate: string;
  grnStatus: string;
  grnType: string;
  gstAmount: number;
  receiptValue: number;
  spareGrnNumber: string;
  sparePartGrnItems: sparePartGrnItem[];
  supplierType: string;
  totalGrnAmount: number;
  invoiceDate: string;
  supplierInvoiceNumber?: string;
  sparePurchaseOrder?:any;
  constructor(keyMap, obj: object) {
    
    const keyValuePair: [string, any][] = Object.entries(obj);
    keyValuePair.forEach(ele => {
      if (keyMap && keyMap[ele[0]]) {
        this[keyMap[ele[0]]] = ele[1];
        return;
      }
      this[ele[0]] = ele[1];
    });
    
  }
}
@Injectable()
export class SpareGrnPageAdapter implements Adapter<SaveSpareGrn> {
  constructor(
    private dateService: DateService
  ) { }
  adapt<R>(item: any, keyMap?: any): SaveSpareGrn | R {
    throw new Error("Method not implemented.");
  }
  saveAdapt?<R = unknown, S = unknown>(record: S): SaveSpareGrn | R;
  saveAdapt?<R = unknown>(record: any): SaveSpareGrn | R;
  saveAdapt?(record: SpareGoodsReceiptNoteForm) {
    const grnFormData = { ...record.grn, ...record.itemDetailTotal, ...{ itemDetail: record.itemDetail } };
    const saveGrn = new SaveSpareGrn({
      itemDetail: 'sparePartGrnItems'
    }, grnFormData);
    saveGrn.goodsReceiptDate = this.dateService.getDateIntoDDMMYYYY(record.grn.goodsReceiptDate);
    saveGrn.invoiceDate = this.dateService.getDateIntoDDMMYYYY(record.grn.invoiceDate);
    if (typeof saveGrn.grnType === 'object') {
      saveGrn.grnType = (saveGrn.grnType as SelectList).value;
    }
    
    if (saveGrn.grnType === 'Others') {
      saveGrn.supplierInvoiceNumber = grnFormData.invoiceNumber as string;  
      delete saveGrn.invoiceNumber
    }else{
        saveGrn.supplierInvoiceNumber = grnFormData.invoiceNumber['value'];
    }
    
    return saveGrn;
  }
}
