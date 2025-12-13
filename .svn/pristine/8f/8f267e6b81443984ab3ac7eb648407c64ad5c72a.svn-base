import { Component, OnInit, Input, EventEmitter,Output } from '@angular/core';
import { FormArray, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';
import { Operation } from '../../../../../utils/operation-util';
@Component({
  selector: 'app-outside-labour-charges',
  templateUrl: './outside-labour-charges.component.html',
  styleUrls: ['./outside-labour-charges.component.scss']
})
export class OutsideLabourChargesComponent implements OnInit {
  @Input() public outsideChargesForm: FormArray;
  
  viewName: string;
  dealerCode: any;
  operation: string;
  isShowKaiRemarks = true;
  isKaiUser:boolean = true;
  constructor(
    private activatedRoute: ActivatedRoute,
    private pagePresenter: WcrPagePresenter,
  ) { }

  ngOnInit() {
    this.activeRoute();
    if(this.pagePresenter.loginUser.dealerCode){
        this.isKaiUser = false;
    }
  }
  private activeRoute() {
    this.dealerCode = this.pagePresenter.loginUser.dealerCode;
    
     this.activatedRoute.queryParams.subscribe((qParam) => {
      this.viewName = qParam.name;
    })
  }
}
