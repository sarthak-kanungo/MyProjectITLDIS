import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ToastrService } from 'ngx-toastr';
import { ComplaintOrQueryResolutionService } from '../../service/complaint-or-query-resolution.service';

@Component({
  selector: 'app-complaint-resolution-popup',
  templateUrl: './complaint-resolution-popup.component.html',
  styleUrls: ['./complaint-resolution-popup.component.css']
})
export class ComplaintResolutionPopupComponent implements OnInit {
  
  constructor(private formBuilder: FormBuilder,
    private service: ComplaintOrQueryResolutionService,
    private toastr : ToastrService,
    private dialogRef: MatDialogRef<ComplaintResolutionPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  complaintResolutionForm:FormGroup = this.formBuilder.group({
    frtStatus : [{value:"Called", disabled:true}],
    reasonForDelayFrt : [null],
    isInvalid : [null],
    actionTaken : [null,Validators.required],
    reasonForDelayArt : [null]
  })
  reasonDelayFRT:any[]
  reasonDelayART:any[]
  complaintId:any
  complaintType:any
  ngOnInit() {

    this.service.getLookupByCode('REASON_FRT_DELAY').subscribe(response => {
      this.reasonDelayFRT = response['result'];
    })
    this.service.getLookupByCode('REASON_ART_DELAY').subscribe(response => {
      this.reasonDelayART = response['result'];
    })
    if(this.data.frt){
      this.complaintResolutionForm.get('reasonForDelayFrt').setValidators(Validators.compose([Validators.required]));
    }
    if(this.data.art){
      this.complaintResolutionForm.get('reasonForDelayArt').setValidators(Validators.compose([Validators.required]));
    }
    this.complaintId = this.data.complaintId;
    this.complaintType = this.data.complaintType;
  }
  closeDialog(){
    this.dialogRef.close();
  }

  updateComplaintResolutionDetails(){
    this.complaintResolutionForm.markAllAsTouched();
    if(this.complaintResolutionForm.valid){
      const resolutionDetails = {...this.complaintResolutionForm.getRawValue(), complaintId: this.complaintId, complaintType: this.complaintType}
      this.service.updateResolutionDetails(resolutionDetails).subscribe(resppnse => {
        this.toastr.success('Details updated successfuly','Success');
        this.dialogRef.close({data:"OK"});
      })
    }else{
      this.toastr.error('Enter Mandatory Fields','Mandatory Fields');
    }
  }
}
