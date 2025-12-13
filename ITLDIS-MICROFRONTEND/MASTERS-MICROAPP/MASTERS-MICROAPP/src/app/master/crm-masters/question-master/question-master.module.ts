import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MaterialModule } from "src/app/app.module";
import { QuestionMasterCreatePageComponent } from "./component/question-master-create-page/question-master-create-page.component";
import { QuestionMasterSearchPageComponent } from "./component/question-master-search-page/question-master-search-page.component";
import { QuestionMasterRoutingModule } from "./question-master-routing.module";
import { QuestionMasterSearchTableComponent } from './component/question-master-search-table/question-master-search-table.component';
@NgModule({
    declarations:[QuestionMasterSearchPageComponent, QuestionMasterCreatePageComponent, QuestionMasterSearchTableComponent],
    imports: [
        CommonModule,
        NgswSearchTableModule,
        FormsModule,
        ReactiveFormsModule,
        MaterialModule,
        QuestionMasterRoutingModule
      ]
})
export class QuestionMasterModule { }