import { Component, OnInit,Input } from '@angular/core';
import { FormArray } from '@angular/forms';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';

@Component({
  selector: 'app-labour-charges',
  templateUrl: './labour-charges.component.html',
  styleUrls: ['./labour-charges.component.scss']
})
export class LabourChargesComponent implements OnInit {

  @Input() labourChargeForm : FormArray
  isKaiUser:boolean = true
  
  constructor(private pcrPagePresenter: PcrPagePresenter) { }

  ngOnInit() {
      if(this.pcrPagePresenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }

}
