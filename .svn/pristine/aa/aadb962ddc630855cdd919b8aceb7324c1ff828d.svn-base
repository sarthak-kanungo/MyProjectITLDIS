import { Component, OnInit, Input, EventEmitter,Output } from '@angular/core';
import { FormArray, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PcrPagePresenter } from '../pcr-page/pcr-page.presenter';
import { Operation } from '../../../../../utils/operation-util';
@Component({
  selector: 'app-outside-labour-charges',
  templateUrl: './outside-labour-charges.component.html',
  styleUrls: ['./outside-labour-charges.component.scss']
})
export class OutsideLabourChargesComponent implements OnInit {
  @Input() public outsideLabourForm: FormArray;
  @Input() public pcrForm: FormGroup;
  viewName: string;
  dealerCode: any;
  operation: string;
  isShowKaiRemarks = false;
  isKaiUser:boolean = true;
  @Output() allowVideoUplodEvent = new EventEmitter<boolean>(); 
  constructor(
    private activatedRoute: ActivatedRoute,
    private pcrPagePresenter: PcrPagePresenter,
  ) { }

  ngOnInit() {
    if(this.pcrPagePresenter.loginUser.dealerCode){
        this.isKaiUser = false;
    }
    this.activeRoute();
  }
  private activeRoute() {
    this.dealerCode = this.pcrPagePresenter.loginUser.dealerCode;
    
    this.checkFormOperation();
    
     this.activatedRoute.queryParams.subscribe((qParam) => {
      this.viewName = qParam.name;
    })
  }

  private checkFormOperation() {
    this.operation = this.pcrPagePresenter.operation;
    // if(this.operation == Operation.VIEW) {
    //   const kaiRemarks = this.pcrForm.get('kaiRemarks');
    //   this.pcrForm.get('kaiRemarks').statusChanges.subscribe(sts => {
    //     if(sts == 'DISABLED') {
    //       if(kaiRemarks.value == '' || kaiRemarks.value == null) {
    //         this.isShowKaiRemarks = false;
    //       }
    //       else {
    //         this.isShowKaiRemarks = true;
    //       }
    //     } 
    //     else {
    //       this.isShowKaiRemarks = true;
    //     }
    //   })
    //   kaiRemarks.setValidators(Validators.required);
    // }
    if(this.operation == Operation.VIEW) {
      this.isShowKaiRemarks = true
    } else if(this.isKaiUser){
      this.isShowKaiRemarks = true;
      this.pcrForm.get('kaiRemarks').enable();
      this.pcrForm.get('kaiRemarks').setValidators(Validators.required);
    } 
  }

  showHideButtonEvent(event){
      let isChecked = event.target.childNodes[0].checked;
      this.allowVideoUplodEvent.emit(!isChecked);
  }
}
