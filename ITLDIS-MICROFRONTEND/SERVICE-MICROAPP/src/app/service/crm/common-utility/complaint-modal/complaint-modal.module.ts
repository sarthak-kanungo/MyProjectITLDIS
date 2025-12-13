import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComplaintAssignPopupComponent } from '../component/complaint-assign-popup/complaint-assign-popup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material';
import { MaterialModule } from 'src/app/app.module';



@NgModule({
  declarations: [
    ComplaintAssignPopupComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    MatDialogModule,
  ]
})
export class ComplaintModalModule { }
