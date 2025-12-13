import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartIssueSearchPageComponent } from './component/part-issue-search-page/part-issue-search-page.component';
import { PartIssuePageComponent } from './component/part-issue-page/part-issue-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:PartIssueSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:PartIssuePageComponent, canActivate: [UserAccessGuard]},
  {path:'view/:viewId', component:PartIssuePageComponent, canActivate: [UserAccessGuard]},
  {path:'update/:updateId', component:PartIssuePageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartIssueRoutingModule { }
