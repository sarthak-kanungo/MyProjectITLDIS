import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineTransportRoutingModule } from './machine-transport-routing.module';
import { CreateMachineTransportComponent } from './create-machine-transport/create-machine-transport.component';
import { SearchMachineTransportComponent } from './search-machine-transport/search-machine-transport.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { EditableTableModule } from 'editable-table';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from 'src/app/ui/ui.module';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SelfRunComponent } from './self-run/self-run.component';
import { TransportComponent } from './transport/transport.component';
import { SelfTruckComponent } from './self-truck/self-truck.component';
import {MatCheckboxModule} from '@angular/material/checkbox';

@NgModule({
  declarations: [CreateMachineTransportComponent, SearchMachineTransportComponent, SelfRunComponent, TransportComponent, SelfTruckComponent],
  imports: [
    CommonModule,
    MachineTransportRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    EditableTableModule,
    NgswSearchTableModule,
    UiModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule
  ]
})
export class MachineTransportModule { }
