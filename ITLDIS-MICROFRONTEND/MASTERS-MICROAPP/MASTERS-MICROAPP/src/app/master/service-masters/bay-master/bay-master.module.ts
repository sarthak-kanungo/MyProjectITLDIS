import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BayMasterRoutingModule } from './bay-master-routing.module';
import { BayMasterComponent } from './pages/bay-master/bay-master.component';
import { SearchBayComponent } from './component/search-bay/search-bay.component';
import { AddBayComponent } from './component/add-bay/add-bay.component';
import { BaySearchResultComponent } from './component/bay-search-result/bay-search-result.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [BayMasterComponent, SearchBayComponent, AddBayComponent, BaySearchResultComponent],
  imports: [
    CommonModule,
    BayMasterRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class BayMasterModule { }
