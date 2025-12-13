import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WcrPartsStatusRoutingModule } from './wcr-parts-status-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { MaterialModule } from 'src/app/app.module';
import { ConfirmationBoxModule } from 'src/app/confirmation-box/confirmation-box.module';
import { UiModule } from 'src/app/ui/ui.module';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { WcrPartsStatusComponent } from './wcr-parts-status.component';


@NgModule({
  declarations: [ WcrPartsStatusComponent ],
  imports: [
    CommonModule,
    WcrPartsStatusRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ConfirmationBoxModule,
    ToastrModule,
    UiModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class WcrPartsStatusModule { }
