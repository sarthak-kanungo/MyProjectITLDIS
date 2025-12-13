import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {path: 'training' , loadChildren:()=> import ('./training/training.module').then(mod=>mod.TrainingModule)},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingSectionRoutingModule { }
