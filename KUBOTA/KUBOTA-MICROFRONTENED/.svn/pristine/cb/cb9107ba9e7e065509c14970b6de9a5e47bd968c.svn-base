import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  {path:'training-program-calendar',loadChildren:()=>import('./training-programme-calender/training-prog-calender.module').then(mod=>mod.TrainingProgrammeCalenderModule)},
  {path:'training-nomination',loadChildren:()=>import('./training-nomination/training-nomination.module').then(mod=>mod.TrainingnominationModule)},
  {path:'attendance-sheet',loadChildren:()=>import('./attendance-sheet/attendance-sheet.module').then(mod=>mod.TrainingattendanceSheetModule)},
  {path:'training-program-report',loadChildren:()=>import('./attendance-training-report/attendance-training-report.module').then(mod=>mod.TrainingattendanceTrainingReportModule)},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainingRoutingModule { }
