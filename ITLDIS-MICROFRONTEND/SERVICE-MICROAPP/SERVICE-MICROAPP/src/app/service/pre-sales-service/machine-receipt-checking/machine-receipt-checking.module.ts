import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MachineReceiptCheckingRoutingModule } from './machine-receipt-checking-routing.module';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MrcDetailsComponent } from './component/mrc-details-page/mrc-details.component';
import { CheckPointComponent } from './component/check-point/check-point.component';
import { PhotosComponent } from './component/photos/photos.component';
import { MaterialModule } from '../../../app.module';
import { DefectShortageAndDamageComponent } from './component/defect-shortage-and-damage/defect-shortage-and-damage.component';
import { BasicMrcDetailsComponent } from './component/basic-mrc-details/basic-mrc-details.component';
import { MrcSearchPageComponent } from './component/mrc-search-page/mrc-search-page.component';
import { MrcSearchComponent } from './component/mrc-search/mrc-search.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { DefectShortageDamageTableComponent } from './component/defect-shortage-damage-table/defect-shortage-damage-table.component';
import { UiModule } from '../../../ui/ui.module';



@NgModule({
  declarations: [MrcDetailsComponent, CheckPointComponent, PhotosComponent, DefectShortageAndDamageComponent, BasicMrcDetailsComponent, MrcSearchPageComponent, MrcSearchComponent, DefectShortageDamageTableComponent],
  imports: [
    CommonModule,
    MachineReceiptCheckingRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class MachineReceiptCheckingModule { }
