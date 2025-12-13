import { Component, OnInit } from '@angular/core';
import { ConfirmationBoxComponent, ConfirmDialogData, ButtonAction } from '../../../../../confirmation-box/confirmation-box.component';
import { MatDialog, DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';

@Component({
  selector: 'app-spare-parts-sales-return-invoice',
  templateUrl: './spare-parts-sales-return-invoice.component.html',
  styleUrls: ['./spare-parts-sales-return-invoice.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class SparePartsSalesReturnInvoiceComponent implements OnInit {

  isEdit: boolean;
  refdocs: string[] = [
    'Sales Order', 'Job Card'
  ];
  saletypes: string[]= [
    'Workshop', 'Counter','Field','Others'
  ];

  pricings: string[]= [
    'MRP', 'MO Price','EO Price'
  ];
  transporters: string[]= [
    'database 1', 'database 2','database 3'
  ];

  transports: string[]= [
    'database 1', 'database 2','database 3'
  ];

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  validateForm() {
   
    this.openConfirmDialog();
  }
  
  submitData() {
  }
  
  private openConfirmDialog(): void | any {
  let message = 'Do you want to submit Sales Invoice Return?';
  if (this.isEdit) {
    message = 'Do you want to update Sales Invoice Return?';
  }
  const confirmationData = this.setConfirmationModalData(message);
  // //console.log ('confirmationData', confirmationData);
  const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
    width: '500px',
    panelClass: 'confirmation_modal',
    data: confirmationData
  });
  
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);
    if (result === 'Confirm' && !this.isEdit) {
      this.submitData();
    }
    if (result === 'Confirm' && this.isEdit) {
      this.submitData();
    }
  });
  }
  private setConfirmationModalData(message: string) {
  const confirmationData = {} as ConfirmDialogData;
  confirmationData.buttonAction = [] as Array<ButtonAction>;
  confirmationData.title = 'Confirmation';
  confirmationData.message = message;
  confirmationData.buttonName = ['Confirm', 'Cancel'];
  // confirmationData.buttonAction.push(this.confimationButtonAction(buyMembership));
  // confirmationData.buttonAction.push(this.cancelButtonAction());
  return confirmationData;
  }
  
}
