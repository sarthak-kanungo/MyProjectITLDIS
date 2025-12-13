import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { RouterModule, Routes } from '@angular/router';
import { PickListPageComponent } from './pick-list-page/pick-list-page.component';
import { PicklistSearchPageComponent } from './picklist-search-page/picklist-search-page.component';

const routes: Routes = [
    { path: '', component: PicklistSearchPageComponent , canActivate: [UserAccessGuard] },
    { path: 'create', component: PickListPageComponent, canActivate: [UserAccessGuard] },
    { path: 'view', component: PickListPageComponent, canActivate: [UserAccessGuard] },
    { path: 'edit', component: PickListPageComponent, canActivate: [UserAccessGuard] }
  ];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class PickListRoutingModule { }
