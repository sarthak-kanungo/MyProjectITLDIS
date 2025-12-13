import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SearchChannelFinanceIndentComponent } from './pages/search-channel-finance-indent/search-channel-finance-indent.component';
import { CreateChannelFinanceIndentComponent } from './pages/create-channel-finance-indent/create-channel-finance-indent.component';
import { UserAccessGuard } from '../../../auth/user-access.guard';


const routes: Routes = [
  { path: '', component: SearchChannelFinanceIndentComponent, canActivate: [UserAccessGuard] },
  { path: 'create', component: CreateChannelFinanceIndentComponent, canActivate: [UserAccessGuard] },
  { path: 'view/:id', component: CreateChannelFinanceIndentComponent, canActivate: [UserAccessGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChannelFinanceIndentRoutingModule { }
