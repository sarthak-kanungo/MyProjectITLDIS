import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DealerEmployeeMasterRoutingModule } from './dealer-employee-master-routing.module';
import { DealerEmployeeMasterCreateComponent } from './pages/dealer-employee-master-create/dealer-employee-master-create.component';
import { DealerEmployeeMasterSearchComponent } from './pages/dealer-employee-master-search/dealer-employee-master-search.component';
import { DealerEmployeeSearchResultComponent } from './component/dealer-employee-search-result/dealer-employee-search-result.component';
import { EmployeeComponent } from './component/employee/employee.component';
import { EmployeeAddressComponent } from './component/employee-address/employee-address.component';
import { EmployeeMasterComponent } from './component/employee-master/employee-master.component';
import { EmploymentComponent } from './component/employment/employment.component';
import { FamilyDetailsComponent } from './component/family-details/family-details.component';
import { SearchDealerEmployeeComponent } from './component/search-dealer-employee/search-dealer-employee.component';
import { WorkExperienceComponent } from './component/work-experience/work-experience.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { EmployeeMasterCreatePageComponent } from './component/employee-master-create-page/employee-master-create-page.component';
import { EmployeeMasterSearchPageComponent } from './component/employee-master-search-page/employee-master-search-page.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../date.adapter';
import { NgswSearchTableModule } from 'ngsw-search-table';


@NgModule({
  declarations: [DealerEmployeeMasterCreateComponent, DealerEmployeeMasterSearchComponent, DealerEmployeeSearchResultComponent, EmployeeComponent, EmployeeAddressComponent, EmployeeMasterComponent, EmploymentComponent, FamilyDetailsComponent, SearchDealerEmployeeComponent, WorkExperienceComponent, EmployeeMasterCreatePageComponent, EmployeeMasterSearchPageComponent],
  imports: [
    CommonModule,
    DealerEmployeeMasterRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class DealerEmployeeMasterModule { }
