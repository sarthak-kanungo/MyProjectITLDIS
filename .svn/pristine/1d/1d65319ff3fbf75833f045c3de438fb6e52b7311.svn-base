import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PsiFieldMasterSearchComponent } from './pages/psi-field-master-search/psi-field-master-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: PsiFieldMasterSearchComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PsiFieldMasterRoutingModule { }
