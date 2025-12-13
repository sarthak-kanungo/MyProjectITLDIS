import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { EmployeeBankDetailCreateComponent } from './component/employee-bank-detail-create/employee-bank-detail-create.component';
import { EmployeeBankDetailSearchComponent } from './component/employee-bank-detail-search/employee-bank-detail-search.component';


const routes: Routes = [
  { path: '', component:  EmployeeBankDetailSearchComponent,canActivate: [UserAccessGuard]},
  { path: 'create', component: EmployeeBankDetailCreateComponent,canActivate: [UserAccessGuard]},
 

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeBankDetailRoutingModule { }
