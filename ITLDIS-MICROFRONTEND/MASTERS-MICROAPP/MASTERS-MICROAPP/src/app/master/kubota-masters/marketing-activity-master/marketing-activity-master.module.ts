import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MarketingActivityMasterRoutingModule } from './marketing-activity-master-routing.module';
import { MarketingActivityMasterSearchComponent } from './pages/marketing-activity-master-search/marketing-activity-master-search.component';


@NgModule({
  declarations: [MarketingActivityMasterSearchComponent],
  imports: [
    CommonModule,
    MarketingActivityMasterRoutingModule
  ]
})
export class MarketingActivityMasterModule { }
