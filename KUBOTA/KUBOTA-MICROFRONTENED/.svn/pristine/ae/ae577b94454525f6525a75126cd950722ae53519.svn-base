import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchSpareDescripancyClaimComponent } from './component/search-spare-descripancy-claim/search-spare-descripancy-claim.component';
import { CreateSpareDescripancyClaimComponent } from './component/create-spare-descripancy-claim/create-spare-descripancy-claim.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchSpareDescripancyClaimComponent,canActivate: [UserAccessGuard]},
  {path:'create',component:CreateSpareDescripancyClaimComponent,canActivate: [UserAccessGuard]},
  {path:'edit',component:CreateSpareDescripancyClaimComponent,canActivate:[UserAccessGuard]},
  {path:'view',component:CreateSpareDescripancyClaimComponent,canActivate:[UserAccessGuard]},
  {path:'approve',component:CreateSpareDescripancyClaimComponent,canActivate:[UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpareDescripancyClaimRoutingModule { }
