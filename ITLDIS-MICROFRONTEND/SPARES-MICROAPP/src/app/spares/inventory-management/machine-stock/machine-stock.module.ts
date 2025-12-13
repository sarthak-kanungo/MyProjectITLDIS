import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ToastrModule } from "ngx-toastr";
import { MaterialModule } from "src/app/app.module";
import { DynamicTableModule } from "src/app/ui/dynamic-table/dynamic-table.module";
import { UiModule } from "src/app/ui/ui.module";
import { MachineStockSearchPageComponent } from "./component/machine-stock-search-page/machine-stock-search-page.component";
import { MachineStockSearchComponent } from "./component/machine-stock-search/machine-stock-search.component";
import { MachineStockRoutingModule } from "./machine-stock-routing.module";
import { NgswSearchTableModule } from 'ngsw-search-table';
import { DateAdapter, MAT_DATE_FORMATS } from "@angular/material/core";
import { AppDateAdapter, APP_DATE_FORMATS } from "src/app/date.adapter";

@NgModule({
    declarations: [MachineStockSearchPageComponent,
        MachineStockSearchComponent
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        MaterialModule,
        FormsModule,
        DynamicTableModule,
        ToastrModule,
        UiModule,
        MachineStockRoutingModule,
        NgswSearchTableModule
    ],

    providers: [
        {
            provide:DateAdapter,useClass:AppDateAdapter
        },
        {
            provide:MAT_DATE_FORMATS,useValue:APP_DATE_FORMATS
        }
    ]
})
export class MachineStockModule { }