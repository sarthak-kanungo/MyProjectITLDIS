import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-claim-approval-search',
  templateUrl: './claim-approval-search.component.html',
  styleUrls: ['./claim-approval-search.component.scss']
})
export class ClaimApprovalSearchComponent implements OnInit {
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  selected = 10;
  constructor() { }

  ngOnInit() {
  }

}
