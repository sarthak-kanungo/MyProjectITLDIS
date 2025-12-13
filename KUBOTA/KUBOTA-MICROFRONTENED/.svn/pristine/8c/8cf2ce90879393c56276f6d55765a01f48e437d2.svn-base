import { Component, OnInit, Input } from '@angular/core';
import { LoginFormService } from '../../../../../root-service/login-form.service';
import { ApprovalHierDetails} from 'ActivityProposalModule';
@Component({
  selector: 'app-approval-details',
  templateUrl: './approval-details.component.html',
  styleUrls: ['./approval-details.component.scss']
})
export class ApprovalDetailsComponent implements OnInit {

  public loginUserType: string;
  @Input() approvalHierDetails : ApprovalHierDetails[]
  @Input() isActivityCategoryKai : boolean
  constructor(
    private loginFormService: LoginFormService,
  ){
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
  }

  ngOnInit() {
      
  }

}
