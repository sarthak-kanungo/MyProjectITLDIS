import { Component, OnInit, Input } from '@angular/core';
import { Observable, Subscriber } from 'rxjs';
import { GoodwillPagePresenter } from '../goodwill-create-page/goodwill-create-page.presenter';

@Component({
  selector: 'app-approval-details',
  templateUrl: './approval-details.component.html',
  styleUrls: ['./approval-details.component.scss']
})
export class ApprovalDetailsComponent implements OnInit {

  @Input() approvalHierDetails : any[]
  isKaiUser:boolean = true
  
  constructor(private presenter: GoodwillPagePresenter){
  //  let time = new Observable<string>((observer:Subscriber<string>)=> {
//      setTimeout(() => observer.next((new Date()).toString()),1000)
    //})
  }

  ngOnInit() {
      if(this.presenter.loginUser.dealerCode){
          this.isKaiUser = false;
      }
  }

}
