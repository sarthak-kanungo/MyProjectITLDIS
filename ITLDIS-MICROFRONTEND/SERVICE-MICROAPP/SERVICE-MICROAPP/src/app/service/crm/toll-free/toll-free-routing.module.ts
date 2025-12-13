import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { SearchTollFreeComponent } from './component/search-toll-free/search-toll-free.component';
import { CreateTollFreeComponent } from './component/create-toll-free/create-toll-free.component';


const routes: Routes = [
  { path: '', component: SearchTollFreeComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateTollFreeComponent, canActivate: [UserAccessGuard] },
  { path: 'view', component: CreateTollFreeComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TollFreeRoutingModule { }
