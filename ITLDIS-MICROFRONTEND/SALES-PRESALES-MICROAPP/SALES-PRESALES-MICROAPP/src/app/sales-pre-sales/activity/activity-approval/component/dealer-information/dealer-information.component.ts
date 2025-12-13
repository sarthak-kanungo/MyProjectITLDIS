import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { DealerMaster } from 'approval';

@Component({
  selector: 'app-dealer-information',
  templateUrl: './dealer-information.component.html',
  styleUrls: ['./dealer-information.component.scss'],
  providers: [
    {
      provide: DateAdapter, useClass: AppDateAdapter
    },
    {
      provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
  ]
})

export class DealerInformationComponent implements OnInit, OnChanges {

  dealerDetailsFrom : FormGroup
  @Input() dealer : DealerMaster
  constructor(
    private fb : FormBuilder
  ) { }

  ngOnChanges(){
    if(this.dealer){
      this.dealerDetailsFrom.patchValue(this.dealer)
    }
  }

  ngOnInit() {
    this.createActivityApproval()
  }

  createActivityApproval() {
    this.dealerDetailsFrom = this.fb.group({
      dealerCode: [{ value: '', disabled: true }],
      dealerName: [{ value: '', disabled: true }],
      state: [{ value: '', disabled: true }],
    })
  }

}
