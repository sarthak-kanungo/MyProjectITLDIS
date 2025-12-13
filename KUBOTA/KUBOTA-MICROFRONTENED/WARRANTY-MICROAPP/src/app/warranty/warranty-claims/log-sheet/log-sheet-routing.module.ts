import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LogSheetSearchPageComponent } from './component/log-sheet-search-page/log-sheet-search-page.component';
import { LogSheetPageComponent } from './component/log-sheet-page/log-sheet-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '',component:LogSheetSearchPageComponent, canActivate: [UserAccessGuard]},
  {path:'create', component:LogSheetPageComponent, canActivate: [UserAccessGuard]},
  {path:'view', component:LogSheetPageComponent, canActivate: [UserAccessGuard]},
  {path:'edit', component:LogSheetPageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LogSheetRoutingModule { }
