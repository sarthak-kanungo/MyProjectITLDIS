import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { KubotaEmpolyeeMasterRoutingModule } from './kubota-empolyee-master-routing.module';
import { KubotaEmpolyeeMasterCreateComponent } from './pages/kubota-empolyee-master-create/kubota-empolyee-master-create.component';
import { KubotaEmpolyeeMasterSearchComponent } from './pages/kubota-empolyee-master-search/kubota-empolyee-master-search.component';
import { EmpolyeeDetailsComponent } from './component/empolyee-details/empolyee-details.component';
import { KubotaEmpolyeeSearchResultComponent } from './component/kubota-empolyee-search-result/kubota-empolyee-search-result.component';
import { SearchKubotaEmpolyeeComponent } from './component/search-kubota-empolyee/search-kubota-empolyee.component';
import { ReportingEmployeeDetailsComponent } from './component/reporting-employee-details/reporting-employee-details.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { UiModule } from '../../../ui/ui.module';
import { OrghierarchyModalComponent } from './orghierarchy-Modal/orghierarchy-modal/orghierarchy-modal.component';

@NgModule({
  declarations: [KubotaEmpolyeeMasterCreateComponent, KubotaEmpolyeeMasterSearchComponent, EmpolyeeDetailsComponent, KubotaEmpolyeeSearchResultComponent, SearchKubotaEmpolyeeComponent, ReportingEmployeeDetailsComponent, OrghierarchyModalComponent],
  imports: [
    CommonModule,
    KubotaEmpolyeeMasterRoutingModule,
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
export class KubotaEmpolyeeMasterModule { }
