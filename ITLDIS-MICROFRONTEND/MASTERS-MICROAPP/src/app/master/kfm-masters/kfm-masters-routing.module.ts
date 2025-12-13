import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';
import { ChannelFinanceLimitUploadComponent } from './Channel-Finance-Limit-Upload/component/channel-finance-limit-upload/channel-finance-limit-upload.component';


const routes: Routes = [
    {path:'channelfinancelimitupload', component:ChannelFinanceLimitUploadComponent, canActivate: [UserAccessGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KFMMastersRoutingModule { }