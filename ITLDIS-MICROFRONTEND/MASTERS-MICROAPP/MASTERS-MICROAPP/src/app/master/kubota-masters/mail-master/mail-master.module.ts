import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MailMasterRoutingModule } from './mail-master-routing.module';
import { MailMasterSearchComponent } from './pages/mail-master-search/mail-master-search.component';


@NgModule({
  declarations: [MailMasterSearchComponent],
  imports: [
    CommonModule,
    MailMasterRoutingModule
  ]
})
export class MailMasterModule { }
