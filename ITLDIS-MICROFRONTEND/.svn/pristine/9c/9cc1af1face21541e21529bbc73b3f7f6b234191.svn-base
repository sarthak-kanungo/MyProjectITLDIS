import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { MaterialModule } from 'src/app/app.module';
import { ToastrModule } from 'ngx-toastr';
import { EditableTableModule } from 'editable-table';
import { UiModule } from '../../../ui/ui.module';
import { PickListRoutingModule } from './pick-list-routing.module';
import { PickListComponent } from './component/pick-list/pick-list.component';
import { PickListPageComponent } from './pick-list-page/pick-list-page.component';
import { PlItemDetailsComponent } from './pl-item-details/pl-item-details.component';
import { PicklistSearchComponent } from './picklist-search/picklist-search.component';
import { PicklistSearchPageComponent } from './picklist-search-page/picklist-search-page.component';
import { ItemPopUpComponent } from './item-pop-up/item-pop-up.component';

@NgModule({
  declarations: [ItemPopUpComponent,PickListComponent, PickListPageComponent, PlItemDetailsComponent, PicklistSearchComponent, PicklistSearchPageComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    NgswSearchTableModule,
    ToastrModule,
    EditableTableModule,
    UiModule,PickListRoutingModule
  ],
  entryComponents: [ItemPopUpComponent]
})
export class PickListModule { }
