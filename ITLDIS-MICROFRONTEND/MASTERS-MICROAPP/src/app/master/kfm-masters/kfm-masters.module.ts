import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChannelFinanceLimitUploadComponent } from './Channel-Finance-Limit-Upload/component/channel-finance-limit-upload/channel-finance-limit-upload.component';
import { KFMMastersRoutingModule } from './kfm-masters-routing.module';
import { ConfirmationBoxModule } from 'src/app/confirmation-box/confirmation-box.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DynamicTableModule, MaterialModule } from 'src/app/ui/dynamic-table/dynamic-table.module';



@NgModule({
  declarations: [ChannelFinanceLimitUploadComponent],
  imports: [
    CommonModule,
    KFMMastersRoutingModule,
    ConfirmationBoxModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    DynamicTableModule,
  ]
})
export class KfmMastersModule { }
