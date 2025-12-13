import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DealerMasterRoutingModule } from './dealer-master-routing.module';
import { DealerMasterCreateComponent } from './component/dealer-master-create/dealer-master-create.component';
import { DealerMasterSearchComponent } from './component/dealer-master-search/dealer-master-search.component';
import { AddressComponent } from './component/address/address.component';
import { DealerAddressComponent } from './component/dealer-address/dealer-address.component';
import { DealerContactDetailsComponent } from './component/dealer-contact-details/dealer-contact-details.component';
import { DealerMasterDetailsComponent } from './component/dealer-master-details/dealer-master-details.component';
import { DealerSearchResultComponent } from './component/dealer-search-result/dealer-search-result.component';
import { DealerTypeComponent } from './component/dealer-type/dealer-type.component';
import { KaiRepresentativesComponent } from './component/kai-representatives/kai-representatives.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchDealerComponent } from './component/search-dealer/search-dealer.component';
import { UiModule } from '../../../ui/ui.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DealerBankComponent } from './component/dealer-bank/dealer-bank.component';

@NgModule({
  declarations: [DealerMasterCreateComponent, DealerMasterSearchComponent, AddressComponent, DealerAddressComponent, DealerContactDetailsComponent, DealerMasterDetailsComponent, DealerSearchResultComponent, DealerTypeComponent, KaiRepresentativesComponent, SearchDealerComponent, DealerBankComponent],
  imports: [
    CommonModule,
    DealerMasterRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    UiModule
  ]
})
export class DealerMasterModule { }
