import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuotationListRoutingModule } from './quotation-list-routing.module';
import { QuotationListSearchComponent } from './quotation-list-search/quotation-list-search.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchResultsQuotationListComponent } from './component/search-results-quotation-list/search-results-quotation-list.component';


@NgModule({
  declarations: [QuotationListSearchComponent, SearchResultsQuotationListComponent],
  imports: [
    CommonModule,
    QuotationListRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ]
})
export class QuotationListModule { }
