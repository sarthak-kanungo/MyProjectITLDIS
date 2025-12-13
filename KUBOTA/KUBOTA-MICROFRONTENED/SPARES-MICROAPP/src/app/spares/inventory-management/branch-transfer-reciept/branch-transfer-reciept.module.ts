import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BranchTransferRecieptRoutingModule } from './branch-transfer-reciept-routing.module';
import { CreateBranchTransferRecieptComponent } from './component/create-branch-transfer-reciept/create-branch-transfer-reciept.component';
import { SearchBranchTransferRecieptComponent } from './component/search-branch-transfer-reciept/search-branch-transfer-reciept.component';
import { BranchTransferRecieptPageComponent } from './component/branch-transfer-reciept-page/branch-transfer-reciept-page.component';
import { BranchTransferRecieptItemDetailsPageComponent } from './component/branch-transfer-reciept-item-details-page/branch-transfer-reciept-item-details-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DynamicTableModule, MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { BranchTransferRecieptSearchPageComponent } from './component/branch-transfer-reciept-search-page/branch-transfer-reciept-search-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';


@NgModule({
  declarations: [ CreateBranchTransferRecieptComponent, SearchBranchTransferRecieptComponent, BranchTransferRecieptPageComponent, BranchTransferRecieptItemDetailsPageComponent, BranchTransferRecieptSearchPageComponent],
  imports: [
    CommonModule,
    BranchTransferRecieptRoutingModule,
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
export class BranchTransferRecieptModule { }
