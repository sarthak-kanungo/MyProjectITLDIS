import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TransporterMasterSearchComponent } from './pages/transporter-master-search/transporter-master-search.component';
import { TransporterMasterCreateComponent } from './pages/transporter-master-create/transporter-master-create.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path: '', component:TransporterMasterSearchComponent,canActivate: [UserAccessGuard] },
  {path: 'create', component:TransporterMasterCreateComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransporterMasterRoutingModule { }
