import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KaiInspectionSheetRoutingModule } from './kai-inspection-sheet-routing.module';
import { KaiInspectionSheetCreatePageComponent } from './component/kai-inspection-sheet-create-page/kai-inspection-sheet-create-page.component';
import { KaiInspectionSheetSearchPageComponent } from './component/kai-inspection-sheet-search-page/kai-inspection-sheet-search-page.component';
import { KaiInspectionSheetComponent } from './component/kai-inspection-sheet/kai-inspection-sheet.component';
import { KaiInspectionSheetMaterialDetailsComponent } from './component/kai-inspection-sheet-material-details/kai-inspection-sheet-material-details.component';
import { KaiInspectionSheetTransporterDetailsComponent } from './component/kai-inspection-sheet-transporter-details/kai-inspection-sheet-transporter-details.component';
import { KaiInspectionSheetKQuicDetailsComponent } from './component/kai-inspection-sheet-k-quic-details/kai-inspection-sheet-k-quic-details.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { KaiInspectionSheetSearchComponent } from './component/kai-inspection-sheet-search/kai-inspection-sheet-search.component';
import { KaiInspectionSheetLabourchargeComponent } from './component/kai-inspection-sheet-labourcharge/kai-inspection-sheet-labourcharge.component';
import { KaiInspectionSheetOutsideLabourchargeComponent } from './component/kai-inspection-sheet-outside-labourcharge/kai-inspection-sheet-outside-labourcharge.component';
import { KaiInspectionSheetFileUploadComponent } from './component/kai-inspection-sheet-file-upload/kai-inspection-sheet-file-upload.component';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { ToastrModule } from 'ngx-toastr';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';


@NgModule({
  declarations: [KaiInspectionSheetCreatePageComponent, KaiInspectionSheetSearchPageComponent, KaiInspectionSheetComponent, KaiInspectionSheetMaterialDetailsComponent, KaiInspectionSheetTransporterDetailsComponent, KaiInspectionSheetKQuicDetailsComponent, KaiInspectionSheetSearchComponent, KaiInspectionSheetLabourchargeComponent, KaiInspectionSheetOutsideLabourchargeComponent, KaiInspectionSheetFileUploadComponent],
  imports: [
    CommonModule,
    KaiInspectionSheetRoutingModule,
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
  ],
})
export class KaiInspectionSheetModule { }
