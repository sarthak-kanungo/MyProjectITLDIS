import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { DeliveryChallansService } from './delivery-challans.service';
import { InvoiceDcByCustomerCode } from '../../model/invoice-dc-by-customer-code-adaptor.service';
import { BehaviorSubject, Observable } from 'rxjs';
import { MatCheckboxChange } from '@angular/material';
import { DeliveryChallanApiService } from './delivery-challan-api.service';
import { InvoiceMaterialByDcAdaptorService } from '../../model/invoice-material-by-dc-adaptor.service';

@Component({
  selector: 'app-delivery-challans',
  templateUrl: './delivery-challans.component.html',
  styleUrls: ['./delivery-challans.component.scss'],
  providers: [DeliveryChallansService, DeliveryChallanApiService, InvoiceMaterialByDcAdaptorService]
})
export class DeliveryChallansComponent implements OnInit, OnChanges {

  dcByCustomerCode$: Observable<InvoiceDcByCustomerCode[]>;
  @Input() isCancelInvoice: boolean;
  @Input() isView: boolean;
  @Input() isEdit: boolean;
  @Input() materialDetail;
  constructor(private deliveryChallansService: DeliveryChallansService) { }

  ngOnInit() {
    this.dcByCustomerCode$ = this.deliveryChallansService.getDeliveryChallanByCustomerCode();
  }
  ngOnChanges(changes: import("@angular/core").SimpleChanges): void {
    if (!!changes.materialDetail && changes.materialDetail.currentValue) {
      this.deliveryChallansService.storeMaterialAndAccessoryIntoInvoiceStore(changes.materialDetail.currentValue);
    }
  }
  deliveryChallanChange(evt: MatCheckboxChange, dc: InvoiceDcByCustomerCode) {
    this.deliveryChallansService.getMaterialDetail(dc, evt.checked);
  }
}
