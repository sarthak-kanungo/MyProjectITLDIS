import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DeAllotmentRoutingModule } from './de-allotment-routing.module';
import { DeAllotmentCreateComponent } from './pages/de-allotment-create/de-allotment-create.component';
import { DeAllotmentSearchComponent } from './pages/de-allotment-search/de-allotment-search.component';
import { AllotmentDeAllotmentComponent } from './component/allotment-de-allotment/allotment-de-allotment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { SearchAllotmentDeAllotmentComponent } from './component/search-allotment-de-allotment/search-allotment-de-allotment.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { UiModule } from '../../../ui/ui.module';


@NgModule({
  declarations: [
    DeAllotmentCreateComponent,
    DeAllotmentSearchComponent,
    AllotmentDeAllotmentComponent,
    SearchAllotmentDeAllotmentComponent
  ],
  imports: [
    CommonModule,
    DeAllotmentRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    EditableTableModule,
    UiModule
  ]
})
export class DeAllotmentModule { }
