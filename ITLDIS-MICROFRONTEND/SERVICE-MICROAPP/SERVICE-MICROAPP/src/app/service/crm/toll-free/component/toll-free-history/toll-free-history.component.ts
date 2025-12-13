import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material';
import { TollFreeService } from '../../service/toll-free.service';
import { CreateTollFreeComponent } from '../create-toll-free/create-toll-free.component';
import { TollFreePagePresenter } from '../create-toll-free/toll-free-page.prensenter';

@Component({
  selector: 'app-toll-free-history',
  templateUrl: './toll-free-history.component.html',
  styleUrls: ['./toll-free-history.component.css']
})
export class TollFreeHistoryComponent implements OnInit {
  tollFreeHistoryList: any[];
  @Input()
  tollFreeHistoryForm: FormArray;
  constructor(private service: TollFreeService, 
    private pagePresenter: TollFreePagePresenter,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.service.fetchServiceCallHistorySubject.subscribe(obj => {
      if(obj['customerId'] && obj['customerId']!=null && obj['vinId']!=null){
        this.service.getCallHistory(obj['customerId'], obj['vinId']).subscribe(response => {
          this.tollFreeHistoryList = response['result'];
          this.tollFreeHistoryForm.clear();
          this.tollFreeHistoryList.forEach(element => {
            this.pagePresenter.addRowCallHistory(element);
          });
        });
      }
    });
  }

  tollFreeModal(id): void {
    const dialogRef = this.dialog.open(CreateTollFreeComponent, {
      width: '90%',
      panelClass: 'confirmation_modal',
      data: {'callId':id},
      maxHeight: '80vh'
  });
    dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed', result);

    });
  }

}
