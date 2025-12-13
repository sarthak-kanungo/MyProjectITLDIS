import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { DeliverableChecklistService } from './deliverable-checklist.service';
import { DeliveryChallanCreateService } from '../../pages/delivery-challan-create/delivery-challan-create.service';

@Component({
  selector: 'app-deliverable-checklist',
  templateUrl: './deliverable-checklist.component.html',
  styleUrls: ['./deliverable-checklist.component.scss']
})
export class DeliverableChecklistComponent implements OnInit, OnChanges {
  @Input() public deliverableCheckList = [];
  @Input() public isView: boolean = false;

  constructor(
    private deliveryChallanCreateService: DeliveryChallanCreateService
  ) { }

  ngOnChanges(changes: SimpleChanges) {
    this.deliverableCheckList = changes.deliverableCheckList.currentValue;
    if(this.isView){
        if(this.deliverableCheckList!=null){
            this.deliverableCheckList.forEach(element => {
                element.deliveryItem = element.dcDeliverableChecklistMaster.deliverableChecklist;
            })
        }
     }
  }
  ngOnInit() {
    if(!this.isView){  
        this.deliveryChallanCreateService.getMachineCustomerAndCheklistDataFromEnq.subscribe(data => {
          if (data !== null) {
            this.deliverableCheckList = data['deliverableChecklistResponse'];
            if (this.deliverableCheckList && this.deliverableCheckList.length > 0) {
              this.deliverableCheckList.forEach(ele => {
                ele['isDelivered'] = false;
              })
              this.deliveryChallanCreateService.deliveryChallanMainData.deliverableChecklist = this.deliverableCheckList;
            }
    
          }
        })
        this.clearTocuhedForm();
    }
    
  }
  private clearTocuhedForm() {
    this.deliveryChallanCreateService.clearOrMarkAsTouchedAll.subscribe(value => {
      if (value && value['key'] === 'clear') {
        this.deliverableCheckList = [];
        return;
      }
    })
  }
}
