import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JobCardCreateComponent } from './component/create-job-card-page/create-job-card-page.component';
import { SearchJobCardPageComponent } from './component/search-job-card-page/search-job-card-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SearchJobCardPageComponent, canActivate:[UserAccessGuard] },
  { path: 'create', component: JobCardCreateComponent, canActivate:[UserAccessGuard] },
  { path: 'view', component: JobCardCreateComponent, canActivate:[UserAccessGuard] },
  { path: 'pdi', component: JobCardCreateComponent, canActivate:[UserAccessGuard] },
  { path: 'pdc', component: JobCardCreateComponent, canActivate:[UserAccessGuard] },
  { path: 'edit', component: JobCardCreateComponent, canActivate:[UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class JobCardRoutingModule { }
