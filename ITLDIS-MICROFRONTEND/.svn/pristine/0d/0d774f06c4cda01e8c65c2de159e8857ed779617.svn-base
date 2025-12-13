import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineTransferRequestRoutingModule } from './machine-transfer-request-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { CreateMachineTransferRequestComponent } from './pages/create-machine-transfer-request/create-machine-transfer-request.component';
import { SearchMachineTransferRequestComponent } from './pages/search-machine-transfer-request/search-machine-transfer-request.component';
import { SearchMtrListComponent } from './component/search-mtr-list/search-mtr-list.component';
import { CreateMtrListApprovalComponent } from './component/create-mtr-list-approval/create-mtr-list-approval.component';
import { SearchMtrListContainerComponent } from './component/search-mtr-list-container/search-mtr-list-container.component';

import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { UiModule } from '../../../ui/ui.module';
import { MachineTransferReqItemDetailsComponent } from './component/machine-transfer-req-item-details/machine-transfer-req-item-details.component';
import { MachineTransferRequestCreateComponent } from './component/machine-transfer-request-create/machine-transfer-request-create.component';


@NgModule({
  declarations: [CreateMachineTransferRequestComponent, SearchMachineTransferRequestComponent, MachineTransferRequestCreateComponent, MachineTransferReqItemDetailsComponent, SearchMtrListComponent, CreateMtrListApprovalComponent, SearchMtrListContainerComponent],
  imports: [
    CommonModule,
    MachineTransferRequestRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule


  ],
  exports: [
    MachineTransferReqItemDetailsComponent
  ]
})
export class MachineTransferRequestModule { }
