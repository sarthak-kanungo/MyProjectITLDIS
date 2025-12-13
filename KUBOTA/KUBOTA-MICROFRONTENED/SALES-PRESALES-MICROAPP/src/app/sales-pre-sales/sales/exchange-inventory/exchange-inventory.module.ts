import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ExchangeInventoryRoutingModule } from './exchange-inventory-routing.module';
import { ExchangeInventoryCreateSaleComponent } from './pages/exchange-inventory-create-sale/exchange-inventory-create-sale.component';
import { ExchangeInventorySearchComponent } from './pages/exchange-inventory-search/exchange-inventory-search.component';
import { ExchangeInventorySaleComponent } from './component/exchange-inventory-sale/exchange-inventory-sale.component';
import { SearchExchangeInventoryComponent } from './component/search-exchange-inventory/search-exchange-inventory.component';
import { SearchExchangeInventoryService } from './component/search-exchange-inventory/search-exchange-inventory.service';
import { SalePopUpComponent } from './sale-pop-up/sale-pop-up.component';


@NgModule({
  declarations: [ExchangeInventoryCreateSaleComponent, ExchangeInventorySearchComponent, 
    ExchangeInventorySaleComponent, SearchExchangeInventoryComponent, SalePopUpComponent],
  imports: [
    CommonModule,
    ExchangeInventoryRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule
  ],
  providers: [{
    provide: DateAdapter, useClass: AppDateAdapter
  },
  {
    provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
  },
  SearchExchangeInventoryService
  ],
  entryComponents: [SalePopUpComponent]

})

export class ExchangeInventoryModule { }
