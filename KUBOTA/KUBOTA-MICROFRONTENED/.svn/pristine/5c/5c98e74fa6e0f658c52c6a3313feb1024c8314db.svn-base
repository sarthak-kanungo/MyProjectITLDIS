import { Component, OnInit, Input } from '@angular/core';
import { LoginFormService } from '../../../../../root-service/login-form.service';
@Component({
  selector: 'app-approval-details',
  templateUrl: './approval-details.component.html',
  styleUrls: ['./approval-details.component.scss']
})
export class ApprovalDetailsComponent implements OnInit {

  public loginUserType: string;
  @Input() approvalHierDetails : any
  constructor(
    private loginFormService: LoginFormService,
  ){
    this.loginUserType = this.loginFormService.getLoginUserType().toLowerCase()
  }

  ngOnInit() {
      
  }

}
