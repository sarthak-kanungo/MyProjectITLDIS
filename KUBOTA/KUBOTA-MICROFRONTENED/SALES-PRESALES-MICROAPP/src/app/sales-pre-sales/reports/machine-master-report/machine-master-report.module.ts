import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MaterialModule } from "src/app/app.module";
import { MachineMasterReportRoutingModule } from "./machine-master-report-routing.module";
import { MachineMasterReportPageComponent } from './component/machine-master-report-page/machine-master-report-page.component';
import { MachineMasterReportTableComponent } from './component/machine-master-report-table/machine-master-report-table.component';
import { SalesReportService } from "../sales-report-service/sales-report.service";
@NgModule({
    declarations: [MachineMasterReportPageComponent, MachineMasterReportTableComponent],
    imports: [
      CommonModule,
      MachineMasterReportRoutingModule,
      NgswSearchTableModule,
      FormsModule,
      ReactiveFormsModule,
      MaterialModule,
    ],providers:[SalesReportService]
  })
  export class MachineMasterReportModule { }