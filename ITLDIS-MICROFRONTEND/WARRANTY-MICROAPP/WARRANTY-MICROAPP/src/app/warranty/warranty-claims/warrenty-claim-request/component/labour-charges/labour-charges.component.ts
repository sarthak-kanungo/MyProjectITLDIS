import { Component, OnInit,Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { WcrPagePresenter } from '../warrenty-claim-request-create-page/warrenty-claim-request-create-page.presenter';

@Component({
  selector: 'app-labour-charges',
  templateUrl: './labour-charges.component.html',
  styleUrls: ['./labour-charges.component.scss']
})
export class LabourChargesComponent implements OnInit {

  @Input() labourChargeForm : FormArray
  isKaiUser:boolean = true
  
  constructor(private pagePresenter: WcrPagePresenter) { }

  ngOnInit() {
      if(this.pagePresenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }

}
