import { Component, OnInit, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FollowupHistory } from '../../domains/enquiry';

export interface BtnAction {
  buttonName: string;
  clickHandler: Observable<any>;
  errorHandler?: (errorRes?: any) => void;
  webCall?: any;
  webCallUrl?: string;
  webCallType?: string;
  data?: any;
}

@Component({
  selector: 'app-followup-history-dialog',
  templateUrl: './followup-history-dialog.component.html',
  styleUrls: ['./followup-history-dialog.component.scss']
})
export class FollowupHistoryDialogComponent implements OnInit {

  followupHistory: Array<FollowupHistory>
  isFollowupHistrory: boolean
  followupHistoryData: string

  constructor(
    public dialogRef: MatDialogRef<FollowupHistoryDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.getFollowUpHistory()
  }

  private getFollowUpHistory() {
    console.log(this.data);
    this.followupHistory = this.data.followupHistory
    console.log(" this.followupHistory ", this.followupHistory);
    if (this.data.followupHistory.length > 0) {
      this.isFollowupHistrory = true
    } else {
      this.isFollowupHistrory = false
      this.followupHistoryData = 'Follow up Histrory Not Available'
    }
  }

  close() {
    this.dialogRef.close();
  }

}
