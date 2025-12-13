import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ComplaintOrQueryResolutionRoutingModule } from './complaint-or-query-resolution-routing.module';
import { ComplaintOrQueryResolutionCreatePageComponent } from './component/complaint-or-query-resolution-create-page/complaint-or-query-resolution-create-page.component';
import { ComplaintOrQueryResolutionSearchPageComponent } from './component/complaint-or-query-resolution-search-page/complaint-or-query-resolution-search-page.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { SearchComplaintOrQueryResolutionComponent } from './component/search-complaint-or-query-resolution/search-complaint-or-query-resolution.component';
import { ComplaintResolutionPopupComponent } from './component/complaint-resolution-popup/complaint-resolution-popup.component';
import { ComplaintAssignPopupComponent } from '../common-utility/component/complaint-assign-popup/complaint-assign-popup.component';


@NgModule({
  declarations: [ComplaintOrQueryResolutionCreatePageComponent, ComplaintOrQueryResolutionSearchPageComponent, SearchComplaintOrQueryResolutionComponent, ComplaintResolutionPopupComponent],
  imports: [
    CommonModule,
    ComplaintOrQueryResolutionRoutingModule,
    NgswSearchTableModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  entryComponents : [ ComplaintResolutionPopupComponent ]
})
export class ComplaintOrQueryResolutionModule { }
