import { Component, OnInit } from '@angular/core';
import { backOrderPresenter } from '../create-back-order-cancellation-page/back-order-presenter';
import { FormGroup } from '@angular/forms';
import { LoginFormService } from 'src/app/root-service/login-form.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-item-details-back-order-cancellation',
  templateUrl: './item-details-back-order-cancellation.component.html',
  styleUrls: ['./item-details-back-order-cancellation.component.css']
})
export class ItemDetailsBackOrderCancellationComponent implements OnInit {
  public itemDetailsArray: Array<FormGroup> = [];
  constructor(
    private presenter:backOrderPresenter,
    private toaster:ToastrService
  ) { }

  ngOnInit() {
    
    this.presenter.itemDetailsGroup.subscribe((res: Array<FormGroup>) => {
      this.itemDetailsArray = res;
      console.log(this.itemDetailsArray,'item Array')
    })
    
  }
  
  validateCancelQty(event:any,fg:any){
       let pendingQty=fg.get('pendingOrderQty').value;
       let cancelQty=fg.get('cancelQty').value;
       if(cancelQty>pendingQty){
        this.toaster.warning('Cancel Qty is not Greater than pending Qty');
        fg.get('cancelQty').reset();
        return false;
       }
       
  }
  preventAlphaNumeric(event:any){
    const pattern = /[0-9]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
        event.preventDefault();
    }
    
  }
}
