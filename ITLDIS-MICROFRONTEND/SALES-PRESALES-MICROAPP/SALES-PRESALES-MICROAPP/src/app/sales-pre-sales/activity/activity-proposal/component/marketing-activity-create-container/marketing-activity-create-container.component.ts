import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { MarketingActivityCreateContainerService } from './marketing-activity-create-container.service';
import { ViewEditActivityProposalDomain } from 'ActivityProposalModule';
import { MatSelectChange } from '@angular/material';

@Component({
  selector: 'app-marketing-activity-create-container',
  templateUrl: './marketing-activity-create-container.component.html',
  styleUrls: ['./marketing-activity-create-container.component.scss'],
  providers: [MarketingActivityCreateContainerService]
})
export class MarketingActivityCreateContainerComponent implements OnInit {

  @Input() budget: number
  @Input() claimableAmount:number
  @Output() selectActivityEvent = new EventEmitter<MatSelectChange>()
  @Input() viewEditActivityProposal: ViewEditActivityProposalDomain
  //submit
  @Output() sendData = new EventEmitter<object>()
  
  @Output() sendMaximumLimitEvent = new EventEmitter<number>()

  constructor(
  ) { }

  ngOnInit() {
  }

  getActivityEvent(event) {
    this.selectActivityEvent.emit(event)
  }

  //submit
  getFormStatusandData(event) {
    this.sendData.emit({ form: 'activityProposal', event: event })
  }

  sendMaxLimitEvent(event) { 
      this.sendMaximumLimitEvent.emit(event);
  }
}
