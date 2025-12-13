import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartmentMasterRoutingModule } from './department-master-routing.module';
import { SearchDepartmentComponent } from './components/search-department/search-department.component';
import { AddDepartmentComponent } from './components/add-department/add-department.component';
import { DepartmentMasterComponent } from './pages/department-master/department-master.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DynamicTableModule } from '../../../ui/dynamic-table/dynamic-table.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { EditableTableModule } from 'editable-table';


@NgModule({
  declarations: [SearchDepartmentComponent, AddDepartmentComponent, DepartmentMasterComponent],
  imports: [
    CommonModule,
    DepartmentMasterRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    DynamicTableModule,
    NgswSearchTableModule,
    EditableTableModule,
  ]
})
export class DepartmentMasterModule { }
