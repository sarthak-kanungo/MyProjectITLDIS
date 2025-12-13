import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from 'src/app/app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { UiModule } from 'src/app/ui/ui.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { MachineFormfComponent } from './machine-formf.component';
import { MachineFormfRoutingModule } from './machine-formf-routing.module';



@NgModule({
  declarations: [MachineFormfComponent],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    DynamicTableModule,
    UiModule,
    MachineFormfRoutingModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class MachineFormfModule { }
