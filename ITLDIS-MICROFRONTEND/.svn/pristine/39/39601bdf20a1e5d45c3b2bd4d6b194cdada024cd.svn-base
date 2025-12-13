import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserAccessGuard } from "src/app/auth/user-access.guard";
import { ModelSurveyMasterCreatePageComponent } from "./component/model-survey-master-create-page/model-survey-master-create-page.component";
import { ModelSurveyMasterSearchPageComponent } from "./component/model-survey-master-search-page/model-survey-master-search-page.component";

const routes: Routes = [
    { path: '', component: ModelSurveyMasterSearchPageComponent, canActivate: [UserAccessGuard] },
    { path: 'create', component: ModelSurveyMasterCreatePageComponent, canActivate: [UserAccessGuard] },
    { path: 'view', component: ModelSurveyMasterCreatePageComponent, canActivate: [UserAccessGuard] },
    { path: 'edit', component: ModelSurveyMasterCreatePageComponent, canActivate: [UserAccessGuard] }
]
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class ModelSurveyMasterRoutingModule { }