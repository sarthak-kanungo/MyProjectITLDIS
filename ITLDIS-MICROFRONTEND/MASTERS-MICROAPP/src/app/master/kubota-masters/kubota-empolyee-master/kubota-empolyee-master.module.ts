import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { itldisEmpolyeeMasterRoutingModule } from './itldis-empolyee-master-routing.module';
import { itldisEmpolyeeMasterCreateComponent } from './pages/itldis-empolyee-master-create/itldis-empolyee-master-create.component';
import { itldisEmpolyeeMasterSearchComponent } from './pages/itldis-empolyee-master-search/itldis-empolyee-master-search.component';
import { EmpolyeeDetailsComponent } from './component/empolyee-details/empolyee-details.component';
import { itldisEmpolyeeSearchResultComponent } from './component/itldis-empolyee-search-result/itldis-empolyee-search-result.component';
import { SearchitldisEmpolyeeComponent } from './component/search-itldis-empolyee/search-itldis-empolyee.component';
import { ReportingEmployeeDetailsComponent } from './component/reporting-employee-details/reporting-employee-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { UiModule } from '../../../ui/ui.module';
import { OrghierarchyModalComponent } from './orghierarchy-Modal/orghierarchy-modal/orghierarchy-modal.component';

@NgModule({
  declarations: [itldisEmpolyeeMasterCreateComponent, itldisEmpolyeeMasterSearchComponent, EmpolyeeDetailsComponent, itldisEmpolyeeSearchResultComponent, SearchitldisEmpolyeeComponent, ReportingEmployeeDetailsComponent, OrghierarchyModalComponent],
  imports: [
    CommonModule,
    itldisEmpolyeeMasterRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    UiModule,
    DynamicTableModule,
    NgswSearchTableModule,
    EditableTableModule,
    
  ],
  entryComponents: [
    OrghierarchyModalComponent,
  ],
})
export class itldisEmpolyeeMasterModule { }
