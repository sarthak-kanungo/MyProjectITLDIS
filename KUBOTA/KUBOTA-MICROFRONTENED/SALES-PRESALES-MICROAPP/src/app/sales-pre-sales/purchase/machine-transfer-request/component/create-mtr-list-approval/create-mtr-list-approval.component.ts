import { Component, Input, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CreateMachineTransferRequestService } from '../../pages/create-machine-transfer-request/create-machine-transfer-request.service';

@Component({
  selector: 'app-create-mtr-list-approval',
  templateUrl: './create-mtr-list-approval.component.html',
  styleUrls: ['./create-mtr-list-approval.component.scss'],
  providers: [
    {
        provide: DateAdapter, useClass: AppDateAdapter
    },
    {
        provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
    }
    ]
})
export class CreateMtrListApprovalComponent implements OnInit {

    approvalList:any
    @Input()
    requestnumber:string;
    
    constructor(private machineTransferService : CreateMachineTransferRequestService) { }
    
    ngOnInit() { 
      this.machineTransferService.getApprovalDetails(this.requestnumber).subscribe(res => {
        this.approvalList = res['result'];
      })
    }

  }
  