import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PreDeliveryInspectionRoutingModule } from './pre-delivery-inspection-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PdiComponent } from './component/pdi/pdi.component';
import { PdiChecklistComponent } from './component/pdi-checklist/pdi-checklist.component';
import { CreatePdiComponent } from './component/pdi-page/create-pdi.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';

import { EditableTableModule } from 'editable-table';
import { PdiSearchComponent } from './component/pdi-search/pdi-search.component';
import { PdiSearchPageComponent } from './component/pdi-search-page/pdi-search-page.component';
import { MAT_DATE_LOCALE } from '@angular/material';
import { UiModule } from '../../../ui/ui.module';



@NgModule({
  declarations: [PdiComponent, PdiChecklistComponent, CreatePdiComponent, PdiSearchComponent, PdiSearchPageComponent],
  imports: [
    CommonModule,
    PreDeliveryInspectionRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  providers: [{ provide: MAT_DATE_LOCALE, useValue: 'en-GB' }]

})
export class PreDeliveryInspectionModule { }
