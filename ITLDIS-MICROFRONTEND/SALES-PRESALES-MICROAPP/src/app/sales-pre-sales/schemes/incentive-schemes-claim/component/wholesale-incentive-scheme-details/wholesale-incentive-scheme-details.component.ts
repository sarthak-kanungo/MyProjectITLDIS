import { Component, OnInit, Input } from '@angular/core';
import { WholesaleIncentiveSchemeDetails } from 'IncentiveSchemeClaimModule';

@Component({
  selector: 'app-wholesale-incentive-scheme-details',
  templateUrl: './wholesale-incentive-scheme-details.component.html',
  styleUrls: ['./wholesale-incentive-scheme-details.component.scss']
})
export class WholesaleIncentiveSchemeDetailsComponent implements OnInit {

  @Input()
  wholesaleIncentiveSchemeDetails: WholesaleIncentiveSchemeDetails;

  constructor() { }

  ngOnInit() {
  }

}
