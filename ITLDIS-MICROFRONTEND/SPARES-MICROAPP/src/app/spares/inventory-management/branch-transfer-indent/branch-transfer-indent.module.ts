import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferIndentRoutingModule } from './branch-transfer-indent-routing.module';
import { CreateBranchIndentComponent } from './component/create-branch-indent/create-branch-indent.component';
import { SearchBranchTransferIndentComponent } from './component/search-branch-transfer-indent/search-branch-transfer-indent.component';
import { BranchTransferPageComponent } from './component/branch-transfer-page/branch-transfer-page.component';
import { IndentItemDetailsPageComponent } from './component/indent-item-details-page/indent-item-details-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';
import { MaterialModule } from '../../../app.module';
import { ModalFileUploadComponent } from './component/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from './component/item-error-report/item-error-report.component';
@NgModule({
  declarations: [ CreateBranchIndentComponent, SearchBranchTransferIndentComponent, BranchTransferPageComponent, IndentItemDetailsPageComponent, ModalFileUploadComponent, ItemErrorReportComponent],
  imports: [
    CommonModule,
    BranchTransferIndentRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,

    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,

  ],
  entryComponents: [ModalFileUploadComponent,ItemErrorReportComponent],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class BranchTransferIndentModule { }
