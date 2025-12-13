import { Component, OnInit, Input } from '@angular/core';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';

@Component({
  selector: 'app-approval-details',
  templateUrl: './approval-details.component.html',
  styleUrls: ['./approval-details.component.scss']
})
export class ApprovalDetailsComponent implements OnInit {

  @Input() approvalHierDetails : any[]
  isKaiUser:boolean = true
  
  constructor(private presenter: WcrPagePresenter){
    
  }

  ngOnInit() {
      if(this.presenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }

}
