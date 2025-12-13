import { NgModule } from '@angular/core';
import { NgswSearchTableComponent } from './ngsw-search-table.component';
import { CamelCaseToRegularStringPipe } from './pipe/camel-case-to-regular-string.pipe';
import { GetKeyValueFromObjectPipe } from './pipe/get-key-value-from-object.pipe';
import { SearchFieldDirective } from './directives/search-field.directive';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule, MatButtonModule, MatButtonToggleModule, MatCardModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatIconModule, MatInputModule, MatListModule, MatPaginatorModule, MatRadioModule, MatRippleModule, MatSelectModule, MatSortModule, MatTableModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { CommonModule } from '@angular/common';
import { NgswSearchTableService } from './ngsw-search-table.service';



@NgModule({
  declarations: [NgswSearchTableComponent, CamelCaseToRegularStringPipe,
    GetKeyValueFromObjectPipe,
    SearchFieldDirective],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    MatTooltipModule
  ],
  exports: [NgswSearchTableComponent, CamelCaseToRegularStringPipe, SearchFieldDirective],
  providers: [NgswSearchTableService]
})
export class NgswSearchTableModule { }
