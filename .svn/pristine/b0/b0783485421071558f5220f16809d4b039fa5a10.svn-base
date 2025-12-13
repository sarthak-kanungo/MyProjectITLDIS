import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrderPlanningSheetRoutingModule } from './order-planning-sheet-routing.module';
import { CreateOrderPlanningSheetComponent } from './create-order-planning-sheet/create-order-planning-sheet.component';
import { OrderPlanningSheetPageComponent } from './order-planning-sheet-page/order-planning-sheet-page.component';
import { SearchOrderPlanningSheetComponent } from './search-order-planning-sheet/search-order-planning-sheet.component';
import { PartDetailOrderPlanningSheetComponent } from './part-detail-order-planning-sheet/part-detail-order-planning-sheet.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { DynamicTableModule } from 'src/app/ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';
import { ScrollingModule } from '@angular/cdk/scrolling';


@NgModule({
  declarations: [CreateOrderPlanningSheetComponent, OrderPlanningSheetPageComponent, SearchOrderPlanningSheetComponent, PartDetailOrderPlanningSheetComponent],
  imports: [
    CommonModule,
    OrderPlanningSheetRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    ScrollingModule,
    DynamicTableModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,

  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class OrderPlanningSheetModule { }
