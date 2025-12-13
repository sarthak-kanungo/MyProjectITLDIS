import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';

@Component({
  selector: 'app-purchase-order',
  templateUrl: './purchase-order.component.html',
  styleUrls: ['./purchase-order.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class PurchaseOrderComponent implements OnInit {
  
  zones: string[] = [
    'All', 'Zone 1', 'Zone 2',
    ];  

    regions: string[] = [
    'All', 'Region 1', 'Region 2'
  ];

  dealers: string[] = [
    'All', 'Dealer 1', 'Dealer 2',
  ];

  
  

  purchaseOrderApprovalFrom : FormGroup;

  constructor(private fb : FormBuilder,
   ) { }

  ngOnInit() {
    this.createpurchaseOrderApprovalFrom();
  }

  createpurchaseOrderApprovalFrom(){
    this.purchaseOrderApprovalFrom =  this.fb.group({
      zone: ['', Validators.compose([])],
      region: ['', Validators.compose([])],
      area:['',Validators.compose([])],
      territory:['',Validators.compose([])],
      dealer: ['', Validators.compose([])],
      fromDate:['',Validators.compose([])],
      toDate:['',Validators.compose([])],
    })
  }


}