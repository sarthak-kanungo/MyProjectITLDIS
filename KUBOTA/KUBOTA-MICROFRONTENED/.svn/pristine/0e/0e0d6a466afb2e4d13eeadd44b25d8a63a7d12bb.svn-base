import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddDesignationLevelComponent } from './components/add-designation-level/add-designation-level.component';
import { DesignationLevelMasterComponent } from './pages/designation-level-master/designation-level-master.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: DesignationLevelMasterComponent,canActivate: [UserAccessGuard]},
  { path: 'create', component: AddDesignationLevelComponent,canActivate: [UserAccessGuard]},
  { path: 'view/:viewId', component: AddDesignationLevelComponent,canActivate: [UserAccessGuard]},
  { path: 'update/:updateId', component: AddDesignationLevelComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DesignationLevelMasterRoutingModule { }
