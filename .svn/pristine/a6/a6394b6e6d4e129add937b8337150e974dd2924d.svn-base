import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WcrPartsStatusComponent } from './wcr-parts-status.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [{path:'',component:WcrPartsStatusComponent,canActivate: [UserAccessGuard]}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WcrPartsStatusRoutingModule { }
