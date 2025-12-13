import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from '../../../auth/user-access.guard';
import { SourceSearchPageComponent } from './component/source-search-page/source-search-page.component';
import { SourcePageComponent } from './component/source-page/source-page.component';


const routes: Routes = [
  { path: '', component: SourceSearchPageComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: SourcePageComponent,canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SourceMasterRoutingModule { }
