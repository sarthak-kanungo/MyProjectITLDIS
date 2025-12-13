import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TestssrRoutingModule } from './testssr-routing.module';
import { TestssrComponent } from './testssr/testssr.component';


@NgModule({
  declarations: [TestssrComponent],
  imports: [
    CommonModule,
    TestssrRoutingModule
  ]
})
export class TestssrModule { }
