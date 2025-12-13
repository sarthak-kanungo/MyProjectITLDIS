import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { MachineServiceHistoryComponent } from './component/machine-service-history.component';


const routes: Routes = [{
  path:'', component: MachineServiceHistoryComponent, canActivate:[UserAccessGuard]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineServiceHistoryRoutingModule { }
