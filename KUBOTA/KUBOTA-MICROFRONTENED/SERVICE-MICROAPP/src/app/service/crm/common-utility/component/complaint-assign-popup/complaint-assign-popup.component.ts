import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TollFreeService } from '../../../toll-free/service/toll-free.service';

@Component({
  selector: 'app-complaint-assign-popup',
  templateUrl: './complaint-assign-popup.component.html',
  styleUrls: ['./complaint-assign-popup.component.css']
})
export class ComplaintAssignPopupComponent implements OnInit {
  reportingPersonList :any[];
  constructor(
    private dialogRef: MatDialogRef<ComplaintAssignPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private service: TollFreeService
  ) { }

  ngOnInit() {
    this.service.complaintReportingList(this.data['dealerId'], this.data['department']).subscribe(res => {
      this.reportingPersonList = res['result'];
    })
  }

  closeDialog(){
    this.dialogRef.close();
  }

  userSelection(houser){
    this.dialogRef.close({data:houser});
  }
}
