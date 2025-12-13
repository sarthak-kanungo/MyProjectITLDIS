import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TransporterPageComponent } from './components/transporter-page/transporter-page.component';
import { TransporterSearchPageComponent } from './components/transporter-search-page/transporter-search-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: TransporterSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: TransporterPageComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransporterMasterRoutingModule { }
