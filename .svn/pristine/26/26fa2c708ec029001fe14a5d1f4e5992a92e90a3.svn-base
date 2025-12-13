import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransferEnquiryRoutingModule } from './transfer-enquiry-routing.module';
import { TransferEnquiryComponent } from './pages/transfer-enquiry/transfer-enquiry.component';
import { TransferSearchCriteriaComponent } from './component/transfer-search-criteria/transfer-search-criteria.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';


@NgModule({
  declarations: [TransferEnquiryComponent, TransferSearchCriteriaComponent],
  imports: [
    CommonModule,
    TransferEnquiryRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    DynamicTableModule
  ],
  exports: [TransferEnquiryComponent]
})
export class TransferEnquiryModule { }
