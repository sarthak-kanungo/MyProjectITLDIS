import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineServiceHistoryRoutingModule } from './machine-service-history-routing.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { MachineServiceHistoryComponent } from './component/machine-service-history.component';
import { MachineServiceHistoryService } from './service/machine-service-history.service';


@NgModule({
  declarations: [MachineServiceHistoryComponent],
  imports: [
    CommonModule,
    MachineServiceHistoryRoutingModule,
    NgswSearchTableModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule
  ],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }, MachineServiceHistoryService
  ]
})
export class MachineServiceHistoryModule { }
