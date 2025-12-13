import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AllDealerAutionlistComponent } from './all-dealer-autionlist/all-dealer-autionlist.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:AllDealerAutionlistComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerallAuctionPartDetailsRoutingModule { }
