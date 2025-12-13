import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DealerPoApprovalRoutingModule } from './dealer-po-approval-routing.module';
import { DealerPoApprovalComponent } from './pages/dealer-po-approval/dealer-po-approval.component';
import { PoSearchResultComponent } from './component/po-search-result/po-search-result.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { PurchaseOrderComponent } from './component/purchase-order/purchase-order.component';


@NgModule({
  declarations: [DealerPoApprovalComponent, PoSearchResultComponent, PurchaseOrderComponent],
  imports: [
    CommonModule,
    DealerPoApprovalRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule
  ]
})
export class DealerPoApprovalModule { }
