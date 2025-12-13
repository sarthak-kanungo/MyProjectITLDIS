import { Injectable } from '@angular/core';
import { InvoiceStoreService } from '../../invoice-store.service';
import { InvoiceDcByCustomerCode } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { BehaviorSubject } from 'rxjs';
import { DeliveryChallanApiService } from './delivery-challan-api.service';
import { InvoiceMaterialByDc } from '../../model/invoice-material-by-dc-adaptor.service';

@Injectable()
export class DeliveryChallansService {

  private materialListByDc = [] as InvoiceMaterialByDc[];
  constructor(
    private invoiceStoreService: InvoiceStoreService,
    private deliveryChallanApiService: DeliveryChallanApiService
  ) { }
  getDeliveryChallanByCustomerCode() {
    return this.invoiceStoreService.dcByCustomerCode$.asObservable();
  }
  getMaterialDetail(dc: InvoiceDcByCustomerCode, checked: boolean) {
    if (!checked) {
      this.invoiceStoreService.unselectedDeliveryChallanTableId$.next(dc.uuid);
      return
    }
    this.deliveryChallanApiService.getMaterialDetail(dc).subscribe(
      res => {
        // this.materialListByDc = [...res];
        this.storeMaterialAndAccessoryIntoInvoiceStore(res.result)
      }
    )
  }
  storeMaterialAndAccessoryIntoInvoiceStore(list) {
    this.invoiceStoreService.invoiceMaterialByDc$.next(list);
  }
}
