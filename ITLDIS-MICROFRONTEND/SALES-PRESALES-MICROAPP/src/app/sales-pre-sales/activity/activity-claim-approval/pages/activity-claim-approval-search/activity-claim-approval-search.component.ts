import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activity-claim-approval-search',
  templateUrl: './activity-claim-approval-search.component.html',
  styleUrls: ['./activity-claim-approval-search.component.scss']
})
export class ActivityClaimApprovalSearchComponent implements OnInit {

  constructor(
    private router : Router,
    private actRt : ActivatedRoute
  ) { }

  ngOnInit() {
    this.router.navigate(['../../../activity-claim'], {relativeTo : this.actRt})
  }
}
