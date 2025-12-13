import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BayMasterComponent } from './pages/bay-master/bay-master.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path: '', component:BayMasterComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BayMasterRoutingModule { }
