import { DatePipe } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-approve-spare-descripancy-claim',
  templateUrl: './approve-spare-descripancy-claim.component.html',
  styleUrls: ['./approve-spare-descripancy-claim.component.css'],
  providers:[DatePipe]
})
export class ApproveSpareDescripancyClaimComponent implements OnInit {
   @Input() spareDescApprovalForm:FormGroup
  constructor(
    private datePipe:DatePipe
  ) { }

  ngOnInit() {
    let date=new Date();
    this.spareDescApprovalForm.get('kaiAcceptanceDate').patchValue(this.datePipe.transform(date,'dd-MM-yyyy'))
    this.spareDescApprovalForm.get('kaiSettlementDate').patchValue(this.datePipe.transform(date,'dd-MM-yyyy'))
  }

}
