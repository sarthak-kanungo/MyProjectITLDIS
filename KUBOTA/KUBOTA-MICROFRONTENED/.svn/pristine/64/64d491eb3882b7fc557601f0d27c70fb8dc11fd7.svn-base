import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarketIntelligenceRoutingModule } from './market-intelligence-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MarketIntelligenceSearchComponent } from './component/market-intelligence-search/market-intelligence-search.component';
import { MarketIntelligenceCreateComponent } from './component/market-intelligence-create/market-intelligence-create.component';
import { UiModule } from 'src/app/ui/ui.module';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';

@NgModule({
  declarations: [MarketIntelligenceSearchComponent, MarketIntelligenceCreateComponent],
  imports: [
    CommonModule,
    MarketIntelligenceRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    UiModule
  ],
  providers : [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }]
})
export class MarketIntelligenceModule { }
