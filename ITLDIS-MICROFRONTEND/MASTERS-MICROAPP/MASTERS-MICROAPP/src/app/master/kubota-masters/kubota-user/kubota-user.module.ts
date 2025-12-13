import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { itldisUserRoutingModule } from './itldis-user-routing.module';
import { AssignFunctionComponent } from './component/assign-function/assign-function.component';
import { CreateNewUserComponent } from './component/create-new-user/create-new-user.component';
import { itldisUserSearchResultComponent } from './component/itldis-user-search-result/itldis-user-search-result.component';
import { SearchitldisUserComponent } from './component/search-itldis-user/search-itldis-user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../../app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { itldisUserSearchPageComponent } from './component/itldis-user-search-page/itldis-user-search-page.component';
import { itldisUserCreatePageComponent } from './component/itldis-user-create-page/itldis-user-create-page.component';


@NgModule({
  declarations: [ AssignFunctionComponent, CreateNewUserComponent, itldisUserSearchResultComponent, SearchitldisUserComponent, itldisUserSearchPageComponent, itldisUserCreatePageComponent],
  imports: [
    CommonModule,
    itldisUserRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule
  ]
})
export class itldisUserModule { }
