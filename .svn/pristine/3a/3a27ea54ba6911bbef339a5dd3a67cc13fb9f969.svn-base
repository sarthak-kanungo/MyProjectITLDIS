import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SparesRoutingModule } from './spares-routing.module';
import { ToastrModule } from 'ngx-toastr';
import { ModalFileUploadComponent } from './Utility/modal-file-upload/modal-file-upload.component';
import { ItemErrorReportComponent } from './Utility/item-error-report/item-error-report.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialTreeDemoComponent } from './material-tree-demo/material-tree-demo.component';
import { MaterialModule } from '../app.module';
import { MatTreeModule } from '@angular/material/tree';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [ModalFileUploadComponent, ItemErrorReportComponent, MaterialTreeDemoComponent],
  imports: [
    CommonModule,
    SparesRoutingModule,
    ToastrModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    MatTreeModule,
    MatIconModule, 
    MatButtonModule
  ],
  entryComponents: [ModalFileUploadComponent, ItemErrorReportComponent],
})
export class SparesModule { }
