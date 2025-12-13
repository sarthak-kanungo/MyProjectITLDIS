import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { QuotationSearchComponent } from './pages/quotation-search/quotation-search.component';
import { QuotationCreateComponent } from './pages/quotation-create/quotation-create.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [

  { path: '', component: QuotationSearchComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: QuotationCreateComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:viewId', component: QuotationCreateComponent, canActivate: [UserAccessGuard] }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuotationRoutingModule { }
