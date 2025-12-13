import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DealerDesignationMasterSearchPageComponent } from './component/dealer-designation-master-search-page/dealer-designation-master-search-page.component';
import { DealerDesignationMasterPageComponent } from './component/dealer-designation-master-page/dealer-designation-master-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DealerDesignationMasterSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: DealerDesignationMasterPageComponent,canActivate: [UserAccessGuard] },
  { path: 'view', component: DealerDesignationMasterPageComponent,canActivate: [UserAccessGuard] },
  { path: 'edit', component: DealerDesignationMasterPageComponent,canActivate: [UserAccessGuard] },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerDesignationMasterRoutingModule { }
