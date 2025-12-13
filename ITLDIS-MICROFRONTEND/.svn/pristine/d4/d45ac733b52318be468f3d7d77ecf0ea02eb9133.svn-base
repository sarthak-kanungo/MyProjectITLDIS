import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { IFrameMessageSource, IFrameService } from 'src/app/root-service/iFrame.service';
import { DirectSurveyService } from '../../direct-survey.service';

@Component({
  selector: 'app-job-card-view-modal',
  templateUrl: './job-card-view-modal.component.html',
  styleUrls: ['./job-card-view-modal.component.css']
})
export class JobCardViewModalComponent implements OnInit {
  safeUrl: SafeHtml;
  newsafeUrl: any;
  jcHealer:any[]=[]
  partRequisition:any[]=[]
  labourCharge:any[]=[]
  outsideJobCharge:any[]=[]
  view:boolean

  constructor(public dialogRef: MatDialogRef<JobCardViewModalComponent>,
             @Inject(MAT_DIALOG_DATA) public data: any,
             private iFrameService: IFrameService,
             private sanitizer: DomSanitizer,
             private surveyService: DirectSurveyService,
             ) {
                this.getJobCardForView(data.jobcode);
            }

  ngOnInit() {
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  private getJobCardForView(id: string) {
    // 'SJC/C01380/2122/0030'
		this.surveyService.viewJobCard(id).subscribe(res => {
      if (res['result']!=null) {
        this.view =true
      }
      this.jcHealer.push(res['result'])
      this.partRequisition = res['result'].sparePartRequisitionItem
      this.labourCharge = res['result'].labourCharge
      this.outsideJobCharge = res['result'].outsideJobCharge
    })
  }

}
