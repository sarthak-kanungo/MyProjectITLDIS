import { Component, OnInit, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';
@Component({
  selector: 'app-goodwill-remarks',
  templateUrl: './goodwill-remarks.component.html',
  styleUrls: ['./goodwill-remarks.component.scss']
})
export class GoodwillRemarksComponent implements OnInit {
  @Input() remarkForm: FormGroup;
  constructor(private presenter: GoodwillPagePresenter) { }
  isKaiUser:boolean = true;
  ngOnInit() {
      if(this.presenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }

}
