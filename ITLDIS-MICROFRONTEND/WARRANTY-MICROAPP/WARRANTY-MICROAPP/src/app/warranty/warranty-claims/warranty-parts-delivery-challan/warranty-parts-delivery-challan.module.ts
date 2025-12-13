import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WarrantyPartsDeliveryChallanRoutingModule } from './warranty-parts-delivery-challan-routing.module';
import { WpdcComponent } from './component/wpdc/wpdc.component';
import { WpdcMaterialDetailsComponent } from './component/wpdc-material-details/wpdc-material-details.component';
import { WpdcTransporterDetailsComponent } from './component/wpdc-transporter-details/wpdc-transporter-details.component';
import { WpdcPageComponent } from './component/wpdc-page/wpdc-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ConfirmationBoxModule } from '../../../confirmation-box/confirmation-box.module';
import { WpdcSearchComponent } from './component/wpdc-search/wpdc-search.component';
import { WpdcSearchPageComponent } from './component/wpdc-search-page/wpdc-search-page.component';
import { NumberToWordsPipe } from '../../../pipe/number-to-words.pipe';
import { ToastrModule } from 'ngx-toastr';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { UiModule } from '../../../ui/ui.module';

@NgModule({
  declarations: [WpdcComponent, WpdcMaterialDetailsComponent, WpdcTransporterDetailsComponent, WpdcPageComponent, WpdcSearchComponent, WpdcSearchPageComponent, NumberToWordsPipe],
  imports: [
    CommonModule,
    WarrantyPartsDeliveryChallanRoutingModule,
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
export class WarrantyPartsDeliveryChallanModule { }
