import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductStorageChecksheetRoutingModule } from './product-storage-checksheet-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PscComponent } from './component/psc/psc.component';
import { ChecklistComponent } from './component/checklist/checklist.component';
import { PscPageComponent } from './component/psc-page/psc-page.component';
import { ToastrModule } from 'ngx-toastr';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { PscSearchComponent } from './component/psc-search/psc-search.component';
import { PscSearchPageComponent } from './component/psc-search-page/psc-search-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [PscComponent, ChecklistComponent, PscPageComponent, PscSearchComponent, PscSearchPageComponent],
  imports: [
    CommonModule,
    ProductStorageChecksheetRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },]
})
export class ProductStorageChecksheetModule { }
