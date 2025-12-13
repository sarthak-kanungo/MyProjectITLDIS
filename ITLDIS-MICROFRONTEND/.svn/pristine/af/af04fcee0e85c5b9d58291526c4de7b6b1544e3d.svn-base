import { Component, OnInit } from '@angular/core';
import { DateAdapter, MAT_DATE_FORMATS, MatDialog } from '@angular/material';
import { AppDateAdapter, APP_DATE_FORMATS } from '../../../../../date.adapter';
import { FormGroup, FormBuilder } from '@angular/forms';
import { SearchActivityProposalService } from './search-activity-proposal.service';

@Component({
  selector: 'app-search-activity-proposal',
  templateUrl: './search-activity-proposal.component.html',
  styleUrls: ['./search-activity-proposal.component.scss'],
  providers: [SearchActivityProposalService]
  // providers: [
  //   {
  //       provide: DateAdapter, useClass: AppDateAdapter
  //   },
  //   {
  //       provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS
  //   }
  //   ],

})

export class SearchActivityProposalComponent implements OnInit {
  recordPerPageList: Array<number> = [5, 10, 25, 50];
  // selected = 10
  statuss: string[] = [
    'Approve', 'Reject', 'Pending'
  ];

  activityTypes: string[] = [
    'Kubota Challange', 'Demo', 'Feel the Difference', 'Big Elephant Small Elephant',
    'Exp/ Exhibition', 'Newspaper', 'Magazine', 'Hoarding', 'Bus Branding', 'Digital Media',
    'Van Campaign', 'Combing', 'Burst Team'
  ];

  activityProposalList: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private searchActivityProposalService: SearchActivityProposalService) { }

  ngOnInit() {
    this.activityProposalList = this.searchActivityProposalService.searchActivityProposalList()
  }


}
