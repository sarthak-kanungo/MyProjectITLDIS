import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { ComplaintOrQueryResolutionCreatePageComponent } from './component/complaint-or-query-resolution-create-page/complaint-or-query-resolution-create-page.component';
import { ComplaintOrQueryResolutionSearchPageComponent } from './component/complaint-or-query-resolution-search-page/complaint-or-query-resolution-search-page.component';


const routes: Routes = [
  { path: '', component: ComplaintOrQueryResolutionSearchPageComponent, canActivate: [UserAccessGuard]  },
  { path: 'create', component: ComplaintOrQueryResolutionCreatePageComponent, canActivate:[UserAccessGuard]  },
  { path: 'view', component: ComplaintOrQueryResolutionCreatePageComponent, canActivate:[UserAccessGuard]  },
  { path: 'edit', component: ComplaintOrQueryResolutionCreatePageComponent, canActivate:[UserAccessGuard]  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ComplaintOrQueryResolutionRoutingModule { }
