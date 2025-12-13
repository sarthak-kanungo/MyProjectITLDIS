import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserAccessGuard } from "src/app/auth/user-access.guard";
import { MachineStockSearchPageComponent } from "./component/machine-stock-search-page/machine-stock-search-page.component";

const routes: Routes = [
    { path: '', component: MachineStockSearchPageComponent, canActivate: [UserAccessGuard] },
    { path: 'create', component: MachineStockSearchPageComponent, canActivate: [UserAccessGuard] },
    { path: 'edit/:id', component: MachineStockSearchPageComponent, canActivate: [UserAccessGuard] },
    { path: 'view/:id', component: MachineStockSearchPageComponent, canActivate: [UserAccessGuard] }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class MachineStockRoutingModule { }
  