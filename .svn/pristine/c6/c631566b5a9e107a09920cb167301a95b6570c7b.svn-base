import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoodwillSearchPageComponent } from './component/goodwill-search-page/goodwill-search-page.component';
import { GoodwillCreatePageComponent } from './component/goodwill-create-page/goodwill-create-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: GoodwillSearchPageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: GoodwillCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: GoodwillCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GoodwillRoutingModule { }
