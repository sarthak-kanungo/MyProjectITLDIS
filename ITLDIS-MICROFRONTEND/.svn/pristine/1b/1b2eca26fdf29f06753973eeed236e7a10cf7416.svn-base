import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreDeliveryChecklistRoutingModule } from './pre-delivery-checklist-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PreDeliveryCheckComponent } from './component/pre-delivery-check/pre-delivery-check.component';
import { PdcChecklistComponent } from './component/pdc-checklist/pdc-checklist.component';
import { PdcSearchPageComponent } from './component/pdc-search-page/pdc-search-page.component';
import { PdcSearchComponent } from './component/pdc-search/pdc-search.component';
import { PdcPageComponent } from './component/pdc-page/pdc-page.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';


@NgModule({
  declarations: [PreDeliveryCheckComponent, PdcChecklistComponent, PdcSearchPageComponent, PdcSearchComponent, PdcPageComponent],
  imports: [
    CommonModule,
    PreDeliveryChecklistRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  providers: [
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}]
})
export class PreDeliveryChecklistModule { }
