import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { JobCardViewModalComponent } from '../../../direct-survey/component/job-card-view-modal/job-card-view-modal.component';
import { PcrViewModalComponent } from '../../../direct-survey/component/pcr-view-modal/pcr-view-modal.component';
import { WcrViewModalComponent } from '../../../direct-survey/component/wcr-view-modal/wcr-view-modal.component';
import { TollFreeService } from '../../service/toll-free.service';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';

@Component({
  selector: 'app-free-service-history',
  templateUrl: './free-service-history.component.html',
  styleUrls: ['./free-service-history.component.css']
})
export class FreeServiceHistoryComponent implements OnInit {
  @Input()
  freeServiceHistoryForm: FormArray;
  @Input()
  callHistoryForm: FormArray;

  serviceHistory: any;
  callHistory: any;
  tollfreeCreateAddressDetailsForm: FormGroup;
  constructor(private service: TollFreeService, private pagePresenter: TollFreePagePresenter,
              public dialog: MatDialog,) { }

  ngOnInit() {
    this.service.fetchServiceCallHistorySubject.subscribe(obj => {
      if (obj['customerId'] != null && obj['vinId']!=null) {
        this.service.getServiceHistory(obj['customerId'], obj['vinId']).subscribe(response => {
          this.serviceHistory = response['result'];
          this.freeServiceHistoryForm.clear();
          this.serviceHistory.forEach(element => {
            this.pagePresenter.addRowServiceHistory(element);
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


