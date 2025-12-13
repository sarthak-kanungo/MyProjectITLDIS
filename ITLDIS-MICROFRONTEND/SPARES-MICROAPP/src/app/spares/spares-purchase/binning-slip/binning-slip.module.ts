import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BinningSlipRoutingModule } from './binning-slip-routing.module';
import { BinningSlipComponent } from './component/binning-slip/binning-slip.component';
import { BinningSlipContainerCreateComponent } from './container/binning-slip-container-create/binning-slip-container-create.component';
import { BinningSlipContainerSearchComponent } from './container/binning-slip-container-search/binning-slip-container-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WebserviceConfigModule } from '../../../webservice-config/webservice-config.module';
import { BinningSlipTableComponent } from './component/binning-slip-table/binning-slip-table.component';
import { BinningSlipSearchComponent } from './component/binning-slip-search/binning-slip-search.component';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';


@NgModule({
  declarations: [BinningSlipComponent, BinningSlipContainerCreateComponent, BinningSlipContainerSearchComponent, BinningSlipTableComponent, BinningSlipSearchComponent],
  imports: [
    CommonModule,
    BinningSlipRoutingModule,
    MaterialModule,
     FormsModule, 
     ReactiveFormsModule,
     WebserviceConfigModule,
     DynamicTableModule

  ]
})
export class BinningSlipModule { }
