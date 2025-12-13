import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchViewTrasitDetailPageComponent } from './component/search-view-trasit-detail-page/search-view-trasit-detail-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchViewTrasitDetailPageComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewTransitDetailRoutingModule { }
