import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DirectSurveyService } from '../../direct-survey.service';

@Component({
  selector: 'app-pcr-view-modal',
  templateUrl: './pcr-view-modal.component.html',
  styleUrls: ['./pcr-view-modal.component.css']
})
export class PcrViewModalComponent implements OnInit {

  view:boolean
  pcrHeader:any[]=[]
  partImplement:any[]=[]
  serviceHistory:any[]=[]
  failureParts:any[]=[]
  labourCharge:any[]=[]
  outSidlabourCharge:any[]=[]

  constructor( public dialogRef: MatDialogRef<PcrViewModalComponent>,
               @Inject(MAT_DIALOG_DATA) public data: any,
               private surveyService: DirectSurveyService) {
                   this.getpcrForView(data.pcrNo)
              }

  ngOnInit() {
    // this.surveyService.jcPcrWcr.subscribe(jc=>{
    //   this.getpcrForView(jc)
    //   })
  }

  private getpcrForView(id: string) {
    // 'PCR/C01380/2021/00018'
		this.surveyService.viewPCR(id).subscribe(res => {
      if (res['result']!=null) {
        this.view =true
        this.pcrHeader.push(res['result'].warrantyPcrViewDto)
        this.partImplement = res['result'].warrantyPcrViewDto.warrantyPcrImplements
        this.failureParts = res['result'].warrantyPart
        this.labourCharge = res['result'].labourCharge
        this.outSidlabourCharge =  res['result'].outSideLabourCharge
        if (res['result'].warrantyPcrViewDto.serviceJobCard.machineInventory.vinId) {
          this.surveyService.viewPCRHistory(res['result'].warrantyPcrViewDto.serviceJobCard.machineInventory.vinId).subscribe(history=>{
            this.serviceHistory = history['result']
          })
        }
      }
    })
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
