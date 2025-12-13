import { Component, OnInit, Input, AfterViewInit, AfterContentChecked } from '@angular/core';
import { of } from 'rxjs';
import { PcrPageWebService } from '../pcr-page/pcr-page-web.service';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';

@Component({
  selector: 'app-approval-details',
  templateUrl: './approval-details.component.html',
  styleUrls: ['./approval-details.component.scss']
})
export class ApprovalDetailsComponent implements OnInit {

  @Input() isApprove:boolean
  //@Input() approvalHierDetails : any[]
  approvalHierDetailsList : any[]
  @Input() public set approvalHierDetails(v:(string | object)[]){
    if (!v) {
      return;
    }
    of(v).subscribe(response => {
      this.approvalHierDetailsList = response;
      this.isApprovalRequired = this.approvalHierDetailsList[0]['mgmtApprovalCheck'];
    });
  }
  isKaiUser:boolean = true
  isApprovalRequired:string='NO'
  public managementCheck:boolean = false;
  constructor(private pcrPagePresenter: PcrPagePresenter,
    private pcrPageWebService: PcrPageWebService
    ){
    
  }
  ngOnInit() {
      if(this.pcrPagePresenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }
  checkManagment(event){
    this.managementCheck = !(this.managementCheck);
    this.pcrPageWebService.managementApprovalCheckSubject.next(this.managementCheck);
  }
}
