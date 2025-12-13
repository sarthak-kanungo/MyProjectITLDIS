import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { InsertQueryParamsDirective } from './insert-query-params/insert-query-params.directive';



@NgModule({
  declarations: [FileUploadComponent, InsertQueryParamsDirective],
  imports: [
    CommonModule,
  ],
  exports: [InsertQueryParamsDirective]
})
export class UiModule { }
