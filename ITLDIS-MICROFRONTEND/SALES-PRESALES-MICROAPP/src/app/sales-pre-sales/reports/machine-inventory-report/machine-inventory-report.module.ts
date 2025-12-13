import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineInventoryReportRoutingModule } from './machine-inventory-report-routing.module';
import { MachineInventoryReportComponent } from './component/machine-inventory-report/machine-inventory-report.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';


@NgModule({
  declarations: [MachineInventoryReportComponent],
  imports: [
    CommonModule,
    MachineInventoryReportRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
  ]
})
export class MachineInventoryReportModule { }
