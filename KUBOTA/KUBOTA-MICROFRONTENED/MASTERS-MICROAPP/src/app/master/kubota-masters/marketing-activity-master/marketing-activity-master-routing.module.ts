import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MarketingActivityMasterSearchComponent } from './pages/marketing-activity-master-search/marketing-activity-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MarketingActivityMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MarketingActivityMasterRoutingModule { }
