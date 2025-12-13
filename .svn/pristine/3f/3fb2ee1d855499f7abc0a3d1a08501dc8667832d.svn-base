import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineDescripancyComplaintRoutingModule } from './machine-descripancy-complaint-routing.module';
import { SearchMachineDescripancyComplaintsComponent } from './pages/search-machine-descripancy-complaints/search-machine-descripancy-complaints.component';
import { CreateMachineDescripancyComplaintsComponent } from './pages/create-machine-descripancy-complaints/create-machine-descripancy-complaints.component';
import { MachineDescripancyComplaintsCreateComponent } from './componant/machine-descripancy-complaints-create/machine-descripancy-complaints-create.component';
import { MdComplaintsComponent } from './componant/md-complaints/md-complaints.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchMachineDescripancyComplaintsListComponent } from './componant/search-machine-descripancy-complaints-list/search-machine-descripancy-complaints-list.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MachineDescripancyCommonService } from './componant/machine-descripancy-common.service';
import { ApprovalDetailsComponent } from './componant/approval-details/approval-details.component';




@NgModule({
  declarations: [SearchMachineDescripancyComplaintsComponent, ApprovalDetailsComponent, CreateMachineDescripancyComplaintsComponent, MachineDescripancyComplaintsCreateComponent, MdComplaintsComponent, SearchMachineDescripancyComplaintsListComponent],
  imports: [
    CommonModule,
    MachineDescripancyComplaintRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    NgswSearchTableModule
  ],
  providers: [MachineDescripancyCommonService]
})
export class MachineDescripancyComplaintModule { }
