import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { MachineFormfComponent } from './machine-formf.component';


const routes: Routes = [
  {path:'', component: MachineFormfComponent, canActivate:[UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineFormfRoutingModule { 
  
}
