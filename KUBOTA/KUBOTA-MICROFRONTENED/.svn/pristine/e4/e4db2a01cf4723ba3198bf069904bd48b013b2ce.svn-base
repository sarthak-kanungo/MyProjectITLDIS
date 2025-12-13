import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BackOrderService } from '../../service/back-order.service';
import { backOrderPresenter } from '../create-back-order-cancellation-page/back-order-presenter';
import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-back-order-cancellation',
  templateUrl: './create-back-order-cancellation.component.html',
  styleUrls: ['./create-back-order-cancellation.component.css'],

  
})
export class CreateBackOrderCancellationComponent implements OnInit {
@Input() backOrderCancellationForm:FormGroup
dealerList: any;
  itemDetailsList: any;
  isView:any
  isEdit:any;
  isCreate:any
  constructor(
    private service:BackOrderService,
     private activedRouter:ActivatedRoute,
    private presenter:backOrderPresenter,

  ) { }

  ngOnInit() {
    this.backOrderCancellationForm.get('dealerMaster').valueChanges.subscribe((res) => {
      if (res) {
        this.service.autoCompleteDealerCode(res).subscribe(response => {
          this.dealerList = response
        })
      }
    })
    this.checkFormOperation()
    
  }
  private checkFormOperation() {
    this.presenter.operation = OperationsUtil.operation(this.activedRouter);
    if (this.presenter.operation == Operation.VIEW) {
      this.isView = true;
      
      
    }
    else if (this.presenter.operation == Operation.EDIT) {
      this.isEdit = true
      
    }
    else if (this.presenter.operation == Operation.CREATE) {
      this.isCreate = true
      
    }

  }
  selectedDealer(event){
    console.log(event.option.value.dealerCode)
    this.service.autoCompleteDealerCode(event.option.value.dealerName).subscribe(response => {
    this.backOrderCancellationForm.get('dealerName').patchValue(response[0].dealerName)
    })
    this.presenter.itemDetailsArray.clear();
    this.presenter.itemDetailsGroup.next(null); 
    this.getItemDetailsData(event.option.value.dealerCode);
  }

  private getItemDetailsData(dealerCode:string){

  
  this.service.getItemDetailsFromDealer(`${dealerCode}`).subscribe(data=>{
    
    this.itemDetailsList=data
    
    if(this.itemDetailsList){
        
      this.itemDetailsList.forEach(itemData => {
       
           this.presenter.addRow(itemData)
          
      })
    }
  })
}

displayCodeFn(dealer) {
 
    return dealer? dealer.dealerCode : undefined
  }

}
