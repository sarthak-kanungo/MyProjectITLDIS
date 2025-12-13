import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewPartsClaimContainerComponent } from './container/new-parts-claim-container/new-parts-claim-container.component';
import { NewPartsClaimSearchContainerComponent } from './container/new-parts-claim-search-container/new-parts-claim-search-container.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component:NewPartsClaimSearchContainerComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:NewPartsClaimContainerComponent, canActivate: [UserAccessGuard]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NewPartsClaimRoutingModule { }
