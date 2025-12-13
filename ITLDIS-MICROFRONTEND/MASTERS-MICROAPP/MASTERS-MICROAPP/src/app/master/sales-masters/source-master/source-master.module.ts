import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SourceMasterRoutingModule } from './source-master-routing.module';
import { SearchSourceComponent } from './component/search-source/search-source.component';
import { AddNewSourceComponent } from './component/add-new-source/add-new-source.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SourceSearchPageComponent } from './component/source-search-page/source-search-page.component';
import { SourcePageComponent } from './component/source-page/source-page.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { SourceCommonWebService } from './service/source-common-web.service';


@NgModule({
  declarations: [ SearchSourceComponent, AddNewSourceComponent, SourceSearchPageComponent, SourcePageComponent],
  imports: [
    CommonModule,
    SourceMasterRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
  ],
  providers: [SourceCommonWebService]
})
export class SourceMasterModule { }
