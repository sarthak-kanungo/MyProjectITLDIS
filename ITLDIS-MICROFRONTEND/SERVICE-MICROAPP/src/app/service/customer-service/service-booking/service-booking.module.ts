import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServiceBookingRoutingModule } from './service-booking-routing.module';;
import { ServiceBookingSearchComponent } from './component/service-booking-search/service-booking-search.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BookingDetailsComponent } from './component/booking-details/booking-details.component';
import { CustomerMachineDetailsComponent } from './component/customer-machine-details/customer-machine-details.component';
import { ViewJobCardDetailsComponent } from './component/view-job-card-details/view-job-card-details.component';
import { ServiceBookingSearchPageComponent } from './component/service-booking-search-page/service-booking-search-page.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { ServiceBookingPageComponent } from './component/service-booking-page/service-booking-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { BookingCancellationComponent } from './component/booking-cancellation/booking-cancellation.component';
import { ServiceBookingCommonWebService } from './service/service-booking-common-web.service';
import { UiModule } from '../../../ui/ui.module';
import { HoursPopUpServiceBookingComponent } from './component/hours-pop-up/hours-pop-up-service-booking.component';

@NgModule({
  declarations: [ServiceBookingSearchComponent, HoursPopUpServiceBookingComponent, BookingDetailsComponent, CustomerMachineDetailsComponent, ViewJobCardDetailsComponent, ServiceBookingSearchPageComponent, ServiceBookingPageComponent, BookingCancellationComponent,],
  imports: [
    CommonModule,
    ServiceBookingRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule
  ],
  entryComponents: [HoursPopUpServiceBookingComponent],
  providers: [{ provide: DateAdapter, useClass: AppDateAdapter },
  { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }, ServiceBookingCommonWebService]
})
export class ServiceBookingModule { }
