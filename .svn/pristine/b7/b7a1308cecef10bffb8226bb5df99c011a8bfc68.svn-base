import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MailMasterSearchComponent } from './pages/mail-master-search/mail-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MailMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MailMasterRoutingModule { }
