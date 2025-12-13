import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ActualReportRoutingModule } from './actual-report-routing.module';
import { ActualReportSearchComponent } from './pages/actual-report-search/actual-report-search.component';
import { ActualReportCreateComponent } from './pages/actual-report-create/actual-report-create.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivityReportSearchTableComponent } from './component/activity-report-search-table/activity-report-search-table.component';
import { ActivityReportCreateComponent } from './component/activity-report-create/activity-report-create.component';
import { EnquiryDetailsComponent } from './component/enquiry-details/enquiry-details.component';
import { ActivityReportSearchTableContainerComponent } from './component/activity-report-search-table-container/activity-report-search-table-container.component';
import { ActivityReportCommonService } from './component/activity-report-create/activity-report-common.service';
import { ActualReportCreatePageService } from './pages/actual-report-create/actual-report-create-page.service';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from '../../../ui/ui.module';
import { ImagePreviewComponent } from 'src/app/ui/image-preview/image-preview.component';
import { MatDialogModule } from '@angular/material';
@NgModule({
  declarations: [ActualReportSearchComponent, ActualReportCreateComponent, ActivityReportSearchTableComponent, ActivityReportCreateComponent, EnquiryDetailsComponent, ActivityReportSearchTableContainerComponent],
  imports: [
    CommonModule,
    ActualReportRoutingModule,
    MaterialModule, FormsModule, ReactiveFormsModule,
    DynamicTableModule,
    NgswSearchTableModule,
    UiModule, MatDialogModule
   ],
  providers : [ActivityReportCommonService, ActualReportCreatePageService],
  entryComponents:[ImagePreviewComponent]
})
export class ActivityReportModule { }
