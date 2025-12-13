import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";



const routes: Routes = [
    { path: 'model-survey-master', loadChildren: () => import('./model-survey-master/model-survey-master.module').then(mod => mod.ModelSurveyMasterModule) },
    { path: 'question-master', loadChildren: () => import('./question-master/question-master.module').then(mod => mod.QuestionMasterModule) },
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class CrmMasterRoutingModule { }