import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateMachineTransportComponent } from './create-machine-transport/create-machine-transport.component';
import { SearchMachineTransportComponent } from './search-machine-transport/search-machine-transport.component';


const routes: Routes = [
  {path:'',component:SearchMachineTransportComponent},
  {path:'create',component:CreateMachineTransportComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MachineTransportRoutingModule { }
