import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PurchaseOrderRoutingModule } from './purchase-order-routing.module';
import { PurchaseOrderCreateComponent } from './pages/purchase-order-create/purchase-order-create.component';
import { PurchaseOrderSearchComponent } from './pages/purchase-order-search/purchase-order-search.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { SalesPurchaseOrderSearchComponent } from './component/sales-purchase-order-search/sales-purchase-order-search.component';
import { SalesPurchaseOrderCreateComponent } from './component/sales-purchase-order-create/sales-purchase-order-create.component';
import { NgxsModule } from '@ngxs/store';
import { ZooState } from './po-state';
import { SalesPurchaseOrderCreateContainerComponent } from './component/sales-purchase-order-create-container/sales-purchase-order-create-container.component';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { MaterialModule } from '../../../app.module';
import { PurchaseOrderService } from './purchase-order.service';
import { PoMachineDetailsComponent } from './component/po-machine-details/po-machine-details.component';
import { PoMachineDetailsContainerComponent } from './component/po-machine-details-container/po-machine-details-container.component';
import { SalesPurchaseOrderSearchContainerComponent } from './component/sales-purchase-order-search-container/sales-purchase-order-search-container.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { DocumentUploadComponent } from './component/document-upload/document-upload.component';
import { DocumentUploadContainerComponent } from './component/document-upload-container/document-upload-container.component';
import { UiModule } from '../../../ui/ui.module';
import {MatSelectModule} from '@angular/material/select';
  

@NgModule({
  declarations: [
    PurchaseOrderCreateComponent,
    PurchaseOrderSearchComponent,
    SalesPurchaseOrderSearchComponent,
    SalesPurchaseOrderCreateComponent,
    SalesPurchaseOrderCreateContainerComponent,
    PoMachineDetailsComponent,
    PoMachineDetailsContainerComponent,
    SalesPurchaseOrderSearchContainerComponent,
    DocumentUploadComponent,
    DocumentUploadContainerComponent
  ],
  imports: [
    CommonModule,
    PurchaseOrderRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    ConfirmationBoxModule,
    MaterialModule,
    NgxsModule.forFeature([ZooState]),
    NgswSearchTableModule,
    ToastrModule,
    UiModule,
    MatSelectModule
  ],
  providers: [
    PurchaseOrderService,
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class PurchaseOrderModule { }
