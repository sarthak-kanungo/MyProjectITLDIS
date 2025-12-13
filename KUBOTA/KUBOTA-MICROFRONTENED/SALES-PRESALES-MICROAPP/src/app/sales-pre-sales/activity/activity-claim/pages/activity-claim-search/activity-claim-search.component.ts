import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-activity-claim-search',
  templateUrl: './activity-claim-search.component.html',
  styleUrls: ['./activity-claim-search.component.scss']
})
export class ActivityClaimSearchComponent implements OnInit {

  constructor(
    private router:Router
  ) { }

  ngOnInit() {
  }
  createActivityClaim(){
    
    this.router.navigate(['/sales-pre-sales/activity/activity-claim/create'])
    
  }
}
