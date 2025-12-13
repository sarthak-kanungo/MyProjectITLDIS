import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { InsertQueryParamsDirective } from './insert-query-params/insert-query-params.directive';
import { ImagePreviewComponent } from './image-preview/image-preview.component';
import { MatDialogModule } from '@angular/material';



@NgModule({
  declarations: [FileUploadComponent,InsertQueryParamsDirective,ImagePreviewComponent],
  imports: [
    CommonModule,
    MatDialogModule
  ],
  exports: [InsertQueryParamsDirective]
})
export class UiModule { }
