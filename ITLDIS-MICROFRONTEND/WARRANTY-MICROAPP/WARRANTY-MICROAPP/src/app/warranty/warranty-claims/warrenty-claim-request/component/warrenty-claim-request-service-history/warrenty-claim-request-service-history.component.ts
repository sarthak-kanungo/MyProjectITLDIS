import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-warrenty-claim-request-service-history',
  templateUrl: './warrenty-claim-request-service-history.component.html',
  styleUrls: ['./warrenty-claim-request-service-history.component.scss']
})
export class WarrentyClaimRequestServiceHistoryComponent implements OnInit {
  @Input() serviceHistoryForm: FormArray;
  serviceHistoryHeading = ['Date In', 'Type of Service', 'Hours ', 'Job Card No', 'Job Card Date'];
  constructor() { }

  ngOnInit() {
  }


}
