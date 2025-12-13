import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { ViewEditActivityProposalDomain } from 'ActivityProposalModule';
import { MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-marketing-activity-addproduct-container',
  templateUrl: './marketing-activity-addproduct-container.component.html',
  styleUrls: ['./marketing-activity-addproduct-container.component.scss']
})
export class MarketingActivityAddproductContainerComponent implements OnInit {

  @Input() selectActivityTypeEvent : MatSelectChange
  @Output() fianlAmount: EventEmitter<number> = new EventEmitter<number>()
  @Output() senditemData = new EventEmitter<object>()
  @Input() viewEditActivityProposal : ViewEditActivityProposalDomain
  @Output() noHeadRepeat: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() validForm:boolean
  @Output() isActivityCategoryKai : boolean
  constructor() { }

  ngOnInit() {
  }
  totalAmount(event) {
    this.fianlAmount.emit(event)
  }

  getFormStatusandData(event) {
    this.senditemData.emit({ form: 'itemDetails', event: event })
    console.log("form__status", event.validStatus)
  }

  noReapeatedHead(event){
    this.noHeadRepeat.emit(event);
  }
}
