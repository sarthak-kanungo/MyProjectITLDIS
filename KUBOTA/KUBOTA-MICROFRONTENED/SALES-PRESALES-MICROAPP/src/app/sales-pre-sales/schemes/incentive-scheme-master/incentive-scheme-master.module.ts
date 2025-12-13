import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IncentiveSchemeMasterRoutingModule } from './incentive-scheme-master-routing.module';
import { IncentiveSchemeMasterSearchComponent } from './pages/incentive-scheme-master-search/incentive-scheme-master-search.component';
import { IncentiveSchemeMasterCreateComponent } from './pages/incentive-scheme-master-create/incentive-scheme-master-create.component';
import { MaterialModule } from '../../../app.module';
import { CreateIncentiveSchemeMasterComponent } from './component/create-incentive-scheme-master/create-incentive-scheme-master.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DealersComponent } from './component/dealers/dealers.component';
import { IncentiveSchemeMasterSearchCriteriaComponent } from './component/incentive-scheme-master-search-criteria/incentive-scheme-master-search-criteria.component';
import { SchemesCommonServiceModule } from '../schemes-common-service/schemes-common-service.module';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from 'src/app/date.adapter';

@NgModule({
  declarations: [IncentiveSchemeMasterSearchComponent, IncentiveSchemeMasterCreateComponent, CreateIncentiveSchemeMasterComponent, DealersComponent, IncentiveSchemeMasterSearchCriteriaComponent],
  imports: [
    CommonModule,
    IncentiveSchemeMasterRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule, 
    SchemesCommonServiceModule ,
    DynamicTableModule,
    NgswSearchTableModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class IncentiveSchemeMasterModule { }
