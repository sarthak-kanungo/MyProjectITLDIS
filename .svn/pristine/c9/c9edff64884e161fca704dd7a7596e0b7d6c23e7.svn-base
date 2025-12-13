import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChannelFinanceIndentRoutingModule } from './channel-finance-indent-routing.module';
import { SearchChannelFinanceIndentComponent } from './pages/search-channel-finance-indent/search-channel-finance-indent.component';
import { CreateChannelFinanceIndentComponent } from './pages/create-channel-finance-indent/create-channel-finance-indent.component';
import { GeneralInfoComponent } from './component/general-info/general-info.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { SearchResultChannelFinanceIndentComponent } from './component/search-result-channel-finance-indent/search-result-channel-finance-indent.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { ToastrModule } from 'ngx-toastr';
import { UiModule } from '../../../ui/ui.module';
import { TwoDigitDecimaNumberDirective } from './component/general-info/two-digit-decimal-number-directive.directive';


@NgModule({
  declarations: [SearchChannelFinanceIndentComponent, CreateChannelFinanceIndentComponent, GeneralInfoComponent, SearchResultChannelFinanceIndentComponent,TwoDigitDecimaNumberDirective],
  imports: [
    CommonModule,
    ChannelFinanceIndentRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    NgswSearchTableModule,
    ToastrModule,
    UiModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS },
  ]
})

export class ChannelFinanceIndentModule { }
