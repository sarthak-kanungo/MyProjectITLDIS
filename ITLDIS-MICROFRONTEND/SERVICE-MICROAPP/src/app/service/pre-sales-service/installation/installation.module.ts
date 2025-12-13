import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstallationRoutingModule } from './installation-routing.module';
import { InstallationSearchComponent } from './component/installation-search/installation-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DeliveryInstallationComponent } from './component/delivery-installation/delivery-installation.component';
import { FieldInstallationComponent } from './component/field-installation/field-installation.component';
import { BasicInstallationDetailsComponent } from './component/basic-installation-details/basic-installation-details.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { InstallationSearchPageComponent } from './component/installation-search-page/installation-search-page.component';
import { InstallationPageComponent } from './component/installation-page/installation-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { InstallationCommonWebService } from './service/installation-common-web.service';
import { InstallationPhotosComponent } from './component/photos/installation-photos.component';
import { UiModule } from '../../../ui/ui.module';


@NgModule({
  declarations: [InstallationSearchComponent, DeliveryInstallationComponent, FieldInstallationComponent, BasicInstallationDetailsComponent, InstallationSearchPageComponent, InstallationPageComponent, InstallationPhotosComponent],
  imports: [
    CommonModule,
    InstallationRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    DynamicTableModule,
    UiModule
  ],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
  { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }, InstallationCommonWebService]
})
export class InstallationModule { }
