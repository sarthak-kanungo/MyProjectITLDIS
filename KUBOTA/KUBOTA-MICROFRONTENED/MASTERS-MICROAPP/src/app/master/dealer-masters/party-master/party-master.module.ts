import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PartyMasterRoutingModule } from './party-master-routing.module';
import { PartyAddressComponent } from './component/party-address/party-address.component';
import { PartyDetailsComponent } from './component/party-details/party-details.component';
import { PartySearchResultComponent } from './component/party-search-result/party-search-result.component';
import { SearchPartyComponent } from './component/search-party/search-party.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { PartyMasterSearchPageComponent } from './component/party-master-search-page/party-master-search-page.component';
import { PartyMasterPageComponent } from './component/party-master-page/party-master-page.component';
import { NgswSearchTableModule } from 'projects/ngsw-search-table/src/public-api';


@NgModule({
  declarations: [PartyAddressComponent, PartyDetailsComponent, PartySearchResultComponent, SearchPartyComponent, PartyMasterSearchPageComponent, PartyMasterPageComponent],
  imports: [
    CommonModule,
    PartyMasterRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
  NgswSearchTableModule
  ]
})
export class PartyMasterModule { }
