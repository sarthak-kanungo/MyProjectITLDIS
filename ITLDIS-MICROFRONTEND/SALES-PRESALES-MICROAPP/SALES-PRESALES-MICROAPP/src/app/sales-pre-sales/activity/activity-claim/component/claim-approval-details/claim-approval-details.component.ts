import { Component, Input, OnInit } from '@angular/core';
import { LoginFormService } from '../../../../../root-service/login-form.service';

@Component({
  selector: 'app-claim-approval-details',
  templateUrl: './claim-approval-details.component.html',
  styleUrls: ['./claim-approval-details.component.scss']
})
export class ClaimApprovalDetailsComponent implements OnInit {
  public loginUserType: string;
  @Input()
  approvalDetails:[];
  constructor(
    private loginFormService: LoginFormService,
  ) {
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
   }

  ngOnInit() {
  }

}
