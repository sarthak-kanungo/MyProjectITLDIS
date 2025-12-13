import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QuotationListSearchComponent } from './quotation-list-search/quotation-list-search.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: QuotationListSearchComponent, canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuotationListRoutingModule { }
