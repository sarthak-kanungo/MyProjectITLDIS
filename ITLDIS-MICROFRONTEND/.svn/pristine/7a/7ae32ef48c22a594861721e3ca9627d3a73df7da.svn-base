import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpareDescripancyClaimRoutingModule } from './spare-descripancy-claim-routing.module';
import { SearchSpareDescripancyClaimComponent } from './component/search-spare-descripancy-claim/search-spare-descripancy-claim.component';
import { CreateSpareDescripancyClaimComponent } from './component/create-spare-descripancy-claim/create-spare-descripancy-claim.component';
import { PageSpareDescripancyClaimComponent } from './component/page-spare-descripancy-claim/page-spare-descripancy-claim.component';
import { ItemDetailsSpareDescripancyClaimComponent } from './component/item-details-spare-descripancy-claim/item-details-spare-descripancy-claim.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/app.module';
import { NgswSearchTableModule } from 'ngsw-search-table';
import { ToastrModule } from 'ngx-toastr';
import { UploadImageComponent } from './component/upload-image/upload-image.component';
import { ApproveSpareDescripancyClaimComponent } from './component/approve-spare-descripancy-claim/approve-spare-descripancy-claim.component';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { APP_DATE_FORMATS, AppDateAdapter } from 'src/app/date.adapter';


@NgModule({
  declarations: [SearchSpareDescripancyClaimComponent, CreateSpareDescripancyClaimComponent, PageSpareDescripancyClaimComponent, ItemDetailsSpareDescripancyClaimComponent, UploadImageComponent, ApproveSpareDescripancyClaimComponent],
  imports: [
    CommonModule,
    SpareDescripancyClaimRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    FormsModule,
    NgswSearchTableModule,
    ToastrModule,
 
  ],
  providers: [
    { provide: DateAdapter, useClass: AppDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ]
})
export class SpareDescripancyClaimModule { }
