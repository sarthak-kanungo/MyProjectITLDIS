import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartQuotationSearchPageComponent } from './component/part-quotation-search-page/part-quotation-search-page.component';
import { PartQuotationPageComponent } from './component/part-quotation-page/part-quotation-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path:'', component : PartQuotationSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component : PartQuotationPageComponent, canActivate: [UserAccessGuard]},
  {path:'view/:id', component : PartQuotationPageComponent, canActivate: [UserAccessGuard]},
  {path:'edit/:id', component : PartQuotationPageComponent, canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartsQuotationRoutingModule { }
