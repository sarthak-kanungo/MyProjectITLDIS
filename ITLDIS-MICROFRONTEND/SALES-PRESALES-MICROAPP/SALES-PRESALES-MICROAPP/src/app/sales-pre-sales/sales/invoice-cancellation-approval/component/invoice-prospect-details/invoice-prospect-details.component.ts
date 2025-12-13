import { Component, OnInit } from '@angular/core';
import { InvoiceProspectDetailsService } from './invoice-prospect-details.service';
import { FormGroup } from '@angular/forms';
import { InvoiceStoreService } from '../../invoice-store.service';
import { CustomerByCustomerCode } from '../../model/invoice-customer-detail-by-customer-code-adaptor.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-invoice-prospect-details',
  templateUrl: './invoice-prospect-details.component.html',
  styleUrls: ['./invoice-prospect-details.component.scss'],
  providers: [InvoiceProspectDetailsService]
})
export class InvoiceProspectDetailsComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  show
  labelPosition = 'before';
  disable = true;

  customertypes: string[] = [
    'Institution', 'Individual'
  ];

  prospectDetailsFrom: FormGroup;
  customerDetail$: BehaviorSubject<CustomerByCustomerCode>;
  constructor(
    private invoiceProspectDetailsService: InvoiceProspectDetailsService,
    private invoiceStoreService: InvoiceStoreService
  ) { }

  ngOnInit() {
    this.prospectDetailsFrom = this.invoiceProspectDetailsService.createprospectDetailsFrom();
    this.invoiceStoreService.customerDetail$.subscribe(res => {
      if (res) {
        this.prospectDetailsFrom.patchValue(res);
        return;
      }
      if (res === null) {
        this.prospectDetailsFrom.reset();
      }
    })

  }

}
