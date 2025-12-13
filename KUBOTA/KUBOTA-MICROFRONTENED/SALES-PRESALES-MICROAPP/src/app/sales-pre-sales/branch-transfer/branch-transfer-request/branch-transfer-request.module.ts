import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferRequestRoutingModule } from './branch-transfer-request-routing.module';
import { BranchTransferRequestSearchComponent } from './pages/branch-transfer-request-search/branch-transfer-request-search.component';
import { BranchTransferRequestCreateComponent } from './pages/branch-transfer-request-create/branch-transfer-request-create.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CreateBranchTransferRequestComponent } from './component/create-branch-transfer-request/create-branch-transfer-request.component';
import { MaterialModule } from '../../../app.module';
import { SearchBranchTransferRequestComponent } from './component/search-branch-transfer-request/search-branch-transfer-request.component';
import { CreateBranchTransferRequestContainerComponent } from './component/create-branch-transfer-request-container/create-branch-transfer-request-container.component';
import { BranchTransReqItemDetailsContainerComponent } from './component/branch-trans-req-item-details-container/branch-trans-req-item-details-container.component';
import { BranchTransferReqItemDetailsComponent } from './component/branch-transfer-req-item-details/branch-transfer-req-item-details.component';


@NgModule({
  declarations: [BranchTransferRequestSearchComponent, BranchTransferRequestCreateComponent, CreateBranchTransferRequestComponent,SearchBranchTransferRequestComponent, CreateBranchTransferRequestContainerComponent, BranchTransReqItemDetailsContainerComponent, BranchTransferReqItemDetailsComponent],
  imports: [
    CommonModule,
    BranchTransferRequestRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
  ]
})
export class BranchTransferRequestModule { }
