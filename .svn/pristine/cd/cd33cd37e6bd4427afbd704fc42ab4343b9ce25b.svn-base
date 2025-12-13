import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MarketIntelligenceCreateComponent } from './component/market-intelligence-create/market-intelligence-create.component';
import { MarketIntelligenceSearchComponent } from './component/market-intelligence-search/market-intelligence-search.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  { path: '', component: MarketIntelligenceSearchComponent,canActivate: [UserAccessGuard] },
  { path: 'create', component: MarketIntelligenceCreateComponent,canActivate: [UserAccessGuard] },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MarketIntelligenceRoutingModule { }
