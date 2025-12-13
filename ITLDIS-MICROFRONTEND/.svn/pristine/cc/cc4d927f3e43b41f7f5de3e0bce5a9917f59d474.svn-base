import { Component, OnInit,Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';

@Component({
  selector: 'app-labour-charges',
  templateUrl: './labour-charges.component.html',
  styleUrls: ['./labour-charges.component.scss']
})
export class LabourChargesComponent implements OnInit {

  @Input() labourChargeForm : FormArray
  isKaiUser:boolean = true
  @Input() isView:boolean
  constructor(private pcrPagePresenter: GoodwillPagePresenter) { }

  ngOnInit() {
      if(this.pcrPagePresenter.loginUser.dealerCode){
          
          this.isKaiUser = false;
      }
  }

}
