import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { JobCardViewModalComponent } from '../../../direct-survey/component/job-card-view-modal/job-card-view-modal.component';
import { PcrViewModalComponent } from '../../../direct-survey/component/pcr-view-modal/pcr-view-modal.component';
import { WcrViewModalComponent } from '../../../direct-survey/component/wcr-view-modal/wcr-view-modal.component';
import { DelearCustomerCareExCallService } from '../../service/delear-customer-care-ex-call.service';
import { DelearCustomerCareExCallPagePresenter } from '../create-delear-customer-care-ex-call/delear-customer-care-ex-call-page.prensenter';

@Component({
  selector: 'app-free-service-history',
  templateUrl: './free-service-history.component.html',
  styleUrls: ['./free-service-history.component.css']
})
export class FreeServiceHistoryComponent implements OnInit {
  @Input()
  freeServiceHistoryForm:FormArray;
  @Input()
  callHistoryForm:FormArray;
  
  serviceHistory:any;
  callHistory:any;

  constructor(
    private pagePresenter : DelearCustomerCareExCallPagePresenter,
    private service: DelearCustomerCareExCallService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.service.fetchServiceCallHistorySubject.subscribe(obj => {
      if(obj['customerId'] && obj['customerId']!=null && obj['vinId']!=null){
        this.service.getServiceHistory(obj['customerId'], obj['vinId']).subscribe(response => {
          this.serviceHistory = response['result'];
          this.freeServiceHistoryForm.clear();
          this.serviceHistory.forEach(element => {
            this.pagePresenter.addRowServiceHistory(element);
          });
        });
        this.service.getCallHistory(obj['customerId'], obj['vinId']).subscribe(response => {
          this.callHistory = response['result'];
          this.callHistoryForm.clear();
          this.callHistory.forEach(element => {
            this.pagePresenter.addRowCallHistory(element);
          });
        });
      }
    });
  }

  
  jcModal(event): void {
    //this.service.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(JobCardViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'jobcode':event.target.innerHTML},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

  pcrModal(event): void {
    this.service.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(PcrViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: '',
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

  wcrModal(event): void {
    this.service.jcPcrWcr.next(event.target.innerHTML)
    const dialogRef = this.dialog.open(WcrViewModalComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: '',
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }
}
