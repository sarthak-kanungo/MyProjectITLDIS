import { Injectable } from '@angular/core';
import { SparesGrnPagePresenter } from '../spares-grn-page/spares-grn-page.presenter';
import { SpareAccpacInvoice, SpareAccpacInvoiceItem } from '../../domain/spare-grn.domain';
import { SelectList } from '../../../../../core/model/select-list.model';

@Injectable()
export class SparesGrnService {

  constructor(
    private sparesGrnPagePresenter: SparesGrnPagePresenter,
  ) { }
  patchInvoiceDetailToGrnForm(invoiceDetail: SpareAccpacInvoice) {
    this.sparesGrnPagePresenter.grnForm.patchValue(invoiceDetail);
  }
  patchInvoiceDetailToItemDetail(itemDetailList: SpareAccpacInvoiceItem[], isOther?:boolean) {
    this.sparesGrnPagePresenter.clearAllItemDetail();
    const grnType: SelectList = this.sparesGrnPagePresenter.grnForm.get('grnType').value;

    if (grnType && typeof grnType==='object' && grnType.value === 'Others') {
      isOther = true;
    }

    itemDetailList.forEach((itemDetail, index) => {
      console.log("itemDetail--->", itemDetail)
      if (isOther) {
        this.sparesGrnPagePresenter.insertRowIntoItemDetailWhereGrnTypeIsOthers(itemDetail);
        return;
      }
      this.sparesGrnPagePresenter.insertRowIntoItemDetail(itemDetail);
    });
  }
}
