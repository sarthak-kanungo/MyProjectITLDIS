import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { UserAccessGuard } from "src/app/auth/user-access.guard";
import { QuestionMasterCreatePageComponent } from "./component/question-master-create-page/question-master-create-page.component";
import { QuestionMasterSearchTableComponent } from "./component/question-master-search-table/question-master-search-table.component";

const routes: Routes = [
    { path: '', component: QuestionMasterSearchTableComponent, canActivate: [UserAccessGuard] },
    { path: 'create', component: QuestionMasterCreatePageComponent, canActivate: [UserAccessGuard] },
    { path: 'view', component: QuestionMasterCreatePageComponent, canActivate: [UserAccessGuard] },
    { path: 'edit', component: QuestionMasterCreatePageComponent, canActivate: [UserAccessGuard] }
]
@NgModule({
    imports:[RouterModule.forChild(routes)],
    exports:[RouterModule],
})
export class QuestionMasterRoutingModule {}