import { Component, OnInit } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { backOrderPresenter } from '../back-order-cancellation-approval-page/back-order-cancellation-approval-presenter';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-item-details-back-order-approval',
  templateUrl: './item-details-back-order-approval.component.html',
  styleUrls: ['./item-details-back-order-approval.component.css']
})
export class ItemDetailsBackOrderApprovalComponent implements OnInit {
    public itemDetailsArray: Array<FormGroup> = [];
  //  itemDetailsArray:any
  constructor(
    private presenter:backOrderPresenter,
    private toaster:ToastrService
  ) { }

  ngOnInit() {
    
    this.presenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
      
        this.itemDetailsArray = res;
       
      })



}
validateKaiQty(event:any,fg:any){
      let cancelQty=fg.get('cancelQty').value;
      let kaiAcceptQty=fg.get('kaiAcceptedQty').value;
      if(kaiAcceptQty>cancelQty){
        this.toaster.warning('Kai Accept Qty is Not Greater Than Cancel Qty');
        fg.get('kaiAcceptedQty').reset();
        return false;
      }
}

}
