import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ButtonAction, ConfirmDialogData, ConfirmationBoxComponent } from '../../../../../confirmation-box/confirmation-box.component';
import { EnquiryFollowupService } from '../../enquiry-followup.service';
import { AddEnquiryFollowupContainerService } from '../../component/add-enquiry-followup-container/add-enquiry-followup-container.service';
import { CurrentEnquiryFollowupContainerService } from '../../component/current-enquiry-followup-container/current-enquiry-followup-container.service';
import { LostDropEnquiryContainerService } from '../../component/lost-drop-enquiry-container/lost-drop-enquiry-container.service';
import { CurrentEnquiryFollowupDomain } from 'CurrentEnquiryFolloeup';
import { LostDropEnquiryDomain } from 'LostDropEnquiry';
import { EnquiryFollowupCreateService } from './enquiry-followup-create.service';
import { FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoginFormService } from '../../../../../root-service/login-form.service';import { FollowupHistoryService } from '../../component/followup-history/followup-history.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FollowupHistoryDomain } from 'FolloupHistory';

@Component({
  selector: 'app-enquiry-followup-create',
  templateUrl: './enquiry-followup-create.component.html',
  styleUrls: ['./enquiry-followup-create.component.scss'],
  providers: [AddEnquiryFollowupContainerService, CurrentEnquiryFollowupContainerService, LostDropEnquiryContainerService, EnquiryFollowupCreateService, FollowupHistoryService]
})
export class EnquiryFollowupCreateComponent implements OnInit {
  isEdit: boolean;
  isView: boolean;
  data: Object;
  currentvalidStatus: boolean
  lostDropvalidStatus: boolean
  loginUserId: number;
  isSelectFollowup: boolean
  isSelectLostDrop: boolean
  currentFollowup: FormGroup
  lostdropFollowup: FormGroup
  followupHistoryDomain : Array<FollowupHistoryDomain>
  constructor(
    public dialog: MatDialog,
    private enquiryFollowupService: EnquiryFollowupService,
    private addEnquiryFollowupContainerService: AddEnquiryFollowupContainerService,
    private currentEnquiryFollowupContainerService: CurrentEnquiryFollowupContainerService,
    private lostDropEnquiryContainerService: LostDropEnquiryContainerService,
    private enquiryFollowupCreateService: EnquiryFollowupCreateService,
    private toastr: ToastrService,
    private loginFormService: LoginFormService,
    private enqRt: ActivatedRoute,
    private followupHistoryService : FollowupHistoryService,
    private router : Router
  ) {
    this.loginUserId = this.loginFormService.getLoginUserId()
  }

  ngOnInit() {
  }

  SelectForDisplayCards(event) {
    console.log('event', event);
    if (event === 'Follow Up') {
      this.isSelectFollowup = !this.isSelectFollowup
    } else {
      this.isSelectFollowup = false
    }
    if (event === 'Lost/Drop') {
      this.isSelectLostDrop = !this.isSelectLostDrop
    } else {
      this.isSelectLostDrop = false
    }
  }

  validateForm(functionalKey: string) {
    this.enquiryFollowupService.submitOrResetEnquiryForm(functionalKey)
    console.log(this.currentFollowup);
    console.log(this.lostdropFollowup);
    if (this.currentvalidStatus === true && (this.currentFollowup)) {
      this.openConfirmDialog();
    }else if (this.lostDropvalidStatus === true && (this.lostdropFollowup)) {
      this.openConfirmDialog();
    } else if ((this.lostDropvalidStatus === false && (this.lostdropFollowup)) || (this.currentvalidStatus === false && (this.currentFollowup))) {         
          this.toastr.error(`Please fill all the mandatory fields`, 'Report mandatory fields')          
    }
  }

  submitFollowupFormAndReset() {
    if (this.lostDropEnquiryContainerService.lostDropEnquiryDomain.result) {
      this.addEnquiryLostDrop()
      console.log('submit', this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain);
      this.enquiryFollowupCreateService.postEnquiryLostDropData(this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain).subscribe(formData => {
        console.log(formData);
        if (formData) {
          this.toastr.success(`Follow up Enquiry Added Successfully`, 'Success')
          this.router.navigate(['../../'], { relativeTo: this.enqRt })
        }
      })
    } else {
      this.addEnquiryFollowUp()
      console.log('submit', this.addEnquiryFollowupContainerService.addEnquiryFollowUp);
      this.enquiryFollowupCreateService.postEnquiryFollowupData(this.addEnquiryFollowupContainerService.addEnquiryFollowUp).subscribe(formData => {
        console.log(formData);
        if (formData) {
          this.toastr.success(`Follow up Enquiry Added Successfully`, 'Success')
          this.parseEnqNoAndFollowUpHistory()
          this.router.navigate(['../../'], { relativeTo: this.enqRt })
        }
      })
    }
  }

  private parseEnqNoAndFollowUpHistory() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enq']))
  }

  private fatchDataForEnqNo(enq: string) {
    this.followupHistoryService.followupHistorye(`` + enq).subscribe(dto => { 
      console.log('dto',dto);
     this.followupHistoryDomain = dto.result
     })
  }

  private openConfirmDialog(): void | any {
    let message = 'Do you want to Add Enquiry Follow Up?';
    if(this.lostdropFollowup){
       if(this.lostDropEnquiryContainerService.lostDropEnquiryDomain.result === 'Lost')
           message = 'Do you want to Lost Enquiry?';
       else if(this.lostDropEnquiryContainerService.lostDropEnquiryDomain.result === 'Drop')
           message = 'Do you want to Drop Enquiry?';
       else 
           message = 'Do you want to Lost/Drop Enquiry?';
    }
    const confirmationData = this.setConfirmationModalData(message);
    const dialogRef = this.dialog.open(ConfirmationBoxComponent, {
      width: '500px',
      panelClass: 'confirmation_modal',
      data: confirmationData
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      if (result === 'Confirm') {
        this.submitFollowupFormAndReset();
      }
    });
  }
  private setConfirmationModalData(message: string) {
    const confirmationData = {} as ConfirmDialogData;
    confirmationData.buttonAction = [] as Array<ButtonAction>;
    confirmationData.title = 'Confirmation';
    confirmationData.message = message;
    confirmationData.buttonName = ['Cancel', 'Confirm'];
    return confirmationData;
  }

  getEnquiryFollowupData(event) {
    console.log('event===>', event);
    this.addEnquiryFollowupContainerService.enquiry = {
      id: event.event.id
    }
  }

  geturrentFollowupData(event) {
    this.currentFollowup = event.form
    this.currentvalidStatus = event.event.validStatus
    console.log("currentFollowup ", this.currentFollowup);
    this.currentEnquiryFollowupContainerService.currentEnquiryFollowupDomain = event.event.formData as CurrentEnquiryFollowupDomain
  }

  getLostDropData(event) {
    this.lostdropFollowup = event.form
    this.lostDropvalidStatus = event.event.validStatus
    console.log("lostdropFollowup ", this.lostdropFollowup);
    this.lostDropEnquiryContainerService.lostDropEnquiryDomain = event.event.formData as LostDropEnquiryDomain
  }
  private addEnquiryFollowUp() {
    const enquiryFollwup = {
      ...this.addEnquiryFollowupContainerService.addEnquiryFollowUp,
      ...this.currentEnquiryFollowupContainerService.currentEnquiryFollowupDomain,
    }
    this.addEnquiryFollowupContainerService.addEnquiryFollowUp = enquiryFollwup;
    
    /*this.addEnquiryFollowupContainerService.addEnquiryFollowUp.createdBy = {
      id: this.loginUserId
    }*/
    this.addEnquiryFollowupContainerService.addEnquiryFollowUp.enquiry = this.addEnquiryFollowupContainerService.enquiry
    console.log('addenquiryfollwup', this.addEnquiryFollowupContainerService.addEnquiryFollowUp);

  }

  private addEnquiryLostDrop() {
    const enquiryLostDrop = {
      ...this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain,
      ...this.lostDropEnquiryContainerService.lostDropEnquiryDomain
    }
    this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain = enquiryLostDrop
    this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain.enquiry = this.addEnquiryFollowupContainerService.enquiry
    console.log('addenquirylostdrop', this.addEnquiryFollowupContainerService.addEnquiryLostDropDomain);
  }

  onClickExit(){
    this.router.navigate(['../../'], { relativeTo: this.enqRt })
  }

}