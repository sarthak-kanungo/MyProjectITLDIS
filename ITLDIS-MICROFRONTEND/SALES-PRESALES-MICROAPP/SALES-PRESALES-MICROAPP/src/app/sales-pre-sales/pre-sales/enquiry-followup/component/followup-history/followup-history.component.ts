import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FollowupHistoryService } from './followup-history.service';
import { FollowupHistoryDomain } from 'FolloupHistory';

@Component({
  selector: 'app-followup-history',
  templateUrl: './followup-history.component.html',
  styleUrls: ['./followup-history.component.scss'],
  providers: [FollowupHistoryService]
})

export class FollowupHistoryComponent implements OnInit {
  isAddFolloUp : boolean
  @Input() followupHistoryDomain : Array<FollowupHistoryDomain>
  constructor(
    private enqRt: ActivatedRoute,
    private followupHistoryService : FollowupHistoryService
  ) { }

  ngOnInit() {
    this.checkOperationType()
    this.FollowUpHistory()
  }

  private FollowUpHistory() {
    if (this.isAddFolloUp) {
      console.log('AddFollowUp Form');
      this.parseEnqNoAndFollowUpHistory()
    }
  }

  private parseEnqNoAndFollowUpHistory() {
    this.enqRt.params.subscribe(prms => this.fatchDataForEnqNo(prms['enq']))
  }

  private fatchDataForEnqNo(enq: string) {
    this.followupHistoryService.followupHistorye(`` + enq).subscribe(dto => { 
      console.log('dto',dto);
      this.followupHistoryDomain = dto.result
     })
  }
 
  private checkOperationType() {
    this.isAddFolloUp = this.enqRt.snapshot.routeConfig.path.split('/')[0] == 'followup'
  }

}