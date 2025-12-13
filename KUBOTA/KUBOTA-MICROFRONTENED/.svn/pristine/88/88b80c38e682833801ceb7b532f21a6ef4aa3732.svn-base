import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';

import { ToastrModule } from 'ngx-toastr';
import { MaterialModule } from '../../../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { ItemDetailsComponent } from './component/item-details/item-details.component';
import { SparesPurchaseOrderRoutingModule } from './spares-purchase-order-routing.module';
import { PurchaseOrderComponent } from './component/purchase-order/purchase-order.component';
import { WebserviceConfigModule } from '../../../webservice-config/webservice-config.module';
import { PurchaseOrderCreatePageComponent } from './component/purchase-order-create-page/purchase-order-create-page.component';
import { PurchaseOrderSearchPageComponent } from './component/purchase-order-search-page/purchase-order-search-page.component';
import { PurchaseOrderSearchComponent } from './component/purchase-order-search/purchase-order-search.component';
import { UiModule } from '../../../ui/ui.module';
import { ModalFileUploadComponent } from './component/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from './component/item-error-report/item-error-report.component';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';


@NgModule({
  declarations: [
    PurchaseOrderComponent,
    PurchaseOrderCreatePageComponent,
    PurchaseOrderSearchPageComponent,
    ItemDetailsComponent,
    PurchaseOrderSearchComponent,
    ModalFileUploadComponent,
    ItemErrorReportComponent,
    ApprovalDetailsComponent
  ],

  imports: [
    CommonModule,
    SparesPurchaseOrderRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    WebserviceConfigModule,
    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    UiModule
  ],
  entryComponents: [ModalFileUploadComponent, ItemErrorReportComponent],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class SparesPurchaseOrderModule { }
