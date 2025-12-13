import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KubotaSourceMasterRoutingModule } from './kubota-source-master-routing.module';
import { KubotaSourceMasterSearchComponent } from './pages/kubota-source-master-search/kubota-source-master-search.component';


@NgModule({
  declarations: [KubotaSourceMasterSearchComponent],
  imports: [
    CommonModule,
    KubotaSourceMasterRoutingModule
  ]
})
export class KubotaSourceMasterModule { }
