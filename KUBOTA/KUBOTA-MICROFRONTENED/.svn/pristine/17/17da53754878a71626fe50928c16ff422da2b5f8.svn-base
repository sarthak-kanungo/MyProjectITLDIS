import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViewTransitDetailRoutingModule } from './view-transit-detail-routing.module';
import { SearchViewTrasitDetailPageComponent } from './component/search-view-trasit-detail-page/search-view-trasit-detail-page.component';
import { SearchViewTrasitDetailSearchComponent } from './component/search-view-trasit-detail-search/search-view-trasit-detail-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';


@NgModule({
  declarations: [SearchViewTrasitDetailPageComponent, SearchViewTrasitDetailSearchComponent],
  imports: [
    CommonModule,
    ViewTransitDetailRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,

    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class ViewTransitDetailModule { }
