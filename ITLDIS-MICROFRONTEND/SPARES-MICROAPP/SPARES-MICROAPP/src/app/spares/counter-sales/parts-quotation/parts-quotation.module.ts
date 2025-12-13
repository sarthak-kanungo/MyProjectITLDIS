import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PartsQuotationRoutingModule } from './parts-quotation-routing.module';
import { PartQuotationComponent } from './component/part-quotation/part-quotation.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PartQuotationTableComponent } from './component/part-quotation-table/part-quotation-table.component';
import { PartQuotationSearchComponent } from './component/part-quotation-search/part-quotation-search.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { PartQuotationPageComponent } from './component/part-quotation-page/part-quotation-page.component';
import { PartQuotationSearchPageComponent } from './component/part-quotation-search-page/part-quotation-search-page.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { PartQuotationCommonWebService } from './service/part-quotation-common-web.service';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [PartQuotationComponent, PartQuotationTableComponent, PartQuotationSearchComponent, PartQuotationPageComponent, PartQuotationSearchPageComponent],
  imports: [
    CommonModule,
    PartsQuotationRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },PartQuotationCommonWebService]
})
export class PartsQuotationModule { }
