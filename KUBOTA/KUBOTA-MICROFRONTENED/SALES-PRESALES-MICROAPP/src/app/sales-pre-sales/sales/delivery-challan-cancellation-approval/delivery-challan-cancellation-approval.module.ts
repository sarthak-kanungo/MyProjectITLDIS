import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DeliveryChallanCancellationApprovalRoutingModule } from './delivery-challan-cancellation-approval-routing.module';

import { DeliveryChallanCancellationApprovalComponent } from './delivery-challan-cancellation-approval.component';

import { DeliveryChallanCreateComponent } from './pages/delivery-challan-create/delivery-challan-create.component';
import { DeliveryChallanComponent } from './component/delivery-challan/delivery-challan.component';
import { DeliverableChecklistComponent } from './component/deliverable-checklist/deliverable-checklist.component';
import { ImplementsAndAccessoriesComponent } from './component/implements-and-accessories/implements-and-accessories.component';
import { DeliveryProspectDetailsComponent } from './component/delivery-prospect-details/delivery-prospect-details.component';
import { DeliveryInsuranceDetailsComponent } from './component/delivery-insurance-details/delivery-insurance-details.component';
import { DeliveryChallanCreateService } from './pages/delivery-challan-create/delivery-challan-create.service';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';

import { EditableTableModule } from 'editable-table';
import { FormsModule,ReactiveFormsModule} from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [DeliveryChallanCancellationApprovalComponent,
                 ImplementsAndAccessoriesComponent,
                 DeliveryInsuranceDetailsComponent,
                 DeliveryProspectDetailsComponent,
                 DeliveryChallanCreateComponent,
                 DeliverableChecklistComponent,
                 DeliveryChallanComponent,
                 ApprovalDetailsComponent],
  imports: [
    CommonModule,
    DeliveryChallanCancellationApprovalRoutingModule,
    EditableTableModule,
    NgswSearchTableModule,
    MaterialModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    UiModule
  ],
  providers:[DeliveryChallanCreateService]
})
export class DeliveryChallanCancellationApprovalModule { }
