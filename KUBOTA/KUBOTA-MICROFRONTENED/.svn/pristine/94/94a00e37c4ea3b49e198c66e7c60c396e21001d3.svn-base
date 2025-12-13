import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchDealerTerritoryMappingComponent } from './component/search-dealer-territory-mapping/search-dealer-territory-mapping.component';
import { CreateDealerTerritoryMappingComponent } from './component/create-dealer-territory-mapping/create-dealer-territory-mapping.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'',component:SearchDealerTerritoryMappingComponent, canActivate: [UserAccessGuard]},
  {path:'create',component:CreateDealerTerritoryMappingComponent,canActivate: [UserAccessGuard]},
  {path:'edit/:territoryNo',component:CreateDealerTerritoryMappingComponent,canActivate: [UserAccessGuard]},
  {path:'view/:territoryNo',component:CreateDealerTerritoryMappingComponent,canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerTerritoryMappingRoutingModule { }
