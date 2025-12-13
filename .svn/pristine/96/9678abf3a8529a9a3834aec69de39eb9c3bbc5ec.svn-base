import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';


import { Operation, OperationsUtil } from 'src/app/utils/operation-util';
import { ActivatedRoute } from '@angular/router';

import { BackOrderService } from '../../../back-order-cancellation-request/service/back-order.service';
import { backOrderPresenter } from '../back-order-cancellation-approval-page/back-order-cancellation-approval-presenter';

@Component({
  selector: 'app-create-back-order-cancellation',
  templateUrl: './create-back-order-cancellation.component.html',
  styleUrls: ['./create-back-order-cancellation.component.css'],
  providers:[backOrderPresenter]
})
export class CreateBackOrderCancellationComponent implements OnInit {
  @Input() backOrderCancellationForm:FormGroup
  isView:any;
  isEdit:any;
  isCreate:any;
  itemDetailsList:any
  dealerList:any;
  constructor(
  private presenter:backOrderPresenter,
    private activatedRoute:ActivatedRoute,
    private service:BackOrderService
    ) { }

  ngOnInit() {

    this.backOrderCancellationForm.get('dealerCode').valueChanges.subscribe((res) => {
      if (res) {
        this.service.autoCompleteDealerCode(res).subscribe(response => {
          this.dealerList = response
         
        })
      }
    })
   
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
  
  displayCodeFn(dealer:any) {
      return dealer? dealer.dealerCode : undefined
    }

}
