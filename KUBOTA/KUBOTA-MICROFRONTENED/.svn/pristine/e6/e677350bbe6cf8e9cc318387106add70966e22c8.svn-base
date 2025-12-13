import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StoreSearchPageComponent } from './component/store-search-page/store-search-page.component';
import { StoreCreatePageComponent } from './component/store-create-page/store-create-page.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'', component: StoreSearchPageComponent},
  {path:'create', component: StoreCreatePageComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StoreMasterRoutingModule { }
