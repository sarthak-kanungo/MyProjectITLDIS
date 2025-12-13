import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UpdateVehicleRegistrationSearchComponent } from './pages/update-vehicle-registration-search/update-vehicle-registration-search.component';
import { UpdateVehicleRegistrationCreateComponent } from './pages/update-vehicle-registration-create/update-vehicle-registration-create.component';
import { UserAccessGuard } from 'src/app/auth/user-access.guard';


const routes: Routes = [
  {path:'', component:UpdateVehicleRegistrationSearchComponent,canActivate: [UserAccessGuard]},
  {path:'create', component:UpdateVehicleRegistrationCreateComponent,canActivate: [UserAccessGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UpdateVehicleRegistrationRoutingModule { }
