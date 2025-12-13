import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KaiInspectionSheetCreatePageComponent } from './component/kai-inspection-sheet-create-page/kai-inspection-sheet-create-page.component';
import { KaiInspectionSheetSearchPageComponent } from './component/kai-inspection-sheet-search-page/kai-inspection-sheet-search-page.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  {path: '', component: KaiInspectionSheetSearchPageComponent, canActivate: [UserAccessGuard]},
  {path: 'create', component: KaiInspectionSheetCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'view', component: KaiInspectionSheetCreatePageComponent, canActivate: [UserAccessGuard]},
  {path: 'approve', component: KaiInspectionSheetCreatePageComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KaiInspectionSheetRoutingModule { }
