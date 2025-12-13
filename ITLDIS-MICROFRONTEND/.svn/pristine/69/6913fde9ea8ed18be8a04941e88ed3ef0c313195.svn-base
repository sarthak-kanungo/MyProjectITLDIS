import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PartIssueRoutingModule } from './part-issue-routing.module';
import { PartIssuePageComponent } from './component/part-issue-page/part-issue-page.component';
import { PartIssueSearchPageComponent } from './component/part-issue-search-page/part-issue-search-page.component';
import { MaterialModule } from '../../../app.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PartIssueSearchComponent } from './component/part-issue-search/part-issue-search.component';
import { PartIssueComponent } from './component/part-issue/part-issue.component';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { UiModule } from '../../../ui/ui.module';
import { PartIssueItemComponent } from './component/part-issue-item/part-issue-item.component';
import { ItemPopUpComponent } from './component/item-pop-up/item-pop-up.component';

@NgModule({
  declarations: [ItemPopUpComponent,PartIssuePageComponent, PartIssueSearchPageComponent, PartIssueSearchComponent, PartIssueComponent, PartIssueItemComponent],
  imports: [
    CommonModule,
    PartIssueRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    UiModule
  ],
  entryComponents: [ItemPopUpComponent]
})
export class PartIssueModule { }
