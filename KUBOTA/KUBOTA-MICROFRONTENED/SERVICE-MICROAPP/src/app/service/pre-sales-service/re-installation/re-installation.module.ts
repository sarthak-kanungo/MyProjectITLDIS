import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReInstallationRoutingModule } from './re-installation-routing.module';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ReInstallationDetailsComponent } from './component/re-installation-details/re-installation-details.component';
import { ReInstallationChecklistComponent } from './component/re-installation-checklist/re-installation-checklist.component';
import { ReInstalltionMachineDetailsComponent } from './component/re-installtion-machine-details/re-installtion-machine-details.component';
import { ReInstallationSearchPageComponent } from './component/re-installation-search-page/re-installation-search-page.component';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { ReInstallationSearchComponent } from './component/re-installation-search/re-installation-search.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ReInstallationPageComponent } from './component/re-installation-page/re-installation-page.component';
import { RepresentativeDetailsComponent } from './component/representative-details/representative-details.component';
import { RepresentativeDialogComponent } from './component/representative-dialog/representative-dialog.component';
import { ReInstallationCommonWebService } from './service/re-installation-common-web.service';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [ReInstallationDetailsComponent, ReInstallationChecklistComponent, ReInstalltionMachineDetailsComponent, ReInstallationSearchPageComponent, ReInstallationSearchComponent, ReInstallationPageComponent, RepresentativeDetailsComponent, RepresentativeDialogComponent],
  imports: [
    CommonModule,
    ReInstallationRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule
  ],
  entryComponents: [RepresentativeDialogComponent],
  providers: [ReInstallationCommonWebService,
    {provide: DateAdapter, useClass: AppDateAdapter},
    {provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS}]
})
export class ReInstallationModule { }
