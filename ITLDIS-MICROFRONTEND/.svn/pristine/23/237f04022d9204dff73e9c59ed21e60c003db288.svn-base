import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DirectSurveyService } from '../../direct-survey.service';

@Component({
  selector: 'app-wcr-view-modal',
  templateUrl: './wcr-view-modal.component.html',
  styleUrls: ['./wcr-view-modal.component.css']
})
export class WcrViewModalComponent implements OnInit {

  view:boolean
  wcrHeader:any[]=[]
  wcrImplements:any[]=[]
  serviceHistory:any[]=[]
  labourCharge:any[]=[]
  failureParts:any[]=[]

  constructor( public dialogRef: MatDialogRef<WcrViewModalComponent>,
               @Inject(MAT_DIALOG_DATA) public data: any,
               private surveyService: DirectSurveyService) { 
                this.getWcrForView(data.wcrNo)
               }

  ngOnInit() {
    // this.surveyService.jcPcrWcr.subscribe(wcr=>{
    //   this.getWcrForView(wcr)
    //   })
  }

  private getWcrForView(id: string) {
    // WCR/C01380/2021/00009
      this.surveyService.viewWCR(id).subscribe(res => {
      if (res['result']!=null) {
        this.view =true
        this.wcrHeader.push(res['result'].warrantyWcrView)
        this.wcrImplements = res['result'].warrantyWcrView.warrantyGoodwill.warrantyPcr.warrantyPcrImplements
        if (res['result'].warrantyWcrView.warrantyGoodwill.warrantyPcr.serviceJobCard.machineInventory.vinId) {
          this.surveyService.viewPCRHistory(res['result'].warrantyWcrView.warrantyGoodwill.warrantyPcr.serviceJobCard.machineInventory.vinId).subscribe(history=>{
            this.serviceHistory = history['result']
          })
        }
        this.labourCharge = res['result'].labourCharge
        this.failureParts = res['result'].warrantyPart
      }
    })
  }


  closeDialog(): void {
    this.dialogRef.close();
  }

}
