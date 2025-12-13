import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LogSheetRoutingModule } from './log-sheet-routing.module';
import { LogSheetPageComponent } from './component/log-sheet-page/log-sheet-page.component';
import { LogSheetComponent } from './component/log-sheet/log-sheet.component';
import { LogSheetSearchPageComponent } from './component/log-sheet-search-page/log-sheet-search-page.component';
import { LogSheetSearchComponent } from './component/log-sheet-search/log-sheet-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LogSheetImplementComponent } from './component/log-sheet-implement/log-sheet-implement.component';
import { LogSheetServiceHistoryComponent } from './component/log-sheet-service-history/log-sheet-service-history.component';
import { LogSheetFailurePartsComponent } from './component/log-sheet-failure-parts/log-sheet-failure-parts.component';
import { LogSheetDealerComponent } from './component/log-sheet-dealer/log-sheet-dealer.component';
import { LogSheetConcernComponent } from './component/log-sheet-concern/log-sheet-concern.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { LogSheetUploadfileComponent } from './component/log-sheet-uploadfile/log-sheet-uploadfile.component';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { ToastrModule } from 'ngx-toastr';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [LogSheetPageComponent, LogSheetComponent, LogSheetSearchPageComponent, LogSheetSearchComponent, LogSheetImplementComponent, LogSheetServiceHistoryComponent, LogSheetFailurePartsComponent, LogSheetDealerComponent, LogSheetConcernComponent, LogSheetUploadfileComponent],
  imports: [
    CommonModule,
    LogSheetRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ConfirmationBoxModule,
    ToastrModule,
    UiModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class LogSheetModule { }
