import { Component, OnInit, Input } from '@angular/core';
import { FormArray } from '@angular/forms';

@Component({
  selector: 'app-log-sheet-service-history',
  templateUrl: './log-sheet-service-history.component.html',
  styleUrls: ['./log-sheet-service-history.component.scss']
})
export class LogSheetServiceHistoryComponent implements OnInit {

  @Input() serviceHistoryForm: FormArray;
  serviceHistoryHeading = [];
  constructor() { }

  ngOnInit() {
    this.serviceHistoryHeading = ['Sl.No','Date In', 'Type oF Service', 'Hours ', 'Job Card No', 'Job Card Date'];
  }

}
