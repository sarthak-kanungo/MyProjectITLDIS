import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DescripancyClaimRoutingModule } from './descripancy-claim-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { MachineDescripancyClaimComponent } from './pages/machine-descripancy-claim/machine-descripancy-claim.component';
import { CreateMachineDescripancyClaimComponent } from './pages/create-machine-descripancy-claim/create-machine-descripancy-claim.component';
import { MachineDescripancyClaimCreateComponent } from './component/machine-descripancy-claim-create/machine-descripancy-claim-create.component';
import { SearchMachineDescripancyClaimComponent } from './component/search-machine-descripancy-claim/search-machine-descripancy-claim.component';
import { SearchDescripancyClaimApprovalComponent } from './component/search-descripancy-claim-approval/search-descripancy-claim-approval.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ApprovalDetailsComponent } from './component/approval-details/approval-details.component';


@NgModule({
  declarations: [SearchMachineDescripancyClaimComponent,MachineDescripancyClaimComponent, CreateMachineDescripancyClaimComponent, MachineDescripancyClaimCreateComponent,SearchDescripancyClaimApprovalComponent, ApprovalDetailsComponent],
  imports: [
    CommonModule,
    DescripancyClaimRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    DynamicTableModule,
    NgswSearchTableModule
  ]
})
export class DescripancyClaimModule { }
