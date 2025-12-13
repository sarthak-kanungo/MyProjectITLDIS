import { Component, OnInit, Input, EventEmitter,Output } from '@angular/core';
import { FormArray, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';
import { Operation } from '../../../../../utils/operation-util';
@Component({
  selector: 'app-outside-labour-charges',
  templateUrl: './outside-labour-charges.component.html',
  styleUrls: ['./outside-labour-charges.component.scss']
})
export class OutsideLabourChargesComponent implements OnInit {
  @Input() public outsideLabourForm: FormArray;
  viewName: string;
  dealerCode: any;
  operation: string;
  isShowKaiRemarks = true;
  isKaiUser:boolean = true;
  @Input() isView:boolean
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private presenter: GoodwillPagePresenter,
  ) { }

  ngOnInit() {
    this.activeRoute();
    if(this.presenter.loginUser.dealerCode){
        this.isKaiUser = false;
    }
  }
  private activeRoute() {
    this.dealerCode = this.presenter.loginUser.dealerCode;
    
   this.activatedRoute.queryParams.subscribe((qParam) => {
      this.viewName = qParam.name;
   })
 }

}
