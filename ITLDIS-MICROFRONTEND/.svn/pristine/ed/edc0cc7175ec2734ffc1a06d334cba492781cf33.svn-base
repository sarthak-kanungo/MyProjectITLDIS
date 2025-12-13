import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../auth/user-access.guard';


const routes: Routes = [

  { path: 'enquiry', loadChildren: () => import('./enquiry-v2/enquiry-v2.module').then(mod => mod.EnquiryV2Module), canActivate: [UserAccessGuard] },
  // {path: 'enquiry-list',loadChildren: ()=>import('./enquiry-list/enquiry-list.module').then(mod=>mod.EnquiryListModule)},
  // {path: 'enquiry-followup',loadChildren: ()=>import('./enquiry-followup/enquiry-followup.module').then(mod=>mod.EnquiryFollowupModule)},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PreSalesRoutingModule { }
