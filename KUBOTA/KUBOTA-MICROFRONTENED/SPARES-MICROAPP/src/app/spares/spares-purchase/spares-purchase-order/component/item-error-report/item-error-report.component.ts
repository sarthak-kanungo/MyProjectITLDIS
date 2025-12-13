import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { SparesPOPartDetails } from '../../domain/spares-purchase-order.domain'

@Component({
  selector: 'app-item-error-report',
  templateUrl: './item-error-report.component.html',
  styleUrls: ['./item-error-report.component.scss']
})
export class ItemErrorReportComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<ItemErrorReportComponent>,
          @Inject(MAT_DIALOG_DATA) public data: Array<SparesPOPartDetails>) { }

  ngOnInit() {
       
  }
  
  public close(){
      this.dialogRef.close();
  }
}
