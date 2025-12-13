import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCheckboxChange, MatDialog, MatDialogRef } from '@angular/material';
import { StorageLoginUser } from 'LoginDto';
import { InProgressComponent } from '../../pages/in-progress/in-progress.component';
import { ServiceClaimService } from '../../pages/service-claim.service';

@Component({
  selector: 'app-claim-job-card-details',
  templateUrl: './claim-job-card-details.component.html',
  styleUrls: ['./claim-job-card-details.component.scss']
})
export class ClaimJobCardDetailsComponent implements OnInit {
  headsTable = []
  rejectionReason:any[] = []
  @Input()
  isView: boolean;
  @Input()
  isApprove: boolean;
  @Input()
  loginUSer: StorageLoginUser
  allChecked:boolean=false
  isKai:boolean = true;
  jobCardDetailsForm:FormArray

  constructor(private claimService : ServiceClaimService,
    private dialog: MatDialog,
    private fb : FormBuilder) {

      this.jobCardDetailsForm = new FormArray([]);
      this.claimService.getLookup('CLAIM_REJECTION_REASON').subscribe(response => {
        this.rejectionReason = response['result'];
      })
     }

  ngOnInit() {
    this.claimService.documentDetailsBehaviourSubject.subscribe(data => {
      if(data){
        this.headsTable = data;
        this.headsTable.forEach(data => {
          let fg:FormGroup = this.fb.group({
              id:[{value:data.id, disabled:!this.isApprove}],
              rejectionReason : [{value:null, disabled:!this.isApprove}],
              remark:[{value:null, disabled:!this.isApprove}],
              selection:[{value:!data.selection, disabled:!this.isApprove}]
          })
          this.jobCardDetailsForm.push(fg)
        });
      }
    })
    if(this.loginUSer.dealerCode){
      this.isKai = false;
    }
  }
  addIdForApproval(id, e:MatCheckboxChange){
    if(e.checked == true){
      this.claimService.addIdForApprovalBehaviourSubject.next(id);
    }else{
      this.claimService.removeIdForApprovalBehaviourSubject.next(id);
    }
  }
  checkAll(e:MatCheckboxChange){
    if(e.checked == true){
     this.allChecked = true;
     this.headsTable.forEach(data => {
      this.claimService.addIdForApprovalBehaviourSubject.next(data.id);
     });
     (this.jobCardDetailsForm.controls as FormGroup[]).forEach(fg => {
      fg.get('rejectionReason').disable();
      fg.get('rejectionReason').clearValidators();
      fg.get('remark').clearValidators();
      fg.get('selection').patchValue(true);
     });
    }else{
      this.allChecked = false;
      this.headsTable.forEach(data => {
        this.claimService.removeIdForApprovalBehaviourSubject.next(data.id);
      });
      (this.jobCardDetailsForm.controls as FormGroup[]).forEach(fg => {
        fg.get('rejectionReason').enable();
        //fg.get('rejectionReason').setValidators(Validators.compose([Validators.required]))
        fg.get('remark').setValidators(Validators.compose([Validators.required]))
        fg.get('selection').patchValue(false);
      });
    }
  }
  showImageInPopup(event){
    
    const dialogRef = this.dialog.open(InProgressComponent, {
      minWidth: '20%',
      panelClass: 'confirmation_modal',
      data: event,
      maxHeight: '80vh'
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }
}
